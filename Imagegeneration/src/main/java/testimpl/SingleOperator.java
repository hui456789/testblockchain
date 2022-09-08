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
 * One kind of all possible node is Single Operators, its mean the operator that need only one parameter.
 * For example, sine and cosine functions need value to multiply with PI
 * 
 */
public abstract class SingleOperator extends ExpressionNode {
  
  protected ExpressionNode nextNode;       // its has only one child
  
  /**
   * First of all, when the node is created, its will assing values to super class (for initial the variable that required)
   * then will create an sub tree for expresstion tree by generateExpression method.
   */
  public SingleOperator (int height) {
    super(height);
    generateExpression(height, x, y);
  }
  
  /**
   * If node is leaf node (height is equal one) its will return one of x and y
   * otherwise, if it's not a leaf node, its will create another subtree in its child.
   */
  public void generateExpression (int height, double x, double y) {
    if (height != 1) {
      nextNode = randExpression();
    }
    else if (height == 1) {
      generateOperand(x, y);
    }
  }
  
  /**
   * Random one of poosible expression node to be child
   */ 
  public ExpressionNode randExpression () {
    int randNum = (int)(Math.random() * 4);
    if (randNum == 0) {
      return (new Multiply(this.height -1));
    }
    else if (randNum == 1) {
      return (new Average(this.height -1));
    }
    else if (randNum == 2) {
      return (new Sine(this.height -1));
    }
    else {
      return (new Cosine(this.height -1));
    }
  }
  
  public abstract double getResult (double x, double y);     // Get result of each node, it need to execute all of expression before return.
  public abstract String toString();                         // toString() will return all of expression as String}

}