package com.andresgracia.practica3.base.gui;

import com.andresgracia.practica3.base.enumerados.Especializacion;
import com.andresgracia.practica3.base.enumerados.TipoCliente;
import com.andresgracia.practica3.base.enumerados.TipoLicencia;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    JPanel panelPrincipal;
    JTabbedPane tabbedPane;

    JPanel JPanelProductos;
    JPanel JPanelClientes;
    JPanel JPanelDesarrolladores;
    JPanel JPanelCategorias;
    JPanel JPanelComentarios;

    JTextField textFieldNombreProducto;
    JTextField textFieldVersionProducto;
    JTextField textFieldPrecioProducto;
    JTextField textFieldDescargasProducto;
    JComboBox comboBoxCategoriasProducto;
    JComboBox comboBoxLicenciaProducto;
    DatePicker datePickerFechaLanzamientoProducto;
    JButton modificarProductoButton;
    JButton crearProductoButton;
    JButton eliminarProductoButton;

    JTextField textFieldNombreCliente;
    JTextField textFieldEmpresaCliente;
    JTextField textFieldEmailCliente;
    JTextField textFieldTelefonoCliente;
    JComboBox comboBoxTipoCliente;
    JButton modificarClienteButton;
    JButton crearClienteButton;
    JButton eliminarClienteButton;

    JTextField textFieldNombreDesarrollador;
    JTextField textFieldEmailDesarrollador;
    JTextField textFieldPaisDesarrollador;
    JCheckBox checkBoxActivoDesarrollador;
    JSpinner spinnerExperienciaAnosDesarrollador;
    JComboBox comboBoxEspecializacionDesarrollador;
    DatePicker datePickerFechaRegistroCliente;
    JButton modificarDesarrolladorButton;
    JButton crearDesarrolladorButton;
    JButton eliminarDesarrolladorButton;

    JTextField textFieldNombreCategoria;
    JTextArea textAreaDescripcionCategoria;
    JCheckBox checkBoxEsLibreCategoria;
    JSlider sliderPopularidadCategoria;
    JButton modificarCategoriaButton;
    JButton crearCategoriaButton;
    JButton eliminarCategoriaButton;

    JTextArea textAreaContenidoComentario;
    JSlider sliderCalificacionComentario;
    DatePicker datePickerFechaComentario;
    JButton modificarComentarioButton;
    JButton crearComentarioButton;
    JButton eliminarComentarioButton;
    JComboBox comboBoxProductoComentario;
    JComboBox comboBoxClienteComentario;

    JList listaProductos;
    JList listaClientes;
    JList listaDesarrolladores;
    JList listaCategorias;
    JList listaComentarios;

    DefaultListModel dlmProductos;
    DefaultListModel dlmClientes;
    DefaultListModel dlmDesarrolladores;
    DefaultListModel dlmCategorias;
    DefaultListModel dlmComentarios;

    JTable tablaComentariosProducto;
    JTable tablaComentariosCliente;
    JTable tablaProductosCategoria;

    public JMenuItem itemOpciones;
    public JMenuItem itemDesconectar;
    public JMenuItem itemConectar;
    public JMenuItem itemSalir;

    public Vista() {
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(1200, 700));
        this.setLocationRelativeTo(null);
        this.setTitle("Tienda de Software");

        crearMenu();
        setEnumComboBox();
        crearModelos();
    }

    private void crearMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemOpciones = new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemDesconectar = new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemDesconectar.setEnabled(false);
        itemConectar = new JMenuItem("Conectar");
        itemConectar.setActionCommand("Conectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemDesconectar);
        menu.add(itemConectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void crearModelos() {
        dlmProductos = new DefaultListModel();
        listaProductos.setModel(dlmProductos);

        dlmClientes = new DefaultListModel();
        listaClientes.setModel(dlmClientes);

        dlmDesarrolladores = new DefaultListModel();
        listaDesarrolladores.setModel(dlmDesarrolladores);

        dlmCategorias = new DefaultListModel();
        listaCategorias.setModel(dlmCategorias);

        dlmComentarios = new DefaultListModel();
        listaComentarios.setModel(dlmComentarios);
    }

    private void setEnumComboBox() {

        for (TipoCliente tipoCliente : TipoCliente.values()) {
            comboBoxTipoCliente.addItem(tipoCliente.getValor());
        }
        comboBoxTipoCliente.setSelectedIndex(-1);

        for (TipoLicencia tipoLicencia : TipoLicencia.values()) {
            comboBoxLicenciaProducto.addItem(tipoLicencia.getValor());
        }
        comboBoxLicenciaProducto.setSelectedIndex(-1);

        for (Especializacion especializacion : Especializacion.values()) {
            comboBoxEspecializacionDesarrollador.addItem(especializacion.getValor());
        }
        comboBoxEspecializacionDesarrollador.setSelectedIndex(-1);
    }

}
