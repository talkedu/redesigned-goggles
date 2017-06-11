package org.npj.sociotorcedor.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.npj.sociotorcedor.model.Campanha;
import org.npj.sociotorcedor.model.SocioTorcedor;
import org.npj.sociotorcedor.service.CampanhaService;
import org.npj.sociotorcedor.service.SocioTorcedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/socioTorcedor")
public class SocioTorcedorEndpoint {
	
	@Autowired
	SocioTorcedorService socioTorcedorService;
	
	@Autowired
	CampanhaService campanhaService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response incluir(SocioTorcedor socioTorcedor) {
		List<Campanha> novasCampanhas = socioTorcedorService.salvarSocioTorcedor(socioTorcedor);
		if(novasCampanhas.isEmpty()) {
			return Response.status(Status.CREATED).entity(novasCampanhas).build();
		} else {
			return Response.status(Status.OK).entity(novasCampanhas).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		List<SocioTorcedor> socioTorcedor = socioTorcedorService.buscarSociosTorcedores();
		return Response.status(Status.OK).entity(socioTorcedor).build();
	}
	
	@PUT
	@Path("{idSocioTorcedor}/{idCampanha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response associarNovaCampanha(@PathParam("idSocioTorcedor") Long idSocioTorcedor, @PathParam("idCampanha") Long idCampanha) {
		SocioTorcedor socioTorcedor = socioTorcedorService.recuperarSocioTorcedorPeloId(idSocioTorcedor);		
		if(socioTorcedor == null) {		
			return Response.status(Status.NOT_FOUND).entity(new SocioTorcedor(idSocioTorcedor)).build();
		} else {
			if(socioTorcedor.getIdsCampanhasAssociadas().contains(idCampanha)) {
				return Response.status(Status.OK).entity(socioTorcedor).build();
			} else {				
				Campanha campanha = campanhaService.recuperarCampanhaPorId(idCampanha);
				if(campanha != null) {
					socioTorcedorService.associarNovaCampanha(socioTorcedor, campanha);
					return Response.status(Status.CREATED).entity(socioTorcedor).build();
				} else {
					return Response.status(Status.NOT_FOUND).entity(new Campanha(idCampanha)).build();
				}
			}
		}	
	}

	
}
