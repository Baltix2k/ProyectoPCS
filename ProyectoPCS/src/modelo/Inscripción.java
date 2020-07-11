package modelo;

import java.time.LocalDate;

/**
 * Clase que representa como objeto la INSCRIPCIÓN de los ESTUDIANTES
 * registrados en la BD.
 * 
 * @version 1.0
 */
public class Inscripción {
    int bloque;
    int cupo;
    String estatus;
    LocalDate fecha;
    int nrc;
    String periodo;
    int seccion;
    String tipo;

    public Inscripción(int bloque, int cupo, String estatus, LocalDate fecha, 
            int nrc, String periodo, int seccion, String tipo) {
        this.bloque = bloque;
        this.cupo = cupo;
        this.estatus = estatus;
        this.fecha = fecha;
        this.nrc = nrc;
        this.periodo = periodo;
        this.seccion = seccion;
        this.tipo = tipo;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
