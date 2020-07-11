package modelo;

import java.time.LocalDate;

/**
 * Clase que representa como objeto los REPORTES entregados por los ESTUDIANTES
 * registrados en la BD.
 * 
 * @version 1.0
 */
public class ReportePOJO extends ArchivoPOJO {
    int horasReportadas;
    String tipoReporte;

    public ReportePOJO(int horasReportadas, String tipoReporte) {
        this.horasReportadas = horasReportadas;
        this.tipoReporte = tipoReporte;
    }
    
    public ReportePOJO(){}

    ReportePOJO(int idArchivo, String titulo, LocalDate fechaEntrega, 
            int horasReportadas, String tipoReporte) {
        super(idArchivo,titulo,fechaEntrega);
        this.horasReportadas = horasReportadas;
        this.tipoReporte = tipoReporte;
    }

    public int getHorasReportadas() {
        return horasReportadas;
    }

    public void setHorasReportadas(int horasReportadas) {
        this.horasReportadas = horasReportadas;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    
}
