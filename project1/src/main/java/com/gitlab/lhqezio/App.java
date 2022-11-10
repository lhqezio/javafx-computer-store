package com.gitlab.lhqezio;

import com.gitlab.lhqezio.cli.Display;
import com.gitlab.lhqezio.cli.InvalidUserInputException;
import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.User;
import com.gitlab.lhqezio.util.CSV_Util;
import com.gitlab.lhqezio.util.ProductsList;


import java.io.Console;
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
        File gitIgnoreDir_ = CSV_Util.getGitIgnoreDir();
        Path usersCsv = (new File(gitIgnoreDir_, "users.csv")).toPath();
        LoginChecker loginChecker_ = new LoginChecker(usersCsv);

        Console myConsole = System.console();

        String username;
        while (true) {
            System.out.println("Enter your username:");
            username = myConsole.readLine();
            System.out.println("Enter your password:");
            char[] password_ = myConsole.readPassword();
            int retValue = loginChecker_.check(username, password_);
            //do NOT tell users that their username is incorrect, you can find users that way, usually there's a "forgot username" button, send email
            if (retValue != 0) {
                System.out.println("incorrect credentials, try again");
                continue;
            }
            break;
        }
        User user_ = loginChecker_.getUser();
        boolean cont = true;
        while (cont) {
            ProductsList productsList_ = new ProductsList();
            Scanner sc = new Scanner(System.in);
            char selection = 'a';
            Display display = new Display(user_, productsList_);
            display.homepage();
            while (cont) {
                selection = sc.next().charAt(0);
                try {
                    switch (selection) {
                        case '1':
                            display.homepage();
                            break;
                        case '2':
                            searchFilterSwitch(display, productsList_);
                            break;
                        case '4':
                            return;
                        default:
                            throw new InvalidUserInputException(null);
                    }
                } catch (InvalidUserInputException e) {
                    System.out.println("Invalid input go back to homepage");
                    display.homepage();
                }

            }
        }
    }
    /**
     * @param products
     * @return
     */
    public static void searchFilterSwitch(Display display,ProductsList prod)throws InvalidUserInputException{
        Scanner sc = new Scanner(System.in);
        char selection = display.searchFilterPage();
        switch (selection) {
            case '1': case '2': case '3': case '4':
                String keyword = sc.nextLine();
                List<Product> filteredProducts = prod.filterBy(selection, keyword);
                display.searchResult(filteredProducts, keyword);
                break;
            case '5':
                display.homepage();
                sc.close();
                break;
            default:
                sc.close();
                throw new InvalidUserInputException();
        }
    }
};
