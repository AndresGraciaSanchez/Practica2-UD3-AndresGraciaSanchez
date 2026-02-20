package com.andresgracia.practica3.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "productos", schema = "tienda_software_open_source", catalog = "")
public class Producto {
    private int id;
    private String nombre;
    private String version;
    private float precio;
    private String licencia;
    private Date fechaLanzamiento;
    private int descargasTotales;
    private Categoria categoria;
    private List<Comentario> comentarios;
    private List<ProductoDesarrollador> productosDesarrolladores;

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
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "licencia")
    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    @Basic
    @Column(name = "fecha_lanzamiento")
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Basic
    @Column(name = "descargas_totales")
    public int getDescargasTotales() {
        return descargasTotales;
    }

    public void setDescargasTotales(int descargasTotales) {
        this.descargasTotales = descargasTotales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
                Float.compare(producto.precio, precio) == 0 &&
                descargasTotales == producto.descargasTotales &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(version, producto.version) &&
                Objects.equals(licencia, producto.licencia) &&
                Objects.equals(fechaLanzamiento, producto.fechaLanzamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, version, precio, licencia, fechaLanzamiento, descargasTotales);
    }

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @OneToMany(mappedBy = "producto")
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @OneToMany(mappedBy = "producto")
    public List<ProductoDesarrollador> getProductosDesarrolladores() {
        return productosDesarrolladores;
    }

    public void setProductosDesarrolladores(List<ProductoDesarrollador> productosDesarrolladores) {
        this.productosDesarrolladores = productosDesarrolladores;
    }
}
