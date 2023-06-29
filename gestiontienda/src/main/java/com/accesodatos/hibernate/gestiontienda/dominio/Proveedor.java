package com.accesodatos.hibernate.gestiontienda.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;


@Entity
@Table(name = "proveedor")
public class Proveedor {
	
	//Atributos	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Las claves que se van generando toman valores de uno en uno.
	@Column(name = "cif")
	private Integer id; //Como el id es un valor autogenerado, en el constructor no se pasará como párametro.	
	
	private String nombre;
	@Column(unique = true, length = 9)
	private Integer telefono;
	private String direccion;
	
	
	@OneToMany(mappedBy = "proveedor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Producto> productos;	


	//Constructor vacío
	public Proveedor() {
		
	}

	//Constructor con parámetros
	public Proveedor(String nombre, Integer telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.productos = new ArrayList<Producto>(); 
	}

	//Getters y setters.
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}		
	
	//Método que se llama en el constructor de producto.
	public void addProducto(Producto p) {
		if (!this.productos.contains(p)) {
			this.productos.add(p);
			p.setProveedor(this);
		}		
	}

	public void removeProducto(Producto p) {
		if(this.productos.contains(p)) {
			this.productos.remove(p);
			p.setProveedor(null);
		}		
	}
	
	//Antes de eliminar un proveedor, se cambia el valor de sus productos asociados a null;
	@PreRemove
	public void nullificarProductosAsociados () {
		this.productos.forEach(prod -> prod.setProveedor(null));
	}	

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+  "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Proveedor other = (Proveedor) obj;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	
}//Fin de la clase.
