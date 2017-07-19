
import java.util.AbstractCollection;
import java.util.Iterator;

/**
 *
 * @author osboxes
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class myAbstractCollection<T> extends AbstractCollection<T>{
    public void appendAnything(myAbstractCollection<T> add)
    {
        this.addAll(add);
    } 
    public boolean addAll(myAbstractCollection<T> addCollection) 
    {
        boolean modified = false;
        Iterator<T> iter = addCollection.iterator();
        while (iter.hasNext()) {
            if (add(iter.next()))
                 modified = true;
        }
        return modified;
    }
}
