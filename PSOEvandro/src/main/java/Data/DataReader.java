package Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entity.ImageEntity;

public class DataReader {

    private final int rows = 28;
    private final int cols = 28;

    public List<ImageEntity> readData(String path){

        List<ImageEntity> images = new ArrayList<>();

        try (BufferedReader dataReader = new BufferedReader(new FileReader(path))){

            String line;

            while((line = dataReader.readLine()) != null){
                String[] lineItems = line.split(",");

                double[][] data = new double[rows][cols];
                List<Double> Data = new ArrayList<Double>();
                char label = lineItems[0].charAt(0);

                int i = 1;
                
                for(int row = 0; row < rows; row++){
                    for(int col = 0; col < cols; col++){
                        data[row][col] = (double) Integer.parseInt(lineItems[i]);
                        Data.add((double) Integer.parseInt(lineItems[i]));
                        i++;
                    }
                }

                images.add(new ImageEntity(Data, label));

            }

        } catch (Exception e){
            throw new IllegalArgumentException("File not found " + path);
        }

        return images;

    }

}
