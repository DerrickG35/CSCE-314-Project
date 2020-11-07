
public class MerkleTree {
	
	
    private InnerNode root;
    private String paths[];
    private int depth;
    private int cFileIndex;
    
    
    
    public MerkleTree(String[] paths) {
        this.root = new InnerNode();
        this.paths = paths;
        this.depth = log2(paths.length);
        this.cFileIndex = 0;
        
        buildTree(0,root);
    }
    
     
    private void buildTree(int depth, InnerNode current) {
    	
    	
    	if( this.depth == depth) {
    		
    		if (cFileIndex >= paths.length) {
    			current.setFile(new TreeFile(paths[paths.length-1]));
    		}
    		else {
	    		current.setFile(new TreeFile(paths[cFileIndex]));
	    		cFileIndex += 1;
    		}
    		
    	}
    	else {
    		
    		current.setLeft(new InnerNode());
    		current.setRight(new InnerNode());
    		
    		buildTree(depth+1, current.getLeft());
        	buildTree(depth+1, current.getRight());
        	
        	// Can add hashes here
        	
    	}
    	
    }
    
    
    private int log2(int N) {

    	int result = (int) Math.ceil((Math.log(N) / Math.log(2)));
    	return result;
    	
    }
    
    public InnerNode root() {
    	return root;
    }
    
    
    public int getDepth() { return depth; } ;
    
    
    // count how many nodes in the tree
    public int treeSize(InnerNode node) {
    	
    	if (node.getFile() != null) {
    		System.out.println(node.getFile());
    		return 1;
    	}
    	else {
    		return 1 + treeSize(node.getLeft()) + treeSize(node.getRight());
    	}
    	
    }
    
}
