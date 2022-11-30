package com.gitlab.lhqezio;

import com.gitlab.lhqezio.cli.Display;
import com.gitlab.lhqezio.exception.*;
import com.gitlab.lhqezio.gui.DisplayGui;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.*;
import com.gitlab.lhqezio.util.ProductsList;

import java.util.List;
import java.util.Scanner;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        DisplayGui dg = new DisplayGui();
        ProductsList pl = new ProductsList();
        primaryStage.setTitle("Computer Shop");
        primaryStage.setScene(dg.login());
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(540);
        primaryStage.show();
        dg.getLoginButton().setOnAction(e -> {
            Auth auth = new Auth();
                int retVal = auth.check(dg.getUsername(), dg.getPassword().toCharArray());
                if(retVal == 0){
                    primaryStage.setScene(dg.menu(pl));
                }
                else{
                    primaryStage.setScene(new Scene(new Label("Login Failed")));
                }
                primaryStage.show();
        });
    }
}
