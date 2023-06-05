package soluzioneRmi;

import jdk.jshell.execution.JdiExecutionControlProvider;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    private static final long serialVersionUID = 1L;
    Deposito d;
    public ServerImpl() throws RemoteException{
        super();
        Deposito d = new Deposito();
    }

    public RichiestaRisposta LeggiQualunque() throws RemoteException, InterruptedException {
        return d.LeggiQualunque();
    }
    public RichiestaRisposta LeggiIdx(int idx) throws RemoteException, InterruptedException {
        return d.LeggiIdx(idx);
    }

    public void Aggiungi(RichiestaRisposta rr) throws RemoteException {
        return d.Aggiungi();
    }
}
