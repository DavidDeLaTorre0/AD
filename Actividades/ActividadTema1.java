import java.util.Scanner;

public class ActividadTema1 {
public static Scanner scan = new Scanner(System.in);

    /*


    * Crear un programa JAVA que realice las siguientes tareas:

    */

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        System.out.println("1 - Leer csv y generar txt con los datos en minuscula");
        System.out.println("2 - Leer csv y generar aleatorio con os dar");
        System.out.println("3 - Leer csv y generar una clase y fichero bin");
        System.out.println("4 - Leer bin y generar con DOM un fichero XML");

        int numero = scan.nextInt();

        switch (numero){
            case 1:
                start01();
                break;
            case 2:
                start02();
                break;
            case 3:
                start03();
                break;
            case 4:
                start04();
                break;
        }

    }

    /*
        1. Leer el fichero “CONCIERTOS.csv” y generar un “CONCIERTOS.txt” con todos los
        datos en minúscula.
    */
    public static void start01() {

    }


    /*
        2. Leer el fichero “CONCIERTOS.csv” y generar un fichero “CONCIERTOS.aleatorio”
        de acceso aleatorio donde los campos de cada columna midan lo mismo
        (50,50,30,10).*/

    public static void start02() {



    }

    /*
        3.
        Leer el fichero “CONCIERTOS.csv”, generar una clase Conciertos y con ella
        generar un fichero “CONCIERTOS.bin” serializable.
    */
        public static void start03() {


        }
    /*
        4. Leer el fichero “CONCIERTOS.bin” serializable y con DOM generar el fichero
        “CONCIERTOS.XML”
    */
    public static void start04() {



    }



}
