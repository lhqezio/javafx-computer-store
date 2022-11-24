/**
 * Display class handles the display of the application via Command Line.
 * It's non static and it's tailored to different users depends on their name and priviledges.
 * It's also handle nenu specific user input.
 */
package com.gitlab.lhqezio.cli;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.User;
import com.gitlab.lhqezio.util.ProductsList;

import java.util.List;
import java.util.Scanner;

public class Display {
    private User user;
    private ProductsList products;

    public Display(User user_, ProductsList productsOfTheDay) {
        this.user = user_;
        this.products = productsOfTheDay;
    }

    /**
     * @return Main Menu String.
     */
    private static String menu() {
        return "\n1.Homepage        2.Search Filter        3.Cart        4.Exit";
    }

    private static String inputMsg() {
        return "\nPlease enter your choice:";
    }

    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Display Welcome message
     */
    public void welcomeMsg() {
        clear();
        System.out.println("Welcome to the Computer Store purchase experience,May I have your credentials ?");
    }

    private String topBar() {
        return "Hello, " + user.getUsername() + "!\n";
    }

    public void homepage() {
        clear();
        StringBuilder sysout = new StringBuilder(topBar()).append("\n\n\nPRODUCTS OF THE DAY!!!\n\n\n");
        for (Product prod : products.getProductsOfTheDay()) {
            sysout.append(prod).append("\n");
        }
        sysout.append(menu()).append(inputMsg());
        System.out.println(sysout);
    }


    /**
     * This method is used to display the search filter menu and return the user selection.
     *
     * @return input
     */
    public char searchFilterPage() {
        clear();
        StringBuilder searchPage = new StringBuilder(topBar());
        searchPage.append("\n1.Search by name        \n\n2.Search by Category        \n\n3.Search by Price        \n\n4.Search by Manufacturer        \n\n5.Back to Homepage");
        searchPage.append(inputMsg());
        System.out.println(searchPage);
        Scanner sc = new Scanner(System.in);
        char input = sc.next().charAt(0);
        System.out.println(input);
        searchSpecificPage(input);
        return input;
    }

    private void searchSpecificPage(char input) {
        clear();
        switch (input) {
            case '1':
                System.out.println("Please enter the name:");
                break;
            case '2':
                System.out.println("Please enter the category:");
                break;
            case '3':
                System.out.println("Please enter the price:");
                break;
            case '4':
                System.out.println("Please enter the manufacturer:");
                break;
            case '5':
                break;
            default:
                System.out.println("Invalid input, please try again.");
                searchFilterPage();
                break;
        }
    }

    public void searchResult(List<Product> products, String filteredBy) {
        clear();
        if (products.size() != 0) {
            StringBuilder results = new StringBuilder("Search Results For \"" + filteredBy + "\"").append(":\n");
            for (Product product : products) {
                results.append(product.getCategory() + ":\n").append(product).append("\n\n");
            }
            results.append(menu()).append(inputMsg());
            System.out.println(results);
        } else {
            System.out.println("No results found for \"" + filteredBy + "\"" + menu() + inputMsg());

        }
    }
}
