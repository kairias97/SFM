/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manejo_catalogos;

import uso_bd.CONEXION;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author labs
 */
public class MATERIALES extends javax.swing.JInternalFrame {

    /**
     * Creates new form MATERIALES
     */
    int a; //de activo
    CONEXION conect;
    
    public MATERIALES() {
        initComponents();
        conect = new CONEXION();
        limpiar();
        a=1;
        updateGrid("Select * from material WHERE activo='"+this.a+"';");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void limpiar(){
        this.txtIDTMAT.setText("");
        this.txtDescripcionTMAT.setText("");
        this.activoTMAT.setSelected(false);
        this.txtIDTMAT.requestFocus();
    
    }
    public void updateGrid(String sql){
        conect.CONECTAR();
        try {
            
            //String[] titulos = {"CEDULA","NOMBRE","EDAD", "SEXO","TELEFONO","EMAIL", "ACTIVO"};
            
            DefaultTableModel modelo = (DefaultTableModel) this.tDatosTMAT.getModel();
            int f = this.tDatosTMAT.getRowCount();
            while(f>0){
                modelo.removeRow(0);
                f--;
            }
            ResultSet rs = conect.CONSULTAR(sql);
            String[] fila = new String[3];
            
            while(rs.next()){
                
                String activo= (String) rs.getString("activo");
                fila[0] = (String) rs.getString("id_material");//Verificar
                fila[1] = (String) rs.getString("descripcion"); 
                fila[2] = activo.equals("1")?"ACTIVO":"INACTIVO";
                modelo.addRow(fila);
                    
            }
            this.tDatosTMAT.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conect.CERRAR();
        
    
    }
    
    private void checkGrid(){ //Para saber que mostrar en el Grid
        if(this.a==0){
            updateGrid("select * from material;");
        } else {
            updateGrid("select * from material WHERE activo=1;");
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

        showTMAT = new javax.swing.JButton();
        deleteTMAT = new javax.swing.JButton();
        newTMAT = new javax.swing.JButton();
        saveTMAT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tDatosTMAT = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionTMAT = new javax.swing.JTextArea();
        txtIDTMAT = new javax.swing.JTextField();
        activoTMAT = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("CATALOGO DE MATERIALES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1461722446_camping-nature-08.png"))); // NOI18N

        showTMAT.setText("Mostrar todos");
        showTMAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showTMATMouseClicked(evt);
            }
        });

        deleteTMAT.setText("Eliminar");
        deleteTMAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTMATActionPerformed(evt);
            }
        });

        newTMAT.setText("Nuevo");
        newTMAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTMATActionPerformed(evt);
            }
        });

        saveTMAT.setText("Guardar");
        saveTMAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTMATActionPerformed(evt);
            }
        });

        tDatosTMAT.setAutoCreateRowSorter(true);
        tDatosTMAT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID TIPO", "DESCRIPCION", "ACTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDatosTMAT.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tDatosTMAT.getTableHeader().setReorderingAllowed(false);
        tDatosTMAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosTMATMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tDatosTMAT);
        if (tDatosTMAT.getColumnModel().getColumnCount() > 0) {
            tDatosTMAT.getColumnModel().getColumn(0).setResizable(false);
            tDatosTMAT.getColumnModel().getColumn(0).setPreferredWidth(80);
            tDatosTMAT.getColumnModel().getColumn(1).setResizable(false);
            tDatosTMAT.getColumnModel().getColumn(1).setPreferredWidth(300);
            tDatosTMAT.getColumnModel().getColumn(2).setResizable(false);
            tDatosTMAT.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        txtDescripcionTMAT.setColumns(20);
        txtDescripcionTMAT.setRows(5);
        txtDescripcionTMAT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionTMATFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcionTMAT);

        txtIDTMAT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDTMATFocusLost(evt);
            }
        });

        jLabel4.setText("Activo:");

        jLabel3.setText("Descripción:");

        jLabel2.setText("ID Tipo:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Materiales de Muebles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(activoTMAT)
                                            .addComponent(txtIDTMAT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newTMAT)
                                .addGap(18, 18, 18)
                                .addComponent(saveTMAT)
                                .addGap(18, 18, 18)
                                .addComponent(deleteTMAT)
                                .addGap(18, 18, 18)
                                .addComponent(showTMAT))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDTMAT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(activoTMAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newTMAT)
                    .addComponent(saveTMAT)
                    .addComponent(deleteTMAT)
                    .addComponent(showTMAT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showTMATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showTMATMouseClicked
        // TODO add your handling code here:
        int f = this.tDatosTMAT.getSelectedRow();
        if(f!=-1){
            boolean activo = this.tDatosTMAT.getValueAt(f, 2).equals("ACTIVO");
            if(!activo){
                limpiar();
                this.txtIDTMAT.setEditable(true);
            }
        }
        if(this.a==1){
            this.a=0;
            this.showTMAT.setText("Mostrar solo activos");
        } else if(this.a==0){
            this.a=1;
            this.showTMAT.setText("Mostrar todos");
        }
        this.checkGrid();
    }//GEN-LAST:event_showTMATMouseClicked

    private void deleteTMATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTMATActionPerformed
        // TODO add your handling code here:
        conect.CONECTAR();
        if(!this.txtIDTMAT.getText().equals("")){
            conect.EJECUTAR("DELETE FROM material WHERE id_material='"+this.txtIDTMAT.getText()+"';");
            JOptionPane.showMessageDialog(null, "Datos eliminados correctamente!");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null," Campo del codigo vacío!");
        }
        conect.EJECUTAR("");
        limpiar();
        conect.CERRAR();
        this.checkGrid();
        
    }//GEN-LAST:event_deleteTMATActionPerformed

    private void newTMATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTMATActionPerformed
        // TODO add your handling code here:
        conect.CONECTAR();
        this.txtIDTMAT.setEditable(true);
        limpiar();
        conect.CERRAR();
    }//GEN-LAST:event_newTMATActionPerformed

    private void saveTMATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTMATActionPerformed
        // TODO add your handling code here:
        if(this.txtIDTMAT.getText().length() <= 5 && this.txtDescripcionTMAT.getText().length() <= 45){
            
            conect.CONECTAR();
            boolean sql;
            int activo = this.activoTMAT.isSelected()?1:0;

            if(this.tDatosTMAT.getSelectedRow()==-1){
                if(!txtIDTMAT.getText().equals("") && !this.txtDescripcionTMAT.getText().equals("")){
                    sql=conect.EJECUTAR("INSERT INTO material(id_material, descripcion, activo) VALUES('"+this.txtIDTMAT.getText()+"', '"+this.txtDescripcionTMAT.getText()+"', '"+activo+"');");
                    if(sql){
                        JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                        limpiar();
                        this.txtIDTMAT.setEditable(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Campos sin llenar!");
                }
            } else {
                if(this.txtIDTMAT.getText().equals("") || this.txtDescripcionTMAT.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Datos a modificar incompletos");
                } else{
                    conect.CONECTAR();
                    sql=conect.EJECUTAR("UPDATE material SET descripcion='"+this.txtDescripcionTMAT.getText()+"', activo='"+activo+"' WHERE id_material='"+this.txtIDTMAT.getText()+"';");
                    conect.CERRAR();
                    if(sql){
                        JOptionPane.showMessageDialog(null,"Datos modificados correctamente!");
                        this.limpiar();
                        this.txtIDTMAT.setEditable(true);
                    }
                }
            }

            conect.CERRAR();
            this.checkGrid();
        } else {
            JOptionPane.showMessageDialog(null, "Revise los datos. Uno o más campos exceden su límite de caracteres!");
        }
    }//GEN-LAST:event_saveTMATActionPerformed

    private void txtIDTMATFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDTMATFocusLost
        // TODO add your handling code here:
        /*
        if(this.txtIDTMAT.getText().length()>5){
            JOptionPane.showMessageDialog(null, "El codigo no puede exceder los 5 caracteres!");
            this.txtIDTMAT.requestFocus();
        }*/
    }//GEN-LAST:event_txtIDTMATFocusLost

    private void txtDescripcionTMATFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionTMATFocusLost
        // TODO add your handling code here:
        /*if(this.txtDescripcionTMAT.getText().length() > 60){
            JOptionPane.showMessageDialog(null, "La descripcion del material no puede exceder los 60 caracteres!");
            this.txtDescripcionTMAT.requestFocus();
        }*/
    }//GEN-LAST:event_txtDescripcionTMATFocusLost

    private void tDatosTMATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosTMATMouseClicked
        // TODO add your handling code here:
        this.txtIDTMAT.setEditable(false);
        int fila = this.tDatosTMAT.getSelectedRow();
        boolean activo = this.tDatosTMAT.getValueAt(fila, 2).equals("ACTIVO");
        this.txtIDTMAT.setText((String) this.tDatosTMAT.getValueAt(fila, 0));
        this.txtDescripcionTMAT.setText((String) this.tDatosTMAT.getValueAt(fila, 1));
        this.activoTMAT.setSelected(activo);
    }//GEN-LAST:event_tDatosTMATMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activoTMAT;
    private javax.swing.JButton deleteTMAT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newTMAT;
    private javax.swing.JButton saveTMAT;
    private javax.swing.JButton showTMAT;
    private javax.swing.JTable tDatosTMAT;
    private javax.swing.JTextArea txtDescripcionTMAT;
    private javax.swing.JTextField txtIDTMAT;
    // End of variables declaration//GEN-END:variables
}
