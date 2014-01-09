/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keot;

/**
 *
 * @author Asennus
 */
public interface SolmullinenKeko {
    public void deleteMin();
    public Solmu etsiKeosta(int node);
    public void insertWithNode(int key, int node);
    public Solmu findMinSolmu();
    public void decreaseKey(Solmu s,int d);
    public void luoPointteritaulukko(int pituus);
}
