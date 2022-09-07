package viewtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.helper.AlbumBuilder;
import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.PhotoAlbumModel;
import shapesphotoalbum.view.PhotoAlbumWebView;

/**
 * JUnit test for PhotoAlbumWebView class.
 */
public class PhotoAlbumWebViewTest {
  private PhotoAlbumWebView viewOne;
  private File sourceOne;
  private String outOne;
  private IPhotoAlbumModel modelOne;
  private PhotoAlbumWebView viewTwo;
  private File sourceTwo;
  private String outTwo;
  private IPhotoAlbumModel modelTwo;

  /**
   * Initializes source files, model, and view objects for testing.
   */
  @Before
  public void setUp() {
    // Test case one using demo_input.txt as input
    this.sourceOne = new File("test/commandfiles/web_one.txt");
    this.outOne = "test/htmlfiles/webViewTestOne.html";
    this.viewOne = new PhotoAlbumWebView("", "", this.outOne);
    this.modelOne = new PhotoAlbumModel();
    AlbumBuilder.setUpAlbum(this.sourceOne, this.modelOne);

    // Test case two using buildings.txt as input
    this.sourceTwo = new File("test/commandfiles/web_two.txt");
    this.outTwo = "test/htmlfiles/webViewTestTwo.html";
    this.viewTwo = new PhotoAlbumWebView("800", "800", this.outTwo);
    this.modelTwo = new PhotoAlbumModel();
    AlbumBuilder.setUpAlbum(this.sourceTwo, this.modelTwo);
  }

  /**
   * Test the constructor.
   */
  @Test
  public void testPhotoAlbumWebView() {
    // Test the default canvas size
    assertEquals("\t<svg width='1000' height='1000'>\n", this.viewOne.getSvgSize());
    // Test the set canvas size
    assertEquals("\t<svg width='800' height='800'>\n", this.viewTwo.getSvgSize());
  }

  /**
   * Test paint method.
   */
  @Test
  public void testPaint() {
    try {
      // Test using the first command file with move, resize, color, snapshot, and remove commands
      this.viewOne.setSnapshotString(this.modelOne.getSnapshotDetails());
      this.viewOne.paint();
      String expected = "<!DOCTYPE html>\n<html>\n<head>\n<link rel=\"stylesheet\" "
              + "href=\"styles.css\">\n"
              + "</head>\n<body>\n<h1>cs5004 Shapes Photo Album Web</h1>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n"
              + "\t<p>Description: Head</p>\n"
              + "\t<svg width='1000' height='1000'>\n"
              + "\t\t<ellipse id='head' cx='350' cy='350' rx='200' ry='200' "
              + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n\t</svg>\n</div>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n\t<p>Description: </p>\n"
              + "\t<svg width='1000' height='1000'>\n"
              + "\t\t<ellipse id='head' cx='350' cy='350' rx='200' ry='200' "
              + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n"
              + "\t\t<rect id='brow1' x='395' y='390' width='20' height='10' fill='rgb(0,0,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<rect id='brow2' x='475' y='390' width='20' height='10' fill='rgb(0,0,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<ellipse id='left' cx='400' cy='425' rx='15' ry='15' fill='rgb(0,0,0)'>\n"
              + "\t\t</ellipse>\n"
              + "\t\t<ellipse id='right' cx='480' cy='425' rx='15' ry='15' fill='rgb(0,0,0)'>\n"
              + "\t\t</ellipse>\n\t</svg>\n</div>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n"
              + "\t<p>Description: add mouth</p>\n\t<svg width='1000' height='1000'>\n"
              + "\t\t<ellipse id='head' cx='350' cy='350' rx='200' ry='200' "
              + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n"
              + "\t\t<rect id='brow1' x='395' y='390' width='20' height='10' fill='rgb(0,0,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<rect id='brow2' x='475' y='390' width='20' height='10' fill='rgb(0,0,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<ellipse id='left' cx='400' cy='425' rx='15' ry='15' fill='rgb(0,0,0)'>\n"
              + "\t\t</ellipse>\n"
              + "\t\t<ellipse id='right' cx='480' cy='425' rx='15' ry='15' fill='rgb(0,0,0)'>\n"
              + "\t\t</ellipse>\n"
              + "\t\t<ellipse id='mouth' cx='420' cy='475' rx='90' ry='25' fill='rgb(0,0,0)'>\n"
              + "\t\t</ellipse>\n"
              + "\t\t<ellipse id='mouth2' cx='400' cy='465' rx='100' ry='25' "
              + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n\t</svg>\n</div>\n</body>\n</html>\n";
      File outFileOne = new File(this.outOne);
      assertTrue(outFileOne.exists());
      StringBuilder actual = new StringBuilder();
      try (
              Scanner output = new Scanner(outFileOne);
      ) {
        while (output.hasNextLine()) {
          actual.append(output.nextLine());
          actual.append("\n");
        }
      }
      // Change the snapshot id from timestamp into "test" as the timestamp id will always be
      // changing
      assertEquals(expected, actual.toString().replaceAll("<h2>[\\w0-9\\:\\-\\.]+</h2>",
              "<h2>test</h2>"));

      // Test using the second command file with move, resize, color, snapshot, and remove commands
      this.viewTwo.setSnapshotString(this.modelTwo.getSnapshotDetails());
      this.viewTwo.paint();
      expected = "<!DOCTYPE html>\n<html>\n<head>\n<link rel=\"stylesheet\" "
              + "href=\"styles.css\">\n"
              + "</head>\n<body>\n<h1>cs5004 Shapes Photo Album Web</h1>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n"
              + "\t<p>Description: 1 Draw shapes</p>\n"
              + "\t<svg width='800' height='800'>\n"
              + "\t\t<rect id='S0' x='100' y='75' width='20' height='15' fill='rgb(255,0,255)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<rect id='S1' x='90' y='90' width='40' height='15' fill='rgb(255,153,100)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<ellipse id='SO1' cx='40' cy='70' rx='30' ry='30' fill='rgb(0,0,255)'>\n"
              + "\t\t</ellipse>\n\t</svg>\n</div>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n\t<p>Description: 2 Move shapes</p>\n"
              + "\t<svg width='800' height='800'>\n"
              + "\t\t<rect id='S0' x='600' y='195' width='20' height='15' fill='rgb(255,0,255)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<rect id='S1' x='590' y='180' width='40' height='15' "
              + "fill='rgb(255,153,100)'>\n\t\t</rect>\n"
              + "\t\t<ellipse id='SO1' cx='40' cy='70' rx='30' ry='30' fill='rgb(0,0,255)'>\n"
              + "\t\t</ellipse>\n\t</svg>\n</div>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n"
              + "\t<p>Description: </p>\n\t<svg width='800' height='800'>\n"
              + "\t\t<rect id='S0' x='600' y='195' width='20' height='15' fill='rgb(0,255,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<rect id='S1' x='590' y='180' width='200' height='15' fill='rgb(255,0,0)'>\n"
              + "\t\t</rect>\n"
              + "\t\t<ellipse id='SO1' cx='40' cy='70' rx='200' ry='15' fill='rgb(0,0,255)'>\n"
              + "\t\t</ellipse>\n\t</svg>\n</div>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n\t<h2>test</h2>\n"
              + "\t<p>Description: 4 remove rectangles done!</p>\n"
              + "\t<svg width='800' height='800'>\n"
              + "\t\t<ellipse id='SO1' cx='40' cy='70' rx='200' ry='15' fill='rgb(0,0,255)'>\n"
              + "\t\t</ellipse>\n\t</svg>\n</div>\n</body>\n</html>\n";
      File outFileTwo = new File(this.outTwo);
      assertTrue(outFileTwo.exists());
      actual = new StringBuilder();
      try (
              Scanner output = new Scanner(outFileTwo);
      ) {
        while (output.hasNextLine()) {
          actual.append(output.nextLine());
          actual.append("\n");
        }
      }
      // Change the snapshot id from timestamp into "test" as the timestamp id will always be
      // changing
      assertEquals(expected, actual.toString().replaceAll("<h2>[\\w0-9\\:\\-\\.]+</h2>",
              "<h2>test</h2>"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test the view without setting up the snapshot string.
   */
  @Test
  public void testInvalidPaint() {
    try {
      this.viewOne.paint();
      fail("Failed to catch the exception");
    } catch (NullPointerException e) {
      // test passed
    }
  }

  /**
   * Test setSnapshotString method.
   */
  @Test
  public void testSetSnapshotString() {
    this.viewOne.setSnapshotString(this.modelOne.getSnapshotDetails());
    String[] expected = {"Printing Snapshots\nSnapshot ID: test\n"
            + "Timestamp: test\nDescription: Head \n"
            + "Shape Information:\n"
            + "Name: head\nType: oval\nCenter: (350,350), X radius: 200, Y radius: 200,"
            + " Color: (255,255,0)", "Snapshot ID: test\nTimestamp: test\nDescription: \n"
            + "Shape Information:\n"
            + "Name: head\nType: oval\nCenter: (350,350), X radius: 200, Y radius: 200,"
            + " Color: (255,255,0)\nName: brow1\nType: rectangle\n"
            + "Min corner: (395,390), Width: 20, Height: 10, Color: (0,0,0)\n"
            + "Name: brow2\nType: rectangle\nMin corner: (475,390), Width: 20, Height: 10,"
            + " Color: (0,0,0)\nName: left\nType: oval\nCenter: (400,425), X radius: 15, "
            + "Y radius: 15, Color: (0,0,0)\nName: right\nType: oval\nCenter: (480,425), "
            + "X radius: 15, Y radius: 15, Color: (0,0,0)", "Snapshot ID: test\n"
            + "Timestamp: test\nDescription: add mouth \n"
            + "Shape Information:\n"
            + "Name: head\nType: oval\nCenter: (350,350), X radius: 200, Y radius: 200,"
            + " Color: (255,255,0)\nName: brow1\nType: rectangle\n"
            + "Min corner: (395,390), Width: 20, Height: 10, Color: (0,0,0)\n"
            + "Name: brow2\nType: rectangle\nMin corner: (475,390), Width: 20, Height: 10,"
            + " Color: (0,0,0)\nName: left\nType: oval\n"
            + "Center: (400,425), X radius: 15, Y radius: 15, Color: (0,0,0)\n"
            + "Name: right\nType: oval\nCenter: (480,425), X radius: 15, Y radius: 15,"
            + " Color: (0,0,0)\nName: mouth\nType: oval\n"
            + "Center: (420,475), X radius: 90, Y radius: 25, Color: (0,0,0)\n"
            + "Name: mouth2\nType: oval\n"
            + "Center: (400,465), X radius: 100, Y radius: 25, Color: (255,255,0)\n"};

    String actual = Arrays.stream(this.viewOne.getSnapshot()).toList().toString();
    // Change the snapshot id from timestamp into "test" as the timestamp id will always be
    // changing
    actual = actual.replaceAll("Snapshot ID: [\\w0-9\\:\\-\\.]+",
            "Snapshot ID: test").replaceAll("Timestamp: [\\w0-9\\:\\-\\. ]+",
            "Timestamp: test");
    assertEquals(Arrays.stream(expected).toList().toString(), actual);
  }

  /**
   * Test the view with an empty snapshot string.
   */
  @Test
  public void testInvalidSetSnapshotString() {
    try {
      this.viewOne.setSnapshotString("");
      fail("Failed to catch the exception");
    } catch (IllegalArgumentException e) {
      // test passed
    }
  }
}