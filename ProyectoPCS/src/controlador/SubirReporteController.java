package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ArchivoDAO;
import modelo.ArchivoPOJO;
import modelo.ReporteDAO;
import modelo.ReportePOJO;

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
    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtClaveExp;
    
    private File file;
    
    ObservableList<String> tiposReporte = FXCollections.observableArrayList("Inicial","Mensual","Final");
    DateFormat df = DateFormat.getDateInstance();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combxTipo.setItems(tiposReporte);
        
        LocalDate fecha = LocalDate.now();
        txtFecha.setText(fecha.toString());
    }    

    @FXML
    private void subirArchivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(null);
        if(file != null){
            labArchivo.setText("Archivo: " + file.getName());
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
    private void aceptar(ActionEvent event) throws FileNotFoundException {
        ArchivoDAO arch = new ArchivoDAO();
        ArchivoPOJO archP = new ArchivoPOJO();
        ReporteDAO rep = new ReporteDAO();
        ReportePOJO repP = new ReportePOJO();
        archP.setTitulo(file.getName());
        String fecha = txtFecha.getText();
        archP.setFechaEntrega(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        repP.setHorasReportadas(Integer.parseInt(txtHoras.getText()));
        repP.setTipoReporte(combxTipo.getValue());
        try{
            byte[] doc = new byte[(int) file.length()];
            InputStream input = new FileInputStream(file);
            input.read(doc);
            archP.setArchivo(doc);
        }catch(IOException ex){
            archP.setArchivo(null);
        }
        arch.subirArchivo(archP, Integer.parseInt(txtClaveExp.getText()));
        int idArch = arch.obtenerClaveArchivo();
        System.out.println(idArch); //-------------------
        rep.subirReporte(repP,idArch);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Exito");
        alert.setContentText("Archivo cargado exitosamente");
        alert.showAndWait();
        this.closeWindows();
        
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
    
    public void initData(String matricula, int claveExp){
        txtMatricula.setText(matricula);
        txtClaveExp.setText(Integer.toString(claveExp));
    }
    
}
