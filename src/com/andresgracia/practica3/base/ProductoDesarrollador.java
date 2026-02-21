package com.andresgracia.practica3.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "productos_desarrolladores", schema = "tienda_software_open_source")
@IdClass(ProductoDesarrolladorPK.class)
public class ProductoDesarrollador {

    private int idProducto;
    private int idDesarrollador;
    private String rol;
    private Date fechaUnion;
    private Producto producto;
    private Desarrollador desarrollador;

    @Id
    @Column(name = "id_producto", insertable = false, updatable = false)
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Id
    @Column(name = "id_desarrollador", insertable = false, updatable = false)
    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    @Basic
    @Column(name = "rol")
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Basic
    @Column(name = "fecha_union")
    public Date getFechaUnion() {
        return fechaUnion;
    }

    public void setFechaUnion(Date fechaUnion) {
        this.fechaUnion = fechaUnion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoDesarrollador that = (ProductoDesarrollador) o;
        return idProducto == that.idProducto &&
                idDesarrollador == that.idDesarrollador &&
                Objects.equals(rol, that.rol) &&
                Objects.equals(fechaUnion, that.fechaUnion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idDesarrollador, rol, fechaUnion);
    }

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id", nullable = false,
            insertable = false, updatable = false)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @ManyToOne
    @JoinColumn(name = "id_desarrollador", referencedColumnName = "id", nullable = false,
            insertable = false, updatable = false)
    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }
}