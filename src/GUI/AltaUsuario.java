/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class AltaUsuario extends JDialog {
    
    private static final Dimension TAMANO_VENTANA = new Dimension(300, 400);
    /**
     * label nombre de usuario
     */
    private JLabel labelUsuario;
    /**
     * label contraseña
     */
    private JLabel labelContrasena;
    /**
     * Campo de texto para ingresar el nombre de usuario
     */
    private JTextField campoUsuario;
    /**
     * Campo de texto para ingresar la contraseña
     */
    private JPasswordField campoContrasena;
    /**
     * Botón que indica donde el usuario debe agregar sus cuentas de correo
     */
    private JButton botonAgregarCuentaDeCorreo;
    /**
     * Lista que representa las cuentas de correo del usuario
     */
    private JList listaCuentasDeCorreo;
    /**
     * Botón que indica aceptar la acción
     */
    private JButton botonAceptar;
    /**
     * Botón que indica cancelar la acción
     */
    private JButton botonCancelar;
    
        public AltaUsuario(LogIn ventanaLogIn) {
        //Llamo al constructor de la super clase JFrame, e instancio la
        //ventana con un título
        super(ventanaLogIn, "Alta Usuario",JDialog.DEFAULT_MODALITY_TYPE);
        this.configuracionBasicaVentana();
        this.inicializarComponentes();
    }

    /**
     * método privado que inicializa la configuración básica de la ventana.
     * Se utiliza en el constructor.
     */
    private void configuracionBasicaVentana(){
        this.setSize(TAMANO_VENTANA);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void inicializarComponentes(){
        this.labelUsuario = new JLabel("NOMBRE DE USUARIO");
        this.labelContrasena = new JLabel("CONTRASEÑA");
        this.campoUsuario = new JTextField();
        this.campoContrasena = new JPasswordField();
        this.botonAgregarCuentaDeCorreo = new JButton("Agregar Cuenta de Correo");
        this.botonAceptar = new JButton("Aceptar");
        this.botonCancelar = new JButton("Cancelar");
        this.listaCuentasDeCorreo = new JList();
        this.getContentPane().add(this.labelUsuario);
        this.getContentPane().add(this.campoUsuario);
        this.getContentPane().add(this.labelContrasena);
        this.getContentPane().add(this.campoContrasena);
        this.getContentPane().add(this.botonAgregarCuentaDeCorreo);
        this.getContentPane().add(this.botonAceptar);
        this.getContentPane().add(this.botonCancelar);
        this.getContentPane().add(this.listaCuentasDeCorreo);
        this.listaCuentasDeCorreo.setBounds(50,150, 200, 150);
        this.labelUsuario.setBounds(10, 20, 140, 20);
        this.campoUsuario.setBounds(140,20,110,20);
        this.labelContrasena.setBounds(10, 60, 100, 20);
        this.campoContrasena.setBounds(140,60,110,20);
        this.botonAgregarCuentaDeCorreo.setBounds(50,110,200,30);
        this.botonAceptar.setBounds(10,320,130,25);
        this.botonCancelar.setBounds(150,320,130,25);
        this.botonAgregarCuentaDeCorreo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
              GUICuentaDeCorreo ventanaCuentaCorreo = new GUICuentaDeCorreo(getThis());
                ventanaCuentaCorreo.setVisible(true);
            }
        });    

    }
        private AltaUsuario getThis () {
        return this;
    }
}
