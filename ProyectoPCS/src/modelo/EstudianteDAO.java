package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que encargada de realizar todas las operaciones de los objetos
 * ESTUDIANTE manejados dentro del sistema con los registrados en la BD.
 *
 * @version 1.0
 */
public class EstudianteDAO {

    /**
     * Recupera un objeto ESTUDIANTE por medio de una matricula especifica.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return EstudiantePOJO Objeto ESTUDIANTE.
     */
    public EstudiantePOJO recuperar(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        EstudiantePOJO e = null;
        String sql = "SELECT * FROM estudiante WHERE matricula = '" + 
                matricula + "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                e = new EstudiantePOJO(rs.getString(1), rs.getString(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getFloat(7), rs.getString(8));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método recuperar()");
            ex.printStackTrace();
        }
        return e;
    }

    /**
     * Recupera el nombre de un ESTUDIANTE especificado.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return nombreEstudiante Nombre del ESTUDIAIANTE especificado.
     */
    public String recuperarNombreEstudiante(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreEstudiante = null;
        String sql = "SELECT estudiante.nombre FROM estudiante WHERE matricula ="
                + " '" + matricula + "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreEstudiante = rs.getString(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método recuperarNombreEstudiante()");
            ex.printStackTrace();
        }
        return nombreEstudiante;
    }

    /**
     * Recupera el nombre de la ORGANIZACION perteneciente al PROYECTO asignado
     * a un ESTUDIANTE especificado.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return nombreOrganizacion Nombre de la ORGANIZACION especificado.
     */
    public String recuperarNombreOrganizacion(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreOrganizacion = null;
        String sql = "SELECT proyecto.nombreorganizacion FROM inscripcion JOIN "
                + "proyecto ON inscripcion.claveProyecto = "
                + "proyecto.claveProyecto WHERE matricula = '" + matricula + 
                "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreOrganizacion = rs.getString(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método "
                    + "recuperarNombreOrganizacion()");
            ex.printStackTrace();
        }
        return nombreOrganizacion;
    }

    /**
     * Recupera el nombre del PROYECTO asignado a un ESTUDIANTE especificado.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return nombreProyecto Nombre del PROYECTO especificado.
     */
    public String recuperarNombreProyecto(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreProyecto = null;
        String sql = "SELECT proyecto.nombre FROM inscripcion JOIN proyecto ON "
                + "inscripcion.claveProyecto = proyecto.claveProyecto WHERE "
                + "matricula = '" + matricula + "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombreProyecto = rs.getString(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método "
                    + "recuperarNombreProyecto()");
            ex.printStackTrace();
        }
        return nombreProyecto;
    }

    /**
     * Recupera la clave del PROYECTO asignado a un ESTUDIANTE especificado.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return claveProyecto Clave del PROYECTO especificado.
     */
    public int recuperarClaveProyecto(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int claveProyecto = 0;
        String sql = "SELECT inscripcion.claveProyecto FROM inscripcion "
                + "WHERE matricula = '" + matricula + "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                claveProyecto = rs.getInt(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método "
                    + "recuperarClaveProyecto)");
            ex.printStackTrace();
        }
        return claveProyecto;
    }

    /**
     * Recupera la clave del EXPEDIENTE perteneciente a un ESTUDIANTE 
     * especificado.
     * 
     * @param matricula Matricula del ESTUDIANTE a recuperar.
     * @return claveExpediente Clave del EXPEDIENTE especificado.
     */
    public int recuperaClaveExpediente(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int claveExpediente = 0;
        String sql = "SELECT expediente.clave FROM expediente WHERE matricula "
                + "= '" + matricula + "';";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                claveExpediente = rs.getInt(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: Clase EstudianteDAO, método "
                    + "recuperarClaveExpediente)");
            ex.printStackTrace();
        }
        return claveExpediente;
    }

    /**
     * Recupera de la base de datos la lista de ESTUDIANTES registrados en la 
     * BD.
     * 
     * @return obs Lista contenedora de los ESTUDIANTES.
     */
    public ObservableList<EstudiantePOJO> getEstudiantes() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select estudiante.nombre, estudiante.apellidopaterno, "
                + "estudiante.apellidomaterno, estudiante.matricula from "
                + "estudiante where not exists (select estudiante.matricula "
                + "from expediente where expediente.matricula = "
                + "estudiante.matricula);";
        ObservableList<EstudiantePOJO> obs = FXCollections.
                observableArrayList();
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                String apellidoPaterno = rs.getString("APELLIDOPATERNO");
                String apellidoMaterno = rs.getString("APELLIDOMATERNO");
                String matricula = rs.getString("MATRICULA");
                EstudiantePOJO c = new EstudiantePOJO(nombre, apellidoPaterno, 
                        apellidoMaterno, matricula);
                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ArchivoDAO, método "
                    + "getEstudiantes()");
            e.printStackTrace();
        }
        return obs;
    }

    /**
     * Recupera de la base de datos la lista de SELECCIONPROYECTO de un
     * ESTUDIANTE especificado registrado en la BD.
     * 
     * @param matricula Matricula del ESTUDIANTE del cual se recuperaran las 
     * SELECCIONPROYECTO.
     * @return obs Lista contenedora de las SELECCIONPROYECTO.
     */
    public ArrayList<SeleccionProyectoPOJO> getSelecciones(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select claveproyecto, fecha, periodo from "
                + "seleccionproyecto where matricula = '" + matricula + "';";
        ArrayList<SeleccionProyectoPOJO> obs 
                = new ArrayList<SeleccionProyectoPOJO>();
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int claveproyecto = rs.getInt("claveproyecto");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"), 
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String periodo = rs.getString("periodo");
                SeleccionProyectoPOJO c = new SeleccionProyectoPOJO(
                        claveproyecto, fecha, periodo);
                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método "
                    + "getSelecciones()");
            e.printStackTrace();
        }
        return obs;
    }

    /**
     * Realiza la asignación de un ESTUDIANTE y un PROYECTO registrados en la BD
     * registrando la INSCRIPCION y abriendo el EXPEDIENTE con las respectivas
     * claves de ambos parametros.
     * 
     * @param matriculaEstudianteElegido Matricula del ESTUDIANTE a asignar.
     * @param claveProyectoElegido Clave del PROYECTO a asignar.
     */
    public void asginarProyecto(String matriculaEstudianteElegido, 
            int claveProyectoElegido) {
        Connection con = null;
        Statement stm = null;
        LocalDate fechaInicioPP = LocalDate.now();
        LocalDate fechaFinPP = fechaInicioPP.plusMonths(6);
        String sql = "INSERT INTO inscripcion VALUES ('" 
                + matriculaEstudianteElegido + "', " + claveProyectoElegido 
                + ", 234, 1, 35, 'Inscrito', '" + fechaInicioPP 
                + "', 123, 'FEB2020-AGO2020', 1, 'Primera inscripcion');";
        String sql2 = "INSERT INTO expediente VALUES (null, '" + fechaFinPP 
                + "', '" + fechaInicioPP + "', 0, 0, '" 
                + matriculaEstudianteElegido + "', " + claveProyectoElegido 
                + ");";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método getSelecciones()");
            e.printStackTrace();
        }
    }
}
