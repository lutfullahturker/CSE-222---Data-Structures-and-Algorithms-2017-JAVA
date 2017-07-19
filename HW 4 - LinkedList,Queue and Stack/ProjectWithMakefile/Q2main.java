
import java.io.File;
import java.io.FileWriter;
import static java.lang.Math.pow;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author osboxes
 */
public class Q2main {
    
    public static void main(String[] args) {
        try {
            ReadCsv fill=new ReadCsv();
            
            myQueue<Integer> queue = new myQueue();
            myQueue<Float> queue1 = new myQueue();
            myQueue<Character> queue2 = new myQueue();
            myQueue<String> queue3 = new myQueue();
            Queue<Integer> queue4 = new LinkedList();
            Queue<Float> queue5 = new LinkedList();
            Queue<Character> queue6 = new LinkedList();
            Queue<String> queue7 = new LinkedList();
            
            long start,end;
            start = System.nanoTime();
            fill.readAndFill(queue3,4);
            writeResultCSV(queue3);
            fill.readAndFill(queue2,3);
            writeResultCSV(queue2);
            fill.readAndFill(queue1,2);
            writeResultCSV(queue1);
            fill.readAndFill(queue,1);
            writeResultCSV(queue);
            end = System.nanoTime() - start;
            System.out.println("myQueue running time is  ==> "+end/pow(10,9)+" seconds.\n");
            
            start = System.nanoTime();
            fill.readAndFill(queue7,4);
            writeResultCSV(queue7);
            fill.readAndFill(queue6,3);
            writeResultCSV(queue6);
            fill.readAndFill(queue5,2);
            writeResultCSV(queue5);
            fill.readAndFill(queue4,1);
            writeResultCSV(queue4);            
            end = System.nanoTime() - start;
            System.out.println("Queue running time is  ==> "+end/pow(10,9)+" seconds.\n");
            
        } catch (Exception ex) {
            System.err.println("Exception caught ==>>  " + ex.toString());
        }

    }
    
    
    public static void writeResultCSV(Queue queue) throws Exception
    {
        File testResult = new File("testResult_2.csv");
        if (!testResult.exists()) 
                testResult.createNewFile();
        try (FileWriter fWriter = new FileWriter(testResult, true)) {
            myQueue tmp = new myQueue();
            tmp.reverseQueue(queue);
            fWriter.write(queue.toString()+"\n");
            fWriter.close();
        }
        catch (Exception e){
            throw e;
        }
    }
    public static void writeResultCSV(myQueue queue) throws Exception
    {
        File testResult = new File("testResult_2.csv");
        if (!testResult.exists()) 
                testResult.createNewFile();
        try (FileWriter fWriter = new FileWriter(testResult, true)) {
            queue.reverse();
            fWriter.write(queue.toString()+"\n");
            fWriter.close();
        }
        catch (Exception e){
            throw e;
        }
    }
    
}
