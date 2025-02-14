package entity;

public class Poblacion {

    private int idPoblacion;
    private String nombrePoblacion;
    private String provincia;


    public Poblacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Poblacion(int idPoblacion, String nombrePoblacion, String provincia) {
        this.idPoblacion = idPoblacion;
        this.nombrePoblacion = nombrePoblacion;
        this.provincia = provincia;
    }

    public int getIdPoblacion() {
        return idPoblacion;
    }

    public void setIdPoblacion(int idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public void setNombrePoblacion(String nombrePoblacion) {
        this.nombrePoblacion = nombrePoblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Poblacion{" +
                "idPoblacion=" + idPoblacion +
                ", nombrePoblacion='" + nombrePoblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
