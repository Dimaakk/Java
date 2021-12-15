
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.logging.Logger;
//import org.apache.commons.io.FileUtils;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void loggingMethod(String msg)
    {
        File f = new File("./logs");
        try{
            if(f.mkdir()) {
                System.out.println("Directory Created");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            FileOutputStream file = new FileOutputStream("./logs/log.txt", true);
            TeePrintStream tee = new TeePrintStream(file, System.err);
            System.setErr(tee);
            log.info(msg);
        }
        catch (Exception ignored) {
        }
    }

    private static void copyDir(String sourceDirName, String targetSourceDir) throws IOException {
        File folder = new File(sourceDirName);

        File[] listOfFiles = folder.listFiles();

        Path destDir = Paths.get(targetSourceDir);
        if (listOfFiles != null)
            for (File file : listOfFiles)
                Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);

    }

    public static void main(String[] args) throws IOException {

        GetFolderSize fz = new GetFolderSize();
        String folder = "./";
        String size = "Folder size = " + String.valueOf(fz.getFileSize(new File(folder))) + " bytes";
        loggingMethod(size);

        System.out.println("Path to folder that wil be copied");
        Scanner in = new Scanner(System.in);
        String sourcedirname = in.nextLine();
        System.out.println("Path to folder where it wil be copied");
        String targetdirname = in.nextLine();

//        sourcedirname = "./logs";
//        targetdirname = "./src";
        copyDir(sourcedirname, targetdirname);

    }
}
