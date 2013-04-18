package org.andvicoso.shopand.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.andvicoso.shopand.model.dao.base.GenericDaoJPA;
import org.andvicoso.shopand.model.entity.user.User;

public class UserDaoJPA extends GenericDaoJPA<User> implements UserDao {

	public User findByEmail(String email) {
		String q = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = createQuery(q);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
		}
		return null;
	}
}
