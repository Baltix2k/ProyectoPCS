package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import modelo.ReporteDAO;
import modelo.ReportePOJO;
import vista.AlertaFXML;

/**
 * Clase controlador de la vista del SubirReporte, pantalla que tiene como
 * objetivo mostrar subir un archivo al expediente del ESTUDIANTE que fue
 * recuperado de la pantalla anterior (preSubirReporte) por medio de una
 * matricula.
 *
 * @version 1.0
 */
public class SubirReporteController implements Initializable {

    @FXML
    private ComboBox<String> combxTipo;
    @FXML
    private TextField txtHoras;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label labArchivo;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtClaveExp;

    private EstudianteDAO eDAO;
    private File file;

    ObservableList<String> tiposReporte = FXCollections.
            observableArrayList("Inicial", "Mensual", "Final");
    DateFormat df = DateFormat.getDateInstance();

    /**
     * Inicializa el controlador de la clase.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combxTipo.setItems(tiposReporte);
        LocalDate fecha = LocalDate.now();
        txtFecha.setText(fecha.toString());
    }

    /**
     * Despliega el explorador de archivos para seleccionar el reporte a subir
     * por medio de un FileChooser.
     *
     * @param event El clic del botón.
     */
    @FXML
    private void subirArchivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(null);
        if (file != null) {
            labArchivo.setText("Archivo: " + file.getName());
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
            MenuController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(SubirReporteController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acción realizada al dar clic en el botón: Aceptar, el cual recupera las
     * cadenas escritas en los textfield requeridos asi como recupera el archivo
     * seleccionado en el filechooser y realiza un registro en la base de datos
     * acorde al ESTUDIANTE seleccionado en la pantalla anterior.
     *
     * @param event El clic del botón.
     * @throws FileNotFoundException Sin selección de archivo.
     */
    @FXML
    private void aceptar(ActionEvent event) throws FileNotFoundException,
            Exception {
        ArchivoDAO arch = new ArchivoDAO();
        ArchivoPOJO archP = new ArchivoPOJO();
        ReporteDAO rep = new ReporteDAO();
        ReportePOJO repP = new ReportePOJO();
        if (this.validarCampos()) {
            archP.setTitulo(file.getName());
            String fecha = txtFecha.getText();
            archP.setFechaEntrega(LocalDate.parse(fecha,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            String horas = txtHoras.getText();

            if (horas.matches("[0-9]*")) {
                repP.setHorasReportadas(Integer.parseInt(txtHoras.getText()));
                repP.setTipoReporte(combxTipo.getValue());

                if (this.validarArchivo()){
                    if(this.getExtensionArchivo().equals(".pdf") || this.getExtensionArchivo().equals(".docx")) {
                        try {
                            byte[] doc = new byte[(int) file.length()];
                            InputStream input = new FileInputStream(file);
                            input.read(doc);
                            archP.setArchivo(doc);
                        } catch (IOException ex) {
                            archP.setArchivo(null);
                        }
                        arch.subirArchivo(archP, Integer.parseInt(txtClaveExp.
                                getText()));
                        int idArch = arch.obtenerClaveArchivo();

                        rep.subirReporte(repP, idArch);
                        AlertaFXML alerta = new AlertaFXML((Stage) this.btnCancelar.
                                getScene().getWindow());
                        alerta.alertaInformacion("Exito", "Archivo subido "
                                + "exitosamente", "El archivo se ha cargado "
                                + "correctamente al sistema");
                        this.closeWindows();
                    }else{
                        AlertaFXML alerta = new AlertaFXML((Stage) this.btnCancelar.
                    getScene().getWindow());
                    alerta.alertaInformacion("Error", "Archivo invalido", "El sistema solo acepta PDF y DOCX");
                    }
                }

            } else {
                AlertaFXML alerta = new AlertaFXML((Stage) this.btnCancelar.
                        getScene().getWindow());
                alerta.alertaInformacion("Error", "Horas a registrar "
                        + "incorrectas", "Las horas son aracteres numericos");
            }
        }
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(SubirReporteController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los textfield y establece que la pantalla responde al
     * ESTUDIANTE recibido de la pantalla anterior por medio de clases DAO y el
     * POJO.
     *
     * @param ePOJO
     */
    public void initData(EstudiantePOJO ePOJO) throws Exception {
        this.eDAO = new EstudianteDAO();
        txtMatricula.setText(ePOJO.getMatricula());
        txtClaveExp.setText(Integer.toString(this.eDAO.
                recuperaClaveExpediente(ePOJO.getMatricula())));
    }

    /**
     * Valida que los campos dentro del boton aceptar no esten incompletos.
     *
     * @return boolean.
     */
    public boolean validarCampos() {
        String errorMessage = "";
        if (this.txtHoras.getText() == null
                || this.txtHoras.getText().length() == 0) {
            errorMessage = "Campo de HORAS vacio \n";
        } else {
            if (this.combxTipo.getSelectionModel().isEmpty()) {
                errorMessage = "Tipo de reporte no seleccionado \n";
            } else {
                if (file == null) {
                    errorMessage = "Archivo no seleccionado \n";
                }
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertaFXML alerta = new AlertaFXML((Stage) this.btnCancelar.
                    getScene().getWindow());
            alerta.alertaInformacion("Error", "Campos incompletos",
                    errorMessage);
            return false;
        }
    }

    /**
     * Evalua la variable file para determinar el tipo de archivo que se intetna
     * subir.
     *
     * @return String.
     */
    private String getExtensionArchivo() {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * Valida que el archivo a subir no sobre pase el tamaño y que sea de los
     * tipos aceptados.
     *
     * @return boolean.
     */
    private boolean validarArchivo() {
        String errorMessage = "";
        System.out.println(this.getExtensionArchivo());
        if (file.length() > Math.pow(2, 32)) {
            errorMessage = "El tamaño del archivo excede el limite soportado";
        /*} else {
            /*if (this.getExtensionArchivo() != ".pdf"
                    || this.getExtensionArchivo() != ".doc"
                    || this.getExtensionArchivo() != ".docx") {
                errorMessage = "El tipo de archivo no es valido, "
                        + "el sistema solo acepta PDF y DOCX";
            }
            if (this.getExtensionArchivo() != ".pdf" || this.getExtensionArchivo() != ".docx") {
                errorMessage = "El tipo de archivo no es valido, "
                        + "el sistema solo acepta PDF y DOCX";
            }/*else{
                if (this.getExtensionArchivo() != ".docx"){
                    errorMessage = "El tipo de archivo no es valido, "
                        + "el sistema solo acepta PDF y DOCX";
                }
            }*/
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertaFXML alerta = new AlertaFXML((Stage) this.btnCancelar.
                    getScene().getWindow());
            alerta.alertaInformacion("Error", "Archivo invalido", errorMessage);
            return false;
        }
    }
}
