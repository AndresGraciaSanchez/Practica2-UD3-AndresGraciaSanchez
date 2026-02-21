package com.andresgracia.practica3.base.enumerados;

public enum Especializacion {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    FULL_STACK("Full Stack"),
    BASES_DATOS("Bases de datos"),
    SEGURIDAD("Seguridad"),
    IA("Inteligencia Artificial"),
    CLOUD("Cloud Computing");

    private String valor;

    Especializacion(String valor) {
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
