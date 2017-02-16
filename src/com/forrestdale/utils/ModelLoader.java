package com.forrestdale.utils;

/**
 * Created by forrestdale on 2/16/17.
 */

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelLoader {
    private String mFilename = "";

    public ModelLoader(String filename) {
        mFilename = new String(filename);
    }

    protected List<String[]> getRows() {
        List<String[]> result = new ArrayList<String[]>();
        try {
            CSVReader reader = new CSVReader(new FileReader(mFilename));
            result = reader.readAll();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not located.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
