package com.forrestdale.utils;

/**
 * Created by forrestdale on 2/16/17.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelLoader {
    private String mFilename = "";
    private boolean mHeader = false;

    public ModelLoader(String filename, boolean header) {
        mFilename = new String(filename);
        mHeader = header;
    }



    protected List<String[]> getRows() {
        List<String[]> result = new ArrayList<String[]>();
        try {
            Scanner file = new Scanner(new FileReader(mFilename));

            while (file.hasNext()) {
                if (!mHeader) {
                    result.add(file.nextLine().split(","));
                }
                else {
                    mHeader = false;
                    file.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not located.");
        }

        return result;
    }
}
