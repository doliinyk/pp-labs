package lpnu.denysoliinyk.oi2023.pp.lab4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantApplicationTest {
	@Test
	void contextLoads(ApplicationContext applicationContext) {
		assertThat(applicationContext).isNotNull();
	}
}
