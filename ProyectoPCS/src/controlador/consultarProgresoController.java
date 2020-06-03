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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ricar
 */
public class consultarProgresoController implements Initializable {

    private Button btnconsultar;
    @FXML
    private ScrollBar sb1;
    @FXML
    private ScrollBar sb2;
    @FXML
    private Label lbnomb;
    @FXML
    private Label lbmatr;
    @FXML
    private Label lborg;
    @FXML
    private Label lbproy;
    @FXML
    private Label lbdocs;
    @FXML
    private Label lbreps;
    @FXML
    private Label lbhorascub;
    @FXML
    private TextField txtfdhorasporcub;
    @FXML
    private Label lbhorasporcub;
    @FXML
    private TextField txtfdnomb;
    @FXML
    private TextField txtfdmatr;
    @FXML
    private TextField txtfdorg;
    @FXML
    private TextField txtfproyecto;
    @FXML
    private Button btnsalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*    public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuController.fxml"));

            Parent root = loader.load();
            
            MenuController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnsalir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     */
    @FXML
    private void salir(ActionEvent event) {
        
    }

    void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));

            Parent root = loader.load();

            MenuController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnsalir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
