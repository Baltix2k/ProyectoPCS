package modelo;

/**
 * Clase que representa como objeto las ORGANIZACIONES registradas en la BD.
 * 
 * @version 1.0
 */
public class OrganizacionPOJO {
    String calle;
    String codigoPostal;
    String colonia;
    String correo;
    String nombre;
    String numExt;
    String telefono;

    public OrganizacionPOJO(String calle, String codigoPostal, String colonia, 
            String correo, String nombre, String numExt, String telefono) {
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.correo = correo;
        this.nombre = nombre;
        this.numExt = numExt;
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
