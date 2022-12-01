package com.gitlab.lhqezio.util;

import java.io.IOException;
import java.nio.file.Path;


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

    public String[][] getUsersData() {
        return getData("users.csv");
    }

    public String[][] getProductsData() {
        return getData("products.csv");
    }

}
