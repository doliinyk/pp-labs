package lpnu.denysoliinyk.oi2023.pp.lab4.exception;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import lpnu.denysoliinyk.oi2023.pp.lab4.service.mail.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
	private final String receiver;
	private final MailService mailService;

	public GlobalExceptionHandler(@Value("${spring.mail.receiver}") String receiver,
	                              MailService mailService) {
		this.receiver = receiver;
		this.mailService = mailService;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> exception(Exception ex, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();

		response.put("message", ex.getMessage());
		response.put("timestamp", new Date().toString());
		response.put("path", request.getRequestURI());

		try {
			mailService.sendMessage(receiver, "Exception handling", response);
		} catch (MessagingException messagingException) {
			log.error(messagingException);
		}

		return response;
	}
}
