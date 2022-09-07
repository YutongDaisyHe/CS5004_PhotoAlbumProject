package shapesphotoalbum.controller;

import java.io.File;

import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.view.PhotoAlbumGraphicalView;
import shapesphotoalbum.view.PhotoAlbumWebView;

/**
 * This abstract class implements IPhotoAlbumController interface. The PhotoAlbumWebController
 * and PhotoAlbumGraphicalController inherits it.
 */
public abstract class AbstractController implements IPhotoAlbumController {
  protected File sourceFile;
  protected IPhotoAlbumModel model;

  /**
   * Constructs the AbstractController for a web controller object.
   * @param source the source command file
   * @param model the model
   * @param view the web view
   * @throws IllegalArgumentException if the input is null
   */
  public AbstractController(File source, IPhotoAlbumModel model, PhotoAlbumWebView view)
          throws IllegalArgumentException {
    if (source == null || model == null || view == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    this.sourceFile = source;
    this.model = model;
  }

  /**
   * Overloads the AbstractController for a graphical controller object.
   * @param source the source command file
   * @param model the model
   * @param view the graphical view
   * @throws IllegalArgumentException if the input is null
   */
  public AbstractController(File source, IPhotoAlbumModel model, PhotoAlbumGraphicalView view)
          throws IllegalArgumentException {
    if (source == null || model == null || view == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    this.sourceFile = source;
    this.model = model;
  }
}
