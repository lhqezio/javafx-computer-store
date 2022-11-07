package com.gitlab.lhqezio.cli;

import com.gitlab.lhqezio.User;
import com.gitlab.lhqezio.items.Product;

import java.util.List;
import java.util.Scanner;

public class Display {
    private User user;

    public static void welcomeMsg() {
        System.out.println("Welcome to the Computer Store purchase experience,May I have your credentials ?(Username then Password)");
    }

    public boolean loginMsg(boolean login, User user) {
        this.user = user;
        if (login) {
            System.out.println("Login successful, welcome back " + user.getUsername() + ".");
            return true;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Login failed, Do you want to browse as Guest ?(Y).");
            if (scanner.next().charAt(0) == 'Y') {
                this.user = new User();
                return true;
            }
        }
        return false;
    }
    private static String menu(){
        return "\n1.Homepage        2.Search Filter        3.Cart        4.Exit";
    }
    private String topBar(){
        return "Hello, "+user.getUsername()+"!\n";
    }
    public void homepage(List<Product> prodOfTheDay){
        StringBuilder sysout = new StringBuilder(topBar());
        for(Product prod:prodOfTheDay){
            sysout.append(prod.getClass().getSimpleName()).append(":\n").append(prod).append("\n");
        }
        sysout.append(menu());
        System.out.println(sysout);
    }
}
