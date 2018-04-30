/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiteste2;

import rmiteste2.Servico2;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL-Fabio
 */
public class Servidor2 extends UnicastRemoteObject implements Servico2{

    public Servidor2() throws RemoteException {
        super();
    }
    
    
    public static void main(String[] args)
    {
        try {
            Servidor2 servidor = new Servidor2();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("TesteLucas",  servidor);
            System.out.println("Servidor iniciado");
                        
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage() );
        }
    }

    @Override
    public String getDataHora() throws RemoteException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return sdf.format(Calendar.getInstance().getTime());
    }

    @Override
    public String invertString(String string) throws RemoteException {
       String retorno = "";
       StringBuilder str = new StringBuilder(string);
       retorno = str.reverse().toString();
       return retorno;       
    }
    
    @Override
    public obClienteBanco2 Objeto() throws RemoteException {
        obClienteBanco2 c =  new obClienteBanco2();
        c.setNome("Fábio");
        c.setEndereco("CCT");
        c.setCidade("Bandeirantes");
        c.setIdade(22);
        return c;
    }
    
    @Override
    public boolean ConectaBanco(String nome, String senha) throws RemoteException
    {
        banco2 b = new banco2();
        int count = 0;
        try {
            Connection con = b.Conexao();
            Statement stmt = null;
            String sql = "select * from cliente where nome = '" + 
                    nome + "' and senha = '" + senha + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                count++;
            }
            System.out.println(count);
        } catch (SQLException ex) {
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (count > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public double Saldo(String nome, String senha) throws RemoteException
    {
        banco2 b = new banco2();
        double valor = 0;
        try {
            Connection con = b.Conexao();
            Statement stmt = null;
            String sql = "select saldo from cliente where nome = '" + 
                    nome + "' and senha = '" + senha + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                valor = rs.getDouble("saldo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    
    @Override
    public double atualizarSaldo(String nome, String senha, Double saldoAtualizado) throws RemoteException{
        double valor = 0;
        banco2 b = new banco2();
        try {
            Connection con = b.Conexao();
            Statement stmt = null;
            String sql = "UPDATE cliente SET saldo = '"+Double.toString(saldoAtualizado)+"' WHERE nome = '" + 
                    nome + "' and senha = '" + senha + "'";
            
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                valor = rs.getDouble("saldo");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
        //valor = 45;
        return valor;  
    }
    
    @Override
    public double depositar(String nome, String senha, Double saldoAtualizado) throws RemoteException{
        double valor = 0;
        banco2 b = new banco2();
        try {
            Connection con = b.Conexao();
            Statement stmt = null;
            String sql = "UPDATE cliente SET saldo = '"+Double.toString(saldoAtualizado)+"' WHERE nome = '" + 
                    nome + "' and senha = '" + senha + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                valor = rs.getDouble("saldo"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Servidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
        //valor = 45;
        return valor;  
    }
}
