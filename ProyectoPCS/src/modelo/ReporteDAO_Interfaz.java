package modelo;

import javafx.collections.ObservableList;

/**
 * Esta interfaz establece los metodos que  utilizará la clase ReporteDAO para lidiar con la evolución constante de la misma.
 * 
 * @version 1.0
 */
public interface ReporteDAO_Interfaz {
    public ObservableList<ReportePOJO> getReportes(String matricula) throws Exception;
    
    public void subirReporte(ReportePOJO report, int idArch) throws Exception;
    
    public int recuperarHoras(String matricula) throws Exception;
}
