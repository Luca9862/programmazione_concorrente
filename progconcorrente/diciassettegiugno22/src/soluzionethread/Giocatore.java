package soluzionethread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Giocatore extends Thread{

    int id = 0;
    Partita partita;
    CyclicBarrier barrier;

    public Giocatore(){
        id += 1;
        //this.partita = match;
    }

    @Override
    public void run() {

    }
}
