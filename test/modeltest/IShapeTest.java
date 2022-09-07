package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.model.IShape;
import shapesphotoalbum.model.Oval;
import shapesphotoalbum.model.Rectangle;

/**
 * JUnit test of shape classes implemented IShape interface.
 */
public class IShapeTest {
  private IShape oval;
  private IShape rectangle;

  /**
   * Initializes shape objects that implemented IShape interface.
   */
  @Before
  public void setUp() {
    this.oval = new Oval("O", 100, 200,
            10, 20, 1, 0, 0);
    this.rectangle = new Rectangle("r", 1001, 70,
            50, 60, 0, 0, 1);
  }

  /**
   * Test constructors of the shape classes.
   */
  @Test
  public void testShape() {
    String expected = "Name: O\n"
            + "Type: oval\n"
            + "Center: (100,200), "
            + "X radius: 10, Y radius: 20, "
            + "Color: (1,0,0)\n";
    assertEquals(expected, this.oval.toString());

    expected = "Name: r\n"
            + "Type: rectangle\n"
            + "Min corner: (1001,70), "
            + "Width: 50, Height: 60, "
            + "Color: (0,0,1)\n";
    assertEquals(expected, this.rectangle.toString());
  }

  /**
   * Test invalid input to shape constructors.
   */
  @Test
  public void testInvalidShape() {
    // Test empty name
    try {
      IShape invalid = new Rectangle("", 1, 2,
              1, 2, 0, 0, 1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    // Test null name
    try {
      IShape invalid = new Rectangle(null, 1, 2,
              1, 2, 0, 0, 1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    // Test zero or negative size
    try {
      IShape invalid = new Rectangle("invalid", 1, 2,
              -1, 1, 0, 0, 1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      IShape invalid = new Rectangle("", 1, 2,
              1, 0, 0, 0, 1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    // Test color with negative value
    try {
      IShape invalid = new Rectangle("invalid", 1, 2,
              1, 2, -1, 0, -1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    // Test color with values above 255
    try {
      IShape invalid = new Rectangle("invalid", 1, 2,
              1, 2, 1, 256, 1);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Test setLocation method.
   */
  @Test
  public void testSetLocation() {
    this.oval.setLocation(-10, -20);
    assertEquals(-10, this.oval.getX(), 0.1);
    assertEquals(-20, this.oval.getY(), 0.1);

    this.rectangle.setLocation(200, 100);
    assertEquals(200, this.rectangle.getX(), 0.1);
    assertEquals(100, this.rectangle.getY(), 0.1);
  }

  /**
   * Test setSize method.
   */
  @Test
  public void testSetSize() {
    this.oval.setSize(1, 2);
    assertEquals(1, this.oval.getSizeOne(), 0.01);
    assertEquals(2, this.oval.getSizeTwo(), 0.01);

    this.rectangle.setSize(16,15);
    assertEquals(16, this.rectangle.getSizeOne(), 0.01);
    assertEquals(15, this.rectangle.getSizeTwo(), 0.01);
  }

  /**
   * Test invalid input to setSize method.
   */
  @Test
  public void testInvalidSetSize() {
    // Test zero or negative size
    try {
      this.oval.setSize(-19, 10);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
    try {
      this.rectangle.setSize(1, 0);
      fail("Failed to catch exceptions.");
    }
    catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  /**
   * Test setColor method.
   */
  @Test
  public void testSetColor() {
    this.oval.setColor(100, 200, 3);
    assertEquals(100, this.oval.getRed(), 0.1);
    assertEquals(200, this.oval.getGreen(), 0.1);
    assertEquals(3, this.oval.getBlue(), 0.1);


    this.rectangle.setColor(1, 1, 0);
    assertEquals(1, this.rectangle.getRed(), 0.1);
    assertEquals(1, this.rectangle.getGreen(), 0.1);
    assertEquals(0, this.rectangle.getBlue(), 0.1);
  }

  /**
   * Test invalid input to setColor.
   */
  @Test
  public void testInvalidSetColor() {
    // Test invalid color
    try {
      this.rectangle.setColor(-1, 0, 0);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
    try {
      this.oval.setColor(1, 0, 256);
      fail("Failed to catch the exception.");
    }
    catch (IllegalArgumentException e) {
      // Passed test
    }
  }

  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("O", this.oval.getName());
    assertEquals("r", this.rectangle.getName());
  }

  /**
   * Test getLocation method.
   */
  @Test
  public void testGetLocation() {
    assertEquals(100, this.oval.getX(), 0.1);
    assertEquals(200, this.oval.getY(), 0.1);

    assertEquals(1001, this.rectangle.getX(), 0.1);
    assertEquals(70, this.rectangle.getY(), 0.1);
  }

  /**
   * Test getSizeOne method.
   */
  @Test
  public void testGetSizeOne() {
    assertEquals(10, this.oval.getSizeOne(), 0.01);
    assertEquals(20, this.oval.getSizeTwo(), 0.01);
  }

  /**
   * Test getSizeTwo method.
   */
  @Test
  public void testGetSizeTwo() {
    assertEquals(50, this.rectangle.getSizeOne(), 0.01);
    assertEquals(60, this.rectangle.getSizeTwo(), 0.01);
  }

  /**
   * Test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals(1, this.oval.getRed(), 0.1);
    assertEquals(0, this.oval.getGreen(), 0.1);
    assertEquals(0, this.oval.getBlue(), 0.1);

    assertEquals(0, this.rectangle.getRed(), 0.1);
    assertEquals(0, this.rectangle.getGreen(), 0.1);
    assertEquals(1, this.rectangle.getBlue(), 0.1);
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    String expected = "Name: O\n"
            + "Type: oval\n"
            + "Center: (100,200), "
            + "X radius: 10, Y radius: 20, "
            + "Color: (1,0,0)\n";
    assertEquals(expected, this.oval.toString());

    expected = "Name: r\n"
            + "Type: rectangle\n"
            + "Min corner: (1001,70), "
            + "Width: 50, Height: 60, "
            + "Color: (0,0,1)\n";
    assertEquals(expected, this.rectangle.toString());
  }
}