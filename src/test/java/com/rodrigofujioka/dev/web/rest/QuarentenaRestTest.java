package com.rodrigofujioka.dev.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigofujioka.dev.web.domain.Quarentena;
import com.rodrigofujioka.dev.web.service.QuarentenaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = QuarentenaRest.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class QuarentenaRestTest {

	private List<Quarentena> quarentenas;

	public QuarentenaRestTest() {
		this.quarentenas = new ArrayList<Quarentena>();

		Quarentena quarentena = new Quarentena("PB", "joao Pessoa");
		quarentena.setNomePessoa("Artur");
		quarentena.setDiasQuarentena(180);

		this.quarentenas.add(quarentena);
	}

	@MockBean
	private QuarentenaService quarentenaService;

	@Autowired
	private MockMvc mvc;

	@Test
	void first() {
		assertThat(quarentenaService).isNotNull();
	}

	@Test
	void testSalvarQuarentena() throws Exception {
		quarentenaService.salvar(this.quarentenas.get(0));

		this.mvc.perform(get("/api/quarentena/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testPost()throws Exception {
		mvc.perform(post("/api/quarentena").contentType("text/xml").content("<estado>PB</estado>")).andDo(print());
	}

}