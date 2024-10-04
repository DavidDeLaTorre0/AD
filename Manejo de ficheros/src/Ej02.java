import java.io.File;
import java.util.Scanner;

public class Ej02 {
    //Aniadir del ejercicio anterior un menu de opciones al programa anterior y que el usuario puede elegir que hacer
    //Crear un directorio llamado nuevoDirectorio dentro de C:\AD\Ejercicios, dentro de else creara un nuevo archivo
public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("1 - Crear Directorio y archivo");
        System.out.println("2 - ELiminar Fichero");
        System.out.println("3 - Eliminar Directorio");
        System.out.println("4 - Salir");

        var numero = scan.nextInt();
        accion(numero);
    }

    public static void accion(int numero) {

        switch (numero){

            case 1:
                System.out.println("Creando Directorio...");
                crearDirectorio();
                break;
            case 2:
                System.out.println("Eliminando Directorio...");
                eliminarDirectorio();
                break;
            case 3:
                System.out.println("Eliminando fichero...");
                eliminarFichero();
                break;
            case 4:
                System.out.println("Saliendo ....");
                break;

                default:System.out.println("Tienes que introducir un numero entre 1-4");

        }

    }

    public static void crearDirectorio(){
        try {
            File directorio = new File(System.getProperty("user.dir"), "nuevoDirectorio");
            directorio.mkdir();
            System.out.println("Directorio creado");

            String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "nuevoDirectorio";
            File fichero = new File(ruta, "miFichero2.txt");
            fichero.createNewFile();

            System.out.println("Fichero creado");
            System.out.println("El directorio y el archivo se han guardado");
            menu();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarDirectorio(){

        try{

            String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "nuevoDirectorio";
            File directorio = new File(ruta);
            directorio.deleteOnExit();
            System.out.println("Directorio eliminado");
            menu();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void eliminarFichero(){

        try{
            String ruta = System.getProperty("user.dir");
            File fichero = new File(ruta, "miFichero.txt");
            fichero.delete();
            System.out.println("Fichero eliminado");
            menu();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
