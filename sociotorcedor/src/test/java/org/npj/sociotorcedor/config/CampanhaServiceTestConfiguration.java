package org.npj.sociotorcedor.config;

import org.mockito.Mockito;
import org.npj.sociotorcedor.service.CampanhaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CampanhaServiceTestConfiguration {
	@Bean
    @Primary
    public CampanhaService campanhaService() {
        return Mockito.mock(CampanhaService.class);
    }
}
