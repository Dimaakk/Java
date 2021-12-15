import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static Map<String, Object> metro = new HashMap<>();                 // Общая коллекция
    public static Map<String, List<String>> stations = new HashMap<>();        // Коллекция станций
    public static Set<String> lineNumbers = new HashSet<>();                   // Коллекция номеров линий
    public static Set<LineToJson> lineSet = new HashSet<>();                   // Коллекция линий


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://clck.ru/MCHxP").maxBodySize(0).get();
        Elements elem = doc.select(".standard.sortable tr");

        elem.stream().filter(tr -> !tr.select("td:nth-child(1) span:nth-child(1)").text().isEmpty())
                .forEach(tr -> lineNumbers.add(tr.select("td:nth-child(1) span:nth-child(1)").text()));

        lineNumbers.forEach(line -> {
            List<String> stationList = new ArrayList<>();

            elem.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)").text().equals(line))
                    .forEach(tr -> {
                        tr.select("td:nth-child(2) small").remove();
                        stationList.add(tr.select("td:nth-child(2)").text());
                    });
            stations.put(line, stationList);

            elem.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)")
                    .text().equals(line)).findFirst().ifPresent(t -> lineSet.add(new LineToJson(
                    line,
                    t.select("td:nth-child(1) span:nth-child(2)").attr("title"),
                    lineColor(t.select("td:nth-child(1)").attr("style")),
                    stationList.size())));
        });

        metro.put("stations", stations);
        metro.put("lines", lineSet);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/metro.json"), metro);


    }
    private static String lineColor(String tdStyle) {
        return tdStyle.isEmpty() ? "неизвестно" : tdStyle.substring(tdStyle.indexOf("#"));
    }

}
