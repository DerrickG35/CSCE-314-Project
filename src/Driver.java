
/* This application mimics the processes of Git (version control) with the use of Merkle Trees. There will be n leaves
 *  representing the files in a directory. The goal is to obtain a hash value for each file which will then be stored
 *  in a single node. All nodes will then be processed into a single hash value at the root, representing the current
 *  status of a git repository. When a file is added, deleted, or edited, we should see changes within the hash values,
 *  giving us a new value at the root. */


public class Driver {
    public static void main(String[] args)  {
        MerkleTree MT = new MerkleTree();

    }
}
