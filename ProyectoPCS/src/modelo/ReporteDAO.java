package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ricar
 */
public class ReporteDAO {
    public ObservableList<ReportePOJO> getReportes(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select archivo.titulo, archivo.rutaUbicacion, archivo.fechaEntrega, reporte.horasReportadas, reporte.tipoReporte from inscripcion join expediente on inscripcion.matricula = expediente.matricula join archivo on expediente.clave = archivo.claveexp join reporte on archivo.idarchivo = reporte.idarchivo where inscripcion.matricula = '" + matricula + "';";

        ObservableList<ReportePOJO> obs = FXCollections.observableArrayList();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String titulo = rs.getString("TITULO");
                String rutaubicacion = rs.getString("RUTAUBICACION");
                LocalDate fechaEntrega = LocalDate.parse(rs.getString("FECHAENTREGA"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int horasReportadas = rs.getInt("HORASREPORTADAS");
                String tipoReporte = rs.getString("TIPOREPORTE");
                
                ReportePOJO c = new ReportePOJO(titulo, rutaubicacion, fechaEntrega, horasReportadas, tipoReporte);
                
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