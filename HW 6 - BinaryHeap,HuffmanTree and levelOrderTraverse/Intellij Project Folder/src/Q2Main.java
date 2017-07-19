/**
 *
 * @author Lütfullah TÜRKER
 */
public class Q2Main {

    /**
     * Testleri mainde uyguluyoruz.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HuffmanTree test = new HuffmanTree();
            test.readFileAndBuildTree();
            System.out.println("Huffman Tree Frequency List  ==>");
            test.printCode(System.out);
            System.out.println("\nDecode Tests   ==>");
            System.out.println("00110001000001100110010  ==>  " + test.decode("00110001000001100110010"));
            System.out.println("01000010001000100010011001001010  ==>  " + test.decode("01000010001000100010011001001010"));
            System.out.println("01000010110000001011011  ==>  " + test.decode("01000010110000001011011"));
            System.out.println("\nEncode Tests   ==>");
            System.out.println("TESTI   ==>  " + test.encode("TESTI"));
            System.out.println("GECTIM   ==>  " + test.encode("GECTIM"));
            System.out.println("GALIBA   ==>  " + test.encode("GALIBA"));
        }
        catch (Exception e){
            System.err.println("Exception Caught ! ==> "+e.toString());
        }
        
        
        
        
    }
    
}
