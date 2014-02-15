
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2013, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package eDentify;

import java.util.ArrayList;
import java.util.List;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.IDocumentSource;
import org.carrot2.core.ProcessingResult;

/**
 * This example shows how to cluster a set of documents available as an {@link ArrayList}.
 * This setting is particularly useful for quick experiments with custom data for which
 * there is no corresponding {@link IDocumentSource} implementation. For production use,
 * it's better to implement a {@link IDocumentSource} for the custom document source, so
 * that e.g., the {@link Controller} can cache its results, if needed.
 * 
 * @see ClusteringDataFromDocumentSources
 * @see UsingCachingController
 */
public class ClusteringDocumentList
{
	
	public void getDocument(){
		
	}
	
    public static void main(String [] args)
    {
        {
            // [[[start:clustering-document-list]]]
            /* A few example documents, normally you would need at least 20 for reasonable clusters. */
            final String [][] data = new String [] []
            {
                {
                    "http://en.wikipedia.org/wiki/Data_mining",
                    "Data mining - Wikipedia, the free encyclopedia",
                    "Article about knowledge-discovery in databases (KDD), the practice of automatically searching large stores of data for patterns."
                },

                {
                    "http://www.ccsu.edu/datamining/resources.html",
                    "CCSU - Data Mining",
                    "A collection of Data Mining links edited by the Central Connecticut State University ... Graduate Certificate Program. Data Mining Resources. Resources. Groups ..."
                },

                {
                    "http://www.kdnuggets.com/",
                    "KDnuggets: Data Mining, Web Mining, and Knowledge Discovery",
                    "Newsletter on the data mining and knowledge industries, offering information on data mining, knowledge discovery, text mining, and web mining software, courses, jobs, publications, and meetings."
                },

                {
                    "http://en.wikipedia.org/wiki/Data-mining",
                    "Data mining - Wikipedia, the free encyclopedia",
                    "Data mining is considered a subfield within the Computer Science field of knowledge discovery. ... claim to perform \"data mining\" by automating the creation ..."
                },

                {
                    "http://www.anderson.ucla.edu/faculty/jason.frand/teacher/technologies/palace/datamining.htm",
                    "Data Mining: What is Data Mining?",
                    "Outlines what knowledge discovery, the process of analyzing data from different perspectives and summarizing it into useful information, can do and how it works."
                },
            };

            /* Prepare Carrot2 documents */
            final ArrayList<Document> documents = new ArrayList<Document>();
            for (String [] row : data)
            {
                documents.add(new Document(row[1], row[2], row[0]));
            }

            /* A controller to manage the processing pipeline. */
            final Controller controller = ControllerFactory.createSimple();

            /*
             * Perform clustering by topic using the Lingo algorithm. Lingo can 
             * take advantage of the original query, so we provide it along with the documents.
             */
            final ProcessingResult byTopicClusters = controller.process(documents, "data mining",
                LingoClusteringAlgorithm.class);
            final List<Cluster> clustersByTopic = byTopicClusters.getClusters();
            
            /* Perform clustering by domain. In this case query is not useful, hence it is null. */
            final ProcessingResult byDomainClusters = controller.process(documents, null,
                ByUrlClusteringAlgorithm.class);
            final List<Cluster> clustersByDomain = byDomainClusters.getClusters();
            // [[[end:clustering-document-list]]]
            
            ConsoleFormatter.displayClusters(clustersByTopic);
            ConsoleFormatter.displayClusters(clustersByDomain);
       }
    }
}
