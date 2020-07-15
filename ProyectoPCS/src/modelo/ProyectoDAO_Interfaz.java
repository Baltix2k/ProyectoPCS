package modelo;

import javafx.collections.ObservableList;

/**
 * Esta interfaz establece los metodos que  utilizará la clase ProyectoDAO para lidiar con la evolución constante de la misma.
 * 
 * @version 1.0
 */
public interface ProyectoDAO_Interfaz {
    public ObservableList<ProyectoPOJO> getProyectos() throws Exception;
    
    public String recuperarNombreProyecto() throws Exception;
    
    public String recuperarNombreOrganizacion(int claveProyecto) throws Exception;
    
    public String recuperarNombre(int claveproyecto) throws Exception;
}
