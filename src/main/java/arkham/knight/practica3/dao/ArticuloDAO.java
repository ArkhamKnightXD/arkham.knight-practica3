package arkham.knight.practica3.dao;

import arkham.knight.practica3.encapsulacion.Articulo;

import java.util.List;

public interface ArticuloDAO {

    List<Articulo> listarArticulos();

    Articulo encontrarPorId(int id);

    void agregar(Articulo articulo);

    void actualizar(Articulo articulo);

    void borrar(Articulo articulo);
}
