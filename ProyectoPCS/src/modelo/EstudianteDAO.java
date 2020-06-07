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

public class EstudianteDAO {

    public EstudiantePOJO recuperar(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        EstudiantePOJO e = null;
        String sql = "SELECT * FROM estudiante WHERE matricula = '" + matricula + "';";

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                e = new EstudiantePOJO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getFloat(7), rs.getString(8));
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
    
    public String recuperarNombreEstudiante(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreEstudiante = null;
        String sql = "SELECT estudiante.nombre FROM estudiante WHERE matricula = '" + matricula + "';";

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

    public String recuperarNombreOrganizacion(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreOrganizacion = null;
        String sql = "SELECT proyecto.nombreorganizacion FROM inscripcion JOIN proyecto ON inscripcion.claveProyecto = proyecto.claveProyecto WHERE matricula = '" + matricula + "';";

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
            System.out.println("Error: Clase EstudianteDAO, método recuperarNombreOrganizacion()");
            ex.printStackTrace();
        }
        return nombreOrganizacion;
    }

    public String recuperarNombreProyecto(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreProyecto = null;
        String sql = "SELECT proyecto.nombre FROM inscripcion JOIN proyecto ON inscripcion.claveProyecto = proyecto.claveProyecto WHERE matricula = '" + matricula + "';";

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
            System.out.println("Error: Clase EstudianteDAO, método recuperarNombreProyecto()");
            ex.printStackTrace();
        }
        return nombreProyecto;
    }

    public int recuperarClaveProyecto(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int claveProyecto = 0;
        String sql = "SELECT inscripcion.claveProyecto FROM inscripcion WHERE matricula = '" + matricula + "';";

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
            System.out.println("Error: Clase EstudianteDAO, método recuperarClaveProyecto)");
            ex.printStackTrace();
        }
        return claveProyecto;
    }

    public int recuperaClaveExpediente(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int claveExpediente = 0;
        String sql = "SELECT expediente.clave FROM expediente WHERE matricula = '" + matricula + "';";

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
            System.out.println("Error: Clase EstudianteDAO, método recuperarClaveExpediente)");
            ex.printStackTrace();
        }
        return claveExpediente;
    }

    public ObservableList<EstudiantePOJO> getEstudiantes() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select estudiante.nombre, estudiante.apellidopaterno, estudiante.apellidomaterno, estudiante.matricula from estudiante where not exists (select estudiante.matricula from expediente where expediente.matricula = estudiante.matricula);";

        ObservableList<EstudiantePOJO> obs = FXCollections.observableArrayList();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                String apellidoPaterno = rs.getString("APELLIDOPATERNO");
                String apellidoMaterno = rs.getString("APELLIDOMATERNO");
                String matricula = rs.getString("MATRICULA");

                //System.out.println("Titulo: " + titulo + "Ruta: " + rutaubicacion + "Fecha:" + fechaEntrega);
                EstudiantePOJO c = new EstudiantePOJO(nombre, apellidoPaterno, apellidoMaterno, matricula);

                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ArchivoDAO, método getEstudiantes()");
            e.printStackTrace();
        }

        return obs;
    }

    public ArrayList<SeleccionProyectoPOJO> getSelecciones(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select claveproyecto, fecha, periodo from seleccionproyecto where matricula = '" + matricula + "';";

        ArrayList<SeleccionProyectoPOJO> obs = new ArrayList<SeleccionProyectoPOJO>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int claveproyecto = rs.getInt("claveproyecto");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String periodo = rs.getString("periodo");

                //System.out.println("Titulo: " + titulo + "Ruta: " + rutaubicacion + "Fecha:" + fechaEntrega);
                SeleccionProyectoPOJO c = new SeleccionProyectoPOJO(claveproyecto, fecha, periodo);

                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase EstudianteDAO, método getSelecciones()");
            e.printStackTrace();
        }

        return obs;
    }

    public void asginarProyecto(String matriculaEstudianteElegido, int claveProyectoElegido) {
        Connection con = null;
        Statement stm = null;
        
        LocalDate fechaInicioPP = LocalDate.now();
        LocalDate fechaFinPP = fechaInicioPP.plusMonths(6);
        String sql = "INSERT INTO inscripcion VALUES ('" + matriculaEstudianteElegido + "', " + claveProyectoElegido + ", 234, 1, 35, 'Inscrito', '" + fechaInicioPP + "', 123, 'FEB2020-AGO2020', 1, 'Primera inscripcion');";
        String sql2 = "INSERT INTO expediente VALUES (null, '" + fechaFinPP + "', '" + fechaInicioPP + "', 0, 0, '" + matriculaEstudianteElegido + "', " + claveProyectoElegido + ");";
        
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
