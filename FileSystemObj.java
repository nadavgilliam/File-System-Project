/**
 * Created by Nadav on 5/17/2017.
 */

import java.util.*;

/**
 * Abstract FileSystemObj class - Will be extended by File and Directory
 * I used an abstract class that contains fields that are common to any type of object that may be added to the FileSystem in the future.
 */
public abstract class FileSystemObj {

    // **********Fields
    private String name;
    private Date createDate;
    private String parentDir;
    // The HashMap is a 'global' variable that is used to store all the names in the FileSystem.
    // It's main purpose is to shorten the search time in the FileSystem.
    // Searching for files and directories is the most common functionality in this library. It occurs before adding and deleting objects.
    // That is why the most important thing to do is shorten the search runtime.
    private HashMap <String, FileSystemObj> fileSystemInventory;

    // **********Constructor 1
    protected FileSystemObj (String name, String parentDir, HashMap <String, FileSystemObj> FileSystemInventory) {
        this.name = name;
        this.createDate = new Date();
        this.parentDir = parentDir;
        this.fileSystemInventory = FileSystemInventory;
    }

    // **********Getters
    protected String getName() {
        return this.name;
    }

    protected Date getCreateDate() {
        return this.createDate;
    }

    protected String getParentDir() {
        return this.parentDir;
    }

    protected HashMap <String, FileSystemObj> getFileSystemInventory() {
        return this.fileSystemInventory;
    }


    // **********Methods to be implemented individually
    public abstract void print(int tabs);
}
