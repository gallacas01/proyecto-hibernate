package com.accesodatos.hibernate.gestiontienda.dao;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.gestiontienda.dominio.Cliente;
import com.accesodatos.hibernate.gestiontienda.dominio.Pedido;
import com.accesodatos.hibernate.gestiontienda.dominio.Producto;
import com.accesodatos.hibernate.gestiontienda.dominio.Proveedor;
import com.accesodatos.hibernate.gestiontienda.dominio.Socio;

public class SocioDao extends AbstractDao<Socio>{
	
	public SocioDao() {
		setClazz(Socio.class);
	}
		
	//Método que devuelve una lista con aquellos socios que se registraron antes de la fecha pasada como parámetro.
	public List getSociosQueSeDieronDeAltaAntesDe(LocalDate fecha) {		
	
		String qlString = " FROM " + Socio.class.getName() + " s WHERE s.fechaAlta < ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, fecha);
		return query.getResultList();
	}
	
	//Método que devuelve una lista con todos los email
	public List getTodosLosEmail() {
		
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos cliente porque vamos a recuperar objetos de tipo Cliente.
		CriteriaQuery<Socio> criteriaQuery = cb.createQuery(Socio.class);

		Root<Socio> fromSocio = criteriaQuery.from(Socio.class);
		criteriaQuery.select(fromSocio.get("email"));		

		TypedQuery<Socio> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();		
		
	}	

}//Fin de la clase.
