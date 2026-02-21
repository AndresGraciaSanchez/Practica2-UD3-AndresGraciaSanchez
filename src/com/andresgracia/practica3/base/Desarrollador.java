package com.andresgracia.practica3.base;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "desarrolladores", schema = "tienda_software_open_source")
public class Desarrollador {
    private int id;
    private String nombre;
    private String email;
    private String pais;
    private int experienciaAnnos;
    private String especializacion;
    private boolean activo;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Basic
    @Column(name = "experiencia_annos")
    public int getExperienciaAnnos() {
        return experienciaAnnos;
    }

    public void setExperienciaAnnos(int experienciaAnnos) {
        this.experienciaAnnos = experienciaAnnos;
    }

    @Basic
    @Column(name = "especializacion")
    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    @Basic
    @Column(name = "activo")
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Desarrollador that = (Desarrollador) o;
        return id == that.id &&
                experienciaAnnos == that.experienciaAnnos &&
                activo == that.activo &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(email, that.email) &&
                Objects.equals(pais, that.pais) &&
                Objects.equals(especializacion, that.especializacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, pais, experienciaAnnos, especializacion, activo);
    }

    @OneToMany(mappedBy = "desarrollador")
    public List<ProductoDesarrollador> getProductosDesarrolladores() {
        return productosDesarrolladores;
    }

    public void setProductosDesarrolladores(List<ProductoDesarrollador> productosDesarrolladores) {
        this.productosDesarrolladores = productosDesarrolladores;
    }

    @Override
    public String toString() {
        return nombre + " (" + especializacion + ")";
    }
}
