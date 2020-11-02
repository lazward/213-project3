import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * This is the main driver class of the program, it runs the 'start' method to
 * initalize the GUI and allow operations to occur.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 * 
 */

public class Main extends Application {

    /**
     *
     * This method starts the GUI, taking in a primaryStage, it sets the scene using
     * titles and dimentions to produce a window to conduct operations.
     * 
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("project3.fxml"));
        primaryStage.setTitle("Project 3");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}