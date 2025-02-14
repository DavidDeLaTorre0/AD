import java.io.File;

public class Ej1 {
    public static void main(String[] args) {

        //CREA UN PORG JAVA QUE CREE UN FICHERO DE TEXTO EN LA RUTA C:\AD\EJERCICIOS

        crearfichero();
    }
    public static void crearfichero() {
        // En esta ocasion quiero crearlo en ubicacion de este proyecto poir eso uso .dir C:\DAM\Acceso a Datos

       String ruta =  System.getProperty("user.dir");
       char separator = File.separatorChar;

       try {
           //De lo contrario deberia introducir la ruta
           File fichero = new File(ruta + separator + "miFichero.txt");
           fichero.createNewFile();
           System.out.println("Fichero fichero creado");

       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
}
