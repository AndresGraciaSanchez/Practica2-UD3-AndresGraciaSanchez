package com.andresgracia.practica3.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clientes", schema = "tienda_software_open_source")
public class Cliente {
    private int id;
    private String nombre;
    private String empresa;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    private String tipoCliente;
    private List<Comentario> comentarios;

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
    @Column(name = "empresa")
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "fecha_registro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Basic
    @Column(name = "tipo_cliente")
    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(empresa, cliente.empresa) &&
                Objects.equals(email, cliente.email) &&
                Objects.equals(telefono, cliente.telefono) &&
                Objects.equals(fechaRegistro, cliente.fechaRegistro) &&
                Objects.equals(tipoCliente, cliente.tipoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, empresa, email, telefono, fechaRegistro, tipoCliente);
    }

    @OneToMany(mappedBy = "cliente")
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return nombre + " - " + empresa;
    }
}
