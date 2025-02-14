package dao;

import entity.Poblacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoblacionDAO {

    public static void insertarPoblacion(Poblacion pepe) {
        //	List<Poblacion> listaloca= new ArrayList<Poblacion>();
        try {
            EnlaceJDBC enlace = new EnlaceJDBC();
            enlace.insertar("insert into poblacion values("+pepe.getIdPoblacion()+",'"+pepe.getNombrePoblacion()+"','"+pepe.getProvincia()+"')");
            System.out.println("Se ha insertado correctamente");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error insertando");
        }




    }

    public static List<Poblacion> seleccionarPoblaciones(){
        List<Poblacion> listaselect= new ArrayList<Poblacion>();
        ResultSet res=null;
        try {
            EnlaceJDBC enlace = new EnlaceJDBC();
            res=enlace.seleccionRegistros("select * from poblacion");
            while(res.next()) {
                listaselect.add(new Poblacion(res.getInt(1),res.getString(2),res.getString(3)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return listaselect;
    }
}
