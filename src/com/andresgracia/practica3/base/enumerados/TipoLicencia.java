package com.andresgracia.practica3.base.enumerados;

public enum TipoLicencia {
    GPL("GPL"),
    MIT("MIT"),
    APACHE("Apache"),
    BSD("BSD"),
    COMERCIAL("Comercial");

    private String valor;

    TipoLicencia(String valor) {
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