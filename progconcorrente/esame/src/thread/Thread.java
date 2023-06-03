package thread;

/*
    in generale nel primo esercizio bisogna operare principalmente nella classe che rappresenta la risorsa condivisa
    (coda, tavolo, rubrica..)

    1.Mettere synchronized ai metodi dove necessario
    2.Mettere wait() e notify() all'interno delle funzioni che lo richiedono
    3.ATTENZIONE: a volte serve un if e a volte un while in base alla tipologia di applicazione
    4.Inserire i notify()/notifyAll() dove necessario
*/
public class Thread {
    /*

    */
    public synchronized void sezioneCritica1(){ //inserisco synchronized
        boolean condizione = true;
        while(condizione){ //while o if in base al tipo di applicazione
            try{
                wait(); //inserisco la wait
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        //faccio qualcosa...
        notifyAll(); //inserisco la notify per svegliare altri thread (se necessaria)
    }

    public synchronized void sezioneCritica2(){
        //faccio qualcosa...
        notifyAll(); //inserisco la notify per svegliare altri thread
    }

}
