/*****************************************
 ** File:    GitRepository.java
 ** Project: CSCE 314 Final Project, Fall 2020
 ** Author:  Derrick Galindo, Manuel Trevino
 ** Date:    11/22/2020
 ** Section: 502, 501
 ** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
 **
 **   This file demonstrates the functionalities of our Merkle Tree Project. GitRepository consists of some of the
 *    most common git commands, git status and git push. We use these methods to demonstrate if our remote repository
 *    (Main Merkle Tree) is up to date with all local changes (local Merkle Tree).
 *
 ***********************************************/

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class GitRepository {
	
    private MerkleTree remote;
    private MerkleTree local;
    private File directory;
    private String[] contents;

    public GitRepository() {
    	directory = new File("projectFiles");
        contents = directory.list();
        Arrays.sort(contents);
        
        remote = new MerkleTree(contents);
        local = new MerkleTree(contents);
        System.out.println("Initializing local and remote branches\n");
    }
    

    public void gitStatus() {
        System.out.println("Comparing local changes to remote repository");
    	InnerNode remoteRoot = remote.root();
    	InnerNode localRoot = local.root();
    	
    	if (remoteRoot.getKey().equals(localRoot.getKey())) {
    		System.out.println("Everything up to date");
    	}
    	else {
    		_gitStatus(remoteRoot, localRoot);
    	}
    	
    	
    	
    }
    
    private void _gitStatus(InnerNode remote, InnerNode local) {
    	
    	if (local.getFile() != null) {
    		
    		if(!(remote.getKey().equals(local.getKey()))) {
    			System.out.println("modified: " + local.getFile().getPath());
    		}
    		
    	}
    	else if (remote.getKey().equals(local.getKey())) {
    		return;
    	}
    	else {
    		_gitStatus(remote.getLeft(), local.getLeft());
    		_gitStatus(remote.getRight(), local.getRight());
    	}
    	
    }

    public void gitPush() throws NoSuchAlgorithmException {
        System.out.println("Pushing files to remote repository");
        InnerNode remoteRoot = remote.root();
        InnerNode localRoot = local.root();
        int numberOfFilesChanged = 0;

        if (remoteRoot.getKey().equals(localRoot.getKey())) {
            System.out.println("Everything up to date");
        }
        else {
            numberOfFilesChanged = _gitPush(remoteRoot, localRoot);
        }
        System.out.println("Number of files changed: " + numberOfFilesChanged);
    }

    private int _gitPush(InnerNode remote, InnerNode local) throws NoSuchAlgorithmException {
    	
        if (local.getFile() != null) {

            if(!(remote.getKey().equals(local.getKey()))) {
                remote.setFile(local.getFile());
                remote.setKey(local.getKey());
                System.out.println("Added: " + local.getFile().getPath());
                return 1;
            }

        }
        else if (remote.getKey().equals(local.getKey())) {
            return 0;
        }
        else {
        	
            int temp = _gitPush(remote.getLeft(), local.getLeft()) + _gitPush(remote.getRight(), local.getRight());
            remote.createParentHash(remote.getLeft().getKey() + remote.getRight().getKey());
            return temp;
        }
        return 0;
    }
    
    public void updateLocal() {
    	
    	local = new MerkleTree(contents);
    	
    }
    
    public void compareHashes() {
    	remote.treeSize(remote.root());
    	System.out.println();
    	local.treeSize(local.root());
    }
    
    
    
}
