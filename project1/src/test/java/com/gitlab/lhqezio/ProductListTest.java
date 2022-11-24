package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.util.CSV_Util;
import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.ProductsList;

public class ProductListTest {
    final double DELTA = 1e-15;
    @Test
    public void initTest(){
        CsvLoader c = new CsvLoader();
        ProductsList prods = new ProductsList();
        String[][] expectedStr = c.getData("products.csv");
        List<Product> p = CSV_Util.parseProduct(expectedStr);
        List<Product> test = prods.getList();
       for(int i = 0;i<p.size();i++){
        Product p1 = p.get(i);
        Product p2 = test.get(i);
        assertEquals(p1.getName(),p2.getName());
        assertEquals(p1.getManufacturer(),p2.getManufacturer());
        assertEquals(p1.getPrice(),p2.getPrice(),DELTA);
        assertEquals(p1.getDiscount(),p2.getDiscount(),DELTA);
        assertEquals(p1.getQuantity(),p2.getQuantity());
        assertEquals(p1.getDescription(),p2.getDescription());
        assertEquals(p1.getID(),p2.getID());
       }
    }
    @Test
    public void searchByNameTest(){
        ProductsList prods = new ProductsList();
        List<Product> result =  prods.filterBy('1', "iMac");
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getName(),"iMac Pro");
        assertEquals("Apple", result.get(0).getManufacturer());
    }
    @Test
    public void searchByManufacturerTest(){
        ProductsList prods = new ProductsList();
        List<Product> result =  prods.filterBy('4', "Apple");
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getName(),"iMac Pro");
        assertEquals("Apple", result.get(0).getManufacturer());
    }
    @Test
    public void searchByCategoryTest(){
        ProductsList prods = new ProductsList();
        List<Product> result =  prods.filterBy('2', "Computer");
        assertEquals(result.size(),4);
        assertEquals(result.get(0).getName(),"Alienware X51");
        assertEquals("Dell", result.get(0).getManufacturer());
    }
    @Test
    public void searchByPriceTest(){
        ProductsList prods = new ProductsList();
        List<Product> result =  prods.filterBy('3', "200");
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getName(),"iMac Pro");
        assertEquals("Apple", result.get(0).getManufacturer());
    }
}
