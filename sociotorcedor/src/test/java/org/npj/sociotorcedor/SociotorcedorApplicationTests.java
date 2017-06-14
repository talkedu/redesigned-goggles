package org.npj.sociotorcedor;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.npj.sociotorcedor.model.Campanha;
import org.npj.sociotorcedor.model.SocioTorcedor;
import org.npj.sociotorcedor.service.CampanhaService;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class SociotorcedorApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	CampanhaService campanhaService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void inserirUmSTQueNaoExiste() throws JSONException {

		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNomeCompleto("Fulano de Tal");
		socioTorcedor.setDataNascimento(new Date());
		socioTorcedor.setEmail("a@b.c");
		socioTorcedor.setIdTimeDoCoracao(1l);

		ResponseEntity<String> response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		JSONAssert.assertEquals("[]", response.getBody(), JSONCompareMode.LENIENT);

	}
	
	
	@Test
	public void inserirSTQueJaExisteENaoTemCampanhaAssociada() throws JSONException {

		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNomeCompleto("Fulano de Tal");
		socioTorcedor.setDataNascimento(new Date());
		socioTorcedor.setEmail("a@b.c");
		socioTorcedor.setIdTimeDoCoracao(1l);

		ResponseEntity<String> response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);
		
		
		List<Campanha> campanhas = new ArrayList<Campanha>();
		
		//campanha mesmo time do coracao
		
		Campanha campanha = new Campanha(1l);
		campanha.setIdTimeDoCoracao(1l);
		
		//campanha outro time do coracao
		Campanha campanha2 = new Campanha(2l);
		campanha2.setIdTimeDoCoracao(2l);
		
		//As duas campanhas são vigentes
		
		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());
		
		campanha2.setDataInicial(dataInicialCal.getTime());
		campanha2.setDataFinal(dataFinalCal.getTime());
		
		campanhas.add(campanha);
		campanhas.add(campanha2);
		
		//mockando o retorno da busca das campanhas disponíveis
		Mockito.when(campanhaService.buscarCampanhas()).thenReturn(campanhas);
		
		response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);

		//Deverá retornar apenas a campanha 1 como sugestão
		
		StringBuilder sb = new StringBuilder("[{\"id\": 1,\"idTimeDoCoracao\": 1,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalCal.getTime())).append("\"}]");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);

	}
	
	@Test
	public void inserirSTQueJaExisteEJaTemCampanhaAssociada() throws JSONException {

		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNomeCompleto("Fulano de Tal");
		socioTorcedor.setDataNascimento(new Date());
		socioTorcedor.setEmail("a@b.c");
		socioTorcedor.setIdTimeDoCoracao(1l);
		socioTorcedor.setIdsCampanhasAssociadas(Arrays.asList(1l));

		ResponseEntity<String> response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);
		
		//campanha mesmo time do coracao
		
		Campanha campanha = new Campanha(1l);
		campanha.setIdTimeDoCoracao(1l);
		
		//As duas campanhas são vigentes
		
		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());
		
		//mockando o retorno da busca da campanha associada
		Mockito.when(campanhaService.recuperarCampanhaPorId(1l)).thenReturn(campanha);
		
		response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);

		//Deverá retornar um array vazio
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		JSONAssert.assertEquals("[]", response.getBody(), JSONCompareMode.LENIENT);

	}
	
	@Test
	public void buscarSTQueTemCampanhaAssociada() throws JSONException {

		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNomeCompleto("Fulano de Tal");
		socioTorcedor.setDataNascimento(new Date());
		socioTorcedor.setEmail("a@b.c");
		socioTorcedor.setIdTimeDoCoracao(1l);

		ResponseEntity<String> response = restTemplate.postForEntity("/socioTorcedor", socioTorcedor, String.class);
		
		//Criar campanha mesmo time do coracao
		
		Campanha campanha = new Campanha(1l);
		campanha.setIdTimeDoCoracao(1l);
		
		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());
		
		//mockando o retorno da busca da campanha
		Mockito.when(campanhaService.recuperarCampanhaPorId(1l)).thenReturn(campanha);
		
		//Associa a campanha 1
		restTemplate.put("/socioTorcedor/1/campanha/1", null);		
		
		response = restTemplate.getForEntity("/socioTorcedor", String.class);

		//Deverá o ST com a campanha associada
		
		StringBuilder sb = new StringBuilder("[{\"id\":1,\"email\":\"a@b.c\",\"nomeCompleto\":\"Fulano de Tal\",\"dataNascimento\":\"")
				.append(sdf.format(new Date()))
				.append("\",\"campanhasAssociadas\":[{\"id\": 1,\"idTimeDoCoracao\": 1,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalCal.getTime())).append("\"}],\"idTimeDoCoracao\":1}]");
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);

	}

	//TODO Falta fazer alguns testes com a associação de campanhas
}
