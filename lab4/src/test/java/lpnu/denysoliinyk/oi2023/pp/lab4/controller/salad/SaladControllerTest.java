package lpnu.denysoliinyk.oi2023.pp.lab4.controller.salad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import lpnu.denysoliinyk.oi2023.pp.lab4.dto.salad.SaladCreationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = "/db/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SaladControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private GreenMail smtpServer;

	@BeforeEach
	public void setUp() {
		smtpServer = new GreenMail(ServerSetup.SMTP);
		smtpServer.start();
	}

	@AfterEach
	public void tearDown() {
		smtpServer.stop();
	}

	@Test
	void testGetAll() throws Exception {
		mockMvc.perform(get("/salads")).andExpect(status().is(200))
				.andExpect(jsonPath("$.content").isNotEmpty());
	}

	@Test
	void testAdd() throws Exception {
		SaladCreationDto saladCreationDto = new SaladCreationDto("test", List.of(UUID.fromString("042b7b20-7421-11ee-b962-0242ac120002")));

		mockMvc.perform(post("/salads").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(saladCreationDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(saladCreationDto.name()));
	}

	@Test
	void testGetById1() throws Exception {
		mockMvc.perform(get("/salads/{id}", "062b7b20-7421-11ee-b962-0242ac120001"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetById2() throws Exception {
		mockMvc.perform(get("/salads/{id}", "062b7b20-7421-11ee-b962-0242ac120012"))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void testUpdate1() throws Exception {
		SaladCreationDto saladCreationDto = new SaladCreationDto("test2", List.of(UUID.fromString("042b7b20-7421-11ee-b962-0242ac120002")));

		mockMvc.perform(patch("/salads/{id}", "062b7b20-7421-11ee-b962-0242ac120004").contentType(MediaType.APPLICATION_JSON_VALUE)
				                .content(objectMapper.writeValueAsString(saladCreationDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(saladCreationDto.name()));
	}

	@Test
	void testUpdate2() throws Exception {
		mockMvc.perform(patch("/salads/{id}", "062b7b20-7421-11ee-b962-0242ac120004").contentType(MediaType.APPLICATION_JSON_VALUE)
				                .content(objectMapper.writeValueAsString(null)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void testDelete() throws Exception {
		mockMvc.perform(delete("/salads/{id}", "062b7b20-7421-11ee-b962-0242ac120004"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetAllVegetablesBySaladIdAndRange() throws Exception {
		mockMvc.perform(get("/salads/{id}/vegetables", "062b7b20-7421-11ee-b962-0242ac120001"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isNotEmpty());
	}
}