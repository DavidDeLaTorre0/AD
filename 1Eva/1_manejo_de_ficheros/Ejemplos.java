import java.io.*;
import java.util.Properties;

public class Ejemplos {
    public static void main(String[] args) {

        /*
        En cualquier caso, para el acceso a ficheros independientemente del tipo de los mismos,
        conviene conocer el funcionamiento de las System Properties de las que dispone Java en su API.
        Éstas permiten acceder a propiedades de la configuración del sistema y, entre otras,
        podemos encontrarnos con algunas muy interesantes relacionadas con este tema:*/

        System.out.println("La carpeta de mi usuario es " + System.getProperty("user.home"));
        System.out.println("La carpeta en la que estamos es " + System.getProperty("user.dir"));
        System.out.println("El separador de mi SO es " + System.getProperty("file.separator"));


        /*==================================================================================================================================*/
        /*Escribir ficheros de texto plano*/

        FileWriter fichero = null;

        PrintWriter escritor = null;


        try {

            fichero = new FileWriter("archivo.txt");

            escritor = new PrintWriter(fichero) ;

            escritor.println("Esto es una linea del fichero");

        } catch (IOException ioe) {

            ioe.printStackTrace() ;

        } finally {

            if (fichero != null)

            try {

                fichero.close();

            } catch (IOException ioe) {

            }
        }

        /*==================================================================================================================================*/
        /*Leer ficheros de texto plano*/

        File fichero1 = null;

        FileReader lector = null;

        BufferedReader buffer = null;



        try {

            buffer = new BufferedReader(new FileReader(new File("archivo.txt")));

            String linea = null;
            while ((linea = buffer.readLine()) != null)

            System.out.println(linea);

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        } finally {

            if (buffer != null)

            try {

                buffer.close();

            } catch (IOException ioe) {  }
        }

        /*==================================================================================================================================*/
        /*Escribir ficheros de configuracion

        Properties configuracion = new Properties();

        configuracion.setProperty("user", miUsuario);

        configuracion.setProperty("password", miContrasena);

        configuracion.setProperty("server", elServidor);

        configuracion.setProperty("port", elPuerto);

        try {

            configuracion.store(new FileOutputStream("configuracion.props"),

                    "Fichero de configuracion");

        } catch (FileNotFoundException fnfe ) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }

        /*==================================================================================================================================*/
        /*Leer ficheros de configuracion*/
/*
        Properties configuracion1 = new Properties();

        try {

            configuracion1.load(new FileInputStream("configuracion.props"));

            usuario = configuracion1.getProperty("user");

            password = configuracion1.getProperty("password");

            servidor = configuracion1.getProperty("server");

            puerto = Integer.valueOf(configuration.getProperty("port"));

        } catch (FileNotFoundException fnfe ) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }
*/
        /*==================================================================================================================================*/
        /* CREACION OBJETO FILE


        File archivo = new File("archivo.txt");
        File archivo1 = new File("ruta","nombre.txt");
        File archivo2 = new File(File ruta,"archivo.txt");*/



    }
}
