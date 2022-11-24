package com.gitlab.lhqezio;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.DataLoader;

public class DataLoaderTest {
    @Test 
    public void CsvLoaderTest() {
        DataLoader dataLoader = new CsvLoader();
        String[][] products = dataLoader.getData("test_product.csv");
        String[][] expected = new String[][]{{
            "Computer","iMac Pro","Apple","2000","5","10","Thin And Light","Leno01","Intel Core i7","16GB","NVMe","Nvidia GTX 1050","Windows 10","1000"
        }};
        assertArrayEquals(products, expected);
    }
}
