/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_catalogos;

import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uso_bd.CONEXION;

/**
 *
 * @author Kevin
 */
public class REPORTE_FACTURA extends javax.swing.JInternalFrame {

    /**
     * Creates new form REPORTE_FACTURA
     */
    CONEXION conect;
    
    public REPORTE_FACTURA() {
        initComponents();
        conect = new CONEXION();
        this.set_Intervalos();
        this.updateGridFactura();
        this.agregar_ListenerF(this.f_Inicio);
        this.agregar_ListenerF(this.f_Fin);
    }
    private void agregar_ListenerF(JDateChooser jdc){
        jdc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        // If the 'date' property was changed...
        if ("date".equals(evt.getPropertyName())) {
            JDateChooser aDateChooser = (JDateChooser) evt.getSource();
            boolean isDateSelectedByUser = false;
            // Get the otherwise unaccessible JDateChooser's 'dateSelected' field.
            try {
                // Get the desired field using reflection
                java.lang.reflect.Field dateSelectedField = JDateChooser.class.getDeclaredField("dateSelected");
                // This line makes the value accesible (can be read and/or modified)
                dateSelectedField.setAccessible(true);
                isDateSelectedByUser = dateSelectedField.getBoolean(aDateChooser);
            } catch (Exception ignoreOrNot) {
            }

            // Do some important stuff depending on wether value was changed by user
            if (isDateSelectedByUser) {
                updateGridFactura();
            }

            // Reset the value to false
            try {
                java.lang.reflect.Field dateSelectedField = JDateChooser.class.getDeclaredField("dateSelected");
                dateSelectedField.setAccessible(true);
                dateSelectedField.setBoolean(aDateChooser, false);
            } catch (Exception ignoreOrNot) {
            }
        }
    }
});
    }
    private void set_Intervalos(){
        //Para primer día del calendario
        Calendar inicio = Calendar.getInstance();
        inicio.set(Calendar.DAY_OF_MONTH, 1);
        Date d = inicio.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //Para el último día del mes
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDayOfTheMonth = cal.getTime();
        this.f_Inicio.setDate(d);
        this.f_Fin.setDate(lastDayOfTheMonth);
        
        //this.f_Inicio.setDate();
    }
    private void updateGridFactura(){
        if(this.f_Inicio.getDate().before(this.f_Fin.getDate())){
            conect.CONECTAR();
            try {

                //String[] titulos = {"CEDULA","NOMBRE","EDAD", "SEXO","TELEFONO","EMAIL", "ACTIVO"};

                DefaultTableModel modelo = (DefaultTableModel) this.tFactR.getModel();
                DefaultTableModel m = (DefaultTableModel) this.tDF.getModel();
                int f = this.tFactR.getRowCount();
                int f2 = this.tDF.getRowCount();
                //Para eliminar modelo actual
                while(f>0){
                    modelo.removeRow(0);
                    f--;
                }
                while(f2>0){
                    m.removeRow(0);
                    f2--;
                }
                
                String inicio="", fin="";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                inicio = sdf.format(f_Inicio.getDate());
                fin = sdf.format(this.f_Fin.getDate());
                //Datos de tabla
                ResultSet rs = conect.CONSULTAR("SELECT \n"+
                                        "factura.id_factura AS No_FACTURA, \n"+
                                        "factura.fh_emision AS FH_EMISION, \n"+
                                        "pago.nombre AS METODO_PAGO, \n"+
                                        "clientes.nombre AS NOMBRE_CLIENTE, \n"+
                                        "factura.id_usuario AS CAJERO, \n"+
                                        "factura.subtotal AS SUBTOTAL, \n"+
                                        "factura.iva AS IVA, \n"+
                                        "factura.total AS TOTAL \n"+
                                        "FROM factura \n"+
                                        "INNER JOIN pago ON factura.id_pago=pago.id_pago \n"+
                                        "INNER JOIN clientes ON factura.id_cliente=clientes.id_cliente \n"+
                                        "WHERE factura.activo='1' AND factura.fh_emision >='"+inicio+"' AND factura.fh_emision<='"+fin+"'"); 
                String[] fila = new String[8];

                while(rs.next()){

                        fila[0] = (String) rs.getString("No_FACTURA");
                        fila[1] = (String) rs.getString("FH_EMISION");
                        fila[2] = (String) rs.getString("METODO_PAGO");  
                        fila[3] = (String) rs.getString("NOMBRE_CLIENTE"); 
                        fila[4] = (String) rs.getString("CAJERO");
                        fila[5] = (String) rs.getString("SUBTOTAL");  
                        fila[6] = (String) rs.getString("IVA"); 
                        fila[7] = (String) rs.getString("TOTAL"); 
                        modelo.addRow(fila);
                }
                this.tFactR.setModel(modelo);
                //Para los datos de resumen
                ResultSet r = conect.CONSULTAR("SELECT SUM(factura.subtotal) AS SUB, SUM(factura.iva) AS IVA, SUM(factura.total) AS TOT FROM factura WHERE factura.activo='1' and factura.fh_emision >='"+inicio+"' and factura.fh_emision<='"+fin+"';");
                String subt="", iva="", t="";
                boolean h =false;
                while(r.next()){
                    subt = (String) r.getString("SUB");
                    iva = (String) r.getString("IVA");
                    t = (String) r.getString("TOT");
                    h=true;
                }
                if(!h){
                    this.ST.setText("0.00");
                    this.IVA.setText("0.00");
                    this.IT.setText("0.00");
                } else{
                    this.ST.setText(subt);
                    this.IVA.setText(iva);
                    this.IT.setText(t);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            conect.CERRAR();
            
        } else{
            JOptionPane.showMessageDialog(null,"La fecha de inicio no puede ser posterior a la de fin!");
            this.set_Intervalos();
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

        tiempo = new javax.swing.JPanel();
        f_Inicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        f_Fin = new com.toedter.calendar.JDateChooser();
        resumen = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ST = new javax.swing.JLabel();
        IVA = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IT = new javax.swing.JLabel();
        facturas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tFactR = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tDF = new javax.swing.JTable();
        btnAnularF = new javax.swing.JButton();

        setClosable(true);
        setTitle("REPORTE DE FACTURAS REALIZADAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte.png"))); // NOI18N

        tiempo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intervalo de Tiempo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        f_Inicio.setDateFormatString("yyyy-MM-dd");
        f_Inicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                f_InicioPropertyChange(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Fecha de inicio:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fecha de fin:");

        f_Fin.setDateFormatString("yyyy-MM-dd");
        f_Fin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                f_FinPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout tiempoLayout = new javax.swing.GroupLayout(tiempo);
        tiempo.setLayout(tiempoLayout);
        tiempoLayout.setHorizontalGroup(
            tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tiempoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(f_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_Fin, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tiempoLayout.setVerticalGroup(
            tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tiempoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(f_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(f_Fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resumen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen del periodo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Subtotal:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Impuesto Total:");

        ST.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ST.setText("0.00");
        ST.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        IVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IVA.setText("0.00");
        IVA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Ingresto Total:");

        IT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IT.setText("0.00");
        IT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout resumenLayout = new javax.swing.GroupLayout(resumen);
        resumen.setLayout(resumenLayout);
        resumenLayout.setHorizontalGroup(
            resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resumenLayout.createSequentialGroup()
                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resumenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ST, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(resumenLayout.createSequentialGroup()
                        .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(resumenLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resumenLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(25, 25, 25)))
                        .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        resumenLayout.setVerticalGroup(
            resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resumenLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ST)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(IVA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(IT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        facturas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Facturas realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        tFactR.setAutoCreateRowSorter(true);
        tFactR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº FACTURA", "FH_EMISION", "METODO_PAGO", "CLIENTE", "CAJERO", "SUBTOTAL", "IVA", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tFactR.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tFactR.getTableHeader().setReorderingAllowed(false);
        tFactR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tFactRMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tFactR);
        if (tFactR.getColumnModel().getColumnCount() > 0) {
            tFactR.getColumnModel().getColumn(0).setResizable(false);
            tFactR.getColumnModel().getColumn(0).setPreferredWidth(80);
            tFactR.getColumnModel().getColumn(1).setResizable(false);
            tFactR.getColumnModel().getColumn(1).setPreferredWidth(150);
            tFactR.getColumnModel().getColumn(2).setResizable(false);
            tFactR.getColumnModel().getColumn(2).setPreferredWidth(190);
            tFactR.getColumnModel().getColumn(3).setResizable(false);
            tFactR.getColumnModel().getColumn(3).setPreferredWidth(360);
            tFactR.getColumnModel().getColumn(4).setResizable(false);
            tFactR.getColumnModel().getColumn(4).setPreferredWidth(100);
            tFactR.getColumnModel().getColumn(5).setResizable(false);
            tFactR.getColumnModel().getColumn(5).setPreferredWidth(110);
            tFactR.getColumnModel().getColumn(6).setResizable(false);
            tFactR.getColumnModel().getColumn(6).setPreferredWidth(110);
            tFactR.getColumnModel().getColumn(7).setResizable(false);
            tFactR.getColumnModel().getColumn(7).setPreferredWidth(115);
        }

        javax.swing.GroupLayout facturasLayout = new javax.swing.GroupLayout(facturas);
        facturas.setLayout(facturasLayout);
        facturasLayout.setHorizontalGroup(
            facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        facturasLayout.setVerticalGroup(
            facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        tDF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE_PRODUCTO", "CANTIDAD", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDF.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tDF);
        if (tDF.getColumnModel().getColumnCount() > 0) {
            tDF.getColumnModel().getColumn(0).setResizable(false);
            tDF.getColumnModel().getColumn(0).setPreferredWidth(350);
            tDF.getColumnModel().getColumn(1).setResizable(false);
            tDF.getColumnModel().getColumn(1).setPreferredWidth(100);
            tDF.getColumnModel().getColumn(2).setResizable(false);
            tDF.getColumnModel().getColumn(2).setPreferredWidth(110);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAnularF.setText("Anular Factura");
        btnAnularF.setEnabled(false);
        btnAnularF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(btnAnularF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(facturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnularF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void f_InicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_f_InicioPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_f_InicioPropertyChange

    private void f_FinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_f_FinPropertyChange
        // TODO add your handling code here:
        this.updateGridFactura();
    }//GEN-LAST:event_f_FinPropertyChange

    private void tFactRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFactRMouseClicked
        // TODO add your handling code here:
        this.btnAnularF.setEnabled(true);
        //Se debe vaciar y llenar la tabla de detalle
        DefaultTableModel modelo = (DefaultTableModel) this.tDF.getModel();
        int f = this.tDF.getRowCount();
        int fi = this.tFactR.getSelectedRow();
        String id=(String)this.tFactR.getValueAt(fi,0);
        //Para eliminar modelo actual
        while(f>0){
            modelo.removeRow(0);
            f--;
        }
        //Ahora se debe llenar
        conect.CONECTAR();
        ResultSet df = conect.CONSULTAR("SELECT p.descripcion AS nombrep, df.cantidad AS cantidad, df.total AS total \n"+
                                        "FROM detalle_factura AS df \n"+
                                        "INNER JOIN producto AS p ON df.id_producto=p.id_producto \n"+
                                        "INNER JOIN factura AS f ON df.id_factura=f.id_factura \n"+
                                        "WHERE df.id_factura='"+id+"';");
        String[] fila = new String[3];
        try {
            while(df.next()){
                fila[0]=(String) df.getString("nombrep");
                fila[1]=(String) df.getString("cantidad");
                fila[2]=(String) df.getString("total");
                modelo.addRow(fila);
            }
            this.tDF.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(REPORTE_FACTURA.class.getName()).log(Level.SEVERE, null, ex);
        }
        conect.CERRAR();
        /**/
    }//GEN-LAST:event_tFactRMouseClicked

    private void btnAnularFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularFActionPerformed
        // TODO add your handling code here:
        int f=this.tFactR.getSelectedRow();
        if(f!=-1){
            String id = (String)this.tFactR.getValueAt(f, 0);
            conect.CONECTAR();
            conect.EJECUTAR("UPDATE factura SET activo='0' WHERE id_factura='"+id+"';");
            conect.CERRAR();
            this.btnAnularF.setEnabled(false);
            this.updateGridFactura();
            JOptionPane.showMessageDialog(null, "Factura anulada exitosamente!");
        }
        
    }//GEN-LAST:event_btnAnularFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IT;
    private javax.swing.JLabel IVA;
    private javax.swing.JLabel ST;
    private javax.swing.JButton btnAnularF;
    private com.toedter.calendar.JDateChooser f_Fin;
    private com.toedter.calendar.JDateChooser f_Inicio;
    private javax.swing.JPanel facturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel resumen;
    private javax.swing.JTable tDF;
    private javax.swing.JTable tFactR;
    private javax.swing.JPanel tiempo;
    // End of variables declaration//GEN-END:variables
}
