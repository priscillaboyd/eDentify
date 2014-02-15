package eDentify;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedEntityExtraction {
	
	//Method to get the named entities
	public static void getNamedEntity(){
		GoogleSearchPostProcessing pp = new GoogleSearchPostProcessing();
		pp.setFilename("clustering", "clusteredResults.txt");
		Path path = Paths.get(pp.filename);
		String line = null;
		
		ArrayList<String> words = new ArrayList<String>();
		
		//Using Windows 1256 in case search returns non-UTF-8 characters
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("windows-1256"))){
	        while ((line = reader.readLine()) != null) {
	        	words.add(line); //add each line to display
	            System.out.println(line);
	            
	            List<List<String>> peopleList = new ArrayList<List<String>>();
	            List<List<String>> orgList = new ArrayList<List<String>>();
	            List<List<String>> locationList = new ArrayList<List<String>>();
	            
	            String headers[] = new String[] {"TITLE:", "SNIPPET:"};
	            
	            //Process Title and Snippet
	            for (int i=0; i<headers.length; i++){
	            	String searchString = headers[i].toString();
	            	
	            	if (line.contains(searchString)){
	            		if (line.contains("<PERSON>")){
		            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
		            		List<String> person = new ArrayList<>();
		            		node.getTag("PEOPLE", "<PERSON>", "</PERSON>", reader, line, peopleList, person);
		            	}
		            		            	
		            	if (line.contains("<ORGANIZATION>")){
		            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
		            		List<String> org = new ArrayList<>();
		            		node.getTag("ORGANISATIONS", "<ORGANIZATION>", "</ORGANIZATION>", reader, line, orgList, org);
		            	}
		            	
		            	if (line.contains("<LOCATION>")){
		            		NamedEntityNodeExpansion node = new NamedEntityNodeExpansion();
		            		List<String> location = new ArrayList<>();
		            		node.getTag("LOCATIONS", "<LOCATION>", "</LOCATION>", reader, line, locationList, location);
		            	}
	            	}
	            	
	            }
	            
	            if (line.contains("URL: ")){
	            	//just copy everything after "URL:"
	            }

	        }   
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString(){
	    return "Pri's test";
	}
	
	public void constructFileForClustering(){
		getNamedEntity();	
	}
	
	public static void main(String[] args){
	    getNamedEntity();
	}
	
}