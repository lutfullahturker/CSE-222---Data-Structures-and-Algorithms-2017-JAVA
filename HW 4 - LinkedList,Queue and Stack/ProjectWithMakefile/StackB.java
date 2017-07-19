
import java.util.ArrayList;

/**
 *
 * @author osboxes
 * @param <T>
 */
public class StackB<T>  implements StackInterface<T> {
    
    private ArrayList<T> arr;
    
    
    public StackB() {
        arr = new ArrayList<>();
    }
    
    /**
     * Gelen elemanı stack e ekler.
     * @param item Eklenecek eleman
     * @return
     */
    @Override
    public T push(Object item) 
    {
        arr.add((T)item);
        return (T) item;
    }

    /**
     * Stack in son elemanını stackden çıkarır ve çıkardığı elemanı return eder.
     * @return
     */
    @Override
    public T pop() 
    {
        T temp ;
        temp = arr.get(arr.size()-1);
        arr.remove(arr.size()-1);
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
        return arr.size() ;
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
        for (int i =this.size()-1;i>=0;--i)
            result.append(",").append(arr.get(i));
        result.append("\n");
        return result.toString();
    }
}
