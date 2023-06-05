package soluzioneRmi;

import java.rmi.RemoteException;

public interface ServerInterface {
    public RichiestaRisposta LeggiQualunque() throws RemoteException, InterruptedException;
    public int rispostaDisponibile() throws RemoteException;
    public RichiestaRisposta LeggiIdx(int idx) throws RemoteException, InterruptedException;
    public void Aggiungi(RichiestaRisposta rr) throws RemoteException;
}
