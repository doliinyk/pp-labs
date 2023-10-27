package lpnu.denysoliinyk.oi2023.pp.lab4.service.impl.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.mail.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Log4j2
@Service
public class MailServiceImpl implements MailService {
	private final String username;
	private final JavaMailSender javaMailSender;
	private final SpringTemplateEngine springTemplateEngine;

	public MailServiceImpl(@Value("${spring.mail.username}") String username,
	                       JavaMailSender javaMailSender,
	                       SpringTemplateEngine springTemplateEngine) {
		this.username = username;
		this.javaMailSender = javaMailSender;
		this.springTemplateEngine = springTemplateEngine;
	}

	@Async
	@Override
	public void sendMessage(String to, String subject, Map<String, Object> variables) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		Context context = new Context();

		context.setVariables(variables);
		mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		mimeMessageHelper.setFrom(username);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSubject(subject);

		String text = springTemplateEngine.process("exception", context);
		mimeMessageHelper.setText(text, true);

		log.info("Exception mail message was sent");
		javaMailSender.send(mimeMessage);
	}
}
