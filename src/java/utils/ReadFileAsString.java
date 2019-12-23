/* The code reads the text file and 
	returns a String

*/

package java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileAsString {
	public static String read(String file_path) {
		try {
			File myFile = new File(file_path);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader bReader = new BufferedReader(fileReader);

			String line = null;
			String full_text = "";

			while((line = bReader.readLine()) != null) {
				full_text += line + "\n";
			}
			bReader.close();
			return full_text;
		} catch (Exception ex) {
			ex.printStackTrace();
			String exc = null;
			return exc;
		}
	}
}