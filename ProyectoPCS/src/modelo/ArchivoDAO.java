package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArchivoDAO {
    public ObservableList<ArchivoPOJO> getArchivos(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select inscripcion.matricula,expediente.clave,archivo.estado,archivo.rutaubicacion,archivo.titulo from inscripcion join expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp where inscripcion.matricula = '" + matricula + "';";

        ObservableList<ArchivoPOJO> obs = (ObservableList<ArchivoPOJO>) new ArrayList<ArchivoPOJO>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                
                String estado = rs.getString("ESTADO");
                String rutaubicacion = rs.getString("RUTAUBICACION");
                String titulo = rs.getString("TITULO");
                
                ArchivoPOJO c = new ArchivoPOJO(estado, rutaubicacion, titulo);
                
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
