package arkham.knight.practica3;

import arkham.knight.practica3.encapsulacion.Articulo;
import arkham.knight.practica3.path.Ruta;
import arkham.knight.practica3.service.BootStrapService;
import arkham.knight.practica3.service.DataBaseService;
import arkham.knight.practica3.service.ArticuloService;
import arkham.knight.practica3.service.UsuarioService;

import java.util.Date;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            // Iniciando el servicio de Base de datos
            BootStrapService.startDb();

            // Prueba de conexión
            DataBaseService.getInstancia().testConexion();

            // Creando tablas de la Base de datos
            BootStrapService.crearTablas();

            // Crear usuario por defecto
            UsuarioService serviciousuario = new UsuarioService();
            serviciousuario.crearUsuarioPorDefecto();

            Ruta.crearRutas();

            // Deteniendo el servicio de Base de datos (no se usará por ahora pues no es necesario).
            // ServicioBootstrap.detenerBaseDatos();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


