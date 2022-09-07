package shapesphotoalbum.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

/**
 * This class represents the graphic view for the Shape Photo Album. It works with the
 * PhotoAlbumGraphicalController and the IPhotoAlbumModel to represents the photo album
 * in graphical/GUI format.
 */
public class PhotoAlbumGraphicalView extends JFrame implements IPhotoAlbumView {
  private PhotoPanel photoPanel;
  private HeaderPanel head;
  private ButtonPanel menu;
  private int xmax;
  private int ymax;
  private String snapshot;
  private static final int MAX = 1000;

  /**
   * Constructs the class.
   * @param xmax the maximum width of the window
   * @param ymax the maximum height of the window
   */
  public PhotoAlbumGraphicalView(String xmax, String ymax) {
    // If no specification of xmax or ymax, set to default values
    if (xmax.equals("") || ymax.equals("")) {
      this.xmax = MAX;
      this.ymax = MAX;
    } else {
      this.xmax = Integer.parseInt(xmax);
      this.ymax = Integer.parseInt(ymax);
    }
    setSize(this.xmax, this.ymax);
    this.setTitle("cs5004 Shapes Photo Album Viewer");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add menu panel with buttons through the ButtonPanel object
    this.menu = new ButtonPanel();
    this.add(this.menu, BorderLayout.SOUTH);
  }

  @Override
  public void paint() {
    // Paint photos of given snapshot through the PhotoPanel object
    this.photoPanel = new PhotoPanel(this.snapshot);
    this.add(this.photoPanel);
    // Add header panel with snapshot id and/or description through the HeaderPanel object
    this.head = new HeaderPanel(this.snapshot);
    this.add(this.head, BorderLayout.NORTH);
  }

  @Override
  public void repaint() {
    // Repaint photos of new snapshot
    this.photoPanel.setShapeDetails(this.snapshot);
    this.photoPanel.repaint();
    this.add(this.photoPanel);
    // Repaint header panel with updated snapshot id and/or description
    this.head.resetHeader(this.snapshot);
    this.add(this.head, BorderLayout.NORTH);
  }

  @Override
  public void setSnapshotString(String snapshot) throws IllegalArgumentException {
    if (snapshot == null || snapshot.equals("")) {
      throw new IllegalArgumentException("Snapshot input to the view cannot be null or empty.");
    }
    this.snapshot = snapshot;
  }

  /**
   * Sets the ActionListener.
   * @param listener the action listener
   */
  public void setActionListener(ActionListener listener) {
    this.menu.setListener(listener);
  }

  /**
   * Returns the user selection of snapshots from the pop-up menu of the view.
   * @param snapshotList the list of snapshot strings
   * @return the index of snapshot list indicating the user selection
   */
  public int popSelection(List<String> snapshotList) {
    Object[] selections = snapshotList.toArray();
    // The default selection is the current presenting snapshot
    String answer = (String) JOptionPane.showInputDialog(this,
            "Choose a snapshot:", "Menu", JOptionPane.PLAIN_MESSAGE, null,
            selections, selections[0]);
    return snapshotList.indexOf(answer);
  }

  /**
   * Returns the parsed shape details from the photo panel.
   * Helper method for testing graphical controller output.
   * @return the String array of shape details
   */
  public String[] getShapeDetails() {
    return this.photoPanel.getShapeDetails();
  }

  /**
   * Returns snapshot ID from the header panel.
   * Helper method for testing graphical controller output.
   * @return snapshot ID
   */
  public String getSnapshotId() {
    return this.head.getSnapId();
  }

  /**
   * Returns snapshot description from the header panel.
   * Helper method for testing graphical controller output.
   * @return snapshot description
   */
  public String getSnapshotDescription() {
    return this.head.getDescription();
  }
}
