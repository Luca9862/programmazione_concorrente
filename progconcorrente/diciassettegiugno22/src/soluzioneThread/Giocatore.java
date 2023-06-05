package soluzioneThread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Giocatore extends Thread{

    int identificativo;
    Partita partita;
    CyclicBarrier c1;
    CyclicBarrier c2;
    Random rnd;



    public Giocatore(int id, Partita p, CyclicBarrier c1, CyclicBarrier c2){
        this.identificativo=id;
        this.partita=p;
        this.c1=c1;
        this.c2=c2;
    }

    public void run(){
        rnd=new Random();
        Partita laPartita=new Partita(4);
        int carta = rnd.nextInt(10);
        for(int j=0; j<laPartita.numMani(); j++) {
            System.out.println("Giocatore "+this.identificativo+" gioco la mano "+j);
            laPartita.giocata(this.identificativo, carta);
            try {
                System.out.println("Giocatore "+this.identificativo+" ho giocato, mi metto in attesa degli altri");
                c1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Giocatore "+identificativo+" ho fatto "+laPartita.puntiMano(carta)+
                        " punti in questa mano");
            try {
                System.out.println("Giocatore "+this.identificativo+" ho calcolato i puntiMano, mi metto in attesa degli altri");
                c2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("partita terminata");
    }
}
