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
public interface Keko {
    public void deleteMin(); 
    public void insert(Solmu s);
    public void insert(int key);
    public int findMin();
    public Solmu findMinSolmu();
    public void decreaseKey(Solmu s, int d);
}
