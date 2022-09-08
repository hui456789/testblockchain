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
 * ExpressionNode is the super class of all node, contains attributes and methods that every nodes need
 */
public abstract class ExpressionNode {
  
  protected String operand;     // Keep an string of each expression
  protected int height;         // Height of each nodes in expression tree
  protected double x;           // Keep the value that input from user
  protected double y;           // Keep the value that input from user

  
  /**
   * When node is created, its will initial the height, and values of x and y into attribute (instance variable)
   */
  public ExpressionNode (int height) {
    this.height = height;
  }
  
  /**
   * When the node deep into the leaf node, its will select one value of x and y for create an expression, select by random value between 0 and 1, inclusive
   * then keep that value (x or y) to operand (use when user aks for)
   * and assign to the variable name 'value' for use to generate expression
   */
  public void generateOperand (double x, double y) {
    int randOperand = (int)(Math.random() * 2);
    if (randOperand == 0) {
      operand = "x";
    }
    else {
      operand = "y";
    }
  }
  
  /**
   * get the current value of operand
   */
  public double getOperand (double x, double y) {
        if ( operand.equals("x") ) {
            return x;
        }
        else {
            return y;
        }
  }
  
  public abstract double getResult (double x, double y);     // Get result of each node, it need to execute all of expression before return.
  public abstract String toString();                         // toString() will return all of expression as String
}