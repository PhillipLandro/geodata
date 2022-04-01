package application;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args){

        System.out.println("N120°40'30\"E19°12'34\"".matches(GeoPosition.REGEX_WITH_DECIMALS));

        File file = new File("data.json");

        List<GeoPosition> list = new ArrayList();

        list.add(new GeoPosition("N120°40'30\"E19°12'34\""));
        list.add(new GeoPosition("N126°59\\.55555'E29°32\\.85'"));

        GeoDataHandler.write(file, list);

        list = GeoDataHandler.read(file);

        for (GeoPosition pos: list){
            System.out.println(pos);
        }

    }
}
