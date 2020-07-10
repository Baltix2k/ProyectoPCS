package modelo;

import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ReporteDAO_Test {
    
    ReporteDAO reporteDAO = null;
    ReportePOJO reporte = null;
    
    @Before
    public void before(){
        reporteDAO = new ReporteDAO();
    }
    
    @Test 
    public void recuperarReportesTest(){
        ObservableList<ReportePOJO> reportes = reporteDAO.getReportes("S18012183");
        reporte = reportes.get(0);
        assertTrue(reporte.getTipoReporte().equalsIgnoreCase("Inicial"));
    }
    
    @Test 
    public void recuperarHorasTest(){
        int horasAcomuladas = reporteDAO.recuperarHoras("S18012183");
        assertTrue(horasAcomuladas == 52);
    }
}
