
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Lütfullah TÜRKER
 * @param <T> Treenin tipini belirten Generic Type
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
    
    /** Return value from the public add method. */
    private boolean addReturn;
    private int index = 0;
    private ArrayList<Node<T>> elements = new ArrayList<> ();
    /**
     * Kitabın BinarySearchTree fonksiyonlarından yararlanılmıştır.
     * @param item The object being inserted
     * @return true if the object is inserted, false
     *         if the object already exists in the tree
     */
    @Override
    public boolean add(T item) {
        root = add(root, item);
        return addReturn;
    }
    /**
     * Recursive add method.
     * @post The data field addReturn is set true if the item is added to
     *       the tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the
     *         inserted item
     */
    private Node<T> add(Node<T> localRoot, T item) {
        if (localRoot == null) {
            addReturn = true;
            return new Node<>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        elements.clear();
        levelOrderTraverse();
        for (int i = 0; i < elements.size();++i)
            result.append(elements.get(i).toString()).append("\n");
        return result.toString();
    }
    
    /**
     * Recursive şekilde LevelOrderTraverse yapan fonksiyon.Traverse listeyi ArrayListe yazar.
     */
    private void levelOrderTraverse() 
    {
        int i = 0;
        if (root != null)
            elements.add(root);
        else
            return ;
        Node <T> tempNode = root;
        while (tempNode != null)
        {
            if (i < elements.size())
                tempNode = elements.get(i++);
            else
                tempNode = null ;
            if (tempNode != null && tempNode.left != null) {
                elements.add(tempNode.left);
            }
            if (tempNode != null && tempNode.right != null) {
                elements.add(tempNode.right);
            }
        }
    }
    
    
    public Iterator<T> iterator() {
        return new levelOrderIter();
    }
    private class  levelOrderIter implements Iterator {

        private int index ;
        
        public levelOrderIter() {
            levelOrderTraverse(); 
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return elements.size()-1 >= index;
        }

        @Override
        public T next() {
            return elements.get(index++).data;  
        }
    }
    
}
