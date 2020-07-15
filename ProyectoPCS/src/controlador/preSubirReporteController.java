package controlador;

import com.mysql.cj.exceptions.CJCommunicationsException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import vista.AlertaFXML;

/**
 * Clase controlador de la vista del preSubirReporte, pantalla que tiene como
 * objetivo recuperar una matricula para poder cargar la entrega en la vista
 * SubirReporte.
 *
 * @version 1.0
 */
public class preSubirReporteController implements Initializable {

    @FXML
    private TextField txfdmatricula;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnAceptar;

    private EstudianteDAO eDAO;

    /**
     * Inicializa el controllador de la clase.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Regresa al menu principal incluso cuando se cierre la ventana.
     */
    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btncancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acción realizada al dar clic en el botón: Aceptar, el cual recupera la
     * cadena escrita en el textfield txtfdmatricula y carga la siguiente
     * pantalla,la cual inicializa con el ESTUDIANTE recuperado por medio de la
     * matricula recuperada y le permitirá subir un archivo a su inscripción. El
     * parent carga la nueva vista de consultarProgreso, el cual se va a cargar
     * en el scene que esta asociado al stage.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void aceptar(ActionEvent event){
        this.eDAO = new EstudianteDAO();
        String matricula = this.txfdmatricula.getText();
        if (this.matriculaValida()) {
            if (matricula.matches("[0-9]*")) {
                AlertaFXML alerta = new AlertaFXML((Stage) this.btnAceptar.
                        getScene().getWindow());
                alerta.alertaInformacion("Error", "Matricula invalida",
                        "La matricula debe comenzar con S");
            } else {
                try {
                    EstudiantePOJO ePOJO = eDAO.recuperar(matricula);
                    if (eDAO.recuperaClaveExpediente(matricula) == 0 && eDAO.
                            recuperarNombreEstudiante(matricula) != null) {
                        AlertaFXML alerta = new AlertaFXML(
                                (Stage) this.btnAceptar.getScene().getWindow());
                        alerta.alertaInformacion("Error", "Estudiante sin exped"
                                + "iente", "El estudiante no cuenta con un "
                                + "expediente, se le debe asignar un proyecto");
                    } else {
                        if (eDAO.recuperarNombreEstudiante(matricula) == null
                                && eDAO.recuperaClaveExpediente(
                                        matricula) == 0) {
                            AlertaFXML alerta = new AlertaFXML(
                                    (Stage) this.btnAceptar.getScene().
                                            getWindow());
                            alerta.alertaInformacion("Error", "Estudiante no "
                                    + "encontrado", "El estudiante no se "
                                    + "encuentra en la base de datos");
                        } else {
                            FXMLLoader loader = new FXMLLoader(getClass().
                                    getResource(
                                            "/vista/SubirReporteVista.fxml"));
                            Parent root = loader.load();
                            SubirReporteController controlador = loader.
                                    getController();
                            controlador.initData(ePOJO);
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                            stage.setOnCloseRequest(e -> controlador.
                                    closeWindows());
                            Stage myStage = (Stage) this.btnAceptar.getScene().
                                    getWindow();
                            myStage.close();
                        }
                    }
                } catch (Exception ex) {
                    AlertaFXML alerta = new AlertaFXML(
                            (Stage) this.btnAceptar.getScene().
                                    getWindow());
                    alerta.alertaInformacion("Error", "Error de conexion" 
                            ,"Ocurrio un error con la conexion a la base de datos");
                }
            }
        }
    }

    /**
     * Acción realizada al dar clic en el botón: Cancelar, el cual regresa al
     * menu principal ignorando la cantidad de ventanas por las cuales habia
     * pasado.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btncancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite validar si la matricula ingresda no contiene espacios o el campo
     * esta vacio.
     *
     * @return boolean.
     *
     */
    private boolean matriculaValida() {
        String errorMessage = "";
        if (this.txfdmatricula.getText() == null
                || this.txfdmatricula.getText().length() == 0) {
            errorMessage = "Campo vacio \n";
        }
        if (this.txfdmatricula.getText().contains(" ")) {
            errorMessage = "La matricula contiene espacios \n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertaFXML alerta = new AlertaFXML((Stage) this.btnAceptar.
                    getScene().getWindow());
            alerta.alertaInformacion("Error", "Campo invalido", errorMessage);
            return false;
        }
    }

}
