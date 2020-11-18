import java.io.File;
import java.util.Arrays;

public class GitRepository {
    private MerkleTree remote;
    private MerkleTree local;
    private File directory;

    public GitRepository() {
        this.remote = remote;
        this.local = local;
        this.directory = directory;
    }
    
    private void init(File directoryPath) {
    	directoryPath = new File("projectFiles");
        String contents [] = directoryPath.list();
        Arrays.sort(contents);
        
    	
    }

    public void gitStatus() {}

    public void gitPush() {}

}
