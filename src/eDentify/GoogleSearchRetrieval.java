package eDentify;

import java.io.*;
import java.net.*;

import com.google.gson.*;

public class GoogleSearchRetrieval {
	
	//TODO: Integration with Google App Engine for publishing
	
	//Declaration of variables to be utilised
	BufferedReader br;
	GoogleSearchResults results;
	URL url = null;
	HttpURLConnection conn;
	Reader reader;
	String key = "AIzaSyD9-bcODFrrpwXRei56djeFBAtTlqo1f7M"; //API key provided by Google
	String cx = "018126130119394071997:acq0r-m2iv0"; //Custom Search Engine ID provided by Google
	String query = "%22James/Boyd%22"; // search key word - TODO: Remember the '/' for spacing and '%22' for quotes
	String firstResultNo = "0";
	int startResult = 0;
	
	//Method to set URL based on initial result ID (startResult)
	public void setURL(int startResult){
		try {
			if (startResult == 0){
				url = new URL ("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q=" + query + "&alt=json" + "&num=10"); //without the start param as start = 0
			}
			else url = new URL ("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q=" + query + "&alt=json" + "&num=10" + "&start=" + startResult);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	//Method to set the connection, parsing the start result number in order to set URL
	public void setConn(int startResult){
		setURL(startResult);
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept","application/json");
			br = new BufferedReader(new InputStreamReader ((conn.getInputStream())));
			results = new Gson().fromJson(br, GoogleSearchResults.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	//Method to get the results
	public void getResults(){
		FileHandler pp = new FileHandler(); //Create object for next stage (i.e. pre-processing)
		pp.setFile(pp.file); //Configure the results file for output
		int resultNo = 0; 
		int page = 1;
		int resultPerPage = 10; //Only 10 allowed as per Google's T&Cs (Custom Search API rules)
		int totalNoPages = 10; //50 pages for test purposes
		
		while (page < totalNoPages+1){
			 setConn(startResult); //set initial connection
			 
			 if (page > 1){
				 int startResult = (10 * page) + 1;
				 setConn(startResult); //if more than 1 page, set another connection and open another stream
			 }
			 
			 for (int i=0; i<resultPerPage; i++){	
				 	resultNo++;
					results.getResult(i); //Get result within page visited
					System.out.println("RESULT NUMBER: " + resultNo);
					System.out.println("PAGE NUMBER: " + page);
					System.out.println("---------- END OF RESULT -----------");
			 }
			 page++;
		}
		
		getNE(); //Go to next processing (get Named Entities)
		
	 }
	
	//Method to get named entities
	public void getNE(){
		NamedEntityProcessing NEProcessing = new NamedEntityProcessing();
		NEProcessing.processNamedEntity();
		getPreClusteredResults(); //Go to next processing (get pre-clustered results with NEs)
	}
	
	//Method to get the pre-clustered results with the named entities extracted
	public void getPreClusteredResults(){
		NamedEntityExtraction NEExtraction = new NamedEntityExtraction();
		NEExtraction.extractNamedEntity();
		getClusteredResults(); //Go to next processing (get clustered results)
	}
	
	public void getClusteredResults(){
		ClusteringProcessing ClusteredResults = new ClusteringProcessing();
		ClusteredResults.clustering();
	}
	
}
