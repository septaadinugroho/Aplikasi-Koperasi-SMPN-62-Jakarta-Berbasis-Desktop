/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

/**
 *
 * @author Septa
 */
public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    public home() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        sisteminformasi = new javax.swing.JLabel();
        sisteminformasi1 = new javax.swing.JLabel();
        sisteminformasi2 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMaster = new javax.swing.JMenu();
        jProduk = new javax.swing.JMenu();
        jAnggota = new javax.swing.JMenu();
        jSupplier = new javax.swing.JMenu();
        jTransaksi = new javax.swing.JMenu();
        jRestock = new javax.swing.JMenu();
        jBrgMasuk = new javax.swing.JMenu();
        jLaporan = new javax.swing.JMenu();
        jLapTransaksi = new javax.swing.JMenu();
        jLapStokBarang = new javax.swing.JMenu();
        jLapBarangMasuk = new javax.swing.JMenu();
        jLapDataSupplier = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sisteminformasi.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sisteminformasi.setForeground(new java.awt.Color(255, 255, 255));
        sisteminformasi.setText("KOPERASI.");
        sisteminformasi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sisteminformasi.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(sisteminformasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        sisteminformasi1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sisteminformasi1.setForeground(new java.awt.Color(255, 255, 255));
        sisteminformasi1.setText("SISTEM");
        sisteminformasi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sisteminformasi1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(sisteminformasi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        sisteminformasi2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sisteminformasi2.setForeground(new java.awt.Color(255, 255, 255));
        sisteminformasi2.setText("INFORMASI");
        sisteminformasi2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sisteminformasi2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(sisteminformasi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/wp4102987-nature-blur-wallpapers.jpg"))); // NOI18N
        jPanel2.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 390));

        jMenuBar1.setBackground(new java.awt.Color(255, 204, 204));

        jMaster.setBackground(new java.awt.Color(0, 153, 153));
        jMaster.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        jMaster.setText("Master");
        jMaster.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N

        jProduk.setText("Produk");
        jProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProdukMouseClicked(evt);
            }
        });
        jProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProdukActionPerformed(evt);
            }
        });
        jMaster.add(jProduk);

        jAnggota.setText("Anggota");
        jAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAnggotaMouseClicked(evt);
            }
        });
        jMaster.add(jAnggota);

        jSupplier.setText("Supplier");
        jSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSupplierMouseClicked(evt);
            }
        });
        jMaster.add(jSupplier);

        jMenuBar1.add(jMaster);

        jTransaksi.setBackground(new java.awt.Color(0, 153, 153));
        jTransaksi.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        jTransaksi.setText("Transaksi");
        jTransaksi.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N

        jRestock.setText("Barang Keluar");
        jRestock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRestockMouseClicked(evt);
            }
        });
        jTransaksi.add(jRestock);

        jBrgMasuk.setText("Barang masuk");
        jBrgMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBrgMasukMouseClicked(evt);
            }
        });
        jTransaksi.add(jBrgMasuk);

        jMenuBar1.add(jTransaksi);

        jLaporan.setBackground(new java.awt.Color(0, 153, 153));
        jLaporan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        jLaporan.setText("Laporan");
        jLaporan.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N

        jLapTransaksi.setText("Transaksi");
        jLapTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLapTransaksiMouseClicked(evt);
            }
        });
        jLaporan.add(jLapTransaksi);

        jLapStokBarang.setText("Stok barang");
        jLapStokBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLapStokBarangMouseClicked(evt);
            }
        });
        jLaporan.add(jLapStokBarang);

        jLapBarangMasuk.setText("Barang masuk");
        jLapBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLapBarangMasukMouseClicked(evt);
            }
        });
        jLaporan.add(jLapBarangMasuk);

        jLapDataSupplier.setText("Data supplier");
        jLapDataSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLapDataSupplierMouseClicked(evt);
            }
        });
        jLaporan.add(jLapDataSupplier);

        jMenu1.setText("Data anggota");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jLaporan.add(jMenu1);

        jMenuBar1.add(jLaporan);

        jLogout.setBackground(new java.awt.Color(0, 153, 153));
        jLogout.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));
        jLogout.setText("Logout");
        jLogout.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        jLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(jLogout);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMouseClicked
        // TODO add your handling code here:
        login login=new login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLogoutMouseClicked

    private void jProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProdukActionPerformed
        // TODO add your handling code here:
        produk produk=new produk();
        produk.setVisible (true);
        this.dispose();
    }//GEN-LAST:event_jProdukActionPerformed

    private void jProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProdukMouseClicked
        // TODO add your handling code here:
        produk produk=new produk();
        produk.setVisible (true);
        this.dispose();
    }//GEN-LAST:event_jProdukMouseClicked

    private void jAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAnggotaMouseClicked
        // TODO add your handling code here:
        anggota anggota=new anggota();
        anggota.setVisible (true);
        this.dispose();
    }//GEN-LAST:event_jAnggotaMouseClicked

    private void jSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSupplierMouseClicked
        // TODO add your handling code here:
        supplier supplier=new supplier();
        supplier.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jSupplierMouseClicked

    private void jRestockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRestockMouseClicked
        // TODO add your handling code here:
        barang_keluar barang_keluar=new barang_keluar();
        barang_keluar.setVisible (true);
        this.dispose();
    }//GEN-LAST:event_jRestockMouseClicked

    private void jBrgMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBrgMasukMouseClicked
        // TODO add your handling code here:
        barang_masuk barang_masuk=new barang_masuk();
        barang_masuk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBrgMasukMouseClicked

    private void jLapTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLapTransaksiMouseClicked
        // TODO add your handling code here:
         new transaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLapTransaksiMouseClicked

    private void jLapStokBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLapStokBarangMouseClicked
        // TODO add your handling code here:
         new lap_stock().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLapStokBarangMouseClicked

    private void jLapBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLapBarangMasukMouseClicked
        // TODO add your handling code here:
         new lap_barang_masuk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLapBarangMasukMouseClicked

    private void jLapDataSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLapDataSupplierMouseClicked
        // TODO add your handling code here:
         new lap_supplier().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLapDataSupplierMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        new lap_anggota().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JMenu jAnggota;
    private javax.swing.JMenu jBrgMasuk;
    private javax.swing.JMenu jLapBarangMasuk;
    private javax.swing.JMenu jLapDataSupplier;
    private javax.swing.JMenu jLapStokBarang;
    private javax.swing.JMenu jLapTransaksi;
    private javax.swing.JMenu jLaporan;
    private javax.swing.JMenu jLogout;
    private javax.swing.JMenu jMaster;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenu jProduk;
    private javax.swing.JMenu jRestock;
    private javax.swing.JMenu jSupplier;
    private javax.swing.JMenu jTransaksi;
    private javax.swing.JLabel sisteminformasi;
    private javax.swing.JLabel sisteminformasi1;
    private javax.swing.JLabel sisteminformasi2;
    // End of variables declaration//GEN-END:variables
}
