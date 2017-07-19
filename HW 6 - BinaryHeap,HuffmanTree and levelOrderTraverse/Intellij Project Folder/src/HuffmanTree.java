/**
 *
 * @author osboxes
 */

import java.io.*;
import java.util.*;



public class HuffmanTree implements Serializable {

    // Nested Classes
    /** A datum in the Huffman tree. */
    public static class HuffData implements Serializable {
        // Data Fields

        /** The weight or probability assigned to this HuffData. */
        private double weight;
        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        public Character getSymbol() {return symbol;}
    }
    // Data Fields
    /** A reference to the completed Huffman tree. */
    protected BinaryTree<HuffData> huffTree;
    private String encodeBin ;
    
    /** A Comparator for Huffman trees; nested class. */
    private static class CompareHuffmanTrees
            implements Comparator<BinaryTree<HuffData>> {

        /**
         * Compare two objects.
         * @param treeLeft The left-hand object
         * @param treeRight The right-hand object
         * @return -1 if left less than right,
         * 0 if left equals right,
         * and +1 if left greater than right
         */
        @Override
        public int compare(BinaryTree<HuffData> treeLeft,
                BinaryTree<HuffData> treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /**
     * freq.txt dosyasindan frequency bilgilerini alip huffData arrayine yaziyor.
     * Ve yazdiktan sonra bu arrayi buildTree fonksiyonuna gonderip HuffmanTree yi olusturuyor.
     * @throws Exception 
     */
    
    public void readFileAndBuildTree() throws Exception{
        HuffData[] data = new HuffData[20];
        HuffData[] temp;
        int size = 20,used = 0;
        double freqNum;
        char letter;
        
        File freq = new File("freq.txt");
        if (!freq.canRead() || !freq.exists() || !freq.isFile())
            throw new IOException("File Doesn't exist or cannot be readable !");
        try (Scanner read = new Scanner(freq)) {
            while(read.hasNext()){
                letter = read.next().charAt(0);
                if (read.hasNextInt())
                    freqNum = read.nextDouble();
                else
                    throw new Exception ("freq.txt file is irregular ! Please make the file like this :\nA 201\nB 176\nC 34\n");
                data[used++] = new HuffData(freqNum, letter);
                if (used >= size){
                    size *= 2;
                    temp = new HuffData[size];
                    for(int i =0;i<used;++i)
                        temp[i] = data[i];
                    data = temp;
                }
            }
            read.close();
            temp = new HuffData[used];
            for(int i =0;i<used;++i)
                temp[i] = data[i];
            data = temp;
            buildTree(data);
        }
    }

    /**
     * Builds the Huffman tree using the given alphabet and weights.
     * @post  huffTree contains a reference to the Huffman tree.
     * @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue<BinaryTree<HuffData>> theQueue =
                new PriorityQueue<>(symbols.length,
                new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> aBinaryTree =
                    new BinaryTree<>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree<HuffData> newTree =
                    new BinaryTree<>(sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }
    /*</listing>*/

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     * @param code The code up to this node
     * @param tree The current node in the tree
     */
    private void printCode(PrintStream out, String code,
            BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != null) {
            if (theData.symbol.equals(' ')) {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * Outputs the resulting code.
     * @param out A PrintStream to write the output to
     */
    public void printCode(PrintStream out) {
        printCode(out, "", huffTree);
    }

    /*<listing chapter="6" number="11">*/
    /**
     * Method to decode a message that is input as a string of
     * digit characters '0' and '1'.
     * @param codedMessage The input message as a String of
     *        zeros and ones.
     * @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }
/**
 * decode işleminin tam tersini yapan encode fonksiyonu.
 * @param message
 * @return 
 */
    
    public String encode(String message) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        String a;
        for (int i = 0; i < message.length(); i++) {
            if ((a = find(huffTree,message.charAt(i),"")) != null){
                if (encodeBin != null)
                    result.append(encodeBin);
                else
                    result.append(" ");
            }
            encodeBin = null;
        }
        return result.toString();
    }
    
    /**
     * PrintCode fonksiyonundaki recursive den yararlanilarak yaptigim verilen harfi
     * tree de bulup encodeBin stringine, bulurken izlediği yolu binary sayilarla atayan fonksiyon.
     * @param tree
     * @param c
     * @param code
     * @return 
     */
    
    private String find(BinaryTree<HuffData> tree ,char c,String code){
        HuffData theData = tree.getData();

        if (theData.symbol != null) {
            if(theData.symbol.equals(c)){
                encodeBin = code;
                return code;
            }
        } else {
            find(tree.getLeftSubtree(),c,code + "0");
            find(tree.getRightSubtree(),c,code + "1");
        }
        return code;
    }
}
