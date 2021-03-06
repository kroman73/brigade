package com.kmwllc.brigade.stage;

import java.util.List;

import com.kmwllc.brigade.config.StageConfig;
import com.kmwllc.brigade.document.Document;

/**
 * This stage will rename the field on a document.
 * 
 * @author kwatters
 *
 */
public class RenameField extends AbstractStage {

  private String oldName = "fielda";
  private String newName = "fieldb";

  @Override
  public void startStage(StageConfig config) {
    if (config != null) {
      oldName = config.getProperty("oldName");
      newName = config.getProperty("newName");
    }
  }

  @Override
  public List<Document> processDocument(Document doc) {

    if (oldName.contentEquals(newName)) {
      // Log.info("bad stage config, old field equals new field names.");
      return null;
    }

    if (!doc.hasField(oldName)) {
      return null;
    }
    // TODO: fix the concurrent modification error here.
    // (if old fieldname = new fieldname)
    for (Object o : doc.getField(oldName)) {
      doc.addToField(newName, o);
    }
    doc.removeField(oldName);
    return null;
  }

  @Override
  public void stopStage() {
    // no-op for this stage
  }

  @Override
  public void flush() {
    // no-op for this stage
  }

}
