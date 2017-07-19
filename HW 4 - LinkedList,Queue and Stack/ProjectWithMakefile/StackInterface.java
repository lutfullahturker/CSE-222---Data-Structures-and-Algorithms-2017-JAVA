/**
 *
 * @author osboxes
 * @param <T>
 */
public interface StackInterface<T> {
    public T push(T item);
    public T pop();
    public boolean isEmpty();
    public int size();
}
