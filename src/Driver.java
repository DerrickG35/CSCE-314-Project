
/* This application mimics the processes of Git (version control) with the use of Merkle Trees. There will be n leaves
 *  representing the files in a directory. The goal is to obtain a hash value for each file which will then be stored
 *  in a single node. All nodes will then be processed into a single hash value at the root, representing the current
 *  status of a git repository. When a file is added, deleted, or edited, we should see changes within the hash values,
 *  giving us a new value at the root. */


import java.io.File;

public class Driver {
    public static void main(String[] args)  {
        File directoryPath = new File("projectFiles");
        String contents [] = directoryPath.list();

        for (int i = 0; i < contents.length; i++) {
            TreeFile file = new TreeFile("projectFiles/" + contents[i]);
        }
        
        
        System.out.println(contents);
        System.out.println(contents.length);
        MerkleTree current = new MerkleTree(contents);
        System.out.println(current.getDepth());
        System.out.println(current.log2(5));
        
        System.out.println();
        System.out.println(current.treeSize(current.root));


    }
}
