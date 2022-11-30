package com.gitlab.lhqezio.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CsvLoader implements DataLoader {
    public String[][] getData(String filename) {
        Path csvPath = Paths.get("csv_files/" + filename);
        try {
            String[][] products = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
