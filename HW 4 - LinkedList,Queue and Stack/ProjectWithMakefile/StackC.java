
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osboxes
 * @param <T>
 */
public class StackC<T> implements StackInterface<T>{
    
    
    private Node<T> head = null;
    private int size = 0;

    public StackC() {
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
    
    
    /**
     * Gelen elemanı stack e ekler.
     * @param item Eklenecek eleman
     * @return
     */
    @Override
    public T push(Object item) 
    {
        if (head == null)
            head = new Node<>((T)item);
        else 
        {
            Node<T> node = head;
            for(int i = 0;i<size-1 && node != null;++i)
                node = node.next;
            node.next = new Node<>((T)item,node.next); 
        }
        size++;
        return (T) item;
    }

    /**
     * Stack in son elemanını stackden çıkarır ve çıkardığı elemanı return eder.
     * @return
     */
    @Override
    public T pop() 
    {
        if (size <= 0)
            throw new NoSuchElementException("Size 0 ! ");
        T temp ;
        Node<T> node = head;
        for(int i = 0;i<size-2 && node != null;++i)
                node = node.next;
        temp = node.next.data;
        node.next = null;
        size--;
        return temp;
    }

    /**
     *
     * @return Stack boşsa true doluysa false return eder.
     */
    @Override
    public boolean isEmpty() 
    {
        return size()==0;
    }

    /**
     * Stack'in boyutunu (eleman sayısını) return eder.
     * @return
     */
    @Override
    public int size() 
    {
        return size ;
    }   
    /**
     * toString fonksiyonu Stack in tüm elemanlarını aralarına virgül(',') koyarak
     * bir stringe yazar ve onu return eder.Sonuna '\n' karakteri eklenir.
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append(this.size());
        Node<T> temp = head;
        for (int i = 0;i<this.size();++i){
            result.append(",").append(temp.data);
            temp =  temp.next;
        }
        String[] arr = result.toString().split(",");
        result = new StringBuilder();
        result.append(arr[0]);
        for (int j=arr.length-1;j>=0;--j){
            if (j == 0);
            else
                result.append(",").append(arr[j]);
        }
        result.append("\n");
        return result.toString();
    }
    
}
