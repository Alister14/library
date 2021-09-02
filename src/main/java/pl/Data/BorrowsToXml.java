package pl.Data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class BorrowsToXml {
    private final XStream xStream;

    public BorrowsToXml() {
        xStream = new XStream(new StaxDriver());
    }

    public void saveTextToFile(String pathName) {
        String borrows = xStream.toXML(DatabaseHandling.getBorrows());

        try {
            //File file = new File(pathName);

            BufferedReader reader = new BufferedReader(new StringReader(borrows));
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, true));

            while ((borrows = reader.readLine()) != null) {
                writer.write(borrows + System.getProperty("line.separator"));
            }
            writer.close();
        } catch (FileNotFoundException io) {
            System.out.println(io.getMessage());
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
