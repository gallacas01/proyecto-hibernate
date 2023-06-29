package com.accesodatos.hibernate.gestiontienda;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.accesodatos.hibernate.gestiontienda.dao.ClienteDao;
import com.accesodatos.hibernate.gestiontienda.dao.PedidoDao;
import com.accesodatos.hibernate.gestiontienda.dao.ProductoDao;
import com.accesodatos.hibernate.gestiontienda.dao.ProveedorDao;
import com.accesodatos.hibernate.gestiontienda.dao.SocioDao;
import com.accesodatos.hibernate.gestiontienda.dominio.Cliente;
import com.accesodatos.hibernate.gestiontienda.dominio.Pedido;
import com.accesodatos.hibernate.gestiontienda.dominio.Producto;
import com.accesodatos.hibernate.gestiontienda.dominio.Proveedor;
import com.accesodatos.hibernate.gestiontienda.dominio.Socio;

public class App {

	private static void insertDatosCliente_Socio(ClienteDao clienteDao) {

		// Creamos 3 clientes, los cuales no habrán sido dado de alta como socios.
		Cliente c1 = new Cliente("Miguel", "Gallardo Castillo", 653782058);
		Cliente c2 = new Cliente("María", "Gallardo Castillo", 678098752);
		Cliente c3 = new Cliente("Marta", "Gallardo Castillo", 82719301);
		Cliente c4 = new Cliente("Jesús", "Arenas Gallardo", 657890456);

		// Creamos 2 clientes los cuales registraremos también como socios.
		Cliente c5 = new Cliente("Elena", "Vega Guillén", 623092884);
		c5.setSocio(new Socio((c5.getNombre() + c5.getApellidos()).toLowerCase().replace(" ", "") + "@gmail.com", c5,
				LocalDate.now()));
		Cliente c6 = new Cliente("Matías", "Casado Lara", 690875623);
		c6.setSocio(new Socio((c6.getNombre() + c6.getApellidos()).toLowerCase().replace(" ", "") + "@gmail.com", c6,
				LocalDate.of(2022, Month.APRIL, 10)));

		clienteDao.save(c1);
		clienteDao.save(c2);
		clienteDao.save(c3);
		clienteDao.save(c4);
		clienteDao.save(c5);
		clienteDao.save(c6);

	}// Fin del método.

	private static void insertDatosProducto_Proveedor(ProveedorDao proveedorDao) {

		// Creamos objetos de tipo proveedor.
		Proveedor prov1 = new Proveedor("Utensilios de cocina", 662819038, "Calle Rábida nº9");
		Proveedor prov2 = new Proveedor("Elementos decorativos", 627819054, "Avda Antonio Gómez nº1");
		Proveedor prov3 = new Proveedor("Mobiliario", 654719031, "Calle María Jiménez s/n");

		// Creamos los objetos de tipo Producto
		Producto cuchillo = new Producto("cuchillo", 12.45, 100, prov1);
		Producto tenedor = new Producto("tenedor", 7.99, 150, prov1);
		Producto vaso = new Producto("vaso", 12.45, 300, prov1);
		Producto cuchara = new Producto("cuchara", 12.45, 400, prov1);
		Producto plato = new Producto("plato", 12.45, 5000, prov1);

		Producto lampara = new Producto("lámpara", 19.99, 400, prov2);
		Producto cuadro = new Producto("cuadro", 76.43, 20, prov2);
		Producto jarron = new Producto("jarrón", 25.99, 999, prov2);
		Producto flores = new Producto("flores", 4.99, 1200, prov2);

		Producto barra = new Producto("barra", 200.0, 80, prov3);
		Producto mesa = new Producto("mesa", 87.89, 450, prov3);
		Producto silla = new Producto("silla", 12.45, 300, prov3);
		Producto taburete = new Producto("taburete", 17.94, 134, prov3);
		Producto estanteria = new Producto("estantería", 71.65, 96, prov3);

		proveedorDao.save(prov1);
		proveedorDao.save(prov2);
		proveedorDao.save(prov3);

	}// Fin del método.

	private static void insertDatosProducto_Pedido(PedidoDao pedidoDao, ProductoDao productoDao) {

		Pedido ped1 = new Pedido(LocalDate.now(), "pedido1");
		// Recuperamos productos de la base de datos y los añadimos al pedido.
		ped1.addProducto(productoDao.getProductoPorNombre("cuchara"));
		ped1.addProducto(productoDao.getProductoPorNombre("lámpara"));
		ped1.addProducto(productoDao.getProductoPorNombre("cuchillo"));
		pedidoDao.save(ped1);

		Pedido ped2 = new Pedido(LocalDate.of(2023, Month.JANUARY, 10), "pedido2");
		// Recuperamos productos de la base de datos y los añadimos al pedido.
		ped2.addProducto(productoDao.getProductoPorNombre("plato"));
		ped2.addProducto(productoDao.getProductoPorNombre("vaso"));
		ped2.addProducto(productoDao.getProductoPorNombre("estantería"));
		ped2.addProducto(productoDao.getProductoPorNombre("silla"));
		pedidoDao.save(ped2);

		Pedido ped3 = new Pedido(LocalDate.of(2023, Month.JANUARY, 26), "pedido3");
		// Recuperamos productos de la base de datos y los añadimos al pedido.
		ped3.addProducto(productoDao.getProductoPorNombre("taburete"));
		ped3.addProducto(productoDao.getProductoPorNombre("flores"));
		ped3.addProducto(productoDao.getProductoPorNombre("jarrón"));
		ped3.addProducto(productoDao.getProductoPorNombre("tenedor"));
		pedidoDao.save(ped3);

		Pedido ped4 = new Pedido(LocalDate.of(2023, Month.JANUARY, 12), "pedido4");
		// Recuperamos productos de la base de datos y los añadimos al pedido.
		ped4.addProducto(productoDao.getProductoPorNombre("jarrón"));
		ped4.addProducto(productoDao.getProductoPorNombre("mesa"));
		ped4.addProducto(productoDao.getProductoPorNombre("silla"));
		ped4.addProducto(productoDao.getProductoPorNombre("barra"));
		ped4.addProducto(productoDao.getProductoPorNombre("cuadro"));
		pedidoDao.save(ped4);

	}// Fin del método.

	private static void opcionesMenuEscogerRelacion() {

		System.out.println("-------------------------------------\nSeleccione una de las relaciones:");
		System.out.println("-Opción 1 (save): Inserción automática de datos.");
		System.out.println("-Opción 2: Relación Cliente-Socio (1:1).");
		System.out.println("-Opción 3: Relación Proveedor-Producto (1:N).");
		System.out.println("-Opción 4: Relación Producto-Pedido (N:M).");
		System.out.println("-Opción 5: Salir. \n -------------------------------------");

	}// Fin del método.

	private static void mostrarOpcionesMenuRelacionCliente_Socio() {

		System.out.println("\t\nRelación Cliente-Socio (1:1). Opciones: ");
		System.out.println(
				"\tOpción 1 (delete): Borrado del socio (id_socio = 1) relacionado con el cliente Elena Vega Guillén (id_cliente = 5).");
		System.out.println(
				"\tOpción 2 (save): Creación e inserción del socio eliminado anteriormente.");
		System.out.println(
				"\tOpción 3 (update): Actualización del cliente 'Elena Vega Guillén' y del socio creado anteriormente enlazando ambos registros.");
		System.out.println(
				"\tOpción 4 (delete): Borrado de la cliente 'Elena Vega Guillén' y del socio relacionado (borrado en cascada).");
		System.out.println("\tOpción 5: Mostrar métodos.");
		System.out.println("\tOpción 6: Salir");
		System.out.println(
				"\t******RECOMENDACIÓN****** : Seleccionar las opciones 1,2,3 y 4 en ese orden.");

	}// Fin del método.

	private static void mostrarMetodosRelacionCliente_Socio() {
		System.out
				.println("\n---------------------- MÉTODOS DE LA RELACIÓN Cliente-Socio ----------------------------");
		System.out.println("\tOpción 1: Mostrar los socios que se dieron de alta con una fecha anterior a hoy.");
		System.out.println("\tOpción 2: Mostrar los clientes que son socios ordenados por el correo electrónico.");
		System.out.println("\tOpción 3: Mostrar los clientes que no son socios ordenados por el nombre.");
		System.out.println("\tOpción 4: Mostrar el número de clientes que no son socios.");
		System.out.println("\tOpción 5: Mostrar todos los email de los socios.");
		System.out.println(
				"\tOpción 6: Salir. \n ---------------------------------------------------------------------------------------");
	}// Fin del método.

	private static void mostrarOpcionesMenuRelacionProveedor_Producto() {

		System.out.println("Relación Proveedor-Producto (1:N). Opciones: ");
		System.out.println(
				"\tOpción 1 (delete): Borrado del proveedor con nombre 'Mobiliario' (id = 3) y modificación a null del 'campo id_proveedor' de los productos relacionados.");
		System.out.println(
				"\tOpción 2 (update): Asignación al Proveedor con nombre 'Elementos decorativos' (cif = 2) de los productos que se habían quedado sin proveedor (productos del proveedor previamente eliminado).");
		System.out.println("\tOpción 3: Mostrar métodos.");
		System.out.println("\tOpción 4: Salir.");
		System.out.println(
				"\t******RECOMENDACIÓN****** : Seleccionar las opciones 1 y 2 en ese orden.");

	}// Fin del método.

	private static void mostrarMetodosRelacionProveedor_Producto() {

		System.out.println(
				"\n---------------------- MÉTODOS DE LA RELACIÓN Proveedor-Producto ----------------------------");
		System.out.println("\tOpción 1: Mostrar el proveedor asociado al producto cuyo nombre se introduzca por parámetro.");
		System.out.println("\tOpción 2: Mostrar los productos del proveedor cuyo cif = 2.");
		System.out.println("\tOpción 3: Mostrar los proveedores que suministran más de 6 productos.");
		System.out.println("\tOpción 4: Mostrar los productos cuyo stock es superior a 850.");
		System.out.println("\tOpción 5: Mostrar los productos que no tienen in proveedor asociado.");
		System.out.println(
				"\tOpción 6: Salir. \n ---------------------------------------------------------------------------------------");

	}// Fin del método.

	private static void mostrarMetodosProducto_Pedido() {

		System.out
				.println("---------------------- MÉTODOS DE LA RELACIÓN Producto-Pedido (N:M) ----------------------------");
		System.out.println("\tOpción 1: Mostrar los pedidos que se registraron con una fecha anterior a la de hoy.");
		System.out.println("\tOpción 2: Mostrar el número de pedidos que se han realizado en el mes de enero.");
		System.out.println("\tOpción 3: Mostrar los productos que se compraron en el pedido con nombre 'pedido1'.");
		System.out.println(
				"\tOpción 4: Mostrar los pedidos en los que ha estado involucrado el producto que se introduzca por teclado.");
		System.out.println(
				"\tOpción 5 (insert): Reaizar la inserción de un pedido cuyo nombre se introduzca como parámetro (probar a introducir un nombre que ya exista en la bbdd).");
		System.out.println("\tOpción 6: Salir.");
	}

	// Método que devuelve una lista con todos los nombres de los productos.
	private static List<String> getNombreDeTodosLosProductosDeLaBD() {

		ProductoDao productoDao = new ProductoDao();
		List<String> nombresDeProductos = new ArrayList<String>();

		for (Producto p : productoDao.getAll()) {
			nombresDeProductos.add(p.getNombre());
		}
		return nombresDeProductos;
	}

	private static void insertarNuevoPedido(Pedido p) throws SQLIntegrityConstraintViolationException {
		PedidoDao pedidoDao = new PedidoDao();
		pedidoDao.save(p);
	}

	public static void main(String[] args) throws PersistenceException {

		// Creamos los objetos de tipo Dao
		ClienteDao clienteDao = new ClienteDao();
		ProductoDao productoDao = new ProductoDao();
		ProveedorDao proveedorDao = new ProveedorDao();
		PedidoDao pedidoDao = new PedidoDao();
		SocioDao socioDao = new SocioDao();

		// Variables del menú.
		int relacionEscogida = 0;
		Scanner sc = new Scanner(System.in);

		// Mostramos las opciones del menú y guardamos leemos la opción introducida por
		// el usuario.
		opcionesMenuEscogerRelacion();
		System.out.print("Opción deseada : ");
		relacionEscogida = sc.nextInt();
		boolean datosInsertados = false;

		while (relacionEscogida >= 1 && relacionEscogida <= 4) {

			if (relacionEscogida == 1) {

				if (!datosInsertados) {
					
					//Controlamos la excepción que se lanza cuando se persisten datos que ya existen en la bbdd.
					try {
						insertDatosCliente_Socio(clienteDao);
						insertDatosProducto_Proveedor(proveedorDao);
						insertDatosProducto_Pedido(pedidoDao, productoDao);
						System.out.println("\nSe ha realizado la inserción de datos correctamente.");
						datosInsertados = true;
					}catch (PersistenceException e) {
						System.out.println("Se ha producido un error en la inserción de datos.");
					}
					
					
				} else {
					System.out.println("La inserción de datos ya ha sido realizada.");
				}

			} else if (relacionEscogida == 2) { // Relación 1:1

				int opcion = 0;
				mostrarOpcionesMenuRelacionCliente_Socio();
				System.out.print("Opción seleccionada: ");
				opcion = sc.nextInt();

				// Recuperamos el cliente de la base de datos filtrando por el número de
				// teléfono.
				Cliente bdc5 = null;

				//Si se ejecuta el programa una segunda vez, se controla el error que buscar un registro que no existe. 
				try {
					bdc5 = clienteDao.getClientePorNumTelefono(623092884);
				} catch (NoResultException nre) {

				}

				while (opcion >= 1 && opcion <= 5) {

////////////////////////////////////////////RELACIÓN 1:1 /////////////////////////////////////////////////////////////////////////////////

					if (opcion == 1) {

						if (!(bdc5 == null)) {
							// Recuperamos el socio de la bb relacionado con el cliente 5 por el id del c5.
							Socio s1 = socioDao.get(bdc5.getSocio().getId()).get();

							// Eliminamos el socio.
							socioDao.delete(s1);
							System.out.println("\nEl socio con id = 1 ha sido eliminado.");

						} else {
							System.out.println(
									"Error. Se intenta recuperar de la base de datos un cliente que no existe.");
						}

					} else if (opcion == 2) {

						if (!(bdc5 == null)) {
							// Creamos de nuevo el socio y lo asignamos al cliente anterior.
							Socio socioNuevo = new Socio(
									(bdc5.getNombre() + bdc5.getApellidos()).toLowerCase().replace(" ", "")
											+ "@gmail.com",
									bdc5, LocalDate.now());

							// Guardamos el nuevo socio.
							try {
								socioDao.save(socioNuevo);
							}catch (PersistenceException e) {
								System.out.println("\nEl socio que se está intentando crear ya existe en la base de datos.");
							}						

						} else {
							System.out.println(
									"Error. Se intenta recuperar de la base de datos un cliente que no existe.");
						}

					} else if (opcion == 3) {

						if (!(bdc5 == null)) {
							
							// Recuperamos el socio de la bbdd y le asignamos un cliente
							Socio socioBD = socioDao.get(3).get();
							bdc5.setSocio(socioBD);
							clienteDao.update(bdc5);

							System.out.println("\nSe ha actualizado el socio con id = 3 y el cliente con id = 5.");							
						}else {
							System.out.println(
									"Error. Se intenta recuperar de la base de datos un cliente que no existe.");
						}						

					} else if (opcion == 4) {

						if (!(bdc5 == null)) {
							// Eliminamos el cliente recuperado de la base de datos.
							clienteDao.delete(bdc5);
							System.out.println(
									"\nLa cliente 'Elena Vega Guillén' y su socio relacionado han sido eliminados.");
						}else {
							System.out.println(
									"Error. Se intenta recuperar de la base de datos un cliente que no existe.");
						}						

					} else if (opcion == 5) {

						int metodoEscogido = 0;
						mostrarMetodosRelacionCliente_Socio();
						System.out.print("Opción escogida: ");
						metodoEscogido = sc.nextInt();

						while (metodoEscogido >= 1 && metodoEscogido <= 5) {

							if (metodoEscogido == 1) {

								// Socios que se dieron de alta antes de una fecha pasada como parámetro:
								System.out.println("\nSocios que se dieron de alta antes de la fecha actual: \n"
										+ socioDao.getSociosQueSeDieronDeAltaAntesDe(LocalDate.now()));

							} else if (metodoEscogido == 2) {

								// Clientes que son socios ordenados por correo electrónico:
								System.out.println("\nClientes que no son socios ordenados por correo electrónico: \n"
										+ clienteDao.getClientesQueSonSociosOrdenadosPorCorreo());

							} else if (metodoEscogido == 3) {

								// Clientes que no son socios ordenados por nombre:
								System.out.println(
										"\nClientes que no son socios: \n" + clienteDao.getClientesQueNoSonSocios());

							} else if (metodoEscogido == 4) {

								// Número de clientes que no son socios:
								System.out.println("\nNúmero de clientes que no son socios: "
										+ clienteDao.getNumDeClientesQueNoSonSocios());

							} else if (metodoEscogido == 5) {

								// Email de los socios:
								System.out.println("\nTodos los email de los socios: " + socioDao.getTodosLosEmail());
							}

							// Volvemos a pedir al usuario que elija uno de los métodos.
							mostrarMetodosRelacionCliente_Socio();
							System.out.print("Opción escogida: ");
							metodoEscogido = sc.nextInt();

						} // Fin del bucle while donde se muestran los métodos.

					} // Fin del bucle while anidado.

					mostrarOpcionesMenuRelacionCliente_Socio();
					System.out.print("Opción seleccionada: ");
					opcion = sc.nextInt();

				} // Fin del while donde se muestra las opciones sobre la Relación Cliente-Socio.

////////////////////////////////////////////RELACIÓN 1:N /////////////////////////////////////////////////////////////////////////////////

			} else if (relacionEscogida == 3) {

				int opcion = 0;
				mostrarOpcionesMenuRelacionProveedor_Producto();
				System.out.print("Opción seleccionada: ");
				opcion = sc.nextInt();

				while (opcion >= 1 && opcion <= 3) {

					// Recuperamos el proveedor con cif = 2 de la bbdd.
					Proveedor bdProv2 = proveedorDao.getProveedorPorNumTelefono(627819054);

					if (opcion == 1) {
						// Recuperamos un proveedor de la base de datos.
						Proveedor bdProv3 = proveedorDao.getProveedorPorNumTelefono(654719031);

						// Eliminamos el proveedor
						proveedorDao.delete(bdProv3);
						System.out.println(
								"\nEl proveedor cuyo cif = 3 ha sido eliminado y los productos que suministraba han sufrido modificaciones. \n");

					} else if (opcion == 2) {

						// Recuperamos de la base de datos aquellos productos que no tienen proveedor
						// asociado.
						List<Producto> prodSinProveedor = productoDao.getProductosSinProveedor();
						System.out.println("Productos que se encuentran sin proveedor: " + prodSinProveedor);

						// Asignamos esos productos al proveedor 2.
						for (Producto p : prodSinProveedor) {
							p.setProveedor(bdProv2);
						}
						// Actualizamos el proveedor.
						proveedorDao.update(bdProv2);
						System.out.println(
								"\nSe han añadido productos al proveedor cuyo cif = 2 y dichos productos vuelven a tener un proveedor asociado. \n");

					} else if (opcion == 3) {

						int metodoEscogido = 0;
						mostrarMetodosRelacionProveedor_Producto();
						System.out.print("Opción escogida: ");
						metodoEscogido = sc.nextInt();

						while (metodoEscogido >= 1 && metodoEscogido <= 5) {

							if (metodoEscogido == 1) {

								System.out.println("Productos disponibles: "  + getNombreDeTodosLosProductosDeLaBD());
								System.out.print("Producto seleccionado: ");
								String productoEscogido = sc.next();
								// Obtenemos un registro de la tabla Producto de la base de datos.
								Producto bdVaso = productoDao.getProductoPorNombre(productoEscogido);

								// Obtener el proveedor asociado a un producto en concreto:
								System.out.println("\nProveedor que suministra el producto con nombre '" + productoEscogido + "': "
										+ proveedorDao.getProveedorAsociadoAunProducto(bdVaso));

							} else if (metodoEscogido == 2) {

								// Productos del proveedor cuyo cif = 2:
								System.out.println("\nProductos del proveedor cuyo cif = 2: "
										+ productoDao.getProductosDeUnProveedor(bdProv2));

							} else if (metodoEscogido == 3) {

								// Proveedores que suministran más de 5 productos:
								System.out.println("\nProveedores que suministran más de 6 productos : "
										+ proveedorDao.getProveedoresConMasDeNproductos(6));

							} else if (metodoEscogido == 4) {

								System.out.println("\nProductos son un stock superior a 850: "
										+ productoDao.findProductosConMasDeNstock(850));
							} else if (metodoEscogido == 5) {

								// Productos que no tienen ningún proveedor asociado.
								System.out.println("\nProductos que no tienen proveedor asociado: "
										+ productoDao.getProductosSinProveedor());
							}

							// Volvemos a mostrar los métodos disponibles.
							mostrarMetodosRelacionProveedor_Producto();
							System.out.print("Opción escogida: ");
							metodoEscogido = sc.nextInt();

						} // Fin del bucle while donde se muestran los métodos.

					} // Fin de la opción 3 de la relación Proveedor-Producto.

					// Volver a solicitar al usuario elija una de las opciones sobre la relación
					// Proveedor-Producto.
					mostrarOpcionesMenuRelacionProveedor_Producto();
					System.out.print("Opción seleccionada: ");
					opcion = sc.nextInt();

				} // Fin del while donde se muestra las opciones sobre la Relación
					// Proveedor-Producto.

////////////////////////////////////////////RELACIÓN N:M /////////////////////////////////////////////////////////////////////////

			} else if (relacionEscogida == 4) {

				int metodoEscogido = 0;
				mostrarMetodosProducto_Pedido();
				System.out.print("Opción escogida: ");
				metodoEscogido = sc.nextInt();

				while (metodoEscogido >= 1 && metodoEscogido <= 5) {

					if (metodoEscogido == 1) {

						// Pedidos con fecha posterior a la introducida por parámetro (hoy):
						System.out.println("\nPedidos que se realizaron con fecha anterior a la de hoy: " + pedidoDao.getPedidosConFechaAnteriorA(LocalDate.now()) + "\n");

					} else if (metodoEscogido == 2) {

						// Número de pedidos que se han realizado en enero:
						System.out.println("\nNúmero de pedidos que se han realizado en enero: " + pedidoDao.numPedidosRealizadosEnUnMes(Month.JANUARY) + "\n");

					} else if (metodoEscogido == 3) {

						Pedido bdPed1 = pedidoDao.getPedidoPorNombre("pedido1");
						// Productos del pedido 1:
						System.out.println("\nProductos que se compraron en el pedido con nombre '" + bdPed1.getNombre() + "': "
								+ "\n" + bdPed1.getProductos() + "\n");

					} else if (metodoEscogido == 4) {

						System.out.println("\nProductos disponibles: " + getNombreDeTodosLosProductosDeLaBD());
						System.out.print("Producto escogido: ");
						String producto = sc.next();

						// Pedidos en los que ha estado involucrado el producto introducido por consola:
						Producto productoBD = productoDao.getProductoPorNombre(producto);
						System.out.println("\nPedidos donde el producto con nombre '" + productoBD.getNombre()
								+ "' ha estado involucrado: " + productoBD.getPedidos() + "\n");

					} else if (metodoEscogido == 5) {

						System.out.print("Introduzca el nombre del pedido: ");
						String nombrePedido = sc.next();

						Pedido pedido = new Pedido(LocalDate.now(), nombrePedido);
						// Añadimos productos al pedido.
						pedido.addProducto(productoDao.getProductoPorNombre("vaso"));
						pedido.addProducto(productoDao.getProductoPorNombre("cuadro"));

						try {
							insertarNuevoPedido(pedido);
							System.out.println("\nSe ha insertado nuevo pedido correctamente.\n");
						} catch (PersistenceException e) {
							System.out.println("\nEl error al intentar persistir el producto. El pedido ya existe en la base de datos.\n");
						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("Ya existe un pedido con el nombre que ha introducido por parámetro. \n");
						}

					} // Fin de la opción 5.
					
					mostrarMetodosProducto_Pedido();
					System.out.print("Opción escogida: ");
					metodoEscogido = sc.nextInt();					

				} // Find el bucle while donde se muestran los métodos de la relación
					// Producto-Pedido.
			}

			// Se muestran las opciones del menú que permite escoger la relación y leemos la
			// selección del usuario.
			opcionesMenuEscogerRelacion();
			System.out.print("Opción deseada : ");
			relacionEscogida = sc.nextInt();

		} // Fin del bucle while.

		System.out.println("Fin del programa.");
		sc.close();

	}// Fin del main.

}
// Fin de la clase.
