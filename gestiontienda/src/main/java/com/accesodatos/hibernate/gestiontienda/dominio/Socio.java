package com.accesodatos.hibernate.gestiontienda.dominio;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "socio")
public class Socio {
	
	//Atributos	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Las claves que se van generando toman valores de uno en uno.
	@Column(name = "id_socio")
	private Integer id; 
	@Column(unique = true)
	private String email;
	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
	
	@OneToOne(mappedBy = "socio", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Cliente cliente;
	
	//Constructor vacío
	public Socio () {
		
	}

	//Constructor con parámetros
	public Socio(String email, Cliente cliente, LocalDate fechaAlta) {
		super();
		this.email = email;
		this.cliente = cliente;
		this.fechaAlta = fechaAlta;
	}	

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	//Getters y setters.
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;			
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Método que cambia a null el atributo del objeto cliente que estaba relacionado con el socio previamente a su eliminación.
	@PreRemove
	public void nullificarCliente () {
		this.cliente.setSocio(null);
	}	

	
	@Override
	public String toString() {
		return "Socio [id=" + id + ", email=" + email + ", fechaAlta=" + fechaAlta +  "]";
	}

}//Fin de la clase.
