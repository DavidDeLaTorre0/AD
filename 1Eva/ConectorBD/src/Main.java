import dao.PoblacionDAO;
import entity.Poblacion;
public class Main {
    public static void main(String[] args) {

        Poblacion poblacion = new Poblacion(2,"mejo","madrid");
        PoblacionDAO dao = new PoblacionDAO();
        PoblacionDAO.insertarPoblacion(poblacion);

        System.out.println("Se ha insertado :" + poblacion);

        System.out.println("Lista BD: " + PoblacionDAO.seleccionarPoblaciones());

    }
}