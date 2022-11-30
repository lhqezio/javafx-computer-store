package com.gitlab.lhqezio.gui;

import java.util.List;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.Auth;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DisplayGui {
    private Button login;
    private TextField username;
    private PasswordField password;
    public DisplayGui(){
        login = new Button("Login");
        username = new TextField();
        password = new PasswordField();
    }
    public Scene login(){
        VBox vBox = new VBox(8);
        Label header = new Label("Computer Shop");
        Label messUsr = new Label("Username:");
        Label messPass = new Label("Password:");
        vBox.getChildren().addAll(header, messUsr, username, messPass, password, login);
        vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        Group root = new Group(vBox);
        Scene scene = new Scene(root);
        return scene;
    }

    public Button getLoginButton(){
        return login;
    }
    public String getUsername(){
        return username.getText();
    }
    public String getPassword(){
        return password.getText();
    }
    public Scene menu(List<Product> products){
        Label header = new Label("Products Of The Day");
        ObservableList<Product> productsOfTheDay = FXCollections.observableArrayList(products);
        ListView<Product> listView = new ListView<>(productsOfTheDay);
        listView.setPrefSize(200, 250);
        listView.setOrientation(Orientation.VERTICAL);
        VBox vBox = new VBox(8);
        vBox.getChildren().addAll(header, listView);
        vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        Group root = new Group(vBox);
        Scene scene = new Scene(root);
        return scene;
    }
}
