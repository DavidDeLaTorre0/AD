//
//<concierto>
//<grupo>SONATA ARCTICA</grupo>
//<lugar>Conciertos WiZink Center - Madrid</lugar>
//    <fecha>Viernes 04 octubre 2024</fecha>
//    <hora>20:00 H.</hora>
//</concierto>
//
//
//import java.io.File;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//
//public class ConciertoApp {
//    public static void main(String[] args) {
//        // Ruta del archivo XML
//        String xmlFilePath = "concierto.xml"; // Asegúrate de que la ruta es correcta
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
//            // Obtener el elemento concierto (suponiendo que hay solo uno)
//            Element concierto = (Element) doc.getElementsByTagName("concierto").item(0);
//
//            // Extraer y mostrar los nombres de las etiquetas y sus valores
//            NodeList campos = concierto.getChildNodes(); // Obtener todos los nodos hijos
//            for (int j = 0; j < campos.getLength(); j++) {
//                if (campos.item(j) instanceof Element) { // Verificar que sea un nodo de tipo Element
//                    Element campo = (Element) campos.item(j);
//                    String nombreEtiqueta = campo.getNodeName(); // Nombre de la etiqueta
//                    String valor = campo.getTextContent(); // Valor de la etiqueta
//
//                    // Imprimir el nombre de la etiqueta y su valor
//                    System.out.println("Etiqueta: " + nombreEtiqueta + " - Valor: " + valor);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//Explicación del código
//Importaciones necesarias: Asegúrate de importar las bibliotecas necesarias para manejar el XML.
//
//Crear un DocumentBuilderFactory y DocumentBuilder:
//
//Se utilizan para crear un objeto DocumentBuilder, que es responsable de analizar el archivo XML.
//Parsear el archivo XML:
//
//        dBuilder.parse(new File(xmlFilePath)) carga el archivo XML en un objeto Document.
//Normalizar el documento:
//
//        doc.getDocumentElement().normalize() asegura que el documento esté en un estado normal para trabajar.
//Obtener el elemento <concierto>:
//
//        doc.getElementsByTagName("concierto").item(0) devuelve el primer (y único) elemento <concierto> en el XML.
//Obtener los campos hijos:
//
//        concierto.getChildNodes() se utiliza para obtener todos los nodos hijos del elemento <concierto>.
//Verificar si el nodo es un Element:
//
//Se verifica que cada nodo hijo sea una instancia de Element.
//Extraer el nombre de la etiqueta y su valor:
//
//        campo.getNodeName() devuelve el nombre de la etiqueta.
//        campo.getTextContent() devuelve el valor de la etiqueta.
//Imprimir el resultado:
//
//Se imprime el nombre de la etiqueta y su valor.