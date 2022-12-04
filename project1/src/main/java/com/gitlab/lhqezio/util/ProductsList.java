package com.gitlab.lhqezio.util;

import com.gitlab.lhqezio.items.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ProductList Class that contains the majority of business logic.
 * @author Hoang
 */

public class ProductsList {
    private List<Product> products;
    private Map<Product, IntCell> cartQuantityMap = new HashMap<>();
    private HashMap<String, HashMap<String, List<Product>>> productsDictionary;

    public ProductsList(DataLoader dataLoader) {
        this.products = dataLoader.getProductsData();
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

    public Map<Product, IntCell> getCartQuantityMap() {
        return cartQuantityMap;
    }

    public void addToCart(Product product){

        IntCell cell = cartQuantityMap.get(product);
        if (cell == null) {
            cartQuantityMap.put(product, new IntCell(1));
        } else {
            cell.value = cell.value + 1;
        }
    }
    public void removeFromCart(Product product){
        IntCell cell = cartQuantityMap.get(product);
        if (cell.value == 1) {
            cartQuantityMap.remove(product);
        } else {
            cell.value = cell.value - 1;
        }
    }
    public void emptyCart(){
        cartQuantityMap.clear();
    }

    public List<Product> getCart(){
        return new ArrayList<Product>(this.cartQuantityMap.keySet());
    }

    public List<Product> getQuantitySubtractedProducts() {
        List<Product> quantitySubtractedProducts = new ArrayList<>();
        for (Map.Entry<Product, IntCell> entry : cartQuantityMap.entrySet()) {
            Product product_ = entry.getKey();
            int newQuantity = product_.getQuantity() - entry.getValue().value;
            Product productCopy = product_.productConstructor(product_.getId(), product_.getName(), product_.getManufacturer(), product_.getDescription(), product_.getPrice(), product_.getDiscount(), newQuantity);
            quantitySubtractedProducts.add(productCopy);
        }
        return quantitySubtractedProducts;
    }

    public int getCartQuantity(Product product){
        IntCell cell = cartQuantityMap.get(product);
        if (cell == null) {
            return 0;
        } else {
            return cell.value;
        }
    }
    public int getQty(){
        int qty = 0;
        for (Map.Entry<Product, IntCell> entry : cartQuantityMap.entrySet()) {
            qty += entry.getValue().value;
        }
        return qty;
    }
    public double getTotal(){
        double total = 0;
        for (Map.Entry<Product, IntCell> entry : cartQuantityMap.entrySet()) {
            Product product_ = entry.getKey();
            total += (product_.getPrice() - product_.getDiscount()) * entry.getValue().value;
        }
        return total;
    }
}
