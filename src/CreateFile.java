import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFile {
    private String filepath;
    private ArrayList<String> userInput = new ArrayList<String>();
    private FileWriter fileWriter;

    public CreateFile(String filename, ArrayList<String> userInput) throws IOException {
        this.filepath = filename;
        this.userInput = userInput;

        File newFile = new File(filename);
        try {
            newFile.createNewFile();
            System.out.println("File added: " + this.filepath);
        }
        catch (IOException e) {
            System.out.println("Error occurred when creating " + this.filepath);
        }
    }

    public void writeToFile() throws IOException {
        try {
            for (String input : userInput) {
                fileWriter.write(input);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
