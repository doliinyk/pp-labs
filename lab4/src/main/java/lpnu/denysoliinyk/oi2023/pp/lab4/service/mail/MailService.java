package lpnu.denysoliinyk.oi2023.pp.lab4.service.mail;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface MailService {
	void sendMessage(String to, String subject, Map<String, Object> variables) throws MessagingException;
}
