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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class GitRepository {
	
    private MerkleTree remote;
    private MerkleTree local;
    private File directory;
    private ArrayList<String> contents = new ArrayList<String>();
    private HashSet<String> deleted = new HashSet<String>();
    private HashSet<String> added = new HashSet<String>();

    public GitRepository() {
    	directory = new File("projectFiles");
        File[] files = directory.listFiles();
        setContents(files);

        remote = new MerkleTree(getContents());
        local = new MerkleTree(getContents());
        System.out.println("Initializing local and remote branches");
    }
    
    //-------------------------------------------------------
    // Name: gitStatus()
    // PreCondition: MerkleTrees initialized with files
    // PostCondition: Compares local and remote hashes to find what files have been modified.
    //---------------------------------------------------------
    public void gitStatus() {
        System.out.println("Comparing local changes to remote repository");
    	InnerNode remoteRoot = remote.root();
    	InnerNode localRoot = local.root();
    	
    	if (remoteRoot.getKey().equals(localRoot.getKey()) && added.size() == 0 && deleted.size() == 0) {
    		System.out.println("Everything up to date");
    	}
    	else {
    		
    		// calls recursive statement for git status, comparing the hash values in the trees
    		_gitStatus(remoteRoot, localRoot);
    		
    		
    		for(String name: added) {
    			System.out.println("added: " + name);
    		}
    		for(String name: deleted) {
    			System.out.println("deleted: " + name);
    		}
    	}
    	
    	
    	
    }
    
    //-------------------------------------------------------
    // Name: _gitStatus(), helper function
    // PreCondition: MerkleTrees initialized with files
    // PostCondition: prints out what files have been modified
    // 				  by recursively comparing the hashes of the
    // 				  remote and local merkle trees
    //---------------------------------------------------------
    private void _gitStatus(InnerNode remote, InnerNode local) {
    	
    	if (local.getFile() != null) {
    		
    		if(!(remote.getKey().equals(local.getKey()))) {
    			
    			String path = local.getFile().getPath().toString();
    			String[] name = path.split("\\|/");
    			path = name[0] + "/" + name[1];
    			
    			if(deleted.contains(path)) {
    				return;
    			}
    			
    			
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

    //-------------------------------------------------------
    // Name: gitPush()
    // PreCondition: MerkleTrees initialized with files
    // PostCondition: Either prints out everything is up-to-date
    //				  or calls _gitPush() to update files
    //---------------------------------------------------------
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

    //-------------------------------------------------------
    // Name: _gitPush(), helper function
    // PreCondition: MerkleTrees initialized with files
    // PostCondition: updates required files by recursively comparing
    //				  the hashes of the local and remote merkle tree
    //				  to see which files have been modified.
    //---------------------------------------------------------
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
    
    
    // updates local merkle tree to reflect the changes made to the files
    public void updateLocal() {
    	
    	local = new MerkleTree(contents);
    	
    }
    
    
    // prints out the hashes for both remote and local merkle trees.
    public void compareHashes() {
    	remote.treeSize(remote.root());
    	System.out.println();
    	local.treeSize(local.root());
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(File[] files) {
        contents.clear();
        for(File currFile : files) {
            contents.add(currFile.getPath());
        }
        Collections.sort(contents);
    }
    
    public String inContents(String file) {
    	for(String checkFile : contents) {
    		if (checkFile.contains(file)) {
    			return checkFile;
    		}
    	}
    	return null;
    }
    
    
    public File getDirectory() {
    	return this.directory;
    }

    public void gitAdd(String filename) throws IOException {
        File newFile = new File(filename);
        try {
            newFile.createNewFile();
            System.out.println("File added: " + filename);
            setContents(directory.listFiles());
        }
        catch (IOException e) {
            System.out.println("Error occurred when creating " + filename);
        }
    }

    public void gitRemove(String filename) throws IOException {
        try {
            Files.delete(Path.of(filename));
            System.out.println("File removed: " + filename);
            setContents(directory.listFiles());
        }
        catch (IOException e) {
            System.out.println("Failed to delete file");
        }
    }
    
    
}
