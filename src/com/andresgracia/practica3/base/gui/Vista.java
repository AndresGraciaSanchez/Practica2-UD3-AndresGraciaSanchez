package com.andresgracia.practica3.base.gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPaneProductos;
    private JPanel JPanelProductos;
    private JPanel JPanelClientes;
    private JPanel JPanelDesarrolladores;
    private JTextField textFieldNombreProducto;
    private JTextField textFieldVersionProducto;
    private JTextField textFieldPrecioProducto;
    private JTextField textFieldDescargasProducto;
    private JComboBox comboBoxCategoriasProducto;
    private JTable tablaProductos;
    private JComboBox comboBoxLicenciaProducto;
    private DatePicker datePickerFechaLanzamientoProducto;
    private JButton modificarProductoButton;
    private JButton crearProductoButton;
    private JButton eliminarProductoButton;
    private JTextField textFieldNombreCliente;
    private JTextField textFieldEmpresaCliente;
    private JTextField textFieldEmailCliente;
    private JTextField textFieldTelefonoCliente;
    private JComboBox comboBoxTipoCliente;
    private JTable tablaClientes;
    private JButton modificarClienteButton;
    private JButton crearClienteButton;
    private JButton eliminarClienteButton;
    private JTextField textFieldNombreDesarrollador;
    private JTextField textFieldEmailDesarrollador;
    private JTextField textFieldPaisDesarrollador;
    private JTextField textFieldExperienciaDesarrollador;
    private JCheckBox checkBoxActivoDesarrollador;
    private JComboBox comboBoxEspecializacionDesarrollador;
    private DatePicker datePickerFechaRegistroCliente;
    private JTable tablaDesarrolladores;
    private JButton modificarDesarrolladorButton;
    private JButton crearDesarrolladorButton;
    private JButton eliminarDesarrolladorButton;
}
