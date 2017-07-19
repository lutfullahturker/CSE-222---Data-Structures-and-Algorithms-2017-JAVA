
import java.util.ArrayList;

/**
 *
 * @author osboxes
 * @param <T>
 */
public class StackA<T> extends ArrayList<T> implements StackInterface<T> {

    //private int size;

    
    
    public StackA() {
        super();
    }
    
    /**
     * Gelen elemanı stack e ekler.
     * @param item Eklenecek eleman
     * @return
     */
    @Override
    public T push(Object item) 
    {
        super.add((T)item);
       // ++size;
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
        temp = super.get(super.size()-1);
        super.remove(super.size()-1);
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
        return super.size() ;
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
            result.append(",").append(super.get(i));
        result.append("\n");
        return result.toString();
    }
    
}
