package controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import modelo.ReporteDAO;
import modelo.ReportePOJO;

/**
 * Clase controlador de la vista del consultarProgreso, pantalla que tiene como
 * objetivo mostrar la consulta del progreso del ESTUDIANTE que fue recuperado
 * de la pantalla anterior (preConsultarProgreso) por medio de una matricula.
 *
 * @version 1.0
 */
public class consultarProgresoController implements Initializable {

    @FXML
    private TextField txtfdhorasporcub;
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

    private EstudianteDAO eDAO;
    private ArchivoDAO aDAO;
    private ReporteDAO rDAO;
    
    ObservableList<ArchivoPOJO> archivos;
    ObservableList<ReportePOJO> reportes;

    /**
     * Inicializa el controlador de la clase.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<ArchivoPOJO> tablaArchivo1 = tableArchivo.
                getSelectionModel().getSelectedItems();
    }

    /**
     * Regresa al menu principal.
     *
     * @param event
     */
    @FXML
    private void salir(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnsalir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Regresa al menu principal incluso cuando se cierre la ventana.
     */
    void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnsalir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los textfield y las tablas mostrados en pantalla acorde al
     * ESTUDIANTE recibido de la pantalla anterior por medio de clases DAO y el
     * POJO.
     *
     * @param ePOJO
     */
    void initData(EstudiantePOJO ePOJO) throws Exception {
        this.eDAO = new EstudianteDAO();
        this.rDAO = new ReporteDAO();
        this.txtfdnomb.setText(ePOJO.getNombre() + " "
                + ePOJO.getApellidoPaterno() + " "
                + ePOJO.getApellidoMaterno());
        this.txtfdmatr.setText(ePOJO.getMatricula());
        this.txtfdorg.setText(this.eDAO.
                recuperarNombreOrganizacion(ePOJO.getMatricula()));
        this.txtfproyecto.setText(this.eDAO.
                recuperarNombreProyecto(ePOJO.getMatricula()));
        int horasCub = this.rDAO.recuperarHoras(ePOJO.getMatricula());
        int horasPorCub = 200 - horasCub;
        this.txtfdhorascub.setText(Integer.toString(horasCub));
        this.txtfdhorasporcub.setText(Integer.toString(horasPorCub));

        this.aDAO = new ArchivoDAO();
        archivoId.setCellValueFactory(new PropertyValueFactory<>("idArchivo"));
        archivoTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        archivoFecha.
                setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));

        ObservableList<ArchivoPOJO> obsArchivo = aDAO.
                getArchivos(ePOJO.getMatricula());
        this.tableArchivo.setItems(obsArchivo);

        this.rDAO = new ReporteDAO();
        reporteId.setCellValueFactory(new PropertyValueFactory<>("idArchivo"));
        reporteTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        reporteFecha.
                setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        reporteHoras.setCellValueFactory(
                new PropertyValueFactory<>("horasReportadas"));
        reporteTipo.
                setCellValueFactory(new PropertyValueFactory<>("tipoReporte"));

        ObservableList<ReportePOJO> obsReporte = rDAO.
                getReportes(ePOJO.getMatricula());
        this.tableReporte.setItems(obsReporte);
    }

}
