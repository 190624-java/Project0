package project0.systems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	private static String path = "src/main/resources/";
	
	public static void serialize(Object ob) {
		String classNameFile = ob.getClass().toString().split("\\.")[2] + ".txt"; // makes string class name for object 
		try {
			FileOutputStream fs = new FileOutputStream(path + classNameFile);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DealershipSystem deserialize() {
		DealershipSystem ds = new DealershipSystem();
		String classNameFile = ds.getClass().toString().split("\\.")[2] + ".txt";
		try {
			FileInputStream fs = new FileInputStream(path + classNameFile);
			ObjectInputStream is = new ObjectInputStream(fs);
			ds = (DealershipSystem) is.readObject();
		} catch(Exception e) {
					System.out.println("*First Time Running*");
		} 
		return ds;
	}

}
