package Bank;

import java.io.File;
import java.io.FileWriter;

public class file {
	public static void main(String args[]) {
	      File file = new File("Hello1.txt");
	      
	      // creates the file
	      try
	      {
	      file.createNewFile();
	      
	      // creates a FileWriter Object
	      FileWriter writer = new FileWriter(file); 
	      
	      // Writes the content to the file
	      writer.write("This\n is\n an\n example\n"); 
	      writer.flush();
	      writer.close();
	      }
	      catch(Exception e)
	      {
	    	  e.getStackTrace();
	      }
	}		

}
