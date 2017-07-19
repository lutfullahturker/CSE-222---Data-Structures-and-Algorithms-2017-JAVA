/**
 *
 * @author Lütfullah TÜRKER
 */
public class FamilyTree extends BinaryTree<String>{

    private int count = 0;
    private Node<String> foundPerson = null;
    
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        super.preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
}
