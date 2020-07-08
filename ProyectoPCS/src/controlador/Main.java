package controlador;

/******************************************************************************/
/* Asignación de programa:  1 - Proyecto final de PCS                         */
/* Nombre(s):               Alarcón Santos Emilio Antonio                     */
/*                          Baltazar Islas Omar                               */
/*                          Ruíz Alarcón Ricardo                              */
/* Fecha de inicio:         01/06/2020                                        */
/* Descripción:             El siguiente programa tiene como objetivo         */
/*                          demostrar la funcionalidad de los casos de uso    */
/*                          de un sistema para el manejo de practicas         */
/*                          profesionales llevadas a su implementación para   */
/*                          asi demostrar los conocimientos aprendidos en la  */
/*                          materia Principios de Construcción de Software    */
/*                          llevando a cabo todas las buenas practas          */
/*                          aprendidas en el curso como parte de un proyecto  */
/*                          final.                                            */
/******************************************************************************/

/** ***************************************************************************/
/* Lista de contenidos:                                                       */
/*    Instrucciones de reutilización                                          */
/*       * El programa utiliza una BD con la que se conecta y realiza todas   */
/*       las acciones. De no contarse con esta BD el programa no funcionará.  */
/*       * El formato de declaración de variables es con el nombre de lo que  */
/*       la variable representa en notación camello iniciando por minuscula.  */
/*       En cuanto a los métodos son verbos (en ingles de pertenecer a alguna */
/*       clase importada o en español de ser creados) y realizan solo la      */
/*       acción indicada.                                                     */
/*       * El desbordamiento o mala funcionalidad del sistema se deberá a la  */
/*       perdida o nula conexión a la BD, o a algún driver mal implementado,  */
/*       debido a que el sistema cuenta con los medios necesarios para        */
/*       funcionar en condiciones ideales.                                    */
/*    Instrucciones de modificación                                           */
/*       La modificación de este programa por cualquier persona que sea ajeno */
/*       a los autores mencionados anteriormente infringirá ante los derechos */
/*       de autor ya que ninguna modificación a este programa es permitida    */
/*       debido a que es una entrega final para motivos escolares.            */
/*    Includes                                                                */
/*         Debido a que el programa solo muestra la implementación de 3 casos */
/*         los cuales no tienen una relación cercana como se muestra en las   */
/*         descripciones de CU, no se implementa ningún Include asi como      */
/*         ningun Extend, sin embargo se respetan los botones que puedan hacer*/
/*         referencia a algun CU no implementado.                             */
/*    Declaracíón de clases:                                                  */
/*         La declaración de las clases se encuentra en sus respectivos       */
/*         archivos ya que cada archivo solo declará una clase en particular, */
/*         que es la que hace referencia el mismo archivo. Para observar las  */
/*         clases que se manejan observar el modelo, debido a que el          */
/*         controlador cuenta con las clases que manejan a las del modelo     */
/*         y emplean la vista para poder interactuar con el usuario final.    */
/*   Código fuente en C:\ProyectoPCS\src\controlador:                         */
/*         AsignarProyectoController                                          */
/*         SubirReporteController                                             */
/*         consultarProgresoController                                        */
/******************************************************************************/

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Clase Main con la que inicia la aplicación.
 * Se ejecuta tras iniciar el programa e inicia la primera vista del Menu.
 * 
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Método que por el mismo nombre (Start), inicia la aplicación.
     * 
     * @param primaryStage Stage que donde busca cargar la primera vista del 
     * Menu a través de un FXMLLoader hacia la ventana, y esta misma hacia el
     * scene, el cual es el que finalmente se carga en el primaryStage y se
     * muestra en pantalla con un show. 
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/MenuVista.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Método que por el mismo nombre (Main), inicia el programa.
     * 
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
