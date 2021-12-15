

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
        String url = "https://www.mirea.ru";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("[src]");

        for (Element src : media) {
            if (src.normalName().equals("img")) {
                print(" * %s: <%s> ",
                        src.tagName(), src.attr("abs:src"));

                //Open a URL Stream
                String imageLocation = src.attr("abs:src");
                String outputFolder = "./src/imgs/";
                String[] location_splitted = imageLocation.split("/");
                String name = location_splitted[location_splitted.length - 1];

                Connection.Response resultImageResponse = Jsoup.connect(imageLocation)
                        .ignoreContentType(true).execute();

                FileOutputStream out = (new FileOutputStream(new java.io.File(outputFolder + name)));
                out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
                out.close();
            }
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}