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
        SingleLinkedList<String> a = new SingleLinkedList<>();
        a.add("bu ");
        a.add("bir ");
        a.add("Testtir");
        a.add("Ve ");
        a.add("ben ");
        a.add("bu ");
        a.add("testten");
        a.add("başarıyla ");
        a.add("geçtim");
        System.out.println(a.toString());
        System.out.println(a.reverseToString());
        System.out.println();
        System.out.println();
        int size = a.getSize();
        for (int i =0;i<size;++i)
            a.remove(0);
        a.add("Ey ");
        a.add("Edip ");
        a.add("Pide ");
        a.add("Ye ");
        System.out.println(a.toString());
        System.out.println(a.reverseToString());
        
        
        
    }
    
}
