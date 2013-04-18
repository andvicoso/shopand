package org.andvicoso.shopand.model.dao;

import org.andvicoso.shopand.model.dao.base.GenericDao;
import org.andvicoso.shopand.model.entity.user.User;

public interface UserDao extends GenericDao<User> {

	User findByEmail(String formLogin);

}
