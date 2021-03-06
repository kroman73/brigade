package com.kmwllc.brigade.config;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * A workflow configuration.  This consists of a name for the workflow
 * and a set of stages.  In addition there are some parameters about the number
 * of threads and blocking queue size.  
 * 
 * @author kwatters
 *
 */
public class WorkflowConfig extends Config {

  ArrayList<StageConfig> stages;
  private String name = "default";
  private int numWorkerThreads = 1;
  private int queueLength = 50;

  public WorkflowConfig(String name) {
    this.name = name;
    stages = new ArrayList<StageConfig>();
    // default workflow static config
  }

  public void addStage(StageConfig config) {
    stages.add(config);
  }

  public ArrayList<StageConfig> getStages() {
    return stages;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumWorkerThreads() {
    return numWorkerThreads;
  }

  public void setNumWorkerThreads(int numWorkerThreads) {
    this.numWorkerThreads = numWorkerThreads;
  }

  public int getQueueLength() {
    return queueLength;
  }

  public void setQueueLength(int queueLength) {
    this.queueLength = queueLength;
  }

  public static WorkflowConfig fromXML(String xml) {
    // TODO: move this to a utility to serialize/deserialize the config objects.
    // TODO: should override on the impl classes so they return a properly
    // cast config.
    Object o = (new XStream(new StaxDriver())).fromXML(xml);
    return (WorkflowConfig) o;
  }

}
