public class Node {
	
    String key;
    Node left;
    Node right;
    TreeFile file;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    public Node(TreeFile file) {
        this.key = null;
        this.left = null;
        this.right = null;
    }


    public Node() {
        this.key = key;
        this.left = null;
        this.right = null;
    }
    
}