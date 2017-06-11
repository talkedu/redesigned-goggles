package org.npj.campanha.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.npj.campanha.model.Campanha;
import org.npj.campanha.model.CampanhaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CampanhaService {
	
	@Autowired
	CampanhaDao campanhaDao;	
	
	
	public List<Campanha> salvarCampanha(Campanha campanha) {
		List<Campanha> campanhasComAMesmaVigencia = new ArrayList<Campanha>();
		return salvarCampanha(campanha, campanhasComAMesmaVigencia);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private List<Campanha> salvarCampanha(Campanha campanha, List<Campanha> campanhasComAMesmaVigencia) {
		
		List<Campanha> campanhasComAmesmaVigenciaNestaRecursao = campanhaDao.findByDataInicialAndDataFinal(campanha.getDataInicial(), campanha.getDataFinal());
		campanhasComAMesmaVigencia.addAll(campanhasComAmesmaVigenciaNestaRecursao);
		campanhaDao.save(campanha);
		
		if(!campanhasComAmesmaVigenciaNestaRecursao.isEmpty()) {
			for (Campanha campanhaComAMesmaVigencia : campanhasComAmesmaVigenciaNestaRecursao) {
				Calendar dataFinalCal = Calendar.getInstance();
				dataFinalCal.setTime(campanhaComAMesmaVigencia.getDataFinal());
				dataFinalCal.add(Calendar.DAY_OF_MONTH, 1);
				
				campanhaComAMesmaVigencia.setDataFinal(dataFinalCal.getTime());
				return salvarCampanha(campanhaComAMesmaVigencia, campanhasComAMesmaVigencia);
			}
		}
		
		return campanhasComAMesmaVigencia;	
	}	
	
	public List<Campanha> buscarCampanhas() {
		return campanhaDao.findAll().stream()
				.filter(c -> c.getDataFinal().after(Calendar.getInstance().getTime()))
				.collect(Collectors.toList());		
	}
	
	public Campanha buscarCampanha(Long IdCampanha) {
		Campanha campanha = campanhaDao.findById(IdCampanha);
		
		if(campanha == null || Calendar.getInstance().getTime().after(campanha.getDataFinal())) {
			return null;
		} else {
			return campanha;
		}
		
	}

	@Transactional
	public Campanha deletarCampanha(Long id) {
		
		Campanha campanha = campanhaDao.findById(id);
		
		if(campanha != null) {
			campanhaDao.delete(id);
		}
		
		return campanha;
	}
	

	
}
