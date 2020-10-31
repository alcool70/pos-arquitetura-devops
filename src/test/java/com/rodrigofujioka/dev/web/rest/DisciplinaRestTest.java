package com.rodrigofujioka.dev.web.rest;

import com.rodrigofujioka.dev.web.domain.Disciplina;
import com.rodrigofujioka.dev.web.service.DisciplinaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
				webEnvironment = SpringBootTest.WebEnvironment.MOCK,
				classes = DisciplinaRest.class)
@AutoConfigureMockMvc
class DisciplinaRestTest {

	final List<Disciplina> disciplinas = Arrays.asList(
					new Disciplina("matematica", "Suely", 2000),
					new Disciplina("portugues", "Franciane", 2007),
					new Disciplina("fisica", "João", 2010)
	);

	@MockBean
	private DisciplinaService disciplinaService;

	@Autowired
	private MockMvc mvc;

	@Test
	void testBuscaDisciplinaPorAno() throws Exception {
		ResultActions resultActions = mvc.perform(
						get("/api/disciplina/busca/{anoInicial}/{anoFinal}",
										2007, 2020)
		)
						.andExpect(status().isNoContent());
	}

	@Test
	void testBuscaDisciplinaPorAno_andReturnObjs() throws Exception {
		disciplinas.forEach(d -> disciplinaService.salvar(d));

		ResultActions resultActions = mvc.perform(
						get("/api/disciplina/busca/{anoInicial}/{anoFinal}",
										2007, 2020)
		)
						.andDo(result -> {
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" +
											result.getResponse().getContentAsString() +
											"\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
							);
						})
						.andExpect(status().isOk())
						.andExpect(content()
										.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$").isNotEmpty());
	}
}