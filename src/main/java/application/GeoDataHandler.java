package application;

import org.json.JSONObject;
import org.json.JSONString;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GeoDataHandler {

    public GeoDataHandler(){}

    public static List<GeoPosition> read(File file){
        LongitudinalOrientation longitudinalOrientation;
        LatitudinalOrientation latitudinalOrientation;
        List<GeoPosition> positions = new ArrayList<>();
        String zeile = "";
        try(BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            while((zeile = reader.readLine()) != null){
                JSONObject object = new JSONObject(zeile);
                JSONObject latitude = new JSONObject(object.get("latitude").toString());
                JSONObject longitude = new JSONObject(object.get("longitude").toString());
                switch (longitude.get("orientation").toString()){
                    case("EAST"):
                        longitudinalOrientation = LongitudinalOrientation.EAST;
                        break;
                    case("WEST"):
                        longitudinalOrientation = LongitudinalOrientation.WEST;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + longitude.get("orientation"));
                }
                switch (latitude.get("orientation").toString()){
                    case("SOUTH"):
                        latitudinalOrientation = LatitudinalOrientation.SOUTH;
                        break;
                    case("NORTH"):
                        latitudinalOrientation = LatitudinalOrientation.NORTH;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + longitude.get("orientation"));
                }
                positions.add(new GeoPosition(
                        new Latitude(latitudinalOrientation,
                                (short) latitude.getInt("degree"),
                                (byte)latitude.getInt("minute"),
                                (byte)latitude.getInt("second")),
                        new Longitude(longitudinalOrientation,
                                (short) longitude.getInt("degree"),
                                (byte) longitude.getInt("minute"),
                                (byte) longitude.getInt("second")))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static void write(File file, List<GeoPosition> list){
        for(GeoPosition position: list){
            try(FileWriter writer = new FileWriter(file, true)) {
                JSONObject object = new JSONObject(position);
                writer.write(object.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
