package eDentify;

import java.util.List;

public class GoogleSearchResults {

	public String link, title, snippet;
	public String urlLabel = "URL: ", titleLabel = "TITLE: ", snippetLabel = "SNIPPET: ";
	public List<GoogleSearchResults> items;
	
	//Method to format results and remove HTML characters from text
	public void formatText(String text){
		text = text.replaceAll("\\<[^>]*>","");
	}
	
	//Method to remove line break from multi line results (e.g. snippet)
	public String removeLineBreak(String text){
		text = text.replace("\n", "").replace("\r", "");
		return text;
	}
	
	//Method to get result
	public void getResult (int i){
		String result = items.get(i).toString();
		formatText(result);
	    System.out.println(result);
	}
	
	//Method to transform results to string and output them
	public String toString(){
		GoogleSearchPostProcessing fw = new GoogleSearchPostProcessing(); //Create new object for file write
    	formatText(title);
    	formatText(snippet);
    	snippet = removeLineBreak(snippet);
    	
    	//Print out contents to screen
    	System.out.println(titleLabel + title);
		System.out.println(urlLabel + link);
		System.out.println(snippetLabel + snippet);
		
		//Write contents to file
		fw.write(titleLabel, title);
		fw.write(urlLabel, link);
		fw.write(snippetLabel, snippet);
		fw.write("---------- END OF RESULT -----------","");
	    return "End of Result";
	}

}