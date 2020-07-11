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
 * Clase que encargada de realizar todas las operaciones de los objetos REPORTE
 * manejados dentro del sistema con los registrados en la BD.
 *
 * @version 1.0
 */
public class ReporteDAO {

    /**
     * Recupera de la base de datos una lista de REPORTES por medio de la
     * matricula identificadora del ESTUDIANTE que requiere los REPORTES.
     *
     * @param matricula Identificador del ESTUDIANTE de los que se obtendran los
     * REPORTES.
     * @return obs Lista contenedora de los REPORTES del ESTUDIANTE.
     */
    public ObservableList<ReportePOJO> getReportes(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select reporte.idArchivo, archivo.titulo, "
                + "archivo.fechaEntrega, reporte.horasReportadas, "
                + "reporte.tipoReporte from inscripcion join expediente on "
                + "inscripcion.matricula = expediente.matricula join archivo "
                + "on expediente.clave = archivo.claveexp join reporte on "
                + "archivo.idarchivo = reporte.idarchivo where "
                + "inscripcion.matricula = '" + matricula + "';";
        ObservableList<ReportePOJO> obs = FXCollections.observableArrayList();
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
                int horasReportadas = rs.getInt("HORASREPORTADAS");
                String tipoReporte = rs.getString("TIPOREPORTE");
                System.out.println("ID: " + idArchivo);
                ReportePOJO c = new ReportePOJO(idArchivo, titulo, fechaEntrega,
                        horasReportadas, tipoReporte);
                obs.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ArchivoDAO, método getReportes");
            e.printStackTrace();
        }
        return obs;
    }

    /**
     * Registra el REPORTE en la base de datos basado en el ARCHIVO generado 
     * del cual recibe su id.
     *
     * @param report Objeto REPORTE a registrar.
     * @param idArch Id del ARCHIVO a donde pertenece el REPORTE.
     */
    public void subirReporte(ReportePOJO report, int idArch) {
        Connection con = null;
        System.out.println(idArch + "-" + report.getHorasReportadas() + "-" 
                + report.getTipoReporte());
        String sql = "INSERT INTO Reporte VALUES(?,?,?);";
        PreparedStatement ps = null;
        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idArch);
            ps.setInt(2, report.getHorasReportadas());
            ps.setString(3, report.getTipoReporte());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar reporte, metodo subirReporte");
            e.printStackTrace();
        }
    }

    /**
     * Recupera el numero de horas registradas mediante los REPORTES de un
     * ESTUDIANTE especificados.
     * 
     * @param matricula Matricula del ESTUDIANTE especificado.
     * @return horas Numero de horas registradas.
     */
    public int recuperarHoras(String matricula) {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select reporte.horasReportadas from inscripcion join "
                + "expediente on inscripcion.matricula = expediente.matricula "
                + "join archivo on expediente.clave = archivo.claveexp join "
                + "reporte on archivo.idarchivo = reporte.idarchivo where "
                + "inscripcion.matricula = '" + matricula + "';";
        int horas = 0;
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                horas = horas + rs.getInt(1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ArchivoDAO, método readAll()");
            e.printStackTrace();
        }
        return horas;
    }
}
