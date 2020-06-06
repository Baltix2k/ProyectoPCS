/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;

public class preConsultarProgresoController implements Initializable {

    @FXML
    private TextField txfdmatricula;
    @FXML
    private Label labelmatricula;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnconsultar;
    
    private EstudianteDAO eDAO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));

            Parent root = loader.load();
            
            MenuController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btncancelar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));

            Parent root = loader.load();
            
            MenuController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btncancelar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consultar(ActionEvent event) {
        this.eDAO = new EstudianteDAO();
        
        try {
            String matricula = this.txfdmatricula.getText();
            EstudiantePOJO ePOJO = eDAO.recuperar(matricula);          
            
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/consultarProgreso.fxml"));
            
            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            consultarProgresoController controlador = loader.getController();
            
            //controlador.initData(ePOJO);
                       
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Indico que debe hacer al cerrar
            stage.setOnCloseRequest(e -> controlador.closeWindows());

            // Ciero la ventana donde estoy
            Stage myStage = (Stage) this.btnconsultar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(preConsultarProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Matricula no encontrada");
            alert.showAndWait();
        }
    }
}
