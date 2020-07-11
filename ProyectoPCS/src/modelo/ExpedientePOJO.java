package modelo;

import java.time.LocalDate;

/**
 * Clase que representa como objeto el EXPEDIENTE de los ESTUDIANTES
 * registrados en la BD.
 * 
 * @version 1.0
 */
public class ExpedientePOJO {
    LocalDate fechaFinPP;
    LocalDate fechainicioPP;
    int horasAcumuladas;
    int numeReportesEntregados;

    public ExpedientePOJO(LocalDate fechaFinPP, LocalDate fechainicioPP, 
            int horasAcumuladas, int numeReportesEntregados) {
        this.fechaFinPP = fechaFinPP;
        this.fechainicioPP = fechainicioPP;
        this.horasAcumuladas = horasAcumuladas;
        this.numeReportesEntregados = numeReportesEntregados;
    }

    public LocalDate getFechaFinPP() {
        return fechaFinPP;
    }

    public void setFechaFinPP(LocalDate fechaFinPP) {
        this.fechaFinPP = fechaFinPP;
    }

    public LocalDate getFechainicioPP() {
        return fechainicioPP;
    }

    public void setFechainicioPP(LocalDate fechainicioPP) {
        this.fechainicioPP = fechainicioPP;
    }

    public int getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(int horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public int getNumeReportesEntregados() {
        return numeReportesEntregados;
    }

    public void setNumeReportesEntregados(int numeReportesEntregados) {
        this.numeReportesEntregados = numeReportesEntregados;
    }
    
    
}
