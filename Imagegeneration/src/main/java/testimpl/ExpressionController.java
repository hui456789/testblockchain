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

/**
 * This class is expression controller, when people want to generate the new expression
 * user can call method in this class then three expression will executes in one times
 * 
 * that's mean user need not to call three method for generate red, green, and blue.
 * but they can control those three method by using one method in this class
 */

public class ExpressionController {
  public static final int DEFAULT_MINIMUM_HEIGHT = 4;
  
  // Three expression for each color
  private ExpressionTree exprRed;
  private ExpressionTree exprGreen;
  private ExpressionTree exprBlue;
  
  private int currentHeight;
  
  /**
   * Default constructure will call another constructure by using default input value
   */
  public ExpressionController () {
    this(DEFAULT_MINIMUM_HEIGHT, false);
    currentHeight = DEFAULT_MINIMUM_HEIGHT;
  }
  
  /**
   * Main constructure
   */
  public ExpressionController (int height, boolean grayscale) {
    if (!grayscale) {
        exprRed   = new ExpressionTree(height);
        exprGreen = new ExpressionTree(height);
        exprBlue  = new ExpressionTree(height); 
    }
    else {
        exprRed   = new ExpressionTree(height);
        exprGreen = exprBlue = exprRed;
    }
    currentHeight = height;
  }
 
  /**
   * Get each expression by verify that name of expression that pass by parameter
   */
  public ExpressionTree getExpr (String name) {
    if ( (name.toUpperCase()).equals("RED") ) {
        return exprRed;
    }
    else if ( (name.toUpperCase()).equals("GREEN") ) {
        return exprGreen;
    } 
    else if ( (name.toUpperCase()).equals("BLUE") ) {
        return exprBlue;
    }
    else {
        return null;
    }
  }
  
  /**
   * Get string of each expression by verify that name of expression that pass by parameter
   */
  public String getExpressionAsString (String name) {
    if ( (name.toUpperCase()).equals("RED") ) {
        return "Red Expression (" +exprRed.getHeight()+ ")    : " +exprRed.toString();
    }
    else if ( (name.toUpperCase()).equals("GREEN") ) {
        return "Green Expression (" +exprGreen.getHeight()+ ") : " +exprGreen.toString();
    } 
    else if ( (name.toUpperCase()).equals("BLUE") ) {
        return "Blue Expression (" +exprBlue.getHeight()+ ")   : " +exprBlue.toString();
    }
    else if ( (name.toUpperCase()).equals("GREY") ) {
        return "Grey Expression (" +exprRed.getHeight()+ ")   : " +exprRed.toString();
    }
    else {
        return "Sorry, not found your choice.";
    }
  }
}
