import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class TreeFile {
	
	private static Scanner sc;
	
	private String hash;
	private String path;
	
	public TreeFile(String path) {
		this.path = path;
		hash = createHash();
	}
	
	protected String getHash() { return hash; } ;
	
	private String createHash() {
		File file = new File(path);
		
		try {
			sc = new Scanner(file);
			
		}
		catch (FileNotFoundException y) {
			y.printStackTrace();
		}
		
		return "temporary";
	}
}
