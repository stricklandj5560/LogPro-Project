//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTool {

    String filePath = "";


    /** Class constructor for the FileTool */
    public FileTool(String pathToFile) {

        filePath = pathToFile;

    }


    /** Returns true if a specified file path */
    public boolean fileExists() {

        try {

            return (new File(filePath).exists());

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    /**Rewrites file (overwrites data) */
    public void reWriteFile(ArrayList<String> lines) {

        try {

            FileWriter fileWriter = new FileWriter(filePath, false);
            PrintWriter write = new PrintWriter(fileWriter);

            for (String line : lines) { //Overwrite the old data with the new data.

                write.println(line);

            }

            write.close();
            fileWriter.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /** Returns true if a file is empty */
    public boolean fileIsEmpty() {

        FileTool el = new FileTool("src/ErrorLog.txt");


        try {

            File file = new File(filePath);

            Scanner scan = new Scanner(file, "UTF-8");

            scan.nextLine();

            scan.close();

        } catch (Exception e) { //If an error is thrown, there is nothing in the file.

            return true;

        }

        return false;

    }


    /** Add a line to a file */
    public void addToFile (String lineToAdd) {

        try {

            FileWriter fw = new FileWriter(filePath,true);
            PrintWriter write = new PrintWriter(fw);

            write.println(lineToAdd);

            fw.close();
            write.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**Converts a specified file into an arraylist */
    public ArrayList<String> getFileAsList() {

        ArrayList<String> returnList = new ArrayList<String>();

        if(!fileIsEmpty()) { //If the file isn't empty, get the file as list, otherwise an error will occur.

            try {

                File f = new File(filePath);
                Scanner scan = new Scanner(f, "UTF-8");
                String line = null;

                while ((line = scan.nextLine()) != null) { //Scan each line of the file and append it to the returnlist.

                    if(line.trim().equals("")) {

                        //Do not add line to the returnlist.

                    } else {

                        returnList.add(line);

                    }

                }

                scan.close();

            } catch (Exception e) {


            }

        }

        return returnList;

    }

    /** Renames the selected file */
    public void reNameFile(String newName, String currentFilePath, String baseFilePath) {

        try {

            //New file object with the desired rename.
            File newF = new File(baseFilePath + newName + ".txt");

            File f = new File(currentFilePath); //Current file.

            //Rename current file with the new file name.
            f.renameTo(newF);

            filePath = baseFilePath + newName + ".txt";


        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /** Creates a directory based on the current filepath. */
    public void createDirectory() {

        try {

            File f = new File(filePath);

            if(!fileExists()) { //Make sure the directory doesn't already exist.

                f.mkdir();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /** Creates a new file with the specified file path */
    public void createFile() {

        try {

            File file = new File(filePath);
            file.createNewFile();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /** Deletes the current file. */
    public void deleteFile() {

        try {

            File f = new File(filePath);

            f.delete();


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /** Returns a list of folders in a directory */
    public static ArrayList<String> getListOfFolders(String filePath) {

        ArrayList<String> returnList = new ArrayList<String>();

        try {

            File[] directories = new File(filePath).listFiles(File::isDirectory); //Get list of directories.

            for (File f : directories) { //Get the name of the directories.

                returnList.add(f.getName()); //Add the name of the folder to the returnlist.

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return returnList;

    }


    /** Returns a list of all of the possible file paths in a directory. */
    public static ArrayList<String> getListOfDirectories(String filePath) {

        ArrayList<String> returnList = new ArrayList<String>();

        try {

            File f = new File(filePath);

            File[] files = f.listFiles(); //Get a list of all files in a directory.

            for(File file : files) { //Append the names of those files to the returnList.

                returnList.add(file.getName());

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return returnList;

    }


    /** Copies a directory to a destination directory. */
    public static void copyDirectory(String copyFilePath, String destinationFilePath) {

        try {

            //Get a list of files in the copyFilePath directory.
            ArrayList<String> copyFileList = getListOfDirectories(copyFilePath);

            for(String file : copyFileList) { //Loop through the files and copy them to the new file path.

                if(!file.equals("admin.txt")) { //prevents the user from copying admin files.

                    //Create a fileTool for the current file.
                    FileTool copyFileTool = new FileTool(copyFilePath + file);

                    ArrayList<String> dataToWrite = copyFileTool.getFileAsList();

                    // Create the filepath.
                    FileTool tempTool = new FileTool(destinationFilePath + file);

                    //Create the file.
                    tempTool.createFile();

                    //Write the data from the copy file to the destination file path.
                    tempTool.reWriteFile(dataToWrite);

                }

            }

            //Check for excess files and delete them.
            ArrayList<String> destinationFiles = getListOfDirectories(destinationFilePath);
            ArrayList<String> copyFiles = getListOfDirectories(copyFilePath);


            for(String s : copyFiles) { //Find the files that were copied over and remove them from the destination filelist.


                if(destinationFiles.contains(s)) {

                    destinationFiles.remove(s);


                }

            }

            if(destinationFiles.size() > 0) { //If there are excess files, loop through and remove them.

                for (String file : destinationFiles) {

                    if(!file.equals("admin.txt")) { //Don't delete the admin file.

                        new FileTool(destinationFilePath + file).deleteFile(); //Delete the file.

                    }

                }

            }


        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    /**Creates a directory based on a passed file path. */
    public static void createNewDirectory(String fp) {

        try {

            File f = new File(fp);

            f.mkdir(); //Create directory

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
