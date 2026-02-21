package com.andresgracia.practica3.base.enumerados;

public enum TipoCliente {
    PARTICULAR("Particular"),
    EMPRESA("Empresa"),
    EDUCACION("Educacion"),
    SIN_ANIMO_LUCRO("Organizacion sin animo de lucro");

    private String valor;

    TipoCliente(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
