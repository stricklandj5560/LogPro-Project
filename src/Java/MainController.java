//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainController {

    public AnchorPane mainScenePane;
    public AnchorPane helpPane;
    public AnchorPane addContactPane;
    public AnchorPane sideBarPane;
    public AnchorPane myHoursScene;
    public AnchorPane welcomeScene;
    public AnchorPane logHoursScene;
    public AnchorPane classroomScene;
    public AnchorPane noClassroomPane;
    public AnchorPane manageStudentsPane;
    public AnchorPane instructorClassroomScene;
    public AnchorPane instructorClassroomAwardScene;
    public AnchorPane adminScenePane;
    public AnchorPane adminUsersScene;
    public AnchorPane adminClassroomsScene;
    public AnchorPane adminSettingsScene;
    public AnchorPane adminSideBarPane;
    public AnchorPane adminChangePasswordScene;
    public AnchorPane adminBackupSettingsPane;
    public AnchorPane helpSearchResultPane;
    public AnchorPane helpResultFullViewPane;

    public AnchorPane mainInstructorPane;
    public AnchorPane instructorWelcomeScene;
    public AnchorPane instructorSidePane;

    public ListView studentLoggedHoursListView;
    public ListView manageStudentsStudentListView;
    public ListView manageStudentsHoursListView;
    public ListView instructorAwardsListView;
    public ListView instructorUsersAwardsListView;
    public ListView studentAwardsEarnedListView;
    public ListView adminManageUsersUsersListView;
    public ListView adminManageClassroomListView;
    public ListView adminSelectedClassroomUserListView;
    public ListView adminSettingsListView;
    public ListView adminBackupsListView;
    public ListView instructorManageClassroomsUsersListView;
    public ListView instructorManageClassroomSelectedUserAwardsListView;
    public ListView helpSearchResultsListView;

    public DatePicker logHoursDatePicker;

    public Text welcomeErrorTxt;
    public Text logHoursErrorTxt;
    public Text logHoursContactTxt;
    public Text addContactErrorTxt;
    public Text instructorWelcomeErrorTxt;
    public Text noClassroomErrorTxt;

    public CheckBox logHoursConfirmCheckBox;

    public ComboBox welcomeGradeComboBox;
    public ComboBox adminManageUsersUserTypeComboBox;

    public TextField welcomeClassroomTxtBox;
    public TextField welcomeFirstNameTxtBox;
    public TextField welcomeLastNameTxtBox;
    public TextField logHoursActivityTxtBox;
    public TextField logHoursHoursTxtBox;
    public TextField addContactEmailAddrTxtBox;
    public TextField addContactNameTxtBox;
    public TextField addContactPhoneNumberTxtBox;
    public TextField myHoursTotalHoursTxtBox;
    public TextField myHoursActivityTxtBox;
    public TextField myHoursContactTxtBox;
    public TextField myHoursHoursEarnedTxtBox;
    public TextField classroomInstructorTxtBox;
    public TextField instructorWelcomeClassroomTxtBox;
    public TextField instructorWelcomeFirstNameTxtBox;
    public TextField instructorWelcomeLastNameTxtBox;
    public TextField noClassroomClassroomTxtBox;
    public TextField manageStudentsSelectedStudentTxtBox;
    public TextField manageStudentsTotalHoursTxtBox;
    public TextField manageStudentsContactTxtBox;
    public TextField manageStudentsHoursEarnedTxtBox;
    public TextField manageStudentsGradeTxtBox;
    public TextField instructorClassCodeTxtBox;
    public TextField instructorSelectedAwardHoursRequiredTxtBox;
    public TextField instructorCreateAwardTitleTxtBox;
    public TextField instructorCreateAwardHoursRequiredTxtBox;
    public TextField instructorAwardsSelectedStudentNameTxtBox;
    public TextField instructorAwardGradeTxtBox;
    public TextField instructorAwardHoursLoggedTxtBox;
    public TextField studentSelectedAwardHoursRequiredTxtBox;
    public TextField adminManageUsersUsersSearchTxtBox;
    public TextField adminManageUsersSelectedUserTxtBox;
    public TextField adminManageUsersFirstNameTxtBox;
    public TextField adminManageUsersLastNameTxtBox;
    public TextField adminManageUsersClassroomTxtBox;
    public TextField adminManageUsersNewPasswordTxtBox;
    public TextField adminManageUsersConfirmNewPasswordTxtBox;
    public TextField adminSelectedClassroomClassCodeTxtBox;
    public TextField adminSelectedClassroomInstructorTxtBox;
    public TextField adminChangePasswordConfirmPasswordTxtBox;
    public TextField adminChangePasswordNewPasswordTxtBox;
    public TextField adminBackupSearchTxtBox;
    public TextField helpOpenSearchTxtBox;
    public TextField helpResultFullViewQuestionTxtBox;
    public TextField helpResultSearchTxtBox;

    public TextArea logHoursExplanationTxtBox;
    public TextArea manageStudentsReflectionTxtBox;
    public TextArea instructorCreateAwardDescriptionTxtBox;
    public TextArea instructorSelectedAwardDescriptionTxtBox;
    public TextArea studentSelectedAwardDescriptionTxtBox;
    public TextArea helpResultFullViewAnswerTxtBox;

    public Button instructorClassroomSwitchBtn;
    public Button instructorAwardsSwitchBtn;

    public BarChart myAverageVrsClassAverageBarChart;
    public BarChart instructorManageClassroomBarChart;

    private static DecimalFormat df = new DecimalFormat("0.00");

    OperatingSystem os = new OperatingSystem();
    Classroom userClassroom;
    User user;
    Admin admin = new Admin();
    ObservableList<String> adminCurrentUserList;
    ArrayList<Classroom> adminClassroomList = new ArrayList<Classroom>();
    ObservableList<String> adminBackupList = FXCollections.observableArrayList();

    private String selectedBackupDate = "";

    @FXML
    public void initialize() {

        if(LoginController.isAdmin) { //if an admin is logged in display admin scene.

            //Populate the admin settings list view.
            ObservableList<String> settingsList = FXCollections.observableArrayList();
            settingsList.addAll("Backup Settings", "Change Password");

            adminSettingsListView.setItems(settingsList);
            adminSettingsListView.getSelectionModel().selectFirst(); //Select backup settings scene.

            //Append user types to the admin manage users, user type combo box.
            ObservableList<String> userTypes = FXCollections.observableArrayList();
            userTypes.addAll("All Users", "Students", "Instructors");

            adminManageUsersUserTypeComboBox.setItems(userTypes);
            adminManageUsersUserTypeComboBox.getSelectionModel().select(0); //Select first type (All Users).

            adminManageUsersBtnClick(); //Open the users menu.

            mainScenePane.setVisible(false);
            mainInstructorPane.setVisible(false);
            adminScenePane.setVisible(true);
            sideBarPane.setVisible(false);
            instructorSidePane.setVisible(false);
            adminSideBarPane.setVisible(true);

        } else { //User is logged in.

            String currentUserFilePath = "";

            if (os.isMac) { //Mac OS uses "/" and Windows OS uses "\" when navigating directories.

                currentUserFilePath = "src/CurrentUser.txt";

            } else {

                currentUserFilePath = "src\\CurrentUser.txt";

            }

            ArrayList<String> userData = (new FileTool(currentUserFilePath).getFileAsList());

            String data = userData.get(0);

            if (data.contains("*")) { //User is new, display welcome page and request more data from user.

                user = new User(data.substring(0, data.length() - 1));

                if (user.isInstructor()) { //Display the instructor welcome page.

                    displayInstructorWelcomePane();

                } else { //Display the user welcome page.

                    displayWelcomePage();

                }

            } else {

                user = new User(data);

                if (user.isInstructor()) { //If the user is an instructor, display the instructor pages.

                    mainInstructorPane.setVisible(true);
                    instructorSidePane.setVisible(true);
                    mainScenePane.setVisible(false);
                    sideBarPane.setVisible(false);

                    manageBtnClick(); //Show manage users page.

                } else {

                    mainScenePane.setVisible(true);
                    sideBarPane.setVisible(true);
                    mainInstructorPane.setVisible(false);
                    instructorSidePane.setVisible(false);

                    myHoursBtnClick(); //Display my hours page.

                }

            }

            setMyHoursDataListView();
            Classroom cr = new Classroom(user.getClassCode());

        }


    }

    /*-------------------
        HELP FUNCTIONS
      -------------------*/

    /** Opens the help menu */
    public void helpBtnClick() {

        if(LoginController.isAdmin) { //Blur admin panes

            adminScenePane.setEffect(new GaussianBlur());
            adminSideBarPane.setEffect(new GaussianBlur());

        } else if (user.isInstructor()){ //Blur instructor panes

            instructorSidePane.setEffect(new GaussianBlur());
            mainInstructorPane.setEffect(new GaussianBlur());

        } else { //Blur student panes

            mainScenePane.setEffect(new GaussianBlur());
            sideBarPane.setEffect(new GaussianBlur());

        }

        helpPane.setVisible(true);

    }

    /** Performs a help search based on the key words found in the helpOpenSearchTxtBox. */
    public void helpGoSearchBtnClick() {

        String search;

        if(helpSearchResultPane.isVisible()) { //Use helpResultSearchTxtBox

            search = helpResultSearchTxtBox.getText();

        } else { //Use helpOpenSearchTxtBox

            search = helpOpenSearchTxtBox.getText();
            helpResultSearchTxtBox.setText(search);

        }

        ArrayList<String[]> results;

        if(!(search.trim().equals(""))) { //Something was searched.

            //Check to see if an admin is logged in.
            if(!LoginController.isAdmin) { //Admin is not logged in.

                if(user.isInstructor()) { //User is instructor

                    results = HelpSearch.getSearchResults(search, "instructor"); //Get instructor results

                } else { //User is student.

                    results = HelpSearch.getSearchResults(search, "student"); //Get student results.

                }

            } else {

                results = HelpSearch.getSearchResults(search, "admin"); //Get admin results.

            }

            //Display search results.
            ObservableList<String> displayList = FXCollections.observableArrayList();

            for(String[] s : results) {

                displayList.add(s[1]); //Add title.

            }

            helpSearchResultsListView.setItems(displayList);

            helpSearchResultPane.setVisible(true);

        } else { //No key words, display all searches.

            viewAllSearchesBtnClick();

        }

    }


    /**Closes the "full-view" answer pane. */
    public void helpFullViewOkayBtnClick() {

        helpResultFullViewPane.setVisible(false);
        helpResultFullViewQuestionTxtBox.setText("");
        helpResultFullViewAnswerTxtBox.setText("");

    }

    /**Gets the selected question and opens up the full view of the question. */
    public void helpSearchResultListViewClick() {

        String selection = helpSearchResultsListView.getSelectionModel().getSelectedItem().toString();

        String body = "";
        String title = "";

        String type;

        if(selection != null) { //Make sure selection is not null.

            if (!LoginController.isAdmin) { //If admin is not logged in.

                if (user.isInstructor()) { //User is instructor type 1.

                    type = "1";

                } else { //User is student, type 0.

                    type = "0";

                }


            } else { //Admin is logged in, type is 2 (Admin)

                type = "2";

            }

            String[] questionData = null;

            //Find the selection
            ArrayList<String[]> allQuestions = HelpSearch.getListOfHelpResults();

            for (String[] s : allQuestions) { //Find the matching question.

                if (s[0].equals(type)) { //User type matches.

                    if (s[1].equals(selection)) { //Question found.

                        questionData = s;

                    }

                }
            }


            if (questionData != null) { //Make sure question data was actually found (this is for safety measures).

                title = questionData[1];
                body = questionData[2];

            }

            helpResultFullViewQuestionTxtBox.setText(title);

            helpResultFullViewAnswerTxtBox.setText(body);
            helpResultFullViewAnswerTxtBox.setWrapText(true);

            helpResultFullViewPane.setVisible(true);

            //Put spaces in the text


        }



    }

    /** Leaves the result page. */
    public void helpBackBtnClick() {

        helpSearchResultPane.setVisible(false);

        //Clear out list views and text boxes
        helpSearchResultsListView.setItems(null);
        helpResultSearchTxtBox.setText("");
        helpOpenSearchTxtBox.setText("");

    }

    /** Closes the help menu. */
    public void exitHelpBtnClick() {

        mainScenePane.setEffect(null);
        sideBarPane.setEffect(null);
        adminSideBarPane.setEffect(null);
        adminScenePane.setEffect(null);
        mainInstructorPane.setEffect(null);
        instructorSidePane.setEffect(null);

        helpPane.setVisible(false);

        //Clear out textfields and list views.
        helpResultSearchTxtBox.setText("");
        helpOpenSearchTxtBox.setText("");
        helpSearchResultsListView.setItems(null);

    }


    /** Displays a list of help menu items. */
    public void viewAllSearchesBtnClick() {

        //Display all possible searches.

        ArrayList<String[]> results;

        if(!LoginController.isAdmin) { //if the admin is not logged in.

            if (user.isInstructor()) {

                results = HelpSearch.getSearchResults("", "instructor"); //get all instructor search results.

            } else { //Display user results.

                results = HelpSearch.getSearchResults("", "student"); //Get student search results.

            }

        } else { //Display admin search results.

            results = HelpSearch.getSearchResults("", "admin"); //get admin search results.

        }

        //Convert items to an observable list.
        ObservableList<String> displayList = FXCollections.observableArrayList();

        for(String[] s : results) {

            displayList.add(s[1]); //Add question (title).

        }

        helpSearchResultsListView.setItems(displayList);
        helpSearchResultPane.setVisible(true);


    }




    /*--------------------------
        ADMINISTRATOR FUNCTIONS
      --------------------------*/

    /** Opens up the settings scene for the administrator. */
    public void adminSettingsBtnClick() {

        adminBackupList.clear();

        adminClassroomsScene.setVisible(false);
        adminUsersScene.setVisible(false);
        adminSettingsScene.setVisible(true);


        //Populate the backup files list view.
        ArrayList<String> backups = Backup.getBackupList();


        ObservableList<String> displayList = FXCollections.observableArrayList();
        //Append backups to an observable list.
        for(String s : backups) {

            displayList.add(s); //Add the folder.
            adminBackupList.add(s);

        }

        adminBackupsListView.setItems(displayList.sorted());


    }


    /** Shows the selected menu that is selected in the adminSettingsListView. */
    public void adminSettingsListViewClick() {

        selectedBackupDate = "";

        //Get selected menu.
        int selectedIndex = adminSettingsListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { //Index is selected.

            if(selectedIndex == 0) { //Display backup scene.

                adminChangePasswordScene.setVisible(false);
                adminBackupSettingsPane.setVisible(true);

            } else if(selectedIndex == 1) { //Display change password scene.

                adminBackupSettingsPane.setVisible(false);
                adminChangePasswordScene.setVisible(true);


            }

        }

    }


    /** Selects a backup from the adminBackupsListView */
    public void adminBackupsListViewClick() {

        int selectedIndex = adminBackupsListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { //Backup is selected.

            //Get selected backup
            selectedBackupDate = adminBackupsListView.getItems().get(selectedIndex).toString();

        }

    }


    /** Performs a manual backup immediately. */
    public void adminPerformManualBackupBtnClick() {

        //Prompt the user for the admin password.
        String pw = NotificationTool.inputWindow("Input", "Enter password.",
                "To manually perform a backup, please input your password (this cannot be undone!): ");

        if(pw != null) { //Password was given.

            if (new Admin().isAdminPassword(pw)) { //Password is correct, manually backup.

                //Get today's date.
                SimpleDateFormat simpledatafo = new SimpleDateFormat("MM-dd-yyyy");
                Date newDate = new Date();

                String date = simpledatafo.format(newDate); //Format date in MM-DD-YYYY format. The backup file will be named this.

                new Backup(date).performBackup(); //Backup.

                NotificationTool.notifyUser("Success!", "Backup successful!",
                        "The backup on " + date + " was successful!");

            } else { //Notify user of incorrect password.

                NotificationTool.notifyUserOfError("Error", "Unable to perform backup.",
                        "Incorrect admin password.");

            }

        }

    }


    /** Updates the items in the adminBackupsListView based on the search. */
    public void adminBackupSearchUpdate() {

        String key = adminBackupSearchTxtBox.getText();

        ObservableList<String> displayList = FXCollections.observableArrayList();

        if(key.trim().equals("")) { //Show all items.

            adminBackupsListView.setItems(adminBackupList);

        } else {

            //Loop through the backup list and look for items that contain the key.
            for(String s : adminBackupList) {

                if(s.contains(key)) {

                    //Append dates that match the key to the display list.
                    displayList.add(s);

                }

            }

            adminBackupsListView.setItems(displayList);

        }

    }


    /** Restores a backup based on the selected date. */
    public void adminRestoreFromBackupBtnClick() {

        if(selectedBackupDate.equals("")) { //Notify user that no backup date is selected.

            NotificationTool.notifyUserOfError("Error", "Backup restoration failed.",
                    "No backup date is selected.");

        } else { //Prompt the admin to perform the restoration.

            //Prompt the admin to enter their password.
            String pw = NotificationTool.inputWindow("Input", "Input Password.", "To restore the " +
                    selectedBackupDate + " backup, please enter your password: ");

            if(pw != null) { //User entered a password.

                if (new Admin().isAdminPassword(pw)) { //Password is correct, restore backup.

                    //Restore backup.
                    new Backup(selectedBackupDate).restoreBackup();

                    NotificationTool.notifyUser("Success!", "Backup restoration successful!",
                            "The program has been restored to the files from date: " + selectedBackupDate + "!");

                } else { //Incorrect password, notify user.

                    NotificationTool.notifyUserOfError("Error", "Backup restoration failed.",
                            "Incorrect admin password.");

                }

            }

        }

    }


    /** Changes the administrator password. */
    public void adminChangePasswordBtnClick() {

        String newPassword = adminChangePasswordNewPasswordTxtBox.getText().trim();
        String confirmPassword = adminChangePasswordConfirmPasswordTxtBox.getText().trim();

        if(newPassword.equals("") || confirmPassword.equals("")) {

            NotificationTool.notifyUserOfError("Error", "Password Null",
                    "The admin password cannot be null.");


        } else {

            if(!(newPassword.equals(confirmPassword))) { //Passwords do not match.

                NotificationTool.notifyUserOfError("Error", "Unable to change password.",
                        "Passwords do not match.");


            } else {

                //Get the user to provide the current admin password.
                String adminPassword = NotificationTool.inputWindow("Input",
                        "Input Password.", "To change the admin password, please input current admin password.");


                if(!(new Admin().isAdminPassword(adminPassword))) { //Incorrect password.

                    NotificationTool.notifyUserOfError("Error", "Incorrect password.",
                            "The administrative password you have entered is not correct.");

                } else {

                    new Admin().setPassword(newPassword);

                    NotificationTool.notifyUser("Success!", "Change Successful!",
                            "The admin password has successfully been changed!");

                }

            }

        }

    }


    /** Opens the manage classrooms page for the administrator. */
    public void adminClassroomsBtnClick() {

        adminClassroomList.clear();

        adminSelectedClassroomClassCodeTxtBox.setText("");
        adminSelectedClassroomInstructorTxtBox.setText("");
        adminSelectedClassroomUserListView.setItems(null);

        //Set the scene visible.
        adminUsersScene.setVisible(false);
        adminSettingsScene.setVisible(false);
        adminClassroomsScene.setVisible(true);

        String fp;

        //Populate the classrooms list view.

        if(os.isWindows) { //Mac uses "/" and Windows "\".

            fp = "src\\Classrooms\\";

        } else {

            fp = "src/Classrooms/";

        }

        ArrayList<String> classrooms = FileTool.getListOfDirectories(fp);

        ObservableList<String> displayList = FXCollections.observableArrayList();

        //populate the classroom list.
        for(String cc : classrooms) {

            adminClassroomList.add(new Classroom(cc.substring(0, (cc.length() - 4))));

        }

        if(adminClassroomList.size() != 0 || adminClassroomList != null) { //If there are classrooms.

            for (Classroom cr : adminClassroomList) {

                //FORMAT: Instructors name's classroom (CLASSROOM CODE)
                displayList.add(new User(cr.getClassroomInstructor()).getFullName() + "'s classroom (" + cr.getClassroomCode() + ")");

            }

            adminManageClassroomListView.setItems(displayList);

        }

    }


    /** Updates classroom code for the selected classroom */
    public void adminManageClassroomsUpdateClassroomBtnClick() {

        String newClassCode = adminSelectedClassroomClassCodeTxtBox.getText().trim();
        int selectedIndex = adminManageClassroomListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0 )) { //If classroom is selected.

            Classroom selectedClassroom = adminClassroomList.get(selectedIndex);

            if(!(newClassCode.trim().equals(""))) { // No classroom.

                if(!(newClassCode.equals(selectedClassroom.getClassroomCode()))) { //new class code does not match old class code.

                    //make sure classroom doesn't already exist and change class code
                    if(!(Classroom.classroomExists(newClassCode))) { //Classroom doesn't exist.

                        //Notify user of success.
                        NotificationTool.notifyUser("Success!", "Successfully updated classroom.",
                                "The classroom, " + selectedClassroom.getClassroomCode() + " to " + newClassCode + "!");

                        selectedClassroom.changeClassroomCode(newClassCode); //Update class code.

                        adminClassroomsBtnClick(); //Refresh the scene.

                    } else { //Classroom exists; throw error.

                        NotificationTool.notifyUserOfError("Error", "Unable to update!",
                                "The classroom code, " + newClassCode + ", already exists!");

                    }


                }

            }


        }


    }


    /** Deletes the selected classroom. */
    public void adminManageClassroomsRemoveClassroomBtnClick() {

        int selectedIndex = adminManageClassroomListView.getSelectionModel().getSelectedIndex(); //Get selected user index.

        if(!(selectedIndex < 0)) { //Classroom is selected if selected index is not less than 0.

            Classroom classroom = adminClassroomList.get(selectedIndex);

            //Prompt the admin to remove the classroom.
            if(NotificationTool.removeWindow("Delete?", "Delete Classroom?", "Are you sure you wish to " +
                    "delete the classroom: " + classroom.getClassroomCode() + "? This will also remove the instructor!")) {

                adminClassroomList.remove(classroom); //Remove classroom from the classroom list.

                String instructorCode = classroom.getClassroomInstructor();
                classroom.selfDestruct(); //Removes the user and deletes the classroom.

                new User(instructorCode).selfDestruct(); //Remove the instructor.

                adminClassroomsBtnClick(); //Refresh the scene.


            }

        }

    }


    /** Removes a selected user from the classroom. */
    public void adminManageRemoveSelectedUserBtnClick() {

        int selectedIndex = adminManageClassroomListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { //Classroom is selected.

            Classroom selectedClassroom = adminClassroomList.get(selectedIndex);

            int selectedUserIndex = adminSelectedClassroomUserListView.getSelectionModel().getSelectedIndex();

            if(!(selectedUserIndex < 0)) { //User is selected.

                String removeUser = selectedClassroom.getStudentList().get(selectedIndex);

                //Prompt the admin to remove the user.
                if(NotificationTool.removeWindow("Remove", "Remove User?",
                        "Are you sure you wish to remove the user " + removeUser + " from the classroom: " +
                        selectedClassroom.getClassroomCode() + "?")) {

                    //Remove selected user from classroom.
                    selectedClassroom.removeUser(removeUser);

                    //Update list view
                    adminManageClassroomsListViewClick();

                }

            }

        }

    }


    /** Appends the selected classroom's data to the appropriate list views. */
    public void adminManageClassroomsListViewClick() {

        int selectedIndex = adminManageClassroomListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { //Classroom is selected.

            Classroom selectedClassroom = adminClassroomList.get(selectedIndex);

            //populate the textfields with selected data.
            adminSelectedClassroomClassCodeTxtBox.setText(selectedClassroom.getClassroomCode());

            //FORMAT: INSTRUCTOR NAME (INSTRUCTOR USERNAME)
            adminSelectedClassroomInstructorTxtBox.setText((new User(selectedClassroom.getClassroomInstructor()).getFullName())
            + " (" + selectedClassroom.getClassroomInstructor() + ")");

            //Populate users list view.
            ArrayList<String> userList = selectedClassroom.getStudentList();

            ObservableList<String> userDisplayList = FXCollections.observableArrayList();

            for(String u : userList) {

                //FORMAT: NAME (USERNAME)
                userDisplayList.add(new User(u).getFullName() + " (" + u + ")");

            }

            adminSelectedClassroomUserListView.setItems(userDisplayList);


        } else { //Clear text boxes.

            adminSelectedClassroomUserListView.setItems(null);
            adminSelectedClassroomInstructorTxtBox.setText("");
            adminSelectedClassroomClassCodeTxtBox.setText("");

        }


    }

    /** Opens up the manage users page for the administrator. */
    public void adminManageUsersBtnClick() {

        adminManageUsersUsersSearchTxtBox.setText(""); //Clear out the admin user search.

        //Clear out selected user text fields.
        adminManageUsersSelectedUserTxtBox.setText("");
        adminManageUsersFirstNameTxtBox.setText("");
        adminManageUsersLastNameTxtBox.setText("");
        adminManageUsersClassroomTxtBox.setText("");
        adminManageUsersNewPasswordTxtBox.setText("");
        adminManageUsersConfirmNewPasswordTxtBox.setText("");

        adminClassroomsScene.setVisible(false);
        adminSettingsScene.setVisible(false);
        adminUsersScene.setVisible(true);

        //Update the user's list view.
        ArrayList<String> userList = admin.getUsersList();
        ObservableList<String> displayList = FXCollections.observableArrayList();


        //KEY: index 0: All users, index 1: Students, index 2: instructors.
        int typeIndex = adminManageUsersUserTypeComboBox.getSelectionModel().getSelectedIndex();

        for(String s : userList) { //Append the userList data to the displayList.

            if(!(s.equals("admin"))) { //Don't add the administrator to the list.

                if(typeIndex == 0) { //Display all users.

                    displayList.add(s);

                }

                if(typeIndex == 1) { //Add only students.

                    if(!(new User(s).isInstructor())) { //User is not instructor.

                        displayList.add(s);

                    }

                }

                if(typeIndex == 2) { //Add only instructors

                    if(new User(s).isInstructor()) { //User is instructor.

                        displayList.add(s);

                    }

                }

            }

        }

        adminManageUsersUsersListView.setItems(displayList.sorted());

        adminCurrentUserList = adminManageUsersUsersListView.getItems();

    }


    /** Displays the users only associated with the type selected by the adminManageUsersUserTypeComboBox. */
    public void adminManageUsersUserTypeUpdate() {

        adminManageUsersBtnClick(); //Refresh list view.

    }


    /**Gets the selected user and appends their data to the appropriate text fields. */
    public void adminManageUsersUsersListViewClick() {

        String selectedUser = null;

        if(!(adminManageUsersUsersListView.getSelectionModel().getSelectedIndex() < 0)) { //If user is selected.

            selectedUser = adminManageUsersUsersListView.getSelectionModel().getSelectedItem().toString();

        }

        if(selectedUser != null) { //User is selected.

            User u = new User(selectedUser);


            if(!(u.isMissingData())) { //User is not missing data, display all of the data.

                if(u.isInstructor()) {

                    adminManageUsersClassroomTxtBox.setEditable(false); //Can't edit an instructor's classroom.

                } else {

                    adminManageUsersClassroomTxtBox.setEditable(true);

                }

                adminManageUsersSelectedUserTxtBox.setText(u.getUserCode());

                String[] name = u.getFullName().split(" "); //Get the users name as an array.

                adminManageUsersFirstNameTxtBox.setText(name[0]); //First name

                adminManageUsersLastNameTxtBox.setText(name[1]); //last name.

                if(u.getClassCode().equals("null")) { //If the user has no classroom.

                    adminManageUsersClassroomTxtBox.setText("");

                } else {

                    adminManageUsersClassroomTxtBox.setText(u.getClassCode()); //class code

                }

            } else { //User has incomplete data.

                adminManageUsersSelectedUserTxtBox.setText(u.getUserCode());

            }

        } else { //Clear out text boxes.

            adminManageUsersSelectedUserTxtBox.setText("");
            adminManageUsersFirstNameTxtBox.setText("");
            adminManageUsersLastNameTxtBox.setText("");
            adminManageUsersClassroomTxtBox.setText("");

        }

    }


    /** Removes a selected user. */
    public void adminManageUsersRemoveUserBtnClick() {

        String selectedUser = adminManageUsersSelectedUserTxtBox.getText();

        if(!selectedUser.trim().equals("")) { //User is selected.


            User u = new User(selectedUser);

            if (u.isMissingData()) { //Directly request to delete user.

                //Prompt the admin to delete the user.
                if (NotificationTool.removeWindow("Remove?", "Remove User",
                        "Are you sure you wish to delete the selected user? This can not be undone!")) {


                    u.selfDestruct();


                }

            } else { //User is normal user.

                if (u.isInstructor()) { //Notify the admin they are removing an instructor.

                    if (NotificationTool.removeWindow("Remove?", "Remove Instructor",
                            "If you remove an instructor, their classroom will be removed as well. This can not be undone!")) {


                        if (!(u.getClassCode().equals("null"))) { //Check to see if the instructor is associated with a classroom.

                            //Delete classroom.
                            u.getClassroom().selfDestruct();

                        }

                        //Remove instructor.
                        u.selfDestruct();

                    }

                } else {

                    if (!(u.getClassCode().equals("null"))) { //If user has joined a classroom, remove them.

                        //Prompt the user to remove the selected user.
                        if (NotificationTool.removeWindow("Remove", "Remove Student",
                                "Are you sure you want to remove this user, this can not be undone.")) {

                            u.getClassroom().removeUser(u.getUserCode());
                            u.selfDestruct();

                        }


                    } else { //User hasn't joined a classroom.


                        //Prompt the user to remove the selected user.
                        if (NotificationTool.removeWindow("Remove", "Remove Student",
                                "Are you sure you want to remove this user, this can not be undone.")) {

                            u.selfDestruct();

                        }

                    }

                }

            }

        }

        adminManageUsersBtnClick(); //Refresh list view.

    }


    /** Updates the selected user's data. */
    public void adminManageUsersUpdateUserBtnClick() {

        String newFirstName = adminManageUsersFirstNameTxtBox.getText().trim();
        String newLastName = adminManageUsersLastNameTxtBox.getText().trim();
        String newClassroom = adminManageUsersClassroomTxtBox.getText().trim();
        String newPassword = adminManageUsersNewPasswordTxtBox.getText().trim();
        String confirmNewPassword = adminManageUsersConfirmNewPasswordTxtBox.getText().trim();

        String oldFirstName;
        String oldLastName;
        String oldClassroom;

        if(!(adminManageUsersSelectedUserTxtBox.getText().trim().equals(""))) { //User is selected.

            boolean goForLaunch = true;
            boolean nameChange = false;
            boolean classroomChange = false;
            boolean changePassword = false;

            boolean anythingChanged = false;

            String reason = "";

            User u = new User(adminManageUsersSelectedUserTxtBox.getText().trim());

            if(u.isMissingData()) { //User is missing data, unable to update.

                NotificationTool.notifyUserOfError("Error", "Unable to perform update.",
                        "The selected user has not finished creating their account, so there is nothing to update.");

            } else {

                //Update user's data.
                oldFirstName = u.getFullName().split(" ")[0]; //Index 0 is first name

                oldLastName = u.getFullName().split(" ")[1]; //Index 1 is last name.

                oldClassroom = u.getClassCode();



                //Check to see if data has changed.

                if(!(newFirstName.equals("") || newLastName.equals(""))) {

                    if (!(oldFirstName.equals(newFirstName))) {

                        nameChange = true;
                        anythingChanged = true;

                    }

                    if (!(oldLastName.equals(newLastName))) {

                        nameChange = true;
                        anythingChanged = true;

                    }

                } else {

                    goForLaunch = false;
                    reason = "Name fields can not be blank.";
                    anythingChanged = true;

                }

                //if the new classroom is empty, set it to "null".
                if(newClassroom.trim().equals("") || adminManageUsersClassroomTxtBox.getText().isEmpty()) {

                    newClassroom = "null";

                }

                if (!(oldClassroom.equals(newClassroom))) { //Old classroom is not the same as the new classroom.

                    anythingChanged = true;

                    if (Classroom.classroomExists(newClassroom)) {

                        classroomChange = true;

                    } else {

                        goForLaunch = false;
                        reason = "The classroom, " + newClassroom + ", does not exist.";

                    }


                }

                //Check to see if the user is updating the password.
                if (!(newPassword.equals(""))) {

                    anythingChanged = true;

                    if (newPassword.equals(confirmNewPassword)) {

                        //Check to see if the new password is the same as the old password.
                        if (!(u.checkPassword(new Encrypter().encryptText(newPassword)))) {

                            changePassword = true;

                        }

                    } else {

                        goForLaunch = false;
                        reason = "New passwords do not match.";

                    }

                }

                if(!anythingChanged) { //Nothing has changed, don't update.

                    goForLaunch = false;

                }

                //Make changes if applicable.
                if (goForLaunch) {

                    if (nameChange) { //Change user's name.

                        u.setFullName(newFirstName, newLastName);

                    }

                    if (classroomChange) { //Remove the user from the old classroom if they had one.

                        if (!(u.getClassroom().equals("null"))) { //User had a classroom, remove them from the classroom.

                            u.getClassroom().removeUser(u.getUserCode()); //Get the user's classroom and remove the user.

                        }

                        u.setClassroom(newClassroom);
                        new Classroom(newClassroom).addUser(u.getUserCode());

                    }

                    if (changePassword) {

                        u.setPassword(new Encrypter().encryptText(newPassword)); //Set the user's password.

                    }

                    //Clear out data.
                    NotificationTool.notifyUser("Success!", "Update Successful!",
                            "You have successfully updated " + u.getUserCode() + "'s data.");

                    adminManageUsersNewPasswordTxtBox.setText("");
                    adminManageUsersConfirmNewPasswordTxtBox.setText("");




                } else { //Notify user of error.

                    if(anythingChanged) { //Something has been changed.

                        NotificationTool.notifyUserOfError("Error.", "Failed To Update.", reason);

                    }


                }

            }

        }

    }


    /** Key is typed in the search users txt box, update the search. */
    public void adminManageUsersUpdateSearch () {

        ObservableList<String> updateSearchList = FXCollections.observableArrayList();

        for(String s : adminCurrentUserList) { //Loop through the items and see if they contain the search text.

            //If text equals search, append user.
            if(s.toLowerCase().contains(adminManageUsersUsersSearchTxtBox.getText().trim().toLowerCase())) {

                updateSearchList.add(s);

            }

        }

        adminManageUsersUsersListView.setItems(updateSearchList);

    }




    /*-----------------------
        INSTRUCTOR FUNCTIONS
      -----------------------*/

    /**Submits the welcome information for the instructor */
    public void instructorWelcomeDoneBtnClick() {

        boolean goForLaunch = true;
        String reason = "";

        String firstName = instructorWelcomeFirstNameTxtBox.getText().trim();
        String lastName = instructorWelcomeLastNameTxtBox.getText().trim();
        String classCode = instructorWelcomeClassroomTxtBox.getText().trim();

        if(firstName.equals("") || lastName.equals("") || classCode.equals("")) { //Check to see if any blanks are empty.

            goForLaunch = false;
            reason = "There are empty fields remaining.";

        } else if (Classroom.classroomExists(classCode)) { //check to see if the class code is already in use.

            goForLaunch = false;
            reason = "The class code " + classCode + " is already in use.";

        }

        //Check to see if any of the fields contain the illegal character "*".
        if (firstName.contains("*") || lastName.contains("*") || classCode.contains("*")) {

            goForLaunch = false;
            reason = "The illegal character \"*\" is not permitted.";

        }


        if(goForLaunch) { //Append data to the user and close the welcome pane.

            instructorWelcomeErrorTxt.setText("");
            user.setWelcomeData(firstName, lastName, classCode, -1);
            userClassroom = new Classroom(classCode);

            userClassroom.setClassroomData(user.getUserCode());

            instructorWelcomeScene.setVisible(false);
            mainInstructorPane.setEffect(null);
            instructorSidePane.setEffect(null);

        } else {

            instructorWelcomeErrorTxt.setText(reason);

        }

    }


    /** Displays the instructor welcome pane */
    public void displayInstructorWelcomePane() {

        mainScenePane.setVisible(false);

        instructorSidePane.setVisible(true);
        mainInstructorPane.setVisible(true);

        instructorSidePane.setEffect(new GaussianBlur());
        mainInstructorPane.setEffect(new GaussianBlur());

        instructorWelcomeScene.setVisible(true);

        manageBtnClick(); //Display manage student scene.

    }


    /**Generates a class code for the instructor*/
    public void instructorWelcomeGenerateClassCodeBtnClick() {

        instructorWelcomeClassroomTxtBox.setText(generateClassCode());

    }


    /** Recursive function that returns a random class code */
    public String generateClassCode() {

        Random rand = new Random();

        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"
                , "T", "U", "V", "W", "X", "Y", "Z"};

        String code = "";

        int random = 6; //Set length of 6.
        int numberat = rand.nextInt(random); //Generate a random number between 0-random

        for (int i = 0; i < random; i++) {

            if (i == numberat) {

                code += rand.nextInt(9); //Add a random number in the position "numberat"

            } else {

                int number = rand.nextInt(25);

                code += alphabet[number]; //Add a random letter from the array "alphabet"

            }

        }

        if (Classroom.classroomExists(code)) { //Check to see if the class code already exists. If so, generate another random code.

            generateClassCode();

        }

        return code;

    }


    /**Opens the manage page for the instructor. */
    public void manageBtnClick() {

        refreshManageStudentListView();
        instructorClassroomScene.setVisible(false);
        manageStudentsPane.setVisible(true);

    }

    /**Populates the manage students student list view. */
    public void refreshManageStudentListView() {

        ArrayList<String> studentList = user.getClassroom().getStudentList(); //Get a list of the classroom's students.

        ObservableList<String> displayList = FXCollections.observableArrayList();

        if(studentList.size() == 0) { //No users added to classroom.

            System.out.println("No users in classroom");

        } else {

            for (String student : studentList) { //Loop through the students.

                //Append user in format: userID (FirstName LastName)
                displayList.add(student + " (" + new User(student).getFullName() + ")");

            }

        }

        manageStudentsStudentListView.setItems(displayList);

    }


    /** Gets the selected user data from the manageStudentsListView. */
    public void manageStudentsListViewClick() {

        manageStudentsReflectionTxtBox.setText("");
        manageStudentsHoursEarnedTxtBox.setText("");
        manageStudentsContactTxtBox.setText("");
        manageStudentsHoursListView.setItems(null);

        int index = manageStudentsStudentListView.getSelectionModel().getSelectedIndex(); //Selected index.

        if(!(index < 0)) { //if an index is selected get that user's data.

            String u = user.getClassroom().getStudentList().get(index);

            User us = new User(u);

            //Set the manageStudentSelectedStudentTxtBox's text.
            manageStudentsSelectedStudentTxtBox.setText(manageStudentsStudentListView.getItems().get(index).toString());

            //Set the user's total hours data.
            manageStudentsTotalHoursTxtBox.setText(String.valueOf(us.getTotalHours()));

            //Set the user's grade.
            manageStudentsGradeTxtBox.setText(us.getGrade());


        } else { //No user is selected.

            manageStudentsSelectedStudentTxtBox.setText("");
            manageStudentsTotalHoursTxtBox.setText("");

        }

    }


    /** Appends data to the logged hours list view. */
    public void manageStudentsViewDetailedReportBtnClick() {

        manageStudentsReflectionTxtBox.setText("");
        manageStudentsHoursEarnedTxtBox.setText("");
        manageStudentsContactTxtBox.setText("");

        int index = manageStudentsStudentListView.getSelectionModel().getSelectedIndex(); //Selected index.

        String approved = "";

        if(!(index < 0)) { //User is selected.

            String u = user.getClassroom().getStudentList().get(index);

            User us = new User(u);

            ArrayList<String[]> userHoursData = us.getHourDataList(); //Get a list of logged hours for the user.


            if(userHoursData != null) { //If the data is not empty.

                ObservableList<String> displayList = FXCollections.observableArrayList();


                for (String[] s : userHoursData) {

                    approved = "";

                    //Check to see if the hours have been approved.
                    if(s.length > 6) { //Selected hours have been approved.

                        approved = "(APR)";

                    } else {

                        approved = "(NA)";

                    }

                    //Format: ACTIVITY on DATE
                    displayList.add(approved + " " + s[1] + " on " + s[2]);

                }

                manageStudentsHoursListView.setItems(displayList);

            } else { //Alert the user that there is no hours data.

                NotificationTool.notifyUser("Attention", "No Logged Hours", "The selected user " +
                        "has no hours logged.");

            }

        } else { //No user is selected.

        }

    }


    /**Gets data from the selected user's logged hours. */
    public void manageStudentsHoursListClick() {

        int index = manageStudentsHoursListView.getSelectionModel().getSelectedIndex();
        int studentIndex = manageStudentsStudentListView.getSelectionModel().getSelectedIndex();

        if(!(index < 0) && !(studentIndex < 0)) { //Index is selected.

            //Get the selected students logged hours.
            ArrayList<String[]> userLoggedHours = (new User(user.getClassroom().getStudentList().get(studentIndex)).getHourDataList());

            String [] hourData = userLoggedHours.get(index);

            manageStudentsHoursEarnedTxtBox.setText(hourData[0]); //Hours earned for activity.

            manageStudentsContactTxtBox.setText(hourData[3] + " - Email: " + hourData[4]); //Contact

            manageStudentsReflectionTxtBox.setWrapText(true); //Make the text wrap.
            manageStudentsReflectionTxtBox.setText(hourData[5]); //Reflection

        } else { //clear fields; nothing is selected

            manageStudentsReflectionTxtBox.setText("");
            manageStudentsHoursEarnedTxtBox.setText("");
            manageStudentsContactTxtBox.setText("");

        }


    }


    /** Approves the user's hours */
    public void manageStudentsApproveBtnClick() {

        int studentIndex = manageStudentsStudentListView.getSelectionModel().getSelectedIndex();
        int index = manageStudentsHoursListView.getSelectionModel().getSelectedIndex();

        if(!manageStudentsContactTxtBox.getText().trim().equals("")) { //User is selected.

            //Approve hours for selected student.
            new User(user.getClassroom().getStudentList().get(studentIndex)).approveLoggedHours(index);
            manageStudentsViewDetailedReportBtnClick(); //Refresh the list view.

        }

    }

    /** Removes specified hours from the user. */
    public void manageStudentsRemoveHoursBtnClick() {

        int index = manageStudentsHoursListView.getSelectionModel().getSelectedIndex();
        int userIndex = manageStudentsStudentListView.getSelectionModel().getSelectedIndex();

        if(!manageStudentsContactTxtBox.getText().trim().equals("")) { //User is selected.

            //Prompt the user to remove the hours.
            if (NotificationTool.removeWindow("Remove", "Remove Hours?", "Are you sure you want to remove " +
                    "the activity: " + manageStudentsHoursListView.getItems().get(index).toString() + "?")) {

                //Get the selected user and remove the selected hours.
               new User(user.getClassroom().getStudentList().get(userIndex)).removeHours(index);


                //Check to see if the user has any hours left.
                if(new User(user.getClassroom().getStudentList().get(userIndex)).getHourDataList() == null) {

                    manageStudentsHoursListView.setItems(null);

                }

                //Refresh the list view.
                manageStudentsViewDetailedReportBtnClick();


            }

        }

    }


    /** Open the classroom scene. */
    public void instructorClassroomBtnClick() {

        manageStudentsPane.setVisible(false);
        instructorClassroomScene.setVisible(true);

        instructorClassCodeTxtBox.setText(user.getClassroom().getClassroomCode());

        //Populate the users list view.
        ArrayList<String> userList = user.getClassroom().getStudentList();
        ObservableList<String> displayList = FXCollections.observableArrayList();

        for(String s : userList) {

            displayList.add(s);

        }

        instructorManageClassroomsUsersListView.setItems(displayList.sorted());


    }


    /** Switches to the instructor classroom scene. */
    public void instructorClassroomSwitchBtnClick() {

        //Swap button colors.
        instructorClassroomSwitchBtn.setStyle("-fx-background-color:  #A1C7C8");
        instructorAwardsSwitchBtn.setStyle("-fx-background-color: transparent");

        instructorClassroomAwardScene.setVisible(false); //Hide the Award Scene

    }


    /** Appends selected user's data to the awards list view and the classroom compare bar chart. */
    public void instructorManageClassroomsUsersListViewClick() {

        //Check to see if a user is selected.
        if(!(instructorManageClassroomsUsersListView.getSelectionModel().getSelectedIndex() < 0)) {

            //Get the selected student.
            String uCode = instructorManageClassroomsUsersListView.getSelectionModel().getSelectedItem().toString();

            //List to be displayed in earned awards ListView.
            ObservableList<String> awardsDisplayList = FXCollections.observableArrayList();

            if (uCode != null) { //User is selected.

                for (String award[] : user.getClassroom().getStudentAwards(uCode)) { //Get awards earned by the user.

                    awardsDisplayList.add(award[0]); //Add award title.

                }

                instructorManageClassroomSelectedUserAwardsListView.setItems(awardsDisplayList.sorted());

                float userHours = new User(uCode).getTotalHours(); //Get total hours of user.
                float classAvg = user.getClassroom().getAverageHours(); //Get class average.

                XYChart.Series series1 = new XYChart.Series();
                series1.setName("User Hours vs. Classroom Hours");

                series1.getData().add(new XYChart.Data("User Average", userHours));
                series1.getData().add(new XYChart.Data("Class Average", classAvg));

                instructorManageClassroomBarChart.getData().setAll(series1);


            } else { //clear out list view.

                instructorManageClassroomSelectedUserAwardsListView.setItems(null);
                instructorManageClassroomBarChart.getData().clear();

            }

        }

    }


    /** Switches to the instructor awards page. */
    public void instructorAwardsSwitchBtnClick() {

        instructorSelectedAwardDescriptionTxtBox.setText("");
        instructorSelectedAwardHoursRequiredTxtBox.setText("");
        instructorUsersAwardsListView.setItems(null);
        instructorAwardsSelectedStudentNameTxtBox.setText("");
        instructorAwardHoursLoggedTxtBox.setText("");
        instructorAwardGradeTxtBox.setText("");

        //Swap button colors.
        instructorAwardsSwitchBtn.setStyle("-fx-background-color:  #A1C7C8");
        instructorClassroomSwitchBtn.setStyle("-fx-background-color: transparent");

        instructorClassroomAwardScene.setVisible(true); //Show the Award Scene

        //Populate the Awards list view.
        if(user.getClassroom().getAwards() != null) { //Awards exist.

            ObservableList<String> displayList = FXCollections.observableArrayList();

            ArrayList<String[]> awardList = user.getClassroom().getAwards(); //Get a list of awards.

            for(String [] s : awardList) {

                displayList.add(s[0]); //Add title of award to display list.

            }

            instructorAwardsListView.setItems(displayList);

        } else {

            instructorAwardsListView.setItems(null);

        }


    }


    /** Creates an award. */
    public void instructorCreateAwardBtnClick() {

        String title = instructorCreateAwardTitleTxtBox.getText();
        String requiredHours = instructorCreateAwardHoursRequiredTxtBox.getText().trim();
        String description = instructorCreateAwardDescriptionTxtBox.getText();

        boolean goForLaunch = true;
        String reason = "";

        //Check for empty fields.
        if(title.trim().equals("") || requiredHours.equals("") || description.trim().equals("")) {

            goForLaunch = false;
            reason = "There are empty fields remaining.";

        }

        try { Integer.parseInt(requiredHours); } catch (Exception e) { //Not a number.

            goForLaunch = false;
            reason = "Please only input an integer for the hours field.";

        }

        if(goForLaunch) { //No errors, create award.

            user.getClassroom().createAward(title, requiredHours, description); //Create the award and add it to classroom file.

            //Alert the user of success and clear out text boxes.

            NotificationTool.notifyUser("Success!", "Award Created", "You have successfully created" +
                    " the award: " + title + "!");

            instructorCreateAwardTitleTxtBox.setText("");
            instructorCreateAwardHoursRequiredTxtBox.setText("");
            instructorCreateAwardDescriptionTxtBox.setText("");

            instructorAwardsSwitchBtnClick(); //Refresh page.


        } else { //Notify user of errors.

            NotificationTool.notifyUserOfError("Error", "Something is not right!", reason);

        }

    }


    /**Gets the selected award in the instructor award list view and searches for the users who have earned the award. */
    public void instructorAwardsListViewClick() {

        int index = instructorAwardsListView.getSelectionModel().getSelectedIndex();

        if(!(index < 0)) { //Award is selected.

            //Get award selected.
            String[] award = user.getClassroom().getAwards().get(index);

            //Set the award text fields.
            instructorSelectedAwardHoursRequiredTxtBox.setText(award[1]);
            instructorSelectedAwardDescriptionTxtBox.setText(award[2]);


            //Get the users who have completed the selected award.
            ArrayList<User> completedUsers = user.getClassroom().getUsersWhoHaveCompletedAward(index);

            //Display users who have completed award.
            ObservableList<String> displayList = FXCollections.observableArrayList();

            for(User u : completedUsers) {

                //Format: FULL NAME (USERNAME)
                displayList.add(u.getFullName() + " (" + u.getUserCode() + ")");

            }

            instructorUsersAwardsListView.setItems(displayList);

        }


    }


    /** Appends the selected user's data from the instructorUsersAwardsListView. */
    public void usersAwardEarnedListViewClick() {

        int selectedIndex = instructorUsersAwardsListView.getSelectionModel().getSelectedIndex();

        int awardIndex = instructorAwardsListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { //User is selected.

            //Get a list of students who have completed the award.
            ArrayList<User> uList = user.getClassroom().getUsersWhoHaveCompletedAward(awardIndex);

            User u = uList.get(selectedIndex);

            //Append users data to the selected appropriate text boxes.
            instructorAwardsSelectedStudentNameTxtBox.setText(u.getFullName() + " (" + u.getUserCode() + ")");
            instructorAwardHoursLoggedTxtBox.setText(String.valueOf(u.getTotalHours()));
            instructorAwardGradeTxtBox.setText(u.getGrade());

        }

    }

    /**Removes a specified award*/
    public void instructorRemoveAwardBtnClick() {

        int awardIndex = instructorAwardsListView.getSelectionModel().getSelectedIndex();

        if(!(awardIndex < 0)) { //Check to see if an award is selected.

            //Prompt the user to remove the hours.
           if( NotificationTool.removeWindow("Remove", "Remove Award?",
                    "Are you sure you want to remove the award, " + instructorAwardsListView.getItems()
                            .get(awardIndex).toString() + "?")) {

                user.getClassroom().removeAward(awardIndex);

                instructorAwardsSwitchBtnClick(); //reset list views.

           }

        }

    }


    /**Updates award data. */
    public void instructorUpdateAwardBtnClick() {

        int awardIndex = instructorAwardsListView.getSelectionModel().getSelectedIndex();

        String requiredHours = instructorSelectedAwardHoursRequiredTxtBox.getText();
        String description = instructorSelectedAwardDescriptionTxtBox.getText();
        String title = "";

        boolean goForLaunch = true;
        String reason = "";

        if(!(awardIndex < 0)) {

            title = instructorAwardsListView.getItems().get(awardIndex).toString(); //Get title from award list view.

            //Check to see if the awards are the same.
            String[] awardData = user.getClassroom().getAwards().get(awardIndex);

            if(awardData[1].trim().equals(requiredHours.trim()) && awardData[2].trim().equals(description.trim())) {

                //Data is the same. Avoid update.
                NotificationTool.notifyUserOfError("Uh-oh", "Nothing to update!", "There is no new" +
                        " data to update!");

            } else {


                //Test to see if the required hours are an integer.
                try {
                    Integer.parseInt(requiredHours);
                } catch (Exception e) {

                    goForLaunch = false;
                    reason = "Please only input an integer for hours required.";

                }

                //Check for empty fields.
                if (requiredHours.trim().equals("") || description.trim().equals("")) {

                    goForLaunch = false;
                    reason = "There are empty fields remaining.";

                }

                if (goForLaunch) { //Update the award data.

                    user.getClassroom().updateAward(awardIndex, title, requiredHours, description);

                    NotificationTool.notifyUser("Success!", "Update completed!", "You have successfully "
                            + "updated the award: " + title + "!");

                } else { //Notify the user of the error.

                    NotificationTool.notifyUserOfError("Uh-oh!", "Unable to complete update!", reason);

                }

            }

        } else { //Notify the user that no award is selected.

            NotificationTool.notifyUserOfError("Uh-oh!", "Unable to complete update!", "No awards selected!");

        }


    }



    /*--------------------
        STUDENT FUNCTIONS
      --------------------*/




    /** Close Join Classroom pane */
    public void noClassroomCancelBtnClick() {

        noClassroomPane.setVisible(false);

        mainScenePane.setEffect(null);
        sideBarPane.setEffect(null);

        myHoursBtnClick();

    }


    /** Opens the classroom scene */
    public void classroomBtnClick() {

        myAverageVrsClassAverageBarChart.getData().clear();

        classroomScene.setVisible(true);
        myHoursScene.setVisible(false);
        logHoursScene.setVisible(false);

        String cm = user.getClassCode();

        if(cm.equals("null")) { //User does not have a classroom, prompt the join classroom pane.

            noClassroomPane.setVisible(true);
            mainScenePane.setEffect(new GaussianBlur());
            sideBarPane.setEffect(new GaussianBlur());

        } else {

            //Set data.
            classroomInstructorTxtBox.setText(
                    new User(user.getClassroom().getClassroomInstructor()).getFullName()); //Get the instructor's full name.

            ObservableList<String> displayList = FXCollections.observableArrayList(); //Display list for awards earned list view.

            ArrayList<String[]> userEarnedAwards = user.getClassroom().getStudentAwards(user.getUserCode());

            for(String [] s : userEarnedAwards) { //Append the display name to the display list.

                displayList.add(s[0]); //Add award title.

            }


            //Set the compare bar chart data.
            float userHours = user.getTotalHours();

            float classroomAvg = user.getClassroom().getAverageHours(); //Get the average hours of the classroom.

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("My Hours vs. Classroom Hours");

            series1.getData().add(new XYChart.Data("My Average", userHours));
            series1.getData().add(new XYChart.Data("Class Average", classroomAvg));


            myAverageVrsClassAverageBarChart.getData().setAll(series1);

            studentAwardsEarnedListView.setItems(displayList);

        }

    }

    /** Gets the award data based on the selected award in the student awards earned list view. */
    public void studentAwardsEarnedListViewClick() {

        int selectedIndex = studentAwardsEarnedListView.getSelectionModel().getSelectedIndex();

        if(!(selectedIndex < 0)) { // if an award is selected.

            String[] award = user.getClassroom().getStudentAwards(user.getUserCode()).get(selectedIndex);

            //Append award data.
            studentSelectedAwardHoursRequiredTxtBox.setText(award[1]); //hours required
            studentSelectedAwardDescriptionTxtBox.setText(award[2]);  //Award description

        }

    }


    /** Joins a classroom for the user */
    public void noClassroomJoinClassroomBtnClick() {

        String classCode = noClassroomClassroomTxtBox.getText().trim();
        boolean goForLaunch = true;
        String reason = "";

        if(classCode.equals("")) {

            goForLaunch = false;
            reason = "No class code inputted.";

        }

        if(!Classroom.classroomExists(classCode)) { //If the classroom doesn't exists, throw an error.

            goForLaunch = false;
            reason = "That classroom does not exist!";

        }

        if(goForLaunch) {

            noClassroomErrorTxt.setText("");

            user.joinClassroom(classCode); //Add the user to the classroom.

            mainScenePane.setEffect(null);
            sideBarPane.setEffect(null);
            noClassroomPane.setVisible(false);

            classroomBtnClick(); //Refresh the classroom page.

        } else {

            noClassroomErrorTxt.setText(reason);

        }

    }


    /** Sets the My Hours data list view */
    public void setMyHoursDataListView() {

        ObservableList<String> displayList = FXCollections.observableArrayList();

        myHoursTotalHoursTxtBox.setText(String.valueOf(user.getTotalHours()));

        ArrayList<String[]> dataList = user.getHourDataList();

        if(dataList != null) {

            for(String[] list : dataList) {

                displayList.add(list[1] + " on " + list[2]); //Format: Activity on DATE

            }

        }

        studentLoggedHoursListView.setItems(displayList);

    }


    /** Gets the index selected in the Logged Hours List View and appends the data to the matching fields. */
    public void myHoursListViewClick() {

        int index = studentLoggedHoursListView.getSelectionModel().getSelectedIndex();


        if(!(index < 0)) {

            String [] data = user.getHourDataList().get(index);
            myHoursHoursEarnedTxtBox.setText(data[0]); //Set hours.
            myHoursActivityTxtBox.setText(data[1]);
            myHoursContactTxtBox.setText(data[3]);

        }

    }


    /** Opens the log hours scene */
    public void logHoursBtnClick() {

        logHoursScene.setVisible(true);
        myHoursScene.setVisible(false);
        classroomScene.setVisible(false);

    }


    /** Submits logged hours */
    public void logHoursSubmitBtnClick() {

        logHoursErrorTxt.setText("");

        String reason = "";
        boolean goForLaunch = true;
        boolean formatCorrect = true;
        LocalDate date = null;
        String hm = logHoursHoursTxtBox.getText().trim(); //Hours and minutes format HH:MM


        if(!logHoursConfirmCheckBox.isSelected()) { //Confirm check box not checked.

            goForLaunch = false;
            reason = "Please confirm your hours and select the check box below.";

        }

        //Check to see if any blanks are empty.
        if (logHoursExplanationTxtBox.getText().trim().equals("") ||
                logHoursActivityTxtBox.getText().trim().equals("") ||
                logHoursHoursTxtBox.getText().trim().equals("")
        ) {

            goForLaunch = false;
            reason = "There are empty fields remaining.";

        }

        if(!hm.contains(":") || hm.contains("-")) { //incorrect format, must format in HH:MM

            goForLaunch = false;
            formatCorrect = false;
            reason = "Incorrect time format, please format hours in HH:MM format.";

        }

        int h = 0;//Hours
        int m = 0; //Minutes

        String [] hoursMinutes = {""};

        if(formatCorrect) { //If data is in the correct format, split the hours and minutes using regex ":"

            hoursMinutes = hm.split(":");

        }

        try {

            if(hoursMinutes.length <= 1) { //Format is incorrect

                goForLaunch = false;
                reason = "Incorrect time format, please format hours in HH:MM format.";

            } else {

                h = Integer.parseInt(hoursMinutes[0]);
                m = Integer.parseInt(hoursMinutes[1]);

                if (h == 0 && m == 0) { //no hours and no minutes were logged.

                    goForLaunch = false;
                    reason = "You must log at least 1 minute of service.";

                }

                if (m >= 60) {

                    goForLaunch = false;
                    reason = "Incorrect time format, please format hours in HH:MM format.";

                }

            }

        } catch (Exception e) {

            goForLaunch = false;
            reason = "Incorrect time format, please format hours in HH:MM format.";

        }

        if(logHoursContactTxt.getText().trim().equals("")) {

            goForLaunch = false;
            reason = "Contact information not attached.";

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); //Format date in MM
        date = logHoursDatePicker.getValue();


        if(date == null) {

            goForLaunch = false;
            reason = "Date not provided.";

        }

        //Check to see if any of the blanks contain the illegal character "*".
        if(logHoursActivityTxtBox.getText().contains("*") ||
            logHoursExplanationTxtBox.getText().contains("*")) {

            goForLaunch = false;
            reason = "You may not use the illegal character \"*\" when logging your hours.";

        }

        if(goForLaunch) { //No errors, submit hours.

            String email = "";
            String phoneNumber; //Note: phone number support has been removed.
            String contactName;
            String activity = logHoursActivityTxtBox.getText();
            String explanation = logHoursExplanationTxtBox.getText();
            String dateData = formatter.format(date);



            if(!addContactPhoneNumberTxtBox.getText().trim().equals("")) { //Use phone number for contact



            } else { //Use email

                email = addContactEmailAddrTxtBox.getText();

            }

            contactName = logHoursContactTxt.getText();

            String contactData = contactName + "*" + email;

            user.logHours((hoursAndMinutesToRatio(h, m)), activity, dateData, contactData, explanation); //Log hours

            //Notify user that their hours have been logged.
            NotificationTool.notifyUser("Success!", "Logged Hours",
                    "Your hours have been successfully submitted!");

            //Clear out text boxes.
            logHoursActivityTxtBox.setText("");
            logHoursExplanationTxtBox.setText("");
            logHoursErrorTxt.setText("");
            logHoursDatePicker.valueProperty().setValue(null);
            logHoursContactTxt.setText("");
            logHoursHoursTxtBox.setText("");
            addContactEmailAddrTxtBox.setText("");
            addContactNameTxtBox.setText("");
            logHoursConfirmCheckBox.setSelected(false); //Deselect confirm check box.

        } else {

            logHoursErrorTxt.setText(reason);

        }

    }


    /** Converts hours and minutes to a fraction of hours. */
    public String hoursAndMinutesToRatio(int hours, int minutes) {
        
        float returnNum = hours;

        float m = minutes;

        df.setRoundingMode(RoundingMode.UP); //Round to two decimal places.

        if(minutes > 0) {

            m = ((float) minutes / 60); //Get ratio of minutes:hours. Ex: 30m -> .5 hrs.


        }

        returnNum += m; //Add the mintues to the hours.

        return df.format(returnNum);

    }


    /** Creates a prompt to attach a contact to your hour log form */
    public void logHoursAddContactBtnClick() {

        addContactPane.setVisible(true);
        mainScenePane.setEffect(new GaussianBlur());
        sideBarPane.setEffect(new GaussianBlur());

    }


    /**Submits contact information */
    public void addContactSubmitBtnClick() {

        String reason = "";
        boolean goForLaunch = true;
        boolean phoneNumberProvided = false;

        String phoneNumber = addContactPhoneNumberTxtBox.getText().trim(); //NOTE: phone number support has been removed,
                                                                            //Phone number FXML is hidden in case of return.
        String email = addContactEmailAddrTxtBox.getText().trim();
        String name = addContactNameTxtBox.getText();

        //Check to see if there's an error with the information provided.

        if(!phoneNumber.equals("")) {

            phoneNumberProvided = true;

        }

        if(email.equals("") && !phoneNumberProvided) { //Either a phone number or an email need to be provided.

            goForLaunch = false;
            reason = "An email must be provided.";

        } else if (!email.contains("@") && !phoneNumberProvided) { //Email address provided is invalid.

            goForLaunch = false;
            reason = "Email address is invalid.";

        }

        if(name.trim().equals("")) { //Name not provided.

            goForLaunch = false;
            reason = "You must provide a name for you contact.";

        }

        //Check for illegal character "*"
        if(email.contains("*") || name.contains("*")) {

            goForLaunch = false;
            reason = "You may not use the illegal character \"*\" in your contact data.";

        }

        if(goForLaunch) {

            addContactPane.setVisible(false);
            mainScenePane.setEffect(null);
            sideBarPane.setEffect(null);

            addContactErrorTxt.setText("");

            logHoursContactTxt.setText(name);

        } else {

            addContactErrorTxt.setText(reason);

        }


    }


    /** Closes the add contact menu and clears the Add Contact Text Fields. */
    public void addContactCancelBtnClick() {

        addContactPane.setVisible(false);
        mainScenePane.setEffect(null);
        sideBarPane.setEffect(null);
        addContactNameTxtBox.setText("");
        addContactPhoneNumberTxtBox.setText("");
        addContactEmailAddrTxtBox.setText("");
        addContactErrorTxt.setText("");

    }


    /** Open the "My Hours" scene */
    public void myHoursBtnClick() {

        setMyHoursDataListView();
        logHoursScene.setVisible(false);
        classroomScene.setVisible(false);
        myHoursScene.setVisible(true);

    }


    /** Blurs background and displays welcome page */
    public void displayWelcomePage() {

        ObservableList<String> gradeList = FXCollections.observableArrayList();

        for(int i = 1; i <= 12; i++) {

            gradeList.add("Grade " + i);

        }

        welcomeGradeComboBox.setItems(gradeList);

        mainScenePane.setEffect(new GaussianBlur());
        sideBarPane.setEffect(new GaussianBlur());
        mainScenePane.setVisible(true);
        sideBarPane.setVisible(true);

        instructorSidePane.setVisible(false);
        mainInstructorPane.setVisible(false);

        welcomeScene.setVisible(true);

        myHoursBtnClick(); //Display my hours page.

    }


    /** Closes welcome scene / appends data such as first name and last name to the user's file. */
    public void welcomeDoneBtnClick() {

        String firstName = welcomeFirstNameTxtBox.getText();
        String lastName = welcomeLastNameTxtBox.getText();
        String classroom = "";
        String reason = "";
        int gradeIndex = welcomeGradeComboBox.getSelectionModel().getSelectedIndex();

        boolean goForLaunch = true;
        boolean addUserToClassroom = false;

        if(gradeIndex < 0) { //Grade not selected.

            goForLaunch = false;
            reason = "You must select a grade.";

        }

        if(firstName.trim().equals("") || lastName.trim().equals("")) { //Name blanks are empty.

            reason = "You have one or more name blanks not filled";
            goForLaunch = false;

        }

        if(!welcomeClassroomTxtBox.getText().trim().equals("")) { //If the classroom text box contains text.

            classroom = welcomeClassroomTxtBox.getText().trim();

            if(Classroom.classroomExists(classroom)) {

                addUserToClassroom = true; //Add the user to the classroom.

            } else { //Classroom doesn't exists, throw error.

                goForLaunch = false;
                reason = "Class code is invalid.";

            }

        }

        if(!goForLaunch) { //One or more name blanks are not filled.

            welcomeErrorTxt.setText(reason);

        } else {

            user.setWelcomeData(firstName, lastName, classroom, gradeIndex + 1);

            if(addUserToClassroom) {

                new Classroom(classroom).addUser(user.getUserCode()); //Add the user to the classroom.

            }
            
            mainScenePane.setEffect(null);
            sideBarPane.setEffect(null);
            welcomeScene.setVisible(false);

        }

    }


    /** Logs the user out */
    public void logOutBtnClick() {

        try {

            Stage currentstage = (Stage) mainScenePane.getScene().getWindow(); //get the current scene window.

            Stage stage = new Stage();

            start(stage);

            currentstage.close(); //Close the current scene and open up the LogPro scene.

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /** Starts the loginController again. */
    public void start(Stage menu) throws Exception { //Launches the LogPro scene.

        String GUIPath;

        if (os.isMac) {

            GUIPath = "GUI/LoginGUI.fxml";

        } else {

            GUIPath = "GUI/LoginGUI.fxml";

        }

        Parent root = FXMLLoader.load(LoginController.class.getClassLoader().getResource(GUIPath)); //Launch Login FXML

        menu.setTitle("LogPro");
        menu.setScene(new Scene(root));
        menu.setResizable(false);
        menu.getScene().setFill(Color.TRANSPARENT);

        menu.initStyle(StageStyle.TRANSPARENT);//Set style as undecorated to remove the close window

        menu.show();

    }

}

//END OF MAIN CONTROLLER (Phew--that was a lot of work!)