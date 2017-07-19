
import java.io.File;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author osboxes
 * @param <T>
 */
public class ReadCsv<T> {

    
    
    public ReadCsv(){       
        
    }
    
    /**
     * Stack ve satır numarası alır. satır numarasına göre o satırı test.csv den okur ve
     * her elemanı stack e ekler.
     * @param stack Doldurulacak stack
     * @param whichLine Okunacak satırın numarası
     * @throws Exception Hata durumlarında fırlatılıyor.
     */
    public void readAndFill(StackInterface<T> stack,int whichLine) throws Exception
    {
        if (whichLine <= 0)
            throw new Exception ("Line number cannot less or equal than 0.");
        File file = new File("test.csv");
        if (!file.canRead() || !file.exists())
            throw new Exception("File is not readable or exist !");
        Scanner read = new Scanner (file);
        String line =null;
        for (int i = 0 ;i<whichLine;++i)
            if (read.hasNextLine())
                line = read.nextLine();
        String[] splitted = null;
        if (line != null)
            splitted = line.split(",");
        else
            throw new Exception ("String line: null");
        Scanner str = new Scanner(splitted[0]);
        for (int i=0;i<splitted.length;++i)
        {
            if (str.hasNextInt())
                stack.push((T)(Integer)Integer.parseInt(splitted[i]));
            else if (str.hasNextFloat())
                stack.push((T)(Float)Float.parseFloat(splitted[i]));
            else if (splitted[0].length() == 1)
                stack.push((T)(Character) splitted[i].charAt(0));
            else
                stack.push((T)(splitted[i]));
        }
            
    }
    /**
     * Stack ve satır numarası alır. satır numarasına göre o satırı test.csv den okur ve
     * her elemanı queue e ekler.
     * @param queue Doldurulacak queue
     * @param whichLine Okunacak satırın numarası
     * @throws Exception Hata durumlarında fırlatılıyor.
     */
    public void readAndFill(myQueue<T> queue,int whichLine) throws Exception
    {
        if (whichLine <= 0)
            throw new Exception ("Line number cannot less or equal than 0.");
        File file = new File("test.csv");
        if (!file.canRead() || !file.exists())
            throw new Exception("File is not readable or exist !");
        Scanner read = new Scanner (file);
        String line =null;
        for (int i = 0 ;i<whichLine;++i)
            if (read.hasNextLine())
                line = read.nextLine();
        String[] splitted = null;
        if (line != null)
            splitted = line.split(",");
        else
            throw new Exception ("String line: null");
        Scanner str = new Scanner(splitted[0]);
        for (int i=0;i<splitted.length;++i)
        {
            if (str.hasNextInt())
                queue.add((T)(Integer)Integer.parseInt(splitted[i]));
            else if (str.hasNextFloat())
                queue.add((T)(Float)Float.parseFloat(splitted[i]));
            else if (splitted[0].length() == 1)
                queue.add((T)(Character) splitted[i].charAt(0));
            else
                queue.add((T)(splitted[i]));
        }
            
    }
    /**
     * Stack ve satır numarası alır. satır numarasına göre o satırı test.csv den okur ve
     * her elemanı queue e ekler.
     * @param queue Doldurulacak queue
     * @param whichLine Okunacak satırın numarası
     * @throws Exception Hata durumlarında fırlatılıyor.
     */
    public void readAndFill(Queue<T> queue,int whichLine) throws Exception
    {
        if (whichLine <= 0)
            throw new Exception ("Line number cannot less or equal than 0.");
        File file = new File("test.csv");
        if (!file.canRead() || !file.exists())
            throw new Exception("File is not readable or exist !");
        Scanner read = new Scanner (file);
        String line =null;
        for (int i = 0 ;i<whichLine;++i)
            if (read.hasNextLine())
                line = read.nextLine();
        String[] splitted = null;
        if (line != null)
            splitted = line.split(",");
        else
            throw new Exception ("String line: null");
        Scanner str = new Scanner(splitted[0]);
        for (int i=0;i<splitted.length;++i)
        {
            if (str.hasNextInt())
                queue.add((T)(Integer)Integer.parseInt(splitted[i]));
            else if (str.hasNextFloat())
                queue.add((T)(Float)Float.parseFloat(splitted[i]));
            else if (splitted[0].length() == 1)
                queue.add((T)(Character) splitted[i].charAt(0));
            else
                queue.add((T)(splitted[i]));
        }
            
    }
    
    
    
}
