package helpertest;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.helper.AlbumBuilder;
import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.PhotoAlbumModel;

/**
 * JUnit test of AlbumBuilder helper class from the controller package.
 */
public class AlbumBuilderTest {
  private File sourceOne;
  private IPhotoAlbumModel modelOne;
  private File sourceTwo;
  private IPhotoAlbumModel modelTwo;
  /**
   * Initializes an AlbumBuilder object.
   */
  @Before
  public void setUp() {
    // Test case one using demo_input.txt as input
    this.sourceOne = new File("test/commandfiles/web_one.txt");
    this.modelOne = new PhotoAlbumModel();

    // Test case two using buildings.txt as input
    this.sourceTwo = new File("test/commandfiles/web_two.txt");
    this.modelTwo = new PhotoAlbumModel();
  }

  /**
   * Test setUpAlbum method.
   */
  @Test
  public void testSetUpAlbum() {
    AlbumBuilder.setUpAlbum(this.sourceOne, this.modelOne);
    String expected = "Name: head\n"
            + "Type: oval\n"
            + "Center: (350,350), X radius: 200, Y radius: 200, Color: (255,255,0)\n"
            + "Name: brow1\n"
            + "Type: rectangle\n"
            + "Min corner: (395,390), Width: 20, Height: 10, Color: (0,0,0)\n"
            + "Name: brow2\n"
            + "Type: rectangle\n"
            + "Min corner: (475,390), Width: 20, Height: 10, Color: (0,0,0)\n"
            + "Name: left\n"
            + "Type: oval\n"
            + "Center: (400,425), X radius: 15, Y radius: 15, Color: (0,0,0)\n"
            + "Name: right\n"
            + "Type: oval\n"
            + "Center: (480,425), X radius: 15, Y radius: 15, Color: (0,0,0)\n"
            + "Name: mouth\n"
            + "Type: oval\n"
            + "Center: (420,475), X radius: 90, Y radius: 25, Color: (0,0,0)\n"
            + "Name: mouth2\n"
            + "Type: oval\n"
            + "Center: (400,465), X radius: 100, Y radius: 25, Color: (255,255,0)\n";
    assertEquals(expected, this.modelOne.toString());

    AlbumBuilder.setUpAlbum(this.sourceTwo, this.modelTwo);
    expected = "Name: SO1\n"
            + "Type: oval\n"
            + "Center: (40,70), X radius: 200, Y radius: 15, Color: (0,0,255)\n";
    assertEquals(expected, this.modelTwo.toString());
  }
}