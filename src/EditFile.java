import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditFile {
    File file;
    FileWriter fileWriter;
    ArrayList<String> userInput = new ArrayList<String>();

    public EditFile(File file, ArrayList<String> userInput) throws IOException {
        this.file = file;
        this.userInput = userInput;
        this.fileWriter = new FileWriter(file, true);
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
