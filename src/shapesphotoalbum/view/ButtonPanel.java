package shapesphotoalbum.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class represents a ButtonPanel object. It works as a helper class to display the
 * menu buttons to the graphical view window. It is used in the PhotoAlbumGraphicalView class.
 */
public class ButtonPanel extends JPanel {
  private JButton btnPrev;
  private JButton btnSelect;
  private JButton btnNext;
  private JButton btnQuit;

  /**
   * Constructs the ButtonPanel class.
   */
  public ButtonPanel() {
    this.setBackground(Color.ORANGE);
    // Add Prev button
    this.btnPrev = new JButton("<< Prev <<");
    this.btnPrev.setActionCommand("Prev");
    this.add(this.btnPrev);
    // Add Select button
    this.btnSelect = new JButton("^^ Select ^^");
    this.btnSelect.setActionCommand("Select");
    this.add(this.btnSelect);
    // Add Next button
    this.btnNext = new JButton(">> Next >>");
    this.btnNext.setActionCommand("Next");
    this.add(this.btnNext);
    // Add Quit button
    this.btnQuit = new JButton("xx Quit xx");
    this.btnQuit.setActionCommand("Quit");
    this.add(this.btnQuit);
  }

  /**
   * Sets listener for the button action.
   * @param listener the listener of the action
   */
  public void setListener(ActionListener listener) {
    this.btnPrev.addActionListener(listener);
    this.btnSelect.addActionListener(listener);
    this.btnNext.addActionListener(listener);
    this.btnQuit.addActionListener(listener);
  }
}
