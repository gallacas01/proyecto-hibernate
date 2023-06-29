package com.accesodatos.hibernate.gestiontienda.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	//Atributos	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Las claves que se van generando toman valores de uno en uno.
	@Column(name = "id_pedido")
	private Integer id; //Como el id es un valor autogenerado, en el constructor no se pasará como párametro.
	@Column(name = "nombre_pedido", unique = true)
	private String nombre;
	private LocalDate fecha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Producto> productos;

	//Constructor vacío.
	public Pedido() {
		
	}
	
	//Constructor con parámetros.	
	public Pedido(LocalDate fecha, String nombrePedido) {
		super();
		this.fecha = fecha;
		this.nombre = nombrePedido;
		this.productos = new ArrayList<Producto>();
	}

	//Getters y setters.
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}	
	
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
	
	//Método que añade a la lista de productos del pedido aquel producto que se pase como parámetro y añade el pedido que
	//invoca el método a la lista de pedidos del producto que pasa como parámetro.
	public void addProducto(Producto p) {
		if (!this.productos.contains(p)) {
			this.productos.add(p);
			p.addPedido(this);
		}
		
	}//Fin del método.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Pedido other = (Pedido) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + "]";
	}
	
	
}//Fin de la clase.
