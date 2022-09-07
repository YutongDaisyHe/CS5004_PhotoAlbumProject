package shapesphotoalbum.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class represents a PhotoAlbumModel. It has a collection of shape objects.
 */
public class PhotoAlbumModel implements IPhotoAlbumModel {
  private Map<String, IShape> album;
  private Snapshot selfie;
  private static final int MIN_SIZE = 0;
  private static final int MAX_SIZE = 1000;

  /**
   * Constructs the PhotoAlbumModel object.
   */
  public PhotoAlbumModel() {
    // Use LinkedHashMap to store the input shapes to guarantee the added order is kept
    this.album = new LinkedHashMap<>();
    this.selfie = new Snapshot();
  }

  @Override
  public IShape createShape(String name, String shape, int x, int y,
                            int sizeOne, int sizeTwo, int r, int g, int b)
          throws IllegalArgumentException {
    // Check if the variables are valid input
    if (name == null || name.equals("") || this.album.containsKey(name)
            || shape == null || shape.equals("")
            || sizeOne <= MIN_SIZE || sizeTwo <= MIN_SIZE
            || r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid input.");
    }
    IShape shapePhoto = null;
    switch (shape.toLowerCase()) {
      case "oval":
        shapePhoto = new Oval(name, x, y, sizeOne, sizeTwo, r, g, b);
        break;
      case "rectangle":
        shapePhoto = new Rectangle(name, x, y, sizeOne, sizeTwo, r, g, b);
        break;
    }
    this.album.put(name, shapePhoto);
    return shapePhoto;
  }

  /**
   * Helper method to check if the given shape photo name is valid for the photo update methods.
   * @param name the given shape photo name
   * @return boolean true if the given name is valid and the shape is ready for updates
   */
  private boolean isValidForChange(String name) {
    if (name == null || name.equals("") || !album.containsKey(name)
            || this.album.get(name) == null) {
      return false;
    }
    return true;
  }

  @Override
  public void moveShape(String name, int x, int y) throws NoSuchElementException,
          IllegalArgumentException {
    if (!isValidForChange(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = this.album.get(name);
    shape.setLocation(x, y);
    this.album.replace(name, shape);
  }

  @Override
  public void resizeShape(String name, int sizeOne, int sizeTwo)
          throws NoSuchElementException {
    if (!isValidForChange(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = this.album.get(name);
    shape.setSize(sizeOne, sizeTwo);
    this.album.replace(name, shape);
  }

  @Override
  public void recolorShape(String name, int r, int g, int b)
          throws NoSuchElementException {
    if (!isValidForChange(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    IShape shape = this.album.get(name);
    shape.setColor(r, g, b);
    this.album.replace(name, shape);
  }

  @Override
  public void removeShape(String name) throws NoSuchElementException {
    if (!isValidForChange(name)) {
      throw new NoSuchElementException("The given name does not exist or has no shape photo.");
    }
    this.album.remove(name);
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
    // Get the user input of the snapshot description
    return this.selfie.takeSnapshot(this, description);
  }

  @Override
  public List<String> getSnapshotList() {
    return Collections.unmodifiableList(this.selfie.getHistoryList());
  }

  @Override
  public String printSnapshotIds() {
    String list = this.getSnapshotList().stream()
            .reduce("", (a, b) -> a + b + ", ");
    if (list.length() < 2) {
      return "List of snapshots taken before reset: []\n";
    }
    // Remove the tailing "," to the list and return the formatted string
    return "List of snapshots taken before reset: ["
            + list.substring(0, list.length()-2) + "]\n";
  }

  @Override
  public String getSnapshot(String id) {
    return this.selfie.getSnapshot(id);
  }

  @Override
  public String getSnapshotDetails() {
    return this.selfie.getHistoryDetails();
  }

  @Override
  public void resetAlbum() {
    this.album.clear();
    this.selfie.reset();
  }

  @Override
  public String toString() {
    String output = "";
    if (this.album != null) {
      output = this.album.values().stream()
              .map(shape -> shape.toString())
              .reduce("", (a, b) -> a + b);
    }
    return output;
  }
}
