package controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.controller.IPhotoAlbumController;
import shapesphotoalbum.controller.PhotoAlbumGraphicalController;
import shapesphotoalbum.controller.PhotoAlbumWebController;
import shapesphotoalbum.model.IShape;
import shapesphotoalbum.model.Oval;
import shapesphotoalbum.view.PhotoAlbumGraphicalView;
import shapesphotoalbum.view.PhotoAlbumWebView;

/**
 * JUnit test of the PhotoAlbumWebController and the PhotoAlbumGraphicalController that
 * implemented IPhotoAlbumController interface.
 */
public class IPhotoAlbumControllerTest {
  private MockModel webModel;
  private IPhotoAlbumController webController;
  private PhotoAlbumWebView webView;
  private StringBuilder webLog;
  private MockModel graphicalModel;
  private IPhotoAlbumController graphicalController;
  private PhotoAlbumGraphicalView graphicalView;
  private StringBuilder graphicalLog;
  private File in;
  private String out;

  /**
   * Initializes variables for the controller testing.
   */
  @Before
  public void setUp() {
    this.in = new File("test/commandfiles/web_one.txt");
    assertTrue(in.exists());
    IShape uniqueShape = new Oval("uniqueShape", 123, 456, 78, 90,
            100, 99, 98);

    // Initialize the web view and web controller
    this.webLog = new StringBuilder();
    this.webModel = new MockModel(this.webLog, uniqueShape);
    this.out = "test/htmlfiles/webControllerOutTest.html";
    this.webView = new PhotoAlbumWebView( "800", "800", this.out);
    this.webController = new PhotoAlbumWebController(this.in, this.webModel, this.webView);

    // Initialize the graphical view and graphical controller
    this.graphicalLog = new StringBuilder();
    this.graphicalModel = new MockModel(this.graphicalLog, uniqueShape);
    this.graphicalView = new PhotoAlbumGraphicalView("", "");
    this.graphicalController = new PhotoAlbumGraphicalController(this.in, this.graphicalModel,
            this.graphicalView);
  }

  /**
   * Test Go method of the PhotoAlbumWebController and PhotoAlbumGraphicalController.
   */
  @Test
  public void testGo() {
    try {
      String expectedInput = "shape head oval 350 350 200 200 255 255 0\n"
              + "snapshot Head\n"
              + "shape brow1 rectangle 395 390 20 10 0 0 0\n"
              + "shape brow2 rectangle 475 390 20 10 0 0 0\n"
              + "shape left oval 400 425 15 15 0 0 0\n"
              + "shape right oval 480 425 15 15 0 0 0\n"
              + "snapshot\n"
              + "shape mouth oval 420 475 90 25 0 0 0\n"
              + "shape mouth2 oval 400 465 100 25 255 255 0\n"
              + "snapshot add mouth\n";
      // Test input to the web controller
      this.webController.go();
      assertEquals(expectedInput, this.webLog.toString());
      // Test input to the graphical controller
      this.graphicalController.go();
      assertEquals(expectedInput, this.graphicalLog.toString());

      // Test output from the web controller
      String expectedOutput = "<!DOCTYPE html>\n<html>\n<head>\n"
              + "<link rel=\"stylesheet\" href=\"styles.css\">\n"
              + "</head>\n<body>\n<h1>cs5004 Shapes Photo Album Web</h1>\n"
              + "<div style='background-color:skyblue;border:4px solid red;margin:20px;"
              + "padding:30px;'>\n"
              + "\t<h2>test</h2>\n\t<p>Description: test</p>\n"
              + "\t<svg width='800' height='800'>\n"
              + "\t\t<ellipse id='uniqueShape' cx='123' cy='456' rx='78' ry='90' "
              + "fill='rgb(100,99,98)'>\n\t\t</ellipse>\n"
              + "\t</svg>\n</div>\n</body>\n</html>\n";
      File outFile = new File(this.out);
      assertTrue(outFile.exists());
      StringBuilder actual = new StringBuilder();
      try (
              Scanner output = new Scanner(outFile);
      ) {
        while (output.hasNextLine()) {
          actual.append(output.nextLine());
          actual.append("\n");
        }
      }
      assertEquals(expectedOutput, actual.toString());

      // Test output from the graphical controller
      expectedOutput = "test test shape uniqueShape oval 123 456 78 90 100 99 98";
      String[] shapeDetails = this.graphicalView.getShapeDetails();
      String[] shape = shapeDetails[0].split(" ");
      // Sample shape: {"shape", "O", "oval", "500", "400", "60", "30", "1", "1", 1"}
      String actualOutput = this.graphicalView.getSnapshotId() + " "
              + this.graphicalView.getSnapshotDescription() + " ";
      for (String each : shape) {
        actualOutput += each + " ";
      }
      assertEquals(expectedOutput, actualOutput.strip());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}