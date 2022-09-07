package shapesphotoalbum.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;

import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.helper.AlbumBuilder;
import shapesphotoalbum.view.PhotoAlbumGraphicalView;

/**
 * This class represents a PhotoAlbumGraphicalController. It connects and communicates between
 * the PhotoAlbumModel and PhotoAlbumGraphicalView.
 */
public class PhotoAlbumGraphicalController extends AbstractController implements ActionListener {
  private PhotoAlbumGraphicalView view;
  private int currentIndex;
  private List<String> snapshotList;

  /**
   * Constructs the PhotoAlbumGraphicalController object.
   * @param source the source command file
   * @param model the model
   * @param view the graphical view
   */
  public PhotoAlbumGraphicalController(File source, IPhotoAlbumModel model,
                                       PhotoAlbumGraphicalView view) {
    super(source, model, view);
    this.view = view;
    this.view.setActionListener(this);
    this.currentIndex = 0;
  }

  @Override
  public void go() {
    try {
      // Call the AlbumBuilder object to initialize and build the album as instructed
      AlbumBuilder.setUpAlbum(this.sourceFile, this.model);
      // Draw the first/default snapshot
      this.snapshotList = this.model.getSnapshotList();
      if (!this.snapshotList.isEmpty()) {
        String initialSnapId = this.snapshotList.get(this.currentIndex);
        String initialSnapshot = this.model.getSnapshot(initialSnapId);
        this.view.setSnapshotString(initialSnapshot);
        this.view.paint();
        this.view.setVisible(true);
      }
    } catch(IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Prev":
        this.currentIndex -= 1;
        if (this.currentIndex < 0) {
          JOptionPane.showMessageDialog(this.view, "Beginning of the photo album."
                  + " No snapshots to show before this one.");
          this.currentIndex = 0;
          break;
        }
        String currentSnapId = this.snapshotList.get(this.currentIndex);
        String currentSnapshot = this.model.getSnapshot(currentSnapId);
        this.view.setSnapshotString(currentSnapshot);
        this.view.repaint();
        this.view.setVisible(true);
        break;
      case "Select":
        int answer = this.view.popSelection(this.snapshotList);
        if (answer < 0) {
          break;
        }
        this.currentIndex = answer;
        currentSnapId = this.snapshotList.get(this.currentIndex);
        currentSnapshot = this.model.getSnapshot(currentSnapId);
        this.view.setSnapshotString(currentSnapshot);
        this.view.repaint();
        this.view.setVisible(true);
        break;
      case "Next":
        this.currentIndex += 1;
        if (this.currentIndex >= this.snapshotList.size()) {
          JOptionPane.showMessageDialog(this.view, "End of the photo album."
                  + " No snapshots to show beyond this one.");
          this.currentIndex -= 1;
          break;
        }
        currentSnapId = this.snapshotList.get(this.currentIndex);
        currentSnapshot = this.model.getSnapshot(currentSnapId);
        this.view.setSnapshotString(currentSnapshot);
        this.view.repaint();
        this.view.setVisible(true);
        break;
      case "Quit":
        System.exit(0);
    }
  }
}
