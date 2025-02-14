package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnlaceJDBC {
    private Connection connection;

    public EnlaceJDBC() throws SQLException {

    }

    public boolean crearTabla() throws SQLException{

    }

    public boolean insertar(String sql) throws SQLException {

        connection = Conexion.conectar();
        Statement statement = connection.createStatement();

        // Ejecutamos la sentencia
        boolean rowInserted = statement.executeUpdate(sql) > 0;

        statement.close();
        Conexion.desconectar();
        return rowInserted;
    }

    public ResultSet seleccionRegistros(String consultaSQL) {
        Statement sentencia = null;
        ResultSet filas = null;

        try {
            connection = Conexion.conectar();
            sentencia = connection.createStatement();
            filas = sentencia.executeQuery(consultaSQL);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filas;

    }
}
