
package kekovertailu;

/**
 *
 * @author Riikka
 */
public class Solmu {
    
    private int arvo;
    private Solmu parent;
    private Solmu[] lapset;
    private Solmu seuraava;
    
    public Solmu(int arvo, Solmu parent,Solmu[] lapset){
        this.arvo=arvo;
        this.parent=parent;
        this.lapset=lapset;
        this.seuraava=null;
    }
    
    public Solmu getParent(){
        return parent;
    }
    public int getArvo(){
        return arvo;
    }
    public Solmu[] getLapset(){
        return lapset;
    }
    public Solmu getSeuraava(){
        return seuraava;
    }
    /**
     * Lisää solmuun lapsen s.
     * @param s lisättävä lapsi
     */
    public void lisaaLapsi(Solmu s){
        Solmu[] uusi=new Solmu[lapset.length+1];
        for(int i=0;i<lapset.length;i++){
            uusi[i]=lapset[i];
        }
        uusi[lapset.length]=s;
        lapset=uusi;
    }
    /**
     * Asettaa solmulle s seuraavan solmun. Keko toteutetaan juurisolmujen 
     * linkitettynä listana; tätä käytetään siihen.
     * @param s Solmu, joka halutaan asettaa seuraavaksi.
     */
    public void setSeuraava(Solmu s){
        seuraava=s;
    }
}
