package org.npj.sociotorcedor.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.npj.sociotorcedor.model.Campanha;
import org.springframework.stereotype.Component;

@Component
public class CampanhaService {
	
	private static final String CAMPANHA_URL = "http://localhost:8080/campanha";	
	private Client client = ClientBuilder.newClient();
	
	public Campanha recuperarCampanhaPorId(Long id) {
		try {
			return client
					.target(CAMPANHA_URL)
					.path(String.valueOf(id))
					.request(MediaType.APPLICATION_JSON)
					.get(Campanha.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Campanha> buscarCampanhas() {
		try {
			return client
					.target(CAMPANHA_URL)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Campanha>> (){});
		} catch (Exception e) {
			return null;
		}
	}
}
