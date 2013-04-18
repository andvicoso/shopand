package org.andvicoso.shopand.model.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andvicoso.shopand.infra.PropertiesManager;

/**
 * Servlet implementation class MailService
 */
public class MailService {
	private static final String MSG_MIME_TYPE = "text/html";

	private Properties props;

	public MailService() {
		props = PropertiesManager.read("mail");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String sendMessage(String from, String to, String subject,
			String message) {
		String resultMsg = "E-Mail enviado com sucesso!";

		try {
			String login = props.getProperty("mail.smtp.login");
			String password = props.getProperty("mail.smtp.password");

			Authenticator auth = new SMTPAuthenticator(login, password);

			Session session = Session.getInstance(props, auth);

			MimeMessage msg = createMessage(from, to, subject, message, session);

			Transport.send(msg);
		} catch (AuthenticationFailedException ex) {
			resultMsg = "Falha na autenticação";
		} catch (AddressException ex) {
			resultMsg = "E-Mail de destino inválido!";
		} catch (MessagingException ex) {
			resultMsg = ex.getMessage();
			ex.printStackTrace();//descomentar para debug
		}

		return resultMsg;
	}

	private MimeMessage createMessage(String from, String to, String subject,
			String message, Session session) throws MessagingException {
		MimeMessage msg = new MimeMessage(session);
		msg.setText(message);
		msg.setSubject(subject);
		msg.setFrom(new InternetAddress(from));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setContent(message, MSG_MIME_TYPE);
		msg.setSentDate(new Date());

		return msg;
	}

	private class SMTPAuthenticator extends Authenticator {
		private String login;
		private String password;

		public SMTPAuthenticator(String login, String password) {
			this.login = login;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(login, password);
		}
	}

}
