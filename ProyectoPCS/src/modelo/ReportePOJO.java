package modelo;

import java.time.LocalDate;

public class ReportePOJO {
    LocalDate fechaEntregada;
    int horasReportadas;
    String mes;
    int numero;
    String tipoReporte;

    public ReportePOJO(LocalDate fechaEntregada, int horasReportadas, String mes, int numero, String tipoReporte) {
        this.fechaEntregada = fechaEntregada;
        this.horasReportadas = horasReportadas;
        this.mes = mes;
        this.numero = numero;
        this.tipoReporte = tipoReporte;
    }

    public LocalDate getFechaEntregada() {
        return fechaEntregada;
    }

    public void setFechaEntregada(LocalDate fechaEntregada) {
        this.fechaEntregada = fechaEntregada;
    }

    public int getHorasReportadas() {
        return horasReportadas;
    }

    public void setHorasReportadas(int horasReportadas) {
        this.horasReportadas = horasReportadas;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    
}
