/*****************************************
** File:    Driver.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**   This file contains the main driver program for FinalProject.
*
***********************************************/

/* This application mimics the processes of Git (version control) with the use of Merkle Trees. There will be n leaves
 *  representing the files in a directory. The goal is to obtain a hash value for each file which will then be stored
 *  in a single node. All nodes will then be processed into a single hash value at the root, representing the current
 *  status of a git repository. When a file is added, deleted, or edited, we should see changes within the hash values,
 *  giving us a new value at the root. */

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

public class Driver {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        File directoryPath = new File("projectFiles");
        String[] contents = directoryPath.list();


        MerkleTree current = new MerkleTree(contents);
        System.out.println(current.treeSize(current.root()));


    }
}
