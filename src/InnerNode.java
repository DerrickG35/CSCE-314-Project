/*****************************************
** File:    InnerNode.java
** Project: CSCE 314 Final Project, Fall 2020
** Author:  Derrick Galindo, Manuel Trevino
** Date:    11/7/2020
** Section: 502, 501
** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
**
**   This file contains the main driver program for FinalProject.
*
***********************************************/

public class InnerNode extends Node {
	
	private InnerNode left;
	private InnerNode right;
	private TreeFile file;
	
	public InnerNode() {
		left = null;
		right = null;
		file = null;
	}
	
	public void setLeft(InnerNode leftNode) {
		left = leftNode;
	}
	
	public InnerNode getLeft() {
		return left;
	}
	
	public void setRight(InnerNode rightNode) {
		right = rightNode;
	}
	
	public InnerNode getRight() {
		return right;
	}
	
	public void setFile(TreeFile file) {
		this.file = file;
	}
	
	public TreeFile getFile() {
		return file;
	}
	
	
}
