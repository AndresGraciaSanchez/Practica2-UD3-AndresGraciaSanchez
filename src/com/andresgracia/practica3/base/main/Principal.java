package com.andresgracia.practica3.base.main;

import com.andresgracia.practica3.base.gui.Controlador;
import com.andresgracia.practica3.base.gui.Modelo;
import com.andresgracia.practica3.base.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
    }
}