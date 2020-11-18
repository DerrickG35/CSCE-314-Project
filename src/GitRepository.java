import java.io.File;

public class GitRepository {
    private MerkleTree remote;
    private MerkleTree local;
    private File directory;

    public GitRepository(MerkleTree remote, MerkleTree local, File directory) {
        this.remote = remote;
        this.local = local;
        this.directory = directory;
    }

    public void gitStatus() {}

    public void gitPush() {}

}
