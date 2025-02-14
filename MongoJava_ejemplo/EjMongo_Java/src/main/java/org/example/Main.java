package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		ArrayList<Futbolista> futbolistas = new ArrayList<Futbolista>();

		futbolistas.add(new Futbolista("Iker", "Casillas", 33, new ArrayList<String>(Arrays.asList("Portero")), true));
		futbolistas.add(new Futbolista("Carles", "Puyol", 36, new ArrayList<String>(Arrays.asList("Central", "Lateral")), true));
		futbolistas.add(new Futbolista("Sergio", "Ramos", 28, new ArrayList<String>(Arrays.asList("Lateral", "Central")), true));
		futbolistas.add(new Futbolista("Andrés", "Iniesta", 30, new ArrayList<String>(Arrays.asList("Centrocampista", "Delantero")), true));
		futbolistas.add(new Futbolista("Fernando", "Torres", 30, new ArrayList<String>(Arrays.asList("Delantero")), true));
		futbolistas.add(new Futbolista("Leo", " Baptistao", 22, new ArrayList<String>(Arrays.asList("Delantero")), false));
		try {
			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
			MongoClient mongoClient = MongoClients.create("mongodb://admin:admin123@localhost:27017");
			System.out.println("PASO 1: Conexión realizada: " +	mongoClient.getClusterDescription() + "\n");

			// PASO 2: Conexión a la base de datos
			MongoDatabase db = mongoClient.getDatabase("Futbol");
			System.out.println("PASO 2: Conexión realizada: " + db.getName() + "\n");

			// PASO 3: Obtenemos una coleccion para trabajar con ella
			MongoCollection<Document> collection = db.getCollection("Futbolistas");
			System.out.println("PASO 3: Conexión realizada:" +collection.getNamespace() + "\n");
			// PASO 4: CRUD (Create-Read-Update-Delete)

			// PASO 4.1: "CREATE" -> Metemos los objetos futbolistas (o documentos en Mongo) en la coleccion Futbolista
			for (Futbolista fut : futbolistas) {
				collection.insertOne(fut.toDBObjectFutbolista());
			}


			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de datos
			int numDocumentos = (int) collection.countDocuments();
			System.out.println("PASO 4.1: Número de documentos en la colección Futbolistas: " + numDocumentos + "\n");
			System.out.println("\n PASO 4.2.1: Futbolistas de la coleccion");


			// Busco todos los documentos de la colección y los imprimo
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					Futbolista futbolista = new Futbolista(cursor.next());
					System.out.println(futbolista.toString());
				}
			} finally {
				cursor.close();
			}

			// PASO 4.2.2: "READ" -> Hacemos una Query con condiciones (Buscar Futbolistas que sean delanteros) y lo pasamos a un objeto Java
			System.out.println("\nFutbolistas que juegan en la posición de Delantero:");
			Document query = new Document("demarcacion", new Document("$regex", "Delantero"));
			cursor = collection.find(query).iterator();
			try {
				while (cursor.hasNext()) {
					Futbolista futbolista = new Futbolista(cursor.next());
					System.out.println(futbolista.toString());
				}
			} finally {
				cursor.close();
			}

			// PASO 4.3: "UPDATE" -> Actualizamos la edad de los jugadores. Sumamos 100 años a los jugadores que tengan mas de 30 años
			Document find = new Document("edad", new BasicDBObject("$gt", 30));
			Document updated = new Document("$inc", new Document("edad", 100));
			collection.updateMany(find, updated);
			cursor = collection.find().iterator();
			System.out.println("\n PASO 4.3: Futbolistas después de la modificacion y antes del borrado :");
			try {
				while (cursor.hasNext()) {
					Futbolista futbolista = new Futbolista(cursor.next());
					System.out.println(futbolista.toString());
				}
			} finally {
				cursor.close();
			}

			// PASO 4.4: "DELETE" -> Borramos todos los futbolistas que sean internacionales (internacional = true)
			System.out.println("\n PASO 4.4: Futbolistas despues del borrado: ");
			Document findDoc = new Document("internacional", true);
			collection.deleteMany(findDoc);
			cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					Futbolista futbolista = new Futbolista(cursor.next());
					System.out.println(futbolista.toString());
				}
			} finally {
				cursor.close();
			}

			// PASO FINAL: Cerrar la conexion
			System.out.println("\nBorrando bbdd Futbol y cerrando conexion....");
			db.drop();
			mongoClient.close();

		} catch (Exception ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}


		ArrayList<Concierto> conciertos=new ArrayList<Concierto>();
		try {

			File inputFile = new File("src/main/resources/conciertos.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(inputFile);
			document.getDocumentElement().normalize();

			// Obtener una lista de elementos
			NodeList listaNodos = document.getElementsByTagName("concierto");

			for (int i = 0; i < listaNodos.getLength(); i++) {
				Node node = listaNodos.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) node;

					// 4 Extraer información del XML
					String grupo = elemento.getElementsByTagName("grupo").item(0).getTextContent();
					String lugar = elemento.getElementsByTagName("lugar").item(0).getTextContent();
					String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent();
					String hora = elemento.getElementsByTagName("hora").item(0).getTextContent();

					// 5 Crear un objeto Concierto y añadirlo a la lista
					Concierto concierto = new Concierto(grupo, lugar, fecha, hora);
					conciertos.add(concierto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n------------------------------------------------------------\n");
		try {
		// PASO 5.1: Conexión al Server de MongoDB Pasandole el host y el puerto
			MongoClient mongoClient1 = MongoClients.create("mongodb://admin:admin123@localhost:27017");

			System.out.println("PASO 5.1: Conexión realizada: " +	mongoClient1.getClusterDescription() + "\n");
		// PASO 5.2: Conexión a la base de datos
			MongoDatabase db1 = mongoClient1.getDatabase("Concierto");
			System.out.println("PASO 5.2: Conexión realizada: " + db1.getName() + "\n");
		// PASO 5.3: Obtenemos una coleccion para trabajar con ella
			MongoCollection<Document> collection1 = db1.getCollection("Conciertos");
			System.out.println("PASO 5.3: Conexión realizada:" +collection1.getNamespace() + "\n");

		// PASO 6: CRUD (Create-Read-Update-Delete)

			// PASO 6.1: "CREATE" -> Metemos los objetos futbolistas (o documentos en Mongo) en la coleccion Futbolista
			for (Concierto concierto : conciertos) {
				collection1.insertOne(concierto.toDBObjectConcierto());
			}
			System.out.println("PASO 6.1: Número de documentos en la colección Conciertos:" + (int) collection1.countDocuments() + "\n");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de datos
			int numDocumentos1 = (int) collection1.countDocuments();
			System.out.println("\nPASO 6.2.1: Conciertos de la coleccion: " + numDocumentos1 + "\n");


			// Busco todos los documentos de la colección y los imprimo
			MongoCursor<Document> cursor1 = collection1.find().iterator();
			try {
				while (cursor1.hasNext()) {
					System.out.println(cursor1.next().toString());
				}
			} finally {
				cursor1.close();
			}

			// PASO 6.2.2: "READ" -> Hacemos una Query con condiciones (Buscar Conciertos que sean a las 21:00) y lo pasamos a un objeto Java
			System.out.println("\n 6.2 Conciertos que son a las 21:00\n");
			Document query = new Document("hora", new Document("$regex", "21:00"));
			cursor1 =collection1.find(query).iterator();
			try {
				while (cursor1.hasNext()) {
					Concierto concierto = new Concierto(cursor1.next());
					System.out.println(concierto.toString());
				}
			} finally {
				cursor1.close();
			}

			// PASO 6.3: "UPDATE" -> Actualizamos el dia de los conciertos del 11 de octubre al 15.
			System.out.println("\n 6.3 Actualizar los conciertos del dia Viernes 11 octubre al dia 15: \n");
			Document filtro = new Document("fecha", new Document("$regex", ".*11 octubre*."));
			Document actualizacion = new Document().append("$set", new Document().append("fecha", "Miercoles 15 de octubre 2024"));
			collection1.updateMany(filtro, actualizacion);

			Document query1 = new Document("fecha", new Document("$regex", ".*15 de octubre*."));
			cursor1 =collection1.find(query1).iterator();
			try {
				while (cursor1.hasNext()) {
					Concierto concierto = new Concierto(cursor1.next());
					System.out.println(concierto.toString());
				}
			} finally {
				cursor1.close();
			}
			// PASO 6.4: "DELETE" -> Borramos todos los conciertos que sean a las 20:00

			Document findDoc1 = new Document("hora",  new Document("$regex", "20:00"));

			collection1.deleteMany(findDoc1);

			System.out.println("\n PASO 6.4: Conciertos despues del borrado de los que son a la 20:00 \n");

			cursor1 =collection1.find().iterator();
			try {
				while (cursor1.hasNext()) {
					Concierto concierto = new Concierto(cursor1.next());
					System.out.println(concierto.toString());
				}
			} finally {
				cursor1.close();
			}

		// PASO FINAL: Cerrar la conexion y borrar la bd
			System.out.println("\nBorrando bbdd Concierto y cerrando conexion....");
			db1.drop();
			mongoClient1.close();


		}catch (Exception ex) {
				System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}

	}

}
