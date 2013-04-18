package org.andvicoso.shopand.model.dao;

import java.util.List;

import org.andvicoso.shopand.model.dao.base.GenericDaoJPA;
import org.andvicoso.shopand.model.entity.CustomerOrder;

public class CustomerOrderDaoJPA extends GenericDaoJPA<CustomerOrder> implements
		CustomerOrderDao {

	public List<CustomerOrder> listByUser(Long id) {
		String q = "SELECT u FROM CustomerOrder u WHERE u.user.id = :id";
		return createQuery(q).setParameter("id", id).getResultList();
	}

}
