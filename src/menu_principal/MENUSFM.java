/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_principal;
import inicio_sesion.INICIOUSB;
import manejo_catalogos.TIPOMUEBLE;
import manejo_catalogos.CLIENTE;
import manejo_catalogos.MATERIALES;
import inicio_sesion.USER;
import uso_bd.CONEXION;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import manejo_catalogos.FACTURA;
import manejo_catalogos.PRODUCTO;
import manejo_catalogos.REPORTE_FACTURA;

/**
 *
 * @author Kevin
 */
public class MENUSFM extends javax.swing.JFrame {

    /**
     * Creates new form MENUSFM
     */
    
    USER u;
    CONEXION conect;
    boolean hUB; //Se hallo coincidencia en borrarUSER
    boolean hAU; //Se puede crear
    boolean hCC; //Se puede cambiar clave
    
    public MENUSFM(USER user) {
        initComponents();
        Dimension screenSize = new Dimension(1280,720);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenSize);
        u=user;
        conect = new CONEXION();
        this.hUB=false;
        this.hAU = false;
        this.setLocationRelativeTo(null);
        URL iconURL = getClass().getResource("/imagenes/home.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        URL iconURL2 = getClass().getResource("/imagenes/settings.png");
        // iconURL is null when not found
        ImageIcon icon2 = new ImageIcon(iconURL2);
        this.cambioClave.setIconImage(icon2.getImage());
        this.agregarUser.setIconImage(icon2.getImage());
        this.borrarUser.setIconImage(icon2.getImage());
        
    }
    
    private boolean checkUser(String user){
        boolean h = false;
        this.conect.CONECTAR();
        ResultSet rs = conect.CONSULTAR("Select * from usuarios WHERE id_usuario='"+user+"';");
        try {
            while(rs.next()){
                h=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MENUSFM.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conect.CERRAR();
        return h;
        
    }
    private void checkDatos(char[] p1, char[] p2){
        String pass1 = new String(p1);
        String pass2 = new String(p2);
         if(pass1.equals(pass2) && !this.checkUser(this.txtUserAU.getText())){
            if(txtUserAU.getText().equals("")){
                this.msgAU.setForeground(Color.RED);
                this.msgAU.setText("*Nombre de usuario obligatorio.");
                this.hAU=false;
            } else{
                if(pass1.equals("") && pass2.equals("")){
                    this.msgAU.setForeground(Color.RED);
                    this.msgAU.setText("*Debe ingresar la clave a asignar.");
                    this.hAU=false;
                } else{
                    this.msgAU.setForeground(Color.GREEN);
                    this.msgAU.setText("Listo para ingreso de usuario.");
                    this.hAU=true;
                }
                
            }
            
        } else {
            if(pass1.equals(pass2)){
                if(txtUserAU.getText().equals("")){
                    this.msgAU.setForeground(Color.RED);
                    this.msgAU.setText("*Nombre de usuario obligatorio.");
                    this.hAU=false;
                } else{
                this.msgAU.setForeground(Color.RED);
                this.msgAU.setText("*Nombre de usuario está en uso.");
                this.hAU=false;
                }
            } else{
                this.msgAU.setForeground(Color.RED);
                this.msgAU.setText("*Ambas contraseñas deben de coincidir.");
                this.hAU=false;
            }
            
        }
    }

    private void checkDatos(char[] oldP, char[] p1, char[] p2){
        String oldPass = new String(oldP);
        String pass1 = new String(p1);
        String pass2 = new String(p2); 
        if(!this.u.getClave().equals(oldPass)){
            this.msgCC.setForeground(Color.RED);
            this.msgCC.setText("*Clave ingresada no coincide con la actual!.");
            this.hCC=false;
        } else{
            if(!pass1.equals("") && !pass2.equals("") && pass1.equals(pass2)){
                this.msgCC.setForeground(Color.GREEN);
                this.msgCC.setText("Listo para ingreso de usuario.");
                this.hCC=true;
            } else if(pass1.equals("") && pass2.equals("")){
                this.msgCC.setForeground(Color.RED);
                this.msgCC.setText("*La nueva clave no puede estar vacía!.");
                this.hCC=false;
            } else{
                this.msgCC.setForeground(Color.RED);
                this.msgCC.setText("*Ambas contraseñas deben de coincidir.");
                this.hCC=false;
            }
        
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

        cambioClave = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtClaveNew = new javax.swing.JPasswordField();
        txtClaveNew2 = new javax.swing.JPasswordField();
        btnCambio = new javax.swing.JButton();
        txtClaveOld = new javax.swing.JPasswordField();
        msgCC = new javax.swing.JLabel();
        agregarUser = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUserAU = new javax.swing.JTextField();
        txtClave1AU = new javax.swing.JPasswordField();
        txtClave2AU = new javax.swing.JPasswordField();
        btnAdd = new javax.swing.JButton();
        msgAU = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboAdmin = new javax.swing.JComboBox<String>();
        borrarUser = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUserBU = new javax.swing.JTextField();
        msgBU = new javax.swing.JLabel();
        btnBorrarBU = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Clientes = new javax.swing.JMenuItem();
        tipoM = new javax.swing.JMenuItem();
        Materiales = new javax.swing.JMenuItem();
        Productos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Facturar = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenuItem();
        cambioClaveM = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        cambioClave.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cambioClave.setTitle("Opciones administrativas");
        cambioClave.setMinimumSize(new java.awt.Dimension(325, 240));
        cambioClave.setResizable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Cambio de clave");

        jLabel9.setText("Clave actual:");

        jLabel10.setText("Clave nueva:");

        jLabel11.setText("Repetir clave:");

        txtClaveNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClaveNewFocusLost(evt);
            }
        });

        txtClaveNew2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClaveNew2FocusLost(evt);
            }
        });

        btnCambio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editUser.png"))); // NOI18N
        btnCambio.setText("Cambiar clave");
        btnCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambioMouseClicked(evt);
            }
        });
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });

        txtClaveOld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClaveOldFocusLost(evt);
            }
        });

        msgCC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout cambioClaveLayout = new javax.swing.GroupLayout(cambioClave.getContentPane());
        cambioClave.getContentPane().setLayout(cambioClaveLayout);
        cambioClaveLayout.setHorizontalGroup(
            cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cambioClaveLayout.createSequentialGroup()
                .addGroup(cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel8))
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel9)
                        .addGap(26, 26, 26)
                        .addComponent(txtClaveOld, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel10)
                        .addGap(26, 26, 26)
                        .addComponent(txtClaveNew, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel11)
                        .addGap(26, 26, 26)
                        .addComponent(txtClaveNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(msgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnCambio)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        cambioClaveLayout.setVerticalGroup(
            cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cambioClaveLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(txtClaveOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(txtClaveNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(cambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cambioClaveLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11))
                    .addComponent(txtClaveNew2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(msgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCambio)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        agregarUser.setTitle("Opciones administrativas");
        agregarUser.setMinimumSize(new java.awt.Dimension(370, 288));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Adición de nuevos usuarios");

        jLabel2.setText("Username:");

        jLabel3.setText("Clave nueva:");

        jLabel4.setText("Repetir clave:");

        txtUserAU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserAUFocusLost(evt);
            }
        });

        txtClave1AU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClave1AUFocusLost(evt);
            }
        });

        txtClave2AU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClave2AUFocusLost(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addUser.png"))); // NOI18N
        btnAdd.setText("Añadir Usuario");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        msgAU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setText("Administrador:");

        comboAdmin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO", "SI" }));

        javax.swing.GroupLayout agregarUserLayout = new javax.swing.GroupLayout(agregarUser.getContentPane());
        agregarUser.getContentPane().setLayout(agregarUserLayout);
        agregarUserLayout.setHorizontalGroup(
            agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarUserLayout.createSequentialGroup()
                .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarUserLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(msgAU, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarUserLayout.createSequentialGroup()
                        .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(26, 26, 26)
                        .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUserAU)
                                .addComponent(txtClave1AU)
                                .addComponent(txtClave2AU, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarUserLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addGap(109, 109, 109))
        );
        agregarUserLayout.setVerticalGroup(
            agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarUserLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserAU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClave1AU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtClave2AU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(agregarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(comboAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(msgAU, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addGap(23, 23, 23))
        );

        borrarUser.setTitle("Opciones administrativas");
        borrarUser.setMinimumSize(new java.awt.Dimension(320, 180));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Eliminación de usuario");

        jLabel6.setText("Username:");

        txtUserBU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserBUFocusLost(evt);
            }
        });
        txtUserBU.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtUserBUInputMethodTextChanged(evt);
            }
        });
        txtUserBU.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtUserBUPropertyChange(evt);
            }
        });
        txtUserBU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserBUKeyPressed(evt);
            }
        });

        msgBU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnBorrarBU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/removeUser.png"))); // NOI18N
        btnBorrarBU.setText("Eliminar");
        btnBorrarBU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBorrarBUMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout borrarUserLayout = new javax.swing.GroupLayout(borrarUser.getContentPane());
        borrarUser.getContentPane().setLayout(borrarUserLayout);
        borrarUserLayout.setHorizontalGroup(
            borrarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrarUserLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(borrarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgBU, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrarUserLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtUserBU, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrarUserLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBorrarBU)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrarUserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(78, 78, 78))
        );
        borrarUserLayout.setVerticalGroup(
            borrarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrarUserLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(borrarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserBU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msgBU, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBorrarBU)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE FACTURACION DE MUEBLES");
        setBackground(new java.awt.Color(0, 0, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        desktopPane.setBackground(new java.awt.Color(0, 0, 102));
        desktopPane.setForeground(new java.awt.Color(0, 51, 153));

        jMenuBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/file.png"))); // NOI18N
        jMenu1.setText("Archivo");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logOut.png"))); // NOI18N
        jMenuItem1.setText("Cerrar sesión");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/catalogo.png"))); // NOI18N
        jMenu2.setText("Catálogos");

        Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        Clientes.setText("Clientes");
        Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesActionPerformed(evt);
            }
        });
        jMenu2.add(Clientes);

        tipoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mueble.png"))); // NOI18N
        tipoM.setText("Tipo de Muebles");
        tipoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoMActionPerformed(evt);
            }
        });
        jMenu2.add(tipoM);

        Materiales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/material.png"))); // NOI18N
        Materiales.setText("Materiales");
        Materiales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaterialesActionPerformed(evt);
            }
        });
        jMenu2.add(Materiales);

        Productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N
        Productos.setText("Productos");
        Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductosMouseClicked(evt);
            }
        });
        Productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductosActionPerformed(evt);
            }
        });
        jMenu2.add(Productos);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/facturacion.png"))); // NOI18N
        jMenu3.setText("Facturación");

        Facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factura.png"))); // NOI18N
        Facturar.setText("Nueva Factura");
        Facturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacturarMouseClicked(evt);
            }
        });
        Facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacturarActionPerformed(evt);
            }
        });
        jMenu3.add(Facturar);

        Reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte.png"))); // NOI18N
        Reportes.setText("Reportes");
        Reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesActionPerformed(evt);
            }
        });
        jMenu3.add(Reportes);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        cambioClaveM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/settings.png"))); // NOI18N
        cambioClaveM.setText("Administración");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editUser.png"))); // NOI18N
        jMenuItem4.setText("Cambiar clave");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        cambioClaveM.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addUser.png"))); // NOI18N
        jMenuItem5.setText("Añadir nuevo usuario");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        cambioClaveM.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/removeUser.png"))); // NOI18N
        jMenuItem6.setText("Eliminar usuario");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseClicked(evt);
            }
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        cambioClaveM.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/backupDB.png"))); // NOI18N
        jMenuItem7.setText("Respaldar Base de Datos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        cambioClaveM.add(jMenuItem7);

        jMenuBar1.add(cambioClaveM);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int salir = JOptionPane.showConfirmDialog(null, "Seguro que desea cerrar sesión?", "Cerrar Sesión", WIDTH);
        if(salir==0){//Si es 0 entonces quiere salir
            this.dispose();
            INICIOUSB i = new INICIOUSB();
            String[] s = new String[1];//Dado que main pide un arreglo como argumento
            i.main(s);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesActionPerformed
        // TODO add your handling code here:
        CLIENTE c = new CLIENTE();
        c.setVisible(true);
        this.desktopPane.add(c);
       

    }//GEN-LAST:event_ClientesActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if(u.getAdmin()){
            
            //REVISAR ESTO LOL
            //Codigo para añadir nuevo usuario
            this.cambioClave.setLocationRelativeTo(null);
            //this.cambioClave.setSize(325, 250);
            this.cambioClave.pack();
            this.cambioClave.setResizable(false);
            this.cambioClave.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null,"No cuenta con privilegios suficientes para realizar esta acción!");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked

    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if(u.getAdmin()){
            //Codigo para añadir nuevo usuario
            this.agregarUser.setLocationRelativeTo(null);
            //this.agregarUser.setSize(370, 290);
            this.cambioClave.pack();
            this.agregarUser.setResizable(false);
            this.agregarUser.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null,"No cuenta con privilegios suficientes para realizar esta acción!");
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseClicked

    }//GEN-LAST:event_jMenuItem6MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if(u.getAdmin()){
            //Codigo para añadir nuevo usuario
            this.borrarUser.setLocationRelativeTo(null);
            //this.borrarUser.setSize(330, 210);
            this.cambioClave.pack();
            this.borrarUser.setResizable(false);
            this.borrarUser.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null,"No cuenta con privilegios suficientes para realizar esta acción!");
        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        //Para obtener la hora
        
        if(this.u.getAdmin()){
            selec_carpeta sc = new selec_carpeta();
            sc.show();
            this.desktopPane.add(sc);
        } else{
            JOptionPane.showMessageDialog(null,"No cuenta con privilegios suficientes para realizar esta acción!");
        }
        
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void txtClaveNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveNewFocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClaveOld.getPassword(), this.txtClaveNew.getPassword(), this.txtClaveNew2.getPassword());
    }//GEN-LAST:event_txtClaveNewFocusLost

    private void txtClaveNew2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveNew2FocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClaveOld.getPassword(), this.txtClaveNew.getPassword(), this.txtClaveNew2.getPassword());
    }//GEN-LAST:event_txtClaveNew2FocusLost

    private void btnCambioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambioMouseClicked

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
        // TODO add your handling code here:
        //Aqui va el codigo para cambio de clave
        if(hCC){
            String clave = new String(this.txtClaveNew.getPassword());
            this.u.setClave(clave);
            conect.CONECTAR();
            conect.EJECUTAR("UPDATE usuarios SET clave='"+clave+"' WHERE id_usuario='"+this.u.getUser()+"';");
            conect.CERRAR();
            this.txtClaveNew.setText("");
            this.txtClaveNew2.setText("");
            this.txtClaveOld.setText("");
            JOptionPane.showMessageDialog(null, "Clave cambiada con éxito!");
            this.msgCC.setText("");
            this.hCC=false;
        } else{
            JOptionPane.showMessageDialog(null,"Datos inválidos. Imposible cambiar clave!");
        }
    }//GEN-LAST:event_btnCambioActionPerformed

    private void txtClaveOldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveOldFocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClaveOld.getPassword(), this.txtClaveNew.getPassword(), this.txtClaveNew2.getPassword());
    }//GEN-LAST:event_txtClaveOldFocusLost

    private void txtUserAUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserAUFocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClave1AU.getPassword(), this.txtClave2AU.getPassword());

    }//GEN-LAST:event_txtUserAUFocusLost

    private void txtClave1AUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClave1AUFocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClave1AU.getPassword(), this.txtClave2AU.getPassword());
    }//GEN-LAST:event_txtClave1AUFocusLost

    private void txtClave2AUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClave2AUFocusLost
        // TODO add your handling code here:
        this.checkDatos(this.txtClave1AU.getPassword(), this.txtClave2AU.getPassword());

    }//GEN-LAST:event_txtClave2AUFocusLost

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        if(this.hAU){
            int admin = this.comboAdmin.getSelectedIndex()==0 ?0:1;//0 para no, 1 para si
            conect.CONECTAR();
            String clave = new String(this.txtClave1AU.getPassword());
            conect.EJECUTAR("INSERT INTO usuarios(id_usuario, clave, admin, activo) VALUES('"+this.txtUserAU.getText()+"', '"+clave+"', "+admin+", 1)");
            this.msgAU.setText("");
            this.txtUserAU.setText("");
            this.txtClave1AU.setText("");
            this.txtClave2AU.setText("");
            conect.CERRAR();
            JOptionPane.showMessageDialog(null,"Usuario añadido correctamente!");
            this.hAU=false;
        } else {
            JOptionPane.showMessageDialog(null,"Datos incompletos. Imposible añadir usuario!");
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void txtUserBUFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserBUFocusLost
        // TODO add your handling code here:
        boolean h = this.checkUser(this.txtUserBU.getText());
        if(h){
            this.msgBU.setForeground(Color.GREEN);
            this.msgBU.setText("Pulse el botón para eliminar este usuario.");
            this.hUB=true;
        } else{
            this.msgBU.setForeground(Color.RED);
            this.msgBU.setText("*Usuario no existente.");
            this.hUB=false;
        }
    }//GEN-LAST:event_txtUserBUFocusLost

    private void txtUserBUInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtUserBUInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUserBUInputMethodTextChanged

    private void txtUserBUPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtUserBUPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUserBUPropertyChange

    private void txtUserBUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserBUKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.btnBorrarBU.requestFocus();
        }
    }//GEN-LAST:event_txtUserBUKeyPressed

    private void btnBorrarBUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarBUMouseClicked
        // TODO add your handling code here:
        if(this.hUB){
            String usuario = this.txtUserBU.getText();
            conect.CONECTAR();
            conect.EJECUTAR("UPDATE usuarios SET activo=0 WHERE id_usuario='"+usuario+"';");
            //conect.EJECUTAR("DELETE FROM usuarios WHERE id_usuario='"+usuario+"';");
            this.msgBU.setText("");
            this.txtUserBU.setText("");
            JOptionPane.showMessageDialog(null,"Datos eliminados correctamente");
            conect.CERRAR();
            if(u.getUser().equals(usuario)){
                JOptionPane.showMessageDialog(null, "Se eliminó el usuario actual. El sistema se cerrará!");
                //INICIOUSB ini = new INICIOUSB();

                this.borrarUser.dispose();
                this.dispose();
                //String[] s = new String[1];
                //ini.main(s);
            }
            this.hUB=false;
        } else {
            JOptionPane.showMessageDialog(null,"Usuario a eliminar no existe!");
        }
    }//GEN-LAST:event_btnBorrarBUMouseClicked

    private void tipoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoMActionPerformed
        // TODO add your handling code here:
        TIPOMUEBLE tm = new TIPOMUEBLE();
        tm.setVisible(true);
        this.desktopPane.add(tm);
    }//GEN-LAST:event_tipoMActionPerformed

    private void MaterialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaterialesActionPerformed
        // TODO add your handling code here:
        MATERIALES mat = new MATERIALES();
        mat.setVisible(true);
        this.desktopPane.add(mat);
    }//GEN-LAST:event_MaterialesActionPerformed

    private void ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductosMouseClicked

    private void ProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductosActionPerformed
        // TODO add your handling code here:
        try {
            PRODUCTO prod = new PRODUCTO();
            prod.setVisible(true);
            this.desktopPane.add(prod);
        } catch (SQLException ex) {
            Logger.getLogger(MENUSFM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ProductosActionPerformed

    private void FacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacturarMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_FacturarMouseClicked

    private void FacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacturarActionPerformed
        // TODO add your handling code here:
        FACTURA f = new FACTURA(this.u);
        f.setVisible(true);
        this.desktopPane.add(f);
    }//GEN-LAST:event_FacturarActionPerformed

    private void ReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesActionPerformed
        // TODO add your handling code here:
        REPORTE_FACTURA rf = new REPORTE_FACTURA();
        rf.setVisible(true);
        this.desktopPane.add(rf);
    }//GEN-LAST:event_ReportesActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Clientes;
    private javax.swing.JMenuItem Facturar;
    private javax.swing.JMenuItem Materiales;
    private javax.swing.JMenuItem Productos;
    private javax.swing.JMenuItem Reportes;
    private javax.swing.JDialog agregarUser;
    private javax.swing.JDialog borrarUser;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBorrarBU;
    private javax.swing.JButton btnCambio;
    private javax.swing.JDialog cambioClave;
    private javax.swing.JMenu cambioClaveM;
    private javax.swing.JComboBox<String> comboAdmin;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JLabel msgAU;
    private javax.swing.JLabel msgBU;
    private javax.swing.JLabel msgCC;
    private javax.swing.JMenuItem tipoM;
    private javax.swing.JPasswordField txtClave1AU;
    private javax.swing.JPasswordField txtClave2AU;
    private javax.swing.JPasswordField txtClaveNew;
    private javax.swing.JPasswordField txtClaveNew2;
    private javax.swing.JPasswordField txtClaveOld;
    private javax.swing.JTextField txtUserAU;
    private javax.swing.JTextField txtUserBU;
    // End of variables declaration//GEN-END:variables

}
