package Main;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.Collection;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // установка надписи
        Text text = new Text("Hello!");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(80);   // установка положения надписи по оси X

        Button btn = new Button("kekes");

        Group group = new Group(text);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
    }
}
