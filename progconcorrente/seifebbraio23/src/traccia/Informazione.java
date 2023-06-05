package traccia;

class Informazione {
  private String descrizione;
  private long timestamp;
  Informazione (String d) {
    this.descrizione=d;
    this.timestamp=System.currentTimeMillis();
  }
  public String toString () {
    return descrizione + " ("+ timestamp+ ")";
  }
  public String getInfo(){
	  return descrizione;
  }
  public long getInfoTime(){
	  return timestamp;
  }
}
