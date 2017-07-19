
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Lütfullah TÜRKER
 * @param <T> Generic Type
 */
public class PreOrderBinTree<T extends Comparable<T>> extends BinaryTree<T> implements Iterable<T> {

    private int index = 0;
    private ArrayList<T> elements = new ArrayList<> ();
    
    
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        elements.clear();
        preOrderTraverse(PreOrderBinTree.this.root, elements);
        for (int i = 0; i < elements.size();++i)
            result.append(elements.get(i).toString()).append("\n");
        return result.toString();
    }
    
    
    /**
     * Kitaptan yararlanılarak fonksiyon değiştirilip ArrayList e yazılmıştır.
     * @param node
     * @param arr
     */
    private void preOrderTraverse(Node<T> node,ArrayList<T> arr) {
       
        if (node == null) {
        } else {
            arr.add(node.data);
            preOrderTraverse(node.left, arr);
            preOrderTraverse(node.right, arr);
        }
    }
    
    
    @Override
    public Iterator<T> iterator() {
        return new preOrderIter();
    }
    private class  preOrderIter implements Iterator {

        public preOrderIter() {
            preOrderTraverse(PreOrderBinTree.this.root, elements); 
        }
        
        
        
        @Override
        public boolean hasNext() {
            return elements.size()-1 >= index;
        }

        @Override
        public T next() {
            return elements.get(index++);  
        }
    }
}