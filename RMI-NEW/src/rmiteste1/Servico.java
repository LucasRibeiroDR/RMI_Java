/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiteste1;
import java.rmi.Remote;
import java.rmi.RemoteException;
import rmiteste1.obClienteBanco;

/**
 *
 * @author DELL-Fabio
 */
public interface Servico extends Remote{
    public String getDataHora() throws RemoteException;
    public String invertString(String string) throws RemoteException;
    public obClienteBanco Objeto() throws RemoteException;

    public boolean ConectaBanco(String nome, String senha) throws RemoteException;
    public double Saldo(String nome, String senha) throws RemoteException;
    public double atualizarSaldo(String nome, String senha, Double saldoAtualizado) throws RemoteException;
    public double depositar(String nome, String senha, Double saldoAtualizado) throws RemoteException;}