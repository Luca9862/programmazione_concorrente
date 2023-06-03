import java.io.Serializable;

class Informazione implements Serializable {
	
private static final long serialVersionUID = 1L;
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
