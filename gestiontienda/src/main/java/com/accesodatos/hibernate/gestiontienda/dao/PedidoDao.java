package com.accesodatos.hibernate.gestiontienda.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.gestiontienda.dominio.Cliente;
import com.accesodatos.hibernate.gestiontienda.dominio.Pedido;
import com.accesodatos.hibernate.gestiontienda.dominio.Producto;

public class PedidoDao extends AbstractDao<Pedido> {

	public PedidoDao() {
		setClazz(Pedido.class);
	}

	//Método que devuelve un Pedido de la base de datos filtrando por el nombre.
	public Pedido getPedidoPorNombre(String nombre) {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos cliente porque vamos a recuperar objetos de tipo Pedido.
		CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
		Root<Pedido> fromPedido = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(fromPedido).where(cb.equal(fromPedido.get("nombre"), nombre));
		
		TypedQuery<Pedido> query = getEntityManager().createQuery(criteriaQuery);
		return query.getSingleResult();
	
	}

	//Método que devuelve una lista con los pedidos que se llevaron a cabo con fecha posterior a la de hoy 
	//(están programados para ese entonces)
	public List getPedidosConFechaPosteriorA(LocalDate fecha) {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos cliente porque vamos a recuperar objetos de tipo Pedido.
		CriteriaQuery<Pedido> criteriaQuery = cb.createQuery(Pedido.class);
		Root<Pedido> fromPedido = criteriaQuery.from(Pedido.class);
		
		criteriaQuery.select(fromPedido).where(cb.greaterThan(fromPedido.get("fecha"), fecha));
		
		TypedQuery<Pedido> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}

	//Método que devuelve una lista con los pedidos que se llevaron a cabo con fecha anterior a aquella pasada como parámetro.
	public List getPedidosConFechaAnteriorA(LocalDate fecha) {

		String qlString = " FROM " + Pedido.class.getName() + " WHERE fecha < ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, fecha);
		return query.getResultList();
	}
	
	//Método que devuelve el número de pedidos que se han realizado en un mes.
	public  Object numPedidosRealizadosEnUnMes(Month mes) {
		
		String qlString = "select count(*) FROM " + Pedido.class.getName() + " WHERE MONTH(fecha) = ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, mes.getValue());
		return  query.getSingleResult();
	}

}// Fin de la clase.
