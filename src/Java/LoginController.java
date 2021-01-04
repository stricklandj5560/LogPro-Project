//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController {

    public TextField userNameTxtBox;
    public TextField passwordTxtBox;
    public TextField createStudentUserNameTxtBox;
    public TextField createStudentPasswordTxtBox;
    public TextField createStudentPasswordConfirmTxtBox;
    public TextField createInstructorUserNameTxtBox;
    public TextField createInstructorPasswordTxtBox;
    public TextField createInstructorPasswordConfirmTxtBox;

    public PasswordField loginPasswordField;

    public CheckBox showPasswordCheckBox;

    public Text createStudentErrorTxt;
    public Text createInstructorErrorTxt;
    public Text loginErrorTxt;

    public AnchorPane studentOrTeacherScene;
    public AnchorPane instructorCreateScene;
    public AnchorPane studentCreateScene;

    public static boolean isNewUser = false;
    public static boolean isAdmin = false;

    OperatingSystem os = new OperatingSystem();


    @FXML
    public void initialize() { //Runs when the FXML loads.

        //Get today's date.
        isAdmin = false;
        isNewUser = false;

        SimpleDateFormat simpledatafo = new SimpleDateFormat("MM-dd-yyyy");
        Date newDate = new Date();
        
        String date = simpledatafo.format(newDate); //Format date in MM-DD-YYYY format. The backup file will be named this.

        //Backup data if needed.
        new Backup(date).performBackupIfNeeded();

    }

    public void loginBtnClick() {

        String userName = userNameTxtBox.getText().trim();
        String password;

        if(showPasswordCheckBox.isSelected()) { //If the password is showing.

             password = passwordTxtBox.getText().trim();

        } else { //Password is hidden.

             password = loginPasswordField.getText().trim();

        }

        User user = new User(userName);

        String errorMsg = "";

        loginErrorTxt.setText("");

        if(user.exists()) {

            if(userName.equals("admin")) { //Check to see if the user is an administrator

                if(new Admin().isAdminPassword(password)) { //If the password is correct, log the admin in.

                    isAdmin = true;
                    launchMenu();


                } else { //Admin password is incorrect.

                    errorMsg = "Incorrect password.";

                }

            } else {

                if (user.checkPassword(password)) { //Check to see if the user's password is correct.

                    user.setUserAsCurrentUser();
                    launchMenu();

                } else { //Password is incorrect.

                    errorMsg = "Incorrect password.";

                }


            }


        } else {

            errorMsg = "User does not exist!";

        }

        loginErrorTxt.setText(errorMsg); //display error if there is one.

    }


    /** Closes the current scene and launches the main logPro scene. */
    public void launchMenu() {

        try {

            Stage currentstage = (Stage) userNameTxtBox.getScene().getWindow(); //get the current scene window.

            Stage stage = new Stage();

            start(stage);

            currentstage.close(); //Close the current scene and open up the LogPro scene.

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /** Loads and launches the main logPro scene. */
    public void start(Stage menu) throws Exception { //Launches the LogPro scene.

        String GUIPath;

        if (os.isMac) {

            GUIPath = "GUI/LogPro.fxml";

        } else {

            GUIPath = "GUI/LogPro.fxml";

        }

        Parent root = FXMLLoader.load(LoginController.class.getClassLoader().getResource(GUIPath)); //Launch Main FXML

        menu.setTitle("LogPro");
        menu.setScene(new Scene(root, 1000,600));
        menu.setResizable(false);
        menu.getScene().setFill(Color.TRANSPARENT);

        menu.initStyle(StageStyle.TRANSPARENT);//Set style as undecorated to remove the close window

        menu.show();

    }


    /**Either shows or hides the login password. */
    public void loginPasswordCheckBoxUpdate() {

        if(showPasswordCheckBox.isSelected()) { //Show password

            passwordTxtBox.setText(loginPasswordField.getText()); //Set show password text to the password field text.
            loginPasswordField.setVisible(false); //set the password field invisible
            passwordTxtBox.setVisible(true); //set the show password txt box visible.

        } else {

            loginPasswordField.setText(passwordTxtBox.getText()); //Set password field text to the show password text.
            passwordTxtBox.setVisible(false); //Set the show password txt field invisible.
            loginPasswordField.setVisible(true); //Set the password field visible.

        }

    }


    /** Opens the create account page */
    public void createAccountBtnClick() {

        studentOrTeacherScene.setVisible(true);
        loginErrorTxt.setText("");

    }

    /** Go back button is clicked in the instructor or teacher scene */
    public void goBackOneBtnClick() {

        //Go back to the login page.
        studentOrTeacherScene.setVisible(false);

    }

    /** Go back button is clicked in the instructor create or student create scene. */
     public void goBackTwoBtnClick() {

         //Go back the student or instructor page.
         studentCreateScene.setVisible(false);
         instructorCreateScene.setVisible(false);


     }

    /** Go to the student create account scene */
    public void studentBtnClick() {

        studentCreateScene.setVisible(true);

    }

    /** Go to the instructor create account scene */
    public void instructorBtnClick() {

        instructorCreateScene.setVisible(true);

    }


    /**Returns true if a text is empty */
    public boolean isEmpty(String s) {

        return(s.trim().equals(""));

    }

    /**Create an instructor account */
    public void createInstructorAccountBtnClick() {

        String userNameTxt = createInstructorUserNameTxtBox.getText();
        String password = createInstructorPasswordTxtBox.getText();
        String confirmPassword = createInstructorPasswordConfirmTxtBox.getText();

        boolean goForLaunch = true; //Good to create account.
        String reason = "";
        createInstructorErrorTxt.setText("");

        if(isEmpty(userNameTxt)) {

            goForLaunch = false;
            reason = "Username field is blank.";

        }

        if(isEmpty(password) || isEmpty(confirmPassword)) {

            goForLaunch = false;
            reason = "Password field is blank.";

        }

        if (!(password.trim().equals(confirmPassword.trim()))) {

            goForLaunch = false;
            reason = "Passwords do not match!";

        }

        createInstructorErrorTxt.setText(reason);

        if (goForLaunch) { //create user.

            User user = new User(userNameTxt.trim());

            if(user.exists()) { //User exists, throw error.

                createInstructorErrorTxt.setText("Username already taken!");

            } else { //create account

                if(userNameTxt.contains("*")) { //* dictates a new user

                    createInstructorErrorTxt.setText("Username cannot contain '*'.");

                } else {

                    createInstructorErrorTxt.setText("");
                    createInstructorUserNameTxtBox.setText("");
                    createInstructorPasswordTxtBox.setText("");
                    createInstructorPasswordConfirmTxtBox.setText("");

                    userNameTxtBox.setText("");
                    passwordTxtBox.setText("");

                    user.setPassword(new Encrypter().encryptText(password));
                    user.createUser(true);

                    instructorCreateScene.setVisible(false);
                    studentOrTeacherScene.setVisible(false);

                }

            }

        }

    }


    /** Create a student account */
    public void createStudentAccountBtnClick() {

        String userNameTxt = createStudentUserNameTxtBox.getText();
        String password = createStudentPasswordTxtBox.getText();
        String confirmPassword = createStudentPasswordConfirmTxtBox.getText();

        boolean goForLaunch = true; //Good to create account.
        String reason = "";
        createStudentErrorTxt.setText("");

        if(isEmpty(userNameTxt)) {

            goForLaunch = false;
            reason = "Username field is blank.";

        }

        if(isEmpty(password) || isEmpty(confirmPassword)) {

            goForLaunch = false;
            reason = "Password field is blank.";

        }

        if (!(password.trim().equals(confirmPassword.trim()))) {

            goForLaunch = false;
            reason = "Passwords do not match!";

        }

        createStudentErrorTxt.setText(reason);

        if (goForLaunch) { //create user.

            User user = new User(userNameTxt.trim());

            if(user.exists()) { //User exists, throw error.

                createStudentErrorTxt.setText("Username already taken!");

            } else { //create account

                if(userNameTxt.contains("*")) { //'*' dictates a new user.

                    createStudentErrorTxt.setText("Username cannot contain '*'.");

                } else {

                    createStudentErrorTxt.setText("");
                    createStudentUserNameTxtBox.setText("");
                    createStudentPasswordTxtBox.setText("");
                    createStudentPasswordConfirmTxtBox.setText("");

                    userNameTxtBox.setText("");
                    passwordTxtBox.setText("");

                    user.setPassword(new Encrypter().encryptText(password));
                    user.createUser(false);

                    studentCreateScene.setVisible(false);
                    studentOrTeacherScene.setVisible(false);

                }

            }

        }

    }


    /** Closes the application. */
    public void loginCloseBtnClick() {

        System.exit(0); //Close the program.

    }


}
