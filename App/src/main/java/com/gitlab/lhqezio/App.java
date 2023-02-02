package com.gitlab.lhqezio;
/**
 * App class.
 * @author Hoang
 */
import java.nio.file.Paths;

import com.gitlab.lhqezio.gui.DisplayGui;
import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.DataLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        DataLoader dataLoader = new CsvLoader(Paths.get("csv_files"));
        DisplayGui dg = new DisplayGui(primaryStage, dataLoader);
        primaryStage.setTitle("Computer Shop");
        primaryStage.setScene(new Scene(dg.login()));
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(540);
        primaryStage.show();
    }
}
