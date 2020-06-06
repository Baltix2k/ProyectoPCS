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
                ArchivoPOJO c = new ArchivoPOJO(titulo, rutaubicacion, fechaEntrega);
                
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
}
