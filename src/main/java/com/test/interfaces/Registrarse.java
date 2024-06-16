package com.test.interfaces;

import javax.swing.JOptionPane;
import com.test.modelo.Usuario;
import com.test.bd.BD_Usuario;

public class Registrarse extends javax.swing.JFrame {
    
    public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    public String mobileNumberPattern = "^[0-9]*$";

    public Registrarse() {
        initComponents();
        btnsave.setEnabled(false); 
    }
    
    public void clear(){
        txtname.setText("");
        txtemail.setText("");
        txtmn.setText("");
        txtaddress.setText("");
        txtsq.setText("");
        txtans.setText("");
        txtpassword.setText("");
        btnsave.setEnabled(false); 
    }
    
    public void validateFields(){
        String name = txtname.getText();
        String email = txtemail.getText();
        String mobileNumber = txtmn.getText();
        String address = txtaddress.getText();
        String password = txtpassword.getText();
        String securityQuestion = txtsq.getText();
        String answer = txtans.getText();
        if(!name.equals("") && email.matches(emailPattern) && mobileNumber.matches(mobileNumberPattern) && mobileNumber.length()== 9 && !address.equals("") && !password.equals("") && !securityQuestion.equals("") && !answer.equals(""))
            btnsave.setEnabled(true);
        else
            btnsave.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtsq = new javax.swing.JTextField();
        txtans = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtaddress = new javax.swing.JTextField();
        txtmn = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrate");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(47, 35, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 370, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 35, 12));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Correo electrónico");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 200, 370, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(47, 35, 12));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Numero telefónico");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 270, 370, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(47, 35, 12));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Localidad");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 340, 370, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(47, 35, 12));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Contraseña");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 410, 370, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(47, 35, 12));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pregunta de seguridad");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 480, 370, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(47, 35, 12));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Respuesta");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 550, 370, -1));

        txtname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 160, 370, -1));

        txtemail.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 230, 370, -1));

        txtsq.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtsq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsqActionPerformed(evt);
            }
        });
        txtsq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsqKeyReleased(evt);
            }
        });
        getContentPane().add(txtsq, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 510, 370, -1));

        txtans.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtansActionPerformed(evt);
            }
        });
        txtans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtansKeyReleased(evt);
            }
        });
        getContentPane().add(txtans, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 370, -1));

        txtpassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpasswordKeyReleased(evt);
            }
        });
        getContentPane().add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 440, 370, -1));

        txtaddress.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddressActionPerformed(evt);
            }
        });
        txtaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaddressKeyReleased(evt);
            }
        });
        getContentPane().add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 370, 370, -1));

        txtmn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtmn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmnKeyReleased(evt);
            }
        });
        getContentPane().add(txtmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 300, 370, -1));

        btnsave.setBackground(new java.awt.Color(174, 148, 63));
        btnsave.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnsave.setForeground(new java.awt.Color(255, 255, 255));
        btnsave.setText("Guardar");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 630, 90, 30));

        btnexit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Salir.png"))); // NOI18N
        btnexit.setContentAreaFilled(false);
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });
        getContentPane().add(btnexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 20, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("¿Haz olvidado tu contraseña?");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 690, -1, -1));

        jButton4.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(47, 35, 12));
        jButton4.setText("Iniciar sesión");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 690, 140, 30));

        btnclear.setBackground(new java.awt.Color(174, 148, 63));
        btnclear.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setText("Limpiar");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        getContentPane().add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 630, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Registrarse.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void txtsqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsqActionPerformed

    private void txtaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaddressActionPerformed

    private void txtansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtansActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        Usuario user = new Usuario();
        user.setName(txtname.getText());
        user.setEmail(txtemail.getText());
        user.setMobileNumber(txtmn.getText());
        user.setAddress(txtaddress.getText());
        user.setPassword(txtpassword.getText());
        user.setSecurityQuestion(txtsq.getText());
        user.setAnswer(txtans.getText());
        BD_Usuario.save(user);
        clear();
        
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new VentanaSalida().setVisible(true);
    }//GEN-LAST:event_btnexitActionPerformed

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtnameKeyReleased

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtemailKeyReleased

    private void txtmnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmnKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtmnKeyReleased

    private void txtaddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaddressKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtaddressKeyReleased

    private void txtpasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtpasswordKeyReleased

    private void txtsqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsqKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtsqKeyReleased

    private void txtansKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtansKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtansKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new IniciarSesion().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new RecordarContraseña().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrarse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtans;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmn;
    private javax.swing.JTextField txtname;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtsq;
    // End of variables declaration//GEN-END:variables
}
