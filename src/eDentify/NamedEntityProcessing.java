package eDentify;

//TODO: Remember to add the fact that the stanford NLP library is required for NER!

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NamedEntityProcessing {
	
	//Method to process the named entities
	public void processNamedEntity(){
		String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz"; //3 class (PERSON, LOCATION and ORGANISATION) classifier
		//String serializedClassifier = "classifiers/english.conll.4class.distsim.crf.ser.gz";
		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		FileHandler fw = new FileHandler();
		  org.apache.log4j.PropertyConfigurator.configure(fw.filenameLog4JConfig);
	      Path path = Paths.get(fw.filename); //path for input file
	      
	      try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())){
	    	  fw.setFile(fw.fileResultsWithNER); //delete contents to file before starting the process
	    	  String line = null;
	    	  while ((line = reader.readLine()) != null) {
	    		  String classifiedData = classifier.classifyWithInlineXML(line);
	    		  fw.writeToFile("", classifiedData, fw.fileResultsWithNER, true);
	    	  }
	      } catch (IOException e) { e.printStackTrace(); }
		
	}
}
