package modelo;

import java.time.LocalDate;

public class ArchivoPOJO {
    int idArchivo;
    byte[] archivo;
    String titulo;
    LocalDate fechaEntrega;

    public ArchivoPOJO(int idArchivo, String titulo,LocalDate fechaEntrega) {
        this.idArchivo = idArchivo;
        //this.archivo = archivo;
        this.fechaEntrega = fechaEntrega;
        this.titulo = titulo;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public ArchivoPOJO() {
        
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public byte[] getArchivo() {
        return archivo;
    }
    
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
    
    
    
}
