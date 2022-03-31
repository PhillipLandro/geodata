package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args){

        File file = new File("data.txt");

        List<GeoPosition> list = new ArrayList();

        list.add(new GeoPosition("N120°45\\.50'E19°12\\.65'"));

//        GeoDataHandler.write(file, list);
//
//        list = GeoDataHandler.read(file);

        for (GeoPosition pos :list) {
            System.out.println(pos);
        }

    }
}
