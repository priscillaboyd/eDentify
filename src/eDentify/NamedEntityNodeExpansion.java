package eDentify;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class defines the expansion of a named entity node within search results
public class NamedEntityNodeExpansion {
	String line = null;
	
	//Method to find the tags defined by the NER processing
	public void getTag(String tagLabel, String tagStart, String tagEnd, BufferedReader reader, String line, ArrayList<String> mainList, ArrayList<String> items){
		String regexString = Pattern.quote(tagStart) + "(.*?)" + Pattern.quote(tagEnd); //regex string to help getting what is in between each tag
		Pattern pattern = Pattern.compile(regexString);
		Matcher matcher = pattern.matcher(line);
		getMatch(tagLabel, matcher, mainList, items);
	}
	
	//Method to get the values from the tags that match the search
	public void getMatch(String tagLabel, Matcher matcher, ArrayList<String> mainList, ArrayList<String> items){
		//fw.setFile(file); //delete contents of file
		System.out.print(tagLabel + " FOUND: ");
		
		while (matcher.find()) {
			String item = matcher.group(1); // (.*?) gets group 1
			items = new ArrayList<String>(); //ignore this
			mainList.add(item);
    		System.out.print(item + "; ");
		};
	}
	
	
	public String toString() {
		return "hi";
	}
	
}
