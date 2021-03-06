/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionLogIn;
import Recursos.utilidades.Validador;
import controladores.ControladorDeFachada;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Clase que representa una ventana de logueo a la aplicación
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class LogIn extends JFrame {

    /**
     * Variable de clase que representa el tamaño por defecto de la ventana.
     */
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
     * Instancia de {@link controladores.ControladorDeFachada
     * ControladorDeFachada} que se utiliza para conectarse con las capas
     * inferiores de la aplicación
     */
    private final ControladorDeFachada controladorDeFachada;
    /**
     * Instancia de validador usado para validar la entrada de datos del usuario
     */
    private Validador validador;

    /**
     * Constructor de la clase que inicializa la ventana, instanciando sus
     * variables de instancia y componentes.
     */
    public LogIn() {
        //Llamo al constructor de la super clase JFrame, e instancio la
        //ventana con un título
        super("Log In");
        this.controladorDeFachada = new ControladorDeFachada();
        this.validador = new Validador();
        this.configuracionBasicaVentana();
        this.inicializarComponentes();
    }

    /**
     * método privado que inicializa la configuración básica de la ventana. Se
     * utiliza en el constructor.
     */
    private void configuracionBasicaVentana() {
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
    private void inicializarComponentes() {
        this.labelUsuario = new JLabel("Usuario");
        this.labelContrasena = new JLabel("Contraseña");
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
        this.labelUsuario.setBounds(20, 70, 100, 20);
        this.campoUsuario.setBounds(130, 70, 130, 20);
        this.labelContrasena.setBounds(20, 110, 100, 20);
        this.campoContrasena.setBounds(130, 110, 130, 20);
        this.botonLogIn.setBounds(20, 170, 120, 25);
        this.botonCrearCuenta.setBounds(150, 170, 120, 25);
        this.botonLogIn.addActionListener((ActionEvent ae) -> {
            loguear();
        });
        this.botonCrearCuenta.addActionListener((ActionEvent ae) -> {
            AltaUsuario ventanaAltaUsuario = new AltaUsuario(getThis());
            ventanaAltaUsuario.setVisible(true);
        });
        this.campoContrasena.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    loguear();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        this.campoUsuario.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }
            /**
             * Redefinición del método que se llama al presionar un botón en el
             * listener del campo usuario. Se observa si se aprieta la tecla
             * ENTER, de ser así, se ejecuta el loguin a la aplicación.
             * @param ke 
             */
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    loguear();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
    }

    /**
     * Método que devuelve la instancia actual de la clase. Se utiliza para
     * pasar por parámetro el padre, a las ventanas hijas.
     *
     * @return La instancia de LogIn
     */
    private LogIn getThis() {
        return this;
    }

    /**
     * Métood privado que se encarga de llamar a la función de login y verifica
     * que sea exitoso. De ser así, abre la ventana principal
     */
    private void loguear() {
        try {
            ArrayList<JTextField> camposObligatorios
                    = new ArrayList();
            camposObligatorios.add(campoUsuario);
            camposObligatorios.add(campoContrasena);
            ArrayList<JTextField> camposNoIngresados
                    = (ArrayList<JTextField>) validador.validarDatosObligatorios(
                            camposObligatorios);
            if (camposNoIngresados.isEmpty()) {
                if (controladorDeFachada.loguinUsuario(
                        campoUsuario.getText(), String.valueOf(
                                campoContrasena.getPassword()))) {
                    GUIPrincipal ventanaPrincipal = new GUIPrincipal();
                    //dispose();
                    ventanaPrincipal.setVisible(true);
                    this.dispose();
                }
            } else {
                Iterator iterador = camposNoIngresados.iterator();
                while (iterador.hasNext()) {
                    ((JTextField) iterador.next()).setBorder(
                            BorderFactory.createLineBorder(Color.red));
                }
                JOptionPane.showMessageDialog(null, "Los campos en rojo "
                        + "son obligatorios",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ExcepcionErrorConexionBD | ExcepcionArchivoDePropiedadesNoEncontrado | ExcepcionLogIn ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
