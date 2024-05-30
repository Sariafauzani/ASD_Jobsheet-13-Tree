import java.util.NoSuchElementException;

public class BinaryTree26 {
    Node26 root;

    public BinaryTree26(){
        root = null;
    }
    boolean isEmpty(){
        return root==null;
    }

    void add(int data){
        if (isEmpty()){ //tree is empty
            root = new Node26(data);
        } else {
            Node26 current = root;
            while(true){
                if (data < current.data){
                    if (current.left == null){
                        current.left = new Node26(data);
                        break;
                        //current = current.left;
                    } else {
                        current = current.left;
                        //current.left = new Node26(data);
                        //break;
                    } 
                } else if (data > current.data){
                    if (current.right == null){
                        current.right = new Node26(data);
                        break;
                        //current = current.right;
                    } else {
                        current = current.right;
                        //current.right = new Node26(data);
                        //break;
                    }
                } else { //data is already exist
                    break;
                }
            }
        }
    }

    void addRekursif(int data){
        root = addRekursif(root, data);
    }
    private Node26 addRekursif(Node26 current, int data){
        if (current == null){
            return new Node26(data);
        }
        if (data < current.data){
            current.left = addRekursif(current.left, data);
        } else if (data > current.data){
            current.right = addRekursif(current.right, data);
        }
        return current;
    }

    boolean find(int data){
        //boolean result = false;
        Node26 current = root;
        while(current != null){
            //if (current.data != data){
            if (data == current.data){
                return true;
                //result = true;
                //break;
            } else if (data < current.data){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    int findMin(){
        if (root == null){
            throw new NoSuchElementException("Tree is empty");
        }
        return findMin(root).data;
    }
    private Node26 findMin(Node26 node){
        return node.left == null ? node : findMin(node.left);
    }
    int findMax(){
        if (root == null){
            throw new NoSuchElementException("Tree is empty");
        }
        return findMax(root).data;
    }
    private Node26 findMax(Node26 node){
        return node.right == null ? node : findMax(node.right); 
    }

    void printLeafNode(){
        printLeafNode(root);
    }
    private void printLeafNode(Node26 node){
        if (node != null){
            if (node.left == null && node.right == null){
                System.out.print(" "+ node.data);
            }
            printLeafNode(node.left);
            printLeafNode(node.right);
        }
    }

    int countLeafNode(){
        return countLeafNode(root);
    }
    private int countLeafNode(Node26 node){
        if (node == null){
            return 0;
        }
        if (node.left == null && node.right == null){
            return 1;
        } else {
            return countLeafNode(node.left) + countLeafNode(node.right);
        }
    }

    void traversePreOrder(Node26 node){
        if (node != null){
            System.out.print(" "+ node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    void traversePostOrder(Node26 node){
        if (node != null){
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" "+ node.data);
        }
    }
    void traverseInOrder(Node26 node){
        if (node != null){
            traverseInOrder(node.left);
            System.out.print(" "+node.data);
            traverseInOrder(node.right);
        }
    }

    Node26 getSuccessor(Node26 del){
        Node26 successor = del.right;
        Node26 successorParent = del;
        while(successor.left != null){
            successorParent = successor;
            successor = successor.left;
        }
        if (successor != del.right){
            successorParent.left = successor.right;
            successor.right = del.right;
        }
        return successor;
    }

    void delete(int data){
         if (isEmpty()){
            System.out.println("Tree is empty!");
            return;
         }
         //find node (current) that will be deleted
         Node26 parent = null;
         //Node26 parent = root;
         Node26 current = root;
         boolean isLeftChild = false;
         while(current != null && current.data != data){
            parent = current;
            if (data < current.data){
                current = current.left;
                isLeftChild = true;
                //break;
            /* } else if (data < current.data){
                parent = current;
                parent = current.left;
                isLeftChild = true;/* */
            /* } else if (data > current.data){
                parent = current;
                current = current.right;
                isLeftChild = false;/* */
            } else {
                current = current.right;
                isLeftChild = false;
            }
         }
         //deletion
         if (current == null){
            System.out.println("Couldn't find data!");
            return;
         }
            //if there is no child, simply delete it
            if (current.left == null && current.right == null){
                if (current == root){
                    root = null;
                } else {
                    if (isLeftChild){
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
            } else if (current.left == null){ //if there is 1 child (right)
                if (current == root){
                    root = current.right;
                } else {
                    if (isLeftChild){
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                }
             } else if (current.right == null){ //if there is 1 child (left)
                    if (current == root){
                        root = current.left;
                    } else {
                        if (isLeftChild){
                            parent.left = current.left;
                        } else {
                            parent.right = current.left;
                        }
                    }
                } else { //if there is 2 childs
                    Node26 successor = getSuccessor(current);
                    if (current == root){
                        root = successor;
                    } else if (isLeftChild){
                        parent.left = successor;
                    } else {
                        parent.right = successor;
                    }
                    successor.left = current.left;
                }
        }
}