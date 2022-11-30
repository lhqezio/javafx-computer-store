package com.gitlab.lhqezio.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.gitlab.lhqezio.items.Computer;
import com.gitlab.lhqezio.items.Laptop;
import com.gitlab.lhqezio.items.Product;

public class CSV_Util {


    public static int[] parseCSV_getRowCol(byte[] buf) {
        // get length
        int colCount = 0;

        int c = 0;
        byte character = buf[c];
        outer:
        while (true) {
            switch (character) {
                case '"':
                    c++;
                    character = buf[c];
                    while (true) {
                        switch (character) {
                            case '"':
                                byte nextChar = buf[c + 1];
                                if (nextChar == '"') {
                                    c += 2;
                                    character = buf[c];
                                    continue;
                                } else {
                                    colCount++;
                                    if (nextChar == '\n') {
                                        break outer;
                                    }
                                    c += 2;
                                    character = buf[c];
                                    continue outer;
                                }
                        }
                        c++;
                        character = buf[c];
                    }
                default:
                    outer2:
                    while (true) {
                        switch (character) {
                            case '\n':
                                colCount++;
                                c++;
                                character = buf[c];

                                break outer;
                            case ',':
                                colCount++;
                                c++;
                                character = buf[c];
                                break outer2;
                        }
                        c++;
                        character = buf[c];
                    }
            }
        }

        int linesCount = 2;
        while (c < buf.length) {
            if (buf[c] == '\n') {
                linesCount++;
            }
            c++;
        }
        int trailingNewlines = 2;
        c = buf.length - 3;
        while (buf[c] == '\n') {
            trailingNewlines++;
            c--;
        }
        int rowCount = linesCount - trailingNewlines;
        return new int[]{rowCount, colCount};
    }

    public static String[][] parseCSV_fromRowCol(byte[] buf, int[] rowCol) {
        int rowCount = rowCol[0];
        int colCount = rowCol[1];

        String[][] allRowsArr = new String[rowCount][];
        String[] rowArr = new String[colCount];
        allRowsArr[0] = rowArr;
        int rowsIdx = 1;

        int fieldIdx = 0;

        try {
            int c = 0;
            int character = buf[c];
            int cBak;
            outer:
            while (true) {
                switch (character) {
                    case '"':
                        c++;
                        cBak = c;
                        character = buf[c];
                        while (true) {
                            switch (character) {
                                case '"':
                                    byte nextChar = buf[c + 1];
                                    if (nextChar == '"') {
                                        c += 2;
                                        character = buf[c];
                                        continue;
                                    } else {
                                        String field_ = new String(buf, cBak, c - cBak, "UTF-8");
                                        String escapedField = field_.replace("\"\"", "\"");
                                        rowArr[fieldIdx] = escapedField;
                                        fieldIdx++;

                                        c += 2;
                                        if (nextChar == '\n') {
                                            if (buf[c] == '\n') {
                                                break outer;
                                            } else {
                                                rowArr = new String[colCount];
                                                fieldIdx = 0;
                                                allRowsArr[rowsIdx] = rowArr;
                                                rowsIdx++;
                                            }
                                        }
                                        character = buf[c];
                                        continue outer;
                                    }
                            }
                            c++;
                            character = buf[c];
                        }
                    default:
                        cBak = c;
                        outer2:
                        while (true) {
                            switch (character) {
                                case '\n': {
                                    String field_ = new String(buf, cBak, c - cBak, "UTF-8");
                                    rowArr[fieldIdx] = field_;
                                    fieldIdx++;

                                    c++;
                                    character = buf[c];

                                    if (character == '\n') {
                                        break outer;
                                    }
                                    rowArr = new String[colCount];
                                    fieldIdx = 0;
                                    allRowsArr[rowsIdx] = rowArr;
                                    rowsIdx++;
                                    break outer2;
                                }
                                case ',': {
                                    String field_ = new String(buf, cBak, c - cBak, "UTF-8");
                                    rowArr[fieldIdx] = field_;
                                    fieldIdx++;

                                    c++;
                                    character = buf[c];
                                    break outer2;
                                }
                            }
                            c++;
                            character = buf[c];
                        }
                }
            }
            return allRowsArr;
        } catch (Exception e) {
            System.out.println(e);
            return new String[][]{};
        }
    }

    public static String[][] parseCSV(byte[] buf) {
        return parseCSV_fromRowCol(buf, parseCSV_getRowCol(buf));
    }

    public static byte[] readBytesAdd2Newline(Path path) throws IOException {
        try (SeekableByteChannel sbc = Files.newByteChannel(path)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect((int) (sbc.size()) + 2);
            sbc.read(buffer);
            byte[] arr = new byte[buffer.capacity()];
            // System.out.println(arr.length);
            buffer.position(0);
            buffer.get(arr);
            arr[arr.length - 1] = '\n';
            arr[arr.length - 2] = '\n';
            // System.out.println(new String(arr, StandardCharsets.UTF_8));
            return arr;
        }
    }

    public static byte[] stringBytesAdd2Newline(String str_) {
        try {
            byte[] from = str_.getBytes("UTF-8");
            byte[] arr = new byte[from.length + 2];
            for (int i = 0; i < from.length; i++) {
                arr[i] = from[i];
            }
            arr[arr.length - 1] = '\n';
            arr[arr.length - 2] = '\n';
            return arr;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new byte[0];
        }
    }

    public static List<Product> parseProduct(String[][] allRowsArr) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            switch (rowArr[0]) {
                case "Laptop":
                    products.add(new Laptop(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], rowArr[12], Integer.parseInt(rowArr[13]), Integer.parseInt(rowArr[14])));
                    break;
                case "Computer":
                    products.add(new Computer(rowArr[1], rowArr[2], Double.parseDouble(rowArr[3]), Double.parseDouble(rowArr[4]), Integer.parseInt(rowArr[5]), rowArr[6], rowArr[7], rowArr[8], rowArr[9], rowArr[10], rowArr[11], rowArr[12], Integer.parseInt(rowArr[13])));
                    break;
            }
        }
        return products;
    }
}