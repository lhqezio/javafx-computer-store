package com.gitlab.lhqezio.util;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSV_Util {

    public static String[][] parseCSV(byte[] buf) {
        // get length
        int colCount = 0;

        int c_ = 0;
        byte char_ = buf[c_];
        outer: while (true) {
            switch (char_) {
                case '"':
                    c_++;
                    char_ = buf[c_];
                    while (true) {
                        switch (char_) {
                            case '"':
                                byte nextChar = buf[c_ + 1];
                                if (nextChar == '"') {
                                    c_ += 2;
                                    char_ = buf[c_];
                                    continue;
                                } else {
                                    colCount++;
                                    if (nextChar == '\n') {
                                        break outer;
                                    }
                                    c_ += 2;
                                    char_ = buf[c_];
                                    continue outer;
                                }
                        }
                        c_++;
                        char_ = buf[c_];
                    }
                default:
                    outer2: while (true) {
                        switch (char_) {
                            case '\n':
                                colCount++;
                                c_++;
                                char_ = buf[c_];

                                break outer;
                            case ',':
                                colCount++;
                                c_++;
                                char_ = buf[c_];
                                break outer2;
                        }
                        c_++;
                        char_ = buf[c_];
                    }
            }
        }

        int linesCount = 2;
        while (c_ < buf.length) {
            if (buf[c_] == '\n') {
                linesCount++;
            }
            c_++;
        }
        int trailingNewlines = 2;
        c_ = buf.length - 3;
        while (buf[c_] == '\n') {
            trailingNewlines++;
            c_--;
        }
        int rowCount = linesCount - trailingNewlines;

        // System.out.println(colCount);
        // System.out.println(rowCount);

        String[][] allRowsArr = new String[rowCount][];
        String[] rowArr = new String[colCount];
        allRowsArr[0] = rowArr;
        int rowsIdx = 1;

        int fieldIdx = 0;

        try {
            c_ = 0;
            char_ = buf[c_];
            int cBak;
            outer: while (true) {
                switch (char_) {
                    case '"':
                        c_++;
                        cBak = c_;
                        char_ = buf[c_];
                        while (true) {
                            switch (char_) {
                                case '"':
                                    byte nextChar = buf[c_ + 1];
                                    if (nextChar == '"') {
                                        c_ += 2;
                                        char_ = buf[c_];
                                        continue;
                                    } else {
                                        String field_ = new String(buf, cBak, c_ - cBak, "UTF-8");
                                        String escapedField = field_.replace("\"\"", "\"");
                                        rowArr[fieldIdx] = escapedField;
                                        fieldIdx++;

                                        c_ += 2;
                                        if (nextChar == '\n') {
                                            if (buf[c_] == '\n') {
                                                break outer;
                                            } else {
                                                rowArr = new String[colCount];
                                                fieldIdx = 0;
                                                allRowsArr[rowsIdx] = rowArr;
                                                rowsIdx++;
                                            }
                                        }
                                        char_ = buf[c_];
                                        continue outer;
                                    }
                            }
                            c_++;
                            char_ = buf[c_];
                        }
                    default:
                        cBak = c_;
                        outer2: while (true) {
                            switch (char_) {
                                case '\n': {
                                    String field_ = new String(buf, cBak, c_ - cBak, "UTF-8");
                                    rowArr[fieldIdx] = field_;
                                    fieldIdx++;

                                    c_++;
                                    char_ = buf[c_];

                                    if (char_ == '\n') {
                                        break outer;
                                    }
                                    rowArr = new String[colCount];
                                    fieldIdx = 0;
                                    allRowsArr[rowsIdx] = rowArr;
                                    rowsIdx++;
                                    break outer2;
                                }
                                case ',': {
                                    String field_ = new String(buf, cBak, c_ - cBak, "UTF-8");
                                    rowArr[fieldIdx] = field_;
                                    fieldIdx++;

                                    c_++;
                                    char_ = buf[c_];
                                    break outer2;
                                }
                            }
                            c_++;
                            char_ = buf[c_];
                        }
                }
            }
            return allRowsArr;
        } catch (Exception e) {
            System.out.println(e);
            return new String[][] {};
        }

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
    public static File getGitIgnoreDir() {
        File dir_ = new File(System.getProperty("user.dir"));
        while (!(new File(dir_, ".gitignore")).exists()) {
            dir_ = dir_.getParentFile();
        }
        return dir_;
    }
}