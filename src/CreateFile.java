import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFile {
    private String filepath;
    private FileWriter fileWriter;

    public CreateFile(String filename) throws IOException {
        this.filepath = filename;

        File newFile = new File(filename);
        try {
            newFile.createNewFile();
            System.out.println("File added: " + this.filepath);
        }
        catch (IOException e) {
            System.out.println("Error occurred when creating " + this.filepath);
        }
    }
}
