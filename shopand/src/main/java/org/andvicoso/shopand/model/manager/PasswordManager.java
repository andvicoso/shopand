package org.andvicoso.shopand.model.manager;

import org.andvicoso.shopand.infra.utils.CriptoUtils;
import org.andvicoso.shopand.model.dao.UserDao;
import org.andvicoso.shopand.model.dao.UserDaoJPA;
import org.andvicoso.shopand.model.entity.user.User;

public class PasswordManager {

	public User verify(String formLogin, String formPassword) {
		UserDao dao = new UserDaoJPA();
		User user = dao.findByEmail(formLogin);
		if (user != null) {
			String dbPassword = user.getPassword();

			String criptoFormPassword = CriptoUtils.encryptMD5(formPassword);

			if (dbPassword.equalsIgnoreCase(criptoFormPassword))
				return user;
		}
		return null;
	}
}
