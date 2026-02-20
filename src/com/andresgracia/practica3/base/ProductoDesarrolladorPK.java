package com.andresgracia.practica3.base;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductoDesarrolladorPK implements Serializable {
    private int idProducto;
    private int idDesarrollador;

    @Column(name = "id_producto")
    @Id
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Column(name = "id_desarrollador")
    @Id
    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoDesarrolladorPK that = (ProductoDesarrolladorPK) o;
        return idProducto == that.idProducto &&
                idDesarrollador == that.idDesarrollador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idDesarrollador);
    }
}
