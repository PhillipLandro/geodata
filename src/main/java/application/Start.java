package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args){

        File file = new File("data.txt");

        List<GeoPosition> list = new ArrayList();

        list.add(new GeoPosition("N50°23'34\"E127°33'23\""));
        list.add(new GeoPosition("S78°58'37\"E90°42'12\""));

        //GeoDataHandler.write(file, list);

        //list = GeoDataHandler.read(file);

        for (GeoPosition pos :list) {
            System.out.println(pos);
        }

    }
}
