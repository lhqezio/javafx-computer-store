package com.gitlab.lhqezio;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.Test;

import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.ProductsList;
import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;


public class CsvCheckoutTest {
    private static final double DELTA = 1e-15;

    @Test
    public void csvSavingTest() throws IOException {

        Path checkout_test_path = Paths.get("checkout_test");

        Path products_before_path = checkout_test_path.resolve("products_before.csv");
        Path products_path = checkout_test_path.resolve("products.csv");
        Path products_expected_path = checkout_test_path.resolve("products_expected.csv");

        Files.copy(products_before_path, products_path, StandardCopyOption.REPLACE_EXISTING);

        // this test is meant for CsvLoader only, do not try to copy this code elsewhere
        CsvLoader dataLoader = new CsvLoader(checkout_test_path);
        ProductsList pl = new ProductsList(dataLoader);
        List<Product> listOfProducts1 = pl.getList();
        for (int i = 0; i < 5; i++) {
            pl.addToCart(listOfProducts1.get(0));
        }
        pl.addToCart(listOfProducts1.get(3));
        pl.addToCart(listOfProducts1.get(4));

        dataLoader.updateRowsAndSave(pl.getQuantitySubtractedProducts(), pl.getList());
        // need to check that after saving it still works


        // compare both the objects and the string to final
        // products_expected.csv
        String productsCsvStr = Files.readString(products_path);
        String productsExpectedCsvStr = Files.readString(products_expected_path);

        assertEquals(productsCsvStr, productsExpectedCsvStr);

        List<Product> listOfProducts2 = dataLoader.getProductsData();


        assertEquals(listOfProducts1.size(), listOfProducts2.size());

        for (int i = 0; i < listOfProducts2.size(); i++) {

            Product got = listOfProducts2.get(i);
            Product expected = listOfProducts1.get(i);

            assertEquals(got.getCategory(), expected.getCategory());

            //I'm not gonna write and add an equals method to the Object
            assertEquals(got.getId(), expected.getId());
            assertEquals(got.getName(), expected.getName());
            assertEquals(got.getManufacturer(), expected.getManufacturer());
            assertEquals(got.getDescription(), expected.getDescription());
            assertEquals(got.getPrice(), expected.getPrice(), DELTA);
            assertEquals(got.getDiscount(), expected.getDiscount(), DELTA);
            assertEquals(got.getQuantity(), expected.getQuantity());
            assertEquals(((Computer)got).getProcessor(), ((Computer)expected).getProcessor());
            assertEquals(((Computer)got).getRam(), ((Computer)expected).getRam());
            assertEquals(((Computer)got).getHardDrive(), ((Computer)expected).getHardDrive());
            assertEquals(((Computer)got).getCapacity(), ((Computer)expected).getCapacity());
            assertEquals(((Computer)got).getGraphicsCard(), ((Computer)expected).getGraphicsCard());
            assertEquals(((Computer)got).getOperatingSystem(), ((Computer)expected).getOperatingSystem());
            if (got.getCategory() == "Laptop") {
                assertEquals(((Laptop)got).getBatteryLife(), ((Laptop)expected).getBatteryLife());
            }

        }

    }
}
