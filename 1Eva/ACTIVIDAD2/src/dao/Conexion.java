package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection jdbcConnection=null;
    private static final String URL="jdbc:mysql://localhost:3308/my_database";

    private Conexion(String usr, String pwd) throws SQLException {
         String USER= usr;
         String PWD= pwd;
         String DRIVER = "com.mysql.cj.jdbc.Driver";

        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(DRIVER);
                jdbcConnection = DriverManager.getConnection(URL, USER, PWD);

            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }

    }

    public static Connection conectar(String usr, String pwd) throws SQLException {

        new Conexion(usr,pwd);
        return jdbcConnection;
    }

    public static void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
