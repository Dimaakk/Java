
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int threads_amount = Runtime.getRuntime().availableProcessors();

        String srcFolder = "./src/images";
        String dstFolder = "./src/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int sz = files.length / threads_amount;

        ArrayList<File[]> to_threads = new ArrayList<>();
        int counter = 0;
        ArrayList<File> tmp = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (counter == sz) {
                File tmp_1[]=tmp.toArray(new File[tmp.size()]);
                to_threads.add(tmp_1);
                tmp = new ArrayList<>();
                counter = 0;
            }
            tmp.add(files[i]);
            counter++;
        }
        if (counter == sz) {
            File tmp_1[]=tmp.toArray(new File[tmp.size()]);
            to_threads.add(tmp_1);
        }
        try
        {
            if (!Files.exists(Paths.get(dstFolder)))
                Files.createDirectories(Paths.get(dstFolder));

            for (int i = 0; i < threads_amount; i++) {
                MyThread thread = new MyThread(dstFolder, to_threads.get(i));
                thread.start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
