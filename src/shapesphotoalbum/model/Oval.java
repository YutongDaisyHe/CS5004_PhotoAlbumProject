package shapesphotoalbum.model;

/**
 * This class represents an Oval object. It extends AbstractShape
 * and implements IShape interface.
 */
public class Oval extends AbstractShape {
  /**
   * Constructs the Oval class.
   * @param name the name of the Oval shape
   * @param x the x-axis value of the center point
   * @param y the y-axis value of the center point
   * @param xRadius the x radius of the shape
   * @param yRadius the y radius
   * @param r the red
   * @param g the green
   * @param b the blue
   */
  public Oval(String name, int x, int y,
              int xRadius, int yRadius, int r, int g, int b) {
    super(name, x, y, xRadius, yRadius, r, g, b);
  }

  @Override
  public String getType() {
    return "oval";
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getType() + "\n"
            + "Center: (" + this.getX() + "," + this.getY() + ")" + ", "
            + String.format("X radius: %d, Y radius: %d, ", this.getSizeOne(), this.getSizeTwo())
            + "Color: (" + this.getRed() + ","
            + this.getGreen() + ","
            + this.getBlue() + ")\n";
  }
}
