import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class trydelete {
	public static void main(String[] args) {
	File myFile = new File("try.txt");
	try {
		FileWriter writer = new FileWriter(myFile);
		writer.close();
		Files.deleteIfExists(Paths.get("try.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
