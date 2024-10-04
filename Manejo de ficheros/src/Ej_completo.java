import java.io.*;
import java.util.Scanner;

public class Ej_completo {
    public static Scanner scan = new Scanner(System.in);
        /*
        *. Escribir un programa en Java que para cualquier ruta indicada por el usuario,muestre:
            - Si el fichero existe o no
            - Si se trata de un directorio o de un fichero
            - En caso de ser un fichero, debe mostrar los siguientes datos:
            o Nombre
            o Tamaño
            o Permisos de lectura y escritura

            2. Partiendo del fichero de csv de ejemplo, crear un programa de Java que muestre los
            datos de todos aquellos restaurantes cuyo código postal empiece por 6.

            3. Partiendo del fichero de csv de ejemplo, crear un programa de Java que permita al
            usuario añadir datos nuevos a ese fichero, utilizando el mismo formato que los ya
            existentes.

            4. Partiendo del fichero de csv de ejemplo, crear un programa de Java que cree una
            copia de ese fichero llamada “Restaurants2.csv” que contenga los mismos datos
            excepto aquellos correspondientes a los restaurantes cuyo código postal empieza por 6.

            5. Crear un programa en Java que borre el fichero cuya ruta ha sido introducida por el
            usuario
        *
        */



    public static void main(String[] args) {

        menu();

    }
    public static void menu() {

        System.out.println("Que ejercicio quieres hacer:");
        System.out.println("1 - Ruta indicada por usuario");
        System.out.println("2 - Muestre los datos de los restaurantes con CP 6..");
        System.out.println("3 - Escribir en un csv");
        System.out.println("4 - Copiar un csv");
        System.out.println("5 - Borrar fichero de ruta indicada por usuario");

        switch (scan.nextInt()) {
            case 1:
                start01();
                break;
            case 2:
                start02();
                break;
            case 3:
                start03();
                break;
        }
    }
    public static void start01() {
        System.out.println("Introduzca una ruta y le dire si es fichero o directorio o si por el contrario no hay nada");
        System.out.println(System.getProperty("user.dir"));

        String ruta = scan.nextLine();

        File documento = new File(ruta);


        if(documento.exists()){
            System.out.println("El directorio existe");

            menu();
        }else {
            System.out.println("El directorio no existe");
            menu();
        }

        if(documento.isFile()){
            System.out.println("Es un fichero");

            System.out.println("El nombre del archivo es: " + documento.getName());
            System.out.println("El tamanio del archivo es: " + documento.length());
            System.out.println("Los permisos del archivo son: " +" leer: "+ documento.canRead() +" escribir: "+ documento.canWrite());
            menu();
        }else {
            System.out.println("No es un fichero");
            menu();
        }
    }

    // 2. Partiendo del fichero de csv de ejemplo, crear un programa de Java que muestre los
    //            datos de todos aquellos restaurantes cuyo código postal empiece por 6.

    public static void start02() {

        String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets";

        File documento = new File(ruta,"restaurantes.csv");
        FileReader lector= null;
        BufferedReader buffer = null;

        try{

            buffer = new BufferedReader(new FileReader(documento));
            String linea = null;

            while ((linea = buffer.readLine()) != null) {

                String[] arrayLista = linea.split(",");

                if(arrayLista[1].startsWith("6")){
                    System.out.println(linea);
                }

            }


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }finally {
            if(buffer!=null){
                try{
                    buffer.close();
                    menu();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }

    /*
        3. Partiendo del fichero de csv de ejemplo, crear un programa de Java que permita al
        usuario añadir datos nuevos a ese fichero, utilizando el mismo formato que los ya
        existentes.
    */

    public static void start03() {

        System.out.println("Introduzca codigo postal");
        int cp = scan.nextInt();

        System.out.println("Introduzca restaurante");
        String restaurante = scan.next();



        String conjunto = String.join(",",restaurante,String.valueOf(cp));


        String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets";
        File documento = new File(ruta,"restaurantes.csv");


        FileWriter fichero= null;
        PrintWriter escritor = null;

        try{
            // El segundo parámetro "true" habilita el modo append
            // LO QUE HACE APPEND ES NO SOBRE ESCRIBIR EL ARCHIVO, SI NO QUE LO CONTINUA(CONCATENA)
             fichero = new FileWriter(documento,true);
             escritor = new PrintWriter(fichero);

             escritor.println(conjunto);
        }catch (IOException e){

            e.printStackTrace();

        }finally{
            if(fichero!=null){
                try{
                    fichero.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }




    /*
        4. Partiendo del fichero de csv de ejemplo, crear un programa de Java que cree una
        copia de ese fichero llamada “Restaurants2.csv” que contenga los mismos datos
        excepto aquellos correspondientes a los restaurantes cuyo código postal empieza por 6.
    */
    /*
        5. Crear un programa en Java que borre el fichero cuya ruta ha sido introducida por el
        usuario
    */
}