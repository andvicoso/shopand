package org.andvicoso.shopand.model.dao.base;

import java.util.List;

public interface SearchableDao<O> {
	List<O> search(String term);
}
