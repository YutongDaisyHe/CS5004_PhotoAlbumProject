package shapesphotoalbum.view;

/**
 * This is the interface of the Shape Photo Album View. It provides protocols for
 * the current PhotoAlbumGraphicView class and PhotoAlbumWebView class.
 */
public interface IPhotoAlbumView {

  /**
   * Displays shape photos through a specific view.
   */
  void paint();

  /**
   * Sets and updates the snapshot info for the view.
   * @param snapshot the snapshot info about the snapshot to be displayed
   * @throws IllegalArgumentException if the input is empty or null
   */
  void setSnapshotString(String snapshot) throws IllegalArgumentException;
}
