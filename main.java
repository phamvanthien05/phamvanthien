import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
	 public static void main(String[] args) {
	        try{

	        	
	        	File f = new File("src/Java/data.txt");
	        	FileInputStream read = new FileInputStream(f);
	        	int i = 0;
	        	while((i = read.read()) != -1) {
	        		System.out.print((char)i);
	        	}
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
}