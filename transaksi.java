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
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import konfigurasi.Koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Septa
 */
public class transaksi extends javax.swing.JFrame {
     DefaultTableModel table = new DefaultTableModel();
 
    public transaksi() {
        initComponents();
        Koneksi.getConnection();
        totalnya();
        modalnya();
        profit();
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale);
        
        
        jTable1.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Modal");
         table.addColumn("Jumlah");
        table.addColumn("Total Modal");
        table.addColumn("Total Jual");
       
        tampilData();
    }

    
    private void tampilData(){
        //untuk mengahapus baris setelah input
        int row = jTable1.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM `transaksi` ";
        String procedures = "CALL `subtotal`()"; //procedurenya sudah dibuat di database
            String procedures1 = "CALL `submodal`()"; //procedurenya sudah dibuat di database
        
        try{
            Connection connect = Koneksi.getConnection();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                    String kode = rslt.getString("id");
                    String nama = rslt.getString("nama_brg");
                    String harga = rslt.getString("harga");
                    String modal = rslt.getString("modal");
                     String jumlah = rslt.getString("jumlah");
                    String total_modal = rslt.getString("total_modal");
                    String total = rslt.getString("total");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,modal,jumlah, total_modal,total};
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
        jHarga.setText(null);
       jModal.setText(null);
         jJumlah.setText(null);
        jTotalmodal.setText(null);
         jTotalbeli.setText(null);
    }
    
    private void simpan(){
        String kode = jId.getText();
        String nama = jNama.getText();
        String harga = jHarga.getText();
        String modal= jModal.getText();
        String jumlah = jJumlah.getText();
        String total_modal = jTotalmodal.getText();
        String total = jTotalbeli.getText();
        
        //panggil koneksi
        Connection connect = Koneksi.getConnection();
        //query untuk memasukan data
        String query = ("insert into transaksi VALUES ('"+jId.getText()+"','"+jNama.getText()+"','"+jHarga.getText()+"','"+jModal.getText()+"','"+jJumlah.getText()+"','"+jTotalmodal.getText()+"','"+jTotalbeli.getText()+"')");
        
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
        totalnya();
        modalnya();
         profit();
    }
    private void hapusData(){
        //ambill data no pendaftaran
        int i = jTable1.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        Connection connect = Koneksi.getConnection();
        
        String query = "DELETE FROM `transaksi` WHERE `id` = '"+jId+"' ";
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
        totalnya();
    }
    
    private void totalnya(){
        String procedures = "CALL `subtotal`()"; //procedurenya sudah dibuat di database
        
        try{
            Connection connect = Koneksi.getConnection();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
                while(rslt.next()){
                   jSubtotal.setText(rslt.getString(1));
                }
                
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
    }
    
    private void modalnya(){
        String procedures = "CALL `submodal`()"; //procedurenya sudah dibuat di database
        
        try{
            Connection connect = Koneksi.getConnection();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
                while(rslt.next()){
                   jSubmodal.setText(rslt.getString(1));
                }
                
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    private void total_modal(){
        String modal = jModal.getText();
        String jumlah = jJumlah.getText();
        
        int modall = Integer.parseInt(modal);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int totalnya = modall * jumlahh;
        String total_modal = Integer.toString(totalnya);
        
        jTotalmodal.setText(total_modal);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number ");
            jSubmodal.setText(null);
        }
    }
    
    
    private void total(){
        String harga = jHarga.getText();
        String jumlah = jJumlah.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int total = hargaa * jumlahh;
        String total_harga = Integer.toString(total);
        
        jTotalbeli.setText(total_harga);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number ");
            jSubtotal.setText(null);
        }
    }
    
    private void profit(){
        String subtotal = jSubtotal.getText();
        String submodal = jSubmodal.getText();
        
        int subtotal2 = Integer.parseInt(subtotal);
        try{
        int submodal2 = Integer.parseInt(submodal);
        
        int profit = subtotal2 - submodal2;
        String total_profit = Integer.toString(profit);
        
        jProfit.setText(total_profit);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number ");
          
        }
    }
    
    private void cari(){
        int row = jTable1.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String cari = jSearch.getText();
        
        String query = "SELECT * FROM `transaksi` WHERE `id`  LIKE '%"+cari+"%'";
                
       try{
           Connection connect = Koneksi.getConnection();//memanggil koneksi
           Statement sttmnt = connect.createStatement();//membuat statement
           ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
           
           while (rslt.next()){
                //menampung data sementara
                   String kode = rslt.getString("id");
                    String nama = rslt.getString("nama_brg");
                    String harga = rslt.getString("harga");
                    String modal = rslt.getString("modal");
                     String jumlah = rslt.getString("jumlah");
                    String total_modal = rslt.getString("total_modal");
                    String total = rslt.getString("total");
                    
                //masukan semua data kedalam array
                String[] data = {kode,nama,harga,modal,jumlah, total_modal,total};
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
        jProfit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jJumlah = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTotalbeli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        clear_table = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSubtotal = new javax.swing.JTextField();
        btn_back1 = new javax.swing.JButton();
        clear_form = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSubmodal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTotalmodal = new javax.swing.JTextField();
        btn_cetak = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jModal = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jSearch = new javax.swing.JTextField();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Data Transaksi Harian");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Profit");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 60, -1));

        jProfit.setBackground(new java.awt.Color(204, 204, 204));
        jProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProfitActionPerformed(evt);
            }
        });
        getContentPane().add(jProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 180, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 14));

        jNama.setBackground(new java.awt.Color(204, 204, 204));
        jNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNamaActionPerformed(evt);
            }
        });
        getContentPane().add(jNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 210, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jumlah");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jJumlah.setBackground(new java.awt.Color(204, 204, 204));
        jJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJumlahActionPerformed(evt);
            }
        });
        jJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jJumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jJumlahKeyTyped(evt);
            }
        });
        getContentPane().add(jJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 210, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Penjualan");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jTotalbeli.setBackground(new java.awt.Color(204, 204, 204));
        jTotalbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotalbeliActionPerformed(evt);
            }
        });
        jTotalbeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTotalbeliKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTotalbeliKeyTyped(evt);
            }
        });
        getContentPane().add(jTotalbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 210, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 50, 470, 358));

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
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 68, -1));

        clear_table.setBackground(new java.awt.Color(255, 0, 0));
        clear_table.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        clear_table.setForeground(new java.awt.Color(255, 255, 255));
        clear_table.setText("Kosongkan");
        clear_table.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        clear_table.setSelected(true);
        clear_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clear_tableMouseClicked(evt);
            }
        });
        clear_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_tableActionPerformed(evt);
            }
        });
        getContentPane().add(clear_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 90, -1));

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
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 68, -1));

        btn_refresh.setBackground(new java.awt.Color(51, 255, 255));
        btn_refresh.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_refresh.setSelected(true);
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        getContentPane().add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 68, -1));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Modal");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jHarga.setBackground(new java.awt.Color(204, 204, 204));
        jHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHargaActionPerformed(evt);
            }
        });
        jHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHargaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jHargaKeyTyped(evt);
            }
        });
        getContentPane().add(jHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 210, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 97, -1));

        jId.setBackground(new java.awt.Color(204, 204, 204));
        jId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIdActionPerformed(evt);
            }
        });
        getContentPane().add(jId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Subtotal ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 60, -1));

        jSubtotal.setBackground(new java.awt.Color(204, 204, 204));
        jSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSubtotalActionPerformed(evt);
            }
        });
        getContentPane().add(jSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 180, -1));

        btn_back1.setBackground(new java.awt.Color(255, 204, 204));
        btn_back1.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_back1.setText("Ke Menu");
        btn_back1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_back1.setSelected(true);
        btn_back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back1MouseClicked(evt);
            }
        });
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 68, -1));

        clear_form.setBackground(new java.awt.Color(255, 0, 0));
        clear_form.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        clear_form.setForeground(new java.awt.Color(255, 255, 255));
        clear_form.setText("Clear form");
        clear_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        clear_form.setSelected(true);
        clear_form.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clear_formMouseClicked(evt);
            }
        });
        clear_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_formActionPerformed(evt);
            }
        });
        getContentPane().add(clear_form, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 68, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Modal");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 80, -1));

        jSubmodal.setBackground(new java.awt.Color(204, 204, 204));
        jSubmodal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSubmodalActionPerformed(evt);
            }
        });
        getContentPane().add(jSubmodal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 180, -1));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total Modal");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 100, -1));

        jTotalmodal.setBackground(new java.awt.Color(204, 204, 204));
        jTotalmodal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotalmodalActionPerformed(evt);
            }
        });
        jTotalmodal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTotalmodalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTotalmodalKeyTyped(evt);
            }
        });
        getContentPane().add(jTotalmodal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 210, -1));

        btn_cetak.setBackground(new java.awt.Color(51, 255, 255));
        btn_cetak.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        btn_cetak.setText("Cetak");
        btn_cetak.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.blue));
        btn_cetak.setSelected(true);
        btn_cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cetakMouseClicked(evt);
            }
        });
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 68, -1));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Harga");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jModal.setBackground(new java.awt.Color(204, 204, 204));
        jModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModalActionPerformed(evt);
            }
        });
        jModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jModalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jModalKeyTyped(evt);
            }
        });
        getContentPane().add(jModal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 210, -1));

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
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 70, -1));

        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });
        getContentPane().add(jSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 227, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/transaksi.JPG"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jProfitActionPerformed

    private void jNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNamaActionPerformed

    private void jJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJumlahActionPerformed

    private void jJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJumlahKeyReleased
        // TODO add your handling code here:
        total();
        total_modal();
    }//GEN-LAST:event_jJumlahKeyReleased

    private void jJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJumlahKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jJumlahKeyTyped

    private void jTotalbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotalbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalbeliActionPerformed

    private void jTotalbeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTotalbeliKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalbeliKeyReleased

    private void jTotalbeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTotalbeliKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalbeliKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        jId.setText(table.getValueAt(baris, 0).toString());
        jNama.setText(table.getValueAt(baris, 1).toString());
        jHarga.setText(table.getValueAt(baris, 2).toString());
        jModal.setText(table.getValueAt(baris, 3).toString());
         jJumlah.setText(table.getValueAt(baris, 4).toString());
        jTotalmodal.setText(table.getValueAt(baris, 5).toString());
        jTotalbeli.setText(table.getValueAt(baris, 6).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void clear_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_tableActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clear_tableActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
       Connection conn = Koneksi.getConnection();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("delete from transaksi where id ='" + jId.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                tampilData();
               clear();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } clear();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        new transaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
        // TODO add your handling code here:
        new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_refreshMouseClicked

    private void jHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHargaActionPerformed

    private void jHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHargaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jHargaKeyReleased

    private void jHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHargaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jHargaKeyTyped

    private void jIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jIdActionPerformed

    private void jSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSubtotalActionPerformed

    private void btn_back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back1MouseClicked
        // TODO add your handling code here:
         new home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_back1MouseClicked

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void clear_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_formActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clear_formActionPerformed

    private void jSubmodalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSubmodalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSubmodalActionPerformed

    private void jTotalmodalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotalmodalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalmodalActionPerformed

    private void jTotalmodalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTotalmodalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalmodalKeyReleased

    private void jTotalmodalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTotalmodalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalmodalKeyTyped

    private void btn_cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cetakMouseClicked

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        // TODO add your handling code here:
         try{
            String file = "/laporan/lap_transaksi.jasper";
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            HashMap param = new HashMap();
            
            param.put("total",jSubtotal.getText());
            param.put("modal",jSubmodal.getText());
            param.put("profit",jProfit.getText());
            
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),param,Koneksi.getConnection());
            JasperViewer.viewReport(print, false);
            
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | JRException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void clear_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clear_tableMouseClicked
        // TODO add your handling code here:
         try{
            String clear = "TRUNCATE `transaksi`";
            Connection connect = Koneksi.getConnection();
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();
//            keranjang();
            
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            tampilData();
            totalnya();
            jSubtotal.setText(null);
            jSubmodal.setText(null);
            jProfit.setText(null);
        }
    }//GEN-LAST:event_clear_tableMouseClicked

    private void jModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jModalActionPerformed

    private void jModalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jModalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jModalKeyReleased

    private void jModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jModalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jModalKeyTyped

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchActionPerformed

    private void clear_formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clear_formMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clear_formMouseClicked

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton clear_form;
    private javax.swing.JButton clear_table;
    public javax.swing.JTextField jHarga;
    public javax.swing.JTextField jId;
    public javax.swing.JTextField jJumlah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JTextField jModal;
    public javax.swing.JTextField jNama;
    public javax.swing.JTextField jProfit;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jSearch;
    public javax.swing.JTextField jSubmodal;
    public javax.swing.JTextField jSubtotal;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTotalbeli;
    public javax.swing.JTextField jTotalmodal;
    // End of variables declaration//GEN-END:variables
}
