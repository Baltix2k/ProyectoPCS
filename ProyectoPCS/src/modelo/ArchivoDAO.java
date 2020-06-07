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
        String sql = "SELECT archivo.titulo, archivo.rutaUbicacion, archivo.fechaEntrega FROM inscripcion JOIN expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where not exists (select archivo.idarchivo from reporte where reporte.idarchivo = archivo.idarchivo) and inscripcion.matricula = '" + matricula + "';";
                   //"select archivo.titulo, archivo.rutaUbicacion, archivo.fechaEntrega from inscripcion join expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where inscripcion.matricula = '" + matricula + "';";

        ObservableList<ArchivoPOJO> obs = FXCollections.observableArrayList();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String titulo = rs.getString("TITULO");
                String rutaubicacion = rs.getString("RUTAUBICACION");
                LocalDate fechaEntrega = LocalDate.parse(rs.getString("FECHAENTREGA"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
                
                System.out.println("Titulo: " + titulo + "Ruta: " + rutaubicacion + "Fecha:" + fechaEntrega);
                //ArchivoPOJO c = new ArchivoPOJO(titulo, rutaubicacion, fechaEntrega);
                
                //obs.add(c);
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
    
    public void subirArchivo(ArchivoPOJO arch,String matricula, int claveExp) {
        /*Conectar conec = new Conectar();
        String sql = "INSERT INTO pdf (codigopdf, nombrepdf, archivopdf) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getCodigopdf());
            ps.setString(2, vo.getNombrepdf());
            ps.setBytes(3, vo.getArchivopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }*/
        
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        //String sql = "INSERT INTO Archivo VALUES (NULL,"+arch.getArchivo()+","+claveExp+",'"+arch.getTitulo()+"','"+arch.getFechaEntrega().toString()+"');";
        String sql = "INSERT INTO Archivo VALUES (NULL,?,?,?,?);";
        PreparedStatement ps = null;
        ConexionDB cc = new ConexionDB();
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
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
        String sql = "SELECT idArchivo FROM Archivo;";
        try{
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                clave = rs.getInt(1);
            }
            stm.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al obtener idArchivo, método obtenerClaveArchivo");
            e.printStackTrace();
        }
        return clave;
    }  
}
