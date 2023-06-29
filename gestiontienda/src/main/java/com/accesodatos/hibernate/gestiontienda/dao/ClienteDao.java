package com.accesodatos.hibernate.gestiontienda.dao;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.gestiontienda.dominio.Cliente;
import com.accesodatos.hibernate.gestiontienda.dominio.Producto;
import com.accesodatos.hibernate.gestiontienda.dominio.Socio;

public class ClienteDao extends AbstractDao<Cliente> {

	public ClienteDao() {

		setClazz(Cliente.class);
	}

	// Método que devuelve un objetio de tipo cliente obtenido de la base datos.
	public Cliente getClientePorNumTelefono(int numTelefono) throws NoResultException {
		String qlString = " FROM " + Cliente.class.getName() + " WHERE telefono = ?1";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter(1, numTelefono);
		return (Cliente) query.getSingleResult();
	}

	//Método que devuelve una lista con los clientes que no son socios ordenados por correo electrónico.
	public List getClientesQueSonSociosOrdenadosPorCorreo() {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Cliente> criteriaQuery = cb.createQuery(Cliente.class);
		Root<Socio> fromSocio = criteriaQuery.from(Socio.class);

		Join<Socio, Cliente> join_socio_cliente = fromSocio.join("cliente", JoinType.INNER);
		criteriaQuery.select(join_socio_cliente).orderBy(cb.asc(fromSocio.get("email")));

		TypedQuery<Cliente> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}

	// Método que devuelve una lista con los clientes que no son socios.
	public List getClientesQueNoSonSocios() {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		// Escribimos cliente porque vamos a recuperar objetos de tipo Cliente.
		CriteriaQuery<Cliente> criteriaQuery = cb.createQuery(Cliente.class);

		Root<Cliente> fromCliente = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(fromCliente).where(cb.isNull(fromCliente.get("socio")));
		criteriaQuery.orderBy(cb.desc(fromCliente.get("nombre")));// Ordenamos por nombre

		TypedQuery<Cliente> query = getEntityManager().createQuery(criteriaQuery);
		return query.getResultList();
	}

	//Método que devuelve el número de clientes que no son socios.
	public long getNumDeClientesQueNoSonSocios() {

		String qlString = "SELECT count(*) FROM " + Cliente.class.getName() + " WHERE id_socio = null";
		Query query = getEntityManager().createQuery(qlString);
		return (long) query.getSingleResult();
	}

}// Fin de la clase.
