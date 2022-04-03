package application;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args){

        File file = new File("data.json");

        List<GeoPosition> list = new ArrayList();

        list.add(new GeoPosition("N90°40'30\"E19°12'34\""));
        list.add(new GeoPosition("S90°35.5'W29°33.8'"));

        GeoDataHandler.write(file, list);

        list = GeoDataHandler.read(file);

        for (GeoPosition pos: list){
            System.out.println(pos);
        }

    }
}
