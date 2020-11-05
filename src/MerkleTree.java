
public class MerkleTree {
    Node root;

    public MerkleTree() {
        this.root = null;
    }
    public MerkleTree(String key) {
        this.root = new Node(key);
    }
}
