
public class InnerNode extends Node{
	
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
