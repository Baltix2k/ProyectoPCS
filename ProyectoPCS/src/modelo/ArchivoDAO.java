package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArchivoDAO {
    public ObservableList<ArchivoPOJO> getArchivos(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT archivo.idarchivo, archivo.titulo, archivo.fechaEntrega FROM inscripcion JOIN expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where not exists (select archivo.idarchivo from reporte where reporte.idarchivo = archivo.idarchivo) and inscripcion.matricula = '" + matricula + "';";
                   //"select archivo.titulo, archivo.rutaUbicacion, archivo.fechaEntrega from inscripcion join expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where inscripcion.matricula = '" + matricula + "';";

        ObservableList<ArchivoPOJO> obs = FXCollections.observableArrayList();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int idArchivo = rs.getInt("IDARCHIVO");
                String titulo = rs.getString("TITULO");
                LocalDate fechaEntrega = LocalDate.parse(rs.getString("FECHAENTREGA"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
                
                System.out.println("ID Archivo: "+ idArchivo +"Titulo: " + titulo + "Fecha:" + fechaEntrega);
                ArchivoPOJO c = new ArchivoPOJO(idArchivo, titulo, fechaEntrega);
                
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
    
    public void subirArchivo(ArchivoPOJO arch,int claveExp) {
        Connection con = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO Archivo VALUES (NULL,?,?,?,?);";
        PreparedStatement ps = null;
        ConexionDB cc = new ConexionDB();
        try{
            con = cc.conectarMySQL();
            //stm = con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setBytes(1,arch.getArchivo());
            ps.setInt(2, claveExp);
            ps.setString(3, arch.getTitulo());
            ps.setString(4, arch.getFechaEntrega().toString());
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Error al cargar archivo, metodo subirArchivo");
            e.printStackTrace();
        } 
    }
    
    public int obtenerClaveArchivo(){
        int clave = 0;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idArchivo) AS id FROM Archivo;";
        try{
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            clave = rs.getInt(1);
            stm.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al obtener idArchivo, método obtenerClaveArchivo");
            e.printStackTrace();
        }
        return clave;
    }  
}
