package modelo;

import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ArchivoDAO_Test {
    
    ArchivoDAO archivoDAO = null;
    ArchivoPOJO archivo = null;
    
    @Before
    public void before(){
        archivoDAO = new ArchivoDAO();
    }
    
    @Test 
    public void recuperarArchivosTest(){
        ObservableList<ArchivoPOJO> archivos = archivoDAO.getArchivos("S18012183");
        assertTrue(archivos.isEmpty());
    }
    
    @Test 
    public void recuerarMaxIDTest(){
        int clave = archivoDAO.obtenerClaveArchivo();
        assertTrue(clave == 3);
    }
}
