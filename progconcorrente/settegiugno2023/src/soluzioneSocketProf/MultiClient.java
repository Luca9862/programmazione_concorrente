package soluzioneSocketProf;

public class MultiClient {

	private static final int Nthreads = 4;

	public static void main(String[] args) {
		for(int i=0; i<Nthreads; i++) {
			new ClientThread(i).start();
		}
	}
}
