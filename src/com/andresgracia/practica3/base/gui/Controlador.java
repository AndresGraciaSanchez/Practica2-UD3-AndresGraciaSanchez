package com.andresgracia.practica3.base.gui;

import com.andresgracia.practica3.base.*;
import com.andresgracia.practica3.base.util.Util;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controlador implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        addActionListeners(this);
        addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ((modelo.getSessionFactory() == null || modelo.getSessionFactory().isClosed())
                && !comando.equals("Conectar") && !comando.equals("Salir")) {
            Util.mensajeError("Debes estar conectado para hacer eso");
            return;
        }

        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;

            case "Conectar":
                try {
                    modelo.conectar();
                    vista.itemConectar.setEnabled(false);
                    vista.itemDesconectar.setEnabled(true);
                    Util.mensajeInformacion("Conectado correctamente");
                    listarTodo();
                } catch (Exception ex) {
                    Util.mensajeError("Error al conectar: " + ex.getMessage());
                }
                break;

            case "Desconectar":
                modelo.desconectar();
                vista.itemConectar.setEnabled(true);
                vista.itemDesconectar.setEnabled(false);
                limpiarTodo();
                limpiarListas();
                Util.mensajeInformacion("Desconectado correctamente");
                break;

            case "Opciones":
                Util.mensajeInformacion("Practica 2 - UD3 - Tienda de Software\n1.0");
                break;

            case "crearProducto":
                if (validarCamposProducto()) {
                    Producto nuevoProducto = crearProductoDesdeCampos();
                    modelo.altaProducto(nuevoProducto);
                    Util.mensajeInformacion("Producto creado correctamente");
                    listarTodo();
                    limpiarCamposProducto();
                }
                break;

            case "modificarProducto":
                if (vista.listaProductos.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de modificar");
                    return;
                }
                if (validarCamposProducto()) {
                    Producto productoModificado = (Producto) vista.listaProductos.getSelectedValue();
                    actualizarProductoDesdeCampos(productoModificado);
                    modelo.modificarProducto(productoModificado);
                    Util.mensajeInformacion("Modificado correctamente");
                    listarTodo();
                    limpiarCamposProducto();
                }
                break;

            case "eliminarProducto":
                if (vista.listaProductos.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de eliminar");
                    return;
                }
                if (Util.mensajeConfirmacion("¿Deseas eliminarlo?")) {
                    Producto productoEliminado = (Producto) vista.listaProductos.getSelectedValue();
                    modelo.borrarProducto(productoEliminado);
                    Util.mensajeInformacion("Eliminado correctamente");
                    listarTodo();
                    limpiarCamposProducto();
                }
                break;

            case "crearCliente":
                if (validarCamposCliente()) {
                    Cliente nuevoCliente = crearClienteDesdeCampos();
                    modelo.altaCliente(nuevoCliente);
                    Util.mensajeInformacion("Cliente creado correctamente");
                    listarTodo();
                    limpiarCamposCliente();
                }
                break;

            case "modificarCliente":
                if (vista.listaClientes.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de modificar");
                    return;
                }
                if (validarCamposCliente()) {
                    Cliente clienteModificado = (Cliente) vista.listaClientes.getSelectedValue();
                    actualizarClienteDesdeCampos(clienteModificado);
                    modelo.modificarCliente(clienteModificado);
                    Util.mensajeInformacion("Modificado correctamente");
                    listarTodo();
                    limpiarCamposCliente();
                }
                break;

            case "eliminarCliente":
                if (vista.listaClientes.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de eliminar");
                    return;
                }
                if (Util.mensajeConfirmacion("¿Deseas eliminarlo?")) {
                    Cliente clienteEliminado = (Cliente) vista.listaClientes.getSelectedValue();
                    modelo.borrarCliente(clienteEliminado);
                    Util.mensajeInformacion("Eliminado correctamente");
                    listarTodo();
                    limpiarCamposCliente();
                }
                break;

            case "crearDesarrollador":
                if (validarCamposDesarrollador()) {
                    Desarrollador nuevoDesarrollador = crearDesarrolladorDesdeCampos();
                    modelo.altaDesarrollador(nuevoDesarrollador);
                    Util.mensajeInformacion("Desarrollador creado correctamente");
                    listarTodo();
                    limpiarCamposDesarrollador();
                }
                break;

            case "modificarDesarrollador":
                if (vista.listaDesarrolladores.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de modificar");
                    return;
                }
                if (validarCamposDesarrollador()) {
                    Desarrollador desarrolladorModificado = (Desarrollador) vista.listaDesarrolladores.getSelectedValue();
                    actualizarDesarrolladorDesdeCampos(desarrolladorModificado);
                    modelo.modificarDesarrollador(desarrolladorModificado);
                    Util.mensajeInformacion("Modificado correctamente");
                    listarTodo();
                    limpiarCamposDesarrollador();
                }
                break;

            case "eliminarDesarrollador":
                if (vista.listaDesarrolladores.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de eliminar");
                    return;
                }
                if (Util.mensajeConfirmacion("¿Deseas eliminarlo?")) {
                    Desarrollador desarrolladorEliminado = (Desarrollador) vista.listaDesarrolladores.getSelectedValue();
                    modelo.borrarDesarrollador(desarrolladorEliminado);
                    Util.mensajeInformacion("Eliminado correctamente");
                    listarTodo();
                    limpiarCamposDesarrollador();
                }
                break;

            case "crearCategoria":
                if (validarCamposCategoria()) {
                    Categoria nuevaCategoria = crearCategoriaDesdeCampos();
                    modelo.altaCategoria(nuevaCategoria);
                    Util.mensajeInformacion("Categoría creada correctamente");
                    listarTodo();
                    limpiarCamposCategoria();
                }
                break;

            case "modificarCategoria":
                if (vista.listaCategorias.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguna antes de modificar");
                    return;
                }
                if (validarCamposCategoria()) {
                    Categoria categoriaModificada = (Categoria) vista.listaCategorias.getSelectedValue();
                    actualizarCategoriaDesdeCampos(categoriaModificada);
                    modelo.modificarCategoria(categoriaModificada);
                    Util.mensajeInformacion("Modificada correctamente");
                    listarTodo();
                    limpiarCamposCategoria();
                }
                break;

            case "eliminarCategoria":
                if (vista.listaCategorias.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguna antes de eliminar");
                    return;
                }
                if (Util.mensajeConfirmacion("¿Deseas eliminarla?")) {
                    Categoria categoriaEliminada = (Categoria) vista.listaCategorias.getSelectedValue();
                    modelo.borrarCategoria(categoriaEliminada);
                    Util.mensajeInformacion("Eliminada correctamente");
                    listarTodo();
                    limpiarCamposCategoria();
                }
                break;

            case "crearComentario":
                Comentario nuevoComentario = crearComentarioDesdeCampos();
                if (nuevoComentario != null) {
                    modelo.altaComentario(nuevoComentario);
                    Util.mensajeInformacion("Comentario creado correctamente");
                    listarTodo();
                    limpiarCamposComentario();
                }
                break;

            case "modificarComentario":
                if (vista.listaComentarios.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de modificar");
                    return;
                }
                if (validarCamposComentario()) {
                    Comentario comentarioModificado = (Comentario) vista.listaComentarios.getSelectedValue();
                    actualizarComentarioDesdeCampos(comentarioModificado);
                    modelo.modificarComentario(comentarioModificado);
                    Util.mensajeInformacion("Modificado correctamente");
                    listarTodo();
                    limpiarCamposComentario();
                }
                break;

            case "eliminarComentario":
                if (vista.listaComentarios.getSelectedIndex() == -1) {
                    Util.mensajeError("Debes seleccionar alguno antes de eliminar");
                    return;
                }
                if (Util.mensajeConfirmacion("¿Deseas eliminarlo?")) {
                    Comentario comentarioEliminado = (Comentario) vista.listaComentarios.getSelectedValue();
                    modelo.borrarComentario(comentarioEliminado);
                    Util.mensajeInformacion("Eliminado correctamente");
                    listarTodo();
                    limpiarCamposComentario();
                }
                break;
        }
    }

    private boolean validarCamposProducto() {
        if (vista.textFieldNombreProducto.getText().trim().isEmpty() ||
                vista.textFieldVersionProducto.getText().trim().isEmpty() ||
                vista.textFieldPrecioProducto.getText().trim().isEmpty() ||
                vista.textFieldDescargasProducto.getText().trim().isEmpty() ||
                vista.datePickerFechaLanzamientoProducto.getDate() == null ||
                vista.comboBoxCategoriasProducto.getSelectedIndex() == -1 ||
                vista.comboBoxLicenciaProducto.getSelectedIndex() == -1) {

            Util.mensajeError("Debes rellenar todos los campos");
            return false;
        }
        return true;
    }

    private boolean validarCamposCliente() {
        if (vista.textFieldNombreCliente.getText().trim().isEmpty() ||
                vista.textFieldEmailCliente.getText().trim().isEmpty() ||
                vista.textFieldTelefonoCliente.getText().trim().isEmpty() ||
                vista.datePickerFechaRegistroCliente.getDate() == null ||
                vista.comboBoxTipoCliente.getSelectedIndex() == -1) {

            Util.mensajeError("Debes rellenar todos los campos");
            return false;
        }
        return true;
    }

    private boolean validarCamposDesarrollador() {
        if (vista.textFieldNombreDesarrollador.getText().trim().isEmpty() ||
                vista.textFieldEmailDesarrollador.getText().trim().isEmpty() ||
                vista.textFieldPaisDesarrollador.getText().trim().isEmpty() ||
                vista.comboBoxEspecializacionDesarrollador.getSelectedIndex() == -1) {

            Util.mensajeError("Debes rellenar todos los campos");
            return false;
        }
        return true;
    }

    private boolean validarCamposCategoria() {
        if (vista.textFieldNombreCategoria.getText().trim().isEmpty() ||
                vista.textAreaDescripcionCategoria.getText().trim().isEmpty()) {

            Util.mensajeError("Debes rellenar todos los campos");
            return false;
        }
        return true;
    }

    private boolean validarCamposComentario() {
        if (vista.textAreaContenidoComentario.getText().trim().isEmpty() ||
                vista.datePickerFechaComentario.getDate() == null) {

            Util.mensajeError("Debes rellenar todos los campos");
            return false;
        }
        return true;
    }

    private Producto crearProductoDesdeCampos() {
        Producto producto = new Producto();
        producto.setNombre(vista.textFieldNombreProducto.getText().trim());
        producto.setVersion(vista.textFieldVersionProducto.getText().trim());
        producto.setPrecio(Float.parseFloat(vista.textFieldPrecioProducto.getText().trim()));
        producto.setDescargasTotales(Integer.parseInt(vista.textFieldDescargasProducto.getText().trim()));
        producto.setFechaLanzamiento(Date.valueOf(vista.datePickerFechaLanzamientoProducto.getDate()));
        String licenciaSeleccionada = (String) vista.comboBoxLicenciaProducto.getSelectedItem();
        producto.setLicencia(licenciaSeleccionada);
        producto.setCategoria((Categoria) vista.comboBoxCategoriasProducto.getSelectedItem());
        return producto;
    }

    private void actualizarProductoDesdeCampos(Producto producto) {
        producto.setNombre(vista.textFieldNombreProducto.getText().trim());
        producto.setVersion(vista.textFieldVersionProducto.getText().trim());
        producto.setPrecio(Float.parseFloat(vista.textFieldPrecioProducto.getText().trim()));
        producto.setDescargasTotales(Integer.parseInt(vista.textFieldDescargasProducto.getText().trim()));
        producto.setFechaLanzamiento(Date.valueOf(vista.datePickerFechaLanzamientoProducto.getDate()));
        String licenciaSeleccionada = (String) vista.comboBoxLicenciaProducto.getSelectedItem();
        producto.setLicencia(licenciaSeleccionada);
        producto.setCategoria((Categoria) vista.comboBoxCategoriasProducto.getSelectedItem());
    }

    private Cliente crearClienteDesdeCampos() {
        Cliente cliente = new Cliente();
        cliente.setNombre(vista.textFieldNombreCliente.getText().trim());
        cliente.setEmpresa(vista.textFieldEmpresaCliente.getText().trim());
        cliente.setEmail(vista.textFieldEmailCliente.getText().trim());
        cliente.setTelefono(vista.textFieldTelefonoCliente.getText().trim());
        cliente.setFechaRegistro(Date.valueOf(vista.datePickerFechaRegistroCliente.getDate()));
        String tipoClienteSeleccionado = (String) vista.comboBoxTipoCliente.getSelectedItem();
        cliente.setTipoCliente(tipoClienteSeleccionado);
        return cliente;
    }

    private void actualizarClienteDesdeCampos(Cliente cliente) {
        cliente.setNombre(vista.textFieldNombreCliente.getText().trim());
        cliente.setEmpresa(vista.textFieldEmpresaCliente.getText().trim());
        cliente.setEmail(vista.textFieldEmailCliente.getText().trim());
        cliente.setTelefono(vista.textFieldTelefonoCliente.getText().trim());
        cliente.setFechaRegistro(Date.valueOf(vista.datePickerFechaRegistroCliente.getDate()));
        String tipoClienteSeleccionado = (String) vista.comboBoxTipoCliente.getSelectedItem();
        cliente.setTipoCliente(tipoClienteSeleccionado);
    }

    private Desarrollador crearDesarrolladorDesdeCampos() {
        Desarrollador desarrollador = new Desarrollador();
        desarrollador.setNombre(vista.textFieldNombreDesarrollador.getText().trim());
        desarrollador.setEmail(vista.textFieldEmailDesarrollador.getText().trim());
        desarrollador.setPais(vista.textFieldPaisDesarrollador.getText().trim());
        desarrollador.setExperienciaAnnos((Integer) vista.spinnerExperienciaAnosDesarrollador.getValue());
        String tipoEspecializacion = (String) vista.comboBoxEspecializacionDesarrollador.getSelectedItem();
        desarrollador.setEspecializacion(tipoEspecializacion);
        desarrollador.setActivo(vista.checkBoxActivoDesarrollador.isSelected());
        return desarrollador;
    }

    private void actualizarDesarrolladorDesdeCampos(Desarrollador desarrollador) {
        desarrollador.setNombre(vista.textFieldNombreDesarrollador.getText().trim());
        desarrollador.setEmail(vista.textFieldEmailDesarrollador.getText().trim());
        desarrollador.setPais(vista.textFieldPaisDesarrollador.getText().trim());
        desarrollador.setExperienciaAnnos((Integer) vista.spinnerExperienciaAnosDesarrollador.getValue());
        String tipoEspecializacion = (String) vista.comboBoxEspecializacionDesarrollador.getSelectedItem();
        desarrollador.setEspecializacion(tipoEspecializacion);
        desarrollador.setActivo(vista.checkBoxActivoDesarrollador.isSelected());
    }

    private Categoria crearCategoriaDesdeCampos() {
        Categoria categoria = new Categoria();
        categoria.setNombre(vista.textFieldNombreCategoria.getText().trim());
        categoria.setDescripcion(vista.textAreaDescripcionCategoria.getText().trim());
        categoria.setEsLibre(vista.checkBoxEsLibreCategoria.isSelected());
        categoria.setPopularidad(vista.sliderPopularidadCategoria.getValue());
        return categoria;
    }

    private void actualizarCategoriaDesdeCampos(Categoria categoria) {
        categoria.setNombre(vista.textFieldNombreCategoria.getText().trim());
        categoria.setDescripcion(vista.textAreaDescripcionCategoria.getText().trim());
        categoria.setEsLibre(vista.checkBoxEsLibreCategoria.isSelected());
        categoria.setPopularidad(vista.sliderPopularidadCategoria.getValue());
    }

    private Comentario crearComentarioDesdeCampos() {
        Comentario comentario = new Comentario();
        comentario.setContenido(vista.textAreaContenidoComentario.getText().trim());
        comentario.setCalificacion(vista.sliderCalificacionComentario.getValue());
        comentario.setFechaComentario(Date.valueOf(vista.datePickerFechaComentario.getDate()));

        Producto producto = (Producto) vista.comboBoxProductoComentario.getSelectedItem();
        Cliente cliente = (Cliente) vista.comboBoxClienteComentario.getSelectedItem();

        comentario.setProducto(producto);
        comentario.setCliente(cliente);

        return comentario;
    }

    private void actualizarComentarioDesdeCampos(Comentario comentario) {
        comentario.setContenido(vista.textAreaContenidoComentario.getText().trim());
        comentario.setCalificacion(vista.sliderCalificacionComentario.getValue());
        comentario.setFechaComentario(Date.valueOf(vista.datePickerFechaComentario.getDate()));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) return;

        if (e.getSource() == vista.listaProductos) {
            Producto productoSeleccionado = (Producto) vista.listaProductos.getSelectedValue();
            if (productoSeleccionado != null) {
                cargarProductoEnCampos(productoSeleccionado);
                ArrayList<Comentario> comentarios = modelo.getComentariosPorProducto(productoSeleccionado);
                cargarComentariosProductoEnTabla(comentarios);
            } else {
                vista.tablaComentariosProducto.setModel(new DefaultTableModel());
            }
        } else if (e.getSource() == vista.listaClientes) {
            Cliente clienteSeleccionado = (Cliente) vista.listaClientes.getSelectedValue();
            if (clienteSeleccionado != null) {
                cargarClienteEnCampos(clienteSeleccionado);
                ArrayList<Comentario> comentarios = modelo.getComentariosPorCliente(clienteSeleccionado);
                cargarComentariosClienteEnTabla(comentarios);
            } else {
                vista.tablaComentariosCliente.setModel(new DefaultTableModel());
            }
        } else if (e.getSource() == vista.listaDesarrolladores) {
            Desarrollador desarrolladorSeleccionado = (Desarrollador) vista.listaDesarrolladores.getSelectedValue();
            if (desarrolladorSeleccionado != null) {
                cargarDesarrolladorEnCampos(desarrolladorSeleccionado);
                ArrayList<Producto> productos = modelo.getProductosPorDesarrollador(desarrolladorSeleccionado);
            }
        } else if (e.getSource() == vista.listaCategorias) {
            Categoria categoriaSeleccionada = (Categoria) vista.listaCategorias.getSelectedValue();
            if (categoriaSeleccionada != null) {
                cargarCategoriaEnCampos(categoriaSeleccionada);
                ArrayList<Producto> productos = modelo.getProductosPorCategoria(categoriaSeleccionada);
                cargarProductosCategoriaEnTabla(productos);
            } else {
                vista.tablaProductosCategoria.setModel(new DefaultTableModel());
            }
        } else if (e.getSource() == vista.listaComentarios) {
            Comentario comentarioSeleccionado = (Comentario) vista.listaComentarios.getSelectedValue();
            if (comentarioSeleccionado != null) {
                cargarComentarioEnCampos(comentarioSeleccionado);
            }
        }
    }

    private void listarTodo() {
        listarCategorias(modelo.getCategorias());
        listarProductos(modelo.getProductos());
        listarDesarrolladores(modelo.getDesarrolladores());
        listarClientes(modelo.getClientes());
        listarComentarios(modelo.getComentarios());
        cargarCombosRelacionados();
        cargarCombosComentarios();
    }

    private void listarCategorias(ArrayList<Categoria> categorias) {
        vista.dlmCategorias.clear();
        for (Categoria categoria : categorias) {
            vista.dlmCategorias.addElement(categoria);
        }
    }

    private void listarProductos(ArrayList<Producto> productos) {
        vista.dlmProductos.clear();
        for (Producto producto : productos) {
            vista.dlmProductos.addElement(producto);
        }
    }

    private void listarDesarrolladores(ArrayList<Desarrollador> desarrolladores) {
        vista.dlmDesarrolladores.clear();
        for (Desarrollador desarrollador : desarrolladores) {
            vista.dlmDesarrolladores.addElement(desarrollador);
        }
    }

    private void listarClientes(ArrayList<Cliente> clientes) {
        vista.dlmClientes.clear();
        for (Cliente cliente : clientes) {
            vista.dlmClientes.addElement(cliente);
        }
    }

    private void listarComentarios(ArrayList<Comentario> comentarios) {
        vista.dlmComentarios.clear();
        for (Comentario comentario : comentarios) {
            vista.dlmComentarios.addElement(comentario);
        }
    }

    private void cargarCombosRelacionados() {
        vista.comboBoxCategoriasProducto.removeAllItems();
        for (Categoria categoria : modelo.getCategorias()) {
            vista.comboBoxCategoriasProducto.addItem(categoria);
        }
    }

    private void cargarProductoEnCampos(Producto producto) {
        vista.textFieldNombreProducto.setText(producto.getNombre());
        vista.textFieldVersionProducto.setText(producto.getVersion());
        vista.textFieldPrecioProducto.setText(String.valueOf(producto.getPrecio()));
        vista.textFieldDescargasProducto.setText(String.valueOf(producto.getDescargasTotales()));

        if (producto.getFechaLanzamiento() != null) {
            vista.datePickerFechaLanzamientoProducto.setDate(producto.getFechaLanzamiento().toLocalDate());
        }

        vista.comboBoxLicenciaProducto.setSelectedItem(producto.getLicencia());
        vista.comboBoxCategoriasProducto.setSelectedItem(producto.getCategoria());
    }

    private void cargarClienteEnCampos(Cliente cliente) {
        vista.textFieldNombreCliente.setText(cliente.getNombre());
        vista.textFieldEmpresaCliente.setText(cliente.getEmpresa());
        vista.textFieldEmailCliente.setText(cliente.getEmail());
        vista.textFieldTelefonoCliente.setText(cliente.getTelefono());

        if (cliente.getFechaRegistro() != null) {
            vista.datePickerFechaRegistroCliente.setDate(cliente.getFechaRegistro().toLocalDate());
        }

        vista.comboBoxTipoCliente.setSelectedItem(cliente.getTipoCliente());
    }

    private void cargarDesarrolladorEnCampos(Desarrollador desarrollador) {
        vista.textFieldNombreDesarrollador.setText(desarrollador.getNombre());
        vista.textFieldEmailDesarrollador.setText(desarrollador.getEmail());
        vista.textFieldPaisDesarrollador.setText(desarrollador.getPais());
        vista.spinnerExperienciaAnosDesarrollador.setValue(desarrollador.getExperienciaAnnos());
        vista.comboBoxEspecializacionDesarrollador.setSelectedItem(desarrollador.getEspecializacion());
        vista.checkBoxActivoDesarrollador.setSelected(desarrollador.isActivo());
    }

    private void cargarCategoriaEnCampos(Categoria categoria) {
        vista.textFieldNombreCategoria.setText(categoria.getNombre());
        vista.textAreaDescripcionCategoria.setText(categoria.getDescripcion());
        vista.checkBoxEsLibreCategoria.setSelected(categoria.isEsLibre());
        vista.sliderPopularidadCategoria.setValue(categoria.getPopularidad());
    }

    private void cargarComentarioEnCampos(Comentario comentario) {
        vista.textAreaContenidoComentario.setText(comentario.getContenido());
        vista.sliderCalificacionComentario.setValue(comentario.getCalificacion());

        if (comentario.getFechaComentario() != null) {
            vista.datePickerFechaComentario.setDate(comentario.getFechaComentario().toLocalDate());
        }
    }

    private void limpiarCamposProducto() {
        vista.textFieldNombreProducto.setText("");
        vista.textFieldVersionProducto.setText("");
        vista.textFieldPrecioProducto.setText("");
        vista.textFieldDescargasProducto.setText("");
        vista.datePickerFechaLanzamientoProducto.setDate(null);
        vista.comboBoxLicenciaProducto.setSelectedIndex(-1);
        vista.comboBoxCategoriasProducto.setSelectedIndex(-1);
    }

    private void limpiarCamposCliente() {
        vista.textFieldNombreCliente.setText("");
        vista.textFieldEmpresaCliente.setText("");
        vista.textFieldEmailCliente.setText("");
        vista.textFieldTelefonoCliente.setText("");
        vista.datePickerFechaRegistroCliente.setDate(null);
        vista.comboBoxTipoCliente.setSelectedIndex(-1);
    }

    private void limpiarCamposDesarrollador() {
        vista.textFieldNombreDesarrollador.setText("");
        vista.textFieldEmailDesarrollador.setText("");
        vista.textFieldPaisDesarrollador.setText("");
        vista.spinnerExperienciaAnosDesarrollador.setValue(0);
        vista.comboBoxEspecializacionDesarrollador.setSelectedIndex(-1);
        vista.checkBoxActivoDesarrollador.setSelected(true);
    }

    private void limpiarCamposCategoria() {
        vista.textFieldNombreCategoria.setText("");
        vista.textAreaDescripcionCategoria.setText("");
        vista.checkBoxEsLibreCategoria.setSelected(true);
        vista.sliderPopularidadCategoria.setValue(0);
    }

    private void limpiarCamposComentario() {
        vista.textAreaContenidoComentario.setText("");
        vista.sliderCalificacionComentario.setValue(3);
        vista.datePickerFechaComentario.setDate(LocalDate.now());
    }

    private void limpiarTodo() {
        limpiarCamposProducto();
        limpiarCamposCliente();
        limpiarCamposDesarrollador();
        limpiarCamposCategoria();
        limpiarCamposComentario();
    }

    private void limpiarListas() {
        vista.dlmProductos.clear();
        vista.dlmClientes.clear();
        vista.dlmDesarrolladores.clear();
        vista.dlmCategorias.clear();
        vista.dlmComentarios.clear();
    }

    private void addActionListeners(ActionListener listener) {

        vista.itemConectar.addActionListener(listener);
        vista.itemConectar.setActionCommand("Conectar");

        vista.itemDesconectar.addActionListener(listener);
        vista.itemDesconectar.setActionCommand("Desconectar");

        vista.itemOpciones.addActionListener(listener);
        vista.itemOpciones.setActionCommand("Opciones");

        vista.itemSalir.addActionListener(listener);
        vista.itemSalir.setActionCommand("Salir");


        vista.crearProductoButton.addActionListener(listener);
        vista.crearProductoButton.setActionCommand("crearProducto");

        vista.modificarProductoButton.addActionListener(listener);
        vista.modificarProductoButton.setActionCommand("modificarProducto");

        vista.eliminarProductoButton.addActionListener(listener);
        vista.eliminarProductoButton.setActionCommand("eliminarProducto");


        vista.crearClienteButton.addActionListener(listener);
        vista.crearClienteButton.setActionCommand("crearCliente");

        vista.modificarClienteButton.addActionListener(listener);
        vista.modificarClienteButton.setActionCommand("modificarCliente");

        vista.eliminarClienteButton.addActionListener(listener);
        vista.eliminarClienteButton.setActionCommand("eliminarCliente");


        vista.crearDesarrolladorButton.addActionListener(listener);
        vista.crearDesarrolladorButton.setActionCommand("crearDesarrollador");

        vista.modificarDesarrolladorButton.addActionListener(listener);
        vista.modificarDesarrolladorButton.setActionCommand("modificarDesarrollador");

        vista.eliminarDesarrolladorButton.addActionListener(listener);
        vista.eliminarDesarrolladorButton.setActionCommand("eliminarDesarrollador");


        vista.crearCategoriaButton.addActionListener(listener);
        vista.crearCategoriaButton.setActionCommand("crearCategoria");

        vista.modificarCategoriaButton.addActionListener(listener);
        vista.modificarCategoriaButton.setActionCommand("modificarCategoria");

        vista.eliminarCategoriaButton.addActionListener(listener);
        vista.eliminarCategoriaButton.setActionCommand("eliminarCategoria");


        vista.crearComentarioButton.addActionListener(listener);
        vista.crearComentarioButton.setActionCommand("crearComentario");

        vista.modificarComentarioButton.addActionListener(listener);
        vista.modificarComentarioButton.setActionCommand("modificarComentario");

        vista.eliminarComentarioButton.addActionListener(listener);
        vista.eliminarComentarioButton.setActionCommand("eliminarComentario");
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listaProductos.addListSelectionListener(listener);
        vista.listaClientes.addListSelectionListener(listener);
        vista.listaDesarrolladores.addListSelectionListener(listener);
        vista.listaCategorias.addListSelectionListener(listener);
        vista.listaComentarios.addListSelectionListener(listener);
    }

    private void cargarCombosComentarios() {
        vista.comboBoxProductoComentario.removeAllItems();
        for (Producto producto : modelo.getProductos()) {
            vista.comboBoxProductoComentario.addItem(producto);
        }

        vista.comboBoxClienteComentario.removeAllItems();
        for (Cliente cliente : modelo.getClientes()) {
            vista.comboBoxClienteComentario.addItem(cliente);
        }
    }

    private void cargarComentariosProductoEnTabla(ArrayList<Comentario> comentarios) {
        String[] columnas = {"Cliente", "Contenido", "Calificacion", "Fecha"};
        Object[][] datos = new Object[comentarios.size()][4];

        for (int i = 0; i < comentarios.size(); i++) {
            Comentario c = comentarios.get(i);
            datos[i][0] = c.getCliente();
            datos[i][1] = c.getContenido();
            datos[i][2] = c.getCalificacion();
            datos[i][3] = c.getFechaComentario();
        }

        vista.tablaComentariosProducto.setModel(new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    private void cargarComentariosClienteEnTabla(ArrayList<Comentario> comentarios) {
        String[] columnas = {"Producto", "Contenido", "Calificacion", "Fecha"};
        Object[][] datos = new Object[comentarios.size()][4];

        for (int i = 0; i < comentarios.size(); i++) {
            Comentario c = comentarios.get(i);
            datos[i][0] = c.getProducto();
            datos[i][1] = c.getContenido();
            datos[i][2] = c.getCalificacion();
            datos[i][3] = c.getFechaComentario();
        }

        vista.tablaComentariosCliente.setModel(new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    private void cargarProductosCategoriaEnTabla(ArrayList<Producto> productos) {
        String[] columnas = {"Producto", "Version", "Precio", "Licencia", "Descargas"};
        Object[][] datos = new Object[productos.size()][5];

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            datos[i][0] = p.getNombre();
            datos[i][1] = p.getVersion();
            datos[i][2] = p.getPrecio();
            datos[i][3] = p.getLicencia();
            datos[i][4] = p.getDescargasTotales();
        }

        vista.tablaProductosCategoria.setModel(new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }
}
