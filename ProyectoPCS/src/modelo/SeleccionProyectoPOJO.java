package modelo;

import java.time.LocalDate;

public class SeleccionProyectoPOJO {
    LocalDate fecha;
    String periodo;

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
