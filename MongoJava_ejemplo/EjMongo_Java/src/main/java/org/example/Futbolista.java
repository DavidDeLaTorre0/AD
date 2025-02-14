package org.example;

import org.bson.Document;

import java.util.ArrayList;
//import javax.swing.text.Document; esta no vale

public class Futbolista {

	private String nombre;
	private String apellidos;
	private Integer edad;
	private ArrayList<String> demarcacion;
	private Boolean internacional;

	public Futbolista() {
	}

	public Futbolista(String nombre, String apellidos, Integer edad, ArrayList<String> demarcacion, Boolean internacional) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.demarcacion = demarcacion;
		this.internacional = internacional;
	}

	// Transformo un objecto que me da MongoDB a un Objecto Java
	public Futbolista(Document dBObjectFutbolista) {
		this.nombre = dBObjectFutbolista.getString("nombre");
		this.apellidos = dBObjectFutbolista.getString("apellidos");
		this.edad = dBObjectFutbolista.getInteger("edad");

		// Cuidado cuando trabajamos con Arrays o Listas
		ArrayList<String> listDemarcaciones = (ArrayList<String>) dBObjectFutbolista.get("demarcacion");
		this.demarcacion = new ArrayList<String>();
		for (Object demarc : listDemarcaciones) {
			this.demarcacion.add(demarc.toString());
		}
		this.internacional = dBObjectFutbolista.getBoolean("internacional");
	}

	public Document toDBObjectFutbolista() {
		// Creamos una instancia de Document
		Document docFutbolista = new Document();

		docFutbolista.append("nombre", this.getNombre());
		docFutbolista.append("apellidos", this.getApellidos());
		docFutbolista.append("edad", this.getEdad());
		docFutbolista.append("demarcacion", this.getDemarcacion());
		docFutbolista.append("internacional", this.getInternacional());

		return docFutbolista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public ArrayList<String> getDemarcacion() {
		return demarcacion;
	}

	public void setDemarcacion(ArrayList<String> demarcacion) {
		this.demarcacion = demarcacion;
	}

	public Boolean getInternacional() {
		return internacional;
	}

	public void setInternacional(Boolean internacional) {
		this.internacional = internacional;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.getNombre() + " " + this.getApellidos() + " / Edad: " + this.edad + " / Demarcación: " + this.demarcacion.toString() + " / Internacional: " + this.internacional;
	}
}
