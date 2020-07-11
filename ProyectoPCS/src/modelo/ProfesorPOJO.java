package modelo;

/**
 * Clase que representa como objeto los PROFESORES registrados en la BD.
 * 
 * @version 1.0
 */
public class ProfesorPOJO {
    String apellidoMaterno;
    String apellidoPaterno;
    String nombre;
    int rfc;

    public ProfesorPOJO(String apellidoMaterno, String apellidoPaterno, String nombre, int rfc) {
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.nombre = nombre;
        this.rfc = rfc;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRfc() {
        return rfc;
    }

    public void setRfc(int rfc) {
        this.rfc = rfc;
    }
    
    
}
