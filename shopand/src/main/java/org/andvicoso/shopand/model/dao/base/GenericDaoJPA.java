package org.andvicoso.shopand.model.dao.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class GenericDaoJPA<O> implements GenericDao<O> {
	private static final String JPA_DB_UNIT = "shopand";
	protected EntityManager entityManager;
	protected static EntityManagerFactory factory;
	private Class<O> type;

	@SuppressWarnings("unchecked")
	protected GenericDaoJPA() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(JPA_DB_UNIT);
		}
		entityManager = factory.createEntityManager();
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<O>) pt.getActualTypeArguments()[0];
	}

	public boolean save(O obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<O> list() {
		String entityName = type.getSimpleName();
		String q = "SELECT o FROM " + entityName + " o";
		return (List<O>) entityManager.createQuery(q).getResultList();
	}

	public O find(long id) {
		return entityManager.find(type, id);
	}

	public boolean remove(long id) {
		O u = find(id);
		boolean found = u != null;
		if (found) {
			entityManager.getTransaction().begin();
			entityManager.remove(u);
			entityManager.getTransaction().commit();
		}
		return found;
	}

	protected TypedQuery<O> createQuery(String q) {
		return entityManager.createQuery(q, type);
	}

}
