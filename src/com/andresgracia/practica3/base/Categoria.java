package com.andresgracia.practica3.base;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "categorias", schema = "tienda_software_open_source", catalog = "")
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean esLibre;
    private int popularidad;
    private List<Producto> productos;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "es_libre")
    public boolean isEsLibre() {
        return esLibre;
    }

    public void setEsLibre(boolean esLibre) {
        this.esLibre = esLibre;
    }

    @Basic
    @Column(name = "popularidad")
    public int getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(int popularidad) {
        this.popularidad = popularidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id &&
                esLibre == categoria.esLibre &&
                popularidad == categoria.popularidad &&
                Objects.equals(nombre, categoria.nombre) &&
                Objects.equals(descripcion, categoria.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, esLibre, popularidad);
    }

    @OneToMany(mappedBy = "categoria")
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
