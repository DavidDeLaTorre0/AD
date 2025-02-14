//<conciertos>
//    <concierto>
//<grupo>SONATA ARCTICA</grupo>
//<lugar>Conciertos WiZink Center - Madrid</lugar>
//        <fecha>Viernes 04 octubre 2024</fecha>
//        <hora>20:00 H.</hora>
//    </concierto>
//    <concierto>
//        <grupo>Grupo 2</grupo>
//        <lugar>Lugar 2</lugar>
//        <fecha>2024-10-25</fecha>
//        <hora>21:00</hora>
//    </concierto>
//</conciertos>
//
//
//        Código para extraer los nombres de las etiquetas
//        A continuación, se muestra un ejemplo de código que lee el archivo XML
//        y extrae no solo los valores de los campos, sino también los nombres de las etiquetas:
//
//import java.io.File;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//
//public class ConciertosApp {
//    public static void main(String[] args) {
//        // Ruta del archivo XML
//        String xmlFilePath = "conciertos.xml"; // Asegúrate de que la ruta es correcta
//
//        try {
//            // Crear un DocumentBuilderFactory
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//
//            // Parsear el archivo XML
//            Document doc = dBuilder.parse(new File(xmlFilePath));
//            doc.getDocumentElement().normalize();
//
//            // Obtener todos los elementos concierto
//            NodeList conciertosList = doc.getElementsByTagName("concierto");
//
//            // Iterar sobre cada elemento concierto
//            for (int i = 0; i < conciertosList.getLength(); i++) {
//                Element concierto = (Element) conciertosList.item(i);
//
//                // Extraer y mostrar los nombres de las etiquetas y sus valores
//                NodeList campos = concierto.getChildNodes(); // Obtener todos los nodos hijos
//                for (int j = 0; j < campos.getLength(); j++) {
//                    if (campos.item(j) instanceof Element) { // Verificar que sea un nodo de tipo Element
//                        Element campo = (Element) campos.item(j);
//                        String nombreEtiqueta = campo.getNodeName(); // Nombre de la etiqueta
//                        String valor = campo.getTextContent(); // Valor de la etiqueta
//
//                        // Imprimir el nombre de la etiqueta y su valor
//                        System.out.println("Etiqueta: " + nombreEtiqueta + " - Valor: " + valor);
//                    }
//                }
//                System.out.println("----------------------------");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//Explicación del código
//Importaciones necesarias: Asegúrate de tener las importaciones correctas para manejar el XML y las excepciones.
//
//Crear un DocumentBuilderFactory y DocumentBuilder:
//
//Se utilizan para crear un objeto DocumentBuilder, que es responsable de analizar el archivo XML.
//Parsear el archivo XML:
//
//Se usa dBuilder.parse(new File(xmlFilePath)) para cargar el archivo XML en un objeto Document.
//Normalizar el documento:
//
//        doc.getDocumentElement().normalize() asegura que el documento se haya normalizado para trabajar correctamente con él.
//Obtener elementos <concierto>:
//
//        doc.getElementsByTagName("concierto") devuelve una lista de todos los elementos <concierto> en el XML.
//Iterar sobre los elementos <concierto>:
//
//Se utiliza un bucle for para iterar sobre todos los elementos <concierto>.
//Obtener los campos hijos:
//
//Para cada elemento <concierto>, se obtienen sus nodos hijos usando concierto.getChildNodes().
//Verificar si el nodo es un Element:
//
//Se verifica que cada nodo hijo sea una instancia de Element.
//Extraer el nombre de la etiqueta y su valor:
//
//        campo.getNodeName() devuelve el nombre de la etiqueta (por ejemplo, grupo, lugar, fecha, hora).
//        campo.getTextContent() devuelve el valor de la etiqueta.
//Imprimir el resultado:
//
//Se imprime el nombre de la etiqueta y su valor.