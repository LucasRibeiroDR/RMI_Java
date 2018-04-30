/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiteste2;
import rmiteste2.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import rmiteste2.obClienteBanco2;

/**
 *
 * @author DELL-Fabio
 */
public interface Servico2 extends Remote{
    public String getDataHora() throws RemoteException;
    public String invertString(String string) throws RemoteException;
    public obClienteBanco2 Objeto() throws RemoteException;

    public boolean ConectaBanco(String nome, String senha) throws RemoteException;
    public double Saldo(String nome, String senha) throws RemoteException;
    public double atualizarSaldo(String nome, String senha, Double saldoAtualizado) throws RemoteException;
    public double depositar(String nome, String senha, Double saldoAtualizado) throws RemoteException;}