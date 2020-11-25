import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditFile {
    private String filepath;
    private FileWriter fileWriter;
    private ArrayList<String> userInput = new ArrayList<String>();

    public EditFile(String filepath, ArrayList<String> userInput) throws IOException {
        File file = new File(filepath);
        this.filepath = filepath;
        this.userInput = userInput;
        this.fileWriter = new FileWriter(file, true);
    }

    public void writeToFile() throws IOException {
        try {
            for (String input : userInput) {
                fileWriter.write("\n" + input);
            }
            fileWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
