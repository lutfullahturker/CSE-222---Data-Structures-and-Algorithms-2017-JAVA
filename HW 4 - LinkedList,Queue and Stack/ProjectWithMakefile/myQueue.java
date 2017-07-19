
import java.util.ListIterator;
import java.util.Queue;

/**
 *
 * @author osboxes
 * @param <T>
 */
public class myQueue<T> extends KWLinkedList<T>{
    
    /**
     * Tüm elemanlarının sırasını ters çevirir. örneğin : 1,2,3 ise  3,2,1 olur.
     */
    public void reverse()
    {
        T temp;
        int size = super.size();
        for (int i = 0,j=size-1 ; i<size/2 ;++i,--j)
        {
            temp = super.get(j);
            super.set(j,super.get(i));
            super.set(i, temp);
        }
    }
    
    /**
     * Gelen queue nin tüm elemanlarının sırasını ters çevirir. 
     * örneğin : 1,2,3 ise  3,2,1 olur.
     * @param queue Değiştirilecek queue
     */
    public void reverseQueue(Queue queue)
    {
        if (queue.isEmpty())
            return;
        Object temp = queue.poll();
        reverseQueue(queue);
        queue.add(temp);
    }
    
    /**
     * Queue ye eleman ekler.
     * @param add
     */
    public void add (T add)
    {
        super.addLast(add);
    }
    
    
    /**
     * Test edebilmek için queue nun tüm elemanlarını bir stringde aralarında boşluk bırakarak return eder.
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        ListIterator iter =  super.listIterator();
        result.append("[");
        while (iter.hasNext())
            result.append(iter.next()).append(" ");
        result.append("]");
        
        return result.toString();
    }
    
    
}
