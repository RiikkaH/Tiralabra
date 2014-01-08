/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keot;

/**
 *
 * @author juhorim
 */
public interface SolmutonKeko {
    public int getSize();
    public int findMin();
    public int findMinNode();
    public void insertWithNode(int key,int n);
    public void deleteMinJaNode();
    public int etsiKeosta(int n);
    public void decreaseKeyWithNode(int i,int n,boolean onNode);
}
