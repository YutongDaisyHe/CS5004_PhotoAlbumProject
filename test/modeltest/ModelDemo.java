package modeltest;

import java.io.File;

import shapesphotoalbum.helper.AlbumBuilder;
import shapesphotoalbum.model.PhotoAlbumModel;

/**
 * Demonstrates the Model part of the ShapesPhotoAlbum application.
 * Shows the text output of the model to the terminal given an input command file.
 */
public class ModelDemo {
  public static void main(String[] args) {
    // Use web_one.txt file as a sample command file input
    File source = new File("test/commandfiles/web_one.txt");
    PhotoAlbumModel model = new PhotoAlbumModel();
    AlbumBuilder.setUpAlbum(source, model);
    // Print out the snapshot id list
    System.out.println(model.printSnapshotIds());
    // Print out the snapshot details
    System.out.println(model.getSnapshotDetails());
  }
}

