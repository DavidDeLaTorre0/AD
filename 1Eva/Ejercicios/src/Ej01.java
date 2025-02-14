import java.io.File;

public class Ej01 {
    //Crea un programa en Java
    // que cree un fichero llamado miFichero.txt que se cree en la ruta C:\AD\miFichero.txt

    public static void main(String[] args) {

        crearFichero();
    }


    public static void crearFichero() {

        try {
            //LA RUTA ES LA DE ESTE PROYECTO, YA QUE ES UN EJERCICIO
            File file = new File(System.getProperty("user.dir") , "miFichero.txt");
            file.createNewFile();
            System.out.println("Fichero creado con exito en :" + System.getProperty("user.dir"));
        } catch (Exception e) {
            System.out.println("No se puede crear el fichero " + e.getMessage());
        }

    }
}