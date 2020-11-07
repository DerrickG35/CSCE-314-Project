import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

/*****************************************
** File:    MerkleTree.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**  Description...
*
***********************************************/

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
    
     
    // buildTree
    // given the root, will create all the branches need for the files
    private void buildTree(int depth, InnerNode current) {
    	
    	if( this.depth == depth) {
    		
    		if (cFileIndex >= paths.length) {
    			current.setFile(new TreeFile(paths[paths.length-1]));
    		}
    		else {
	    		current.setFile(new TreeFile(paths[cFileIndex]));
	    		cFileIndex += 1;
    		}
    		
    		current.setKey(current.getFile().getKey());
    		
    	}
    	else {
    		
    		
    		current.setLeft(new InnerNode());
    		current.setRight(new InnerNode());
    		
    		buildTree(depth+1, current.getLeft());
        	buildTree(depth+1, current.getRight());
        	
        	current.setKey(current.getLeft().getKey() + current.getRight().getKey());
        	
        	
    	}
    	
    }
    
    
    // log base 2 operator
    private int log2(int N) {

    	int result = (int) Math.ceil((Math.log(N) / Math.log(2)));
    	return result;
    	
    }
    
    
    // return the root of the Merkle Tree
    public InnerNode root() {
    	return root;
    }
    
    
    // returns the depth of the MerkleTree
    public int getDepth() { return depth; } ;
    
    
    // count how many InnerNodes in the tree
    public int treeSize(InnerNode node) {
    	System.out.println(node.getKey());
    	if (node.getFile() != null) {
    		return 1;
    	}
    	else {
    		return 1 + treeSize(node.getLeft()) + treeSize(node.getRight());
    	}
    	
    }
    
}
