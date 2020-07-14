package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que encargada de realizar todas las operaciones de los objetos ARCHIVO 
 * manejados dentro del sistema con los registrados en la BD.
 * 
 * @version 1.0
 */
public class ArchivoDAO {

    /**
     * Recupera de la base de datos una lista de ARCHIVOS por medio de la
     * matricula identificadora del ESTUDIANTE que requiere los ARCHIVOS.
     *
     * @param matricula Identificador del ESTUDIANTE de los que se obtendran los
     * ARCHIVOS.
     * @return obs Lista contenedora de los ARCHIVOS del
     * ESTUDIANTE.
     */
    public ObservableList<ArchivoPOJO> getArchivos(String matricula) throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT archivo.idarchivo, archivo.titulo, "
                + "archivo.fechaEntrega FROM inscripcion JOIN expediente on "
                + "inscripcion.matricula = expediente.matricula join archivo "
                + "on expediente.clave = archivo.claveexp where not exists "
                + "(select archivo.idarchivo from reporte where "
                + "reporte.idarchivo = archivo.idarchivo) and "
                + "inscripcion.matricula = '" + matricula + "';";
        ObservableList<ArchivoPOJO> obs = FXCollections.observableArrayList();
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int idArchivo = rs.getInt("IDARCHIVO");
                String titulo = rs.getString("TITULO");
                LocalDate fechaEntrega = LocalDate.
                        parse(rs.getString("FECHAENTREGA"), DateTimeFormatter.
                                ofPattern("yyyy-MM-dd"));
                System.out.println("ID Archivo: " + idArchivo + "Titulo: "
                        + titulo + "Fecha:" + fechaEntrega);
                ArchivoPOJO c = new ArchivoPOJO(idArchivo, titulo,
                        fechaEntrega);
                obs.add(c);
            }
        } catch (SQLException e) {           
            throw new Exception("Error en create Clase ArchivoDAO, método readAll: " + e.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        
        return obs;
    }

    /**
     * Registra en la base de datos un ARCHIVO dentro del expediente 
     * especificado por medio de una clave identificadora.
     * 
     * @param arch Archivo a cargar.
     * @param claveExp Clave del expediente donde el archivo se va a cargar.
     */
    public void subirArchivo(ArchivoPOJO arch, int claveExp) throws Exception{
        Connection con = null;
        String sql = "INSERT INTO Archivo VALUES (NULL,?,?,?,?);";
        PreparedStatement ps = null;
        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            //stm = con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setBytes(1, arch.getArchivo());
            ps.setInt(2, claveExp);
            ps.setString(3, arch.getTitulo());
            ps.setString(4, arch.getFechaEntrega().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en Clase ArchivoDAO, metodo subirArchivo: " + e.getMessage());
        }finally{
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
    }

    /**
     * Recupera la clase del ultimo archivo registrado en la BD.
     * 
     * @return clave Regresa la clase del archivo.
     */
    public int obtenerClaveArchivo() throws Exception{
        int clave = 0;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(idArchivo) AS id FROM Archivo;";
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            clave = rs.getInt(1);
        } catch (Exception e) {            
            throw new Exception("Error al obtener idArchivo, método "
                    + "obtenerClaveArchivo: " + e.getMessage());
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stm != null) stm.close(); } catch (Exception e) {};
            try { if (con!= null) con.close(); } catch (Exception e) {};
        }
        
        return clave;
    }

}
