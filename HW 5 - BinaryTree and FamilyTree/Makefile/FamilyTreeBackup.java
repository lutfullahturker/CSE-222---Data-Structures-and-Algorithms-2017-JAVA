

/**
 *
 * @author Lütfullah TÜRKER
 */
public class FamilyTree extends BinaryTree<String>{

    public FamilyTree() {
        super();
    }

    public FamilyTree(String person) {
        super(new Node<String>(person));
    }
    public void add(String name,String parentName,String parentsNickname) throws Exception
    {
        Node<String> parent = findPerson(name,parentName, parentsNickname, root);
        if (parent == null)
            throw new Exception("Parent Not Found !");
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
    
    private Node<String> findPerson(String newChild,String name,String nickName, Node<String> Root) throws Exception
    {
        Node<String> tmp = Root;
        if(tmp.data.equals(name)){
            String[] splitted = nickName.split("-");
            if (splitted.length != 2)
                throw new Exception("Wrong Nickname is given ! ");
            switch (splitted[0]) {
                case "ibn":
                    Node<String>parent = findPerson(newChild,splitted[1],"ebu-"+name,root);
                    if (parent == null);
                    else{
                        if (parent.left != null && parent.left.data.equals(name))
                            return tmp;
                        else if(parent.left != null){
                            Node<String> Temp = parent.left;
                            while (Temp.right != null){
                                if (Temp.right.data.equals(name))
                                    return tmp;
                                Temp = Temp.right;
                            }
                        }
                    }
                    break;
                case "ebu":
                    if (tmp.left == null && splitted[1].equals(newChild))
                        return tmp;
                    if (tmp.left != null && tmp.left.data.equals(splitted[1]))
                        return tmp;
                    else if(tmp.left != null){
                        Node<String> temp = tmp.left;
                        while (temp.right != null){
                            if (temp.right.data.equals(splitted[1]))
                                return tmp;
                            temp = temp.right;
                        }
                    }
                    break;
                default:
                    throw new Exception("Wrong Nickname is given ! ");
            }
        }
        if(tmp.left != null){
            Node<String> isFound = findPerson(newChild,name,nickName,tmp.left);
            if ( isFound != null )
                return isFound;
        }
        if(tmp.right != null)
            return findPerson(newChild,name,nickName, tmp.right);
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        super.preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
}
