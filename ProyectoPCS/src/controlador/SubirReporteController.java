package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author obalt
 */
public class SubirReporteController implements Initializable {
    @FXML
    private ComboBox<String> combxTipo;
    @FXML
    private TextField txtHoras;
    @FXML
    private Button btnExaminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Label labArchivo;
    @FXML
    private TextField txtFecha;
    
    ObservableList<String> tiposReporte = FXCollections.observableArrayList("Inicial","Mensual","Final");
    DateFormat df = DateFormat.getDateInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combxTipo.setItems(tiposReporte);
        Date FechaActual = new Date();
        txtFecha.setText(df.format(FechaActual));
    }    

    @FXML
    private void subirArchivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if(f != null){
            labArchivo.setText("Archivo: " + f.getName());
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            MenuController controlador = loader.getController();

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Cierro la ventana donde estoy
            Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(SubirReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aceptar(ActionEvent event) {
        
        
    }

    public void closeWindows() {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador
            MenuController controlador = loader.getController();

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Cierro la ventana donde estoy
            Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(SubirReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
