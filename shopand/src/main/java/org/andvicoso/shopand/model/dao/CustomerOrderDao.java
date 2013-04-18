package org.andvicoso.shopand.model.dao;

import java.util.List;

import org.andvicoso.shopand.model.dao.base.GenericDao;
import org.andvicoso.shopand.model.entity.CustomerOrder;

public interface CustomerOrderDao extends GenericDao<CustomerOrder> {

	List<CustomerOrder> listByUser(Long id);

}
