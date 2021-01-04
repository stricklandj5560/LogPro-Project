//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.util.ArrayList;

public class Admin {

    private String filePath = "";
    static FileTool fileTool;

    public Admin() {

        OperatingSystem os = new OperatingSystem();

        if (os.isMac) { //MAC os uses "/" and Windows OS uses "\".

            filePath = "src/Users/admin.txt";

        } else {

            filePath = "src\\Users\\admin.txt";

        }

        fileTool = new FileTool(filePath);

    }

    /** Set admin password */
    public void setPassword(String newPassword) {

        ArrayList<String> fileList = fileTool.getFileAsList();

        fileList.set(0, (new Encrypter().encryptText(newPassword))); //Replace the old password with the new one.

        //Rewrite the file.
        fileTool.reWriteFile(fileList);

    }


   /* public void setStandardPassword() {


        String password = new Encrypter().encryptText("password");

        fileTool.addToFile(password);


    } */

    /** Returns true if the passed password matches the administrator's password. */
    public boolean isAdminPassword(String password) {

        //If password equals admin password
        return fileTool.getFileAsList().get(0).trim().equals(new Encrypter().encryptText(password).trim());

    }

    /** Returns a list of all of the users. */
    public ArrayList<String> getUsersList() {

        ArrayList<String> returnList = new ArrayList<String>();

        String userFilePath = "";

        if(new OperatingSystem().isMac) { //Mac uses "/"; Windows uses "\"

            userFilePath = "src/Users";

        } else {

            userFilePath = "src\\Users";

        }

        ArrayList<String> possibleDirectories = FileTool.getListOfDirectories(userFilePath);

        //Get rid of the ".txt" and add files to the returnList.

        for(String s : possibleDirectories) {

            returnList.add(s.substring(0, s.length() - 4));

        }

        return returnList;

    }



}
