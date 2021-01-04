//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.util.ArrayList;

public class Backup {

    private String backupFilePath = "";
    private String backupUsersFilePath = "";
    private String backupClassroomsFilePath = "";
    private String usersFilePath = "";
    private String classroomFilePath = "";

    private OperatingSystem os = new OperatingSystem();
    private FileTool backupDatesFileTool;
    private FileTool backupFileTool;

    public Backup (String date) {

        //String date = "01-08-2020";


        if(os.isMac) { //Mac uses "/" Windows uses "\"

            backupFilePath = "src/Backups/" + date + "/";
            backupUsersFilePath = backupFilePath + "Users/";
            backupClassroomsFilePath = backupFilePath + "Classrooms/";
            usersFilePath = "src/Users/";
            classroomFilePath =  "src/Classrooms/";


        } else {

            backupFilePath = "src\\Backups\\" + date + "\\";
            backupUsersFilePath = backupFilePath + "Users\\";
            backupClassroomsFilePath = backupFilePath + "Classrooms\\";
            usersFilePath = "src\\Users\\";
            classroomFilePath = "src\\Classrooms\\";


        }

        backupFileTool = new FileTool(backupFilePath); //backup file path.

    }


    /**Performs a backup even if a backup has been performed today. */
    public void performBackup () {

        //Directly perform backup.

        //Copy the users file path to the backup users file path.
        FileTool.copyDirectory(usersFilePath, backupUsersFilePath);

        //Copy the classrooms file path the backup users file path.
        FileTool.copyDirectory(classroomFilePath, backupClassroomsFilePath);


    }


    /** Performs a backup if one hasn't been performed today. */
    public void performBackupIfNeeded() {


        if(!backupFileTool.fileExists()) { //Backup hasn't been done today, perform backup.

            backupFileTool.createDirectory();

            FileTool.createNewDirectory(backupUsersFilePath);
            FileTool.createNewDirectory(backupClassroomsFilePath);

            //Copy the users file path to the backup users file path.
            FileTool.copyDirectory(usersFilePath, backupUsersFilePath);

            //Copy the classrooms file path the backup users file path.
            FileTool.copyDirectory(classroomFilePath, backupClassroomsFilePath);


        }

    }


    /** Returns a list of backups. */
    public static ArrayList<String> getBackupList() {

        String returnPath;

        if(new OperatingSystem().isMac) { //Windows uses "\" and Mac uses "/"

            returnPath = "src/Backups/";

        } else {

            returnPath = "src\\Backups\\";

        }

        return FileTool.getListOfFolders(returnPath); //Get a list of folders for the backup file path.

    }


    /** Restores file from passed backup. */
    public void restoreBackup() {

        //Restore the classrooms files with the backup classrooms files.
        FileTool.copyDirectory(backupClassroomsFilePath, classroomFilePath);

        //Restore the user files with the backup user files.
        FileTool.copyDirectory(backupUsersFilePath, usersFilePath);

    }



}
