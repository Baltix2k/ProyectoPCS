package modelo;

import java.time.LocalDate;

public class ReportePOJO extends ArchivoPOJO {
    int horasReportadas;
    String tipoReporte;

    public ReportePOJO(int horasReportadas, String tipoReporte) {
        this.horasReportadas = horasReportadas;
        this.tipoReporte = tipoReporte;
    }
    
    public ReportePOJO(){}

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
