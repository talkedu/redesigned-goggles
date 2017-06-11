package org.npj.campanha.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.npj.campanha.endpoint.CampanhaEndpoint;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CampanhaEndpoint.class);
	}
}
