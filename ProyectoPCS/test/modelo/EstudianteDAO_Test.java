package modelo;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class EstudianteDAO_Test {
    
    EstudianteDAO estudianteDAO = null;
    EstudiantePOJO estudiante = null;
    
    @Before
    public void before(){
        estudianteDAO = new EstudianteDAO();
    }
    
    @Test 
    public void recuperarEstudianteTest() throws Exception{
        estudiante = estudianteDAO.recuperar("S18012180");
        assertTrue(estudiante.getNombre().equalsIgnoreCase("Omar"));
    }
    
    @Test 
    public void recuperarNombreEstudianteTest() throws Exception{
        String nombre = estudianteDAO.recuperarNombreEstudiante("S18012180");
        assertTrue(nombre.equalsIgnoreCase("Omar"));
    }
    
    @Test 
    public void recuperarNombreOrganizacionTest() throws Exception{
        String nombreOrganizacion = estudianteDAO.recuperarNombreOrganizacion("S18012183");
        assertTrue(nombreOrganizacion.equalsIgnoreCase("Telmex"));
    }
    
    @Test 
    public void recuperarNombreProyectoTest() throws Exception{
        String nombreProyecto = estudianteDAO.recuperarNombreProyecto("S18012183");
        assertTrue(nombreProyecto.equalsIgnoreCase("PagoMex"));
    }
    
    @Test 
    public void recuperarClaveProyectoTest() throws Exception{
        int claveProyecto = estudianteDAO.recuperarClaveProyecto("S18012183");
        assertTrue(claveProyecto == 912);
    }
    
    @Test 
    public void rcuperarClaveExpedienteTest() throws Exception{
        int claveExpediente = estudianteDAO.recuperaClaveExpediente("S18012183");
        assertTrue(claveExpediente == 1);
    }
    
    @Test 
    public void asignarProyectoTest() throws Exception{
        estudianteDAO.asginarProyecto("S18012182", 812);
        int claveExpediente = estudianteDAO.recuperaClaveExpediente("S18012180");
        assertTrue(claveExpediente == 3);
    }
    
    @Test 
    public void recuperarEstudiantesTest() throws Exception{
        ObservableList<EstudiantePOJO> estudiantes = estudianteDAO.getEstudiantes();
        estudiante = estudiantes.get(0);
        assertTrue(estudiante.getNombre().equalsIgnoreCase("Emilio Antonio"));
    }
    
    @Test 
    public void reuperarselecciones() throws Exception{
        ArrayList<SeleccionProyectoPOJO> selecciones = estudianteDAO.getSelecciones("S18012180");
        SeleccionProyectoPOJO  seleccion = selecciones.get(0);
        assertTrue(seleccion.getClaveProyecto() == 711);
    }

}
