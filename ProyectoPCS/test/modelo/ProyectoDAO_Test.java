package modelo;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ProyectoDAO_Test{
    ProyectoDAO proyectoDAO;
    ProyectoPOJO proyecto;
    
    @Before
    public void before(){
        proyectoDAO = new ProyectoDAO();
    }
    
    @Test
    public void testRecuperarNombre(){
        String nombre = proyectoDAO.recuperarNombre(712);
        assertTrue(nombre.equalsIgnoreCase("SecureServ"));
    }
    
    @Test
    public void testRecuperarNombreOrganizacion(){
        String nombreOrganizacion = proyectoDAO.recuperarNombreOrganizacion(712);
        assertTrue(nombreOrganizacion.equalsIgnoreCase("Softeck"));
    }
}
