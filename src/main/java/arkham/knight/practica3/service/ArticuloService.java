package arkham.knight.practica3.service;

import arkham.knight.practica3.encapsulacion.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

public class ArticuloService {

    public List<Articulo> listaArticulos() {
        List<Articulo> lista = new ArrayList<>();
        Connection conexionList = null; //objeto conexion.
        try {
            //
            String query = "select * from Articulo ";
            conexionList = DataBaseService.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = conexionList.prepareStatement(query);
            ResultSet resultado = prepareStatement.executeQuery();
            while(resultado.next()){
                Articulo articulo = new Articulo();
                articulo.setId(resultado.getLong("id"));
                articulo.setTitulo(resultado.getString("titulo"));
                articulo.setCuerpo(resultado.getString("cuerpo"));
                articulo.setFecha(resultado.getDate("fecha"));
              //  articulo.setFecha(resultado.getString("fecha"));

                lista.add(articulo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conexionList.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }


    public Articulo getArticulo(long id) {
        Articulo art = null;
        Connection conexionGet = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from Articulo where id = ?";
            conexionGet = DataBaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conexionGet.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet resultado = prepareStatement.executeQuery();
            while(resultado.next()){
                Articulo articulo = new Articulo();
                articulo.setId(resultado.getLong("id"));
                articulo.setTitulo(resultado.getString("titulo"));
                articulo.setCuerpo(resultado.getString("cuerpo"));
                articulo.setFecha(resultado.getDate("fecha"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conexionGet.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return art;
    }




    public boolean crearArticulo(Articulo articulo){
        boolean ok =false;

        Connection conexionCreate = null;
        try {

            String query = "insert into Articulo(id, titulo, cuerpo) values(?,?,?)";
            conexionCreate = DataBaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conexionCreate.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, articulo.getId());
            prepareStatement.setString(2, articulo.getTitulo());
            prepareStatement.setString(3, articulo.getCuerpo());
           // prepareStatement.setDate(4, articulo.getFecha());

            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conexionCreate.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }




    public boolean actualizarArticulo(Articulo articulo){
        boolean ok =false;

        Connection conexionUpdate = null;
        try {

            String query = "update Articulo set tiulo=?, cuerpo=? where id = ?";
            conexionUpdate = DataBaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conexionUpdate.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());

            //Indica el where...
            prepareStatement.setLong(3, articulo.getId());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conexionUpdate.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }



    public boolean borrarArticulo(long id){
        boolean ok =false;

        Connection conexionDelete = null;
        try {

            String query = "delete from Articulo where id = ?";
            conexionDelete = DataBaseService.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conexionDelete.prepareStatement(query);

            //Indica el where...
            prepareStatement.setLong(1, id);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conexionDelete.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }
}
