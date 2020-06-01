package vista;

import java.util.Scanner;

public class Teclado {
    
    /**
     * Método que regresa la entrada de usuario de un número entero
     * @return int
     **/
    public int leerEntero() {
        int entero = 0;
        Scanner sc = new Scanner(System.in);
        try {
            entero = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error al leer el dato");
        }
        return entero;
    }
    
    /**
     * Método que regresa la entrada de usuario de una cadena
     * @return String
     */
    public String leerCadena() {
        String cadena = "";
        Scanner sc = new Scanner(System.in);
        try {
            cadena = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error al leer el dato");
        }
        return cadena;
    }
    
    public double leerDouble() {
        double doble = 0.0;
        Scanner sc = new Scanner(System.in);
        try {
            doble = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Error al leer el dato");
        }
        return doble;
    }
}
