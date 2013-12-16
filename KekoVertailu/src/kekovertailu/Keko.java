
package kekovertailu;

/**
 *
 * @author Riikka
 */
public interface Keko {
    void heapify(int key);
    int findMin();
    void deleteMin();
    void decreaseKey(int i,int d);
    void insert(int key);
    Keko merge(Keko k);
}
