package controlador;

import modelo.EstudianteDAO;
import modelo.EstudiantePOJO;
import modelo.OrganizacionDAO;
import vista.Mensajes;

public class Operaciones {
    
    Mensajes ms;
    EstudianteDAO estudianteDAO;
    //ProyectoDAO proyectoDAO;
    //OrganizacionDAO organizacionDAO;
    
    public Operaciones() {
        this.ms = new Mensajes();
        this.estudianteDAO = new EstudianteDAO();
        //this.proyectoDAO = new ProyectoDAO();
        //this.organizacionDAO = new OrganizacionDAO();
    }
    
     public void ejecutarAplicacion() {
        int opcion = 0;
        do {
            ms.menu();
            opcion = ms.opcionEnterio();
            switch (opcion) {
                case 1:
                    ms.consultarMensaje();
                    String matricula = ms.leerMatricula();
                    EstudiantePOJO estudiante = this.estudianteDAO.recuperar(matricula);
                    System.out.println("Nombre: " + estudiante.getNombre() + " " + estudiante.getApellidoPaterno() + " " + estudiante.getApellidoMaterno());
                    System.out.println("Matricula: " + estudiante.getMatricula());
                    String nombreOrganizacion = this.estudianteDAO.recuperarNombreOrganizacion(estudiante.getMatricula());
                    System.out.println("Organizacion: " + nombreOrganizacion);                    
                    String nombreProyecto = this.estudianteDAO.recuperarNombreProyecto(estudiante.getMatricula());
                    System.out.println("Proyecto: " + nombreProyecto);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
            }
        } while (opcion != 4);
    }
}
