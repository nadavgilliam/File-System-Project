/**
 * Created by Nadav on 5/17/2017.
 */

import java.util.*;

/**
 * File object
 */
public class File extends FileSystemObj {

    // **********Fields
    private long size;

    // **********Constructor 1
    public File(String name, long size, String parentDir, HashMap <String, FileSystemObj> FileSystemInventory) {
        super(name, parentDir, FileSystemInventory); // inherited constructor
        this.size = size;
    }

    // **********Method
    public long getSize(){
        return this.size;
    }

    /**
     * The implemented print function for files
     * @param tabs - The amount of spaces to before each File print
     */
    public void print(int tabs) {
        for (int j = 0; j < tabs; j++) { // Indentation
            System.out.print("    ");
        }
        System.out.println(this.getName() + ", Size: " + this.getSize() + ", Date Created: " + this.getCreateDate());
    }
}
