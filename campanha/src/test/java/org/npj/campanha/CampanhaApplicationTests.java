package org.npj.campanha;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.npj.campanha.model.Campanha;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CampanhaApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void inserirUmaCampanha() throws JSONException {

		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Vigência começa hoje e vai até 10 dias

		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		JSONAssert.assertEquals("[]", response.getBody(), JSONCompareMode.LENIENT);

	}

	@Test
	public void inserirDuasCampanhas() throws JSONException {

		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Vigência começa hoje e vai até 10 dias

		// primeira request
		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		// segunda request (tenta incluir campanha com a mesma vigência)
		response = restTemplate.postForEntity("/campanha", campanha, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		Calendar dataFinalMaisUmCal = (Calendar) dataFinalCal.clone();
		dataFinalMaisUmCal.add(Calendar.DAY_OF_MONTH, 1);

		// Retorno tem que ser um array apenas com a campanha da primeira
		// requisição com a dataFinal +1
		StringBuilder sb = new StringBuilder("[{\"id\": 1,\"idTimeDoCoracao\": 1,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalMaisUmCal.getTime())).append("\"}]");

		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);

	}

	@Test
	public void buscarUmaCampanhaVigente() throws JSONException {
		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Insere uma campanha vigente
		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		// Recupera a campanha
		response = restTemplate.getForEntity("/campanha/1", String.class);

		StringBuilder sb = new StringBuilder("{\"id\": 1,\"idTimeDoCoracao\": 1,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalCal.getTime())).append("\"}");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);
	}

	@Test
	public void buscarUmaCampanhaNaoVigente() throws JSONException {
		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataFinalCal = Calendar.getInstance();
		Calendar dataInicialCal = (Calendar) dataFinalCal.clone();

		dataInicialCal.add(Calendar.DAY_OF_MONTH, -10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Insere uma campanha nao vigente
		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		// Recupera a campanha
		response = restTemplate.getForEntity("/campanha/1", String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		JSONAssert.assertEquals("{\"id\":1,\"idTimeDoCoracao\":null,\"dataInicial\":null,\"dataFinal\":null}", response.getBody(), JSONCompareMode.LENIENT);
	}
	
	
	@Test
	public void deletarUmaCampanha() throws JSONException {
		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Insere uma campanha
		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		// Deleta a campanha
		response = restTemplate.exchange("/campanha/1", HttpMethod.DELETE, null, String.class);
		
		
		StringBuilder sb = new StringBuilder("{\"id\": 1,\"idTimeDoCoracao\": 1,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalCal.getTime())).append("\"}");
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);
	}
	
	
	@Test
	public void alterarUmaCampanha() throws JSONException, RestClientException, URISyntaxException {
		Campanha campanha = new Campanha();
		campanha.setIdTimeDoCoracao(1l);

		Calendar dataInicialCal = Calendar.getInstance();
		Calendar dataFinalCal = (Calendar) dataInicialCal.clone();

		dataFinalCal.add(Calendar.DAY_OF_MONTH, 10);

		campanha.setDataInicial(dataInicialCal.getTime());
		campanha.setDataFinal(dataFinalCal.getTime());

		// Insere uma campanha
		ResponseEntity<String> response = restTemplate.postForEntity("/campanha", campanha, String.class);

		//Muda o time do coracao
		campanha.setIdTimeDoCoracao(2l);

		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.set("Content-Type", "application/json");
		
		response = restTemplate.exchange(new RequestEntity<Campanha>(campanha, headers, HttpMethod.PUT, new URI("/campanha/1")), String.class);
		
		StringBuilder sb = new StringBuilder("{\"id\": 1,\"idTimeDoCoracao\": 2,\"dataInicial\": \"")
				.append(sdf.format(dataInicialCal.getTime())).append("\",\"dataFinal\": \"")
				.append(sdf.format(dataFinalCal.getTime())).append("\"}");
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		JSONAssert.assertEquals(sb.toString(), response.getBody(), JSONCompareMode.LENIENT);
	}
}
