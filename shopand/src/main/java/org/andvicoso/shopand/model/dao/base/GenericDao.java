package org.andvicoso.shopand.model.dao.base;

import java.util.List;

public interface GenericDao<O> {

	List<O> list();

	O find(long id);

	boolean remove(long id);

	boolean save(O obj);
}
