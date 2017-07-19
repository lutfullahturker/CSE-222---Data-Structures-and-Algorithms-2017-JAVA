
import java.util.ArrayList;

/**
 *
 * @author Lütfullah TÜRKER
 */
public class FamilyTree extends BinaryTree<String>{

    private int count = 0;
    private Node<String> foundPerson = null;
    private ArrayList<Node<String>> elements = new ArrayList<>();
    
    public FamilyTree() {
        super();
    }

    /**
     *
     * @param person ilk eleman Root
     */
    public FamilyTree(String person) {
        super(new Node<String>(person));
    }

    /**
     *
     * @param name eklenecek isim
     * @param parentName parent ismi
     * @param parentsNickname parent nickname i
     * @throws Exception hata kontrol
     */
    public void add(String name,String parentName,String parentsNickname) throws Exception
    {
        Node<String> parent = findPerson(name,parentName, parentsNickname, root);
        foundPerson = null;
        if (parent == null || count != 1)
            throw new Exception("Parent Not Found or More than 1 Found!");
        count = 0;
        Node<String> parChild = parent.left;
        if (parent.left == null)
            parent.left = new Node<>(name);
        else
        {
            while (parChild.right != null)
                parChild = parChild.right;
            parChild.right = new Node<>(name);
        }
    }
    
    /**
     * 
     * @param newChild
     * @param name
     * @param nickName
     * @param Root
     * @return
     * @throws Exception 
     */
    private Node<String> findPerson(String newChild,String name,String nickName, Node<String> Root) throws Exception
    {
        Node<String> tmp = Root;
        if(tmp.data.equals(name)){
            String[] splitted = nickName.split("-");
            if (splitted.length != 2)
                throw new Exception("Wrong Nickname is given ! ");
            switch (splitted[0]) {
                case "ibn":
                    int tempCount = count;
                    Node<String> tempNode = foundPerson;
                    Node<String>parent = findPerson(newChild,splitted[1],"ebu-"+name,root);
                    count = tempCount;
                    foundPerson = tempNode;
                    if (parent == null);
                    else{
                        if (parent.left != null && parent.left.data.equals(name)){
                            foundPerson = tmp;
                            count ++;
                        }
                        else if(parent.left != null){
                            Node<String> Temp = parent.left;
                            while (Temp.right != null){
                                if (Temp.right.data.equals(name)){
                                    foundPerson=tmp;
                                    count++;
                                }
                                Temp = Temp.right;
                            }
                        }
                    }
                    break;
                case "ebu":
                    if (tmp.left == null && splitted[1].equals(newChild)){
                        foundPerson=tmp;
                        count++;
                    }
                    if (tmp.left != null && tmp.left.data.equals(splitted[1])){
                        foundPerson=tmp;
                        count++;
                    }
                    else if(tmp.left != null){
                        Node<String> temp = tmp.left;
                        while (temp.right != null){
                            if (temp.right.data.equals(splitted[1])){
                                foundPerson=tmp;
                                count++;
                            }
                            temp = temp.right;
                        }
                    }
                    break;
                default:
                    throw new Exception("Wrong Nickname is given ! ");
            }
        }
        if(tmp.left != null){
            findPerson(newChild,name,nickName,tmp.left);
        }
        if(tmp.right != null)
            findPerson(newChild,name,nickName, tmp.right);
        return foundPerson;
    }
    
    /**
     * Recursive sekilde LevelOrderTraverse yapan fonksiyon.ArrayListe Level order sekilde yazar.
     */
    private void levelOrderTraverse() 
    {
        int i = 0;
        if (root != null)
            elements.add(root);
        else
            return ;
        Node <String> tempNode = root;
        while (tempNode != null)
        {
            if (i < elements.size())
                tempNode = elements.get(i++);
            else
                tempNode = null ;
            if (tempNode != null && tempNode.left != null) {
                elements.add(tempNode.left);
            }
            if (tempNode != null && tempNode.right != null) {
                elements.add(tempNode.right);
            }
        }
    }
    
    /**
     * Level order traverse yaparak ekrana sirayla aile bireylerini level order sekilde yazar.
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        levelOrderTraverse();
        for (int i =0;i< elements.size();++i)
            sb.append("\t").append(elements.get(i).data);
		elements = new ArrayList<>(); 
        return sb.toString();
    }
    
}