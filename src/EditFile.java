/*****************************************
 ** File:    EditFile.java
 ** Project: CSCE 314 Final Project, Fall 2020
 ** Author:  Derrick Galindo, Manuel Trevino
 ** Date:    12/6/2020
 ** Section: 502, 501
 ** E-mail:  derrickg@tamu.edu, manuelraul5@tamu.edu
 **
 **   This file contains the EditFile objects that allows the user to make changes to an existing file.
 *    Its attributes are a filepath, a filewriter to take in user input, and an ArrayList to store the data.
 ***********************************************/

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

    //---------------------------------------------------------------------
    // Name: writeToFile()
    // PreCondition:  Empty file attribute
    // PostCondition: Uses FileWriter to write in the users input stored in
    //                the ArrayList to at the given filepath.
    //----------------------------------------------------------------------
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
