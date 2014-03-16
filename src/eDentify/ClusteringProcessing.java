package eDentify;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithmDescriptor;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.attribute.CommonAttributesDescriptor;
import org.carrot2.text.preprocessing.DocumentAssigner;
import org.carrot2.text.preprocessing.DocumentAssignerDescriptor;

public class ClusteringProcessing {

	String line = null;
	FileHandler fw = new FileHandler();
	Path path = Paths.get(fw.filenamePreClusteredResults); //inputFile
	File inputFile = fw.filePreClusteredResults;
	File outputFile = fw.fileClusteredResults;
	String delimiter = ",";
	

	//Method to process a cluster
	public void clustering(){
	
		ArrayList<Document> documents = new ArrayList<Document>(); //creates a list of documents for clustering
    	fw.erase(outputFile); //Method to erase file in case it's already populated from last run
		
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("windows-1256"))){
	        while ((line = reader.readLine()) != null) {
	        	
	        	String[] records = line.split(delimiter); //read each record using the comma delimiter
	        	
	        	int m=0, j=1, k=2;
	        	
	        	//Processing records that have all 3 (Title, Snippet and URL)
	        	if (records.length == 3){
	        		documents.add(new Document(records[1], records[2], records[0]));
	        	}
	        	
	        	//Processing records that have only 2 (Title and URL)
	        	else if (records.length == 2){
	        		documents.add(new Document(records[1], "", records[0]));
	        	}
	        	
	        	//Processing when records have 1 only (URL)
	        	else documents.add(new Document("", "", records[0]));

	        	//Go to next line
	        	m=m+3;
	        	j=j+3;
	        	k=k+3;
	        	
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final Controller controller = ControllerFactory.createSimple();
        
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put(CommonAttributesDescriptor.Keys.QUERY, "John Smith England Devon");
        
        attributes.put("LingoClusteringAlgorithm.desiredClusterCountBase", 30);
        attributes.put("LingoClusteringAlgorithm.clusterMergingThreshold", 0.7);
        attributes.put("LingoClusteringAlgorithm.scoreWeight", 1.0); //size-score sorting ration
        attributes.put("LingoClusteringAlgorithm.titleWordsBoost",2.0);
        attributes.put("DocumentAssignerDescriptor.exactPhraseAssignment",false);
        
        //attributes.put("LingoClusteringAlgorithm.matrixBuilder",0);
        CommonAttributesDescriptor
        .attributeBuilder(attributes)
        	.documents(documents)
        .results(100);
        
        //attributes.put(DocumentAssignerDescriptor.Keys.MIN_CLUSTER_SIZE, 3);
        
        DocumentAssignerDescriptor
        .attributeBuilder(attributes);
        
        LingoClusteringAlgorithmDescriptor
        .attributeBuilder(attributes);
       // .desiredClusterCountBase(15) //15 was the usual
        //.matrixReducer()
        //.matrixBuilder()
       // 	.titleWordsBoost(8.5); //boost to title word
        //.factorizationQuality(FactorizationQuality.HIGH);
        
        final ProcessingResult result = controller.process(attributes, LingoClusteringAlgorithm.class);
        
        final List<Cluster> clusters = result.getClusters();
        
        //Display cluster information
        ConsoleFormatter.displayClusters(clusters);
		
	}

	
	//Main method
	public static void main(String[] args){
		ClusteringProcessing obj = new ClusteringProcessing();
		obj.clustering();
	}
	
}
