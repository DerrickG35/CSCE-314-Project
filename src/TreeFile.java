import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;


public class TreeFile extends Node {
	
	private static Scanner sc;
	private Path path;
	
	public TreeFile(String path) {
		this.path = Path.of(path);
	}

	public Path getPath() {
		return path;
	}
	 

	public void setPath(Path path) {
		this.path = path;
	}
}
