package com.accesodatos.hibernate.gestiontienda.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.gestiontienda.dominio.Cliente;
import com.accesodatos.hibernate.gestiontienda.dominio.Pedido;
import com.accesodatos.hibernate.gestiontienda.dominio.Producto;
import com.accesodatos.hibernate.gestiontienda.dominio.Proveedor;

public class ProductoDao extends AbstractDao<Producto> {

	public ProductoDao() {
		setClazz(Producto.class);
	}

	//Método que obtiene un producto de la base de datos filtrando por el nombre.
	public Producto getProductoPorNombre(String nombre) {

		String qlString = "FROM " + Producto.class.getName() + " WHERE nombre = ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, nombre);
		System.out.println(qlString);
	
		return (Producto) query.getSingleResult();
	}

	//Método que devuelve una lista con aquellos productos cuyo stock esté por encima del valor pasado como parámetro.
	public List<Producto> findProductosConMasDeNstock(int stock) {
		
		String qlString = "FROM " + Producto.class.getName() + " WHERE stock >= ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, stock);
		return query.getResultList();
	}
	
	//Método que devuelve una lista con los productos que se encuentran sin proveedor.
	public List<Producto> getProductosSinProveedor () {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos Producto porque vamos a recuperar objetos de tipo Producto.
		CriteriaQuery<Producto> criteriaQuery = cb.createQuery(Producto.class);
		Root<Producto> fromProducto = criteriaQuery.from(Producto.class);
		
		criteriaQuery.select(fromProducto).where(cb.isNull(fromProducto.get("proveedor")));

		TypedQuery<Producto> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	//Método que devuelve una lista con los productos de un proveedor en concreto.
	public List getProductosDeUnProveedor(Proveedor p)  {
		
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos Producto porque vamos a recuperar objetos de tipo Producto.
		CriteriaQuery<Producto> criteriaQuery = cb.createQuery(Producto.class);
		Root<Proveedor> fromProveedor = criteriaQuery.from(Proveedor.class);
		
		Join<Proveedor,Producto> joinProveedor = fromProveedor.join("productos", JoinType.INNER);
		criteriaQuery.select(joinProveedor);
		
		TypedQuery<Producto> query = getEntityManager().createQuery(criteriaQuery);
		return  query.getResultList();
	}
	
	
}// Fin de la clase.
