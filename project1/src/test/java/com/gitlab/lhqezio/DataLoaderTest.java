package com.gitlab.lhqezio;
/**
 * Unit test for DataLoader.
 * @author Hoang
 */



import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.DataLoader;

public class DataLoaderTest {
    private static final double DELTA = 1e-15;

    @Test
    public void CsvLoaderTest() {
        DataLoader dataLoader = new CsvLoader(Paths.get("test_csv_files"));
        List<Product> products = dataLoader.getProductsData();

        Computer firstProduct = (Computer)products.get(0);
        Computer expected = new Computer(0,"iMac Pro","Apple","Thin And Light",2000,500,10,"Intel Core i7","16GB","NVMe",1000,"Nvidia GTX 1050","Windows 10");

        //I'm not gonna write add an equals method to the Object
        assertEquals(firstProduct.getId(), expected.getId());
        assertEquals(firstProduct.getName(), expected.getName());
        assertEquals(firstProduct.getManufacturer(), expected.getManufacturer());
        assertEquals(firstProduct.getDescription(), expected.getDescription());
        assertEquals(firstProduct.getPrice(), expected.getPrice(), DELTA);
        assertEquals(firstProduct.getDiscount(), expected.getDiscount(), DELTA);
        assertEquals(firstProduct.getQuantity(), expected.getQuantity());
        assertEquals(firstProduct.getProcessor(), expected.getProcessor());
        assertEquals(firstProduct.getRam(), expected.getRam());
        assertEquals(firstProduct.getHardDrive(), expected.getHardDrive());
        assertEquals(firstProduct.getCapacity(), expected.getCapacity());
        assertEquals(firstProduct.getGraphicsCard(), expected.getGraphicsCard());
        assertEquals(firstProduct.getOperatingSystem(), expected.getOperatingSystem());
    }
}
