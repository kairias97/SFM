/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inicio_sesion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrador
 */
public class INICIOUSB {

    /**
     * @param args the command line arguments
     */
    public static String leerUSB(String[] Puerto){
        try {  
           int n = Puerto.length;
            for (int i = 0; i < n; i++) {
                String s="";
                Process p = Runtime.getRuntime().exec("cmd /C vol "+Puerto[i]+":");  
                BufferedReader in = new BufferedReader(  
                                new InputStreamReader(p.getInputStream()));  
                String line = null;  
                while ((line = in.readLine()) != null) { 
                    s=line;//Al final leera la ultima linea
                }  
                p.destroy();
                if(!s.equals("")){
                    return s;
                }
            }
           
            
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return "";
    
    }
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(INICIOUSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(INICIOUSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(INICIOUSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(INICIOUSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigo="";
        String[] P = {"F","G","H","I","J","K","L","E","D"};
        codigo=leerUSB(P);
        codigo=codigo.substring(36);
        //JOptionPane.showMessageDialog(null, codigo);
        
        if(codigo.equals("8218-BE34") || codigo.equals("DC5E-908A")){
            LOGIN l = new LOGIN();
            l.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente la usb");
            MSGUSB m = new MSGUSB();
            m.setVisible(true);
        }
        
    }
    
}
