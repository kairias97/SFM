/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_catalogos;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import inicio_sesion.USER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import uso_bd.CONEXION;

/**
 *
 * @author kevin
 */
public class FACTURA extends javax.swing.JInternalFrame{

    /**
     * Creates new form FACTURA
     */
    ArrayList<String> idCliente;
    ArrayList<String> pago;
    ArrayList<String> id_pago;
    ArrayList<String> nombreCliente;
    ArrayList<String> nombreProd;
    ArrayList<Double> precioP;
    ArrayList<Integer> cantidad;
    CONEXION conect;
    USER usuario;
    
    public FACTURA(USER u) {
        Locale.setDefault(new Locale("en", "US"));
        initComponents();
        this.usuario=u;
        this.crearTimer();
        this.crearFecha();
        this.SubTotal.setText("0.00");
        this.IVA.setText("0.00");
        this.Total.setText("0.00");
        idCliente = new ArrayList();
        precioP = new ArrayList();
        cantidad = new ArrayList();
        nombreProd = new ArrayList();
        nombreCliente = new ArrayList();
        pago = new ArrayList();
        id_pago = new ArrayList();
        conect = new CONEXION();
        this.newFactura();
    }
    private void crearFecha(){
        Calendar c = Calendar.getInstance();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        this.Fecha.setText(fecha);
    }
    private void crearTimer(){
        int delay = 1000; //milliseconds para update hora.
        Timer timer;
        
        ActionListener taskPerformer;
        taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                Calendar calendar = Calendar.getInstance();
                String hora = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
                
                Hora.setText(hora);
            }
            
        };
        timer=new Timer(delay, taskPerformer); 
        timer.start();
     
     }
    
    private void newFactura(){
        //Primero a resetear las listas
        this.idCliente.clear();
        this.pago.clear();
        this.id_pago.clear();
        this.nombreCliente.clear();
        this.precioP.clear();
        this.cantidad.clear();
        this.nombreProd.clear();
        this.comboClienteF.removeAllItems();
        this.comboProdF.removeAllItems();
        this.comboPagoF.removeAllItems();
        //Se cargan 3 resultset para utilizarlos despues
        DefaultTableModel m = (DefaultTableModel) this.tCarritoF.getModel();
        int f = this.tCarritoF.getRowCount();
        while(f>0){
            m.removeRow(0);
            f--;
        }
        
        try{
            conect.CONECTAR();
            ResultSet id = this.conect.CONSULTAR("Select*from clientes WHERE activo=1;"); 
            ResultSet producto = this.conect.CONSULTAR("Select*from producto WHERE activo=1;");
            ResultSet pagar  = this.conect.CONSULTAR("Select*from pago WHERE activo=1;");
            //Para el autoincrement del id factura
            String auto="0";
            ResultSet a=conect.CONSULTAR("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES\n" +
"WHERE table_name = 'factura'");
            while(a.next()){
                auto = (String)a.getString("auto_increment");
            }
            this.idFactura.setText(auto);
            conect.CERRAR();
            while(id.next()){
                this.nombreCliente.add((String) id.getString("nombre"));
                this.idCliente.add((String) id.getString("id_cliente"));
            }
            while(producto.next()){
                DecimalFormat df = new DecimalFormat("#.00"); 
                Double d = Double.parseDouble((String)producto.getString("precio"));
                d = Double.parseDouble(df.format(d));
                //JOptionPane.showMessageDialog(null, d);
                this.nombreProd.add((String) producto.getString("descripcion"));
                this.precioP.add(d);
                this.cantidad.add(Integer.parseInt((String) producto.getString("cantidad")));
                
            }
            while(pagar.next()){
                this.pago.add((String) pagar.getString("nombre"));
                this.id_pago.add((String) pagar.getString("id_pago"));
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //Ahora se carga en los combo box los elementos de la lista
        for (int i = 0; i < this.nombreCliente.size(); i++) {
            this.comboClienteF.addItem(this.nombreCliente.get(i));
        }
        for (int i = 0; i < this.nombreProd.size(); i++) {
            this.comboProdF.addItem(this.nombreProd.get(i));
        }
        for (int i = 0; i < this.pago.size(); i++) {
            this.comboPagoF.addItem(this.pago.get(i));
        }
        this.updateMontos();
        
    };
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Hora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idFactura = new javax.swing.JLabel();
        SubTotal = new javax.swing.JLabel();
        IVA = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboClienteF = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        comboPagoF = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnAddCarrito = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        comboProdF = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cantP = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        deleteCarrito = new javax.swing.JButton();
        deleteProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCarritoF = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setTitle("NUEVA FACTURA");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factura.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        Hora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Hora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Fecha:");

        jLabel2.setText("Hora:");

        Fecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Subtotal:");

        jLabel4.setText("IVA:");

        jLabel5.setText("Total:");

        jLabel6.setText("N°:");

        idFactura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idFactura.setText("    ");
        idFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SubTotal.setText("     ");
        SubTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        IVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IVA.setText("     ");
        IVA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Total.setText("     ");
        Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Cliente:");

        jLabel10.setText("Pago:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Hora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboClienteF, javax.swing.GroupLayout.Alignment.TRAILING, 0, 260, Short.MAX_VALUE)
                    .addComponent(comboPagoF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(idFactura))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboClienteF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPagoF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar producto al carrito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        btnAddCarrito.setText("Añadir al carrito");
        btnAddCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCarritoMouseClicked(evt);
            }
        });
        btnAddCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarritoActionPerformed(evt);
            }
        });

        jLabel9.setText("Producto:");

        jLabel7.setText("Cantidad:");

        cantP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboProdF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cantP, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddCarrito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboProdF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCarrito)
                    .addComponent(jLabel7)
                    .addComponent(cantP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrito de compras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N

        deleteCarrito.setText("Vaciar carrito");
        deleteCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCarritoActionPerformed(evt);
            }
        });

        deleteProducto.setText("Eliminar elemento");
        deleteProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductoActionPerformed(evt);
            }
        });

        tCarritoF.setAutoCreateRowSorter(true);
        tCarritoF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCTO", "PRECIO", "CANTIDAD", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tCarritoF.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tCarritoF.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tCarritoF);
        if (tCarritoF.getColumnModel().getColumnCount() > 0) {
            tCarritoF.getColumnModel().getColumn(0).setResizable(false);
            tCarritoF.getColumnModel().getColumn(0).setPreferredWidth(300);
            tCarritoF.getColumnModel().getColumn(1).setResizable(false);
            tCarritoF.getColumnModel().getColumn(1).setPreferredWidth(100);
            tCarritoF.getColumnModel().getColumn(2).setResizable(false);
            tCarritoF.getColumnModel().getColumn(2).setPreferredWidth(100);
            tCarritoF.getColumnModel().getColumn(3).setResizable(false);
            tCarritoF.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteCarrito)
                .addGap(12, 12, 12))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteCarrito)
                    .addComponent(deleteProducto))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jButton1.setText("Anular factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Completar factura");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.newFactura();
    }//GEN-LAST:event_jButton1ActionPerformed
    private boolean checkCarrito(){
        String p = this.comboProdF.getSelectedItem().toString();
        boolean igual = false;
        for (int i = 0; i < tCarritoF.getRowCount(); i++) {
            if(tCarritoF.getValueAt(i, 0).equals(p)){
                igual=true;
            }
        }
        return igual;
    }
    private void updateMontos(){
        //Para actualizar los valores de los labels
        Double subt=0.00;
        Double iva=0.00;
        Double total=0.00;
        for (int i = 0; i < this.tCarritoF.getRowCount(); i++) {
            Object o =this.tCarritoF.getValueAt(i, 3);
            subt+=Double.parseDouble(o.toString());
        }
        DecimalFormat df = new DecimalFormat("#.00"); 
        subt=Double.parseDouble(df.format(subt));
        iva= Double.parseDouble(df.format(subt * 0.16));
        total= Double.parseDouble(df.format(subt + iva));
        this.SubTotal.setText(String.valueOf(subt));
        this.IVA.setText(String.valueOf(iva));
        this.Total.setText(String.valueOf(total));
        this.cantP.setText("");
    }
    private void btnAddCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCarritoMouseClicked
        // TODO add your handling code here:  
    }//GEN-LAST:event_btnAddCarritoMouseClicked

    private void deleteCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCarritoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) this.tCarritoF.getModel();
            int f = this.tCarritoF.getRowCount();
            while(f>0){
                modelo.removeRow(0);
                f--;
            }
        this.updateMontos();
    }//GEN-LAST:event_deleteCarritoActionPerformed

    private void deleteProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductoActionPerformed
        // TODO add your handling code here:
        int fila = this.tCarritoF.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado elemento del carrito a eliminar!");
        } else{
            DefaultTableModel modelo = (DefaultTableModel) this.tCarritoF.getModel();
            modelo.removeRow(fila);
            this.updateMontos();
        }
    }//GEN-LAST:event_deleteProductoActionPerformed

    private void btnAddCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarritoActionPerformed
        // TODO add your handling code here:
        if(this.checkCarrito()){
           int adicional = JOptionPane.showConfirmDialog(null, "Item ya existente en el carrito de compras. ¿Desea agregar unidades adicionales?");
           if(adicional==0){
               //Proceso de agregar al carro más unidades
                if(this.cantP.getText().equals("") || Integer.parseInt(this.cantP.getText()) <=0){
                    JOptionPane.showMessageDialog(null, "Cantidad a agregar inválida!");
                } else{
                    int c=Integer.parseInt(this.cantP.getText());//Cantidad a agregar
                    int ce=0;//Cantidad existente
                    String p = this.comboProdF.getSelectedItem().toString();
                    for (int i = 0; i < tCarritoF.getRowCount(); i++) {
                        if(tCarritoF.getValueAt(i, 0).equals(p)){
                            //Aca se ha hallado la posición en el carrito
                            //ce=Integer.parseInt((String) tCarritoF.getValueAt(i, 2));
                            Object o;
                            o=tCarritoF.getValueAt(i, 2);
                            ce=Integer.parseInt(o.toString());
                            if(ce+c > (int)this.cantidad.get(this.comboProdF.getSelectedIndex())){
                                JOptionPane.showMessageDialog(null, "Imposible incrementar cantidad deseada. No hay suficiente de este producto para abastecer la compra!");
                            } else{
                                DecimalFormat df = new DecimalFormat("#.00"); 
                                Double subt = Double.parseDouble(df.format(Double.parseDouble(String.valueOf(ce+c)) * this.precioP.get(this.comboProdF.getSelectedIndex())));
                                tCarritoF.setValueAt(c+ce, i, 2);
                                tCarritoF.setValueAt(subt,i,3);
                                this.updateMontos();
                                
                            }
                        }
                    }
                }
                       
           }
           //JOptionPane.showMessageDialog(null, "Imposible agregar elemento al carro. Item ya existente.");
       } else {
           if(this.cantP.getText().equals("") || Integer.parseInt(this.cantP.getText()) <=0){
               JOptionPane.showMessageDialog(null, "Cantidad a agregar inválida!");
           } else{
               DefaultTableModel model = (DefaultTableModel) this.tCarritoF.getModel();
                String[] fila = new String[4];
                fila[0]=this.nombreProd.get(this.comboProdF.getSelectedIndex());
                fila[1]=this.precioP.get(this.comboProdF.getSelectedIndex()).toString();
                fila[2]=this.cantP.getText();
                Double subtotal;
                DecimalFormat df = new DecimalFormat("#.00"); 
                subtotal = Double.parseDouble(df.format(Double.parseDouble(fila[1]) * Double.parseDouble(fila[2])));
                fila[3]=subtotal.toString();
                model.addRow(fila);
                this.updateMontos();
           }
           
       }
       
    }//GEN-LAST:event_btnAddCarritoActionPerformed
    public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int r = tCarritoF.getRowCount();
        if(r==0){
            JOptionPane.showMessageDialog(null, "No se puede crear factura dado que el carrito de compras está vacío!");
        }else{
            //Primero se crea la factura
            conect.CONECTAR();
            boolean sql, sql2;
            sql = conect.EJECUTAR("INSERT INTO factura(fh_emision, id_pago, id_cliente, id_usuario, subtotal, iva, total, activo) VALUES('"+this.Fecha.getText().concat(" "+this.Hora.getText())+"', '"+this.id_pago.get(this.comboPagoF.getSelectedIndex())+"','"+this.idCliente.get(this.comboClienteF.getSelectedIndex())+"','"+this.usuario.getUser()+"','"+this.SubTotal.getText()+"','"+this.IVA.getText()+"','"+this.Total.getText()+"', '1');");
            //if(sql)JOptionPane.showMessageDialog(null, "Se creó espacio para factura");
            //Aca se va a crear los detalles de factura
            for (int i = 0; i < this.tCarritoF.getRowCount(); i++) {
                DecimalFormat df = new DecimalFormat("#.00"); 
                String np = (String)this.tCarritoF.getValueAt(i,0);//Subtotal
                String id="";
                Object o;
                o = tCarritoF.getValueAt(i, 2);
                int c = Integer.parseInt(o.toString());//Cantidad
                o = tCarritoF.getValueAt(i,3); 
                Double st; 
                st=Double.parseDouble(o.toString());//Subtotal
                st=Double.parseDouble(df.format(st));
                //Para obtener el id producto
                ResultSet idp = conect.CONSULTAR("Select id_producto from producto WHERE descripcion='"+np+"';");
                try {
                    while(idp.next()){
                        id=(String) idp.getString("id_producto");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FACTURA.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql2 = conect.EJECUTAR("INSERT INTO detalle_factura(id_producto, cantidad, total, id_factura) VALUES('"+id+"','"+c+"','"+st+"','"+this.idFactura.getText()+"');");
                //if(sql2)System.out.println("Detalle ingresado correctamente");
                conect.EJECUTAR("UPDATE producto SET cantidad=cantidad-"+c+" WHERE id_producto='"+id+"'");
            }
            JOptionPane.showMessageDialog(null, "Factura realizada correctamente!");
            this.newFactura();
            conect.CERRAR();
        }
        this.updateMontos();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel IVA;
    private javax.swing.JLabel SubTotal;
    private javax.swing.JLabel Total;
    private javax.swing.JButton btnAddCarrito;
    private javax.swing.JFormattedTextField cantP;
    private javax.swing.JComboBox comboClienteF;
    private javax.swing.JComboBox<String> comboPagoF;
    private javax.swing.JComboBox comboProdF;
    private javax.swing.JButton deleteCarrito;
    private javax.swing.JButton deleteProducto;
    private javax.swing.JLabel idFactura;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCarritoF;
    // End of variables declaration//GEN-END:variables
}
