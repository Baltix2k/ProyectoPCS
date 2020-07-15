package modelo;

import javafx.collections.ObservableList;

/**
 * Esta interfaz establece los metodos que utilizará la clase ArchivoDAO para
 * lidiar con la evolución constante de la misma.
 *
 * @version 1.0
 */
public interface ArchivoDAO_Interfaz {

    public ObservableList<ArchivoPOJO> getArchivos(String matricula)
            throws Exception;

    public void subirArchivo(ArchivoPOJO arch, int claveExp) throws Exception;

    public int obtenerClaveArchivo() throws Exception;
}
