package modelo;

import java.time.LocalDate;

/**
 * Clase que representa como objeto los ARCHIVOS entregados por los ESTUDIANTES
 * registrados en la BD.
 * 
 * @version 1.0
 */
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
