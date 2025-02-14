import entity.Concierto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class actividad1 {
    public static Scanner scan = new Scanner(System.in);
    /*
       Crear un programa JAVA que realice las siguientes tareas:
    */

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        System.out.println("1 - Leer csv y generar txt con los datos en minuscula");
        System.out.println("2 - Leer csv y generar aleatorio ");
        System.out.println("3 - Leer csv y generar una clase y fichero bin");
        System.out.println("4 - Leer bin y generar con DOM un fichero XML");

        int numero = scan.nextInt();

        switch (numero) {
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
//              USANDO PRINT WRITER
//    String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" + System.getProperty("file.separator")+ "ACTIVIDADES";
//
//
//    try {
//        File fichero = new File(ruta, "CONCIERTOS.csv");
//        FileReader fr = new FileReader(fichero);
//        BufferedReader br = new BufferedReader(fr);
//        String linea = null;
//
//        File fichero1 = new File(ruta, "CONCIERTOS.txt");
//        FileWriter fw = new FileWriter(fichero1);
//        PrintWriter pw = new PrintWriter(fw);
//
//        while ((linea = br.readLine()) != null) {
//            pw.println(linea.toLowerCase());
//        }
//        menu();
//    }catch (Exception e){
//        System.out.println(e.getMessage());
//    }
    //  USANDO BUFFERED WRITER
        String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" + System.getProperty("file.separator") + "ACTIVIDADES";

        File fichero = new File(ruta, "sources/CONCIERTOS.csv");
        File fichero1 = new File(ruta, "sources/CONCIERTOS.txt");

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(fichero));
            bw = new BufferedWriter(new FileWriter(fichero1));

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea.toLowerCase());
                bw.newLine(); // Agrega un salto de línea
                //bw.flush();
            }

        } catch (IOException e) {

            System.out.println("Error al procesar los archivos: " + e.getMessage());

        }finally{
            if(bw!=null){
                try{
                    bw.close();
                    menu();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }


    /*
        2. Leer el fichero “CONCIERTOS.csv” y generar un fichero “CONCIERTOS.aleatorio”
        de acceso aleatorio donde los campos de cada columna midan lo mismo
        (50,50,30,10).*/

    public static void start02() {


//    String[] extensiones = {"txt","csv","json","xml","log","dat","bin","md","html","ini"};
//
//    String ext = obtenerExtension(extensiones);
//
//    File fichero = new File(ruta, "CONCIERTOS.csv");
//    //PONGO CONIERTOS1(NOMBRE UNICO) PARA QUE PUEDA CREAR TODO TIPO DE FICCHERO
//    File fichero1 = new File(ruta, "CONCIERTOS1."+ext);
//
//    try (
//            BufferedReader br = new BufferedReader(new FileReader(fichero));
//            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichero1)))
//    ) {
//        String linea;
//        while ((linea = br.readLine()) != null) {
//            pw.println(linea.toLowerCase());
//        }
//        menu();
//    } catch (IOException e) {
//        System.out.println("Error al procesar los archivos: " + e.getMessage());
//    }
        String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" + System.getProperty("file.separator") + "ACTIVIDADES";

        File ficheroCSV = new File(ruta, "sources/CONCIERTOS.csv"); // Ruta del archivo CSV de entrada
        File ficheroAleatorio = new File(ruta, "sources/CONCIERTOS.aleatorio"); // Ruta del archivo de salida

        BufferedReader br= null;
        BufferedWriter bw= null;

        try {
            br = new BufferedReader(new FileReader(ficheroCSV));
            bw = new BufferedWriter(new FileWriter(ficheroAleatorio));

            String linea;
            while ((linea = br.readLine()) != null) {

                // Dividir la línea en columnas por comas
                String[] columnas = linea.split(";");


                // Procesar las columnas y formatearlas
                String columna1 = formatearColumna(columnas[0], 50);
                String columna2 = formatearColumna(columnas[1], 50);
                String columna3 = formatearColumna(columnas[2], 30);
                String columna4 = formatearColumna(columnas[3], 10);

                // Escribir la fila formateada en el archivo de salida
                bw.write(columna1 + columna2 + columna3 + columna4);
                bw.newLine();  // maneja salto de línea

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bw!=null){
                try{
                    bw.close();
                    menu();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

/*
    3.
    Leer el fichero “CONCIERTOS.csv”, generar una clase Conciertos y con ella
    generar un fichero “CONCIERTOS.bin” serializable.
*/

    public static void start03() {

        List<Concierto> conciertos = new ArrayList<>();

        //Leer el archivo CSV

        // Files new Bufered reader recoge la ruta del archivo y la guarad en un obj file
        File rutaCSV = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" +System.getProperty("file.separator")+ "ACTIVIDADES", "sources/CONCIERTOS.csv");
        File rutaBin = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" +System.getProperty("file.separator")+ "ACTIVIDADES", "sources/CONCIERTOS.bin");


        // try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoCSV))) {
        BufferedReader br = null ;

        try{

            br = new BufferedReader(new FileReader(rutaCSV));
            String linea;

            // Leer el encabezado
            br.readLine();  // La primera línea es el encabezado

            // Leer cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String grupo = datos[0];
                String lugar = datos[1];
                String fecha= datos[2];
                String hora = datos[3];

                // Creamos una instancia de Concierto y entra a la lista
                Concierto concierto = new Concierto(grupo,lugar,fecha,hora);
                conciertos.add(concierto);
            }
            System.out.println(conciertos);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        //Serializar los objetos y guardarlos en un archivo binario
        ObjectOutputStream oos =null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(rutaBin));
            oos.writeObject(conciertos);
            System.out.println("Conciertos serializados correctamente en CONCIERTOS.bin");
            menu();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo binario: " + e.getMessage());
        }





    }

/*
    4. Leer el fichero “CONCIERTOS.bin” serializable y con DOM generar el fichero
    “CONCIERTOS.XML”
*/

    public static void start04() {

        File rutaBin = new File(System.getProperty("user.dir") + System.getProperty("file.separator") , "sources/CONCIERTOS.bin");
        File rutaXml = new File(System.getProperty("user.dir") + System.getProperty("file.separator") , "sources/CONCIERTOS.xml");


        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaBin))) {

            ArrayList<Concierto> conciertos = (ArrayList<Concierto>) entrada.readObject();

            // Crear documento XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Elemento raíz
            Element rootElement = doc.createElement("conciertos");
            doc.appendChild(rootElement);

            // Añadir cada concierto
            for (Concierto concierto : conciertos) {
                Element conciertoElement = doc.createElement("concierto");

                Element grupo = doc.createElement("grupo");
                grupo.appendChild(doc.createTextNode(concierto.getGrupo()));
                conciertoElement.appendChild(grupo);

                Element lugar = doc.createElement("lugar");
                lugar.appendChild(doc.createTextNode(concierto.getLugar()));
                conciertoElement.appendChild(lugar);

                Element fecha = doc.createElement("fecha");
                fecha.appendChild(doc.createTextNode(concierto.getFecha()));
                conciertoElement.appendChild(fecha);

                Element hora = doc.createElement("hora");
                hora.appendChild(doc.createTextNode(concierto.getHora()));
                conciertoElement.appendChild(hora);

                rootElement.appendChild(conciertoElement);
            }

            // Escribir el contenido en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(rutaXml);
            transformer.transform(source, result);

            System.out.println("Archivo CONCIERTOS.xml generado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //public static String obtenerExtension(String[] extensiones) {
    //
    //    Random random = new Random();
    //    int indice = random.nextInt(extensiones.length);
    //    return extensiones[indice];
    //}

    // Método para formatear cada columna a un tamaño fijo
    private static String formatearColumna(String dato, int tamaño) {
        if (dato.length() > tamaño) {
            // Truncar si el dato es más largo que el tamaño permitido
            return dato.substring(0, tamaño);
        } else {


            /*
                %-10s: Alinea la cadena a la izquierda con un ancho de 10 caracteres.
                %5d: Número entero con un ancho de 5 caracteres, alineado a la derecha por defecto.
                %10.2f: Número de punto flotante con un ancho de 10 caracteres y 2 decimales.
            */

            // Rellenar con espacios si es más corto
            return String.format("%-" + tamaño + "s", dato);
        }
    }
}