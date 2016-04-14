/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafacturacionmuebles;
import java.sql.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author kevin
 */
public class CONEXION { //Esta clase puede llamarse conexion
    //Datos de la conexión
    public String servidor = "sql5.freemysqlhosting.net";
    public String nameDB = "sql5113770";
    public String login = "sql5113770";
    public String password = "RXv6DPXMwF";
    
    public String url = "jdbc:mysql://"+servidor+":3306/"+nameDB;
    public Connection Link = null;
    
    //Método Conectar a la base de datos
    public boolean CONECTAR(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.Link = DriverManager.getConnection(this.url, this.login, this.password);
            if(this.Link!=null){
                return true;
            }
        }
        catch(SQLException ex){
            System.out.println("ERROR DE CONEXION A: "+this.url);
        }
        catch(ClassNotFoundException ex){ //Para error de SQL
            System.out.println(ex);
        }
        return false;
    }
    //Metodo Result Set Consultar
    public ResultSet CONSULTAR(String SQL){
        try
        {
            //Arreglo que almacenara el conjunto de informacion
            PreparedStatement Query = this.getConnection().prepareStatement(SQL);
            ResultSet Resultado = Query.executeQuery(); //Variable de tipo librería ResultSet
            //El executeQuery manda a ejecutar la sentencia
            return Resultado;
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: "+ex);
            return null;
        }
    }
    private Connection getConnection() {
        return this.Link;
    }
    //METODO PARA DESCONECTAR DE LA BASE DE DATOS
    public void CERRAR(){
        this.Link = null;
    }
    //METODO EJECUTAR
    public boolean EJECUTAR(String SQL){
        try
        {
            PreparedStatement Query = this.getConnection().prepareStatement(SQL);
            Query.execute();
            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
    
    
}
