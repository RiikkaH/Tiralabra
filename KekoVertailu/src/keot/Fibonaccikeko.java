package keot;

/**
 *
 * @author Riikka
 */
public class Fibonaccikeko implements Keko {

    private Solmu keko;//linkitetty lista juurisolmuista, osoittaa ensimmäiseen
    private Solmu min;
    private int solmuja;

    public Fibonaccikeko() {
        keko = null;
        min = keko;
        solmuja = 0;
    }

    public Fibonaccikeko(Solmu s) {
        keko = s;
        min = s;
        solmuja = 1;
    }

    /**
     * Etsii ja palauttaa keon pienimmän solmun.
     *
     * @return keon pienin solmu
     */
    @Override
    public Solmu findMinSolmu() {
        if(solmuja==0){
            return null;
        }
        return min;
    }

    /**
     * Palauttaa keon pienimmän solmun arvon.
     *
     * @return keon pienimmän solmun arvo
     */
    @Override
    public int findMin() {
        return min.getArvo();
    }

    /**
     * Poistaa keon pienimmän alkion ja tiivistää keon.
     */
    @Override
    public void deleteMin() {
        //System.out.println("seuraavaksi poistetaan min "+min.getArvo()+","+min.getNode()+", solmuja "+solmuja);
        poistaMinLisaaLapset();
        //System.out.println("min poistettu ja lapset lisätty. seuraavaksi tiivistetään keko");
        yhdistaKeonPuut();
        paivitaMin();
        solmuja--;
        //System.out.println("min poistettu! solmuja "+solmuja);
    }

    /**
     * Yhdistelee keon puita niin, ettei millään kahdella puulla ole samaa
     * astetta.
     */
    private void yhdistaKeonPuut() {
        Solmu[] lista = new Solmu[solmuja];//listassa kohdassa n on viite kekoon, jonka aste on n
        Solmu edellinen = null;
        Solmu nyk = keko;
        while (nyk != null) {
            //listassa ei ole ennestään tämänasteista solmua
            if (lista[nyk.getAste()] == null) {
                lista[nyk.getAste()] = nyk;
                edellinen = nyk;
                nyk = nyk.getSeuraava();
            }//keossa on tämänasteinen solmu 
            else {
                Solmu s = lista[nyk.getAste()];
                //irrotetaan samanasteinen solmu keosta
                if (s.getEdellinen() != null) {
                    s.getEdellinen().setSeuraava(s.getSeuraava());
                    s.getSeuraava().setEdellinen(s.getEdellinen());
                } else {
                    s.getSeuraava().setEdellinen(null);
                }
                s.setSeuraava(null);
                s.setEdellinen(null);
                Solmu ed = nyk.getEdellinen();
                Solmu seur = nyk.getSeuraava();
                lista[nyk.getAste()] = null;
                nyk.setSeuraava(null);
                nyk.setEdellinen(null);
                //yhdistetään nyk ja s ja korvataan nyk solmulla uusi
                Solmu uusi = yhdistaPuut(nyk, s);
                uusi.setEdellinen(ed);
                uusi.setSeuraava(seur);
                if (seur != null) {
                    seur.setEdellinen(uusi);
                }
                if (ed != null) {
                    ed.setSeuraava(uusi);
                }
                edellinen = uusi;
                nyk = uusi;
            }
            //System.out.println("looppina while, metodi yhdistaKeonPuut");
        }
        //päivitetään keko osoittamaan oikeaa kohtaa
        while (edellinen != null && edellinen.getEdellinen() != null) {
            edellinen = edellinen.getEdellinen();
            //System.out.println("yhdistellään keon puita, jälkimmäinen looppi");
        }
        //System.out.println("päästiin pois puiden yhdistämisestä");
        keko = edellinen;//edellinen==null vain jos keko==null alussa
    }

    private Solmu yhdistaPuut(Solmu s, Solmu t) {
        //System.out.println("pitää yhdistää puita");
        if (s.getArvo() < t.getArvo()) {
            Solmu apu = s;
            s = t;
            t = apu;
        }
        lisaaLapsi(t, s);
        //System.out.println("puut yhdstettiin");
        return t;
    }

    /**
     * Lisää solmulle p lapsen l
     *
     * @param p solmu, johon lapsi lisätään
     * @param l lisättävä lapsi
     */
    //lisättävä tulee aina lapsista viimeiseksi
    private void lisaaLapsi(Solmu p, Solmu l) {
        if (p.getLapsi() == null) {
            //System.out.println("aste == 0");
            l.setParent(p);
            p.setLapsi(l);
            l.setEdellinen(null);
            l.setSeuraava(null);
        } else {
            //System.out.println("else");
            Solmu s = p.getLapsi();
            while (s.getSeuraava() != null) {
                s = s.getSeuraava();
                //System.out.println("while... solmu on "+s.getArvo()+","+s.getAste());
            }
            l.setParent(p);
            s.setSeuraava(l);
            l.setEdellinen(s);
            l.setSeuraava(null);
        }
        p.setAste(p.getAste() + 1);
    }

    public Solmu getKeko() {
        return keko;
    }

    /**
     * Päivittää min:in arvon.
     */
    private void paivitaMin() {
        Solmu uusimin = keko;
        Solmu s = keko;
        while (s != null && s.getSeuraava() != null) {
            if (s.getArvo() < uusimin.getArvo()) {
                uusimin = s;
            }
            s = s.getSeuraava();
            //System.out.println("paivitaMin:in looppi");
        }
        //viimeisen solmun tarkistaminen voisi myös olla hyödyllistä
        if (s != null && s.getArvo() < uusimin.getArvo()) {
            uusimin = s;
        }
        min = uusimin;
    }

    /**
     * Poistaa keosta minimialkion ja lisää sen lapset kekoon.
     */
    private void poistaMinLisaaLapset() {
        Solmu uudetpuut = min.getLapsi();
        //poistetaan min eli päivitetään pointterit sen ohi
        Solmu ed = min.getEdellinen();
        Solmu seur = min.getSeuraava();
        min.setSeuraava(null);
        min.setEdellinen(null);
        min.setLapsi(null);
        Solmu s = null;
        if (ed != null) {
            //System.out.println("ed: "+ed.getArvo()+","+ed.getNode());
            ed.setSeuraava(seur);
            s = ed;
            //System.out.println("s = ed");
        }
        if (seur != null) {
            //System.out.println("seur: "+seur.getArvo()+","+seur.getNode());
            seur.setEdellinen(ed);
            s = seur;
            //System.out.println("s = seur");
        }
        //jos min oli ainoa solmu, voidaan uudetpuut laittaa suoraan keoksi
        if (s == null) {
            //System.out.println("min oli ainoa");
            keko = uudetpuut;
            if (uudetpuut != null) {
                //uusillapuilla ei tietenkään ole enää parent:ia.
                uudetpuut.setParent(null);
                while (uudetpuut.getSeuraava() != null) {
                    uudetpuut = uudetpuut.getSeuraava();
                    uudetpuut.setParent(null);
                    //System.out.println("uudetpuut.parent=null");
                }
            }
            return;
        }
        Solmu tallennettu = s;//s ei ole null
        //min ei ollut ainoa ja lapsia on
        //min on poistettu, laitetaan lapset listaan
        if (uudetpuut != null) {
            //System.out.println("min ei ollut ainoa, uudetpuut!=null");
            seur=s.getSeuraava();
            s.setSeuraava(uudetpuut);
            uudetpuut.setEdellinen(s);
            uudetpuut.setParent(null);
            while(uudetpuut.getSeuraava()!=null){
                uudetpuut=uudetpuut.getSeuraava();
                uudetpuut.setParent(null); 
                //System.out.println("käydään uudetpuut läpi");
            }
            uudetpuut.setSeuraava(seur);
            if(seur!=null){
                seur.setEdellinen(uudetpuut);
            }
        }
        //System.out.println("enää kekopointterin päivitys");
        //päivitetään kekopointteri osoittamaan oikein
        while (tallennettu.getEdellinen() != null) {
            tallennettu = tallennettu.getEdellinen();
            //System.out.println("while");
        }
        keko = tallennettu;
    }

    /**
     * Vähentää solmun s arvon d:hen.
     *
     * @param s solmu, jonka arvoa vähennetään
     * @param d solmun uusi arvo
     */
    @Override
    public void decreaseKey(Solmu s, int d) {
        if (d < s.getArvo() && d > 0) {
            s.setArvo(d);
            //jos kekoehto rikkoutuu
            if (s.getParent() != null && s.getParent().getArvo() > d) {
                Solmu p = leikkaaJaLiita(s);
                //jos parent ei ole merkattu, se merkataan. jos se on merkattu, sekin
                //leikataan pois
                boolean jatkuu = true;
                while (p != null && jatkuu) {
                    if (p.getParent() == null) {
                        jatkuu = false;
                    } else if (p.getMarked() == 0) {
                        p.mark();
                        jatkuu = false;
                    } else if (p.getMarked() == 1) {
                        p = leikkaaJaLiita(p);
                    }else{
                        jatkuu=false;
                    }
                }
            }
        }
    }

    /**
     * Irrottaa solmun vanhemmastaan ja lisää kekoon.
     *
     * @param s
     * @return
     */
    private Solmu leikkaaJaLiita(Solmu s) {
        Solmu p = s.getParent();
        //pitää tarkistaa onko ainoa lapsi, pitääkö vanhemman lapsipointteria siirtää
        //parent osoittaa tähän, mutta sisaruksia on: parentin pointteria pitää siirtää
        if (p != null && s.getSeuraava() != null) {
            p.setLapsi(s.getSeuraava());
            s.setSeuraava(null);
        } //tämä on ainoa
        else if (p != null && p.getSeuraava() == null) {
            p.setLapsi(null);
        }
        if (p != null) {
            p.setAste(p.getAste() - 1);
        }
        //jos parent osoittaa muualle tai sitä ei ole, sille ei tarvitse tehdä mitään
        s.setParent(null);
        s.setEdellinen(null);
        s.setSeuraava(null); 
        s.unmark();
        insert(s);//tämä päivittää minin
        return p;
    }

    /**
     * Lisää kekoon uuden solmun. Paivittaa myös minin arvon.
     *
     * @param s kekoon lisättävä solmu
     */
    @Override
    public void insert(Solmu s) {
        s.setParent(null);
        if (keko == null) {
            keko = s;
            min = s;
        } else {
            if (keko.getSeuraava() != null) {
                Solmu apu = keko.getSeuraava();
                keko.setSeuraava(s);
                s.setSeuraava(apu);
                apu.setEdellinen(s);
                s.setEdellinen(keko);
            } else {
                keko.setSeuraava(s);
                s.setEdellinen(keko);
                s.setSeuraava(null);
            }
            if (s.getArvo() < min.getArvo()) {
                min = s;
            }
        }
        solmuja++;
    }

    /**
     * Lisää kekoon uuden solmun arvolla key.
     *
     * @param key uuden solmun arvo
     */
    @Override
    public void insert(int key) {
        Solmu s = new Solmu(key, null, null, 0);
        insert(s);
    }

}
