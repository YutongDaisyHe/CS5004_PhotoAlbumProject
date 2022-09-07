package shapesphotoalbum.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a helper class to parse details of a snapshot to obtain information for drawing
 * graphics presented in both web and graphical Views.
 * It is used in HeaderPanel class, HtmlAlbumAdapter class, and PhotoPanel class inside the view
 * package.
 */
public class SnapshotParser {
  private String snapId;
  private String description;
  private String shapeInfo;
  private String[] shapeInfoList;
  private final static String patternSnapId = "(Snapshot ID: )(?<snapshotId>(.+?))\\n";
  private final static String patternDescription = "(Description: )(?<description>(.+?))\\n";
  private final static String patternShape
          = "(Shape Information:\\n)(?<shape>[\\w\\:\\s\\(\\.\\,\\)]+)";
  private final static String patternType = "(Type: )(?<type>[\\w]+)";
  private final static String patternShapeDetail = "(\\((?<x>[0-9\\.]+),(?<y>[0-9\\.]+)"
          + "[\\)],\\s[\\w\\s]+:\\s(?<sizeOne>[0-9\\.]+),\\s[\\w\\s]+:\\s(?<sizeTwo>[0-9\\.]+)"
          + ",\\sColor:\\s\\((?<r>[0-9\\\\.]+),(?<g>[0-9\\.]+),(?<b>[0-9\\.]+)\\))";

  /**
   * Constructor of the SnapshotParser helper class.
   * @param input the string of details about a snapshot
   * @throws IllegalArgumentException if the input is null or empty.
   */
  public SnapshotParser(String input) throws IllegalArgumentException {
    if (input == null || input.equals("")) {
      throw new IllegalArgumentException("Input about the snapshot cannot be empty or null.");
    }

    // Create pattern objects
    Pattern rId = Pattern.compile(patternSnapId);
    Pattern rDes = Pattern.compile(patternDescription);
    Pattern rShape = Pattern.compile(patternShape);

    // Create matcher objects
    Matcher mId = rId.matcher(input);
    Matcher mDes = rDes.matcher(input);
    Matcher mShape = rShape.matcher(input);

    // Parse each matching group
    if (mId.find()) {
      this.snapId = mId.group("snapshotId");
    } else {
      this.snapId = "";
    }
    if (mDes.find()) {
      this.description = mDes.group("description");
    } else {
      this.description = "";
    }
    if (mShape.find()) {
      this.shapeInfo = mShape.group("shape");
    } else {
      this.shapeInfo = "";
    }
  }

  /**
   * Returns snapshot id.
   * @return snapshot id
   */
  public String getSnapId() {
    return this.snapId;
  }

  /**
   * Returns description.
   * @return description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns shapes info.
   * @return shapes info
   */
  public String getShapeInfo() {
    return this.shapeInfo;
  }

  /**
   * Returns the shape info string list.
   * @return the shape info string list
   */
  public String[] getShapeInfoList() {
    String[] temp = this.shapeInfo.split("Name: ", 0);
    this.shapeInfoList = new String[temp.length - 1];
    for (int i = 1; i < temp.length; i++) {
      String[] shapeDetail = temp[i].split("\\n");
      StringBuilder shape = new StringBuilder("shape");
      // Add the name
      shape.append(" ").append(shapeDetail[0]);
      // Match and add the shape type
      Pattern rType = Pattern.compile(patternType);
      Matcher mType = rType.matcher(shapeDetail[1]);
      mType.find();
      shape.append(" ").append(mType.group("type"));
      // Match and add the rest of the shape details
      Pattern rDetail = Pattern.compile(patternShapeDetail);
      Matcher mDetail = rDetail.matcher(shapeDetail[2]);
      mDetail.find();
      shape.append(" ").append(mDetail.group("x"));
      shape.append(" ").append(mDetail.group("y"));
      shape.append(" ").append(mDetail.group("sizeOne"));
      shape.append(" ").append(mDetail.group("sizeTwo"));
      shape.append(" ").append(mDetail.group("r"));
      shape.append(" ").append(mDetail.group("g"));
      shape.append(" ").append(mDetail.group("b"));
      // Sample string element in the list "shape O oval 500 400 60 30 1 1 1"
      this.shapeInfoList[i - 1] = shape.toString();
    }
    return this.shapeInfoList;
  }
}
