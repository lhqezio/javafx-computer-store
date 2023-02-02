package com.gitlab.lhqezio.util;
/*
 * CsvLoader class. used to load csv file.
 * @author Fu Pei
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.UserData;


public class CsvLoader implements DataLoader {

    private List<Product> productListCopy = new ArrayList<>();
    private Path usersPath;
    private Path productsPath;

    public CsvLoader(Path csv_dir) {
        this.usersPath = csv_dir.resolve("users.csv");
        this.productsPath = csv_dir.resolve("products.csv");
    }

    private String[][] getData(Path csvPath) {
        try {
            String[][] products = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<UserData> getUsersData() {
        String[][] allRowsArr = getData(this.usersPath);
        List<UserData> userDataList = new ArrayList<>();
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            userDataList.add(new UserData(rowArr[0], rowArr[1], rowArr[2], rowArr[3]));
        }
        return userDataList;
    }

    public List<Product> getProductsData() {
        String[][] allRowsArr = getData(this.productsPath);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            switch (rowArr[0]) {
                case "Laptop":
                    Laptop laptop_ = new Laptop(i, rowArr[1], rowArr[2], rowArr[3], Double.parseDouble(rowArr[4]), Double.parseDouble(rowArr[5]), Integer.parseInt(rowArr[6]), rowArr[7], rowArr[8], rowArr[9], Integer.parseInt(rowArr[10]), rowArr[11], rowArr[12], Integer.parseInt(rowArr[13]));
                    products.add(laptop_);
                    productListCopy.add(laptop_.createCopy());
                    break;
                case "Computer":
                    Computer computer_ = new Computer(i, rowArr[1], rowArr[2], rowArr[3], Double.parseDouble(rowArr[4]), Double.parseDouble(rowArr[5]), Integer.parseInt(rowArr[6]), rowArr[7], rowArr[8], rowArr[9], Integer.parseInt(rowArr[10]), rowArr[11], rowArr[12]);
                    products.add(computer_);
                    productListCopy.add(computer_.createCopy());
                    break;
            }
        }
        return products;
    }

    public void updateRowsAndSave(List<Product> updatedRows, List<Product> productListToEdit) {
        for (Product updatedProduct : updatedRows) {
            productListToEdit.set(updatedProduct.getId(), updatedProduct);
        }
        updateRowsAndSave(updatedRows);
    }

    public void updateRowsAndSave(List<Product> updatedRows) {
        for (Product updatedProduct : updatedRows) {
            productListCopy.set(updatedProduct.getId(), updatedProduct);
        }
        saveToFile();
    }

    private static String escapeCsv(String field) {
        if (field.contains(",") || field.contains("\"")) {
            String escapedStr = field.replaceAll("\"", "\"\"");
            return "\"" + escapedStr + "\"";
        }
        return field;
    }

    private void saveToFile() {

        StringBuilder sb = new StringBuilder();

        for (Product product_ : this.productListCopy) {

            sb.append(escapeCsv(product_.getCategory()));
            sb.append(",");
            sb.append(escapeCsv(product_.getName()));
            sb.append(",");
            sb.append(escapeCsv(product_.getManufacturer()));
            sb.append(",");
            sb.append(escapeCsv(product_.getDescription()));
            sb.append(",");
            sb.append(product_.getPrice());
            sb.append(",");
            sb.append(product_.getDiscount());
            sb.append(",");
            sb.append(product_.getQuantity());
            sb.append(",");

            sb.append(escapeCsv(((Computer)product_).getProcessor()));
            sb.append(",");
            sb.append(escapeCsv(((Computer)product_).getRam()));
            sb.append(",");
            sb.append(escapeCsv(((Computer)product_).getHardDrive()));
            sb.append(",");
            sb.append(((Computer)product_).getCapacity());
            sb.append(",");
            sb.append(escapeCsv(((Computer)product_).getGraphicsCard()));
            sb.append(",");
            sb.append(escapeCsv(((Computer)product_).getOperatingSystem()));
            sb.append(",");

            switch (product_.getCategory()) {
                case "Laptop":
                sb.append(((Laptop)product_).getBatteryLife());
                break;
                case "Computer":
                break;
            }
            sb.append("\n");
        }

        try (PrintWriter out = new PrintWriter(this.productsPath.toFile())) {
            out.print(sb.toString());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
