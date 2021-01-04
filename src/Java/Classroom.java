//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;

public class Classroom {

    private String classroomCode;
    private String filePath = "";

    public static OperatingSystem os = new OperatingSystem();
    FileTool fileTool;

    public Classroom (String classCode) {

        classroomCode = classCode;

        if(os.isMac) { //Mac OS uses "/" and Windows OS uses "\" when navigating directories.

            filePath = "src/Classrooms/";

        } else {

            filePath = "src\\Classrooms\\";

        }

        fileTool = new FileTool(filePath + classCode + ".txt");

    }


    /**Adds a user to a classroom. */
    public void addUser(String user) {

        ArrayList<String> classroomData = fileTool.getFileAsList();

        boolean userIsInFile = false;

        for(int i = 2; i < classroomData.size(); i++) {

            if(classroomData.get(i).equals(user)) { //Check to see if the user is already added.

                userIsInFile = true;

            }

        }

        if(!userIsInFile) { //If the user isn't added to the file, add the user.

            classroomData.add(user);

        }

        fileTool.reWriteFile(classroomData);

    }


    /** Creates an award for the classroom. */
    public void createAward(String title, String hoursRequired, String description) {

        ArrayList<String> fileList = fileTool.getFileAsList();

        //Format index 1 (line 2) with format AVERAGE-HOURS*TITLE%HOURS-REQUIRED%DESCRIPTION
        fileList.set(1, (fileList.get(1) + "*" + title + "%" + hoursRequired + "%" + description));

        fileTool.reWriteFile(fileList);

    }


    /**Returns an array list of type arrays with awards associated with the classroom. */
    public ArrayList<String[]> getAwards() {

        ArrayList<String[]> awardList = new ArrayList<String[]>();

        String line = fileTool.getFileAsList().get(1); //Get line of file where awards are located.

        if(!line.contains("*")) { //No awards.

            return null;

        } else {

            String[] a = line.split("\\*"); //Get a list of awards.

            for(int i = 1; i < a.length; i++) { //Loop through list and split award data.

                //FORMAT: [TITLE], [HOURS REQUIRED], [DESCRIPTION]
                awardList.add(a[i].split("%")); //Add award data.

            }

        }

        return awardList;

    }


    /** Returns a list of users that have completed a specified award. */
    public ArrayList<User> getUsersWhoHaveCompletedAward(int awardIndex) {

        ArrayList<User> returnList = new ArrayList<User>();

        //Get the award and select the second index (hours required) and get the integer value of the hour requirement
        int hourRequirement = Integer.valueOf((fileTool.getFileAsList().get(1).split("\\*")[awardIndex + 1].split("%")[1]));

        ArrayList<String> studentList = getStudentList();

        //Loop through students and get their total hours to compare with the hour requirement.
        for(String s : studentList) {

            User u = new User(s);

            if(u.getTotalHours() >= hourRequirement) { //User has met hour requirement, add them to the returnList.

                returnList.add(u);

            }

        }

        return returnList;

    }

    /** Removes a specified award. */
    public void removeAward(int awardIndex) {

        ArrayList<String> classroomData = fileTool.getFileAsList(); //Get file data.

        //get awards and remove specified award.
        ArrayList<String[]> data = getAwards();

        data.remove(awardIndex);

        String newLineData = classroomData.get(1).split("\\*")[0].trim(); //Keep average hours.

        for(String[] s : data) { //Append the rest of the awards.

            newLineData += "*" + s[0] + "%" + s[1] + "%" + s[2];

        }

        //Set index 1 to the new data.
        classroomData.set(1, newLineData);

        //re write classroom file.
        fileTool.reWriteFile(classroomData);

    }


    /**Updates a specified award.*/
    public void updateAward(int awardIndex, String title, String hoursRequired, String description) {

        ArrayList<String> classroomData = fileTool.getFileAsList(); //Get file data.

        //get awards and update specified award.
        ArrayList<String[]> data = getAwards();

        String[] newAwardData = {(title), (hoursRequired), description};
        data.set(awardIndex, newAwardData); //Replace the old data with the new data.

        String newLineData = classroomData.get(1).split("\\*")[0].trim(); //Keep average hours.

        for(String[] s : data) { //Append the rest of the awards.

            newLineData += "*" + s[0] + "%" + s[1] + "%" + s[2];

        }

        classroomData.set(1, newLineData); //Replace the old line with the new one.

        fileTool.reWriteFile(classroomData); //Rewrite file.



    }


    /**Returns a list of awards that a specified student has earned. */
    public ArrayList<String[]> getStudentAwards(String user) {

        float hoursEarned = new User(user).getTotalHours();

        ArrayList<String[]> awardList = getAwards();
        ArrayList<String[]> returnList = new ArrayList<String[]>();

        if(awardList != null) {

            for (String[] s : awardList) { //Loop through awards and find the awards that the user has met the requirement for.

                if (parseFloat(s[1]) <= hoursEarned) { //If the user has met the hour requirement, add the award to the return list.

                    returnList.add(s);

                }

            }

        }

        return returnList;

    }


    /** Removes all of the users and deletes the classroom. */
    public void selfDestruct() {

        //Remove users
        ArrayList<String> studentList = getStudentList();

        for(String s : studentList) {

            new User(s).setClassroom("null"); //Sets the classrooms as null.

        }

        //Remove instructor.
        new User(getClassroomInstructor()).setClassroom("null");

        //Delete classroom file.
        fileTool.deleteFile();


    }


    /**Removes a specified user from the classroom. */
    public void removeUser (String userCode) {

         ArrayList<String> fileList = fileTool.getFileAsList();

         ArrayList<String> studentList = getStudentList();

         for(int i = 0; i < studentList.size(); i++) {

             if(studentList.get(i).equals(userCode)) { //Remove user.

                 fileList.remove(i + 2); //Skip over the basic classroom data and remove user at index i + 2.

                 //Set user's classroom to null.
                 new User(userCode).setClassroom("null");

             }

         }

         //Rewrite classroom file.
        fileTool.reWriteFile(fileList);


    }


    /** Retruns the classroom code. */
    public String getClassroomCode() {

        return classroomCode;

    }

    /** Returns a list of the classroom's students. */
    public ArrayList<String> getStudentList() {

        ArrayList<String> classData = fileTool.getFileAsList();

        ArrayList<String> returnList = new ArrayList<String>();

        for(int i = 2; i < classData.size(); i++) { //Loop through the file and pull out the user data.

            returnList.add(classData.get(i)); //Append user data to the return list.

        }

        return returnList;

    }


    /**Changes classroom code for users and classroom. */
    public void changeClassroomCode(String newClassCode) {

        newClassCode = newClassCode.trim();

        ArrayList<String> studentList = getStudentList(); //Get list of students.

        if(studentList.size() > 0) { //Make sure there are students in the list view.

            for(String s : studentList) { //Change class code for each user linked with the classroom.

                new User(s).setClassroom(newClassCode);

            }

        }

        //Change class code for instructor.
        new User(getClassroomInstructor()).setClassroom(newClassCode);

        //Rename the classroom file.
        fileTool.reNameFile(newClassCode, (filePath + classroomCode + ".txt"), filePath);

        classroomCode = newClassCode;

    }


    /**Returns the instructor's username */
    public String getClassroomInstructor() {

        return fileTool.getFileAsList().get(0); //Return the instructor's username.

    }


    /** Returns the class average (community service hours). */
    public float getAverageHours() {

        ArrayList<String> userList = getStudentList();
        float total = 0;

        if(userList.size() > 0) { //Make sure the userlist has items.

            for (String u : userList) { // get each users hours and add them together.

                total += new User(u).getTotalHours(); //Add the user's hours to the total hours.

            }

            //Return the average hours of the classroom.
            return (total / userList.size());

        }

        return 0; //No hours.

    }


    /** Creates a classroom */
    public void setClassroomData(String instructor) {

        fileTool.createFile();

        ArrayList<String> fileData = new ArrayList<String>();

        fileData.add(instructor); //Add instructor.
        fileData.add("0"); //add average hours.

        fileTool.reWriteFile(fileData); //Rewrite the classroom file with the data from the fileData list.

    }


    /**Checks to see if a classroom exists; returns true if the classroom exists.*/
    public static boolean classroomExists(String classCode) {

        String fp;

        if(os.isMac) { //Mac OS uses "/" and Windows OS uses "\" when navigating directories.

            fp = "src/Classrooms/";

        } else {

            fp = "src\\Classrooms\\";

        }

        return (new FileTool(fp + classCode + ".txt").fileExists()); //Returns true if the file exists.

    }


}
