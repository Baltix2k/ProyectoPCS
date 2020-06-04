package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                e = new EstudiantePOJO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getFloat(7),rs.getString(8));
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
            while (rs.next()){ 
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
}
