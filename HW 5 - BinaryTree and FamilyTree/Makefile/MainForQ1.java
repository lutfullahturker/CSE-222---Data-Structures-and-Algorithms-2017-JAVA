
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Lütfullah TÜRKER
 */
public class MainForQ1 {

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
        
    }
    
    /**
     *
     * @throws IOException Okumada herhangi bir sorun olması durumunda Exception fırlatılıyor
     */
    public static void readFile() throws IOException{
        File test = new File("test.txt");
            if (!test.exists() || !test.canRead())
                throw new IOException ("File Not Found or Is not Readable! ");
        try  {
            Scanner read = new Scanner(test);
            PreOrderBinTree<Integer> binaryTree = new PreOrderBinTree<>();
            BinarySearchTree<Integer> binSearchTree = new BinarySearchTree<>();
            while (read.hasNextInt()){
                int temp = read.nextInt();
                binaryTree.add(temp);
                binSearchTree.add(temp);
            }
            read.close();
            Iterator iterBinTree = binaryTree.iterator();
            Iterator iterBinSearchTree = binSearchTree.iterator();
            System.out.println("Pre Order Binary Tree Traverse is ==>> ");
            while (iterBinTree.hasNext())
                System.out.println(iterBinTree.next().toString());
            System.out.println("\nLevel Order Binary Search Tree Traverse is ==>> ");
            while (iterBinSearchTree.hasNext())
                System.out.println(iterBinSearchTree.next().toString());
        }
        catch (IOException e){
            throw new IOException("test.txt File Read Error ! ");
        }
        
        
    }
    
}
