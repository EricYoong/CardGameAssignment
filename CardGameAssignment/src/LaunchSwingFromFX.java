import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LaunchSwingFromFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        Platform.setImplicitExit(false);

        Button launch = new Button("Launch");
        launch.setOnAction(e -> {
            SwingUtilities.invokeLater(SwingApp::new);
            primaryStage.hide();
        });
        StackPane root = new StackPane(launch);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}