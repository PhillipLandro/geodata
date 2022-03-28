package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GeoDataHandler {

    public GeoDataHandler(){}

    public static List<GeoPosition> read(File file){
        List<GeoPosition> positions = new ArrayList<>();
        String zeile = "";
        try(BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            while((zeile = reader.readLine()) != null){
                positions.add(new GeoPosition(zeile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(File file, List<GeoPosition> list){

    }

}
