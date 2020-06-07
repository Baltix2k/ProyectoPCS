/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.ArchivoDAO;
import modelo.ArchivoPOJO;
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import modelo.ReporteDAO;
import modelo.ReportePOJO;

/**
 * FXML Controller class
 *
 * @author ricar
 */
public class consultarProgresoController implements Initializable {

    private Button btnconsultar;
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
    @FXML
    private TextField txtfdhorascub;

    private EstudianteDAO eDAO;
    private ArchivoDAO aDAO;
    private ReporteDAO rDAO;

    @FXML
    private TableView<ArchivoPOJO> tableArchivo;
    @FXML
    private TableColumn<ArchivoPOJO, Integer> archivoId;
    @FXML
    private TableColumn<ArchivoPOJO, String> archivoTitulo;
    @FXML
    private TableColumn<ArchivoPOJO, LocalDate> archivoFecha;
    @FXML
    private TableView<ReportePOJO> tableReporte;
    @FXML
    private TableColumn<ReportePOJO, Integer> reporteId;
    @FXML
    private TableColumn<ReportePOJO, String> reporteTitulo;
    @FXML
    private TableColumn<ReportePOJO, LocalDate> reporteFecha;
    @FXML
    private TableColumn<ReportePOJO, Integer> reporteHoras;
    @FXML
    private TableColumn<ReportePOJO, String> reporteTipo;

    ObservableList<ArchivoPOJO> archivos;
    ObservableList<ReportePOJO> reportes;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<ArchivoPOJO> tablaArchivo1 = tableArchivo.getSelectionModel().getSelectedItems();
        //tablaArchivo1.addListener(selector);
    }

    @FXML
    private void salir(ActionEvent event) {
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

    void initData(EstudiantePOJO ePOJO) {
        this.eDAO = new EstudianteDAO();
        this.txtfdnomb.setText(ePOJO.getNombre() + " " + ePOJO.getApellidoPaterno() + " " + ePOJO.getApellidoMaterno());
        this.txtfdmatr.setText(ePOJO.getMatricula());
        this.txtfdorg.setText(this.eDAO.recuperarNombreOrganizacion(ePOJO.getMatricula()));
        this.txtfproyecto.setText(this.eDAO.recuperarNombreProyecto(ePOJO.getMatricula()));

        this.aDAO = new ArchivoDAO();
        archivoId.setCellValueFactory(new PropertyValueFactory<>("idArchivo"));
        archivoTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        archivoFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        
        ObservableList<ArchivoPOJO> obsArchivo = aDAO.getArchivos(ePOJO.getMatricula());
        this.tableArchivo.setItems(obsArchivo);
        
        this.rDAO = new ReporteDAO();
        reporteId.setCellValueFactory(new PropertyValueFactory<>("idArchivo"));
        reporteTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        reporteFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        reporteHoras.setCellValueFactory(new PropertyValueFactory<>("horasReportadas"));
        reporteTipo.setCellValueFactory(new PropertyValueFactory<>("tipoReporte"));
        
        ObservableList<ReportePOJO> obsReporte = rDAO.getReportes(ePOJO.getMatricula());
        this.tableReporte.setItems(obsReporte);
    }
}
