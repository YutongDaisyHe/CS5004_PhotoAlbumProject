package shapesphotoalbum.controller;

import java.io.File;

import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.helper.AlbumBuilder;
import shapesphotoalbum.view.PhotoAlbumWebView;

/**
 * This class represents a PhotoAlbumWebController. It connects and communicates between
 * the PhotoAlbumModel and PhotoAlbumWebView.
 */
public class PhotoAlbumWebController extends AbstractController {
  private PhotoAlbumWebView view;

  /**
   * Constructs the PhotoAlbumWebController object.
   * @param source the source command file
   * @param model the model
   * @param view the web view
   */
  public PhotoAlbumWebController(File source, IPhotoAlbumModel model, PhotoAlbumWebView view) {
    super(source, model, view);
    this.view = view;
  }

  @Override
  public void go() {
    // Call the AlbumBuilder object to initialize and build the album as instructed
    AlbumBuilder.setUpAlbum(this.sourceFile, this.model);
    String allSnapshots = this.model.getSnapshotDetails();
    this.view.setSnapshotString(allSnapshots);
    this.view.paint();
  }
}
