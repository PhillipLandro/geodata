package application;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GeoDataHandler {

    public GeoDataHandler(){}

    public static List<GeoPosition> read(File file){
        List<GeoPosition> positions = new ArrayList<>();
        String zeile = "";
        try(BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            if((zeile = reader.readLine()) != null){
                JSONArray json = new JSONArray(zeile);
                for (Object j: json) {
                    positions.add(jsonToGeoPosition(new JSONObject(j.toString())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static void write(File file, List<GeoPosition> list){
            try(FileWriter writer = new FileWriter(file)) {
                Gson gson = new Gson();
                String s = gson.toJson(list);
                writer.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static GeoPosition jsonToGeoPosition(JSONObject json){

        JSONObject latitude = new JSONObject(json.get("latitude").toString());
        JSONObject longitude = new JSONObject(json.get("longitude").toString());

        LatitudinalOrientation latitudinalOrientation = latitude.get("orientation").toString().equals("NORTH") ? LatitudinalOrientation.NORTH
                                                        : latitude.get("orientation").toString().equals("SOUTH") ? LatitudinalOrientation.SOUTH
                                                        : null;

        LongitudinalOrientation longitudinalOrientation = longitude.get("orientation").toString().equals("EAST") ? LongitudinalOrientation.EAST
                                                        : longitude.get("orientation").toString().equals("WEST") ? LongitudinalOrientation.WEST
                                                        : null;

        return new GeoPosition(
                new Latitude(latitudinalOrientation,
                        (short) latitude.getInt("degree"),
                        (byte)latitude.getInt("minute"),
                        (byte)latitude.getInt("second")),
                new Longitude(longitudinalOrientation,
                        (short) longitude.getInt("degree"),
                        (byte) longitude.getInt("minute"),
                        (byte) longitude.getInt("second")));
    }



}
