package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //Libreria MySQL
    public String driver = "com.mysql.jdbc.Driver";
    //Nombre base de datos
    public String database = "practicas_profesionales";
    //Host
    public String hostname = "localhost";
    //Puerto
    public String port = "3306";
    //Ruta de nuestra base de datos (Desactivamos el uso de SSL con "?usessl=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    //Nombre de usuario
    public String username = "root";
    //Clave de usuario
    public String password = "Jinchuriki2k";

    /**
     * Genera conexion a la base de datos de acuerdo a los datos que se encuentran en los atributos.
     * @return void
     */
    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Se establece la conexion a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
            e.printStackTrace();
        }
        return conn;
    }
    

}
