/**
 * Display class handles the display of the application via Command Line.
 * It's non static and it's tailored to different users depends on their name and priviledges.
 * It's also handle nenu specific user input.
 */
package com.gitlab.lhqezio.cli;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.User;

import java.util.List;
import java.util.Scanner;

public class Display {
    private User user;

    public Display(User user_) {
        this.user = user_;
    }

    /**
     * Display Welcome message
     */
    public void welcomeMsg() {
        System.out.println("Welcome to the Computer Store purchase experience,May I have your credentials ?(Username then Password)");
    }

    /**
     * Take user credentials and return whether they are valid or not and if they want to browse as guest
     * @param user
     * @return user's status (Good to continue or not)
     */
    public boolean loginMsg(User user) {
        //for reference only, do not use this method now
        System.out.println("Login successful, welcome back " + user.getUsername() + ".");
        this.user = user;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login failed, Do you want to browse as Guest ?(Y).");
        char input = scanner.nextLine().charAt(0);
        if (input == 'Y' || input == 'y') {
            System.out.println("Welcome Guest.");
            return true;
        } else {
            System.out.println("Goodbye.");
            return false;
        }
    }

    /**
     * @return Main Menu String.
     */
    private static String menu() {
        return "\n1.Homepage        2.Search Filter        3.Cart        4.Exit";
    }

    private String topBar() {
        return "Hello, " + user.getUsername() + "!\n";
    }

    public void homepage(List<Product> prodOfTheDay) {
        StringBuilder sysout = new StringBuilder(topBar()).append("\n\n\nPRODUCTS OF THE DAY!!!\n\n\n");
        for (Product prod : prodOfTheDay) {
            sysout.append(prod.getCategory()).append(":\n").append(prod).append("\n");
        }
        sysout.append(menu()).append(inputMsg());
        System.out.println(sysout);
    }

    private static String inputMsg() {
        return "\nPlease enter your choice:";
    }

    /**
     * This method is used to display the search filter menu and return the user selection.
     * @return input
     */
    public char searchFilterPage() {
        StringBuilder searchPage = new StringBuilder(topBar());
        searchPage.append("\n1.Search by Keyword        \n\n2.Search by Category        \n\n3.Search by Price        \n\n4.Search by Manufacturer        \n\n5.Back to Homepage");
        searchPage.append(inputMsg());
        System.out.println(searchPage);
        Scanner sc = new Scanner(System.in);
        char input = sc.next().charAt(0);
        System.out.println(input);
        searchSpecificPage(input);
        return input;
    }

    private void searchSpecificPage(char input) {
        switch (input) {
            case '1':
                System.out.println("Please enter the keyword:");
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
        StringBuilder results = new StringBuilder("Search Results For: " + filteredBy).append(":\n");
        for (Product product : products) {
            results.append(product.getName()).append("\n" + product.getPrice() + "\n").append(product.getDescription());
        }
        results.append(menu()).append(inputMsg());
        System.out.println(results);
    }
}
