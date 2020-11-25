import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFile {
    private String filename;
    private ArrayList<String> userInput = new ArrayList<String>();

    public CreateFile(String filename, ArrayList<String> userInput) throws IOException {
        this.filename = filename;
        this.userInput = userInput;

        File newFile = new File(filename);
        try {
            newFile.createNewFile();
            System.out.println("File added: " + this.filename);
        }
        catch (IOException e) {
            System.out.println("Error occurred when creating " + this.filename);
        }
    }
}
