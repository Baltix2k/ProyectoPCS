package modelo;

import java.time.LocalDate;

public class ReportePOJO extends ArchivoPOJO {
    int horasReportadas;
    String tipoReporte;

    public ReportePOJO(String titulo, String rutaUbicacion, LocalDate fechaEntrega, int horasReportadas, String tipoReporte) {
        super(titulo, rutaUbicacion, fechaEntrega);
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
