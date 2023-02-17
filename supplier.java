/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import konfigurasi.Koneksi;
/**
 *
 * @author Septa
 */
public class supplier extends javax.swing.JFrame {
     DefaultTableModel table = new DefaultTableModel();
 
    public supplier() {
        initComponents();
        Koneksi.getConnection();
        
        
        jTable1.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama");
        table.addColumn("Alamat");
        table.addColumn("No Tlp");
       
        tampilData();
    }

    
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = jTable1.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `supplier` ";
        
        try{
            Connection connect = Koneksi.getConnection();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                    String id = rslt.getString("id");
                    String nama = rslt.getString("nama");
                    String alamat = rslt.getString("alamat");
                    String tlp = rslt.getString("no_tlp");
        
                    
                //masukan semua data kedalam array
                String[] data = {id,nama,alamat,tlp};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                jTable1.setModel(table);
            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    
    private void clear(){
        jId.setText(null);
        jNama.setText(null);
        jAlamat.setText(null);
        jTlp.setText(null);
    }
    
    private void simpan(){
        String kode = jId.getText();
        String nama = jNama.getText();
        String no_telp = jTlp.getText();
        String alamat = jAlamat.getText();
        
        //panggil koneksi
        Connection connect = Koneksi.getConnection();
        //query untuk memasukan data
        String query = ("insert into supplier VALUES ('"+jId.getText()+"','"+jNama.getText()+"','"+jAlamat.getText()+"','"+jTlp.getText()+"')");
        
        try{
            //menyiapkan statement untuk di eksekusi
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan!");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            tampilData();
            clear();
            
        }
    }
    private void hapusData(){
        //ambill data no pendaftaran
        int i = jTable1.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        Connection connect = Koneksi.getConnection();
        
        String query = "DELETE FROM `supplier` WHERE `id` = '"+jId+"' ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilData();
            clear();
        }
        
    }
    
    private void cari(){
        int row = jTable1.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = jSearch.getText();
        
        String query = "SELECT * FROM `supplier` WHERE `id`  LIKE '%"+cari+"%'";
                
       try{
           Connection connect = Koneksi.getConnection();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   
                    String id = rslt.getString("id");
                    String nama = rslt.getString("nama");
                    String alamat = rslt.getString("alamat");
                    String tlp = rslt.getString("no_tlp");
                    
                //masukan semua data kedalam array
                String[] data = {id,nama,alamat,tlp};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                jTable1.setModel(table);
           
        
    }catch(Exception e){
           System.out.println(e);
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

        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTlp = new javax.swing.JTextField();
        jSearch = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        clear_form = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Data Supplier");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 16, -1, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 65, 97, -1));

        jId.setBackground(new java.awt.Color(204, 204, 204));
        jId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdActionPerformed(evt);
            }
        });
        getContentPane().add(jId, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 86, 169, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 120, 60, 14));

        jNama.setBackground(new java.awt.Color(204, 204, 204));
        jNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNamaActionPerformed(evt);
            }
        });
        getContentPane().add(jNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 140, 169, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Alamat");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 173, -1, -1));

        jAlamat.setBackground(new java.awt.Color(204, 204, 204));
        jAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlamatActionPerformed(evt);
            }
        });
        jAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAlamatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jAlamatKeyTyped(evt);
            }
        });
        getContentPane().add(jAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 194, 169, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No Telepon");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 220, -1, -1));

        jTlp.setBackground(new java.awt.Color(204, 204, 204));
        jTlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTlpActionPerformed(evt);
            }
        });
        jTlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTlpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTlpKeyTyped(evt);
            }
        });
        getContentPane().add(jTlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 241, 169, -1));

        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });
        getContentPane().add(jSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

        btn_cari.setBackground(new java.awt.Color(0, 153, 0));
        btn_cari.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_cari.setSelected(true);
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 68, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 45, 435, 358));

        btn_tambah.setBackground(new java.awt.Color(102, 102, 255));
        btn_tambah.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_tambah.setSelected(true);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 279, 68, -1));

        clear_form.setBackground(new java.awt.Color(255, 0, 0));
        clear_form.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        clear_form.setForeground(new java.awt.Color(255, 255, 255));
        clear_form.setText("Clear form");
        clear_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        clear_form.setSelected(true);
        clear_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_formActionPerformed(evt);
            }
        });
        getContentPane().add(clear_form, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 279, 68, -1));

        btn_edit.setBackground(new java.awt.Color(102, 102, 255));
        btn_edit.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_edit.setSelected(true);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 320, 68, -1));

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_hapus.setSelected(true);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 320, 68, -1));

        btn_back.setBackground(new java.awt.Color(255, 204, 204));
        btn_back.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_back.setText("Kembali");
        btn_back.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_back.setSelected(true);
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
        });
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 390, 68, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/supplier.jpg"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 424));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdActionPerformed

    private void jNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNamaActionPerformed

    private void jAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAlamatActionPerformed

    private void jAlamatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAlamatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jAlamatKeyReleased

    private void jAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAlamatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jAlamatKeyTyped

    private void jTlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTlpActionPerformed

    private void jTlpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTlpKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTlpKeyReleased

    private void jTlpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTlpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTlpKeyTyped

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        jId.setText(table.getValueAt(baris, 0).toString());
        jNama.setText(table.getValueAt(baris, 1).toString());
        jAlamat.setText(table.getValueAt(baris, 2).toString());
        jTlp.setText(table.getValueAt(baris, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void clear_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_formActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clear_formActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE supplier SET id = '"+jId.getText()+"', nama = '"+jNama.getText()+"', alamat = '"+jAlamat.getText()+"', no_tlp= '"+jTlp.getText()+"' WHERE id = '"+jId.getText()+"'");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            tampilData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        clear();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        Connection conn = Koneksi.getConnection();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("delete from supplier where id ='" + jId.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                tampilData();
                jId.setText("");
                jNama.setText("");
                jAlamat.setText("");
                jTlp.setText("");
                jId.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } clear();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        // TODO add your handling code here:
        new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backMouseClicked

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
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton clear_form;
    public javax.swing.JTextField jAlamat;
    public javax.swing.JTextField jId;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JTextField jNama;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jSearch;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTlp;
    // End of variables declaration//GEN-END:variables
}
