package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que realiza la conexión con la base de datos.
 *
 * @version 1.0
 */
public class ConexionDB {

    public String driver = "com.mysql.jdbc.Driver";
    public String database = "practicas_profesionales";
    public String hostname = "localhost";
    public String port = "3306";
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database
            + "?useSSL=false";
    public String username = "root";
    public String password = "Jinchuriki2k";

    /**
     * Genera conexion a la base de datos de acuerdo a los datos que se
     * encuentran en los atributos.
     *
     * @return void
     */
    public Connection conectarMySQL() throws Exception {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Se establece la conexion a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Error en ConexionDB la causa es: " + e.
                    getCause().toString());
        }
        return conn;
    }
}
