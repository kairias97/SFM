/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_catalogos;

import java.net.URL;
import uso_bd.CONEXION;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
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
        this.txtIDCliente.setFocusLostBehavior(JFormattedTextField.PERSIST);
        this.txtTlfDC.setFocusLostBehavior(JFormattedTextField.PERSIST);
        this.txtEdadDC.setFocusLostBehavior(JFormattedTextField.PERSIST);
        conect = new CONEXION();
        conect.CONECTAR();
        limpiar();
        a=1;
        updateGrid("Select * from clientes WHERE activo='"+this.a+"' ORDER BY nombre ASC");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.txtIDCliente.setEditable(true);
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
            updateGrid("select * from clientes ORDER BY nombre ASC;");
        } else {
            updateGrid("select * from clientes WHERE activo=1 ORDER BY nombre ASC;");
        }
    }
        public void limpiar(){
            
            this.txtIDCliente.setText("");
            this.nombre.setText("");
            this.txtEdadDC.setText("");
            this.sexo.setSelectedIndex(0);
            this.txtTlfDC.setText("");
            this.email.setText("");
            this.activo.setSelected(false);
            this.txtIDCliente.requestFocus();
            
        }
        /*
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
            
        }*/
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
        jLabel3 = new javax.swing.JLabel();
        activo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEdadDC = new javax.swing.JFormattedTextField();
        txtTlfDC = new javax.swing.JFormattedTextField();
        txtIDCliente = new javax.swing.JFormattedTextField();
        btnRefresh = new javax.swing.JButton();

        setClosable(true);
        setTitle("CATALOGO DE CLIENTES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N

        nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFocusLost(evt);
            }
        });

        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });

        newDC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        newDC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
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
            tDatos.getColumnModel().getColumn(2).setPreferredWidth(70);
            tDatos.getColumnModel().getColumn(3).setResizable(false);
            tDatos.getColumnModel().getColumn(3).setPreferredWidth(70);
            tDatos.getColumnModel().getColumn(4).setResizable(false);
            tDatos.getColumnModel().getColumn(4).setPreferredWidth(90);
            tDatos.getColumnModel().getColumn(5).setResizable(false);
            tDatos.getColumnModel().getColumn(5).setPreferredWidth(200);
            tDatos.getColumnModel().getColumn(6).setResizable(false);
            tDatos.getColumnModel().getColumn(6).setPreferredWidth(70);
        }

        saveDC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveDC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        saveDC.setText("Guardar");
        saveDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDCActionPerformed(evt);
            }
        });

        deleteDC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deleteDC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        deleteDC.setText("Eliminar");
        deleteDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDCActionPerformed(evt);
            }
        });

        btnMostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mostrar.png"))); // NOI18N
        btnMostrar.setText("Mostrar todos");
        btnMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMostrarMouseClicked(evt);
            }
        });
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cédula:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Ingreso de datos de clientes");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nombre: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Teléfono: ");

        activo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        activo.setText("Activo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Edad:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Sexo:");

        txtEdadDC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        txtTlfDC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        try {
            txtIDCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtIDCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDClienteActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refrescar.png"))); // NOI18N
        btnRefresh.setText("Actualizar Tabla");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEdadDC, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtIDCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTlfDC, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(newDC)
                            .addGap(18, 18, 18)
                            .addComponent(saveDC)
                            .addGap(18, 18, 18)
                            .addComponent(deleteDC)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activo)
                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTlfDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEdadDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(activo)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDC)
                    .addComponent(saveDC)
                    .addComponent(deleteDC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrar)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void newDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDCActionPerformed
        // TODO add your handling code here:
        conect.CONECTAR();
        this.txtIDCliente.setEditable(true);
        limpiar();
        conect.CERRAR();
    }//GEN-LAST:event_newDCActionPerformed

    private void tDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosMouseClicked
        // TODO add your handling code here:
        this.txtIDCliente.setEditable(false);
        int fila = this.tDatos.getSelectedRow();
        this.txtIDCliente.setText((String) this.tDatos.getValueAt(fila, 0));
        this.nombre.setText((String) this.tDatos.getValueAt(fila, 1));
        this.txtEdadDC.setText((String) this.tDatos.getValueAt(fila, 2));
        this.sexo.setSelectedIndex((String) this.tDatos.getValueAt(fila, 3)=="HOMBRE"?0:1);
        this.txtTlfDC.setText((String) this.tDatos.getValueAt(fila, 4));
        this.email.setText((String) this.tDatos.getValueAt(fila, 5));
        this.activo.setSelected((String) this.tDatos.getValueAt(fila, 6)=="ACTIVO"?true:false);
    }//GEN-LAST:event_tDatosMouseClicked

    private void saveDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDCActionPerformed
        // TODO add your handling code here:
        boolean sql;//Si se ejecuta bien la sentencia o no.
        if(Integer.parseInt(this.txtEdadDC.getText()) < 135 && Integer.parseInt(this.txtEdadDC.getText()) > 16 && email.getText().length() <= 60 && txtIDCliente.getText().length() ==16 && nombre.getText().length() <=60 ){
            conect.CONECTAR();
            int activo = this.activo.isSelected()?1:0;
            String sexo = this.sexo.getSelectedIndex()==0?"1":"0";
            if(this.tDatos.getSelectedRow()==-1){
                if(!this.txtIDCliente.getText().equals("")&&!nombre.getText().equals("") && !txtTlfDC.getText().equals("") && !email.getText().equals("") && !txtEdadDC.getText().equals("")){
                    sql=conect.EJECUTAR("INSERT INTO clientes(id_cliente, nombre, telefono, email, activo, edad, sexo) VALUES('"+this.txtIDCliente.getText()+"', '"+nombre.getText().toUpperCase()+"', '"+txtTlfDC.getText()+"', '"+email.getText()+"', '"+activo+"', "+this.txtEdadDC.getText()+", "+sexo+");");
                    if(sql){
                         JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                         limpiar();
                         this.txtIDCliente.setEditable(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campos sin llenar!");
                }
            } else {
                if(this.nombre.getText().equals("") || this.txtTlfDC.getText().equals("") || this.email.getText().equals("") || this.txtEdadDC.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Datos a modificar incompletos");
                } else{
                    conect.CONECTAR();
                    sql=conect.EJECUTAR("UPDATE clientes SET nombre='"+this.nombre.getText().toUpperCase()+"', telefono='"+this.txtTlfDC.getText()+"', email='"+this.email.getText()+"', activo='"+activo+"', edad="+txtEdadDC.getText()+", sexo="+sexo+" WHERE id_cliente='"+this.txtIDCliente.getText()+"';");
                    conect.CERRAR();      
                    if(sql){
                        JOptionPane.showMessageDialog(null,"Datos modificados correctamente!");
                        this.limpiar();
                        this.txtIDCliente.setEditable(true);
                    }
                    

                }
            }
            conect.CERRAR();
            this.checkGrid();
        
        } else {
            JOptionPane.showMessageDialog(null, "Revise los datos. Uno o más campos exceden su límite de caracteres!");
        } 
        

    }//GEN-LAST:event_saveDCActionPerformed

    private void deleteDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDCActionPerformed
        // TODO add your handling code here:
        boolean sql;//si se realiza bien la sentencia
        conect.CONECTAR();
        if(!txtIDCliente.getText().equals("")){
            sql=conect.EJECUTAR("DELETE FROM clientes WHERE id_cliente='"+txtIDCliente.getText()+"';");
            if(sql){
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente!");
            }    
            
        } else {
            JOptionPane.showMessageDialog(null," Campo del codigo vacío!");
        }
        limpiar();
        conect.CERRAR();
        this.checkGrid();
    }//GEN-LAST:event_deleteDCActionPerformed

    private void btnMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarMouseClicked
        // TODO add your handling code here:
        int f = this.tDatos.getSelectedRow();
        if(f!=-1){
            boolean activo = this.tDatos.getValueAt(f, 6).equals("ACTIVO");
            if(!activo){
                limpiar();
                this.txtIDCliente.setEditable(true);
            }
        }
        if(this.a==1){
            this.a=0;
            this.btnMostrar.setText("Mostrar solo activos");
        } else if(this.a==0){
            this.a=1;
            this.btnMostrar.setText("Mostrar todos");
        }
        this.checkGrid();

    }//GEN-LAST:event_btnMostrarMouseClicked

    private void nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFocusLost
        // TODO add your handling code here:
        /*
        if(this.nombre.getText().length() > 60){
            JOptionPane.showMessageDialog(null, "El nombre completo no puede exceder los 60 caracteres!");
            this.valido=false;
            this.nombre.requestFocus();
        } else{
            this.valido=true;
        }*/
    }//GEN-LAST:event_nombreFocusLost

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        // TODO add your handling code here:
       /*
        if(this.email.getText().length() > 50){
            JOptionPane.showMessageDialog(null, "La longitud del correo no puede ser mayor a 50 caracteres!");
            this.valido=false;
            this.email.requestFocus();
        } else{
            this.valido=true;
        }
               */
    }//GEN-LAST:event_emailFocusLost

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void txtIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDClienteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        this.limpiar();
        this.checkGrid();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activo;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton deleteDC;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton newDC;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton saveDC;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTable tDatos;
    private javax.swing.JFormattedTextField txtEdadDC;
    private javax.swing.JFormattedTextField txtIDCliente;
    private javax.swing.JFormattedTextField txtTlfDC;
    // End of variables declaration//GEN-END:variables
}
