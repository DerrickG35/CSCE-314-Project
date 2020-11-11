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
**  This file contains the MerkleTree data structure.
**	given the paths of all the files, it constructs
**	a perfect binary tree with TreeFile nodes connected
**	at the leaves of the Merkle Tree.
**
***********************************************/

public class MerkleTree {
	
	
    private InnerNode root;
    private String paths[];
    private int depth;
    private int cFileIndex;
    
    
    
    //-------------------------------------------------------
    // Name: MerkleTree, constructor
    // PostCondition: constructs a perfect binary tree
    //---------------------------------------------------------
    public MerkleTree(String[] paths) {
        this.root = new InnerNode();
        this.paths = paths;
        this.depth = log2(paths.length);
        this.cFileIndex = 0;
        
        buildTree(0,root);
    }
    
     
    //-------------------------------------------------------
    // Name: buildTree
    // PostCondition: given required depth, and root node
    // 				  constructs the merkleTree
    //---------------------------------------------------------
    private void buildTree(int depth, InnerNode current) {
    	
    	// if required depth reach, attach the TreeFile nodes to InnerNodes
    	if( this.depth == depth) {
    		
    		// if all files already attached, then continue attaching the last one.
    		// else attach the correct file and move index by 1
    		if (cFileIndex >= paths.length) {
    			
    			current.setFile(new TreeFile(paths[paths.length-1]));
    		}
    		else {
    		
	    		current.setFile(new TreeFile(paths[cFileIndex]));
	    		cFileIndex += 1;
    		}
    		
    		// set InnerNode key to TreeFile's key
    		current.setKey(Integer.toString(current.getFile().getKey().hashCode()));
    		
    	}
    	else {
    		
    		// if not at the desired depth
    		// create a left and right InnerNode
    		current.setLeft(new InnerNode());
    		current.setRight(new InnerNode());
    		
    		// recursively do down the left subtree then right until reaching needed depth
    		buildTree(depth+1, current.getLeft());
        	buildTree(depth+1, current.getRight());
        	
        	// set parent InnerNode key to the keys of the children
        	String leftHash = current.getLeft().getKey();
        	String rightHash = current.getRight().getKey();
        	
        	long result = Long.parseLong(leftHash) + Long.parseLong(rightHash);
        	current.createParentHash(Long.toString(result));
        	
        	
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
    	
    	// currently added to show that hash values are being stored.
    	System.out.println(node.getKey());
    	System.out.println();
    	
    	
    	if (node.getFile() != null) {
    		return 1;
    	}
    	else {
    		return 1 + treeSize(node.getLeft()) + treeSize(node.getRight());
    	}
    	
    }
    
}
