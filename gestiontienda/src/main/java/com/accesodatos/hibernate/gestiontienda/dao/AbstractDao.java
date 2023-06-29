package com.accesodatos.hibernate.gestiontienda.dao;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.accesodatos.hibernate.gestiontienda.utiles.EntityManagerUtil;


public abstract class AbstractDao<T> implements Dao<T>{

	//Atributos.
	private EntityManager entityManager = EntityManagerUtil.getEntityManager(); //Para establecer la conexiÃ³n con la bbdd.
	private Class<T> clazz;
	
	@Override
	public Optional<T> get(int id) {
		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clazz.getName();
		Query query = entityManager.createQuery(qlString);
		return query.getResultList();
	}

	@Override
	public void save(T t){			
		
		executeInsideTransaction(entityManager -> entityManager.persist(t));			
		
	}//Fin del método save.

	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	public void delete(T t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
	}

	public void setClazz(Class <T> clazz) {
		this.clazz = clazz;
	}
	
	public EntityManager getEntityManager () {
		return entityManager;
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			
			tx.begin();
			action.accept(entityManager);
			tx.commit();
			
		}catch (RuntimeException e) {
			
			tx.rollback();
			throw e;
		}		
		
	}//Fin del mÃ©todo.
	
	
	
}//Fin de la clase. 
