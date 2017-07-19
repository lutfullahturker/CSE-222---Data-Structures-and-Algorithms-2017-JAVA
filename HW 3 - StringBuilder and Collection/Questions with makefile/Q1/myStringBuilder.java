
import java.util.Iterator;

/**
 *
 * @author osboxes
 */
@SuppressWarnings("unchecked")
public class myStringBuilder {
    
    private SingleLinkedList<String> myList ;

    public myStringBuilder() {
        this.myList = new SingleLinkedList<>();
    }
    
    public myStringBuilder append (String str)
    {
        if (str == null) 
            str = "This is 'null' ! ";
        int len = str.length();
        if (len == 0) 
            return this ;
        myList.add(str);
        return this;
    }
    
    public myStringBuilder append (char str[])
    {
        int len = str.length;
        if (len == 0) 
            return this ;
        String forAdd = new String(str);
        myList.add(forAdd);
        return this;
    }
    
    public <E>myStringBuilder append (E num)
    {
        if (num == null)
            myList.add("null");
        else
            myList.add(num.toString());
        return this;
    }
    
    public myStringBuilder append (char str[],int start,int len)
    {
        if (len <= 0) 
            return this ;
        String forAdd = new String(str);
        myList.add(forAdd.substring(start,start+len));
        return this;
    }
    
    public myStringBuilder append (CharSequence str,int start,int end)
    {
        if (str == null)
            str = "This is null";
        if ((start < 0) || (end < 0) || (start > end) || (end > str.length()))
            throw new IndexOutOfBoundsException("Index Out Of Bounds !");
        String forAdd = (String) str;
        myList.add(forAdd.substring(start,end+1));
        return this;
    }
    
    public String toString2()
    {
        String print = new String();
        Iterator<String> iter = myList.iterator();
        while (iter.hasNext())
        {
            print += iter.next();
        }
        
        return print;
    }
    public String toString1()
    {
        String print = new String();
        for (int i = 0;i<myList.getSize();++i)
        {
            print += myList.get(i);
        }
        return print;
    }
    public String toString3()
    {
        return myList.toString();
    }
}
