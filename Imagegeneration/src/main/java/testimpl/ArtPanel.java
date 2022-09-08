package testimpl; /**
 * RANDOM ART project assignment
 * CSC319 Object-Oriented Software Development
 * School of Information Technology
 * King Mongkut's University of Technology Thonburi
 * 
 * Group No. 17 
 * 55130500205 Khemmachart Chutapetch 
 * 55130500239 Nontachai Booontavornsakun
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * This lass contain the main panel that represent the random art
 * and the commom methods that need to generate random art
 */
public class ArtPanel extends JPanel   {
  
  // CS324e students. 
  // Add class constants and instance variables here
  
  public static final int SIZE = 400;
  public static final int NUM_COLOR_OPTIONS = 2;
  public static final double pi = Math.PI;
  
  private ExpressionController exprController;
  private Color color;
  private int height;
  private boolean grayscale;
  
  /**
   * The default constructor will initial dafual value need
   * the default height is 4, and grayscale mode is off
   */
  public ArtPanel(){ 
    this.height = 4;
    this.grayscale = false;
    setPreferredSize(new Dimension(SIZE, SIZE));
    exprController = new ExpressionController(height, grayscale);
  }
  
  /**
   * This method will be called when user want to create new random art
   */
  public void setNewExpr() {
    exprController = new ExpressionController(height, grayscale);
  }
  
  /**
   * Get the string of expression of random art
   */
  public String getExpressionAsString (String name) {
      return exprController.getExpressionAsString( name.toUpperCase() );
    }
  
  /**
   * Calculate and paint the conponent
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    // CS324e students: add and change as necessary
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    
    for(int i=0 ; i<getWidth() ; i++) {
      for(int j=0 ; j<getHeight() ; j++) {
        
        // x and y are always between -1.0 and 1.0, inclusive.
        // x and y represent the coordinates of a pixel in the panel scaled from -1.0 to 1.0.
        // So for example if we had a panel that was 200 pixels wide by 400 pixels tall, the upper left corner pixel with coordinates (0,0) 
        // would be scaled to (-1.0, -1.0). This assumes we have not translated or scaled the graphics objects. 
        // The pixel at the lower right corner would have coordinates(199, 399) and this would scale to (1.0, 1.0). 
        // Pixels in between are scaled based on the size of the panel.
        
        double x = ((((double) i) / getWidth()) * 2.0) - 1.0;
        double y = ((((double) j) / getHeight()) * 2.0) - 1.0;
        
        // For a given pixel the random function returns a value that is also between -1.0 and 1.0. 
        // This value is then scaled to a grayscale value. -1.0 is black, Color (0,0,0) and 1.0 is white, Color(255, 255, 255). 
        // The graphics object is set to that color and a rectangle of size 1 is filled at the coordinates for that pixel.
        // (You may find using fillRect instead of fill on a Rectangle object improves the speed of your program.)
        
        int red = getColorCode( "RED", x, y );
        int green = getColorCode( "GREEN", x, y );
        int blue = getColorCode( "BLUE", x, y );
        
        color = new Color(red, green, blue);
        g2.setColor(color);
        g2.fillRect(i,j,i,j);
      }
    }  
  }
  
  /**
   * method for set the grayscale mode, if grayscale mode is true that's mean the tree color will
   * use the same expression and the art will be grayscale
   */
  public void setGrayscale (boolean grayscale) {
      this.grayscale = grayscale;
      this.setNewExpr();
  }
  
  /**
   * Case the double value of expression [-1,1] to the int value of color between [0,255] 
   */
  public int getColorCode (String name, double x, double y) {
    return (int)(( (exprController.getExpr( name.toUpperCase() )).getResult( x , y ) + 1 ) * 255/2 );
  }
  
  /**
   * THe code that pass the expression will pass into this method and will generate to value between (0-255)
   */
  public int getColorCode2 (Double num) {
    return (int)(( num + 1 ) * 255/2 );
  }
  
  /**
   * Set height of the expression
   */
  public void setHeight (int height) {
      this.height = height;
  }
}