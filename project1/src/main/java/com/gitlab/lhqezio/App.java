package com.gitlab.lhqezio;

import com.gitlab.lhqezio.cli.Display;
import com.gitlab.lhqezio.cli.InvalidUserInputException;
import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.util.CSV_Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Display display = new Display();
        boolean cont = true;
        display.welcomeMsg();
        if (display.loginMsg(new User())) {
            while (cont) {
                List<Product> prod = loadProducts();
                Scanner sc = new Scanner(System.in);
                char selection = 'a';
                display.homepage(prod);
                while (cont) {
                    selection = sc.next().charAt(0);
                    sc.nextLine();
                    try {
                        switch (selection) {
                            case '1':
                                display.homepage(prod);
                                break;
                            case '2':
                                switch (display.searchFilterPage()) {
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                        String keyword = sc.nextLine();
                                        display.searchResult(prod, keyword);
                                        break;
                                    case '5':
                                        display.homepage(prod);
                                        break;
                                    default:
                                        throw new InvalidUserInputException(null);
                                }
                            case '4':
                                return;
                            default:
                                throw new InvalidUserInputException(null);
                        }
                    } catch (InvalidUserInputException e) {
                        System.out.println("Invalid input go back to homepage");
                        display.homepage(prod);
                    }

                }
            }
        } else {
            return;
        }

    }

    public static File getGitIgnoreDir() {
        File dir_ = new File(System.getProperty("user.dir"));
        while (!(new File(dir_, ".gitignore")).exists()) {
            dir_ = dir_.getParentFile();
        }
        return dir_;
    }

    public static List<Product> loadProducts() {

        Path csvPath = (new File(getGitIgnoreDir(), "computers.csv")).toPath();
        try {
            List<Product> products = new ArrayList<>();

            String[][] allRowsArr = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            for (int i = 0; i < allRowsArr.length; i++) {
                String[] rowArr = allRowsArr[i];
                switch (rowArr[0]) {
                    case "Laptop":
                        products.add(new Laptop(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Integer.parseInt(rowArr[4]), rowArr[5], rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], Integer.parseInt(rowArr[12]), Integer.parseInt(rowArr[13])));
                        break;
                    case "Computer":
                        products.add(new Computer(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Integer.parseInt(rowArr[4]), rowArr[5], rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], Integer.parseInt(rowArr[12])));
                        break;
                }
            }

            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // List<Product>products = new ArrayList<>();
        // products.add(new Laptop("Lenovo IdeaPad 5X","Lenovo",1000,10,"Thin And Light","Leno01","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500,18));
        // products.add(new Laptop("Lenovo X1 Carbon","Lenovo",1000,10,"Thin And Ultra Light","Leno02","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500,18));
        // products.add(new Computer("Alienware X51","Lenovo",1000,10,"Thin And Light","Leno01","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500));
        // return products;
    }
};
