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
    public void recuperarArchivosTest() throws Exception{
        ObservableList<ArchivoPOJO> archivos = archivoDAO.getArchivos("S18012183");
        assertTrue(archivos.isEmpty());
    }
    
    @Test 
    public void recuerarMaxIDTest() throws Exception{
        int clave = archivoDAO.obtenerClaveArchivo();
        assertTrue(clave == 6);
    }
}
