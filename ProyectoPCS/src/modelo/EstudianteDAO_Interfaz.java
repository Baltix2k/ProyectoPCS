package modelo;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Esta interfaz establece los metodos que utilizará la clase EstudianteDAO para
 * lidiar con la evolución constante de la misma.
 *
 * @version 1.0
 */
public interface EstudianteDAO_Interfaz {

    public EstudiantePOJO recuperar(String matricula) throws Exception;

    public String recuperarNombreEstudiante(String matricula) throws Exception;

    public String recuperarNombreOrganizacion(String matricula)
            throws Exception;

    public String recuperarNombreProyecto(String matricula) throws Exception;

    public int recuperarClaveProyecto(String matricula) throws Exception;

    public int recuperaClaveExpediente(String matricula) throws Exception;

    public ObservableList<EstudiantePOJO> getEstudiantes() throws Exception;

    public ArrayList<SeleccionProyectoPOJO> getSelecciones(String matricula)
            throws Exception;

    public void asginarProyecto(String matriculaEstudianteElegido,
            int claveProyectoElegido) throws Exception;
}
