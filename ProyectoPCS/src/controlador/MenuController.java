package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase controlador de la vista del Menu, la cual es la primera pantalla de la
 * aplicación. Se ejecuta tras iniciar el programa.
 *
 * @version 1.0
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;

    /**
     * Inicializa el controlador de la clase.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Acción realizada al dar clic en el tercer botón: Consultar progreso. El
     * parent carga la nueva vista del preConsultarProgreso, el cual se va a
     * cargar en el scene que esta asociado al stage.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void consultarProgreso(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/vista/preConsultarProgreso.fxml"));
            Parent root = loader.load();
            preConsultarProgresoController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage = (Stage) this.btn3.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acción realizada al dar clic en el segundo botón: Subir reporte. El
     * parent carga la nueva vista de subirReporte, el cual se va a cargar en el
     * scene que esta asociado al stage.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void subirReporte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/preSubirReporte.fxml"));
            Parent root = loader.load();
            preSubirReporteController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage = (Stage) this.btn2.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acción realizada al dar clic en el primer botón: Asignar proyecto a 
     * estudiante. El parent carga la nueva vista de subirReporte, el cual se va
     * a cargar en el scene que esta asociado al stage.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void asignarProyecto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/AsignarProyecto.fxml"));
            Parent root = loader.load();
            AsignarProyectoController controlador = loader.getController();
            controlador.initData();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage = (Stage) this.btn1.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
