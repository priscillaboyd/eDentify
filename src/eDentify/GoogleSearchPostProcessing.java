package eDentify;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleSearchPostProcessing {
	
	//As part of the package, results file is in the src/eDentify/preprocess folder
	String defaultLocation = System.getProperty("user.dir");
	String filename = defaultLocation + "/src/eDentify/preprocess/" + "results.txt";
	File file = new File(filename); //File object using filename defined
	
	//Method to set new folder and new file
	public void setFilename(String newFolder, String newFile){
		filename = defaultLocation + "/src/eDentify/" + newFolder + "/" + newFile;
	}
	
	//Method to write to file
	public void write(String desc, String text){
		try
		{
		    FileWriter fw = new FileWriter(file,true); //Create new fileWriter object and set to append to file every time ('true' param)
		    fw.write(desc + text + "\n");
		    fw.close(); //Close file after writing
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	//Method to delete contents of a file
	public void erase(){
		try {
			FileWriter fd = new FileWriter(file); //Create new fileWriter object for deletion
			fd.write(""); //Write to ensure connection to file works
			fd.flush(); //Delete contents of file
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	//Method to get size of the results file
	public double getFileSize(File file){
		double fileSize = 0;
		if (file.exists()){
			fileSize = file.length(); //given in bytes
			fileSize = (fileSize / 1024); //transform to kilobytes
		}
		return fileSize;
	}
	
	//Method to set file. If file is greater than 1kb, delete contents
	public void setFile(){
		if (getFileSize(file) > 0){
			erase();
		}
	}
	
}