package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProyectoDAO {
    
    public ObservableList<ProyectoPOJO> getProyectos() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select proyecto.claveproyecto, proyecto.nombre, proyecto.nombreOrganizacion, proyecto.descripcion from proyecto where not exists (select proyecto.claveproyecto from expediente where expediente.claveproyecto = proyecto.claveproyecto);";

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
                
                System.out.println("CLAVEPROYECTO: " + claveproyecto + "NOMBRE: " + nombre + "NOMBREORGANIZACION:" + nombreOrganizacion);
                ProyectoPOJO c = new ProyectoPOJO(claveproyecto, nombre, nombreOrganizacion, descripcion);
                
                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ArchivoDAO, método readAll()");
            e.printStackTrace();
        }

        return obs;
    }
    
    public String recuperarNombreProyecto(){
       Connection con = null;
       Statement stm = null;
       ResultSet rs = null;
       String nombreProyecto = null;
       String sql = "SELECT nombre FROM proyecto WHERE noEstudiantes > 0;";
       
       try{
           con = new ConexionDB().conectarMySQL();
           stm = con.createStatement();
           rs = stm.executeQuery(sql);
           while (rs.next()){
               nombreProyecto = rs.getString(1);           }
        }catch(SQLException ex){
           System.out.println("Error: Clase ProyectoDAO, método recuperarNombreProyecto()");
           ex.printStackTrace();
       }
       return nombreProyecto;
   }
   
    public String recuperarNombreOrganizacion(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String nombreOrganizacion = null;
        String sql = "SELECT proyecto.nombreorganizacion FROM organizacion JOIN proyecto ON proyecto.claveProyecto = proyecto.claveProyecto;";
        
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
            System.out.println("Error: Clase ProyectoDAO, método recuperarNombreOrganizacion()");
            ex.printStackTrace();
        }
        return nombreOrganizacion;
    }
   
}
