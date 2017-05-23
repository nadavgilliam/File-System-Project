/**
 * Created by Nadav on 5/17/2017.
 */

import java.util.*;

/**
 * Singleton class FileSystem
 */
public class FileSystem {

    // **********Fields
    private Directory root;
    private HashMap <String, FileSystemObj> fileSystemInventory = new HashMap <String, FileSystemObj>();
    // Singleton implementation -
    private static FileSystem fileSystemHolder = new FileSystem();


    // **********Constructors
    private FileSystem () {
        System.out.println("Created FileSystem");
        this.root = new Directory("Root", "Root", this.fileSystemInventory);
        this.fileSystemInventory.put("Root", this.root);
    }

    // Singleton implementation
    public static FileSystem getInstance() {
        return fileSystemHolder;
    }

    // **********Methods

    /**
     * addFile will generate a new file and send it to the function authorizeAndAdd.
     * I use a separate function because the actions are similar here and in addDir() so I want to avoid code repetition
     * @param parentDirName - location to add new file
     * @param fileName - the name of the new file
     * @param fileSize - the size of the new file
     */
    public void addFile (String parentDirName, String fileName, int fileSize) {
        if (fileName.length() > 32){
            System.out.println("File name too long - limited to 32 chars");
        }
        else
            authorizeAndAdd(new File(fileName, fileSize, parentDirName, this.fileSystemInventory));
    }

    /**
     * addDir will generate a new directory and send it to the function authorizeAndAdd.
     * I use a separate function because the actions are similar here and in addFile() so I want to avoid code repetition
     * @param parentDirName - location of the new directory
     * @param dirName - name of the new directory
     */
    public void addDir (String parentDirName, String dirName) {
        if (dirName.length() > 32){
            System.out.println("File name too long - limited to 32 chars");
        }
        else
            authorizeAndAdd(new Directory(dirName, parentDirName, this.fileSystemInventory));
    }

    /**
     * delete will delete the requested obj (and all of its inventory if it's of type directory) from the FileSystem.
     * Also, it will delete the obj from the fileSystemInventory.
     * @param name - the name of the object we are deleting
     */
    public void delete (String name) {
        // Hash Implementation
        if (this.fileSystemInventory.containsKey(name)) { // use the fileSystemInventory to find if the name exists in the inventory
            FileSystemObj deleteObj = this.fileSystemInventory.remove(name); // retrieve the object from the fileSystemInventory if so
            if (deleteObj instanceof Directory) { // if the object is a directory
                ((Directory) deleteObj).delete();
            }
                Directory parentOfDeleteObj = (Directory)this.fileSystemInventory.get(deleteObj.getParentDir()); // retrieve the parent directory
                parentOfDeleteObj.getInventory().remove(deleteObj); // delete the requested object from the parent directory
                System.out.println(deleteObj.getName() + " was deleted from " + parentOfDeleteObj.getName()); // let user know
        } else { // The name doesn't exist in the FileSystem - cannot be deleted
            System.out.println("Delete Failed - " + name + " does not exist");
        }
    }

    /**
     * Prints the entire FileSystem
     */
    public void showFileSystem() {
        this.root.print(0);
    }

    /**
     * authorizeAndAdd will first check to see if the new object is valid - meaning that it's name is unique.
     * Then, the function will check that it's parent directory already exists in the FileSystem and that it is in fact a directory.
     * If so, add the new object.
     * @param newObj - the new object that I am adding
     */
    private void authorizeAndAdd (FileSystemObj newObj) {
        if (!this.fileSystemInventory.containsKey(newObj.getName())) { // unique name
            if (this.fileSystemInventory.containsKey(newObj.getParentDir()) && this.fileSystemInventory.get(newObj.getParentDir()) instanceof Directory) { // parent directory is a directory and already exists in the system
                Directory parentDirectory = (Directory) this.fileSystemInventory.get(newObj.getParentDir()); // retrieve instance of the parent directory
                this.fileSystemInventory.put(newObj.getName(), newObj); // add the newObject to the fileSystemInventory
                parentDirectory.addObj(newObj);// add the newObject to it's parent directory's inventory
            }
            else {
                System.out.println(newObj.getParentDir() + " doesn't exist or is not a directory");
            }
        }
        else
            System.out.println(newObj.getName() + " already exists");
    }
}

