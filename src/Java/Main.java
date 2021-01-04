//Jonathan Strickland
//LogPro Project
//Completed on 02/10/2020

package Java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


/** Main class for SISO */
public class Main extends Application {

    String GUIPath;


    @Override
    /** Launches the Main program. */
    public void start(Stage menu) throws Exception {

        OperatingSystem os = new OperatingSystem();

        if(os.isMac) {

            GUIPath = "GUI/LoginGUI.fxml";

        } else {

            GUIPath = "GUI/LoginGUI.fxml";

        }

        try {

            Parent root = FXMLLoader.load(Main.class.getClassLoader().getResource(GUIPath)); //Load the LogPro FXML
            menu.setTitle("LogPro Version 1.0 2020");

            menu.initStyle(StageStyle.TRANSPARENT);//Set style as undecorated to remove the close window

            menu.setScene(new Scene(root));

            menu.getScene().setFill(Color.TRANSPARENT);

            menu.setResizable(false);



            menu.show();



        } catch (Exception e) {

            e.printStackTrace();

            FileTool ft = new FileTool("src/ErrorLog.txt");

            ft.addToFile(e.getMessage() + "\n");

        }

    }

}