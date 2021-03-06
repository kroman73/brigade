package com.kmwllc.brigade.workflow;

import org.junit.Test;
import org.slf4j.Logger;

import com.kmwllc.brigade.config.StageConfig;
import com.kmwllc.brigade.config.WorkflowConfig;
import com.kmwllc.brigade.document.Document;
import com.kmwllc.brigade.logging.LoggerFactory;

public class WorkflowTest {

  public final static Logger log = LoggerFactory.getLogger(WorkflowTest.class.getCanonicalName());

  @Test
	public void testWorkflow() throws ClassNotFoundException, InterruptedException {


		// Create a workflow config
		WorkflowConfig wC = new WorkflowConfig("testWorkflow");

		StageConfig s1Conf = new StageConfig();
		s1Conf.setStageClass("com.kmwllc.brigade.stage.SetStaticFieldValue");
		s1Conf.setStageName("set title");
		s1Conf.setStringParam("fieldName", "title");
		s1Conf.setStringParam("value", "Hello World.");

		StageConfig s2Conf = new StageConfig();
		s2Conf.setStageName("Solr Sender");
		s2Conf.setStageClass("com.kmwllc.brigade.stage.SendToSolr");
		s2Conf.setStringParam("solrUrl", "http://localhost:8983/solr");
		s2Conf.setStringParam("idField", "id");

		wC.addStage(s1Conf);
		wC.addStage(s2Conf);

		// Create a workflow
		Workflow w = new Workflow(wC);
		w.initialize();

		// Create a document to be processed
		Document d = new Document("1");
		try {
			w.processDocument(d);
		} catch (InterruptedException e) {
		  log.warn("Interrupted exception: {}", e);
		}
		

		System.out.println("Processed...");

		while(true) {
			// Serve...
			Thread.sleep(100000);
		}
	}
}
