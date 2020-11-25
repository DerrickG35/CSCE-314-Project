import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteFile {
    private String filepath;

    public DeleteFile(String filepath) throws IOException {
        this.filepath = filepath;
        try {
            Files.delete(Path.of(this.filepath));
        }
        catch (IOException e) {
            System.out.println("Failed to delete file");
        }
    }
}
