package com.accesodatos.hibernate.gestiontienda.utiles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerUtil {
	
	/*clase sencilla de utilidad que nos ayude a establecer y manejar las conexiones con la base de datos*/
	
	public static EntityManager getEntityManager() {		

		//en la siguiente línea se pone lo que pusimos en <persistence-unit name="GestionTienda" 
		//en el fichero persistence.xml
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionTienda");
		EntityManager manager = factory.createEntityManager();
		
		return manager;
	}	
	
	/*en esta clase crearemos un único método estático, getEntityManager que no recibe nada, pero devuelve un objeto EntityManager, 
	 * el de la librería de persistencia de Java. Crearemos un objeto factoría dentro de este método. A la hora de crear la factoría, 
	 * es importante que le indiquemos el nombre correcto que le hayamos dado a la unidad de persistencia del fichero de configuración Persistence.xml, 
	 * en nuestro caso, OtraReunionMas, sin acentos, claro. 
	 * Y ahora que ya tenemos la factoría, le pedimos al gestor de entidades, el EntityManager que necesitamos devolver. */
	

}
