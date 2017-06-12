package org.npj.sociotorcedor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.npj.sociotorcedor.model.Campanha;
import org.npj.sociotorcedor.model.SocioTorcedor;
import org.npj.sociotorcedor.model.SocioTorcedorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocioTorcedorService {

	@Autowired
	CampanhaService campanhaService;
	
	@Autowired
	SocioTorcedorDao socioTorcedorDao;
	
	public void salvarSocioTorcedor(SocioTorcedor socioTorcedor) {
		socioTorcedorDao.save(socioTorcedor);
	}
	
	public SocioTorcedor recuperarSocioTorcedorPeloEmail(String email) {
		return socioTorcedorDao.findByEmail(email);
	}

	public List<SocioTorcedor> buscarSociosTorcedores() {
		List<SocioTorcedor> socioTorcedores = socioTorcedorDao.findAll();
		
		socioTorcedores.forEach(s -> s.setCampanhasAssociadas(buscarCampanhasAssociadas(s)));
		
		return socioTorcedores;
	}
	
	public SocioTorcedor associarNovaCampanha(SocioTorcedor socioTorcedor, Campanha campanha) {
		socioTorcedor.addIdCampanhaAssociada(campanha.getId());
		socioTorcedor.setCampanhasAssociadas(buscarCampanhasAssociadas(socioTorcedor));
		socioTorcedorDao.save(socioTorcedor);
		return socioTorcedor;
	}

	public List<Campanha> buscarNovasCampanhas(SocioTorcedor socioTorcedor) {
		List<Campanha> campanhas = campanhaService.buscarCampanhas();
		if(campanhas != null) {
			return campanhaService.buscarCampanhas()
					.stream()
					.filter(c -> socioTorcedor.getIdTimeDoCoracao().equals(c.getIdTimeDoCoracao()))
					.collect(Collectors.toList());
		} else {
			return null;
		}
		
	}

	public List<Campanha> buscarCampanhasAssociadas(SocioTorcedor socioTorcedor) {
		List<Campanha> campanhasAssociadas = new ArrayList<>();
		
		socioTorcedor
			.getIdsCampanhasAssociadas()
			.forEach(c -> campanhasAssociadas.add(campanhaService.recuperarCampanhaPorId(c)));
		
		return campanhasAssociadas;
	}

	public SocioTorcedor recuperarSocioTorcedorPeloId(Long idSocioTorcedor) {
		SocioTorcedor socioTorcedor = socioTorcedorDao.findOne(idSocioTorcedor);
		return socioTorcedor;
	}

}
