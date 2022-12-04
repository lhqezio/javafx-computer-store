package com.gitlab.lhqezio;

/**
 * Unit Test for Item class and its extended classes.
 * @author Hoang
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Item;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;

public class ItemTest {
    private static final double DELTA = 1e-15;

    @Test
    public void laptopObjTest(){
        Laptop laptop = new Laptop(123, "Macbook Air", "Apple", "A laptop", 1000, 0, 10, "Apple M1", "16GB", "NVMe", 1000, "7 Cores", "MacOS Ventura", 15);
        assertEquals(123, laptop.getId());
        assertEquals("Macbook Air", laptop.getName());
        assertEquals("Apple", laptop.getManufacturer());
        assertEquals("A laptop", laptop.getDescription());

        assertEquals(1000, laptop.getPrice(), DELTA);
        assertEquals(0, laptop.getDiscount(), DELTA);
        assertEquals(10, laptop.getQuantity());

        assertEquals("Laptop", laptop.getCategory());

        assertEquals("Apple M1", laptop.getProcessor());
        assertEquals("16GB", laptop.getRam());
        assertEquals("NVMe", laptop.getHardDrive());
        assertEquals(1000, laptop.getCapacity());
        assertEquals("7 Cores", laptop.getGraphicsCard());
        assertEquals("MacOS Ventura", laptop.getOperatingSystem());

        assertEquals(15, laptop.getBatteryLife());
    }

    @Test
    public void computerObjTest(){
        Computer computer = new Computer(123, "Computer", "Apple", "A computer", 1000, 0, 10, "Intel", "16GB", "HDD", 1000, "Nvidia", "Windows");
        assertEquals(123, computer.getId());
        assertEquals("Computer", computer.getName());
        assertEquals("Apple", computer.getManufacturer());
        assertEquals("A computer", computer.getDescription());

        assertEquals(1000, computer.getPrice(), DELTA);
        assertEquals(0, computer.getDiscount(), DELTA);
        assertEquals(10, computer.getQuantity());

        assertEquals("Computer", computer.getCategory());

        assertEquals("Intel", computer.getProcessor());
        assertEquals("16GB", computer.getRam());
        assertEquals("HDD", computer.getHardDrive());
        assertEquals(1000, computer.getCapacity());
        assertEquals("Nvidia", computer.getGraphicsCard());
        assertEquals("Windows", computer.getOperatingSystem());
    }

    @Test
    public void productObjTest(){
        Product product = new Computer(123,"Aspire 5", "Acer", "A computer", 1000, 0, 10, "Intel", "16GB", "HDD", 1000, "Nvidia RTX 3050", "Windows");
        assertEquals(123, product.getId());
        assertEquals("Aspire 5", product.getName());
        assertEquals("Acer", product.getManufacturer());
        assertEquals("A computer", product.getDescription());

        assertEquals(1000, product.getPrice(), DELTA);
        assertEquals(0, product.getDiscount(), DELTA);
        assertEquals(10, product.getQuantity());
    }
    @Test
    public void itemObjTest(){
        Item item = new Computer(123,"Aspire 5", "Acer", "A computer", 1000, 0, 10, "Intel", "16GB", "HDD", 1000, "Nvidia RTX 3050", "Windows");
        assertEquals(123, item.getId());
        assertEquals("Aspire 5", item.getName());
        assertEquals("Acer", item.getManufacturer());
        assertEquals("A computer", item.getDescription());

    }
}
