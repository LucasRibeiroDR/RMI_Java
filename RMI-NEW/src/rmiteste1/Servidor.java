/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiteste1;

import rmiteste1.Servico;
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
public class Servidor extends UnicastRemoteObject implements Servico{

    public Servidor() throws RemoteException {
        super();
    }
    
    
    public static void main(String[] args)
    {
        try {
            Servidor servidor = new Servidor();
            Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind("TesteFabio",  servidor);
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
    public obClienteBanco Objeto() throws RemoteException {
        obClienteBanco c =  new obClienteBanco();
        c.setNome("Fábio");
        c.setEndereco("CCT");
        c.setCidade("Bandeirantes");
        c.setIdade(22);
        return c;
    }
    
    @Override
    public boolean ConectaBanco(String nome, String senha) throws RemoteException
    {
        banco b = new banco();
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
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
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
        banco b = new banco();
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
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    
    @Override
    public double atualizarSaldo(String nome, String senha, Double saldoAtualizado) throws RemoteException{
        double valor = 0;
        banco b = new banco();
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
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //valor = 45;
        return valor;  
    }
    
    @Override
    public double depositar(String nome, String senha, Double saldoAtualizado) throws RemoteException{
        double valor = 0;
        banco b = new banco();
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
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //valor = 45;
        return valor;  
    }
}
