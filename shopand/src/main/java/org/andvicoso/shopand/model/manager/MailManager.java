package org.andvicoso.shopand.model.manager;

import java.util.List;

import org.andvicoso.shopand.model.entity.user.User;
import org.andvicoso.shopand.model.service.MailService;

public class MailManager {

	private MailService mailService;

	public MailManager() {
		mailService = new MailService();
	}

	public String sendSignUpMail(User user) {
		String from = "shopand@shopand.com.br";
		String subject = "Cadastro realizado";
		String message = "Seu cadastro foi realizado com sucesso! <br> Agora você tem acesso a todas as ofertas!";
		return mailService.sendMessage(from, user.getEmail(), subject, message);
	}

	public String sendPurchaseMail(String context, User user) {
		String from = "compras@shopand.com.br";
		String subject = "Pagamento realizado com sucesso!";
		String link = "<a href='" + context
				+ "/view/product/list.jsp'>Produtos</a>";
		String message = "Seu pagamento foi realizado com sucesso! <br>"
				+ " Que tal dar uma olhadinha em novas ofertas?<br>" + link;
		return mailService.sendMessage(from, user.getEmail(), subject, message);
	}

	public String sendNewPasswordMail(User user, String newPass) {
		String from = "shopand@shopand.com.br";
		String subject = "Senha alterada";
		String message = "Sua nova senha é: " + newPass;
		return mailService.sendMessage(from, user.getEmail(), subject, message);
	}

	public void sendOffersMail(List<User> list) {
		String from = "ofertas@shopand.com.br";
		String subject = "Ofertas";
		String message = "Super oferta da semana!";

		for (User user : list) {
			mailService.sendMessage(from, user.getEmail(), subject, message);
		}
	}
}
