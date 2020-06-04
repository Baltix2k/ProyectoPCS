package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstudiantePOJO {
    String apellidoMaterno;
    String apellidoPaterno;
    String nombre;
    String correoPersonal;
    String estado;
    String matricula;
    float promedio;
    String telefono;
    
    public EstudiantePOJO(String apellidoMaterno, String apellidoPaterno, String nombre, String correoPersonal, String estado, String matricula, float promedio, String telefono) {
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.nombre = nombre;
        this.correoPersonal = correoPersonal;
        this.estado = estado;
        this.matricula = matricula;
        this.promedio = promedio;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
      
}
