package helpertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.helper.CommandInputParser;

/**
 * JUnit test on CommandInputParser helper class.
 */
public class CommandInputParserTest {
  private CommandInputParser regexOne;
  private CommandInputParser regexTwo;
  private CommandInputParser regexThree;
  private CommandInputParser regexFour;
  private static final String inputOne = "-in buildings.txt -out buildingsOut.html  -v web";
  private static final String inputTwo = "-in buildings.txt -v graphical 700 900";
  private static final String inputThree = "-view graphical -in buildings.txt";
  private static final String inputFour = "-v web -in buildings.txt -out buildingsOut.html";

  /**
   * Initializes CommandInputParser objects.
   */
  @Before
  public void setUp() {
    this.regexOne = new CommandInputParser(inputOne);
    this.regexTwo = new CommandInputParser(inputTwo);
    this.regexThree = new CommandInputParser(inputThree);
    this.regexFour = new CommandInputParser(inputFour);
  }

  /**
   * Test CommandInputParser constructor.
   */
  @Test
  public void testRegexParser() {
    // Check mandatory fields -in and -v/-view
    assertEquals("buildings.txt", this.regexOne.getSourceFile());
    assertEquals("buildings.txt", this.regexTwo.getSourceFile());
    assertEquals("buildings.txt", this.regexThree.getSourceFile());
    assertEquals("buildings.txt", this.regexFour.getSourceFile());

    assertEquals("web", this.regexOne.getViewType());
    assertEquals("graphical", this.regexTwo.getViewType());
    assertEquals("graphical", this.regexThree.getViewType());
    assertEquals("web", this.regexFour.getViewType());

    // Check optional -out and -xmax, -ymax
    assertEquals("buildingsOut.html", this.regexOne.getHtml());
    assertEquals("", this.regexOne.getXmax());
    assertEquals("", this.regexOne.getYmax());

    assertEquals("", this.regexTwo.getHtml());
    assertEquals("700", this.regexTwo.getXmax());
    assertEquals("900", this.regexTwo.getYmax());

    assertEquals("", this.regexThree.getHtml());
    assertEquals("", this.regexThree.getXmax());
    assertEquals("", this.regexThree.getYmax());

    assertEquals("buildingsOut.html", this.regexFour.getHtml());
    assertEquals("", this.regexFour.getXmax());
    assertEquals("", this.regexFour.getYmax());
  }

  /**
   * Test invalid input to CommandInputParser.
   */
  @Test
  public void testInvalidRegexParser() {
    // Input is null or empty
    try {
      CommandInputParser invalidRegex = new CommandInputParser("");
      fail("Failed to catch exception.");
    } catch (IllegalArgumentException e) {
      // Passed test
    }
    try {
      CommandInputParser invalidRegex = new CommandInputParser(null);
      fail("Failed to catch exception.");
    } catch (IllegalArgumentException e) {
      // Passed test
    }
  }

  /**
   * Test getSourceFile method.
   */
  @Test
  public void testGetSourceFile() {
    assertEquals("buildings.txt", this.regexOne.getSourceFile());
    assertEquals("buildings.txt", this.regexTwo.getSourceFile());
    assertEquals("buildings.txt", this.regexThree.getSourceFile());
    assertEquals("buildings.txt", this.regexFour.getSourceFile());
  }

  /**
   * Test getViewType method.
   */
  @Test
  public void testGetViewType() {
    assertEquals("web", this.regexOne.getViewType());
    assertEquals("graphical", this.regexTwo.getViewType());
    assertEquals("graphical", this.regexThree.getViewType());
    assertEquals("web", this.regexFour.getViewType());
  }

  /**
   * Test getHtml method.
   */
  @Test
  public void testGetHtml() {
    assertEquals("buildingsOut.html", this.regexOne.getHtml());
    assertEquals("", this.regexTwo.getHtml());
    assertEquals("", this.regexThree.getHtml());
    assertEquals("buildingsOut.html", this.regexFour.getHtml());
  }

  /**
   * Test getXmax method.
   */
  @Test
  public void testGetXmax() {
    assertEquals("", this.regexOne.getXmax());
    assertEquals("700", this.regexTwo.getXmax());
    assertEquals("", this.regexThree.getXmax());
    assertEquals("", this.regexFour.getXmax());
  }

  /**
   * Test getYmax method.
   */
  @Test
  public void testGetYmax() {
    assertEquals("", this.regexOne.getYmax());
    assertEquals("900", this.regexTwo.getYmax());
    assertEquals("", this.regexThree.getYmax());
    assertEquals("", this.regexFour.getYmax());
  }
}