	package com.accesodatos.hibernate.gestiontienda.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Las claves que se van generando toman valores de uno en uno.
	@Column(name = "id_producto")
	private Integer id;

	@Column(unique = true)
	private String nombre;
	private Double precio;
	private Integer stock;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;	

	@ManyToMany(mappedBy = "productos", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;

	// Constructor vacio
	public Producto() {

	}

	// Constructor con parámetros.
	public Producto(String nombre, Double precio, Integer stock, Proveedor proveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		proveedor.addProducto(this);
	}

	// Getters y setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {		
		this.proveedor = proveedor;				
		//Si el producto no tenía proveedor asociado y su lista de productos no contenía al producto invocante, se añade.
		//de productos del proveedor.
		if (proveedor != null && !proveedor.getProductos().contains(this)) {
			proveedor.addProducto(this);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	//Método que añade un pedido a la lista de pedidos y añade el pedido a la lista de pedidos del producto pasado como parámetro.
	public void addPedido(Pedido p) {
		if (!this.pedidos.contains(p)) {
			this.pedidos.add(p);
			p.addProducto(this);
		}		
	}

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
		Producto other = (Producto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
	}



}//Fin de la clase.
