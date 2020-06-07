package modelo;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArchivoDAO {
    public ObservableList<ArchivoPOJO> getArchivos(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT archivo.idarchivo, archivo.titulo, archivo.fechaEntrega FROM inscripcion JOIN expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where not exists (select archivo.idarchivo from reporte where reporte.idarchivo = archivo.idarchivo) and inscripcion.matricula = '" + matricula + "';";

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
    
    public void abrirArchivo(int idArchivo){
        /*Conectar cn = new Conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;

        try {
            ps = cn.getConnection().prepareStatement("SELECT archivopdf FROM pdf WHERE codigopdf = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("new.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            cn.desconectar();*/
        Connection con = null;
        ResultSet rs = null;
        byte[] b = null;
        
        String sql = "SELECT archivo FROM Archivo WHERE idArchivo =?;";
        PreparedStatement ps = null;
        ConexionDB cc = new ConexionDB();
        try{
            con = cc.conectarMySQL();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idArchivo);
            rs = ps.executeQuery();
            while (rs.next()){
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);
            
            int tamInput = bos.available();
            byte[] datosArch = new byte[tamInput];
            bos.read(datosArch,0,tamInput);
            
            OutputStream out = new FileOutputStream("new.pdf");
            out.write(datosArch);
            
            //abrir Archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Error al abrir archivo");
            e.printStackTrace();
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(ArchivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
