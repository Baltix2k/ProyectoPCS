package modelo;

import java.time.LocalDate;

public class ArchivoPOJO {
    String titulo;
    String rutaUbicacion;
    LocalDate fechaEntrega;

    public ArchivoPOJO(String titulo, String rutaUbicacion, LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        this.rutaUbicacion = rutaUbicacion;
        this.titulo = titulo;
    }

    public ArchivoPOJO() {
        
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getRutaUbicacion() {
        return rutaUbicacion;
    }

    public void setRutaUbicacion(String rutaUbicacion) {
        this.rutaUbicacion = rutaUbicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
