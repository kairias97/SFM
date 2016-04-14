/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafacturacionmuebles;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class CLIENTE extends javax.swing.JInternalFrame {

    /**
     * Creates new form CLIENTE
     */
    CONEXION conect;
    int a; //de activo
    boolean valido;//Para validar si la info es correcta
    public CLIENTE() {
        initComponents();
        conect = new CONEXION();
        conect.CONECTAR();
        limpiar();
        a=1;
        updateGrid("Select * from clientes WHERE activo='"+this.a+"';");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        valido=false;
        
    }
    
    private void updateGrid(String sql){
        conect.CONECTAR();
        try {
            
            //String[] titulos = {"CEDULA","NOMBRE","EDAD", "SEXO","TELEFONO","EMAIL", "ACTIVO"};
            
            DefaultTableModel modelo = (DefaultTableModel) this.tDatos.getModel();
            int f = this.tDatos.getRowCount();
            while(f>0){
                modelo.removeRow(0);
                f--;
            }
            ResultSet rs = conect.CONSULTAR(sql);
            String[] fila = new String[7];
            
            while(rs.next()){
                
                String sexo= (String) rs.getString("sexo");
                String activo= (String) rs.getString("activo");
                    fila[0] = (String) rs.getString("id_cliente");
                    fila[1] = (String) rs.getString("nombre");
                    fila[2] = (String) rs.getString("edad");  
                    fila[3] = sexo.equals("1")?"HOMBRE":"MUJER";
                    fila[4] = (String) rs.getString("telefono");
                    fila[5] = (String) rs.getString("email");  
                    fila[6] = activo.equals("1")?"ACTIVO":"INACTIVO";
                    modelo.addRow(fila);
            }
            this.tDatos.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conect.CERRAR();
        
    
        }
    
    private void checkGrid(){ //Para saber que mostrar en el Grid
        if(this.a==0){
            updateGrid("select * from clientes;");
        } else {
            updateGrid("select * from clientes WHERE activo=1;");
        }
    }
        public void limpiar(){
            this.idcliente.setText("");
            this.nombre.setText("");
            this.edad.setText("");
            this.sexo.setSelectedIndex(0);
            this.telefono.setText("");
            this.email.setText("");
            this.activo.setSelected(false);
        }
        private boolean checkDatos(){
            try{
            int edad = Integer.parseInt(this.edad.getText());
            if(edad < 18 || edad > 135){
                return false;
            } else{
                return true;
            }
            } catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null,"Edad inválida. Favor ingrese un número");
                return false;
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

        nombre = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox();
        telefono = new javax.swing.JTextField();
        edad = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        newDC = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tDatos = new javax.swing.JTable();
        saveDC = new javax.swing.JButton();
        deleteDC = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        activo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("CATALOGO DE CLIENTES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFocusLost(evt);
            }
        });

        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonoFocusLost(evt);
            }
        });
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });

        edad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edadFocusLost(evt);
            }
        });

        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        newDC.setText("Nuevo");
        newDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDCActionPerformed(evt);
            }
        });

        tDatos.setAutoCreateRowSorter(true);
        tDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CEDULA", "NOMBRE", "EDAD", "SEXO", "TELEFONO", "EMAIL", "ACTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tDatos.getTableHeader().setReorderingAllowed(false);
        tDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tDatos);
        if (tDatos.getColumnModel().getColumnCount() > 0) {
            tDatos.getColumnModel().getColumn(0).setResizable(false);
            tDatos.getColumnModel().getColumn(0).setPreferredWidth(120);
            tDatos.getColumnModel().getColumn(1).setResizable(false);
            tDatos.getColumnModel().getColumn(1).setPreferredWidth(280);
            tDatos.getColumnModel().getColumn(2).setResizable(false);
            tDatos.getColumnModel().getColumn(2).setPreferredWidth(90);
            tDatos.getColumnModel().getColumn(3).setResizable(false);
            tDatos.getColumnModel().getColumn(3).setPreferredWidth(90);
            tDatos.getColumnModel().getColumn(4).setResizable(false);
            tDatos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tDatos.getColumnModel().getColumn(5).setResizable(false);
            tDatos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tDatos.getColumnModel().getColumn(6).setResizable(false);
            tDatos.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        saveDC.setText("Guardar");
        saveDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDCActionPerformed(evt);
            }
        });

        deleteDC.setText("Eliminar");
        deleteDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDCActionPerformed(evt);
            }
        });

        btnMostrar.setText("Mostrar todos");
        btnMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMostrarMouseClicked(evt);
            }
        });

        jLabel1.setText("Cédula:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Ingreso de datos de clientes");

        jLabel2.setText("Nombre: ");

        jLabel6.setText("Activo:");

        jLabel3.setText("Teléfono: ");

        jLabel4.setText("Email:");

        jLabel7.setText("Edad:");

        idcliente.setEditable(false);
        idcliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                idclienteFocusLost(evt);
            }
        });
        idcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idclienteActionPerformed(evt);
            }
        });

        jLabel8.setText("Sexo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(activo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(idcliente, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                            .addComponent(nombre)
                                            .addComponent(telefono)
                                            .addComponent(email)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(33, 33, 33))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(edad)
                                                .addGap(181, 181, 181))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newDC)
                                .addGap(18, 18, 18)
                                .addComponent(saveDC)
                                .addGap(18, 18, 18)
                                .addComponent(deleteDC)
                                .addGap(18, 18, 18)
                                .addComponent(btnMostrar))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(activo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDC)
                    .addComponent(saveDC)
                    .addComponent(deleteDC)
                    .addComponent(btnMostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void edadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edadFocusLost
        // TODO add your handling code here:

        try{
            int edad = Integer.parseInt(this.edad.getText());
            if(edad < 18){
                JOptionPane.showMessageDialog(null, "Edad inválida. El cliente debe ser mayor de 18 años!");
                this.valido=false;
                this.edad.requestFocus();
            } else if(edad > 135){
                JOptionPane.showMessageDialog(null, "Edad inválida. Los clientes no suelen vivir tanto!");
                this.valido=false;
                this.edad.requestFocus();
            } else{
                this.valido=true;
            }
        } catch(NumberFormatException n){
            JOptionPane.showMessageDialog(null,"Edad inválida. Favor ingrese un número");
            this.valido=false;
            this.edad.requestFocus();
        }
    }//GEN-LAST:event_edadFocusLost

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void newDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDCActionPerformed
        // TODO add your handling code here:
        conect.CONECTAR();
        this.idcliente.setEditable(true);
        limpiar();
        conect.CERRAR();
    }//GEN-LAST:event_newDCActionPerformed

    private void tDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosMouseClicked
        // TODO add your handling code here:
        this.idcliente.setEditable(false);
        int fila = this.tDatos.getSelectedRow();
        this.idcliente.setText((String) this.tDatos.getValueAt(fila, 0));
        this.nombre.setText((String) this.tDatos.getValueAt(fila, 1));
        this.edad.setText((String) this.tDatos.getValueAt(fila, 2));
        this.sexo.setSelectedIndex((String) this.tDatos.getValueAt(fila, 3)=="HOMBRE"?0:1);
        this.telefono.setText((String) this.tDatos.getValueAt(fila, 4));
        this.email.setText((String) this.tDatos.getValueAt(fila, 5));
        this.activo.setSelected((String) this.tDatos.getValueAt(fila, 6)=="ACTIVO"?true:false);
    }//GEN-LAST:event_tDatosMouseClicked

    private void saveDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDCActionPerformed
        // TODO add your handling code here:
        boolean sql;//Si se ejecuta bien la sentencia o no.
        if(this.checkDatos() && this.valido){
            conect.CONECTAR();
            int activo = this.activo.isSelected()?1:0;
            String sexo = this.sexo.getSelectedIndex()==0?"1":"0";
            if(this.tDatos.getSelectedRow()==-1){
                if(!idcliente.getText().equals("")&&!nombre.getText().equals("") && !telefono.getText().equals("") && !email.getText().equals("") && !edad.getText().equals("")){
                    sql=conect.EJECUTAR("INSERT INTO clientes(id_cliente, nombre, telefono, email, activo, edad, sexo) VALUES('"+this.idcliente.getText()+"', '"+nombre.getText()+"', '"+telefono.getText()+"', '"+email.getText()+"', '"+activo+"', "+this.edad.getText()+", "+sexo+");");
                    if(sql){
                         JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                    }
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Campos sin llenar!");
                }
            } else {
                if(this.nombre.getText().equals("") || this.telefono.getText().equals("") || this.email.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Datos a modificar incompletos");
                } else{
                    conect.CONECTAR();
                    sql=conect.EJECUTAR("UPDATE clientes SET nombre='"+this.nombre.getText()+"', telefono='"+this.telefono.getText()+"', email='"+this.email.getText()+"', activo='"+activo+"', edad="+edad.getText()+", sexo="+sexo+" WHERE id_cliente='"+this.idcliente.getText()+"';");
                    conect.CERRAR();
                    this.limpiar();
                    if(sql){
                        JOptionPane.showMessageDialog(null,"Datos modificados correctamente!");
                    }
                    

                }
            }
            conect.CERRAR();
            this.checkGrid();
        
        } else {
            JOptionPane.showMessageDialog(null, "TADAIMA");
        } 
        

    }//GEN-LAST:event_saveDCActionPerformed

    private void deleteDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDCActionPerformed
        // TODO add your handling code here:
        boolean sql;//si se realiza bien la sentencia
        conect.CONECTAR();
        if(!idcliente.getText().equals("")){
            sql=conect.EJECUTAR("DELETE FROM clientes WHERE id_cliente="+idcliente.getText());
            if(sql){
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente!");
            }    
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null," Campo del codigo vacío!");
        }
        limpiar();
        conect.CERRAR();
        this.checkGrid();
    }//GEN-LAST:event_deleteDCActionPerformed

    private void btnMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarMouseClicked
        // TODO add your handling code here:
        if(this.a==1){
            this.a=0;
            this.btnMostrar.setText("Mostrar solo activos");
        } else if(this.a==0){
            this.a=1;
            this.btnMostrar.setText("Mostrar todos");
        }
        this.checkGrid();

    }//GEN-LAST:event_btnMostrarMouseClicked

    private void idclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idclienteActionPerformed

    private void idclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idclienteFocusLost
        // TODO add your handling code here:
        if(this.idcliente.getText().length() > 16){
            JOptionPane.showMessageDialog(null, "Su ID de cedula no debe exceder los 16 caracteres!");
            this.valido=false;
            this.idcliente.requestFocus();
        } else{
            this.valido=true;
        }
    }//GEN-LAST:event_idclienteFocusLost

    private void nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFocusLost
        // TODO add your handling code here:
        if(this.nombre.getText().length() > 60){
            JOptionPane.showMessageDialog(null, "El nombre completo no puede exceder los 60 caracteres!");
            this.valido=false;
            this.nombre.requestFocus();
        } else{
            this.valido=true;
        }
    }//GEN-LAST:event_nombreFocusLost

    private void telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonoFocusLost
        // TODO add your handling code here:
        if(this.telefono.getText().length() > 8 || this.telefono.getText().length() < 8){
            JOptionPane.showMessageDialog(null, "La longitud del telefono debe de ser 8 caracteres!");
            if(!this.nombre.getText().equals("") || !this.email.getText().equals("") || !!this.edad.getText().equals("")){
                this.telefono.requestFocus();
            } 
            this.valido=false;
        } else{
            try{
                int t = Integer.parseInt(this.telefono.getText());
                this.valido=true;
            } catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Error! El n° de telefono no puede contener letras!");
                this.valido=false;
                this.telefono.setText("");
            }
            
        }
    }//GEN-LAST:event_telefonoFocusLost

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        // TODO add your handling code here:
        if(this.email.getText().length() > 50){
            JOptionPane.showMessageDialog(null, "La longitud del correo no puede ser mayor a 50 caracteres!");
            this.valido=false;
            this.email.requestFocus();
        } else{
            this.valido=true;
        }
    }//GEN-LAST:event_emailFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activo;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton deleteDC;
    private javax.swing.JTextField edad;
    private javax.swing.JTextField email;
    private javax.swing.JTextField idcliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton newDC;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton saveDC;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTable tDatos;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
