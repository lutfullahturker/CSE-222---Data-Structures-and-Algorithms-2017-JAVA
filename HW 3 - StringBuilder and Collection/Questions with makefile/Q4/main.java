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
        
        SingleLinkedList<Integer> test =  new SingleLinkedList<>();
        for (int i = 0;i < 100;++i) 
            test.add(i);
        System.out.println("100 integers added to the list  ==>>  " + test.toString());
        for (int i = 0; i < 50; i++) {
            test.remove(0);
        }
        System.out.println("50 integers removed from the list  ==>>  " + test.deletedToString());
        for (int i = -100,j=0; j < 100;--i,++j ) {
            test.add(i);
        }
        System.out.println("100 integers Re-added to the list  ==>>  " + test.toString());
    }
        

    }
