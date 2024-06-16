package com.test.interfaces;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pdf.OpenPdf;
import com.test.bd.BD_Boleta;
import com.test.bd.BD_Categoria;
import com.test.bd.BD_Productos;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.test.modelo.Boleta;
import com.test.modelo.Producto;
import com.test.modelo.Categoria;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class RealizarPedido extends javax.swing.JFrame {

    //Email
    private static String emailFrom = "jcarmonarz@gmail.com";
    private static String passwordFrom = "lojo ixad cfah rzsy";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    // Boleta
    public int billId = 1;
    public int grandTotal = 0;
    public int productPrice = 0;
    public int productTotal = 0;
    public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    public String mobileNumberPattern = "^[0-9]*$";
    public String userEmail;

    public RealizarPedido() {
        initComponents();
        mProperties = new Properties();
        btnbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbillActionPerformed(evt);
            }
        });
    }

    public RealizarPedido(String email) {
        mProperties = new Properties();
        initComponents();
        txtproductname.setEditable(false);
        txtprice.setEditable(false);
        txttotal.setEditable(false);
        btnaddtocart.setEnabled(false);
        btnmail.setEnabled(false);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) jSpinner1.getEditor()).getTextField();
        tf.setEditable(false);
        userEmail = email;
    }

    private void createEmail() throws IOException {
        emailTo = txtcusemail.getText().trim();
        // Asunto predeterminado
        subject = "Hola " + txtcusname.getText().trim() + ", la Panquita De Oro le envía su boleta.";
        // Contenido predeterminado
        content = "Gracias por visitarnos en Av .Arenales #652, Ica, Peru. Le adjuntamos la boleta de su compra realizada el día de hoy. Vuelva pronto.";

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            MimeMultipart mElementosCorreo = new MimeMultipart();
            // Contenido del correo
            MimeBodyPart mContenido = new MimeBodyPart();
            mContenido.setContent(content, "text/html; charset=utf-8");
            mElementosCorreo.addBodyPart(mContenido);

            // Agregar archivo adjunto predeterminado
            MimeBodyPart mAdjunto = new MimeBodyPart();
            mAdjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\" + billId + ".pdf")));
            mAdjunto.setFileName(billId + ".pdf");
            mElementosCorreo.addBodyPart(mAdjunto);

            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(mElementosCorreo);
        } catch (AddressException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

// Método para adjuntar el archivo PDF al correo electrónico
    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void productNameByCategory(String category) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        ArrayList<Producto> list = BD_Productos.getAllRecordsByCategory(category);
        Iterator<Producto> itr = list.iterator();
        while (itr.hasNext()) {
            Producto productObj = itr.next();
            dtm.addRow(new Object[]{productObj.getName()});
        }
    }

    public void filterProductByname(String name, String category) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        ArrayList<Producto> list = BD_Productos.filterProductByname(name, category);
        Iterator<Producto> itr = list.iterator();
        while (itr.hasNext()) {
            Producto productObj = itr.next();
            dtm.addRow(new Object[]{productObj.getName()});
        }
    }

    public void clearProductFields() {
        txtproductname.setText("");
        txtprice.setText("");
        jSpinner1.setValue(1);
        txttotal.setText("");
        btnaddtocart.setEnabled(false);
    }

    public void validateField() {
        String customerName = txtcusname.getText();
        String customerMobileNumber = txtcusmobile.getText();
        String customerEmail = txtcusemail.getText();
        if (!customerEmail.equals("") && customerMobileNumber.matches(mobileNumberPattern) && customerMobileNumber.length() == 9 && customerEmail.matches(emailPattern) && grandTotal > 0) {
            btnmail.setEnabled(true);
        } else {
            btnmail.setEnabled(false);
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcusname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcusmobile = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcusemail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtproductname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        btnclear = new javax.swing.JButton();
        btnaddtocart = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtgrandtotal = new javax.swing.JTextField();
        btnmail = new javax.swing.JButton();
        btnbill = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/VP_RealizarPedido.png"))); // NOI18N
        jLabel1.setText("Realizar pedido");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Salir.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("N°de boleta:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Detalles del consumidor:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        txtcusname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtcusname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusnameKeyReleased(evt);
            }
        });
        getContentPane().add(txtcusname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 250, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Número telefónico");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        txtcusmobile.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtcusmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusmobileKeyReleased(evt);
            }
        });
        getContentPane().add(txtcusmobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 250, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Correo electrónico");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        txtcusemail.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtcusemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusemailKeyReleased(evt);
            }
        });
        getContentPane().add(txtcusemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 250, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Categoría");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 250, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Buscar en el menú");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        txtsearch.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 250, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 250, 357));

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        txtproductname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(txtproductname, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 250, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Precio");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 130, -1, -1));

        txtprice.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 250, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cantidad");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, -1, -1));

        jLabel12.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 210, -1, -1));

        txttotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, 250, -1));

        jSpinner1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 250, -1));

        btnclear.setBackground(new java.awt.Color(174, 148, 63));
        btnclear.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Limpiar.png"))); // NOI18N
        btnclear.setText("Limpiar");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        getContentPane().add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, -1, -1));

        btnaddtocart.setBackground(new java.awt.Color(174, 148, 63));
        btnaddtocart.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnaddtocart.setForeground(new java.awt.Color(255, 255, 255));
        btnaddtocart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Icono-Carrito.png"))); // NOI18N
        btnaddtocart.setText("Añadir al carrito");
        btnaddtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddtocartActionPerformed(evt);
            }
        });
        getContentPane().add(btnaddtocart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 290, -1, -1));

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "PRECIO", "CANTIDAD", "TOTAL"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, 578, 280));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Monto total S/.");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, -1, -1));

        txtgrandtotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtgrandtotal.setText("000");
        txtgrandtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgrandtotalActionPerformed(evt);
            }
        });
        getContentPane().add(txtgrandtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 640, 90, -1));

        btnmail.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnmail.setForeground(new java.awt.Color(83, 67, 27));
        btnmail.setText("Enviar");
        btnmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailActionPerformed(evt);
            }
        });
        getContentPane().add(btnmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 640, 80, -1));

        btnbill.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        btnbill.setForeground(new java.awt.Color(83, 67, 27));
        btnbill.setText("Generar boleta e imprimir");
        btnbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbillActionPerformed(evt);
            }
        });
        getContentPane().add(btnbill, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 640, 210, -1));

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("--");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/test/imagenes/Fondo.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String category = (String) jComboBox1.getSelectedItem();
        productNameByCategory(category);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtgrandtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgrandtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgrandtotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new VentanaPrincipal(userEmail).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        billId = Integer.parseInt(BD_Boleta.getId());
        jLabel15.setText(BD_Boleta.getId());
        ArrayList<Categoria> list = BD_Categoria.getAllRecorded();
        Iterator<Categoria> itr = list.iterator();
        while (itr.hasNext()) {
            Categoria categoryObj = itr.next();
            jComboBox1.addItem(categoryObj.getName());
        }
        String category = (String) jComboBox1.getSelectedItem();
        productNameByCategory(category);
    }//GEN-LAST:event_formComponentShown

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        String name = txtsearch.getText();
        String category = (String) jComboBox1.getSelectedItem();
        filterProductByname(name, category);
    }//GEN-LAST:event_txtsearchKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String productName = model.getValueAt(index, 0).toString();
        Producto product = BD_Productos.getProductByname(productName);
        txtproductname.setText(product.getName());
        txtprice.setText(product.getPrice());
        jSpinner1.setValue(1);
        txttotal.setText(product.getPrice());
        productPrice = Integer.parseInt(product.getPrice());
        productTotal = Integer.parseInt(product.getPrice());
        btnaddtocart.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        int quantity = (Integer) jSpinner1.getValue();
        if (quantity <= 1) {
            jSpinner1.setValue(1);
            quantity = 1;
        }
        productTotal = productPrice * quantity;
        txttotal.setText(String.valueOf(productTotal));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void btnaddtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddtocartActionPerformed
        // TODO add your handling code here:
        String name = txtproductname.getText();
        String price = txtprice.getText();
        String quantity = String.valueOf(jSpinner1.getValue());
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.addRow(new Object[]{name, price, quantity, productTotal});
        grandTotal = grandTotal + productTotal;
        txtgrandtotal.setText(String.valueOf(grandTotal));

        clearProductFields();
        validateField();
    }//GEN-LAST:event_btnaddtocartActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clearProductFields();
    }//GEN-LAST:event_btnclearActionPerformed

    private void txtcusnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusnameKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusnameKeyReleased

    private void txtcusmobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusmobileKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusmobileKeyReleased

    private void txtcusemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusemailKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusemailKeyReleased

    private void btnmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailActionPerformed
        try {
            createEmail();
        } catch (IOException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendEmail();
        
    }//GEN-LAST:event_btnmailActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int index = jTable2.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "¿Está seguro de querer eliminar este producto?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = jTable2.getModel();
            String total = model.getValueAt(index, 3).toString();
            grandTotal = grandTotal - Integer.parseInt(total);
            txtgrandtotal.setText(String.valueOf(grandTotal));
            ((DefaultTableModel) jTable2.getModel()).removeRow(index);
        }
    }//GEN-LAST:event_jTable2MouseClicked


    private void btnbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbillActionPerformed
        // TODO add your handling code here:
        String customerName = txtcusname.getText();
        String customerMobileNumber = txtcusmobile.getText();
        String customerEmail = txtcusemail.getText();
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String todaydate = dFormat.format(date);
        String total = String.valueOf(grandTotal);
        String createdBy = userEmail;
        Boleta bill = new Boleta();
        bill.setId(billId);
        bill.setName(customerName);
        bill.setMobileNumber(customerMobileNumber);
        bill.setEmail(customerEmail);
        bill.setDate(todaydate);
        bill.setTotal(total);
        bill.setCreatedBy(createdBy);
        BD_Boleta.save(bill);

        //Creando el documento:
        String path = "C:\\";
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "" + billId + ".pdf"));
            doc.open();
            Paragraph blank = new Paragraph("\n");
            doc.add(blank);
            Paragraph nombrerestaurante = new Paragraph("                                                                 La Panquita de Oro\n");
            doc.add(nombrerestaurante);
            Paragraph direccion = new Paragraph("                                                           Av.Arenales #652, Ica, Peru\n");
            doc.add(direccion);
            doc.add(blank);
            Paragraph starLine = new Paragraph("****************************************************************************************************************");
            doc.add(starLine);
            doc.add(blank);
            Paragraph boleta = new Paragraph("                                                               BOLETA DE VENTA N° " + billId + "\n");
            doc.add(boleta);
            Paragraph paragraph3 = new Paragraph("\tFECHA DE EMISIÓN: " + todaydate + "\nNombre del consumidor: " + customerName + "\nPago total: S/." + grandTotal);
            doc.add(paragraph3);
            doc.add(starLine);
            PdfPTable tb1 = new PdfPTable(4);
            tb1.getDefaultCell().setBorderWidth(0);
            tb1.addCell("NOMBRE");
            tb1.addCell("P. UNIT.");
            tb1.addCell("CANTIDAD");
            tb1.addCell("IMPORTE");
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                String n = jTable2.getValueAt(i, 0).toString();
                String d = jTable2.getValueAt(i, 1).toString();
                String r = jTable2.getValueAt(i, 2).toString();
                String q = jTable2.getValueAt(i, 3).toString();
                tb1.addCell(n);
                tb1.addCell(d);
                tb1.addCell(r);
                tb1.addCell(q);
            }
            doc.add(tb1);
            doc.add(starLine);
            doc.add(blank);
            Paragraph thanksMsg = new Paragraph("Gracias, vuelva pronto.");
            doc.add(thanksMsg);
            OpenPdf.openById(String.valueOf(billId));

            doc.close();
            //Enviar correo electrónico
            createEmail();
            sendEmail();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            // Enviar correo electrónico
            createEmail();
        } catch (IOException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendEmail();
        
        setVisible(false);
        new RealizarPedido(createdBy).setVisible(true);
    }//GEN-LAST:event_btnbillActionPerformed

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
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RealizarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RealizarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddtocart;
    private javax.swing.JButton btnbill;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnmail;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtcusemail;
    private javax.swing.JTextField txtcusmobile;
    private javax.swing.JTextField txtcusname;
    private javax.swing.JTextField txtgrandtotal;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproductname;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
