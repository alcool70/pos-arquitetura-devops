package com.rodrigofujioka.dev.web.rest;

import com.rodrigofujioka.dev.web.domain.Quarentena;
import com.rodrigofujioka.dev.web.service.QuarentenaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "ENV", matches = "ci")
@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.MOCK,
				classes = QuarentenaRest.class)
@AutoConfigureMockMvc
class QuarentenaRestTest {

	final List<Quarentena> quarentenas = Arrays.asList(
					new Quarentena("PB", "Joao Pessoa", "Adamastor"),
					new Quarentena("PE", "Recife", "Lucrecia"),
					new Quarentena("RN", "Natal", "Noel")
	);

	@MockBean
	private QuarentenaService quarentenaService;

	@Autowired
	private MockMvc mvc;

	@Test
	void testSalvarQuarentena() throws Exception {
//		quarentenaService.salvar(quarentenas.get(0));

		ResultActions res;

		res = mvc
						.perform(get("/api/quarentena/{id}", 1))
						.andExpect(status().isOk());
		System.out.println(">>> "+ res.andReturn().getResponse().getContentAsString());
	}

	@Test
	void testPost() throws Exception {
		mvc.perform(
						post("/api/quarentena")
										.contentType(MediaType.APPLICATION_JSON)
										.content("{\"estado\": \"PB\"}")
		)
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

}