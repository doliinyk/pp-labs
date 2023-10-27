package lpnu.denysoliinyk.oi2023.pp.lab4.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class ThymeleafTemplateConfiguration {
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

		springTemplateEngine.addTemplateResolver(mailTemplateResolver());

		return springTemplateEngine;
	}

	private ClassLoaderTemplateResolver mailTemplateResolver() {
		ClassLoaderTemplateResolver mailTemplateResolver = new ClassLoaderTemplateResolver();

		mailTemplateResolver.setPrefix("/templates/");
		mailTemplateResolver.setSuffix(".html");
		mailTemplateResolver.setTemplateMode(TemplateMode.HTML);
		mailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		mailTemplateResolver.setCacheable(false);

		return mailTemplateResolver;
	}
}
