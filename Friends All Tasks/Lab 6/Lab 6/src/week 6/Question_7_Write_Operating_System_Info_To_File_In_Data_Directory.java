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
            File infoFile = new File("data");
            // Create the data directory if it doesn't exist
            if (!infoFile.exists()) {
                infoFile.mkdir();
            }

            // Create the os.txt file in the data directory
            File osFile = new File(infoFile, "myData.txt");

            // Get the operating system name
            String nameOfOS = System.getProperty("os.name");

            // Write the operating system name to the os.txt file
            FileWriter fileHandler = new FileWriter(osFile);
            fileHandler.write(nameOfOS);
            fileHandler.close();
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
}
