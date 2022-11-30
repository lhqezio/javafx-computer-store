package com.gitlab.lhqezio.util;

import com.gitlab.lhqezio.items.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsList {
    private List<Product> products;
    private HashMap<String, HashMap<String, List<Product>>> productsDictionary;

    public ProductsList() {
        DataLoader dataLoader = new CsvLoader();
        String[][] allRowsArr = dataLoader.getData("products.csv");
        this.products = CSV_Util.parseProduct(allRowsArr);
        initProductDictionary();
    }

    public List<Product> getProductsOfTheDay() {
        List<Product> productsOfTheDay = new ArrayList<>();
        products.stream().filter(product -> product.getDiscount() > 0 && product.getQuantity()>0).forEach(productsOfTheDay::add);
        productsOfTheDay.sort((o1, o2) -> (int) (o1.discountCompareTo(o2)));
        if (productsOfTheDay.size() > 5) {
            return productsOfTheDay.subList(0, 5);
        }

        return productsOfTheDay;
    }

    public void initProductDictionary() {
        HashMap<String, HashMap<String, List<Product>>> productsDictionary = new HashMap<>();
        HashMap<String, List<Product>> manufactureMap = new HashMap<>();
        HashMap<String, List<Product>> nameMap = new HashMap<>();
        HashMap<String, List<Product>> categoryMap = new HashMap<>();
        HashMap<String, List<Product>> priceMap = new HashMap<>();
        for (Product product : products) {
            String manufacturer = product.getManufacturer();
            String name = product.getName();
            String category = product.getCategory();
            String priceString = String.valueOf(product.getPrice());
            if (!manufactureMap.containsKey(manufacturer)) {
                manufactureMap.put(manufacturer, new ArrayList<>());
            }
            manufactureMap.get(manufacturer).add(product);

            if (!nameMap.containsKey(name)) {
                nameMap.put(name, new ArrayList<>());
            }
            nameMap.get(name).add(product);

            if (!categoryMap.containsKey(category)) {
                categoryMap.put(category, new ArrayList<>());
            }
            categoryMap.get(category).add(product);

            if (!priceMap.containsKey(priceString)) {
                priceMap.put(priceString, new ArrayList<>());
            }
            priceMap.get(priceString).add(product);

        }
        productsDictionary.put("Manufacturer", manufactureMap);
        productsDictionary.put("Name", nameMap);
        productsDictionary.put("Category", categoryMap);
        productsDictionary.put("Price", priceMap);
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
                        System.out.println("key: " + key + " keyword: " + keyword_);
                        if (key.toLowerCase().contains(keyword_.toLowerCase())) {
                            filteredProducts.addAll(productsDictionary.get("Name").get(key));
                        }
                    }
                }
                System.out.println("filteredProducts: " + filteredProducts);
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
                        if (key.contains(keyword_)) {
                            filteredProducts.addAll(productsDictionary.get("Price").get(key));
                        }
                    }
                }
                break;
            case '4':
                for (String key : productsDictionary.get("Manufacturer").keySet()) {
                    for (String keyword_ : keywords) {
                        if (key.toLowerCase().contains(keyword_.toLowerCase())) {
                            filteredProducts.addAll(productsDictionary.get("Manufacturer").get(key));
                        }
                    }
                }
                break;
        }
        return filteredProducts;
    }
    public List<Product> getList(){
        return this.products;
    }

}
