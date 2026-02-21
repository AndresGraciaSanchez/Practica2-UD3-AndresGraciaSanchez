package com.andresgracia.practica3.base.gui;

import com.andresgracia.practica3.base.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class Modelo {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void conectar() {
        Configuration configuracion = new Configuration();
        configuracion.configure("hibernate.cfg.xml");
        configuracion.addAnnotatedClass(Categoria.class);
        configuracion.addAnnotatedClass(Producto.class);
        configuracion.addAnnotatedClass(Desarrollador.class);
        configuracion.addAnnotatedClass(ProductoDesarrollador.class);
        configuracion.addAnnotatedClass(Cliente.class);
        configuracion.addAnnotatedClass(Comentario.class);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(configuracion.getProperties()).build();

        sessionFactory = configuracion.buildSessionFactory(ssr);
    }

    public void desconectar() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    public void altaCategoria(Categoria nuevaCategoria) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevaCategoria);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarCategoria(Categoria categoriaSeleccionada) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(categoriaSeleccionada);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarCategoria(Categoria categoriaBorrada) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(categoriaBorrada);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Categoria> getCategorias() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Categoria ORDER BY nombre");
        ArrayList<Categoria> lista = (ArrayList<Categoria>) query.getResultList();
        sesion.close();
        return lista;
    }

    public void altaProducto(Producto nuevoProducto) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoProducto);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarProducto(Producto productoSeleccionado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(productoSeleccionado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarProducto(Producto productoBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(productoBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Producto> getProductos() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Producto ORDER BY nombre");
        ArrayList<Producto> lista = (ArrayList<Producto>) query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Producto> getProductosPorCategoria(Categoria categoria) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Producto WHERE categoria = :cat ORDER BY nombre");
        query.setParameter("cat", categoria);
        ArrayList<Producto> lista = (ArrayList<Producto>) query.getResultList();
        sesion.close();
        return lista;
    }

    public void altaDesarrollador(Desarrollador nuevoDesarrollador) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoDesarrollador);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarDesarrollador(Desarrollador desarrolladorSeleccionado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(desarrolladorSeleccionado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarDesarrollador(Desarrollador desarrolladorBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(desarrolladorBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Desarrollador> getDesarrolladores() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Desarrollador ORDER BY nombre");
        ArrayList<Desarrollador> lista = (ArrayList<Desarrollador>) query.getResultList();
        sesion.close();
        return lista;
    }

    public void altaCliente(Cliente nuevoCliente) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoCliente);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarCliente(Cliente clienteSeleccionado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(clienteSeleccionado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarCliente(Cliente clienteBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(clienteBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Cliente> getClientes() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Cliente ORDER BY nombre");
        ArrayList<Cliente> lista = (ArrayList<Cliente>) query.getResultList();
        sesion.close();
        return lista;
    }

    public void altaComentario(Comentario nuevoComentario) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.save(nuevoComentario);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void modificarComentario(Comentario comentarioSeleccionado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(comentarioSeleccionado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarComentario(Comentario comentarioBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(comentarioBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Comentario> getComentarios() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Comentario ORDER BY fechaComentario DESC");
        ArrayList<Comentario> lista = (ArrayList<Comentario>) query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Comentario> getComentariosPorProducto(Producto producto) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Comentario WHERE producto = :prod ORDER BY fechaComentario DESC");
        query.setParameter("prod", producto);
        ArrayList<Comentario> lista = (ArrayList<Comentario>) query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Comentario> getComentariosPorCliente(Cliente cliente) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Comentario WHERE cliente = :cli ORDER BY fechaComentario DESC");
        query.setParameter("cli", cliente);
        ArrayList<Comentario> lista = (ArrayList<Comentario>) query.getResultList();
        sesion.close();
        return lista;
    }

    public ArrayList<Producto> getProductosPorDesarrollador(Desarrollador desarrollador) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery(
                "SELECT pd.producto FROM ProductoDesarrollador pd WHERE pd.desarrollador = :dev ORDER BY pd.producto.nombre");
        query.setParameter("dev", desarrollador);
        ArrayList<Producto> lista = (ArrayList<Producto>) query.getResultList();
        sesion.close();
        return lista;
    }

}
