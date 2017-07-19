
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.Scanner;



/**
 *
 * @author osboxes
 */
@SuppressWarnings("unchecked")
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try 
        {
            myStringBuilder test = new myStringBuilder();
            File numbers = new File("numbers.txt");
            Scanner file;
            /**
            * dosya açılamadıysa,yoksa,okunabilir değilse gibi durumlar için
            * exception sınıfımızı fırlatıyoruz (içinde bir string ile birlikte).
            */
            if (!numbers.canRead() && !numbers.isFile() && !numbers.exists())
                throw new Exception("File Open Failed !!!") ;
            file = new Scanner(new FileReader(numbers));
            while(file.hasNextInt()){
                test.append(file.nextInt());
            }
            file.close();
            long startTime,endTime;
            startTime = System.nanoTime();
            writeResult(test.toString1(), 1);
            endTime = System.nanoTime()-startTime;
            System.out.println("toString1 's running time (Uses indexes and get method) is ==>  "+endTime/pow(10,9)+ "  seconds.");
            startTime = System.nanoTime();
            writeResult(test.toString2(), 2);
            endTime = System.nanoTime()-startTime;
            System.out.println("toString2 's running time (Uses iterator) is ==>  "+endTime/pow(10,9) + "  seconds.");
            startTime = System.nanoTime();
            writeResult(test.toString3(), 3);
            endTime = System.nanoTime()-startTime;
            System.out.println("toString3 's running time (Uses toString method of the SingleLinkedList) is ==>  "+endTime/pow(10,9)+ "  seconds.");
            
        }
        catch (FileNotFoundException ex) 
        {
                System.err.println(ex.toString());
        }
        catch (Exception ex) {
            System.err.println("An error has occured !");
            System.err.println(ex.toString());
        }
        
        
        
    }
    
    public static void writeResult(String toStr,int which) throws IOException
    {
        File result;
        switch (which) {
            case 1:
                result = new File("result1.txt");
                break;
            case 2:
                result = new File("result2.txt");
                break;
            case 3:
                result = new File("result3.txt");
                break;
            default:
                System.err.println("An Error Occured !");
                return;
        }
        if (!result.exists())
            result.createNewFile();
        FileWriter file = new FileWriter(result);
        file.write(toStr);
        file.close();        
    }
    
}
