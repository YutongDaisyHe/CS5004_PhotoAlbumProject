package helpertest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import shapesphotoalbum.helper.HtmlAlbumAdapter;

/**
 * JUnit test of HtmlAlbumAdapter helper class.
 */
public class HtmlAlbumAdapterTest {
  private HtmlAlbumAdapter adapterOne;
  private HtmlAlbumAdapter adapterTwo;

  /**
   * Initializes a HtmlAlbumAdapter object.
   */
  @Before
  public void setUp() {
    String snapshotOne = "Printing Snapshots\n"
            + "Snapshot ID: 2022-04-16T22:25:28.448041\n"
            + "Timestamp: 16-04-2022 22:25:28\n"
            + "Description: Head \n"
            + "Shape Information:\n"
            + "Name: head\n"
            + "Type: oval\n"
            + "Center: (350,350), X radius: 200, Y radius: 200, Color: (255,255,0)\n";
    String snapshotTwo = "Snapshot ID: 2022-04-15T22:37:03.661049\n"
            + "Timestamp: 15-04-2022 22:37:03\n"
            + "Description: \n"
            + "Shape Information:\n"
            + "Name: S1\n"
            + "Type: rectangle\n"
            + "Min corner: (590,180), Width: 200, Height: 15, Color: (255,0,0)\n"
            + "Name: SO\n"
            + "Type: oval\n"
            + "Center: (350,350), X radius: 200, Y radius: 200, Color: (255,255,0)\n";
    this.adapterOne = new HtmlAlbumAdapter(snapshotOne);
    this.adapterTwo = new HtmlAlbumAdapter(snapshotTwo);
  }

  /**
   * Test the constructor.
   */
  @Test
  public void testHtmlAlbumAdapter() {
    assertEquals("", this.adapterOne.getHtmlAlbum());
    assertEquals("\t<h2>2022-04-16T22:25:28.448041</h2>\n"
            + "\t<p>Description: Head</p>\n", this.adapterOne.getHtmlIdDes());

    assertEquals("", this.adapterTwo.getHtmlAlbum());
    assertEquals("\t<h2>2022-04-15T22:37:03.661049</h2>\n"
            + "\t<p>Description: </p>\n", this.adapterTwo.getHtmlIdDes());
  }

  /**
   * Test makeHtmlAlbum method.
   */
  @Test
  public void testMakeHtmlAlbum() {
    this.adapterOne.makeHtmlAlbum();
    String expected = "\t\t<ellipse id='head' cx='350' cy='350' rx='200' ry='200' "
            + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n";
    assertEquals(expected, this.adapterOne.getHtmlAlbum());
  }

  /**
   * Test getHtmlAlbum method.
   */
  @Test
  public void testGetHtmlAlbum() {
    String expected = "\t\t<rect id='S1' x='590' y='180' width='200' height='15' "
            + "fill='rgb(255,0,0)'>\n"
            + "\t\t</rect>\n"
            + "\t\t<ellipse id='SO' cx='350' cy='350' rx='200' ry='200' "
            + "fill='rgb(255,255,0)'>\n\t\t</ellipse>\n";
    this.adapterTwo.makeHtmlAlbum();
    assertEquals(expected, this.adapterTwo.getHtmlAlbum());
  }

  /**
   * Test getHtmlIdDes method.
   */
  @Test
  public void testGetHtmlIdDes() {
    assertEquals("\t<h2>2022-04-16T22:25:28.448041</h2>\n"
            + "\t<p>Description: Head</p>\n", this.adapterOne.getHtmlIdDes());
    assertEquals("\t<h2>2022-04-15T22:37:03.661049</h2>\n"
            + "\t<p>Description: </p>\n", this.adapterTwo.getHtmlIdDes());
  }
}