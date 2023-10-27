package lpnu.denysoliinyk.oi2023.pp.lab4;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import lpnu.denysoliinyk.oi2023.pp.lab4.mapper.salad.SaladMapper;
import lpnu.denysoliinyk.oi2023.pp.lab4.mapper.vegetable.VegetableMapper;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.salad.SaladService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Sql(value = "/db/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MapperServiceTest {
	@Autowired
	private VegetableMapper vegetableMapper;

	@Autowired
	private SaladMapper saladMapper;

	@Autowired
	private SaladService saladService;

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
	void testToEntityNull() {
		assertNull(vegetableMapper.toEntity(null));
	}

	@Test
	void testToDtoNull() {
		assertNull(vegetableMapper.toDto(null));
		assertNull(saladMapper.toDto(null));
	}

	@Test
	void testPartialUpdateNull() {
		vegetableMapper.partialUpdate(null, null);
	}

	@Test
	void testPartialDtoToEntityNull() {
		saladService.update(UUID.fromString("062b7b20-7421-11ee-b962-0242ac120004"), null);
	}
}