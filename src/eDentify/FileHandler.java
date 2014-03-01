package eDentify;

import java.io.*;

public class FileHandler {
	
	//As part of the package, results file is in the src/eDentify/preprocess folder
	String defaultLocation = System.getProperty("user.dir");
	String preProcessFolder = "/src/eDentify/preprocess/"; //PreProcess folder is being used to contain all files/data that is used before clustering
	String clusteringFolder = "/src/eDentify/clustering/"; //Clustering folder is being used to contain all files/data that is used for clustering
	
	//Define filenames for text files used
	String filename = defaultLocation + preProcessFolder + "results.txt";
	String filenameResultsWithNER = defaultLocation + preProcessFolder + "resultsWithNER.txt";
	String filenamePreClusteredResults = defaultLocation + clusteringFolder + "preClusteredResults.txt";
	String filenameClusteredResults = defaultLocation + clusteringFolder + "clusteredResults.txt";
	
	//Create objects for each of the files being handled
	File file = new File(filename); //File object using filename defined
	File fileResultsWithNER = new File(filenameResultsWithNER);
	File filePreClusteredResults = new File(filenamePreClusteredResults);
	File fileClusteredResults = new File (filenameClusteredResults);
	
	//Method to set new folder and new file
	public void setFilename(String newFolder, String newFile){
		filename = defaultLocation + "/src/eDentify/" + newFolder + "/" + newFile;
	}
	
	//Method to write to file
	//TODO: Remove the boolean spacing
	public void writeToFile(String desc, String text, File file, boolean spacing){
		try
		{
		    FileWriter fw = new FileWriter(file,true); //Create new fileWriter object and set to append to file every time ('true' param)
		    //String fullText = text.replace("\n", "").replace("\r", ""); //remove line break
		    fw.write(desc + text);
		    fw.write("\n");
		    //else text = text + text.replace("\n", "").replace("\r", "");; //remove line break
		    fw.close(); //Close file after writing
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	//Method to delete contents of a file (used for results.txt, each time the program runs)
	public void erase(File file){
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
	public void setFile(File file){
		if (getFileSize(file) > 0){
			erase(file);
		}
	}
	
	//Method to get the number of lines in a file
	public void writeCSVEntry(String text){
		try
		{
			file = filePreClusteredResults;
		    FileWriter fw = new FileWriter(file,true); //Create new fileWriter object and set to append to file every time ('true' param)
		    fw.append(text);
		    fw.close(); //Close file after writing
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	
	public int getFileNumberLines(File file){
		int lineNumber = 0;
		try {
			FileReader fr = new FileReader(file);
			LineNumberReader ln = new LineNumberReader(fr);
			while (ln.readLine() != null){
				lineNumber++;
			}
			
			ln.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineNumber;
		
	}
	
}