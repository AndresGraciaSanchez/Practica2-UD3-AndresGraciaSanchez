package com.andresgracia.practica3.base.util;

import javax.swing.*;

public class Util {

    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean mensajeConfirmacion(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }
}