/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Iterator;
import java.util.List;
import modelo.ProductoPojo;
import modelo.RegistroPojo;

/**
 *
 * @author ricar
 */
public class Mensajes {

    Teclado teclado;

    public Mensajes() {
        this.teclado = new Teclado();
    }

    public int opcionEnterio() {
        int opcion = 0;
        System.out.println("Introduce un dato del tipo entero");
        opcion = teclado.leerEntero();
        return opcion;
    }

    public void menu() {
        System.out.println("Elige una opcion:");
        System.out.println("1) Agregar");
        System.out.println("2) Mostrar");
        System.out.println("3) Modificar");
        System.out.println("4) Eliminar");
        System.out.println("5) Menu ventas");
        System.out.println("6) Añadir cantidad");
        System.out.println("7) Quitar cantidad");
        System.out.println("8) Salir");
    }
    
    public void menuVentas() {
        System.out.println("Elige una opcion:");
        System.out.println("1) Realizar venta");
        System.out.println("2) Consultar ventas");
        System.out.println("3) Salir");
    }
    
    public int realizarVenta() {
        int indice;
        System.out.println("Seleccione la clave del producto a comprar: ");
        indice = teclado.leerEntero();
        return indice;
    }
    
    public int realizarCompra() {
        int indice;
        System.out.println("Seleccione la cantidad a comprar de el producto elegido: ");
        indice = teclado.leerEntero();
        return indice;
    }
    
    public int terminarVenta() {
        int indice;
        System.out.println("1 Para seguir comprando / 2 Para terminar venta. ");
        indice = teclado.leerEntero();
        return indice;
    }

    public void agregarMensaje() {
        System.out.println("Ingresando un producto: ");
    }
    
    public void modificarMensaje() {
        System.out.println("Ingrese la clave del producto a modificar: ");
    }
    
    public void eliminarMensaje() {
        System.out.println("Ingrese la clave del producto a eliminar: ");
    }
    
    public void añadirCantidad() {
        System.out.println("Ingrese la clave del producto a añadir cantidad: ");
    }
    
    public void quitarCantidad() {
        System.out.println("Ingrese la clave del producto a quitar cantidad: ");
    }
    
    public void sinCantidad() {
        System.out.println("No hay cantidad suficiente en el inventario.");
    }
    
    public int leerModificar() {
        int indice;
        System.out.println("Introduzca lo que modificara de ese producto: ");
        System.out.println("1. Clave");
        System.out.println("2. Nombre");
        System.out.println("3. Descripcion");
        System.out.println("4. Unidad");
        System.out.println("5. Cantidad");
        System.out.println("6. Precio");
        indice = teclado.leerEntero();
        return indice;
    }

    public String leerNombre() {
        String nombre;
        System.out.println("Introduce un nombre");
        nombre = teclado.leerCadena();
        return nombre;
    }
    
    public String leerDescripcion() {
        String nombre;
        System.out.println("Introduce una descripcion");
        nombre = teclado.leerCadena();
        return nombre;
    }
    
    public String leerUnidad() {
        String nombre;
        System.out.println("Introduce un tipo de unidad");
        nombre = teclado.leerCadena();
        return nombre;
    }

    public int leerClave() {
        int clave;
        System.out.println("Introduce una clave");
        clave = teclado.leerEntero();
        return clave;
    }
    
    public int leerCantidad() {
        int clave;
        System.out.println("Introduce una cantidad");
        clave = teclado.leerEntero();
        return clave;
    }
    
    public double leerPrecio() {
        double clave;
        System.out.println("Introduce el precio unitario");
        clave = teclado.leerDouble();
        return clave;
    }

    public void mostrarProductos(List<ProductoPojo> lista) {
        System.out.println("\t Los productos son: ");
        Iterator<ProductoPojo> iteradorE = lista.iterator();
        while (iteradorE.hasNext()) {
            System.out.println("\t \t " + iteradorE.next());
        }
    }
    
    public void mostrarVentas(List<RegistroPojo> lista) {
        System.out.println("\t Los registros de venta son: ");
        Iterator<RegistroPojo> iteradorE = lista.iterator();
        while (iteradorE.hasNext()) {
            System.out.println("\t \t " + iteradorE.next());
        }
    }

    public void resutladoOperacion(boolean resultado, String operacion) {
        if (resultado) {
            System.out.println(" \n Resultado Exitoso al " + operacion + "\n");
        } else {
            System.out.println(" \n Resultado Fallido al " + operacion + "\n");
        }
    }
}
