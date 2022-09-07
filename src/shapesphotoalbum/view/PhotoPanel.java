package shapesphotoalbum.view;

import java.awt.*;
import javax.swing.*;

import shapesphotoalbum.helper.SnapshotParser;

/**
 * This class represents a PhotoPanel object. It works as a helper class to read photo snapshot
 * information and transfer it into corresponding graphics drawing commands to display the photo
 * of snapshots to the graphical view window.
 * It is used in the PhotoAlbumGraphicalView class.
 */
public class PhotoPanel extends JPanel {
  private String[] shapeDetails;
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
   * Constructs a PhotoPanel class.
   * @param snapshot the input snapshot
   */
  public PhotoPanel(String snapshot) {
    SnapshotParser parser = new SnapshotParser(snapshot);
    this.shapeDetails = parser.getShapeInfoList();
    // Set the defaulted background as light blue
    this.setBackground(new Color(5, 153, 255));
  }

  /**
   * Paints in the graphical window and displays buttons, ids, and descriptions.
   * @param g the graphics object
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    String[] shape;
    // Paint photos from the selected snapshot
    for (int i = 0; i < shapeDetails.length; i++) {
      shape = shapeDetails[i].split(" ");
      // Sample shape: {"shape", "O", "oval", "500", "400", "60", "30", "1", "1", 1"}
      g.setColor(new Color(Integer.parseInt(shape[R]), Integer.parseInt(shape[G]),
              Integer.parseInt(shape[B])));
      switch (shape[SHAPE].toLowerCase()) {
        case "oval":
          g.drawOval(Integer.parseInt(shape[X]), Integer.parseInt(shape[Y]),
                  Integer.parseInt(shape[SIZEONE]), Integer.parseInt(shape[SIZETWO]));
          g.fillOval(Integer.parseInt(shape[X]), Integer.parseInt(shape[Y]),
                  Integer.parseInt(shape[SIZEONE]), Integer.parseInt(shape[SIZETWO]));
          break;
        case "rectangle":
          g.drawRect(Integer.parseInt(shape[X]), Integer.parseInt(shape[Y]),
                  Integer.parseInt(shape[SIZEONE]), Integer.parseInt(shape[SIZETWO]));
          g.fillRect(Integer.parseInt(shape[X]), Integer.parseInt(shape[Y]),
                  Integer.parseInt(shape[SIZEONE]), Integer.parseInt(shape[SIZETWO]));
      }
    }
  }

  /**
   * Resets and updates shape details.
   * @param snapshot the updated snapshot
   */
  public void setShapeDetails(String snapshot) {
    SnapshotParser parser = new SnapshotParser(snapshot);
    this.shapeDetails = parser.getShapeInfoList();
  }

  /**
   * Returns the shapeDetails. Helper method for testing graphical controller output.
   * @return the String array of shape details
   */
  public String[] getShapeDetails() {
    return this.shapeDetails;
  }
}
