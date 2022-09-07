package shapesphotoalbum.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Snapshot object. It provides snapshots for the PhotoAlbumModel object.
 */
public class Snapshot {
  private List<String> historyList;
  private Map<String, String> historyDetails;
  private static long startTime;
  private static final SimpleDateFormat idFormat
          = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
  private static final SimpleDateFormat timestampFormat
          = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  /**
   * Constructs a Snapshot class object.
   */
  public Snapshot() {
    this.historyList = new ArrayList<>();
    // Initialize a LinkedHashMap to keep the collection of a series of snapshots according
    // to insertion order. The LinkedHashMap can guarantee that items be listed in insertion
    // order, therefore I chose this collection type here.
    // The key is the snapshot ID e.g. 2022-04-03T22:59:14.831046, and
    // the value is the String representation of the Snapshot record of the album.
    this.historyDetails = new LinkedHashMap<>();
    startTime = System.nanoTime();
  }

  /**
   * Takes and returns a snapshot of the given album.
   * @param album the IPhotoAlbumModel album for the snapshot
   * @param description the description of the snapshot. It can be empty or null.
   * @return a String representation if snapshot id
   */
  public String takeSnapshot(IPhotoAlbumModel album, String description) {
    if (album == null) {
      return "";
    }
    long micros = (System.nanoTime() - this.startTime) / 1000 ;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String id = idFormat.format(timestamp) + String.format("%03d", micros % 100);
    // Update the list of snapshot history
    this.historyList.add(id);
    // Update the historyDetails hashmap
    String item = "\nSnapshot ID: " + id + "\n"
            + "Timestamp: " + timestampFormat.format(timestamp) + "\n"
            + "Description: " + description + "\n"
            + "Shape Information:\n"
            + album;
    this.historyDetails.put(id, item);
    return id;
  }

  /**
   * Returns the String representation of a snapshot based on a given snapshot id.
   * @return the String representation of a snapshot
   */
  public String getSnapshot(String id) {
    return this.historyDetails.get(id);
  }

  /**
   * Returns String representation of list of snapshot id records.
   * @return List<String> snapshot id list
   */
  public List<String> getHistoryList() {
    return Collections.unmodifiableList(this.historyList);
  }

  /**
   * Returns String representation of snapshot details.
   * @return snapshot details
   */
  public String getHistoryDetails() {
    String output = "Printing Snapshots";
    if (this.historyDetails != null) {
      output = this.historyDetails.values().stream()
              .reduce("Printing Snapshots", (a, b) -> a + b);
    }
    return output;
  }

  /**
   * Resets and clears the snapshot history.
   */
  public void reset() {
    this.historyList.clear();
    this.historyDetails.clear();
  }
}
