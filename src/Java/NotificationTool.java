//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NotificationTool {


    /** When called, this creates a new alert window with a field and a submit and cancel button, "Cancel" and "Confirm". */
    public static String inputWindow(String title, String header, String content) {

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        String returnString = "";

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            return result.get(); //What the user typed.

        }

        return null;

    }


    /** A message type used to notify the user of an error. */
    public static void notifyUserOfError(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeOne = new ButtonType("Okay");
        alert.getButtonTypes().setAll(buttonTypeOne);

        alert.showAndWait();

    }

    /** When called, this creates a new alert window with the custom buttons, "Cancel" and "Remove". */
    public static boolean removeWindow(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeOne = new ButtonType("Cancel");
        ButtonType buttonTypeTwo = new ButtonType("Remove");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeTwo) { //If confirm is pressed, return true, else return false.

            return true;

        } else {

            return false;

        }

    }

    /** A message type used to notify the user of something */
    public static void notifyUser(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeOne = new ButtonType("Okay");
        alert.getButtonTypes().setAll(buttonTypeOne);

        alert.showAndWait();

    }



}
