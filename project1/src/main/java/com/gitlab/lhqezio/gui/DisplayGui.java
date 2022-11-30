package com.gitlab.lhqezio.gui;

import java.util.List;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.Auth;
import com.gitlab.lhqezio.util.DataLoader;
import com.gitlab.lhqezio.util.ProductsList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayGui {
    private ProductsList pl;
    private Button login;
    private TextField username;
    private PasswordField password;
    private HBox menuContainer;
    private Button searchButton;
    private ComboBox comboBox;
    private TextField searchField;
    private VBox searchBox;
    private VBox menu;
    private Button searchMenuButton = new Button("Search");
    private Button menuButton = new Button("Menu");
    private Button cartButton = new Button("Cart");
    private HBox buttons;

    public DisplayGui(Stage primaryStage, DataLoader dataLoader) {
        login = new Button("Login");

        Auth auth = new Auth(dataLoader);
        login.setOnAction(e -> {
                int retVal = auth.check(this.getUsername(), this.getPassword().toCharArray());
                if(retVal == 0){
                    ProductsList pl = new ProductsList(dataLoader);
                    primaryStage.setScene(this.menu(pl));
                }
                else{
                    Group loginFailed = this.login();
                    VBox v = (VBox)loginFailed.getChildren().get(0);
                    v.getChildren().add(new Label("Login Failed"));
                    primaryStage.setScene(new Scene(loginFailed));
                }
                primaryStage.show();
        });

        username = new TextField();
        password = new PasswordField();
        menuButtonHandler();
    }

    public Group login() {
        VBox vBox = new VBox(8);
        Label header = new Label("Computer Shop");
        Label messUsr = new Label("Username:");
        Label messPass = new Label("Password:");
        vBox.getChildren().addAll(header, messUsr, username, messPass, password, login);
        vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        Group root = new Group(vBox);
        return root;
    }

    public Button getLoginButton() {
        return login;
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public Scene menu(ProductsList products) {
        this.pl = products;
        Label header = new Label("Products Of The Day");
        ObservableList<Product> productsOfTheDay = FXCollections.observableArrayList(pl.getProductsOfTheDay());
        ListView<Product> listView = createProdList(productsOfTheDay);
        productDetails(listView);

        searchMenuButton.setOnAction(e -> {
            menuContainer.getChildren().clear();
            menuContainer.getChildren().addAll(search());
        });
        buttons = new HBox(8);
        buttons.getChildren().addAll(cartButton, searchMenuButton);
        searchMenuButton.setPrefSize(100, 20);
        cartButton.setPrefSize(100, 20);
        this.menu = new VBox(8);
        menu.getChildren().addAll(header, listView, buttons);
        menu.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        menuContainer = new HBox(8);
        menuContainer.getChildren().addAll(menu);
        Group root = new Group(menuContainer);
        Scene scene = new Scene(root);
        return scene;
    }

    private ListView<Product> createProdList(List<Product> products) {
        ObservableList<Product> productsOfTheDay = FXCollections.observableArrayList(products);
        ListView<Product> listView = new ListView<>(productsOfTheDay);
        listView.setPrefSize(200, 250);
        listView.setOrientation(Orientation.VERTICAL);
        return listView;
    }

    private void productDetails(ListView<Product> listView) {
        listView.setOnMouseClicked(e -> {
            VBox productDetails = new VBox(8);
            Product product = listView.getSelectionModel().getSelectedItem();
            Label category = new Label(product.getCategory());
            Label name = new Label(product.getName());
            Label manu = new Label(product.getManufacturer());
            Label rprice = new Label("ORIGINAL PRICE: " + String.valueOf(product.getPrice()) + "$");
            Label oprice = new Label("OUR PRICE: " + String.valueOf(product.getPrice() - product.getDiscount()) + "$");
            productDetails.getChildren().addAll(category, name, manu, rprice, oprice);
            if (product.getQuantity() < 5) {
                Label status = new Label("Low Stock!!!");
                productDetails.getChildren().add(status);
            }
            Label desc = new Label("Description:\n" + product.getDescription());
            ;
            Button addToCart = new Button("Add To Cart");
            productDetails.getChildren().addAll(desc, addToCart);
            productDetails.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
            if (menuContainer.getChildren().size() == 1) {
                menuContainer.getChildren().add(productDetails);
            } else {
                menuContainer.getChildren().set(1, productDetails);
            }
        });
    }

    public VBox search() {
        ObservableList<String> searchFilter = FXCollections.observableArrayList("Search By Name", "Search By Category", "Search By Price", "Search By Manufacturer");
        comboBox = new ComboBox(searchFilter);
        searchField = new TextField();
        searchField.setPromptText("Enter Keyword");
        this.searchButton = new Button("Search");
        searchButtonHandler();
        comboBox.getSelectionModel().selectFirst();
        HBox buttons = new HBox(8);
        buttons.getChildren().addAll(searchButton, menuButton, cartButton);
        searchBox = new VBox(8);
        searchBox.getChildren().addAll(comboBox, searchField, buttons);
        searchBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        return searchBox;

    }

    private void searchButtonHandler() {
        this.searchButton.setOnAction(e -> {
            int index = comboBox.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            char selection = Integer.toString(index + 1).charAt(0);
            ;
            System.out.println(selection);
            ListView<Product> listView = createProdList(pl.filterBy(selection, searchField.getText()));
            if (searchBox.getChildren().size() == 3) {
                searchBox.getChildren().add(listView);
            } else {
                searchBox.getChildren().set(3, listView);
            }
            productDetails(listView);
        });
    }

    private void menuButtonHandler() {
        this.menuButton.setOnAction(e -> {
            menuContainer.getChildren().clear();
            menuContainer.getChildren().addAll(menu);
            buttons.getChildren().clear();
            buttons.getChildren().addAll(cartButton, searchMenuButton);
        });
    }
}