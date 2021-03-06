/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionLogIn;
import Recursos.utilidades.DatosDeSesion;
import controladores.ControladorDeFachada;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Mail;
import modelo.Recepcion;
import modelo.ServicioCorreo;
import modelo.Usuario;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class GUIPrincipal extends javax.swing.JFrame {

    /**
     * Instancia del controlador de fachada que se utiliza. Se utiliza para
     * interactuar con las capas inferiores de la aplicación
     */
    private ControladorDeFachada controladorDeFachada;
    /**
     * instancia del modelo de la lista de correos. Se utiliza para agregar
     * items a la lista de correos del usuario logueado a la aplicación
     */
    private DefaultListModel modeloListaCorreos;
    /**
     * instancia del modelo de la lista de carpetas. Se utiliza para agregar las
     * carpetas a la lista que contiene las carpetas
     */
    private DefaultListModel modeloCarpetasCorreo;
    /**
     * instancia del modelo de la tabla que muestra la lista de mails
     * correspondiente a una carpeta
     */
    private DefaultTableModel modeloTablaMails;
    /**
     *
     */
    private Thread hiloEnviarCorreo;
    /**
     * Creates new form GUIPrincipal
     */
    public GUIPrincipal() {
        initComponents();
        this.controladorDeFachada = new ControladorDeFachada();
        this.setLocationRelativeTo(null);
        try {
            this.construirListaCorreos();
            this.construirListaCarpetas();
            this.construirTablaMails();
            this.actualizarVentana();
        } catch (ExcepcionLogIn | ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            JOptionPane.showMessageDialog(null, "no log");
        } catch (ExcepcionErrorConexionBD ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMails = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaCarpetasCorreo = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        textPreviewCorreo = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        panelBotones = new javax.swing.JPanel();
        btnEnviarNuevo = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaCuentasDeCorreo = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaMails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "De", "Asunto", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaMails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaMails);
        if (tablaMails.getColumnModel().getColumnCount() > 0) {
            tablaMails.getColumnModel().getColumn(0).setResizable(false);
        }

        listaCarpetasCorreo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaCarpetasCorreo);

        textPreviewCorreo.setColumns(20);
        textPreviewCorreo.setRows(5);
        jScrollPane3.setViewportView(textPreviewCorreo);

        jToolBar1.setRollover(true);

        btnEnviarNuevo.setText("Nuevo");
        btnEnviarNuevo.setToolTipText("");
        btnEnviarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEnviarNuevo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(btnEnviarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("Enviando...");

        listaCuentasDeCorreo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaCuentasDeCorreo);

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane4)))
                        .addGap(18, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarNuevoActionPerformed
        try {
            CuentaDeCorreo cuentaDeEnvio = DatosDeSesion.getDatosDeSesion().getCuenta(this.listaCuentasDeCorreo.getSelectedValue().toString());
            if (cuentaDeEnvio != null) {
                GUIEnviarCorreo ventana = new GUIEnviarCorreo(this, true, cuentaDeEnvio);
                ventana.setVisible(true);
                if (ventana.getCorreoEnviado()) {
                    Mail mail = this.controladorDeFachada.getMailPorId(
                            ventana.getIdCorreoEnviado());
                    this.controladorDeFachada.enviarMail(mail);
                }
            }
        } catch (ExcepcionArchivoDePropiedadesNoEncontrado | 
                ExcepcionErrorConexionBD ex1) {
            JOptionPane.showMessageDialog(this, ex1.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEnviarNuevoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            CuentaDeCorreo cuenta = new CuentaDeCorreo();
            cuenta.setServicio(new ServicioCorreo(1, "@gmail.com"));
            cuenta.setContrasenaCuenta("tallerfrcu123");
            cuenta.setNombreCuenta("tallerfrcu");
            this.controladorDeFachada.actualizarCorreoRecibidos(cuenta);
        } catch (ExcepcionArchivoDePropiedadesNoEncontrado |
                ExcepcionDeServiciosCorreo | ExcepcionErrorConexionBD ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     *
     * @throws ExcepcionLogIn
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado
     */
    private void construirListaCorreos()
            throws ExcepcionLogIn, ExcepcionArchivoDePropiedadesNoEncontrado {
        this.modeloListaCorreos = new DefaultListModel();
        this.listaCuentasDeCorreo.setModel(this.modeloListaCorreos);
        //this.actualizarCorreosUsuario();
        this.listaCuentasDeCorreo.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    /**
     * Método que actualiza la lista de correos que tiene el usuario.
     *
     * @throws ExcepcionLogIn se lanza si no hay un usuario logueado a la
     * aplicación
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un
     * error al abrir el archivo de propiedades de la base de datos
     */
    private void actualizarCorreosUsuario()
            throws ExcepcionLogIn, ExcepcionArchivoDePropiedadesNoEncontrado {
        Usuario usuarioActual
                = this.controladorDeFachada.getUsuarioLogueado();
        if (usuarioActual.getCuentasCorreo().isEmpty()) {
            this.listaCuentasDeCorreo.setVisible(false);
        } else {
            this.modeloListaCorreos.clear();
            for (byte i = 0; i < usuarioActual.getCuentasCorreo().size(); i++) {
                this.modeloListaCorreos.addElement(
                        usuarioActual.getCuentasCorreo().get(i));
            }
            this.listaCuentasDeCorreo.setSelectedIndex(0);
        }
    }

    /**
     * Método privado que actualiza la lista de carpetas de la cuenta de correo
     * que se encuentra seleccionada.
     *
     * @param posicionCuentaSeleccionada
     */
    private void actualizarCarpetas(int posicionCuentaSeleccionada) {
        if (this.listaCuentasDeCorreo.isVisible()
                && !this.modeloListaCorreos.isEmpty()) {
            try {
                CuentaDeCorreo cuenta = (CuentaDeCorreo) this.modeloListaCorreos.get(posicionCuentaSeleccionada);
                ArrayList lista = (ArrayList) this.controladorDeFachada.getCarpetasCuenta(cuenta);
                this.modeloCarpetasCorreo.clear();
                Iterator iterador = lista.iterator();
                while (iterador.hasNext()) {
                    this.modeloCarpetasCorreo.addElement(((CarpetaCuentaCorreo) iterador.next()));
                }
                this.listaCarpetasCorreo.setSelectedIndex(0);
            } catch (ExcepcionErrorConexionBD ex) {
                JOptionPane.showMessageDialog(this, "Error al recuperar las carpetas",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ExcepcionArchivoDePropiedadesNoEncontrado ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método privado que construye la lista de carpetas
     */
    private void construirListaCarpetas() {
        this.modeloCarpetasCorreo = new DefaultListModel();
        this.listaCarpetasCorreo.setModel(this.modeloCarpetasCorreo);
        //this.actualizarCarpetas(0);
        this.listaCarpetasCorreo.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    //limpiarTablaMails();
                    actualizarTablaMails(listaCarpetasCorreo.getSelectedIndex());
                } catch (ExcepcionErrorConexionBD | ExcepcionArchivoDePropiedadesNoEncontrado ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), 
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    /**
     * Método privado que construye la tabla de mails
     */
    private void construirTablaMails()
            throws ExcepcionErrorConexionBD,
            ExcepcionArchivoDePropiedadesNoEncontrado {
        this.modeloTablaMails = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.modeloTablaMails.addColumn("");
        this.modeloTablaMails.addColumn("Asunto");
        this.modeloTablaMails.addColumn("De");
        this.modeloTablaMails.addColumn("Fecha");
        this.tablaMails.setModel(this.modeloTablaMails);
        this.tablaMails.getColumn("").setMaxWidth(14);
        this.tablaMails.getColumn("").setMinWidth(10);
        this.tablaMails.getColumn("").setPreferredWidth(12);
        this.tablaMails.getColumn("Asunto").setPreferredWidth(80);
        this.tablaMails.getColumn("De").setPreferredWidth(30);
        this.tablaMails.getColumn("Fecha").setPreferredWidth(20);
        //this.actualizarTablaMails(0);
    }

    /**
     * Método privado que actualiza la tabla de mails
     *
     * @param posicionCarpetaSeleccionada posición de la lista de la carpeta que
     * se encuentra seleccionada actualmente
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error al
     * conectarse a la base de datos local
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un
     * error al levantar el archivo de pripiedades de la base de datos local
     */
    private void actualizarTablaMails(int posicionCarpetaSeleccionada)
            throws ExcepcionErrorConexionBD,
            ExcepcionArchivoDePropiedadesNoEncontrado {
        CarpetaCuentaCorreo carpeta = (CarpetaCuentaCorreo) 
                this.modeloCarpetasCorreo.get(posicionCarpetaSeleccionada);
        carpeta.setListaMails(
                this.controladorDeFachada.getCorreosDeCarpeta(carpeta));
        Iterator iterador = carpeta.getListaMails().iterator();
        this.limpiarTablaMails();
        while (iterador.hasNext()) {
            Mail mail = null;
            Object obj = iterador.next();
            if (obj instanceof Recepcion) {
                mail = (Recepcion) obj;
            } else if (obj instanceof Envio) {
                mail = (Envio) obj;
            }
            Object[] filaDatos = new Object[4];
            filaDatos[0] = "";
            filaDatos[1] = mail.getAsuntoMail();
            filaDatos[2] = mail.getOrigen();
            filaDatos[3] = mail.getFechaMail();
            this.modeloTablaMails.addRow(filaDatos);
        }
    }

    /**
     * Método privado que limpia todas las filas de la tabla de mails.
     */
    private void limpiarTablaMails() {
        for (byte i = 0; i < this.modeloTablaMails.getRowCount(); i++) {
            this.modeloTablaMails.removeRow(i);
        }
    }
    /**
     * Método privado que actualiza la ventana para el caso que el usuario 
     * logueado no tenga cuentas de correo configuradas.
     */
    private void actualizarVentana() 
            throws ExcepcionArchivoDePropiedadesNoEncontrado, ExcepcionLogIn, ExcepcionErrorConexionBD{
        ArrayList cuentas = (ArrayList) DatosDeSesion.getDatosDeSesion().
                getUsuarioLogueado().getCuentasCorreo();
        if(cuentas.isEmpty()) {
            GUISinCuentasCorreo ventanaSinCuentaCorreo = 
                    new GUISinCuentasCorreo(this, true);
            this.setVisible(false);
            ventanaSinCuentaCorreo.setVisible(true);
        } else {
            this.listaCarpetasCorreo.setVisible(true);
            this.listaCuentasDeCorreo.setVisible(true);
            this.panelBotones.setVisible(true);
            this.textPreviewCorreo.setVisible(true);
            this.actualizarCorreosUsuario();
            this.actualizarCarpetas(this.listaCuentasDeCorreo.getSelectedIndex());
            this.actualizarTablaMails(this.listaCarpetasCorreo.getSelectedIndex());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JList listaCarpetasCorreo;
    private javax.swing.JList listaCuentasDeCorreo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JTable tablaMails;
    private javax.swing.JTextArea textPreviewCorreo;
    // End of variables declaration//GEN-END:variables

}
