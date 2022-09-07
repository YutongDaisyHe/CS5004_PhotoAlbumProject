package shapesphotoalbum.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a helper class to parse the input command line to obtain -in source file,
 * -view/-v view type, optional -out html file, xmax, and ymax.
 * It is used in the PhotoAlbumMain class inside the shapesphotoalbum package.
 */
public class CommandInputParser {
  private String sourceFile;
  private String viewType;
  private String html;
  private String xmax;
  private String ymax;
  private final static String patternIn = "(?<in>-in\\s(?<sourceFile>[\\w\\/\\w]+(.txt)))";
  private final static String patternV = "(?<view>-v(iew)?\\s(?<viewType>[\\w]+))";
  private final static String patternOut = "(?<out>-out\\s(?<html>[\\w\\/\\.]+))";
  private final static String patternMax = "((?<xmax>[0-9]+)\\s(?<ymax>[0-9]+))";

  /**
   * Constructor of the CommandInputParser helper class.
   * @param input the string read in from console
   * @throws IllegalArgumentException if the input is null or empty.
   */
  public CommandInputParser(String input) throws IllegalArgumentException {
    if (input == null || input.equals("")) {
      throw new IllegalArgumentException("Input command line cannot be empty or null.");
    }
    // Create pattern objects
    Pattern rIn = Pattern.compile(patternIn);
    Pattern rV = Pattern.compile(patternV);
    Pattern rOut = Pattern.compile(patternOut);
    Pattern rMax = Pattern.compile(patternMax);

    // Create matcher objects
    Matcher mIn = rIn.matcher(input);
    Matcher mV = rV.matcher(input);
    Matcher mOut = rOut.matcher(input);
    Matcher mMax = rMax.matcher(input);

    // Parse each matching group
    if (mIn.find()) {
      this.sourceFile = mIn.group("sourceFile");
    } else {
      this.sourceFile = "";
    }
    if (mV.find()) {
      this.viewType = mV.group("viewType");
    } else {
      this.viewType = "";
    }
    if (mOut.find()) {
      this.html = mOut.group("html");
    } else {
      this.html = "";
    }
    if (mMax.find()) {
      this.xmax = mMax.group("xmax");
      this.ymax = mMax.group("ymax");
    } else {
      this.xmax = "";
      this.ymax = "";
    }
  }

  /**
   * Returns sourceFile.
   * @return sourceFile
   */
  public String getSourceFile() {
    return this.sourceFile;
  }

  /**
   * Returns viewType.
   * @return viewType
   */
  public String getViewType() {
    return this.viewType;
  }

  /**
   * Returns html.
   * @return html
   */
  public String getHtml() {
    return this.html;
  }

  /**
   * Returns xmax.
   * @return xmax
   */
  public String getXmax() {
    return this.xmax;
  }

  /**
   * Returns ymax.
   * @return ymax
   */
  public String getYmax() {
    return this.ymax;
  }
}
