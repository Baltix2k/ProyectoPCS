package modelo;

public class ArchivoPOJO {
    String estado;
    String rutaUbicacion;
    String titulo;

    public ArchivoPOJO(String estado, String rutaUbicacion, String titulo) {
        this.estado = estado;
        this.rutaUbicacion = rutaUbicacion;
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
