package shapesphotoalbum.helper;

/**
 * This class represents a HtmlAlbumAdapter object. It works as a helper adapter class to transform
 * the text formatted album into a html formatted album representing one snapshot from the shape
 * album.
 * It is used in the PhotoAlbumWebView class.
 */
public class HtmlAlbumAdapter {
  private String[] shapeDetails;
  private String id;
  private String description;
  private String htmlAlbum;
  private static final int ID = 1;
  private static final int SHAPE = 2;
  private static final int X = 3;
  private static final int Y = 4;
  private static final int SIZEONE = 5;
  private static final int SIZETWO = 6;
  private static final int R = 7;
  private static final int G = 8;
  private static final int B = 9;

  /**
   * Constructs the HtmlAlbumAdapter class.
   * @param snapshot a snapshot string
   */
  public HtmlAlbumAdapter(String snapshot) {
    SnapshotParser parser = new SnapshotParser(snapshot);
    this.shapeDetails = parser.getShapeInfoList();
    this.id = parser.getSnapId().strip();
    this.description = parser.getDescription().strip();
    this.htmlAlbum = "";
  }

  /**
   * Creates a html formatted string representation of one snapshot of an album.
   */
  public void makeHtmlAlbum() {
    String[] shape;
    // Paint photos from the selected snapshot
    for (int i = 0; i < shapeDetails.length; i++) {
      shape = shapeDetails[i].split(" ");
      // Sample shape: {"shape", "O", "oval", "500", "400", "60", "30", "1", "1", 1"}
      switch (shape[SHAPE].toLowerCase()) {
        case "oval":
          String ellipse = "\t\t<ellipse id='" + shape[ID]
                  + "' cx='" + shape[X] + "' cy='" + shape[Y]
                  + "' rx='" + shape[SIZEONE] + "' ry='" + shape[SIZETWO] + "' "
                  + "fill='rgb(" + shape[R] + "," + shape[G] + "," + shape[B]
                  + ")'>\n\t\t</ellipse>\n";
          this.htmlAlbum += ellipse;
          break;
        case "rectangle":
          String rect = "\t\t<rect id='" + shape[ID]
                  + "' x='" + shape[X] + "' y='" + shape[Y]
                  + "' width='" + shape[SIZEONE] + "' height='" + shape[SIZETWO] + "' "
                  + "fill='rgb(" + shape[R] + "," + shape[G] + "," + shape[B]
                  + ")'>\n\t\t</rect>\n";
          this.htmlAlbum += rect;
      }
    }
  }

  /**
   * Returns the html formatted album string.
   * @return the html formatted string representing a snapshot of an album
   */
  public String getHtmlAlbum() {
    return this.htmlAlbum;
  }

  /**
   * Returns the html formatted id and description of the snapshot.
   * @return the html formatted id and description of the snapshot
   */
  public String getHtmlIdDes() {
    return "\t<h2>" + this.id + "</h2>\n" + "\t<p>Description: " + this.description + "</p>\n";
  }
}
