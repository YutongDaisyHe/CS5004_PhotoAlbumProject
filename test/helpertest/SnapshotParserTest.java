package helpertest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import shapesphotoalbum.helper.SnapshotParser;

/**
 * JUnit test on SnapshotParser helper class.
 */
public class SnapshotParserTest {
  private SnapshotParser regexOne;
  private SnapshotParser  regexTwo;
  private static final String inputOne = "Snapshot ID: 2022-04-12T17:46:33.675080\n"
          + "Timestamp: 12-04-2022 17:46:33\n"
          + "Description: \n"
          + "Shape Information:\n"
          + "Name: R\n"
          + "Type: rectangle\n"
          + "Min corner: (100,300), Width: 25, Height: 100, Color: (255,255,0)\n"
          + "Name: O\n"
          + "Type: oval\n"
          + "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n";
  private static final String inputTwo = "Snapshot ID: 2022-04-12T17:49:33.815065\n"
          + "Timestamp: 12-04-2022 17:49:33\n"
          + "Description: Selfie after removing the rectangle from the picture\n"
          + "Shape Information:\n"
          + "Name: O\n"
          + "Type: oval\n"
          + "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n";

  /**
   * Initializes SnapshotParser objects.
   */
  @Before
  public void setUp() throws Exception {
    this.regexOne = new SnapshotParser(inputOne);
    this.regexTwo = new SnapshotParser(inputTwo);
  }

  /**
   * Test SnapshotParser constructor.
   */
  @Test
  public void testSnapshotParser() {
    assertEquals("2022-04-12T17:46:33.675080", this.regexOne.getSnapId());
    assertEquals("2022-04-12T17:49:33.815065", this.regexTwo.getSnapId());

    assertEquals("", this.regexOne.getDescription());
    assertEquals("Selfie after removing the rectangle from the picture",
            this.regexTwo.getDescription());

    assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "Min corner: (100,300), Width: 25, Height: 100, Color: (255,255,0)\n" +
            "Name: O\n" +
            "Type: oval\n" +
            "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n",
            this.regexOne.getShapeInfo());
    assertEquals("Name: O\n"
            + "Type: oval\n"
            + "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n",
            this.regexTwo.getShapeInfo());
  }

  /**
   * Test getSnapId method.
   */
  @Test
  public void testGetSnapId() {
    assertEquals("2022-04-12T17:46:33.675080", this.regexOne.getSnapId());
    assertEquals("2022-04-12T17:49:33.815065", this.regexTwo.getSnapId());
  }

  /**
   * Test getDescription method.
   */
  @Test
  public void testGetDescription() {
    assertEquals("", this.regexOne.getDescription());
    assertEquals("Selfie after removing the rectangle from the picture",
            this.regexTwo.getDescription());
  }

  /**
   * Test getShapeInfo method.
   */
  @Test
  public void testGetShapeInfo() {
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (100,300), Width: 25, Height: 100, Color: (255,255,0)\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n",
            this.regexOne.getShapeInfo());
    assertEquals("Name: O\n"
            + "Type: oval\n"
            + "Center: (500,400), X radius: 60, Y radius: 30, Color: (1,1,1)\n",
            this.regexTwo.getShapeInfo());
  }

  /**
   * Test getShapeInfoList method.
   */
  @Test
  public void testGetShapeInfoList() {
    String[] expected = {"shape R rectangle 100 300 25 100 255 255 0",
            "shape O oval 500 400 60 30 1 1 1"};
    assertEquals(Arrays.stream(expected).toList(),
            Arrays.stream(this.regexOne.getShapeInfoList()).toList());
    assertEquals("shape R rectangle 100 300 25 100 255 255 0",
            this.regexOne.getShapeInfoList()[0]);
    assertEquals("shape O oval 500 400 60 30 1 1 1",
            this.regexOne.getShapeInfoList()[1]);
    expected = new String[]{"shape O oval 500 400 60 30 1 1 1"};
    assertEquals(Arrays.stream(expected).toList(),
            Arrays.stream(this.regexTwo.getShapeInfoList()).toList());
  }
}