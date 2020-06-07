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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.ArchivoDAO;
import modelo.ArchivoPOJO;
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import modelo.ProyectoDAO;
import modelo.ProyectoPOJO;

/**
 * FXML Controller class
 *
 * @author enano
 */
public class AsignarProyectoController implements Initializable {

    @FXML
    private Button BtnAceptar;
    @FXML
    private Button BtnCancelar;
    @FXML
    private TableView<ProyectoPOJO> TblProyecto;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColProyecto;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColOrganizacion;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColDescripcion;
    @FXML
    private TableView<?> TblAlumno;
    @FXML
    private TableColumn<?, ?> ColAlumno;
    @FXML
    private TableColumn<?, ?> ColMatricula;
    @FXML
    private TextField LbOpcion1;
    @FXML
    private TextField LbOpcion2;
    @FXML
    private TextField LbOpcion3;
    
    private ProyectoDAO pDAO;

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

            Stage myStage = (Stage) this.BtnCancelar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void initData() {
        this.pDAO = new ProyectoDAO();
        ColProyecto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColOrganizacion.setCellValueFactory(new PropertyValueFactory<>("responsableNombre"));
        ColDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        
        ObservableList<ProyectoPOJO> obsProyecto = pDAO.getProyectos();
        this.TblProyecto.setItems(obsProyecto);
    }
    
}
