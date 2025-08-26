import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWrite {

    public static void addLine(String filePath, String line) { //type, mark, desc, by/from, to
        try {
            FileWriter fw = new FileWriter(filePath, true); //append mode, strictly to add new lines
            fw.write(line + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.print("Unable to write data");
        }
    }

    public static void markLine(String filePath, boolean mark, int idx) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(idx);

            String[] temp = line.split("`");
            temp[1] = mark ? "M" : "UM";
            String changedLine = String.join("`", temp);

            lines.set(idx, changedLine);

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.print("Unable to change data");
        }
    }

    public static void deleteLine(String filePath, int idx) { //need to delete the whole file and rewrite everything ;-;
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            lines.remove(idx);
            Files.write(path, lines);
        } catch (IOException e) {
            System.out.print("Unable to change data");
        }
    }
}
