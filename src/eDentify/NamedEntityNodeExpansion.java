package eDentify;

import java.io.BufferedReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedEntityNodeExpansion {
	String line = null;
	
	//Method to find the tags defined by the NER processing
	public void getTag(String tagLabel, String tagStart, String tagEnd, BufferedReader reader, String line, List<List<String>> mainList, List<String> items){
		String regexString = Pattern.quote(tagStart) + "(.*?)" + Pattern.quote(tagEnd); //regex string to help getting the value of the tag
		Pattern pattern = Pattern.compile(regexString);
		Matcher matcher = pattern.matcher(line);
		getMatch(tagLabel, matcher, mainList, items);
	}
	
	//Method to get the values from the tags that match the search
	public void getMatch(String tagLabel, Matcher matcher, List<List<String>> mainList, List<String> items){
		System.out.print(tagLabel + " FOUND: ");
		while (matcher.find()) {
			  String personName = matcher.group(1); // Since (.*?) is capturing group 1
    		  mainList.add(items); //add this finding to the main list
    		  System.out.print(personName + "; ");
		}
		System.out.println("");
	}
	
}
