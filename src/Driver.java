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
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	private static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    	
    	
    	GitRepository gitRepository = null;
    	System.out.print("Run command prompt, Y or N: if no a demo will run ");
    	String input = sc.nextLine().strip();
    	
    	if(input.equals("Y")) {
    		System.out.println("print out instructions here: ");
    		while(true) {
    			input = sc.nextLine().strip();
    	
    			
    			switch(input) {
    				case "git init":
    					gitRepository = new GitRepository();
    					break;
    					
    				case "git status":
    					
    					try {
    						gitRepository.gitStatus();
    					} catch (Exception e) {
    						System.out.println("Repository not initialized");
    					}
    					break;
    					
    				case "git push":
    					try {
    						gitRepository.gitPush();
    					} catch (Exception e) {
    						System.out.println("Repository not initialized");
    					}
    					break;
    					
    				case "git edit":
    					
    					ArrayList<String> userInput = new ArrayList<String>();
    					System.out.println("Enter the name of the file to edit: ");
    					input = sc.nextLine().strip();
    					boolean result = false;
    					String fileName = input;
    					System.out.println(fileName);
    					
    					try {
    						result = gitRepository.inContents(fileName);
    					} catch (Exception e) {
    						System.out.println("Repository not initialized");
    						break;
    					}
    					
    					if (!result) {
    						System.out.println("file not in system, run \"git {command}\" to create a file ");
    						break;
    					}
    					
    					System.out.println("editing file, type \"STOP\" to save. \n");
    					
    					while(true) {
    						input = sc.nextLine().strip();
    						if(input.equals("STOP")) {
    							break;
    						} else {
    							userInput.add(input);
    						}
    					}
    					EditFile file = new EditFile(fileName, userInput);
    					file.writeToFile();
    					gitRepository.updateLocal();
    					System.out.println("Saving changes and exiting file. \n");
    					break;
    				
    				case "quit":
    					System.exit(0);
    				
    				default:
    					System.out.println("Please type a valid command, type \"help\" to see commands");
    					
    			}
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    		}
    		
    		
    		
    		
    	}
    	else {
 
    		System.out.println("Running git repository demo");
    		
            // Building Git Repository
            gitRepository = new GitRepository();
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
}
