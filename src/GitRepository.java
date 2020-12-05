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
    private ArrayList<String> contents1 = new ArrayList<String>();
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
    			
    			String path = mW(local.getFile().getPath().toString());
    			
    			if(deleted.contains(path)) {
    				return;
    			}
    			
    			
    			System.out.println("modified: " + mW(local.getFile().getPath().toString()));
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

        if (remoteRoot.getKey().equals(localRoot.getKey()) && added.size() == 0 && deleted.size() == 0) {
            System.out.println("Everything up to date");
        }
        else {
        	
        	
            numberOfFilesChanged = _gitPush(remoteRoot, localRoot);
            
            // only building remote tree when a file was added or removed
            if(added.size() != 0 || deleted.size() != 0) {
            	contents.clear();
            	for (String filename: contents1) {
            		contents.add(filename);
            	}
            	updateLocal();
            	remote = new MerkleTree(contents);
            	
            	contents1.clear();
            }
            
            for(String name: added) {
    			System.out.println("added: " + name);
    		}
    		for(String name: deleted) {
    			System.out.println("deleted: " + name);
    		}
        }
        System.out.println("Number of files changed: " + (numberOfFilesChanged + deleted.size() + added.size()));
        added.clear();
        deleted.clear();
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
                System.out.println("added: " + mW(local.getFile().getPath().toString()));
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
    
    
    // updates local Merkle tree to reflect the changes made to the files
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
    
    
    // sets contents array to file paths
    public void setContents(File[] files) {

        for(File currFile : files) {
            contents.add(currFile.getPath());
        }
        Collections.sort(contents);
    }
    
    
    // sets contents1 array to file paths
    public void setContents1(File[] files) {

    	contents1.clear();
        for(File currFile : files) {
            contents1.add(currFile.getPath());
        }
        Collections.sort(contents1);
    }
    
    
    // returns path of a file if file is in contents array
    public String inContents(String file) {
    	
    	for(String checkFile : contents) {
    		if (checkFile.contains(file)) {
    			return checkFile;
    		}
    	}
    	
    	for(String checkFile : contents1) {
    		if (checkFile.contains(file)) {
    			return checkFile;
    		}
    	}
    	return null;
    }
    
    

    public void gitAdd(String filename) throws IOException {
    	
    	added.add(filename);
    	deleted.remove(filename);
    	
        File newFile = new File(filename);
        try {
            newFile.createNewFile();
            System.out.println("File added: " + filename);
            setContents1(directory.listFiles());
        }
        catch (IOException e) {
            System.out.println("Error occurred when creating " + filename);
        }
    }

    public void gitRemove(String filename) throws IOException {
    	
    	deleted.add(filename);
    	added.remove(filename);
    	
        try {
            Files.delete(Path.of(filename));
            System.out.println("File removed: " + filename);
            setContents1(directory.listFiles());
        }
        catch (IOException e) {
            System.out.println("Failed to delete file");
        }
    }
    
    
    // returns true if a given file is in the deleted or added hash set.
    public boolean checkHashSets(String filename) {
		filename = mW(filename);
		
		if ( added.contains(filename) || deleted.contains(filename)) {
			return true;
		}
    	
    	return false;
    }

    
    // converts Windows directory path to macOS
	public String mW (String filename) {
		String[] name = filename.split("\\\\");
		if(name.length == 2) {
			filename = name[0] + "/" + name[1];
		}
		return filename;
	}
	
}
