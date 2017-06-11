package org.npj.sociotorcedor.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.npj.sociotorcedor.endpoint.SocioTorcedorEndpoint;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(SocioTorcedorEndpoint.class);
	}
}
