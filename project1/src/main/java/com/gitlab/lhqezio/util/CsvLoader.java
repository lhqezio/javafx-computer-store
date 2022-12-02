package com.gitlab.lhqezio.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;
import com.gitlab.lhqezio.user.UserData;


public class CsvLoader implements DataLoader {

    private Path csv_dir;

    public CsvLoader(Path csv_dir_) {
        this.csv_dir = csv_dir_;
    }

    private String[][] getData(String filename) {

        Path csvPath = this.csv_dir.resolve(filename);
        try {
            String[][] products = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<UserData> getUsersData() {
        String[][] allRowsArr = getData("users.csv");
        List<UserData> userDataList = new ArrayList<>();
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            userDataList.add(new UserData(rowArr[0], rowArr[1], rowArr[2], rowArr[3]));
        }
        return userDataList;
    }

    public List<Product> getProductsData() {
        String[][] allRowsArr = getData("products.csv");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            switch (rowArr[0]) {
                case "Laptop":
                    products.add(new Laptop(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], Integer.parseInt(rowArr[11]), rowArr[12], rowArr[13], Integer.parseInt(rowArr[14])));
                    break;
                case "Computer":
                    products.add(new Computer(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], Integer.parseInt(rowArr[11]), rowArr[12], rowArr[13]));
                    break;
            }
        }
        return products;
    }

}
