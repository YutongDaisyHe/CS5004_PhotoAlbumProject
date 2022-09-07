package shapesphotoalbum.view;

import java.awt.*;
import javax.swing.*;
import shapesphotoalbum.helper.SnapshotParser;

/**
 * This class represents a HeaderPanel object. It works as a helper class to display the id
 * and description of snapshot to the graphical view window.
 * It is used in the PhotoAlbumGraphicalView class.
 */
public class HeaderPanel extends JPanel {
  private String snapId;
  private String description;
  private JLabel idLabel;
  private JLabel desLabel;

  /**
   * Constructs a HeaderPanel class.
   * @param snapshot a snapshot
   */
  public HeaderPanel(String snapshot) {
    this.setBackground(Color.PINK);
    // Set the id and description text label
    SnapshotParser parser = new SnapshotParser(snapshot);
    this.setLayout(new GridLayout(1, 1));
    this.snapId = parser.getSnapId();
    this.idLabel = new JLabel(this.snapId, SwingConstants.LEFT);
    this.add(this.idLabel);

    this.description = parser.getDescription();
    this.desLabel = new JLabel(this.description, SwingConstants.LEFT);
    if (!this.description.equals("")) {
      this.setLayout(new GridLayout(2, 1));
      this.add(this.desLabel);
    }
  }

  /**
   * Resets and updates the id and description information during the repaint process of
   * the graphical view.
   * @param snapshot the updated snapshot
   */
  public void resetHeader(String snapshot) {
    SnapshotParser parser = new SnapshotParser(snapshot);
    // Update snapshot id
    this.setLayout(new GridLayout(1, 1));
    this.snapId = parser.getSnapId();
    this.idLabel.setText(this.snapId);
    this.add(this.idLabel);
    // Update description
    this.description = parser.getDescription();
    if (!this.description.equals("")) {
      this.setLayout(new GridLayout(2, 1));
    }
    this.desLabel.setText(this.description);
    this.add(this.desLabel);
  }

  /**
   * Returns snapshot ID. Helper method for testing graphical controller output.
   * @return snapshot ID
   */
  public String getSnapId() {
    return this.snapId;
  }

  /**
   * Returns snapshot description. Helper method for testing graphical controller output.
   * @return snapshot description
   */
  public String getDescription() {
    return this.description;
  }
}
