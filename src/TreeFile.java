/*****************************************
** File:    TreeFile.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**   This file contains the TreeFile objects that serve as the outer nodes of our Merkle Tree.
 *   TreeFile is inherited from node, which allows us to create a hash of the file at the path
 *   passed to this object.
*
***********************************************/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;


public class TreeFile extends Node {

	private Path path;

	public TreeFile(String path) {

		// Gets the entire String path and is then converted to a Path object
		path = "projectFiles/" + path;
		this.path = Path.of(path);
		
		try {
			createHash(this.path);
		} catch (NoSuchAlgorithmException | IOException e) {
			System.out.println("failing");
		} 
	}

	public Path getPath() {
		return path;
	}
	 

	public void setPath(Path path) {
		this.path = path;
	}
	
	
}
