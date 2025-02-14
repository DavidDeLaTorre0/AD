import dao.Conexion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class Main {

    /*
    *  Crear un programa JAVA que realice las siguientes tareas:
    *   1. Conectarse a una BBDD MYSQL como administrador.
        2. Crear un usuario DAM2 password DAM2 con los permisos necesarios para manejar
        tablas y sql.
        3. Conectarse a la BBDD MYSQL como usuario DAM2 password DAM2
        4. Leer el fichero “CONCIERTOS.XML”
        5. Crear la tabla CONCIERTOS con los campos extraídos del XML
        6. Insertar los datos obtenidos del fichero “CONCIERTOS.XML”.
        7. Realizar una consulta de los registros de la tabla de conciertos y mostrarlos por
        pantalla bien formateados
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"main"+System.getProperty("file.separator")+"assets"+System.getProperty("file.separator")+"CONCIERTOS.XML");
        menu();

    }

    public static void menu() {

        System.out.println(" 1 - Conectarse como administrador y crear usuario DAM2");
        System.out.println(" 2 - Conectarse como usuario DAM2");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        switch (num) {

            case 1:
                CAdmin();
                break;
            case 2:
                conectarUsu();
                break;
        }

    }

    public static void CAdmin(){

        System.out.println("Conectado como Administrador");
        String createUser ="CREATE USER 'DAM2'@'%' IDENTIFIED BY 'DAM2';";
        String grantPrivileges ="GRANT ALL PRIVILEGES ON *.* TO 'DAM2'@'%';";
//        String usr = "root";  PARA XAMPP
//        String pwd = "";
        String usr = "root";
        String pwd = "rootpassword";

        try{
            Connection connection= null;
            connection = Conexion.conectar(usr, pwd);
            Statement statement= connection.createStatement();
            statement.executeUpdate(createUser);
            System.out.println("Usuario DAM2 creado");
            statement.executeUpdate(grantPrivileges);
            System.out.println("Privilegios otorgados");
            statement.close();
            Conexion.desconectar();
            System.out.println("Usuario DAM2 creado con exito");

            menu();
        }catch(SQLException e){
            e.printStackTrace();
        }


    }
    public static void menuUsu(String usr,String pwd){
        System.out.println(" 1 - Crear tabla BD con los campos y valores del fichero XML ");
        System.out.println(" 2 - Mostrar BD ");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        switch (num) {

            case 1:
                rFichero(usr, pwd);
                break;
            case 2:
                mostrarBD(usr, pwd);
                break;

        }

    }


    public static void conectarUsu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca la contrasenia del usuario DAM2");
        String pwd = scan.next();
        String usr = "DAM2";

        try{
            Connection connection= null;
            connection = Conexion.conectar(usr, pwd);

            if(connection != null){
                System.out.println("Conectado como" + usr);
                menuUsu(usr, pwd);
            }else{
                    System.out.println("Error");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }



    }


    public static void rFichero(String usr, String pwd) {
        try {
            Connection connection = Conexion.conectar(usr, pwd);
            Statement sentencia = connection.createStatement();

            // Ruta del archivo XML
            File rutaXML = new File(System.getProperty("user.dir") + System.getProperty("file.separator") +
                    "src" + System.getProperty("file.separator") + "assets" + System.getProperty("file.separator") + "CONCIERTOS.XML");

            // Parsear el archivo XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(rutaXML);
            doc.getDocumentElement().normalize();

            // Obtener todos los elementos "concierto"
            NodeList nList = doc.getElementsByTagName("concierto");

            if (nList.getLength() > 0) {
                // Crear la tabla CONCIERTOS si no existe
                String createTableSQL = "CREATE TABLE CONCIERTOS (" +
                        "grupo VARCHAR(50) CHARACTER SET utf8mb4, " +
                        "lugar VARCHAR(50) CHARACTER SET utf8mb4, " +
                        "fecha VARCHAR(30) CHARACTER SET utf8mb4, " +
                        "hora VARCHAR(10) CHARACTER SET utf8mb4)";
                sentencia.executeUpdate(createTableSQL);
                System.out.println("Tabla CONCIERTOS creada exitosamente.");

                // Preparar la consulta de inserción
                String insertSQL = "INSERT INTO CONCIERTOS (grupo, lugar, fecha, hora) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

                // Iterar sobre cada concierto y obtener sus valores
                for (int i = 0; i < nList.getLength(); i++) {
                    Element concierto = (Element) nList.item(i);


                    String grupo = truncar(concierto.getElementsByTagName("grupo").item(0).getTextContent(), 50);
                    String lugar = truncar(concierto.getElementsByTagName("lugar").item(0).getTextContent(), 50);
                    String fecha = truncar(concierto.getElementsByTagName("fecha").item(0).getTextContent(), 30);
                    String hora = truncar(concierto.getElementsByTagName("hora").item(0).getTextContent(), 10);

                    // Asignar los valores a la consulta preparada
                    preparedStatement.setString(1, grupo);
                    preparedStatement.setString(2, lugar);
                    preparedStatement.setString(3, fecha);
                    preparedStatement.setString(4, hora);

                    // Ejecutar la inserción
                    preparedStatement.executeUpdate();
                }

                System.out.println("Datos insertados exitosamente en la tabla CONCIERTOS.");

                // Cerrar conexiones
                preparedStatement.close();
                sentencia.close();
                Conexion.desconectar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String truncar(String original, int maxLength) {
        if (original.length() > maxLength) {
            return original.substring(0, maxLength);
        } else {
            return original;
        }
    }

    public static void mostrarBD(String usr,String pwd){

        Statement sentencia = null;
        ResultSet filas = null;

        try {
            Connection connection = Conexion.conectar(usr, pwd);
            sentencia = connection.createStatement();
            filas = sentencia.executeQuery(
                    "SELECT * FROM CONCIERTOS"
            );
            System.out.printf("%-30s %-50s %-20s %-10s%n", "Grupo", "Lugar", "Fecha", "Hora");
            while(filas.next()) {

                String grupo = filas.getString("grupo");
                String lugar = filas.getString("lugar");
                String fecha = filas.getString("fecha");
                String hora = filas.getString("hora");

                System.out.printf("%-30s %-50s %-20s %-10s%n", grupo, lugar, fecha, hora);
            }
            filas.close();
            sentencia.close();
            Conexion.desconectar();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}