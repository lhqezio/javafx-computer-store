package com.gitlab.lhqezio;

import com.gitlab.lhqezio.cli.Display;
import com.gitlab.lhqezio.exception.*;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.*;
import com.gitlab.lhqezio.util.ProductsList;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Display.clear();
        Auth auth = new Auth();
        auth.login();
        User user = auth.getUser();
        boolean cont = true;
        while (cont) {
            ProductsList productsList_ = new ProductsList();
            Scanner sc = new Scanner(System.in);
            char selection = 'a';
            Display display = new Display(user, productsList_);
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
                            throw new InvalidUserInputException();
                    }
                } catch (InvalidUserInputException e) {
                    System.out.println("Invalid input go back to homepage");
                    display.homepage();
                }

            }
        }
    }

    /**
     * This method is used as a switch for the search filter menu.
     *
     * @param display
     * @param productsList
     * @return void
     */
    public static void searchFilterSwitch(Display display, ProductsList prod) throws InvalidUserInputException {
        Scanner sc = new Scanner(System.in);
        char selection = display.searchFilterPage();
        switch (selection) {
            case '1':
            case '2':
            case '3':
            case '4':
                String keyword = sc.nextLine();
                List<Product> filteredProducts = prod.filterBy(selection, keyword);
                display.searchResult(filteredProducts, keyword);
                break;
            case '5':
                display.homepage();
                break;
            default:
                throw new InvalidUserInputException();
        }
    }
};
