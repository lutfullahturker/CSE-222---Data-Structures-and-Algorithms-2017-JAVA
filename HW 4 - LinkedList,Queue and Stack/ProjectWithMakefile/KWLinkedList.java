/**
 *
 * @author osboxes
 */

import java.util.*;


public class KWLinkedList < E >{
    

  private Node < E > head = null;
  private Node < E > tail = null;
  private int size = 0;
  
  /** 
        @param item
   */
  public void addFirst(E item) {
    add(0, item);
  }

    /**
     *
     * @return
     */
    public int size()
  {
      return size;
  }
  
  
  /**
      @param item 
   */
  public void addLast(E item) {
    add(size, item);
  }

  /**
      @return 
   */
  public E getFirst() {
    return head.data;
  }

  /**
      @return 
   */
  public E getLast() {
    return tail.data;
  }

  /**
          @return 
   */
  public Iterator < E > iterator() {
    return new KWListIter(0);
  }

  /**
      @return 
   */
  public ListIterator < E > listIterator() {
    return new KWListIter(0);
  }

  /**
      @param index 
      @return 
   */
  public ListIterator < E > listIterator(int index) {
    return new KWListIter(index);
  }


    /**
     *
     * @param index
     * @param obj
     */
    public void add(int index, E obj) {
        listIterator(index).add(obj);
  }

    /**
     *
     * @param index
     * @return
     */
    public E get(int index) {
        return listIterator(index).next();
  }
 /**
  * 
  * @param index
  * @return 
  */
    private Node<E> getNode(int index)
    {
        Node<E> node = head;
        for(int i =0;i<index &&node != null;++i)
            node = node.next;
        return node;
    }
    /**
     * 
     * @param index
     * @param set 
     */
    public void set(int index,E set){
        getNode(index).data = set;
    }
    
  private static class Node < E > {
    private E data;

   
    private Node < E > next = null;

    
    private Node < E > prev = null;

    /** 
        @param dataItem
     */
    private Node(E dataItem) {
      data = dataItem;
    }
  }

  private class KWListIter implements ListIterator < E > {
    private Node < E > nextItem;

    private Node < E > lastItemReturned;
    private int index = 0;

    public KWListIter(int i) {
      if (i < 0 || i > size) {
        throw new IndexOutOfBoundsException(
            "Invalid index " + i);
      }
      lastItemReturned = null; 
      
      if (i == size) {
        index = size;
        nextItem = null;
      }
      else { 
        nextItem = head;
        for (index = 0; index < i; index++) {
          nextItem = nextItem.next;
        }
      }
    }

    @Override
    public boolean hasNext() {
      return nextItem != null;
    }

    /** 
        @return 
        @throws 
     */
    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      lastItemReturned = nextItem;
      nextItem = nextItem.next;
      index++;
      return lastItemReturned.data;
    }

    /** 
        @return
     */
    @Override
    public boolean hasPrevious() {
      return (nextItem == null && size != 0)
          || nextItem.prev != null;
    }

    /**
            @return 
     */
    @Override
    public int nextIndex() {
      return index;
    }

    /**
           @return
     */
    @Override
    public int previousIndex() {
      return index - 1;
    }

    /**
        @return 
        @throws NoSuchElementException
     */
    @Override
    public E previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      if (nextItem == null) { 
        nextItem = tail;
      }
      else {
        nextItem = nextItem.prev;
      }
      lastItemReturned = nextItem;
      index--;
      return lastItemReturned.data;
    }

    /** 
        @param obj
     */
    @Override
    public void add(E obj) {
      if (head == null) { 
        head = new Node <  > (obj);
        tail = head;
      }
      else if (nextItem == head) { 
        Node < E > newNode = new Node <  > (obj);
        newNode.next = nextItem; 
       
        nextItem.prev = newNode; 
        head = newNode; 
      }
      else if (nextItem == null) {
        Node < E > newNode = new Node <  > (obj);
        tail.next = newNode; 
        newNode.prev = tail; 
        tail = newNode; 
      }
      else { 
        Node < E > newNode = new Node <  > (obj);
        newNode.prev = nextItem.prev; 
        nextItem.prev.next = newNode; 
        newNode.next = nextItem; 
        nextItem.prev = newNode; 
      }
      size++;
      index++;
      lastItemReturned = null;
    }

    /** 
     *  @throws IllegalStateException 
     */
    @Override
    public void remove() {
      if (lastItemReturned == null) {
        throw new IllegalStateException();
      }

      if (lastItemReturned.next != null) {
        lastItemReturned.next.prev = lastItemReturned.prev;
      }
      else { 
        tail = lastItemReturned.prev;
        if (tail != null) {
          tail.next = null;
        }
        else { 
          head = null;
        }
      }
      if (lastItemReturned.prev != null) {
        lastItemReturned.prev.next = lastItemReturned.next;
      }
      else { // Item is the head
        head = lastItemReturned.next;
        if (head != null) {
          head.prev = null;
        }
        else {
          tail = null;
        }
      }
      lastItemReturned = null;
      size--;
      index--;
    }

    /** 
     *  @param item 
     *  @throws IllegalStateException
     *  
     */
    @Override
    public void set(E item) {
      if (lastItemReturned == null) {
        throw new IllegalStateException();
      }
      lastItemReturned.data = item;
    }
  }
    
}
