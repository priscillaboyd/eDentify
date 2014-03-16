package eDentify;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.carrot2.matrix.factorization.IterationNumberGuesser;

public class NamedEntityExtraction {

	//Method to get the named entities
	public void extractNamedEntity(){
		
		FileHandler fw = new FileHandler();
		File outputFile = fw.filePreClusteredResults;
		Path path = Paths.get(fw.filenameResultsWithNER); //inputFile
		String line = null;
		
		ArrayList<String> words = new ArrayList<String>(); //list of words in the file
		
		fw.erase(outputFile); //Method to erase file in case it's already populated from last run
		
		//Using Windows 1256 in case search returns non-UTF-8 characters
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("windows-1256"))){
	        while ((line = reader.readLine()) != null) {
	        	words.add(line); //add each line to display
	            System.out.println(line);
	            
	            String headers[] = new String[] {"URL:", "TITLE:", "SNIPPET:"}; //Create new array to hold any possible labels
	            ArrayList<String> record = new ArrayList<String>(); //Create new list to keep all records

	            for (int i=0; i<headers.length; i++){
	            	String searchString = headers[i].toString();
	            	
	            	//Process line that contains one of the search strings
	            	if (line.contains(searchString)){
	            		
	            		//Special processing for TITLE and SNIPPET
	            		if (searchString == headers[0] || searchString == headers[1] || searchString == headers[2]){

		            		ArrayList<String> mainList = new ArrayList<String>(); //Create main list
		            		ArrayList<String> subitem = new ArrayList<String>(); //Create list for sub-items
		            		
		            		//If line contains URL, copy everything
		            		if (line.contains("URL:")){
			            		fw.writeCSVEntry(line);
		            		}
		            		
		            		/*if (line.contains("<PERSON>")){
			            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();            		
			            		node.getTag("PEOPLE", "<PERSON>", "</PERSON>", reader, line, mainList, subitem);
		            		}*/
			            		            	
			            	if (line.contains("<ORGANIZATION>")){
			            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
			            		node.getTag("ORGANISATIONS", "<ORGANIZATION>", "</ORGANIZATION>", reader, line, mainList, subitem);
			            	}
			            	
			            	if (line.contains("<LOCATION>")){
			            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
			            		node.getTag("LOCATIONS", "<LOCATION>", "</LOCATION>", reader, line, mainList, subitem);
			            	}
			            	
			            	/*if (line.contains("<MISC>")){
			            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
			            		node.getTag("MISC", "<MISC>", "</MISC>", reader, line, mainList, subitem);
			            	}*/
			            	
			            	//Add ';' between named entities for all entities
			            	for (int j=0; j<mainList.size(); j++){
			            		String text = mainList.get(j).toString() + ";";
			            		record.add(text);
			        		}
			            	
			            	//If last one, then jump a line - else, add comma separator
			            	if (searchString == headers[2]){
			            		record.add("\n");
			            	} else record.add(",");

			            	//Write everything to file in CSV style
			            	for (int k=0; k<record.size(); k++){
			            		fw.writeCSVEntry(record.get(k).toString());
			            	}
			            	
			            	System.out.println("");
	            			
	            		}
	            	}
	            	
	            }

	        }   
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}