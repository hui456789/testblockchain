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
 * First of all, the expression tree will create one kind of Expression Node, such as, Average, Multoply, Sine, and Cosine
 * to be the root of the tree, then the root will create another subtree autometically until height of that node is one, it's mean the left node
 * 
 * If the height of tree that input from user is less than DEFAULT_MINIMUM_HEIGHT, its will be equal minimun height
 * 
 */

public class ExpressionTree {
  
  public static final int DEFAULT_MINIMUM_HEIGHT = 4;               // Minimum height of tree that require
  private ExpressionNode root;                                      // Root node, can be any posiible node
  private int height;                                               // Height of tree, need to create each node
  private boolean grayscale;
  private double x;                                                 // x value's
  private double y;                                                 // y value's
  
  /**
   * This method will initial the height of tree
   */
  public ExpressionTree (int height) {
    // If height of tree that less than minimum height requirement, it will be equal minimum height
    if ( height < DEFAULT_MINIMUM_HEIGHT ) {
      this.height = DEFAULT_MINIMUM_HEIGHT;
    }
    else {
      this.height = height;
    }

    // Create an expresstion tree
    this.root = createExpressionTree();
  }
  
  /**
   * The createExpression method will create one kind of poosible node (average, multiply, sine, and cosine) to be the root
   * then the root will create its subtree autometically
   */
  public ExpressionNode createExpressionTree () {
    int randNum = (int)(Math.random() * 4);
    if (randNum == 0) {
      return (new Multiply(this.height));
    }
    else if (randNum == 1) {
      return (new Average(this.height));
    }
    else if (randNum == 2) {
      return (new Sine(this.height));
    }
    else {
      return (new Cosine(this.height));
    }
  }
  
  public void setHeight (int height) {
      this.height = height;
  }
  
  public int getHeight () {
      return height;
  }
  
  // toString() will return all of expreesion in expression tree
  public String toString () {
    return root.toString();
  }
  
  // Get result from expression tree
  public double getResult (double x, double y) {
      return root.getResult(x, y);
  }
}