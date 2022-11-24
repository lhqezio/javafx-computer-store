package com.gitlab.lhqezio;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Item;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;

public class ItemTest {
    final double DELTA = 1e-15;
    @Test
    public void computerObjTest(){
        Computer computer = new Computer("Computer", "Apple", 1000, 0, 10, "A computer", "123", "Intel", "16GB", "HDD", "Nvidia", "Windows", 1000);
        assertEquals("Computer", computer.getName());
        assertEquals("Apple", computer.getManufacturer());
        assertEquals(1000, computer.getPrice(), DELTA);
        assertEquals(0, computer.getDiscount(), DELTA);
        assertEquals(10, computer.getQuantity());
        assertEquals("A computer", computer.getDescription());
        assertEquals("123", computer.getID());
        assertEquals("Intel", computer.getProcessor());
        assertEquals("16GB", computer.getRam());
        assertEquals("HDD", computer.getHardDrive());
        assertEquals("Nvidia", computer.getGraphicsCard());
        assertEquals("Windows", computer.getOperatingSystem());
        assertEquals(1000, computer.getCapacity());
        assertEquals("Computer", computer.getCategory());
    }
    @Test
    public void laptopObjTest(){
        Laptop laptop = new Laptop("Macbook Air", "Apple", 1000, 0, 10, "A laptop", "123", "Apple M1", "16GB", "NVMe", "7 Cores", "MacOS Ventura", 1000, 15);
        assertEquals("Macbook Air", laptop.getName());
        assertEquals("Apple", laptop.getManufacturer());
        assertEquals(1000, laptop.getPrice(), DELTA);
        assertEquals(0, laptop.getDiscount(), DELTA);
        assertEquals(10, laptop.getQuantity());
        assertEquals("A laptop", laptop.getDescription());
        assertEquals("123", laptop.getID());
        assertEquals("Apple M1", laptop.getProcessor());
        assertEquals("16GB", laptop.getRam());
        assertEquals("NVMe", laptop.getHardDrive());
        assertEquals("7 Cores", laptop.getGraphicsCard());
        assertEquals("MacOS Ventura", laptop.getOperatingSystem());
        assertEquals(1000, laptop.getCapacity());  
        assertEquals(15, laptop.getBatteryLife());
        assertEquals("Laptop", laptop.getCategory());
    }
    @Test
    public void productObjTest(){
        Product product = new Computer("Aspire 5", "Acer", 1000, 0, 10, "A computer", "123", "Intel", "16GB", "HDD", "Nvidia RTX 3050", "Windows", 1000);
        assertEquals("Aspire 5", product.getName());
        assertEquals("Acer", product.getManufacturer());
        assertEquals(1000, product.getPrice(), DELTA);
        assertEquals(0, product.getDiscount(), DELTA);
        assertEquals(10, product.getQuantity());
        assertEquals("A computer", product.getDescription());
        assertEquals("123", product.getID());
    }
    @Test 
    public void itemObjTest(){
        Item item = new Computer("Aspire 5", "Acer", 1000, 0, 10, "A computer", "123", "Intel", "16GB", "HDD", "Nvidia RTX 3050", "Windows", 1000);
        assertEquals("Aspire 5", item.getName());
        assertEquals("Acer", item.getManufacturer());
        assertEquals("123", item.getID());
        assertEquals("A computer", item.getDescription());

    }
}
