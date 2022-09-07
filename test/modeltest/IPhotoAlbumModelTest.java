package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.IShape;
import shapesphotoalbum.model.PhotoAlbumModel;

/**
 * JUnit test for the IPhotoAlbumModel interface and PhotoAlbumModel class.
 */
public class IPhotoAlbumModelTest {
  private IPhotoAlbumModel album;

  /**
   * Sets up the PhotoAlbumModel object and shape objects O and R for the test.
   */
  @Before
  public void setUp() {
    this.album = new PhotoAlbumModel();
  }

  /**
   * Test constructor of the PhotoAlbumModel.
   */
  @Test
  public void testPhotoAlbumModel() {
    // Test initial empty album
    assertEquals("", this.album.toString());

    // Test album with two shape photos
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 1,0,0);
    this.album.createShape("O","oval",
            400, 100, 60, 30, 0,0,1);
    String expected = "Name: R\nType: rectangle\nMin corner: (200,200), "
            + "Width: 50, Height: 100, Color: (1,0,0)\n"
            + "Name: O\nType: oval\nCenter: (400,100), "
            + "X radius: 60, Y radius: 30, Color: (0,0,1)\n";
    assertEquals(expected, this.album.toString());
  }

  /**
   * Test createShape method.
   */
  @Test
  public void testCreateShape() {
    // Create one rectangle
    IShape r = this.album.createShape("R","RecTangle",
            200, 200, 50, 100, 1,0,0);
    String expected = "Name: R\nType: rectangle\nMin corner: (200,200), "
            + "Width: 50, Height: 100, Color: (1,0,0)\n";
    assertEquals(expected, this.album.toString());
    // Create the second shape, an oval
    IShape o = this.album.createShape("O","oVaL",
            400, 100, 60, 30, 0, 1, 0);

    // Create the third shape, another oval with the same features but a different name
    IShape o2 = this.album.createShape("O2","oval",
            400, 100, 60, 30, 0, 1, 0);
    // Check if the album size = 3
    assertEquals(3, this.album.getAlbum().size());
    // Check if the album contains all added shapes
    assertTrue(this.album.getAlbum().containsValue(r));
    assertTrue(this.album.getAlbum().containsValue(o));
    assertTrue(this.album.getAlbum().containsValue(o2));
  }

  /**
   * Test invalid input to createShape method.
   */
  @Test
  public void testInvalidCreateShape() {
    // Test null input
    try {
      this.album.createShape("s", null, 0, 100,
              20, 30, 0, 0, 0);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    // Test empty input
    try {
      this.album.createShape("", "", 0, 100,
              20, 30, 0, 0, 0);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    // Test existed name
    try {
      this.album.createShape("R", "rectangle", -11, 100,
              20, 30, 1, 1, 1);
      // Create another shape with the same name, expected to throw exception
      this.album.createShape("R", "rectangle", 0, 100,
              20, 30, 1, 0, 1);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    // Test negative input
    try {
      this.album.createShape("s", "square", 0, 100,
              -20, 30, 0, 0, 1);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    // Test size == 0
    try {
      this.album.createShape("O", "Oval", 100, 100, 0, 0,
              2, 2, 1);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    // Test invalid color
    try {
      this.album.createShape("s", "square", 0, 100,
              20, 30, -1, 0, 0);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    try {
      this.album.createShape("s", "square", 0, 100,
              20, 30, 1, 0, 256);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
//    // Test shapes out of range of the set canvas (1000 * 1000, origin in the upper left corner)
//    try {
//      this.album.createShape("O", "Oval", -1, 500, 1, 1,
//              1, 1, 0);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("O", "Oval", 1, 1000, 1, 1,
//              1, 1, 0);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("R", "rectangle", 1000, 500, 1, 1,
//              1, 1, 0);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("O","oval", 70, 2,
//              120, 320, 2, 1, 1);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("R", "Rectangle", 0, 0, 1, 1001,
//              2, 1, 1);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
  }

  /**
   * Test moveShape method.
   */
  @Test
  public void testMoveShape() {
    // Move oval from (500,100) to (400,400)
    this.album.createShape("O","oval",
            200, 100, 60, 30, 0,0,1);
    this.album.moveShape("O", 400, 400);
    assertEquals("Name: O\nType: oval\nCenter: (400,400), "
            + "X radius: 60, Y radius: 30, Color: (0,0,1)\n", this.album.toString());

    // Move oval from (400,400) to (940,970)
    this.album.moveShape("O", 940, 970);
    assertEquals("Name: O\nType: oval\nCenter: (940,970), "
            + "X radius: 60, Y radius: 30, Color: (0,0,1)\n", this.album.toString());

    // Move rectangular from (200,200) to the upper edge of the canvas (450, 400)
    this.album.removeShape("O");
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 1,0,0);
    this.album.moveShape("R", 450, 400);
    assertEquals("Name: R\nType: rectangle\nMin corner: (450,400), "
            + "Width: 50, Height: 100, Color: (1,0,0)\n",  this.album.toString());

    // Move rectangular from (450, 400) to the lower edge of the canvas(950,900)
    this.album.moveShape("R", 950, 900);
    assertEquals("Name: R\nType: rectangle\nMin corner: (950,900), "
            + "Width: 50, Height: 100, Color: (1,0,0)\n",  this.album.toString());
  }

  @Test
  public void testInvalidMoveShape() {
    // Test an empty album
    try {
      this.album.moveShape("O", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test an empty input
    try {
      this.album.moveShape("", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test null input
    try {
      this.album.moveShape(null, 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test a non-exist shape
    try {
      this.album.createShape("R","rectangle",
              200, 200, 50, 100, 1, 1, 2);
      this.album.moveShape("o", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
//    // Test shape that is outside the canvas
//    try {
//      this.album.createShape("R","rectangle",
//              200, 200, 50, 100, 1, 1, 2);
//      this.album.moveShape("R", -501, 100);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("O","oval",
//              200, 200, 50, 100, 1, 1, 2);
//      this.album.resizeShape("O", 0, 501);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
  }

  /**
   * Test resizeShape method.
   */
  @Test
  public void testResizeShape() {
    // Resize rectangle
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 1,0,0);
    this.album.resizeShape("R", 25, 101);
    assertEquals("Name: R\nType: rectangle\nMin corner: (200,200), "
            + "Width: 25, Height: 101, Color: (1,0,0)\n",  this.album.toString());

    // Resize oval
    this.album.removeShape("R");
    this.album.createShape("O","oval",
            300, 100, 60, 30, 0,0,1);
    this.album.resizeShape("O", 1, 100);
    assertEquals("Name: O\nType: oval\nCenter: (300,100), "
            + "X radius: 1, Y radius: 100, Color: (0,0,1)\n",  this.album.toString());
  }

  /**
   * Test invalid input to resizeShape method.
   */
  @Test
  public void testInvalidResizeShape() {
    // Test an empty album
    try {
      this.album.resizeShape("O", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test an empty input
    try {
      this.album.resizeShape("", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test null input
    try {
      this.album.resizeShape(null, 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test a non-exist shape
    try {
      this.album.createShape("R","rectangle",
              200, 200, 50, 100, 0,0,1);
      this.album.resizeShape("r", 100, 90);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
//    // Test shape that is outside the canvas
//    try {
//      this.album.createShape("R","rectangle",
//              200, 200, 50, 100, 0,0,1);
//      this.album.resizeShape("R", 100, 1001);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
//    try {
//      this.album.createShape("O","oval",
//              200, 200, 50, 100, 0,0,1);
//      this.album.resizeShape("O", 1001, 90);
//      fail("Failed to catch the exception.");
//    }
//    catch (IllegalArgumentException e) {
//      // Passed test
//    }
  }

  /**
   * Test recolorShape method.
   */
  @Test
  public void testRecolorShape() {
    // Recolor rectangle
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 2,2,1);
    this.album.recolorShape("R", 1,1,0);
    assertEquals("Name: R\nType: rectangle\nMin corner: (200,200), "
            + "Width: 50, Height: 100, Color: (1,1,0)\n",  this.album.toString());

    // Resize oval
    this.album.removeShape("R");
    this.album.createShape("O","oval",
            200, 100, 60, 30, 0,2,1);
    this.album.recolorShape("O", 1,1,1);
    assertEquals("Name: O\nType: oval\nCenter: (200,100), "
            + "X radius: 60, Y radius: 30, Color: (1,1,1)\n",  this.album.toString());
  }

  /**
   * Test invalid input to recolorShape method.
   */
  @Test
  public void testInvalidRecolorShape() {
    // Test an empty album
    try {
      this.album.recolorShape("O", 1,1,1);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test an empty input
    try {
      this.album.recolorShape("", 2,1,1);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test null input
    try {
      this.album.recolorShape(null, 2,1,1);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test a non-exist shape
    try {
      this.album.createShape("R","rectangle",
              200, 200, 50, 100, 1,2,1);
      this.album.recolorShape("o", 1,1,1);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
  }

  /**
   * Test removeShape method.
   */
  @Test
  public void testRemoveShape() {
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 1,1,1);
    assertEquals(1,  this.album.getAlbum().size());
    this.album.createShape("O","oval",
            200, 100, 60, 30, 0,0,1);
    assertEquals(2,  this.album.getAlbum().size());

    // Resize oval
    this.album.removeShape("R");
    assertEquals(1,  this.album.getAlbum().size());
    assertEquals("Name: O\nType: oval\nCenter: (200,100), "
            + "X radius: 60, Y radius: 30, Color: (0,0,1)\n",  this.album.toString());
  }

  /**
   * Test invalid input to removeShape method.
   */
  @Test
  public void testInvalidRemoveShape() {
    // Test an empty album
    try {
      this.album.removeShape("O");
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test an empty input
    try {
      this.album.removeShape("");
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test null input
    try {
      this.album.removeShape(null);
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
    // Test a non-exist shape
    try {
      this.album.createShape("R","rectangle",
              200, 200, 50, 100, 1,0,1);
      this.album.removeShape("o");
      fail("Failed to catch the exception.");
    }
    catch (NoSuchElementException e) {
      // Passed test
    }
  }

  /**
   * Test takeSelfie method and getSnapshotDetails method.
   */
  @Test
  public void testTakeSelfieAndGetDetails() {
    // Take a selfie in an empty album
    String one = this.album.takeSelfie("After first selfie");
    String selfieOne = this.album.getSelfie().getSnapshot(one);
    String head = "Printing Snapshots";
    String expected = head + selfieOne;
    assertEquals(expected, this.album.getSnapshotDetails());

    // Take the second selfie in a non-empty album
    this.album.createShape("R","rectangle",
            200, 200, 60, 100, 1,0,1);
    this.album.createShape("O","oval",
            100, 200, 50, 30, 0,2,1);
    String two = this.album.takeSelfie("2nd selfie");
    String selfieTwo = this.album.getSelfie().getSnapshot(two);
    expected += selfieTwo;
    assertEquals(expected, this.album.getSnapshotDetails());

    // Take the third selfie after changing the color of the shape "O" from the album
    this.album.recolorShape("O", 1,2,1);
    String three = this.album.takeSelfie("Changed O's color");
    String selfieThree = this.album.getSelfie().getSnapshot(three);
    expected += selfieThree;
    assertEquals(expected, this.album.getSnapshotDetails());

    // Take the forth selfie after changing the location of the shape "R" and size of "O"
    this.album.moveShape("R", 0, 0);
    this.album.resizeShape("O", 10, 1);
    String four = this.album.takeSelfie("Changed location and size");
    String selfieFour = this.album.getSelfie().getSnapshot(four);
    expected += selfieFour;
    assertEquals(expected, this.album.getSnapshotDetails());

    // Take the fifth selfie after removing shape "O"
    this.album.removeShape("O");
    String five = this.album.takeSelfie("Remove shape O");
    String selfieFive = this.album.getSelfie().getSnapshot(five);
    expected += selfieFive;
    assertEquals(expected, this.album.getSnapshotDetails());
  }

  /**
   * Test getSnapshotList method and printSnapshotIds method.
   */
  @Test
  public void testSnapshotIds() {
    // Take a selfie in an empty album
    String one = this.album.takeSelfie("After first selfie");
    List<String> list = new ArrayList<>();
    list.add(one);
    String expected = "List of snapshots taken before reset: ";
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(expected + list + "\n", this.album.printSnapshotIds());

    // Take the second selfie in a non-empty album
    this.album.createShape("R","rectangle",
            200, 200, 60, 100, 0,2,1);
    this.album.createShape("O","oval",
            100, 200, 50, 30, 1,0,1);
    String two = this.album.takeSelfie("2nd selfie");
    list.add(two);
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(expected + list + "\n", this.album.printSnapshotIds());

    // Take the third selfie after changing the color of the shape "O" from the album
    this.album.recolorShape("O", 0,0,1);
    String three = this.album.takeSelfie("Changed O's color");
    list.add(three);
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(expected + list + "\n", this.album.printSnapshotIds());

    // Take the forth selfie after changing the location of the shape "R" and size of "O"
    this.album.moveShape("R", 0, 0);
    this.album.resizeShape("O", 10, 1);
    String four = this.album.takeSelfie("Changed location and size");
    list.add(four);
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(expected + list + "\n", this.album.printSnapshotIds());


    // Take the fifth selfie after removing shape "O"
    this.album.removeShape("O");
    String five = this.album.takeSelfie("Remove shape O");
    list.add(five);
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(expected + list + "\n", this.album.printSnapshotIds());
  }

  /**
   * Test resetAlbum method.
   */
  @Test
  public void testResetAlbum() {
    // Test initial empty album
    assertEquals("", this.album.toString());

    // Reset an album with one shape photo
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 0,0,1);
    assertEquals(1,  this.album.getAlbum().size());
    this.album.resetAlbum();
    assertEquals(0,  this.album.getAlbum().size());
    assertEquals("", this.album.toString());
    assertEquals("List of snapshots taken before reset: []\n",
            this.album.printSnapshotIds());
    String head = "Printing Snapshots";
    assertEquals(head, this.album.getSnapshotDetails());

    // Add photos to the album after resetting
    this.album.createShape("R","rectangle",
            50, 100, 20, 14, 1,0,1);
    this.album.createShape("O","oval",
            200, 100, 50, 30, 1,0,0);
    assertEquals(2,  this.album.getAlbum().size());
    String id = this.album.takeSelfie("2nd selfie");
    List<String> list = new ArrayList<>();
    list.add(id);
    assertEquals(list.toString(), this.album.getSnapshotList().toString());
    assertEquals(head + this.album.getSelfie().getSnapshot(id),
            this.album.getSnapshotDetails());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    // Test initial empty album
    assertEquals("", this.album.toString());

    // Test album with two shape photos
    this.album.createShape("R","rectangle",
            200, 200, 50, 100, 1,0,0);
    this.album.createShape("O","oval",
            100, 100, 60, 30, 0,0,1);
    String expected = "Name: R\nType: rectangle\nMin corner: (200,200), "
            + "Width: 50, Height: 100, Color: (1,0,0)\n"
            + "Name: O\nType: oval\nCenter: (100,100), "
            + "X radius: 60, Y radius: 30, Color: (0,0,1)\n";
    assertEquals(expected, this.album.toString());
  }
}