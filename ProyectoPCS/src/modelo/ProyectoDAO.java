package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProyectoDAO {
    
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
