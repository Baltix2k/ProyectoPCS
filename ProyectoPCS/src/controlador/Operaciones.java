package controlador;

import vista.Mensajes;

public class Operaciones {
    
    Mensajes ms;
    
    public Operaciones() {
        this.ms = new Mensajes();
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
