import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> load() { //give a list of task strings
        File dataFile = new File(filePath);
        if (!dataFile.exists()) { //does not yet exist, then create
            dataFile.getParentFile().mkdir(); //make the data folder if does not yet exist
            try {
                if (dataFile.createNewFile()) { //boolean, attempt to create file
                    System.out.print("Data file Created\n");
                } else {
                    System.out.print("Unable to create data file\n");
                }
            } catch (IOException e) {
                System.out.print("Unable to create data file\n");
            }
        }
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Unable to read File\n");
        }
        return List.of(); //return empty list
    }

    public void markLine(int idx) {
        FileWrite.markLine(this.filePath, true, idx);
    }

    public void unmarkLine(int idx) {
        FileWrite.markLine(this.filePath, false,idx);
    }

    public void deleteLine(int idx) {
        FileWrite.deleteLine(this.filePath, idx);
    }

    public void addLine(String line) {
        FileWrite.addLine(this.filePath, line);
    }

}
