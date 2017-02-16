package com.forrestdale;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        try {

            CSVReader reader = new CSVReader(new FileReader(new String("./out/production/WeatherPredictor/com/forrestdale/weatherData.csv")));

            String[] row = reader.readNext();
            while (row != null) {
                for (String cell : row) {
                    System.out.print(cell);
                }
                System.out.println("\n");
                row = reader.readNext();
            }
        }
        catch (IOException ex) {
            System.out.println("File not found: " + ex.getMessage());
            System.out.println(new java.io.File( "." ).getCanonicalPath());;
        }

    }
}
