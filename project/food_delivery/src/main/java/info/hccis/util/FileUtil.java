package info.hccis.util;

import info.hccis.squash.util.CisUtility;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Business functionality related to squash skills.
 *
 * @author bjmaclean
 * @since 20231019+
 */
public class FileUtil {

    private static Path relative;

    public static void writeToFile(String reportName, ArrayList theObjects) {

        //Issue#5 Use 24 hour time (HH) so 3:37 PM shows as 1537 like the sprint example.
        String datetime = CisUtility.getCurrentDate("yyyyMMddHHmmss");
        String fileName = reportName.replaceAll(" ", "");
        relative = Paths.get("c:/cis2232/" + fileName+datetime+".txt");

        try {
            //Issue#5 Create the c:/cis2232 folder first if it does not exist yet.
            Files.createDirectories(relative.getParent());
            Files.createFile(relative);
            System.out.println("BJM writeToFile Created the file. " + relative);
        } catch (IOException ex) {
            System.out.println("BJM writeToFile Could not create the files." + ex.getMessage());
            ex.getStackTrace();
        } catch (Exception e) {
            System.out.println("BJM writeToFile Exception was caught: " + e.getMessage());
        }

        writeToFile(reportName+" "+datetime);

        if(theObjects == null ||theObjects.isEmpty()){
            writeToFile("no data found");
        }

        for (Object current : theObjects) {
            writeToFile(current.toString());
        }
    }

    public static void writeToFile(String line) {
        try {
            FileWriter myWriter = new FileWriter(relative.toString(), true);
            myWriter.write(line + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}