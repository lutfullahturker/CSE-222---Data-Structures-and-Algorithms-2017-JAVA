
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author osboxes
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class SingleLinkedList<T> implements Iterable<T>{
    
    private Node<T> head = null;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new myIterator<>();
    }
    
    private static class Node<T>{
        private T data;
        private Node<T> next;
        
        private Node(T dataItem)
        {
            data = dataItem;
            next =null;
        }
        private Node(T dataItem,Node<T> nodeRef)
        {
            data = dataItem;
            next = nodeRef;
        }   
    }
    
    private class myIterator<T> implements Iterator<T>{

        private Node<T> nextItem;
        private Node<T> lastItemReturned;
        private int index=0;
            
        public myIterator()
        {
            lastItemReturned = null;
            nextItem = (Node<T>) head;
        }
        @Override
        public boolean hasNext() {
            return (nextItem != null) ;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }
        
        
    }
    
    private void addFirst (T item)
    {
        head = new Node<>(item,head);
        size++;
    }
    private void addAfter (Node<T> node ,T item)
    {
        node.next = new Node<>(item,node.next);
        size++;
    }
    private T removeAfter(Node<T> node)
    {
        Node<T> temp = node.next;
        if (temp != null)
        {
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else 
            return null ;
    }
    private T removeFirst(){
        Node<T> temp = head;
        if (head != null)
            head = head.next;
        if (temp != null)
        {
            size--;
            return temp.data;
        }
        else
            return null;
    }
    
    private Node<T> getNode(int index)
    {
        Node<T> node = head;
        for(int i =0;i<index &&node != null;++i)
            node = node.next;
        return node;
    }
    public T get(int index){
        if (index <0 || index>=size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        Node<T> temp = getNode(index);
        return temp.data;
    }
    public T set(int index ,T newVal)
    {
        if (index <0 || index>=size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        Node<T> temp = getNode(index);
        T result = temp.data;
        temp.data = newVal;
        return result;
    }
    public void add (int index ,T addItem)
    {
        if (index <0 || index>size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        if (index ==0)
            addFirst(addItem);
        else
        {
            Node<T> newNode =getNode(index-1);
            addAfter(newNode,addItem);
        }
    }
    public void add(T addItem){
        add(size,addItem);
    }
    public void remove(int index){
        if (index <0 || index>=size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        if (index == 0)
            removeFirst();
        else
            removeAfter(getNode(index-1));
    }
    public int getSize(){
        return size;
    }
    
    @Override
    public String toString()
    {
        Node<T> nodeRef = head;
        String print = "[";
        while (nodeRef != null)
        {
            print = print.concat(nodeRef.data.toString());
            if(nodeRef.next != null)
                print += ", ";
            nodeRef = nodeRef.next;
        }
        print += "]";
        return print;
    }
}
