package com.gitlab.lhqezio;

import com.gitlab.lhqezio.cli.Display;
import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Product> products = loadProducts();
        Display display = new Display();
        display.loginMsg(true, new User());
        display.homepage(products);

    }
    public static List<Product> loadProducts(){
        List<Product>products = new ArrayList<>();
        products.add(new Laptop("Laptop1","Lenovo",1000,10,"Thin And Light","Leno01","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500,18));
        products.add(new Laptop("Laptop2","Lenovo",1000,10,"Thin And Ultra Light","Leno02","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500,18));
        products.add(new Computer("Computer1","Lenovo",1000,10,"Thin And Light","Leno01","Intel Core i7","16GB","1TB","Nvidia GTX 1050","Windows 10",500));
        return products;
    }
};

