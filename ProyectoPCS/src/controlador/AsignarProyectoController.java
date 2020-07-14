package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import modelo.ProyectoDAO;
import modelo.ProyectoPOJO;
import modelo.SeleccionProyectoPOJO;
import vista.AlertaFXML;

/**
 * Clase controlador de la vista de AsignarProyecto, pantalla que tiene como
 * objetivo realizar la asignación de un ESTUDIANTE registrado en la BD con un
 * PROYECTO que no tenga un ESTUDIANTE asociado.
 *
 * @version 1.0
 */
public class AsignarProyectoController implements Initializable {

    @FXML
    private Button BtnCancelar;
    @FXML
    private TableView<ProyectoPOJO> TblProyecto;
    @FXML
    private TableColumn<ProyectoPOJO, Integer> ColClave;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColProyecto;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColOrganizacion;
    @FXML
    private TableColumn<ProyectoPOJO, String> ColDescripcion;
    @FXML
    private TableView<EstudiantePOJO> TblAlumno;
    @FXML
    private TableColumn<EstudiantePOJO, String> ColAlumno;
    @FXML
    private TableColumn<EstudiantePOJO, String> ColMatricula;
    @FXML
    private TableColumn<EstudiantePOJO, String> estudianteApPat;
    @FXML
    private TableColumn<EstudiantePOJO, String> estudianteApMat;
    @FXML
    private TextField LbOpcion1;
    @FXML
    private TextField LbOpcion2;
    @FXML
    private TextField LbOpcion3;

    private ProyectoDAO pDAO;
    private EstudianteDAO eDAO;

    ObservableList<ProyectoPOJO> proyectos;
    ObservableList<EstudiantePOJO> estudiantes;
    ArrayList<SeleccionProyectoPOJO> selecciones;

    int claveProyectoElegido;
    String matriculaEstudianteElegido;

    /**
     * Inicializa el controlador de la clase.
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
            Stage myStage = (Stage) this.BtnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los textfield, las tablas y sus listeners mostrados en
     * pantalla medio de clases DAO y el POJO.
     */
    void initData() throws Exception {
        this.pDAO = new ProyectoDAO();
        ColClave.setCellValueFactory(new PropertyValueFactory<>(
                "claveProyecto"));
        ColProyecto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColOrganizacion.setCellValueFactory(new PropertyValueFactory<>(
                "responsableNombre"));
        ColDescripcion.setCellValueFactory(new PropertyValueFactory<>(
                "descripcion"));
        this.proyectos = pDAO.getProyectos();
        this.TblProyecto.setItems(proyectos);

        this.eDAO = new EstudianteDAO();
        ColAlumno.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        estudianteApPat.setCellValueFactory(new PropertyValueFactory<>(
                "apellidoPaterno"));
        estudianteApMat.setCellValueFactory(new PropertyValueFactory<>(
                "apellidoMaterno"));
        ColMatricula.setCellValueFactory(new PropertyValueFactory<>(
                "matricula"));
        this.estudiantes = eDAO.getEstudiantes();
        this.TblAlumno.setItems(estudiantes);

        TblProyecto.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelectionProyecto) -> {
            if (newSelectionProyecto != null) {
                System.out.println(newSelectionProyecto.getNombre());
                claveProyectoElegido = newSelectionProyecto.getClaveProyecto();
                System.out.println("CLAVE: " + claveProyectoElegido);
            }
        });

        TblAlumno.getSelectionModel().selectedItemProperty().
                addListener((obs, oldSelection, newSelectionEstudiante) -> {
            if (newSelectionEstudiante != null) {
                System.out.println(newSelectionEstudiante.getNombre());
                try {
                    selecciones = eDAO. 
                            getSelecciones(newSelectionEstudiante.getMatricula());
                } catch (Exception ex) {
                    Logger.getLogger(AsignarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                int s1 = selecciones.get(0).getClaveProyecto();
                int s2 = selecciones.get(1).getClaveProyecto();;
                int s3 = selecciones.get(2).getClaveProyecto();;
                try {
                    this.LbOpcion1.setText(pDAO.recuperarNombre(s1));
                    this.LbOpcion2.setText(pDAO.recuperarNombre(s2));
                    this.LbOpcion3.setText(pDAO.recuperarNombre(s3));
                    matriculaEstudianteElegido = newSelectionEstudiante.
                            getMatricula();
                    System.out.println("MATRICULA: " + matriculaEstudianteElegido);
                }catch (Exception ex) {
                    Logger.getLogger(AsignarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    /**
     * Acción realizada al dar clic en el botón: Cancelar, el cual regresa al
     * menu principal ignorando la cantidad de ventanas por las cuales habia
     * pasado.
     *
     * @param event El clic del botón.
     */
    @FXML
    void cancelar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.BtnCancelar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recupera las selecciones de los listeners (ESTUDIANTE y PROYECTO) y 
     * realiza la asociacion entre ellas generando asi una INSCRIPCIÓN.
     * 
     * @param event
     */
    @FXML
    private void aceptar(ActionEvent event) throws Exception {
        if(matriculaEstudianteElegido != null && claveProyectoElegido != 0){
            eDAO.asginarProyecto(matriculaEstudianteElegido, claveProyectoElegido);
            System.out.println("Asignación realizada");
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("Asignación realizada");
            //alert.showAndWait();
            ButtonType generarOficio = new ButtonType(
                    "Generar oficio de asignación");
            ButtonType finalizar = new ButtonType("Finalizar");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(generarOficio, finalizar);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == finalizar) {
                this.closeWindows();
            } else if (option.get() == generarOficio) {
                System.out.println("Caso de uso no implementado");
                this.closeWindows();
            }
        }else{
            AlertaFXML alerta = new AlertaFXML((Stage)this.BtnCancelar.getScene().getWindow());
                            alerta.alertaInformacion("Error", "Opciones incompletas", 
                                    "Faltan selecciones para realisar la asignacion");
        }
    }
}
