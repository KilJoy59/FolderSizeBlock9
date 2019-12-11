import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = reader.readLine();
            getSize(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void getSize(String file) throws IOException {
       Long size = Files.walk(new File(file).toPath())
                .map(Path::toFile)
                .filter(File::isFile)
                .mapToLong(File::length).sum();
        System.out.println(humanReadableByteCount(size,true));
    }
    private static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
