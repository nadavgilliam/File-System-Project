/**
 * Created by Nadav on 5/17/2017.
 */

import java.util.*;

/**
 * Directory object
 */
public class Directory extends FileSystemObj {

    // **********Fields

    /**
     * One additional field that contains this directory's' sub-files and sub-folders.
     * The data structure is an ArrayList because it dynamic in size and supports adding and removing.
     */
    private ArrayList <FileSystemObj> dirInventory;

    // **********Constructor 1
    public Directory (String name, String parentDir, HashMap <String, FileSystemObj> dirInventory1) {
        super(name, parentDir, dirInventory1); // Inherited constructor
        this.dirInventory = new ArrayList<FileSystemObj>();
    }

    // **********Getter
    public ArrayList<FileSystemObj> getInventory() {
        return this.dirInventory;
    }

    // **********Methods

    /**
     * Add the new object to the current directory's inventory
     * @param newObj
     */
    public void addObj(FileSystemObj newObj) {
        this.dirInventory.add(newObj);
        System.out.println(newObj.getClass() + ": " + newObj.getName() + " was added to " + newObj.getParentDir());
    }

    /**
     * Print function will traverse through the tree in "DFS" order and print all files and folders
     * @param tabs
     */
    public void print(int tabs) { // Print function - obviously not the most efficient way, but I wanted the print to be clear. 'tabs' is used for clearer printing format
        if (!this.getInventory().isEmpty()) { // Make sure there is something to print
            FileSystemObj temp;
            System.out.println(); // print format
            for (int j = 0; j < tabs; j++) { // Indentation
                System.out.print("    ");
            }
            System.out.println(this.getName() + " Files: "); // print format
            System.out.println(); // print format
            for (int i = 0; i < this.getInventory().size(); i++) { // Traverse through all objects of inventory
                temp = this.getInventory().get(i);
                if (temp instanceof File){ // If the current object is a file
                    temp.print(tabs + 1); // send to print in class File
                }
            }
            System.out.println(); // print format
            for (int j = 0; j < tabs; j++) { // Indentation
                System.out.print("    ");
            }
            System.out.println(this.getName() + " Directories: ");
            System.out.println();
            for (int i = 0; i <this.getInventory().size(); i++) {// Traverse *AGAIN* through all objects of inventory
                temp = this.getInventory().get(i);
                if (temp instanceof Directory){ // If the current object is a directory
                    for (int j = 0; j < tabs; j++) { // Indentation
                        System.out.print("    ");
                    }
                    System.out.println("    " + temp.getName() + ", Date Created: " + temp.getCreateDate());
                    temp.print(tabs + 4); // Recursive Print
                }
            }
        }
    }

    /**
     * The delete() function is called from the singleton and is used here to delete the sub-files and sub-folders recursively
     */
    public void delete () {
        int i = 0;
        while (!this.dirInventory.isEmpty()) { // As long as the inventory isn't empty
            if (this.dirInventory.get(i) instanceof File) { // if its a file
                File deleteFile = (File)this.dirInventory.remove(i); // remove the file from the inventory
                this.getFileSystemInventory().remove(deleteFile.getName()); // remove the file from the FileSystemInventory
                System.out.println(deleteFile.getName() + " was deleted"); // let user know file was deleted
            }
            else { // if its a directory
                Directory temp = (Directory)this.dirInventory.remove(i); // remove the directory from the inventory
                temp.delete(); // delete all sub-file and sub-folders of the directory we just removed recursively
                this.getFileSystemInventory().remove(temp.getName()); // remove directory from FileSystemInventory
                System.out.println(temp.getName() + " was deleted"); // let user know
            }
        }
    }
}
