package com.gitlab.lhqezio.util;

import java.io.IOException;
import java.nio.file.Path;


public class CsvLoader implements DataLoader {
    public String[][] getData(String filename) {
        Path csvPath = CSV_Util.getCsvFilePath(filename);
        try {
            String[][] products = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
