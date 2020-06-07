package modelo;

import java.time.LocalDate;

public class ProyectoPOJO {
    String actividades;
    int claveProyecto;
    String descripcion;
    LocalDate fechaRegistro;
    int noEstudiantes;
    String nombre;
    String responsableNombre;

    public ProyectoPOJO(String actividades, int claveProyecto, String descripcion, LocalDate fechaRegistro, int noEstudiantes, String nombre, String responsableNombre) {
        this.actividades = actividades;
        this.claveProyecto = claveProyecto;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.noEstudiantes = noEstudiantes;
        this.nombre = nombre;
        this.responsableNombre = responsableNombre;
    }

    ProyectoPOJO(String nombre, String nombreOrganizacion, String descripcion) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.responsableNombre = nombreOrganizacion;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public int getClaveProyecto() {
        return claveProyecto;
    }

    public void setClaveProyecto(int claveProyecto) {
        this.claveProyecto = claveProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getNoEstudiantes() {
        return noEstudiantes;
    }

    public void setNoEstudiantes(int noEstudiantes) {
        this.noEstudiantes = noEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResponsableNombre() {
        return responsableNombre;
    }

    public void setResponsableNombre(String responsableNombre) {
        this.responsableNombre = responsableNombre;
    }
    
    
}
