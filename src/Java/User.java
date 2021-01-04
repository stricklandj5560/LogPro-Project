//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;

public class User {

    private String userCode;
    private String password;
    private String filePath;
    private String currentUserFilePath;
    private FileTool fileTool;


    private static DecimalFormat df = new DecimalFormat("0.00");

    /** Class constructor for the User object */
    public User(String userID) {

        userCode = userID;
        OperatingSystem os = new OperatingSystem();

        if(os.isMac) { //Mac OS uses "/" and Windows OS uses "\" when navigating directories.

            filePath = "src/Users/";
            currentUserFilePath = "src/CurrentUser.txt";

        } else {

            filePath = "src\\Users\\";
            currentUserFilePath = "src\\CurrentUser.txt";

        }

        fileTool = new FileTool(filePath + userID + ".txt");

    }


    /** Returns true if the user is an instructor */
    public boolean isInstructor() {

        return fileTool.getFileAsList().get(1).trim().equals("true"); //If the second line of the file equals "true,"
                                                                        // then the user is an instructor.
    }


    /** Links a user to a classroom */
    public void joinClassroom(String classCode) {

        Classroom classroom = new Classroom(classCode);

        classroom.addUser(userCode);

        ArrayList<String> data = fileTool.getFileAsList();

        data.set(2, classCode); //Set the class code to the new class code.

        fileTool.reWriteFile(data);
    }


    /** Sets the user's welcome data */
    public void setWelcomeData(String firstName, String lastName, String classroom, int grade) {

        ArrayList<String> userData = fileTool.getFileAsList();

        if(classroom.trim().equals("") ) { //If there is no classroom, put "null" in its place.

            userData.add("null");

        } else {

            userData.add(classroom);

        }

        userData.add(firstName + "*" + lastName + "*" + String.valueOf(grade));

        fileTool.reWriteFile(userData);

    }


    /** Returns the user's grade. */
    public String getGrade() {

        //Return the grade (4th line of file, split index 2).
        return fileTool.getFileAsList().get(3).split("\\*")[2];

    }


    /**Logs community service hours for a user. */
    public void logHours(String hours, String activity, String date, String contactData, String whatYouDid) {

        fileTool.addToFile(hours + "*" + activity + "*" + date + "*" + contactData + "*" + whatYouDid);

    }

    /** Checks to see if the user is a new user. */
    public boolean isNewUser() {

       ArrayList<String> dataList = fileTool.getFileAsList();

       if(dataList.size() >= 3) { //If there are atleast 3 items, check for new user.

           if(dataList.get(2).equals("newUser")) {

               dataList.remove(2); //Clear out the new user status

               fileTool.reWriteFile(dataList); //Rewrite the user file with the new data.

               return true;

           } else if(dataList.size() < 4) { //User has not completed welcome scene.

               return true;

           }

       } else { //User list is not large enough, return true;

           return true;

       }

       return false; //User is not new user.

    }

    /** Returns the set userCode */
    public String getUserCode() {

        return userCode;

    }

    /** Adds an approved txt to the beginning of a specified logged hour */
    public void approveLoggedHours(int index) {

        int i = index + 4;

        ArrayList<String> editList = fileTool.getFileAsList();

        if(!(editList.get(i).split("\\*").length >= 7)) { //If the selected hours haven't been approved.

            editList.set(i, (editList.get(i) + "*(APR)")); //Add "APR to the end of data of the specified index.

            fileTool.reWriteFile(editList); //Rewrite the user's file.

        }

    }


    /** Adds up all of the user's hours and returns them.*/
    public float getTotalHours() {

        ArrayList<String[]> data = getHourDataList();
        float total = 0;

        df.setRoundingMode(RoundingMode.UP); //Round to two decimal places.

        if(data != null) {

            for(String [] l : data) { //Add up hours.

                total += parseFloat(df.format(parseFloat((l[0])))); //Append hours to the total.

            }

        }

        return parseFloat(df.format(total)); //Round final total to two decimal places.
    }


    /** Returns the user's hours data */
    public ArrayList<String[]> getHourDataList() {

        ArrayList<String[]> returnList = new ArrayList<String[]>();

        ArrayList<String> userData = fileTool.getFileAsList();

        if(userData.size() <= 4) { //No hours have been logged.

            return null;

        } else {

            //Logged hours start at index 3.
            for(int i = 4; i < userData.size(); i++) { //Loop through user data and append data to the return list.

                String [] lineData = userData.get(i).split("\\*"); //Split the data with the regex "*".

                returnList.add(lineData);

            }

        }

        return returnList;

    }


    /** Creates a new user file */
    public void createUser(boolean instructor) {

        fileTool.createFile();

        if(instructor) { //If the user is an instructor, add "true" on the second line of their user document.

            fileTool.addToFile("true");

        } else {

            fileTool.addToFile("false");

        }

        fileTool.addToFile("newUser");

    }

    /** Returns true if passwords match */
    public boolean checkPassword(String pw) {

        String establishedPassword = "";

        ArrayList<String> userData = fileTool.getFileAsList();
        establishedPassword = userData.get(0);

        if(establishedPassword.equals(new Encrypter().encryptText(pw))) { //Check to see if the passwords are the same.

            return true;

        }

        return false;

    }

    /** Set user as current logged in user */
    public void setUserAsCurrentUser() {

        FileTool ft = new FileTool(currentUserFilePath);

        ArrayList<String> dataToAdd = new ArrayList<String>();
        String data = userCode;

        if(isNewUser()) { //If the user is a new user, add a '*' to trigger the welcome scene.

            data += "*";

        }

        dataToAdd.add(data);

        ft.reWriteFile(dataToAdd);

    }

    /** Adds a password to the user file */
    public void setPassword(String encryptedPassword) {

        password = encryptedPassword.trim();

        ArrayList<String> userData = fileTool.getFileAsList();

        if(userData.size() == 0) { //if the arraylist is empty, append to it.

            userData.add(password);

        } else {

            userData.set(0, password);

        }

        fileTool.reWriteFile(userData);

    }


    /**Sets the users classroom.*/
    public void setClassroom (String classCode) {

        ArrayList<String> fileList = fileTool.getFileAsList();

        //Set second line of file to the classroom code.
        fileList.set(2, classCode);

        fileTool.reWriteFile(fileList);

    }

    /** Deletes the user. */
    public void selfDestruct() {


        System.gc();
        fileTool.deleteFile();


    }


    /**Sets the user's first and last name. */
    public void setFullName(String firstName, String lastName) {

        ArrayList<String> fileList = fileTool.getFileAsList();

        String [] oldData = fileList.get(3).split("\\*"); //Get the old name data.

        //Change the names and re-add the data to the file.

        oldData[0] = firstName.trim();

        oldData[1] = lastName.trim();

        String replacementLine = oldData[0] + "*" + oldData[1];

        for(String s : oldData) { //Add the rest of the line data.

            replacementLine += "*" + s;

        }

        //replace the line in the file.
        fileList.set(3, replacementLine);

        fileTool.reWriteFile(fileList);

    }


    /** Returns the classroom of the current user. */
    public String getClassCode () {

        ArrayList<String> userData = fileTool.getFileAsList();

        String classroomCode;

        if(!(userData.size() > 2)) {

            return "null";

        }

        classroomCode = userData.get(2);


        return classroomCode;

    }

    /** Returns true if the user exists */
    public boolean exists() {

       return fileTool.fileExists();

    }

    /**Returns the Classroom that user is associated with. */
    public Classroom getClassroom() {

        String classCode = getClassCode();

        return new Classroom(classCode);

    }

    /**Returns the users first and last name separated by a space. */
    public String getFullName() {

       String [] nameList = fileTool.getFileAsList().get(3).split("\\*"); //Splits line 4 of the user's file.


        return (nameList[0] + " " + nameList[1]); //Index 0: first name, index 1: last name.


    }

    /**Returns true if the user has incomplete data */
    public boolean isMissingData() {

        //If the size is less than or equal to two items, the user has incomplete data.
        return (fileTool.getFileAsList().size() <= 2);

    }

    /**Removes a specified logged hour from the user. */
    public void removeHours(int index) {

        ArrayList<String> userData = fileTool.getFileAsList();

        userData.remove(index + 4);

        fileTool.reWriteFile(userData);

    }

}
