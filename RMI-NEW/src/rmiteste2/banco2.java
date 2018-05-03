/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiteste2;

import rmiteste1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL-Fabio
 */
public class banco2 {
   public String url = "jdbc:postgresql://localhost:5432/banco2";  
   public String usuario = "postgres";  
   public String senha = "postgres";  
   
   
   public Connection Conexao() throws SQLException
   {
        Connection con;   
        con = DriverManager.getConnection(url, usuario, senha);    
        System.out.println("Conexão realizada com sucesso.");  
        return con;
   }
    
}
