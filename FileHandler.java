package recomendation_system;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileHandler {

	Boolean writeDataToFile(Object obj, String filename) {
		try {
			
			FileOutputStream fos = new FileOutputStream(filename+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(obj);
//			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
			return true;
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		}
	}
	
	Object readDataFromFile(Object obj,String filename) {
		
		try {
			FileInputStream is = new FileInputStream(filename+".ser");
			ObjectInputStream ois = new ObjectInputStream(is);
			obj = (Object) ois.readObject();
			ois.close();
			is.close();
//			System.out.println(obj.toString());
			return obj;
		} catch (ClassNotFoundException | IOException e) {			
			e.printStackTrace();
			return null;
		}

	}
}
