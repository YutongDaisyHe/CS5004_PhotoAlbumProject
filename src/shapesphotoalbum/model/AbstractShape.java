package shapesphotoalbum.model;

/**
 * This abstract class AbstractShape implements IShape.
 */
public abstract class AbstractShape implements IShape {
  private String name;
  private int x;
  private int y;
  private int sizeOne;
  private int sizeTwo;
  private int r;
  private int g;
  private int b;

  /**
   * Constructs Shape objects.
   * @param name the String representation of the shape name
   * @param x the x-axis value of the shape location, origin is at the top left
   * @param y the y-axis value of the shape location, origin is at the top left
   * @param sizeOne the int of the first size value (width, x radius, etc.)
   * @param sizeTwo the int of the second size value (height, y radius, etc.)
   * @param r the red
   * @param g the green
   * @param b the blue
   * @throws IllegalArgumentException if the input is invalid
   */
  public AbstractShape(String name, int x, int y,
                       int sizeOne, int sizeTwo, int r, int g, int b)
          throws IllegalArgumentException {
    if (name == null || name.equals("")
            || sizeOne <= 0 || sizeTwo <= 0
            || r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.name = name;
    this.x = x;
    this.y = y;
    this.sizeOne = sizeOne;
    this.sizeTwo = sizeTwo;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void setSize(int one, int two) throws IllegalArgumentException {
    if (one <= 0 || two <= 0) {
      throw new IllegalArgumentException("Size cannot be zero or negative.");
    }
    this.sizeOne = one;
    this.sizeTwo = two;
  }

  @Override
  public void setColor(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getSizeOne() {
    return this.sizeOne;
  }

  @Override
  public int getSizeTwo() {
    return this.sizeTwo;
  }

  @Override
  public int getRed() {
    return this.r;
  }

  @Override
  public int getGreen() {
    return this.g;
  }

  @Override
  public int getBlue() {
    return this.b;
  }
}
