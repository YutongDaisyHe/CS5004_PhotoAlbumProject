package shapesphotoalbum.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import shapesphotoalbum.helper.HtmlAlbumAdapter;

/**
 * This class represents the web view for the Shape Photo Album. It works with the
 * PhotoAlbumWebController and the IPhotoAlbumModel to represents the photo album
 * in html format.
 */
public class PhotoAlbumWebView implements IPhotoAlbumView {
  private String svgSize;
  private String[] snapshotList;
  private String htmlSnapshots;
  private HtmlAlbumAdapter album;
  private File htmlFile;
  private static final String SVGMAX = "\t<svg width='1000' height='1000'>\n";
  private static final String header = "<!DOCTYPE html>\n<html>\n<head>\n"
          + "<link rel=\"stylesheet\" href=\"styles.css\">\n</head>\n<body>\n"
          + "<h1>cs5004 Shapes Photo Album Web</h1>\n";
  private static final String footer = "</body>\n</html>\n";
  private static final String backgroundStyle = "style='background-color:skyblue;"
          + "border:4px solid red;margin:20px;padding:30px;'";

  /**
   * Constructs the web view class.
   */
  public PhotoAlbumWebView(String xmax, String ymax, String outFile) {
    // If no specification of xmax or ymax, set to default values
    if (xmax.equals("") || ymax.equals("")) {
      this.svgSize = SVGMAX;
    } else {
      this.svgSize = "\t<svg width='" + xmax + "' height='" + ymax + "'>\n";
    }
    this.htmlFile = new File(outFile);
  }

  /**
   * Helper method to write the html formatted snapshots to the target file.
   * @throws FileNotFoundException if the target file does not exist
   */
  private void writeHtml() throws FileNotFoundException {
    PrintWriter output = new PrintWriter(this.htmlFile);
    if (this.htmlFile.exists()) {
      output.append(this.htmlSnapshots);
    } else {
      output.println(this.htmlSnapshots);
    }
    output.close();
  }

  @Override
  public void paint() throws NullPointerException {
    if (this.snapshotList == null) {
      throw new NullPointerException("The snapshot list is empty.");
    }
    // Create html formatted snapshots
    this.htmlSnapshots = header;
    for (int i = 0; i < this.snapshotList.length; i++) {
      this.album = new HtmlAlbumAdapter(this.snapshotList[i]);
      this.album.makeHtmlAlbum();
      this.htmlSnapshots += "<div " + backgroundStyle + ">\n" + this.album.getHtmlIdDes()
              + this.svgSize + this.album.getHtmlAlbum() + "\t</svg>\n</div>\n";
    }
    this.htmlSnapshots += footer;
    // Call the writeHtml() helper method to write the html string into the target html file
    try {
      writeHtml();
    } catch (FileNotFoundException e) {
      System.out.println("The output file does not exist.");
    }
  }

  @Override
  public void setSnapshotString(String allSnapshots) throws IllegalArgumentException {
    if (allSnapshots == null || allSnapshots.equals("")) {
      throw new IllegalArgumentException("Snapshot input to the view cannot be null or empty.");
    }
    this.snapshotList = allSnapshots.split("\\n\\n");
  }

  /**
   * Returns svg size line.
   * Helper method for JUnit testing the web view class.
   * @return svg size line
   */
  public String getSvgSize() {
    return this.svgSize;
  }

  /**
   * Returns list of snapshot strings.
   * Helper method for testing web view class.
   * @return snapshot list of strings
   */
  public String[] getSnapshot() {
    return this.snapshotList;
  }

}
