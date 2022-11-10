package com.gitlab.lhqezio.util;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.util.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsList {
    private List<Product> products;
    private HashMap<String,HashMap<String,List<Product>>> productsDictionary;

    private File gitIgnoreDir_;
    public ProductsList() {
        gitIgnoreDir_ = CSV_Util.getGitIgnoreDir();
        Path csvPath = (new File(gitIgnoreDir_, "computers.csv")).toPath();
        try {
            List<Product> products = new ArrayList<>();

            String[][] allRowsArr = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            for (int i = 0; i < allRowsArr.length; i++) {
                String[] rowArr = allRowsArr[i];
                switch (rowArr[0]) {
                    case "Laptop":
                        products.add(new Laptop(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]),Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], rowArr[12], Integer.parseInt(rowArr[13]), Integer.parseInt(rowArr[14])));
                        break;
                    case "Computer":
                        products.add(new Computer(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]),Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], rowArr[12], Integer.parseInt(rowArr[13])));
                        break;
                }
            }
            this.products = products;
            initProductDictionary();
        } 
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public List<Product> getProductsOfTheDay() {
        List<Product> productsOfTheDay = new ArrayList<>();
        products.stream().filter(product -> product.getDiscount() > 0).forEach(productsOfTheDay::add);
        productsOfTheDay.sort((o1, o2) -> (int) (o1.discountCompareTo(o2)));
        if(productsOfTheDay.size() > 5) {
            return productsOfTheDay.subList(0, 5);
        }

        return productsOfTheDay;
    }
    public void initProductDictionary(){
        HashMap<String,HashMap<String,List<Product>>> productsDictionary = new HashMap<>();
        HashMap<String,List<Product>> manufactureMap = new HashMap<>();
        HashMap<String,List<Product>> nameMap = new HashMap<>();
        HashMap<String,List<Product>> categoryMap = new HashMap<>();
        HashMap<String,List<Product>> priceMap = new HashMap<>();
        productsDictionary.put("Manufacturer",new HashMap<>());
        productsDictionary.put("Name",new HashMap<>());
        productsDictionary.put("Category",new HashMap<>());
        productsDictionary.put("Price",new HashMap<>());
        for(Product product : products){
            if(manufactureMap.containsKey(product.getManufacturer())){
                manufactureMap.get(product.getManufacturer()).add(product);
            }
            else{
                manufactureMap.put(product.getManufacturer(),new ArrayList<>());
                manufactureMap.get(product.getManufacturer()).add(product);
            }
            if(nameMap.containsKey(product.getName())){
                nameMap.get(product.getName()).add(product);
            }
            else{
                nameMap.put(product.getName(),new ArrayList<>());
                nameMap.get(product.getName()).add(product);
            }
            if(categoryMap.containsKey(product.getCategory())){
                categoryMap.get(product.getCategory()).add(product);
            }
            else{
                categoryMap.put(product.getCategory(),new ArrayList<>());
                categoryMap.get(product.getCategory()).add(product);
            }
            if(priceMap.containsKey(String.valueOf(product.getPrice()))){
                priceMap.get(String.valueOf(product.getPrice())).add(product);
            }
            else{
                priceMap.put(String.valueOf(product.getPrice()),new ArrayList<>());
                priceMap.get(String.valueOf(product.getPrice())).add(product);
            }
        }
        this.productsDictionary = productsDictionary;
    }
    public List<Product> filterBy(char selection, String keyword) {
        System.out.println("selection: " + selection + " keyword: " + keyword);
        String[] keywords = keyword.split(";");
        List<Product> filteredProducts = new ArrayList<>();
        switch (selection) {
            case '1':
                for (String key : productsDictionary.get("Name").keySet()) {
                    for (String keyword_ : keywords) {
                        if (key.toLowerCase().contains(keyword_.toLowerCase())) {
                            filteredProducts.addAll(productsDictionary.get("Name").get(key));
                        }
                    }
                }
                break;
            case '2':
                for (String key : productsDictionary.get("Category").keySet()) {
                    System.out.println(key);
                    for (String keyword_ : keywords) {
                        if (key.toLowerCase().contains(keyword_.toLowerCase())) {
                            filteredProducts.addAll(productsDictionary.get("Category").get(key));
                        }
                    }
                }
                break;
            case '3':
                for (String key : productsDictionary.get("Price").keySet()) {
                    for (String keyword_ : keywords) {
                        if (key.toLowerCase().contains(keyword_.toLowerCase())) {
                            filteredProducts.addAll(productsDictionary.get("Price").get(key));
                        }
                    }
                }
                break;
            case '4':
                for (String key : productsDictionary.get("Manufacture").keySet()) {
                    for (String keyword_ : keywords) {
                        if (key.contains(keyword_)) {
                            filteredProducts.addAll(productsDictionary.get("Manufacture").get(key));
                        }
                    }
                }
                break;
        }
        return filteredProducts;
    }
}
