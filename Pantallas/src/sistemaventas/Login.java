package sistemaventas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rancagua
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        limitar();
        SNumeros(tfRut);
    }
    public void limitar(){
        tfRut.setDocument(new Limitador(tfRut, 8));
        tfContraseña.setDocument(new Limitador (tfContraseña,30));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfRut = new javax.swing.JTextField();
        tfContraseña = new javax.swing.JPasswordField();
        CBXperfil = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnIniciarSesion = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bienvenido");
        setBackground(java.awt.Color.lightGray);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfRut.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        tfRut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(tfRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 210, -1));

        tfContraseña.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        tfContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(tfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 210, -1));

        CBXperfil.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        CBXperfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empleado", "Cliente" }));
        getContentPane().add(CBXperfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 210, -1));

        btnCancelar.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        btnIniciarSesion.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnIniciarSesion.setText("Iniciar Sesion");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        fondo.setBackground(new java.awt.Color(0, 204, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Layout Login.jpg"))); // NOI18N
        fondo.setText("jLabel1");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        // Crear intancia conexion
        ConexionBD conexion = new ConexionBD();
        Connection cn = conexion.conectar();
        //abrir conexion
        conexion.conectar();
        //Crear variables y asignar valores
        String Usuario = tfRut.getText();
        String Contraseña = tfContraseña.getText();
        String Tipo = (String) CBXperfil.getSelectedItem();
        String Perfil = "";
        //Saber si es cliente o empleado
        if(Tipo.equals("Cliente")){
            Perfil = "0";
            Variable.Tipo = "0";
        }else{
            Perfil = "1";
            Variable.Tipo = "1";
        }
        //
        if(Perfil.equals("0")){
            try {
                //Crear consulta para cliente
                String Query = "SELECT * FROM cliente WHERE Rut = "+Usuario+" and Contraseña = '"+Contraseña+"' and Perfil = 0";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(Query);
                if(rs.next())
                {
                    //obtener rut
                    Variable.Rut = tfRut.getText();
                    this.dispose();
                    PPrincipal PP = new PPrincipal();
                    PP.setVisible(true);
                }else
                {
                    JOptionPane.showMessageDialog(this, "Error, Ingrese sus datos Correctamete");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                //Crear consulta para cliente
                String Query = "SELECT * FROM empleado WHERE Rut = "+Usuario+" and Contraseña = '"+Contraseña+"' and Perfil = 1";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(Query);
                if(rs.next())
                {
                    //obtener rut
                    Variable.Rut = tfRut.getText();
                    this.dispose();
                    PPrincipal PP = new PPrincipal();
                    PP.setVisible(true);
                }else
                {
                    JOptionPane.showMessageDialog(this, "Error, Ingrese sus datos Correctamete");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    //Metodo para validar solo numeros
    public void SNumeros (JTextField a){
    //permite hacer la llamada al evento
        //KeyAdapter es una clase abstracta que se adapta para recibir los eventos del teclado
        a.addKeyListener(new KeyAdapter() {
            //Evento a utilizar
            public void keyTyped(KeyEvent e){
            //La variable char extrae la variable que se ingresa
                char c=e.getKeyChar();
                if(Character.isLetter(c)){
                //Sonido en caso que se ingrese un caracter no admitido
                getToolkit().beep();
                e.consume();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBXperfil;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField tfContraseña;
    private javax.swing.JTextField tfRut;
    // End of variables declaration//GEN-END:variables
}
