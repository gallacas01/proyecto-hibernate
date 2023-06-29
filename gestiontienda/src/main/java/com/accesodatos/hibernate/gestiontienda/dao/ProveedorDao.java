package com.accesodatos.hibernate.gestiontienda.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import com.accesodatos.hibernate.gestiontienda.dominio.Producto;
import com.accesodatos.hibernate.gestiontienda.dominio.Proveedor;

public class ProveedorDao extends AbstractDao<Proveedor>{

	public ProveedorDao() {
		setClazz(Proveedor.class);
	}
	
	//Método que devuelve un proveedor de la base de datos filtrando por el número de teléfono.
	public Proveedor getProveedorPorNumTelefono(int telefono){
		
		String qlString = " FROM " + Proveedor.class.getName() + " WHERE telefono = ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, telefono);
		return (Proveedor) query.getSingleResult();		
	}
	
	
	//Método que devuelve el proveedor que suministra el proudcto pasado como parámetro.
	public Proveedor getProveedorAsociadoAunProducto(Producto p) {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos Producto porque vamos a recuperar objetos de tipo Cliente.
		CriteriaQuery<Proveedor> criteriaQuery = cb.createQuery(Proveedor .class);
		Root<Proveedor> fromProveedor = criteriaQuery.from(Proveedor.class);
		
		criteriaQuery.select(fromProveedor);
		criteriaQuery.where(cb.equal(fromProveedor, p.getProveedor()));

		TypedQuery<Proveedor> query = getEntityManager().createQuery(criteriaQuery);
		return query.getSingleResult();
	}	
	
	//Método que devuelve una lista con los aquellos proveedores que suministre un número mayor de productos 
	//del que se pasa como parámetro.
	public List<String> getProveedoresConMasDeNproductos (long numProductos) {
		
		String qlString = "SELECT p.proveedor.nombre FROM " + Producto.class.getName() + " p group by p.proveedor.id having count(*) > ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, numProductos);		
		
		return query.getResultList();
	}
	
	
}//Fin de la clase.
