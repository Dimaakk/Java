import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;



public class PrintStation {

    private static String dataFile = "data/metro.json";

    public static void main(String[] args) throws ParseException {


        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");

        stationsObject.keySet().stream().sorted(Comparator.comparingInt(s -> Integer.parseInt(((String)s)
                .replaceAll("[^\\d]", "")))).forEach(lineNumberObject ->
        {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            int stationsCount = stationsArray.size();
            System.out.println("Номер линиии " + lineNumberObject + " - колличество станций : " + stationsCount);
        });

    }
    public static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
