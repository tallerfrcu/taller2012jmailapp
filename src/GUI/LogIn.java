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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Clase que representa una ventana de logueo a la aplicación
 * @author Accornero, Fontana, García, Pascal
 */
public class LogIn extends JFrame {
    /**
     * Variable de clase que representa el tamaño por defecto de la ventana.
     */
    private static final Dimension TAMANO_VENTANA = new Dimension(300, 500);
    /**
     * label nombre de usuario
     */
    private JLabel labelUsuario;
    /**
     * label contraseña
     */
    private JLabel labelContrasena;
    /**
     * campo de texto para ingresar el nombre de usuario
     */
    private JTextField campoUsuario;
    /**
     * campo de texto para ingresar la contraseña
     */
    private JPasswordField campoContrasena;
    /**
     * Botón que inicia sesión
     */
    private JButton botonLogIn;
    /**
     * Botón que inicia el alta de usuario
     */
    private JButton botonCrearCuenta;
    /**
     * Constructor de la clase que inicializa la ventana, instanciando sus
     * variables de instancia y componentes.
     */
    public LogIn() {
        //Llamo al constructor de la super clase JFrame, e instancio la
        //ventana con un título
        super("Log In");
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /**
     * método privado que inicializa los componentes de la ventana y los ubica
     * en la misma. Se utiliza en el constructor.
     */
    private void inicializarComponentes(){
        this.labelUsuario = new JLabel("USUARIO");
        this.labelContrasena = new JLabel("CONTRASEÑA");
        this.campoUsuario = new JTextField();
        this.campoContrasena = new JPasswordField();
        this.botonLogIn = new JButton("Iniciar Sesión");
        this.botonCrearCuenta = new JButton("Crear Cuenta");
        this.getContentPane().add(this.labelUsuario);
        this.getContentPane().add(this.campoUsuario);
        this.getContentPane().add(this.labelContrasena);
        this.getContentPane().add(this.campoContrasena);
        this.getContentPane().add(this.botonLogIn);
        this.getContentPane().add(this.botonCrearCuenta);
        this.labelUsuario.setBounds(10, 10, 100, 20);
        this.campoUsuario.setBounds(10,30,100,20);
        this.labelContrasena.setBounds(10, 50, 100, 20);
        this.campoContrasena.setBounds(10,70,100,20);
        this.botonLogIn.setBounds(10,90,150,25);
        this.botonCrearCuenta.setBounds(10,120,150,25);
        this.botonLogIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(rootPane, "El botón anda", "chingo", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
