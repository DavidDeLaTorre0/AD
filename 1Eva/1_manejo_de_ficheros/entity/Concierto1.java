package entity;
import java.io.*;

// Paso 1: Crear la clase Concierto
    public class Concierto1 implements Serializable {
        private String nombre;
        private String fecha;
        private String lugar;
        private double precio;

        public Concierto1(String nombre, String fecha, String lugar, double precio) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.lugar = lugar;
            this.precio = precio;
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
        public String toString() {
            return "Concierto{" +
                    "nombre='" + nombre + '\'' +
                    ", fecha='" + fecha + '\'' +
                    ", lugar='" + lugar + '\'' +
                    ", precio=" + precio +
                    '}';
        }
    }

