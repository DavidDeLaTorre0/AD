package org.example;

import org.bson.Document;

import java.util.ArrayList;

public class Concierto {

    private String grupo;
    private String lugar;
    private String fecha;
    private String hora;

    public Concierto(String grupo,String lugar,String fecha, String hora) {
        this.grupo = grupo;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Transformo un objecto que me da MongoDB a un Objecto Java
    //AQUI SE USA EL CURSOR.NEXT
    public Concierto(Document dBObjectFutbolista) {
        this.grupo = dBObjectFutbolista.getString("grupo");
        this.lugar = dBObjectFutbolista.getString("lugar");
        this.fecha = dBObjectFutbolista.getString("fecha");
        this.hora = dBObjectFutbolista.getString("hora");

    }

    public Document toDBObjectConcierto() {
        // Creamos una instancia de Document
        Document docConcierto = new Document();

        docConcierto.append("grupo", this.getGrupo());
        docConcierto.append("lugar", this.getLugar());
        docConcierto.append("fecha", this.getFecha());
        docConcierto.append("hora", this.getHora());

        return docConcierto;
    }
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Concierto{" +
                "grupo='" + grupo + '\'' +
                ", lugar='" + lugar + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora=" + hora +
                '}';
    }
}
