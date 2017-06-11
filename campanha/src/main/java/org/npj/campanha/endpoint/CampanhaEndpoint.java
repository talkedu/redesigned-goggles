package org.npj.campanha.endpoint;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.npj.campanha.model.Campanha;
import org.npj.campanha.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component	
@Path("/campanha")
public class CampanhaEndpoint {
	
	@Autowired
	CampanhaService campanhaService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response incluir(Campanha campanha) {
		List<Campanha> campanhasAlteradas = campanhaService.salvarCampanha(campanha);
		return Response.ok(campanhasAlteradas).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Campanha> buscar() {
		List<Campanha> campanhas = campanhaService.buscarCampanhas();
		return campanhas;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Campanha buscarUma(@PathParam("id") Long id) {
		Campanha campanha = campanhaService.buscarCampanha(id);
		return campanha;
	}
	
	@PUT
	public Response alterar(Campanha campanha) {
		List<Campanha> campanhasAlteradas = campanhaService.salvarCampanha(campanha);
		return Response.ok(campanhasAlteradas).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("id") Long id) {
		Campanha campanhaDeletada = campanhaService.deletarCampanha(id);
		return Response.ok(campanhaDeletada).build();
	}
	
}
