package Main;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Calculate");
        stage.setMaxHeight(270);
        stage.setMaxWidth(270);
        stage.setMinHeight(270);
        stage.setMinWidth(270);

        stage.show();
    }


}
