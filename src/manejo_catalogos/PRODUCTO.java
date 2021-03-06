/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_catalogos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uso_bd.CONEXION;

/**
 *
 * @author kevin
 */
public class PRODUCTO extends javax.swing.JInternalFrame {

    /**
     * Creates new form PRODUCTO
     */
    CONEXION conect;
    int a; //de activo
    ArrayList <String> idTipo;
    ArrayList <String> idMat;
    
    public PRODUCTO() throws SQLException {
        Locale.setDefault(new Locale("en", "US"));
        initComponents();
        idTipo = new ArrayList();
        idMat = new ArrayList();
        conect = new CONEXION();
        conect.CONECTAR();
        limpiar();
        this.a=1;
        updateGrid("Select * from producto WHERE activo='"+this.a+"';");
        this.txtIDP.setEditable(false);
        this.setComboModel(this.comboTP, "Select * From tipo WHERE activo=1 ORDER BY descripcion ASC", "id_tipo","descripcion", this.idTipo);
        this.setComboModel(this.comboMATP, "Select * From material WHERE activo=1 ORDER BY descripcion ASC", "id_material", "descripcion",this.idMat);
        conect.CERRAR();
    }
    
    private void setComboModel(JComboBox cb, String sql, String id, String desc, ArrayList l) throws SQLException{
        this.conect.CONECTAR();
        ResultSet rs= this.conect.CONSULTAR(sql);
        l.clear();
        cb.removeAllItems();
        while(rs.next()){
            cb.addItem((String) rs.getString(desc));
            l.add((String) rs.getString(id));
        }
        this.conect.CERRAR();
    
    }
    private void checkGrid(){ //Para saber que mostrar en el Grid
        if(this.a==0){
            updateGrid("select * from producto;");
        } else {
            updateGrid("select * from producto WHERE activo=1;");
        }
    }
    public void limpiar() throws SQLException{
            int auto=0;
            conect.CONECTAR();
            ResultSet rs=conect.CONSULTAR("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES\n" +
"WHERE table_name = 'producto'");
            while(rs.next()){
                auto = Integer.parseInt(rs.getString("auto_increment"));
            }
            this.txtIDP.setText(Integer.toString(auto));
            this.txtDescP.setText("");
             this.setComboModel(this.comboTP, "Select * From tipo WHERE activo=1 ORDER BY descripcion ASC", "id_tipo","descripcion", this.idTipo);
        this.setComboModel(this.comboMATP, "Select * From material WHERE activo=1 ORDER BY descripcion ASC", "id_material", "descripcion",this.idMat);
            this.txtPrecioP.setText("");
            this.txtCantP.setText("");
            this.activoP.setSelected(false);
            conect.CERRAR();
        }
    
    private void updateGrid(String sql){
        conect.CONECTAR();
        try {
            //String[] titulos = {"CEDULA","NOMBRE","EDAD", "SEXO","TELEFONO","EMAIL", "ACTIVO"};
            
            DefaultTableModel modelo = (DefaultTableModel) this.tDatosP.getModel();
            int f = this.tDatosP.getRowCount();
            while(f>0){
                modelo.removeRow(0);
                f--;
            }
            ResultSet rs = conect.CONSULTAR(sql);
            String[] fila = new String[7];
            
            while(rs.next()){
                
                String activo= (String) rs.getString("activo");
                    fila[0] = (String) rs.getString("id_producto");
                    fila[1] = (String) rs.getString("descripcion");
                    fila[2] = (String) rs.getString("id_tipo");  
                    fila[3] = (String) rs.getString("id_material"); 
                    fila[4] = (String) rs.getString("precio");
                    fila[5] = (String) rs.getString("cantidad");  
                    fila[6] = activo.equals("1")?"ACTIVO":"INACTIVO";
                    modelo.addRow(fila);
            }
            this.tDatosP.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conect.CERRAR();
    }
    
    private boolean checkProducto(){ 
        
        try {
        //Para ver si el producto ya existe en la bd
        
            boolean h=false; //Si se hallo coincidencia
            conect.CONECTAR();
            ResultSet rs = conect.CONSULTAR("Select * from producto WHERE descripcion='"+this.txtDescP.getText().toUpperCase()+"';");
            while(rs.next()){
                h=true;
            }
            conect.CERRAR();
            return h;
            
        } catch (SQLException ex) {
            Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIDP = new javax.swing.JTextField();
        txtDescP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioP = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCantP = new javax.swing.JFormattedTextField();
        activoP = new javax.swing.JCheckBox();
        comboTP = new javax.swing.JComboBox();
        comboMATP = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tDatosP = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setClosable(true);
        setTitle("CATALOGO DE PRODUCTOS");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Ingreso de nuevo producto");

        jLabel1.setText("ID Producto:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Tipo de producto:");

        jLabel4.setText("Material:");

        jLabel6.setText("Precio:");

        txtPrecioP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel7.setText("Cantidad:");

        activoP.setText("Activo");

        comboTP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTP.setToolTipText("");

        comboMATP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tDatosP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID PRODUCTO", "DESCRIPCION", "ID TIPO PRODUCTO", "ID MATERIAL", "PRECIO", "CANTIDAD", "ACTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDatosP.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tDatosP.getTableHeader().setReorderingAllowed(false);
        tDatosP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tDatosP);
        if (tDatosP.getColumnModel().getColumnCount() > 0) {
            tDatosP.getColumnModel().getColumn(0).setResizable(false);
            tDatosP.getColumnModel().getColumn(0).setPreferredWidth(100);
            tDatosP.getColumnModel().getColumn(1).setResizable(false);
            tDatosP.getColumnModel().getColumn(1).setPreferredWidth(350);
            tDatosP.getColumnModel().getColumn(2).setResizable(false);
            tDatosP.getColumnModel().getColumn(2).setPreferredWidth(130);
            tDatosP.getColumnModel().getColumn(3).setResizable(false);
            tDatosP.getColumnModel().getColumn(3).setPreferredWidth(100);
            tDatosP.getColumnModel().getColumn(4).setResizable(false);
            tDatosP.getColumnModel().getColumn(4).setPreferredWidth(100);
            tDatosP.getColumnModel().getColumn(5).setResizable(false);
            tDatosP.getColumnModel().getColumn(5).setPreferredWidth(100);
            tDatosP.getColumnModel().getColumn(6).setResizable(false);
            tDatosP.getColumnModel().getColumn(6).setPreferredWidth(70);
        }

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnNew.setText("Nuevo");
        btnNew.setToolTipText("");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mostrar.png"))); // NOI18N
        btnShow.setText("Mostrar todos");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refrescar.png"))); // NOI18N
        btnRefresh.setText("Actualizar tabla");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(activoP)
                                .addComponent(txtDescP)
                                .addComponent(txtPrecioP)
                                .addComponent(comboTP, 0, 192, Short.MAX_VALUE)
                                .addComponent(comboMATP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIDP))
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtDescP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(comboTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMATP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(activoP)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        conect.CONECTAR();
        try {
            limpiar();
        } catch (SQLException ex) {
            Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conect.CERRAR();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        boolean sql;//si se realiza bien la sentencia
        conect.CONECTAR();
        if(!txtIDP.getText().equals("")){
            sql=conect.EJECUTAR("DELETE FROM producto WHERE id_producto="+txtIDP.getText());
            if(sql){
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente!");
            }    
            
        } else {
            JOptionPane.showMessageDialog(null," Campo del codigo vacío!");
        }
        try {
            limpiar();
        } catch (SQLException ex) {
            Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conect.CERRAR();
        this.checkGrid();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        int f = this.tDatosP.getSelectedRow();
        if(f!=-1){
            boolean activo = this.tDatosP.getValueAt(f, 6).equals("ACTIVO");
            if(!activo){
                try {
                    limpiar();
                } catch (SQLException ex) {
                    Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.txtIDP.setEditable(true);
            }
        }
        if(this.a==1){
            this.a=0;
            this.btnShow.setText("Mostrar solo activos");
        } else if(this.a==0){
            this.a=1;
            this.btnShow.setText("Mostrar todos");
        }
        this.checkGrid();
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boolean sql;//Si se ejecuta bien la sentencia o no.
        if(Integer.parseInt(this.txtCantP.getText()) >=0 && Double.parseDouble(this.txtPrecioP.getText().replace(",", "."))>=0){
            if(this.checkProducto() && this.tDatosP.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null,"Un producto ya existe con ese nombre. Imposible guardar o modificar nombre de producto!");
            } else {
                conect.CONECTAR();
                int activo = this.activoP.isSelected()?1:0;
                if(this.tDatosP.getSelectedRow()==-1){
                    if(!txtIDP.getText().equals("")&&!this.txtDescP.getText().equals("") && !this.txtCantP.getText().equals("") && !this.txtPrecioP.getText().equals("")){
                        sql=conect.EJECUTAR("INSERT INTO producto(descripcion, id_tipo, id_material, precio, cantidad, activo) VALUES('"+txtDescP.getText().toUpperCase()+"', '"+this.idTipo.get(this.comboTP.getSelectedIndex())+"', '"+this.idMat.get(this.comboMATP.getSelectedIndex())+"', '"+this.txtPrecioP.getText().replace(",", ".")+"', "+this.txtCantP.getText()+", "+activo+");");
                        if(sql){
                             JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                            try {
                                limpiar();
                            } catch (SQLException ex) {
                                Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Campos sin llenar!");
                    }
                } else {
                    if(this.txtDescP.getText().equals("") || this.txtPrecioP.getText().equals("") || this.txtCantP.getText().equals("") || this.txtPrecioP.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Datos a modificar incompletos");
                    } else{
                        conect.CONECTAR();
                        sql=conect.EJECUTAR("UPDATE producto SET descripcion='"+this.txtDescP.getText().toUpperCase()+"', precio='"+this.txtPrecioP.getText()+"', cantidad='"+this.txtCantP.getText()+"', activo='"+activo+"', id_tipo='"+idTipo.get(this.comboTP.getSelectedIndex())+"', id_material='"+idMat.get(this.comboMATP.getSelectedIndex())+"' WHERE id_producto='"+this.txtIDP.getText()+"';");
                        conect.CERRAR();      
                        if(sql){
                            JOptionPane.showMessageDialog(null,"Datos modificados correctamente!");
                            try {
                                this.limpiar();
                            } catch (SQLException ex) {
                                Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }


                    }
                }
                conect.CERRAR();
            }
                
            this.checkGrid();
        
        } else {
            JOptionPane.showMessageDialog(null, "Revise los datos. Uno o más campos exceden su límite de caracteres!");
        } 
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tDatosPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosPMouseClicked
        // TODO add your handling code here:
        this.txtIDP.setEditable(false);
        int fila = this.tDatosP.getSelectedRow();
        this.txtIDP.setText((String) this.tDatosP.getValueAt(fila, 0));
        this.txtDescP.setText((String) this.tDatosP.getValueAt(fila, 1));
        this.txtPrecioP.setText((String) this.tDatosP.getValueAt(fila, 4));
        this.txtCantP.setText((String) this.tDatosP.getValueAt(fila, 5));
        this.activoP.setSelected((String) this.tDatosP.getValueAt(fila, 6)=="ACTIVO");
        int x=0, y=0;
        for (int i = 0; i < idTipo.size(); i++) {
            if(idTipo.get(i).equals((String) this.tDatosP.getValueAt(fila, 2))){
                x=i;//Para el lugar de index en donde se encuentra en la lista
            }
        }
        for (int i = 0; i < idMat.size(); i++) {
            if(idMat.get(i).equals((String) this.tDatosP.getValueAt(fila, 3))){
                y=i;//Para el lugar de index en donde se encuentra en la lista
            }
        }
        this.comboTP.setSelectedIndex(x);
        this.comboMATP.setSelectedIndex(y);
    }//GEN-LAST:event_tDatosPMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            // TODO add your handling code here:
            this.limpiar();
        } catch (SQLException ex) {
            Logger.getLogger(PRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.checkGrid();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activoP;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox comboMATP;
    private javax.swing.JComboBox comboTP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tDatosP;
    private javax.swing.JFormattedTextField txtCantP;
    private javax.swing.JTextField txtDescP;
    private javax.swing.JTextField txtIDP;
    private javax.swing.JFormattedTextField txtPrecioP;
    // End of variables declaration//GEN-END:variables
}
