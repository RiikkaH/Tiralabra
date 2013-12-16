
package kekovertailu;

/**
 *
 * @author Riikka
 */
public interface Keko {
    void heapify();
    int findMin();
    void deleteMin();
    void decreaseKey();
    void insert(int key);
    Keko merge(Keko k);
}
