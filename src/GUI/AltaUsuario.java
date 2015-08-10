/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import Recursos.utilidades.Validador;
import controladores.ControladorDeFachada;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class AltaUsuario extends JDialog {
    
    private static final Dimension TAMANO_VENTANA = new Dimension(300, 250);
    /**
     * label nombre de usuario
     */
    private JLabel labelUsuario;
    /**
     * label contraseña
     */
    private JLabel labelContrasena;
    /**
     * label repetir contraseña
     */
    private JLabel labelRepetirContrasena;
    /**
     * Campo de texto para ingresar el nombre de usuario
     */
    private JTextField campoUsuario;
    /**
     * Campo de texto para ingresar la contraseña
     */
    private JPasswordField campoContrasena;
    /**
     * Campo de texto para reingresar la contraseña
     */
    private JPasswordField campoRepetirContrasena;
    /**
     * Botón que indica aceptar la acción
     */
    private JButton botonAceptar;
    /**
     * Botón que indica cancelar la acción
     */
    private JButton botonCancelar;
    /**
     * Instancia del validador
     */
    private final Validador validador;
    /**
     * Instancia del controlador de fachada
     */
    private final ControladorDeFachada controlador;
    
        public AltaUsuario(LogIn ventanaLogIn) {
        //Llamo al constructor de la super clase JFrame, e instancio la
        //ventana con un título
        super(ventanaLogIn, "Alta Usuario",JDialog.DEFAULT_MODALITY_TYPE);
        this.validador = new Validador();
        this.controlador = new ControladorDeFachada();
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
        this.labelUsuario = new JLabel("Nombre de usuario");
        this.labelContrasena = new JLabel("Contraseña");
        this.labelRepetirContrasena = new JLabel("Repetir contraseña");
        this.campoUsuario = new JTextField();
        this.campoContrasena = new JPasswordField();
        this.campoRepetirContrasena = new JPasswordField();
        this.botonAceptar = new JButton("Aceptar");
        this.botonCancelar = new JButton("Cancelar");
        this.getContentPane().add(this.labelUsuario);
        this.getContentPane().add(this.campoUsuario);
        this.getContentPane().add(this.labelContrasena);
        this.getContentPane().add(this.campoContrasena);
        this.getContentPane().add(this.labelRepetirContrasena);
        this.getContentPane().add(this.campoRepetirContrasena);
        this.getContentPane().add(this.botonAceptar);
        this.getContentPane().add(this.botonCancelar);
        this.labelUsuario.setBounds(10, 20, 140, 20);
        this.campoUsuario.setBounds(160,20,110,20);
        this.labelContrasena.setBounds(10, 60, 100, 20);
        this.campoContrasena.setBounds(160,60,110,20);
        this.labelRepetirContrasena.setBounds(10, 100, 120, 20);
        this.campoRepetirContrasena.setBounds(160, 100, 110, 20);
        this.botonAceptar.setBounds(10,180,130,25);
        this.botonCancelar.setBounds(150,180,130,25);
        this.botonCancelar.addActionListener((ActionEvent ae) -> {
            dispose();
        });
        this.botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<JTextField> camposAValidar = new ArrayList<>();
                camposAValidar.add(campoUsuario);
                camposAValidar.add(campoContrasena);
                camposAValidar.add(campoRepetirContrasena);
                ArrayList camposInalidos =
                        (ArrayList) validador.validarDatosObligatorios(camposAValidar);
                if (camposInalidos.isEmpty()) {
                    if(validador.validarContraseñas(String.valueOf(
                            campoContrasena.getPassword()) ,
                            String.valueOf(campoRepetirContrasena.getPassword()))){
                        try {
                            controlador.generarUsuario(campoUsuario.getText(),
                                    String.valueOf(campoRepetirContrasena.getPassword()));
                            JOptionPane.showMessageDialog(null,
                                    "¡Usuario creado con éxito!",
                                    "Alta de Usuario",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    (new ImageIcon(getClass().
                                            getResource("/Recursos/imagenes/check.gif"))));
                            dispose();
                        } catch (ExcepcionErrorConexionBD |
                                ExcepcionArchivoDePropiedadesNoEncontrado ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage()
                                    , "Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No Coinciden las "
                                + "contraseñas", "Alta de Usuario",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    Iterator iterador = camposInalidos.iterator();
                    while(iterador.hasNext()) {
                        JTextField campoInvalido = (JTextField) iterador.next();
                        campoInvalido.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                    JOptionPane.showMessageDialog(null, "Los campos marcados "
                            + "con rojo son obligatorios", "Alta de Usuario",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
        /**
         * Método interno que devuelve la instancia actual de la ventana
         * @return 
         */
        private AltaUsuario getThis () {
        return this;
    }
}
