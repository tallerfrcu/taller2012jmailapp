/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import controladores.ControladorDeFachada;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import modelo.CuentaDeCorreo;
import modelo.Envio;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class GUIEnviarCorreo extends javax.swing.JDialog {
    /**
     * instancia del controlador de fachada
     */
    private final ControladorDeFachada controladorDeFachada;
    /**
     * variable booleana que indica si el correo se guardó correctamente.
     * Se utiliza para indicarle a la GUIPrincipal que se guardó el correo
     * y hay que enviarlo.
     */
    private boolean correoEnviado = false;
    /**
     * Id del correo que se envió
     */
    private int idCorreoEnviado = 0;
    /**
     * Creates new form GUIEnviarCorreo
     * @param parent
     * @param modal
     * @param cuentaEnvio
     */
    public GUIEnviarCorreo(java.awt.Frame parent, boolean modal, 
            CuentaDeCorreo cuentaEnvio) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.cuentaEnvio = cuentaEnvio;
        this.controladorDeFachada = new ControladorDeFachada();
        this.campoOrigen.setText(this.cuentaEnvio.getNombreCuenta() + 
                this.cuentaEnvio.getServicio().getUrlServicioCorreo());
    }
    /**
     * instancia de la cuenta de correo de la que se está enviando
     */
    private final CuentaDeCorreo cuentaEnvio;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatosEnvio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoAsunto = new javax.swing.JTextField();
        campoOrigen = new javax.swing.JTextField();
        campoDestino = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelTexto = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoCuerpoMail = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("De: ");

        jLabel2.setText("Para: ");

        jLabel3.setText("Asunto: ");

        jLabel4.setText("Adjuntar");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/imagenes/attachment.gif"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatosEnvioLayout = new javax.swing.GroupLayout(panelDatosEnvio);
        panelDatosEnvio.setLayout(panelDatosEnvioLayout);
        panelDatosEnvioLayout.setHorizontalGroup(
            panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosEnvioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDatosEnvioLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(campoDestino))
                    .addGroup(panelDatosEnvioLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosEnvioLayout.createSequentialGroup()
                        .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(6, 6, 6)
                        .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDatosEnvioLayout.setVerticalGroup(
            panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosEnvioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32))
        );

        campoCuerpoMail.setColumns(20);
        campoCuerpoMail.setRows(5);
        jScrollPane1.setViewportView(campoCuerpoMail);

        javax.swing.GroupLayout panelTextoLayout = new javax.swing.GroupLayout(panelTexto);
        panelTexto.setLayout(panelTextoLayout);
        panelTextoLayout.setHorizontalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTextoLayout.setVerticalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnEnviar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE))
                            .addComponent(panelDatosEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDatosEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnviar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 
     * @param evt 
     */
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try {
            String destinoTemp = this.campoDestino.getText();
            Timestamp fechaTemp = new Timestamp(new java.util.Date().getTime());
            Envio mail = new Envio();
            mail.setAsuntoMail(this.campoAsunto.getText());
            mail.setDestinoMail(destinoTemp);
            mail.setFechaMail(fechaTemp);
            mail.setEnviado(false);
            mail.setOrigenMail(cuentaEnvio);
            mail.setTextoMail(this.campoCuerpoMail.getText());
            boolean aa = this.controladorDeFachada.guardarCorreoEnvio(mail);
            this.idCorreoEnviado = this.controladorDeFachada.getIdMail(destinoTemp, fechaTemp);
            this.correoEnviado = true;
            this.setVisible(false);
        } catch (ExcepcionErrorConexionBD | ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed
    /**
     * Método que devuelve true si el correo se guardó correctamente o false
     * si no se guardó ningún correo
     * @return true si se guardó el correo, false en caso contrario
     */
    public boolean getCorreoEnviado(){
        return this.correoEnviado;
    }
    public int getIdCorreoEnviado(){
        return this.idCorreoEnviado;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JTextField campoAsunto;
    private javax.swing.JTextArea campoCuerpoMail;
    private javax.swing.JTextField campoDestino;
    private javax.swing.JTextField campoOrigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatosEnvio;
    private javax.swing.JPanel panelTexto;
    // End of variables declaration//GEN-END:variables
}
