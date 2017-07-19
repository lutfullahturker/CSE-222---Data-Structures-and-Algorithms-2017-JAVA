
import java.io.File;
import java.io.FileWriter;
import static java.lang.Math.pow;

/**
 *
 * @author osboxes
 */
public class Q1main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StackA<Integer> A=new StackA<>();
        StackB<Integer> B=new StackB<>();
        StackC<Integer> C=new StackC<>();
        StackD<Integer> D=new StackD<>();
        StackA<Double> Ad=new StackA<>();
        StackB<Double> Bd=new StackB<>();
        StackC<Double> Cd=new StackC<>();
        StackD<Double> Dd=new StackD<>();
        StackA<Character> Ac=new StackA<>();
        StackB<Character> Bc=new StackB<>();
        StackC<Character> Cc=new StackC<>();
        StackD<Character> Dc=new StackD<>();
        StackA<String> As=new StackA<>();
        StackB<String> Bs=new StackB<>();
        StackC<String> Cs=new StackC<>();
        StackD<String> Ds=new StackD<>();
        try {
            ReadCsv fill=new ReadCsv();
            long start,end;
            start = System.nanoTime();
            fill.readAndFill(A,1);
            writeResultCSV(A);
            fill.readAndFill(Ad,2);
            writeResultCSV(Ad);
            fill.readAndFill(Ac,3);
            writeResultCSV(Ac);
            fill.readAndFill(As,4);
            writeResultCSV(As);
            end = System.nanoTime() - start;
            System.out.println("StackA running time is  ==> "+end/pow(10,9)+" seconds.\n");
            
            start = System.nanoTime();
            fill.readAndFill(B,1);
            writeResultCSV(B);
            fill.readAndFill(Bd,2);
            writeResultCSV(Bd);
            fill.readAndFill(Bc,3);
            writeResultCSV(Bc);
            fill.readAndFill(Bs,4);
            writeResultCSV(Bs);
            end = System.nanoTime() - start;
            System.out.println("StackB running time is  ==> "+end/pow(10,9)+" seconds.\n");
            
            start = System.nanoTime();
            fill.readAndFill(C,1);
            writeResultCSV(C);
            fill.readAndFill(Cd,2);
            writeResultCSV(Cd);
            fill.readAndFill(Cc,3);
            writeResultCSV(Cc);
            fill.readAndFill(Cs,4);
            writeResultCSV(Cs);
            
            end = System.nanoTime() - start;
            System.out.println("StackC running time is  ==> "+end/pow(10,9)+" seconds.\n");
            
            start = System.nanoTime();
            fill.readAndFill(D,1);
            writeResultCSV(D);
            fill.readAndFill(Dd,2);
            writeResultCSV(Dd);
            fill.readAndFill(Dc,3);
            writeResultCSV(Dc);
            fill.readAndFill(Ds,4);
            writeResultCSV(Ds);
            end = System.nanoTime() - start;
            System.out.println("StackD running time is  ==> "+end/pow(10,9)+" seconds.\n");
        } catch (Exception ex) {
            System.err.println("Exception caught ==>>  " + ex.toString());
        }

    }

    public static void writeResultCSV(StackInterface stack) throws Exception
    {
        File testResult = new File("testResult_1.csv");
        if (!testResult.exists()) 
                testResult.createNewFile();
        try (FileWriter fWriter = new FileWriter(testResult, true)) {
            fWriter.write(stack.toString());
            fWriter.close();
        }
        catch (Exception e){
            throw e;
        }
    }
    
}