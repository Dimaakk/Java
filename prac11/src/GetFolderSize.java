
import java.io.File;

public class GetFolderSize {

    int totalFolder = 0;
    int totalFile = 0;

    public static void main(String args[]) {
        String folder = "./";
        try {
            GetFolderSize size = new GetFolderSize();
            long fileSizeByte = size.getFileSize(new File(folder));
            System.out.println("Folder Size: " + fileSizeByte + " Bytes");
            System.out.println("Total Number of Folders: "
                    + size.getTotalFolder());
            System.out.println("Total Number of Files: " + size.getTotalFile());
        } catch (Exception e) {}
    }

    public long getFileSize(File folder) {
        totalFolder++;
        System.out.println("Folder: " + folder.getName());
        long foldersize = 0;
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
            } else {
                totalFile++;
                foldersize += filelist[i].length();
            }
        }
        return foldersize;
    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }
}
