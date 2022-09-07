package controllertest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.IShape;
import shapesphotoalbum.model.Snapshot;

/**
 * This helper class represents a mock model object for JUnit testing the controllers.
 * It is used in the controllertest.IPhotoAlbumControllerTest.
 */
public class MockModel implements IPhotoAlbumModel {
  private StringBuilder log;
  private IShape uniqueShape;
  private String uniqueCode;
  private Map<String, IShape> album;
  private Snapshot selfie;

  /**
   * Initializes the controllertest.MockModel class.
   * @param log the log to keep a record of the input commands
   * @param uniqueShape the unique shape as a correct output indicator
   */
  public MockModel(StringBuilder log, IShape uniqueShape) {
    this.log = log;
    this.uniqueShape = uniqueShape;
    this.uniqueCode = "test";
    this.album = new LinkedHashMap<>();
    this.selfie = new Snapshot();
  }

  @Override
  public IShape createShape(String name, String shape, int x, int y, int sizeOne,
                            int sizeTwo, int r, int g, int b) {
    this.log.append("shape " + name + " " + shape + " "
            + x + " " + y + " " + sizeOne + " " + sizeTwo + " "
            + r + " " + g + " " + b + "\n");
    this.album.put("uniqueShape", this.uniqueShape);
    return this.uniqueShape;
  }

  @Override
  public void moveShape(String name, int x, int y) {
    this.log.append("move " + name + " " + x + " " + y + "\n");
  }

  @Override
  public void resizeShape(String name, int sizeOne, int sizeTwo) {
    this.log.append("resize " + name + " " + sizeOne + " " + sizeTwo + "\n");
  }

  @Override
  public void recolorShape(String name, int r, int g, int b) {
    this.log.append("color " + name + " " + r + " " + g + " " + b + "\n");
  }

  @Override
  public void removeShape(String name) {
    this.log.append("remove " + name + "\n");
  }

  @Override
  public Map<String, IShape> getAlbum() {
    return this.album;
  }

  @Override
  public Snapshot getSelfie() {
    return this.selfie;
  }

  @Override
  public String takeSelfie(String description) {
    if (description.equals("")){
      this.log.append("snapshot\n");
    } else {
      this.log.append("snapshot " + description.strip() + "\n");
    }
    return "\nSnapshot ID: " + this.uniqueCode + "\n"
            + "Timestamp: " + this.uniqueCode + "\n"
            + "Description: " + this.uniqueCode + "\n"
            + "Shape Information:\n" + this.uniqueShape.toString();
  }

  @Override
  public List<String> getSnapshotList() {
    List<String> uniqueList = new ArrayList<>();
    uniqueList.add(this.uniqueCode);
    return uniqueList;
  }

  @Override
  public String printSnapshotIds() {
    return "List of snapshots taken before reset: " + getSnapshotList().toString() + "\n";
  }

  @Override
  public String getSnapshot(String id) {
    return "\nSnapshot ID: " + this.uniqueCode + "\n"
            + "Timestamp: " + this.uniqueCode + "\n"
            + "Description: " + this.uniqueCode + "\n"
            + "Shape Information:\n" + this.uniqueShape.toString();
  }

  @Override
  public String getSnapshotDetails() {
    return "Printing Snapshots\n"
            + "Snapshot ID: " + this.uniqueCode + "\n"
            + "Timestamp: " + this.uniqueCode + "\n"
            + "Description: " + this.uniqueCode + "\n"
            + "Shape Information:\n" + this.uniqueShape.toString();
  }

  @Override
  public void resetAlbum() {
    this.album.clear();
    this.selfie.reset();
  }
}
