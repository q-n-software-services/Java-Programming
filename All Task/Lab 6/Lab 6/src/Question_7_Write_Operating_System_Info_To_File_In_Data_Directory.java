import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Question_7_Write_Operating_System_Info_To_File_In_Data_Directory {

    public static void main(String[] args) {
        writeOSName();
    }

    public static void writeOSName() {
        try {
            // Get the data directory of the project
            File dataDir = new File("data");
            // Create the data directory if it doesn't exist
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            // Create the os.txt file in the data directory
            File osFile = new File(dataDir, "os.txt");

            // Get the operating system name
            String osName = System.getProperty("os.name");

            // Write the operating system name to the os.txt file
            FileWriter writer = new FileWriter(osFile);
            writer.write(osName);
            writer.close();
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
}
