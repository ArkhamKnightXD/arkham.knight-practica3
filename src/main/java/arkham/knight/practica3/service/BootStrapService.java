package arkham.knight.practica3.service;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootStrapService {

    /**
     *
     * @throws SQLException
     */
    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    /**
     *
     * @throws SQLException
     */
    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }


    /**
     * Metodo para recrear las tablas necesarios
     * @throws SQLException
     */
    public static void crearTablas() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS ARTICULO\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY NOT NULL,\n" +
                "  TITULO VARCHAR(100) NOT NULL,\n" +
                "  CUERPO VARCHAR(100) NOT NULL,\n" +
                "  FECHA DATE  NULL,\n" +
                "  AUTOR VARCHAR(50)  NULL,\n" +
                "  COMENTARIO VARCHAR(100)  NULL,\n" +
                "  ETIQUETA VARCHAR(50)  NULL\n" +
                ");";
        Connection conexion = DataBaseService.getInstancia().getConexion();
        Statement statement = conexion.createStatement();
        statement.execute(sql);
        statement.close();
        conexion.close();
    }
}
