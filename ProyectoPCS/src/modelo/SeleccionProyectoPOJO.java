package modelo;

import java.time.LocalDate;

/**
 * Clase que representa como objeto la SELECCIONPROYECTO de los ESTUDIANTES
 * registrados en la BD.
 *
 * @version 1.0
 */
public class SeleccionProyectoPOJO {

    int claveProyecto;
    LocalDate fecha;
    String periodo;

    public SeleccionProyectoPOJO(int claveProyecto, LocalDate fecha,
            String periodo) {
        this.claveProyecto = claveProyecto;
        this.fecha = fecha;
        this.periodo = periodo;
    }

    public int getClaveProyecto() {
        return claveProyecto;
    }

    public void setClaveProyecto(int claveProyecto) {
        this.claveProyecto = claveProyecto;
    }

    public SeleccionProyectoPOJO(LocalDate fecha, String periodo) {
        this.fecha = fecha;
        this.periodo = periodo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
