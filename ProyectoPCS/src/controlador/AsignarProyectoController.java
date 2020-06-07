/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<?> TblProyecto;
    @FXML
    private TableColumn<?, ?> ColProyecto;
    @FXML
    private TableColumn<?, ?> ColOrganizacion;
    @FXML
    private TableColumn<?, ?> ColDescripcion;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
