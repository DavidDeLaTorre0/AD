import entity.Concierto1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializarData {


    public static void main(String[] args) {

        //Serializar los datos y guardarlos en un archivo binario
        Serializar();
        //Deserializar los datos de un binario y recuperar los objeros CONCIERTO
        Deserializar();
    }


    public static void Serializar() {
        List<Concierto1> conciertos = new ArrayList<>();

        // Paso 2: Leer el archivo CSV

//        String ruta = System.getProperty("user.dir") + System.getProperty("file.separator") + "1Eva" + System.getProperty("file.separator") + "ACTIVIDADES";
//        File archivoCSV = new File(ruta,"CONCIERTOS.csv");

        //HE DEJADO MEJOR ESTOS METOODS YA QUE ES MAS MODERNO Y MAS LEGIBLE
        // Files new Bufered reader recoge la ruta del archivo y la guarad en un obj file
        String archivoCSV = "sources/CONCIERTOS.csv";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoCSV))) {

            //try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV)){
            String linea;
            // Leer el encabezado
            br.readLine();  // Suponemos que la primera línea es el encabezado

            // Leer cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String fecha = datos[1];
                String lugar = datos[2];
                double precio = Double.parseDouble(datos[3]);

                // Crear una instancia de Concierto y agregarla a la lista
                Concierto1 concierto = new Concierto1(nombre, fecha, lugar, precio);
                conciertos.add(concierto);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        // Paso 3: Serializar los objetos y guardarlos en un archivo binario
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sources/CONCIERTOS.bin"))) {
            oos.writeObject(conciertos);
            System.out.println("Conciertos serializados correctamente en CONCIERTOS.bin");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo binario: " + e.getMessage());
        }
    }

    public static void Deserializar() {

        String archivoBinario = "sources/CONCIERTOS.bin";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoBinario))) {

            // Leer el objeto serializado (en este caso, una lista de Concierto)
            List<Concierto1> conciertos = (List<Concierto1>) ois.readObject();


            // Mostrar los conciertos deserializados
            System.out.println("Conciertos deserializados desde " + archivoBinario + ":");
            for (Concierto1 concierto : conciertos) {
                System.out.println(concierto);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo binario: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + e.getMessage());
        }


    }
}