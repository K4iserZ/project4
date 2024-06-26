package com.test.interfaces;

import com.test.bd.BD_Usuario;

public class CambiarContrasena extends javax.swing.JFrame {

    public String userEmail;

    public CambiarContrasena() {
        initComponents();
    }

    public CambiarContrasena(String email) {
        initComponents();
        userEmail = email;
        btnupdate.setEnabled(false);
    }
    
    public void validateField(){
        String oldPassword = txtold.getText();
        String newPassword = txtnew.getText();
        String confirmPassword = txtconfirm.getText();
        if(!oldPassword.equals("") && !newPassword.equals("") && !confirmPassword.equals("") && newPassword.equals(confirmPassword)){
            btnupdate.setEnabled(true);
        }
        else
        btnupdate.setEnabled(false);
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
        jButton1 = new javax.swing.JButton();
        txtoldpassword = new javax.swing.JLabel();
        txtnewpassword = new javax.swing.JLabel();
        txtconfirmpassword = new javax.swing.JLabel();
        txtold = new javax.swing.JPasswordField();
        txtnew = new javax.swing.JPasswordField();
        txtconfirm = new javax.swing.JPasswordField();
        btnupdate = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(350, 134));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Contraseña.png"))); // NOI18N
        jLabel1.setText("Cambiar contraseña");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Salir.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, 30));

        txtoldpassword.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        txtoldpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtoldpassword.setText("Antigua contraseña:");
        getContentPane().add(txtoldpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        txtnewpassword.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        txtnewpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtnewpassword.setText("Nueva contraseña:");
        getContentPane().add(txtnewpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        txtconfirmpassword.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        txtconfirmpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtconfirmpassword.setText("Confirma la contraseña:");
        getContentPane().add(txtconfirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        txtold.setBackground(new java.awt.Color(83, 67, 27));
        txtold.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtold.setForeground(new java.awt.Color(255, 255, 255));
        txtold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtoldKeyReleased(evt);
            }
        });
        getContentPane().add(txtold, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 300, -1));

        txtnew.setBackground(new java.awt.Color(83, 67, 27));
        txtnew.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtnew.setForeground(new java.awt.Color(255, 255, 255));
        txtnew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnewKeyReleased(evt);
            }
        });
        getContentPane().add(txtnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 300, -1));

        txtconfirm.setBackground(new java.awt.Color(83, 67, 27));
        txtconfirm.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtconfirm.setForeground(new java.awt.Color(255, 255, 255));
        txtconfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconfirmActionPerformed(evt);
            }
        });
        txtconfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtconfirmKeyReleased(evt);
            }
        });
        getContentPane().add(txtconfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 300, -1));

        btnupdate.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(174, 148, 63));
        btnupdate.setText("Actualizar");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 110, 30));

        jButton3.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(174, 148, 63));
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 90, 30));

        jPanel1.setBackground(new java.awt.Color(174, 148, 63));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtconfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconfirmActionPerformed

    private void txtoldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtoldKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtoldKeyReleased

    private void txtnewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnewKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtnewKeyReleased

    private void txtconfirmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconfirmKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtconfirmKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new CambiarContrasena(userEmail).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        String oldPassword = txtold.getText();
        String newPassword = txtnew.getText();
        BD_Usuario.changePassword(userEmail,oldPassword,newPassword);
        setVisible(false);
        new CambiarContrasena(userEmail).setVisible(true);
    }//GEN-LAST:event_btnupdateActionPerformed

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
            java.util.logging.Logger.getLogger(CambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CambiarContrasena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtconfirm;
    private javax.swing.JLabel txtconfirmpassword;
    private javax.swing.JPasswordField txtnew;
    private javax.swing.JLabel txtnewpassword;
    private javax.swing.JPasswordField txtold;
    private javax.swing.JLabel txtoldpassword;
    // End of variables declaration//GEN-END:variables
}
