package project0.systems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer {

	private static String fileName = "DealershipSystem.txt";
	
	public static void serialize(Object ob) {
		try {
			FileOutputStream fs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
