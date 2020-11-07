
public class MerkleTree {
	
	
    public Node root;
    private int depth;
    private String paths[];
    private int cFileIndex;
    
    
    
    public MerkleTree(String[] paths) {
        this.root = new Node();
        this.paths = paths;
        this.depth = log2(paths.length);
        this.cFileIndex = 0;
        
        buildTree(0,root);
    }
    
     
    private void buildTree(int depth, Node current) {
    	
    	
    	if( this.depth == depth) {
    		
    		if (cFileIndex >= paths.length) {
    			current.file = new TreeFile(paths[paths.length-1]);
    		}
    		else {
	    		current.file = new TreeFile(paths[cFileIndex]);
	    		cFileIndex += 1;
    		}
    		
    	}
    	else {
    		
    		current.left = new Node();
    		current.right = new Node();
    		buildTree(depth+1, current.left);
        	buildTree(depth+1, current.right);
    	}
    	
    }
    
    public int getDepth() { return depth; } ;
    
    public int log2(int N) {

    	int result = (int) Math.ceil((Math.log(N) / Math.log(2)));
    	return result;
    	
    }
    
    // count how many nodes in the tree
    public int treeSize(Node node) {
    	
    	if (node.file != null) {
    		System.out.println(node.file);
    		return 1;
    	}
    	else {
    		return 1 + treeSize(node.left) + treeSize(node.right);
    	}
    	
    }
    
}
