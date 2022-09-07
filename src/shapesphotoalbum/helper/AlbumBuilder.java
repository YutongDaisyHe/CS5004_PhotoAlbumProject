package shapesphotoalbum.helper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import shapesphotoalbum.model.IPhotoAlbumModel;

/**
 * This class represents an AlbumBuilder object. It works as a helper class to set up
 * the photo album according to the read-in instructions from the txt file.
 * It is used in both PhotoAlbumGraphicalController and PhotoAlbumWebController.
 * This class uses factory design and has only one method.
 */
public class AlbumBuilder {
  private static final int COMMAND = 0;
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
   * Set up the album model according to the read-in source file.
   */
  public static void setUpAlbum(File sourceFile, IPhotoAlbumModel model) {
    try (
            Scanner input = new Scanner(sourceFile);
    ) {
      // Read data from sourceFile to create an album with specified shapes and snapshots
      while (input.hasNextLine()) {
        String[] line = (input.nextLine().strip()).split("\\s+");
        switch (line[COMMAND].toLowerCase()) {
          case "#":
            break;
          case "shape":
            model.createShape(line[ID], line[SHAPE],
                    Integer.parseInt(line[X]), Integer.parseInt(line[Y]),
                    Integer.parseInt(line[SIZEONE]), Integer.parseInt(line[SIZETWO]),
                    Integer.parseInt(line[R]), Integer.parseInt(line[G]),
                    Integer.parseInt(line[B]));
            break;
          case "move":
            model.moveShape(line[ID], Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            break;
          case "color":
            model.recolorShape(line[ID], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
                    Integer.parseInt(line[4]));
            break;
          case "resize":
            model.resizeShape(line[ID], Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            break;
          case "remove":
            model.removeShape(line[ID]);
            break;
          case "snapshot":
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < line.length; i++) {
              description.append(line[i] + " ");
            }
            model.takeSelfie(description.toString());
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}