/**
 *
 * @author Lütfullah TÜRKER
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Q3Main {
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            readFile();   
        }
        catch (IOException e){
            System.err.println("Exception Caught : " + e.toString());
        } 
        catch (Exception ex) {
            System.err.println("Exception Caught : " + ex.toString());
        }
        
    }
    
    /**
     * family.txt isimli txt dosyasından aile bilgilerini tree şeklinde çeker ve treeyi doldurur.
     * Doldurduktan sonra toString metodunu çağırarak tree yi levelOrder şeklinde ekrana basar.
     * @throws IOException Okumada herhangi bir sorun olması durumunda Exception fırlatılıyor
     * @throws Exception hata kontrol
     */
    public static void readFile() throws IOException, Exception{
        File test = new File("family.txt");
            if (!test.exists() || !test.canRead())
                throw new IOException ("File Not Found or Is not Readable! ");
        try  {
            Scanner read = new Scanner(test);
            if (!read.hasNextLine())
                throw new IOException("File is empty !");
            FamilyTree familyTree = new FamilyTree(read.nextLine().trim());
            while (read.hasNextLine()){
                String temp = read.nextLine();
                String[] splitted = temp.split(",");
                if (splitted.length != 3)
                    throw new IOException ("File's each line (except first line) must have 3 words !");
                familyTree.add(splitted[0].trim(),splitted[1].trim(),splitted[2].trim());
            }
            read.close();
            System.out.println(familyTree.toString());
        }
        catch (IOException e){
            throw new IOException("family.txt File Read Error ! ");
        }
        
        
    }
}
