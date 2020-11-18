import java.io.File;
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
           
    }
    

    public void gitStatus() {
    	
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

    public void gitPush() {
    	
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
