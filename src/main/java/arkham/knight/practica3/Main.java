package arkham.knight.practica3;

import arkham.knight.practica3.encapsulacion.Articulo;
import arkham.knight.practica3.service.BootStrapService;
import arkham.knight.practica3.service.DataBaseService;
import arkham.knight.practica3.service.ArticuloService;
import java.util.Date;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Iniciando el servicio
        try {
            BootStrapService.startDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Prueba de Conexión.
        DataBaseService.getInstancia().testConexion();

        try {
            BootStrapService.crearTablas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        ArticuloService ArticuloService = new ArticuloService();
        //Insertando
        Articulo articulo = new Articulo();

        articulo.setTitulo("trigo");
        articulo.setCuerpo("Con leche");


        if(ArticuloService.getArticulo(articulo.getId())==null){
            ArticuloService.crearArticulo(articulo);
        }

        List<Articulo> listaArticulos = ArticuloService.listaArticulos();
        System.out.println("La cantidad de Articulos: "+listaArticulos.size());
        for(Articulo art : listaArticulos){
            System.out.println("El id es: "+art.getId());
        }

        for(Articulo art : listaArticulos){
            System.out.println("El titulo es: "+art.getTitulo());
        }

        for(Articulo art : listaArticulos){
            System.out.println("El cuerpo es: "+art.getCuerpo());
        }

        Articulo Articulo = ArticuloService.getArticulo(1);
        if(Articulo!=null){
            System.out.println("El titulo es: "+Articulo.getTitulo());
        }else{
            System.out.println("No existe el articulo consultado");
        }

        //Actualizando
      //  articulo.setTitulo("Cambiando el titulo.....");
        //rticuloService.actualizarArticulo(articulo);

        //Eleminando...
        //ArticuloServices.borrarArticulo(insertar.getMatricula());

        //Parando el servicio
        try {
            BootStrapService.stopDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }


