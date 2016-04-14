/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafacturacionmuebles;

/**
 *
 * @author Kevin
 */
public class USER {
    String nUser;
    String clave;
    boolean admin;
    
    public USER(String u, String c, boolean ad){
        this.nUser=u;
        this.clave=c;
        this.admin=ad;
    }
    public String getUser(){
        return this.nUser;
    }
    public String getClave(){
        return this.clave;
    }
    public boolean getAdmin(){
        return this.admin;
    }
    public void setClave(String c){
        this.clave=c;
    }
}
