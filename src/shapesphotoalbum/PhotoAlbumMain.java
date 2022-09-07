package shapesphotoalbum;

import java.io.File;

import shapesphotoalbum.controller.IPhotoAlbumController;
import shapesphotoalbum.controller.PhotoAlbumGraphicalController;
import shapesphotoalbum.controller.PhotoAlbumWebController;
import shapesphotoalbum.model.IPhotoAlbumModel;
import shapesphotoalbum.model.PhotoAlbumModel;
import shapesphotoalbum.helper.CommandInputParser;
import shapesphotoalbum.view.PhotoAlbumGraphicalView;
import shapesphotoalbum.view.PhotoAlbumWebView;

/**
 * Entry-point for the Shape Photo Album application. It hands most of the control over to MVC.
 */
public class PhotoAlbumMain {
  public static void main(String[] args) {
    try {
      // Read in command line
      if (args.length < 4) {
        System.out.println("Usage: java MyProgram -in name-of-command-file -view type-of-view "
                + "[-out where-output-should-go] [xmax] [ymax]");
        System.exit(1);
      }

      // Parse the command line
      StringBuilder input = new StringBuilder();
      for (String each : args) input.append(each).append(" ");
      CommandInputParser parser = new CommandInputParser(input.toString());
      String file = parser.getSourceFile();
      String viewType = parser.getViewType();
      // Check if mandatory -in pair and -view pair exist
      if (file.equals("") || viewType.equals("")) {
        System.out.println("An input file (the -in pair) and a view "
                + "(the -view pair) are mandatory.");
        System.exit(2);
      }
      String outFile = parser.getHtml();
      // Check if -out set is given for web view
      if (viewType.equalsIgnoreCase("web") && outFile.equals("")) {
        System.out.println("Web view type requests an -out set");
        System.exit(3);
      }
      String xmax = parser.getXmax();
      String ymax = parser.getYmax();

      // Read in source file
      File sourceFile = new File(file);
      if (!sourceFile.exists()) {
        System.out.println("Source file " + file + " does not exist.");
        System.exit(4);
      }

      // Initialize MVC
      IPhotoAlbumModel model = new PhotoAlbumModel();
      IPhotoAlbumController controller;
      switch (viewType.toLowerCase()) {
        case "graphical":
          PhotoAlbumGraphicalView graphicalView = new PhotoAlbumGraphicalView(xmax, ymax);
          controller = new PhotoAlbumGraphicalController(sourceFile, model, graphicalView);
          break;
        case "web":
          PhotoAlbumWebView webView = new PhotoAlbumWebView(xmax, ymax, outFile);
          controller = new PhotoAlbumWebController(sourceFile, model, webView);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + viewType.toLowerCase());
      }
      controller.go();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
