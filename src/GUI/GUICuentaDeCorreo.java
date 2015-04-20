/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import controladores.ControladorDeFachada;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.ServicioCorreo;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class GUICuentaDeCorreo extends JDialog {

    /**
     *
     */
    private static final Dimension TAMANO_VENTANA = new Dimension(500, 300);
    /**
     * label nombre de cuenta
     */
    private JLabel labelNombreCuenta;
    /**
     * label servicio de correo
     */
    private JLabel labelServicio;
    /**
     * Campo de texto para ingresar el nombre de la cuenta
     */
    private JTextField campoNombreCuenta;
    /**
     * Botón que indica aceptar
     */
    private JButton botonAceptar;
    /**
     * Botón que indica cancelar
     */
    private JButton botonCancelar;
    /**
     * Combo que representa una lista con los servicios de correo disponibles
     */
    private JComboBox comboDeServicios;
    /**
     *
     */
    private JPasswordField campoContrasena;
    /**
     *
     */

    private JLabel labelContrasena;

    private ControladorDeFachada controladorFachada;



    /**
     *
     * @param ventanaAlta
     */

    public GUICuentaDeCorreo(AltaUsuario ventanaAlta) {
        //Llamo al constructor de la super clase JFrame, e instancio la
        //ventana con un título
        super(ventanaAlta, "Cuenta de correo", JDialog.DEFAULT_MODALITY_TYPE);
        this.controladorFachada = new ControladorDeFachada();
        this.configuracionBasicaVentana();
        this.inicializarComponentes();
    }

    /**
     *
     */
    private void configuracionBasicaVentana() {
        this.setSize(TAMANO_VENTANA);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     *
     */
    private void inicializarComponentes() {
        this.labelNombreCuenta = new JLabel("NOMBRE DE CUENTA");
        this.labelServicio = new JLabel("SERVICIO");
        this.campoNombreCuenta = new JTextField();
        this.botonAceptar = new JButton("Aceptar");
        this.botonCancelar = new JButton("Cancelar");
        this.comboDeServicios = new JComboBox();
        this.labelContrasena = new JLabel("CONTRASEÑA");
        this.campoContrasena = new JPasswordField();
        this.getContentPane().add(this.labelNombreCuenta);
        this.getContentPane().add(this.campoNombreCuenta);
        this.getContentPane().add(this.labelServicio);
        this.getContentPane().add(this.labelContrasena);
        this.getContentPane().add(this.campoContrasena);
        this.getContentPane().add(this.botonAceptar);
        this.getContentPane().add(this.botonCancelar);
        this.getContentPane().add(this.comboDeServicios);
        this.comboDeServicios.setBounds(350, 20, 100, 30);
        this.labelServicio.setBounds(260, 20, 60, 20);
        this.labelNombreCuenta.setBounds(20, 20, 100, 20);
        this.campoNombreCuenta.setBounds(140, 20, 100, 20);
        this.labelContrasena.setBounds(20, 60, 100, 20);
        this.campoContrasena.setBounds(140, 60, 100, 20);
        this.botonAceptar.setBounds(80, 220, 150, 25);
        this.botonCancelar.setBounds(260, 220, 150, 25);
        
        Iterator iterador;
        ArrayList<ServicioCorreo> lista = null;
        try {
            lista = (ArrayList<ServicioCorreo>)
                    this.controladorFachada.getListaServicioCorreo();

        } catch (ExcepcionErrorConexionBD | ExcepcionArchivoDePropiedadesNoEncontrado ex ) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        iterador = lista.iterator();
        while (iterador.hasNext()) {
            this.comboDeServicios.addItem(
                    ((ServicioCorreo)iterador.next()).getUrlServicioCorreo());
        }
    }


}
