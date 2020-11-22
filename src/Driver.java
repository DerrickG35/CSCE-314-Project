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
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Driver {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // Building Git Repository
        GitRepository gitRepository = new GitRepository();
        gitRepository.gitStatus();

        System.out.println();

        System.out.println("Making local changes...");
        try {
            File editedFile = new File("projectFiles/a.txt");
            File editedFile1 = new File("projectFiles/g.txt");
            FileWriter fileWriter = new FileWriter(editedFile, true);
            FileWriter fileWriter1 = new FileWriter(editedFile1, true);
            fileWriter.write("\nCSCE 314 Project");
            fileWriter1.write("hello world");
            fileWriter.close();
            fileWriter1.close();
        }
        catch(IOException e) {
        	
            e.printStackTrace();
        }

        /* Saving changes to the local Merkle Tree and checking the status. (Comparing it to the remote repository)
          Since local changes have been made, the call to gitStatus should show us the files that have been modified. */
        gitRepository.updateLocal();
        gitRepository.gitStatus();

        // Pushing local changes
        System.out.println();
        gitRepository.gitPush();

        /* All local changes have already been pushed and no further changes have been made, so we should see a message
         notifying us the repository is up to date. */
        System.out.println();
        gitRepository.gitStatus();

    }
}
