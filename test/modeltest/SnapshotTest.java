package modeltest;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.PhotoAlbumModel;
import shapesphotoalbum.model.Snapshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test on Snapshot class.
 */
public class SnapshotTest {
  private Snapshot selfie;
  private IPhotoAlbumModel album;
  private static long startTime;
  private static final SimpleDateFormat idFormat
          = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
  private static final SimpleDateFormat timestampFormat
          = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  /**
   * Initializes Snapshot objects.
   */
  @Before
  public void setUp() {
    this.album = new PhotoAlbumModel();
    this.selfie = new Snapshot();
    this.startTime = System.nanoTime();
  }

  /**
   * Test constructor.
   */
  @Test
  public void testSnapShot() {
    assertEquals("[]", this.selfie.getHistoryList().toString());
    assertEquals("Printing Snapshots", this.selfie.getHistoryDetails());
  }

  /**
   * Test takeSnapshot method.
   */
  @Test
  public void testTakeSnapshot() {
    // Take one snapshot on a null album
    assertEquals("", this.selfie.takeSnapshot(null, "null snapshot"));
    // Take one snapshot on a null album with an empty description
    assertEquals("", this.selfie.takeSnapshot(null, ""));

    // Take one snapshot on an empty album with a null description
    String idOne = this.selfie.takeSnapshot(this.album, null);
    List<String> list = new ArrayList<>();
    list.add(idOne);
    assertEquals(list, this.selfie.getHistoryList());
    String headDetails = "Printing Snapshots";
    String expected = headDetails + this.selfie.getSnapshot(idOne);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the second snapshot on an album with one shape photo
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 255, 0, 0);
    String idTwo = this.selfie.takeSnapshot(this.album,
            "second snapshot with one shape");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idTwo));
    list.add(idTwo);
    assertEquals(list, this.selfie.getHistoryList());
    expected += this.selfie.getSnapshot(idTwo);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the third snapshot on an album with two shape photos
    this.album.createShape("O","oval",
            100, 200, 50, 30, 100, 90, 230);
    String idThree = this.selfie.takeSnapshot(this.album,
            "third snapshot with two shapes");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idThree));
    list.add(idThree);
    assertEquals(list, this.selfie.getHistoryList());
    expected += this.selfie.getSnapshot(idThree);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the forth snapshot on an album with revised photo and removed photo
    this.album.resizeShape("O", 1, 1);
    this.album.recolorShape("O", 200, 100, 1);
    this.album.moveShape("O", 10, 2);
    this.album.removeShape("R");
    String idFour = this.selfie.takeSnapshot(this.album, "revised and removed shapes");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idFour));
    list.add(idFour);
    assertEquals(list, this.selfie.getHistoryList());
    expected += this.selfie.getSnapshot(idFour);
    assertEquals(expected, this.selfie.getHistoryDetails());
  }

  /**
   * Test getSnapshot method.
   */
  @Test
  public void testGetSnapshot() {
    this.selfie.reset();
    this.album.resetAlbum();
    // Take one snapshot on an empty album
    String idOne = this.selfie.takeSnapshot(this.album, "snapshot one");
    String head = "Printing Snapshots";
    String expected = head + this.selfie.getSnapshot(idOne);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the second snapshot on an album with one shape photo
    this.album.createShape("R","rectangle",
            10, 10, 10, 10, 0, 0, 0);
    String idTwo = this.selfie.takeSnapshot(this.album,
            "second snapshot with one shape");
    expected += this.selfie.getSnapshot(idTwo);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the third snapshot on an album with two shape photos
    this.album.createShape("O","oval",
            11, 2, 2, 1, 1, 0, 0);
    String idThree = this.selfie.takeSnapshot(this.album,
            "third snapshot with two shapes");
    expected += this.selfie.getSnapshot(idThree);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the forth snapshot on an album with revised photo and removed photo
    this.album.resizeShape("R", 1, 1);
    this.album.recolorShape("R", 100, 200, 200);
    this.album.moveShape("R", 0, 0);
    this.album.removeShape("O");
    String idFour = this.selfie.takeSnapshot(this.album, "revised and removed shapes");
    expected += this.selfie.getSnapshot(idFour);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the fifth snapshot after reset snapshot
    this.selfie.reset();
    String idFive = this.selfie.takeSnapshot(this.album, "reset snapshot");
    assertEquals(head + this.selfie.getSnapshot(idFive), this.selfie.getHistoryDetails());
  }

  /**
   * Test getHistoryList method.
   */
  @Test
  public void testGetHistoryList() {
    this.selfie.reset();
    this.album.resetAlbum();
    // Take one snapshot on an empty album
    String idOne = this.selfie.takeSnapshot(this.album, "first snapshot");
    List<String> list = new ArrayList<>();
    list.add(idOne);
    assertEquals(list, this.selfie.getHistoryList());

    // Take the second snapshot on an album with one shape photo
    this.album.createShape("R","rectangle",
            100, 20, 30, 30, 255, 230, 200);
    String idTwo = this.selfie.takeSnapshot(this.album,
            "second snapshot with one shape");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idTwo));
    list.add(idTwo);
    assertEquals(list, this.selfie.getHistoryList());

    // Take the third snapshot on an album with two shape photos
    this.album.createShape("O","oval",
            70, 321, 35, 320, 1, 1, 1);
    String idThree = this.selfie.takeSnapshot(this.album,
            "third snapshot with two shapes");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idThree));
    list.add(idThree);
    assertEquals(list, this.selfie.getHistoryList());

    // Take the forth snapshot on an album with revised photo and removed photo
    this.album.resizeShape("O", 14, 22);
    this.album.recolorShape("O", 0, 0, 0);
    this.album.moveShape("O", 90, 30);
    this.album.removeShape("R");
    String idFour = this.selfie.takeSnapshot(this.album, "revised and removed shapes");
    // Check if the new snapshot id is unique
    assertFalse(list.contains(idFour));
    list.add(idFour);
    assertEquals(list, this.selfie.getHistoryList());

    // Take the fifth snapshot after reset snapshot
    this.selfie.reset();
    String idFive = this.selfie.takeSnapshot(this.album, "reset snapshot");
    list.clear();
    list.add(idFive);
    assertEquals(list, this.selfie.getHistoryList());
  }

  /**
   * Test getHistoryDetails method.
   */
  @Test
  public void testGetHistoryDetails() {
    this.selfie.reset();
    this.album.resetAlbum();
    // Take one snapshot on an empty album
    String idOne = this.selfie.takeSnapshot(this.album, "");
    String head = "Printing Snapshots";
    String expected = head + this.selfie.getSnapshot(idOne);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the second snapshot on an album with one shape photo
    this.album.createShape("R","rectangle",
            120, 200, 10, 100, 200, 200, 100);
    String idTwo = this.selfie.takeSnapshot(this.album,
            "second snapshot with one shape");
    expected += this.selfie.getSnapshot(idTwo);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the third snapshot on an album with two shape photos
    this.album.createShape("O","oval",
            110, 200, 50, 200, 10, 90, 20);
    String idThree = this.selfie.takeSnapshot(this.album,
            "third snapshot with two shapes");
    expected += this.selfie.getSnapshot(idThree);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the forth snapshot on an album with revised photo and removed photo
    this.album.resizeShape("R", 2, 10);
    this.album.recolorShape("O", 20, 100, 10);
    this.album.moveShape("O", 51, 300);
    this.album.removeShape("R");
    String idFour = this.selfie.takeSnapshot(this.album, "revised and removed shapes");
    expected += this.selfie.getSnapshot(idFour);
    assertEquals(expected, this.selfie.getHistoryDetails());

    // Take the fifth snapshot after reset snapshot
    this.selfie.reset();
    assertEquals(head, this.selfie.getHistoryDetails());
    String idFive = this.selfie.takeSnapshot(this.album, "reset snapshot");
    assertEquals(head + this.selfie.getSnapshot(idFive), this.selfie.getHistoryDetails());
  }

  /**
   * Test reset method.
   */
  @Test
  public void testReset() {
    // Reset an empty Snapshot
    this.selfie.reset();
    String head = "Printing Snapshots";
    assertEquals(head, this.selfie.getHistoryDetails());
    this.album.createShape("O","oval",
            100, 200, 50, 200,10, 10, 100);
    this.album.createShape("R","rectangle",
            120, 200, 10, 100, 200, 21, 200);
    this.album.createShape("O2","oval",
            70, 2, 69, 1, 1, 0, 0);
    String id = this.selfie.takeSnapshot(this.album, "snapshot of three shape photos");
    assertEquals(head + this.selfie.getSnapshot(id), this.selfie.getHistoryDetails());
    // Reset a Snapshot of an album with three shape photos
    this.selfie.reset();
    assertEquals(null, this.selfie.getSnapshot(id));
    assertEquals(head, this.selfie.getHistoryDetails());
  }
}