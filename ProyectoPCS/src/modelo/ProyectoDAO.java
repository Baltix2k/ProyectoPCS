package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que encargada de realizar todas las operaciones de los objetos PROYECTO
 * manejados dentro del sistema con los registrados en la BD.
 *
 * @version 1.0
 */
public class ProyectoDAO {

    /**
     * Recupera de la base de datos la lista de PROYECTOS registrados en la BD.
     *
     * @return obs Lista contenedora de los PROYECTOS.
     */
    public ObservableList<ProyectoPOJO> getProyectos() throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select proyecto.claveproyecto, proyecto.nombre, "
                + "proyecto.nombreOrganizacion, proyecto.descripcion from "
                + "proyecto where not exists (select proyecto.claveproyecto "
                + "from expediente where expediente.claveproyecto = "
                + "proyecto.claveproyecto);";
        ObservableList<ProyectoPOJO> obs = FXCollections.observableArrayList();
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Integer claveproyecto = rs.getInt("CLAVEPROYECTO");
                String nombre = rs.getString("NOMBRE");
                String nombreOrganizacion = rs.getString("NOMBREORGANIZACION");
                String descripcion = rs.getString("DESCRIPCION");
                System.out.println("CLAVEPROYECTO: " + claveproyecto
                        + " NOMBRE: " + nombre + " NOMBREORGANIZACION:"
                        + nombreOrganizacion);
                ProyectoPOJO c = new ProyectoPOJO(claveproyecto, nombre,
                        nombreOrganizacion, descripcion);
                obs.add(c);
            }
        } catch (SQLException e) {
            throw new Exception("Error en Clase ProyectoDAO, "
                    + "método readAll(): " + e.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        return obs;
    }

    /**
     * Recupera de la base de datos el nombre del ultimo PROYECTO registrado en
     * la BD.
     *
     * @return nombreProyecto Nombre del PROYECTO.
     */
    public String recuperarNombreProyecto() throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreProyecto = null;
        String sql = "SELECT nombre FROM proyecto WHERE noEstudiantes > 0;";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreProyecto = rs.getString(1);
            }
        } catch (SQLException ex) {
            throw new Exception("Error en Clase ProyectoDAO, "
                    + "método recuperarNombreProyecto: " + ex.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        return nombreProyecto;
    }

    /**
     * Recupera de la base de datos el nombre de la ORGANIZACION de un PROYECTO
     * especificado registrado en la BD.
     *
     * @param claveProyecto Clave del PROYECTO del cual se requiere el nombre de
     * la ORGANIZACIÓN.
     * @return nombreOrganizacion Nombre de la ORGANIZACIÓN.
     */
    public String recuperarNombreOrganizacion(int claveProyecto) throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreOrganizacion = null;
        String sql = "SELECT proyecto.nombreorganizacion FROM proyecto WHERE "
                + "proyecto.claveProyecto = " + claveProyecto + ";";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreOrganizacion = rs.getString(1);
            }
        } catch (SQLException ex) {
            throw new Exception("Error en Clase ProyectoDAO, método "
                    + "recuperarNombreOrganizacion: " + ex.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        return nombreOrganizacion;
    }

    /**
     * Recupera el nombre de un PROYECTO especificado por medio de la clave.
     *
     * @param claveproyecto Clave del PROYECTO requerido.
     * @return nombreProyecto Nombre del PROYECTO.
     */
    public String recuperarNombre(int claveproyecto) throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreProyecto = null;
        String sql = "SELECT nombre FROM proyecto WHERE claveproyecto = " 
                + claveproyecto + ";";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreProyecto = rs.getString(1);
            }
        } catch (SQLException ex) {
            throw new Exception("Error en Clase ProyectoDAO, método "
                    + "recuperarNombre: " + ex.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        return nombreProyecto;
    }
}
