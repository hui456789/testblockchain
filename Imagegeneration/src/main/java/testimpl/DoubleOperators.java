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
 * One kind of all possible node is Double Operators, its mean the operator that need two parameters.
 * For example, Firstly, multiply function need 2 values to be compute. Next, average function need two ot more values to compute
 * but in this algorithm, its implement from binary tree, so average function just has only 2 values
 * 
 */
public abstract class DoubleOperators extends ExpressionNode {
  
  // The name the two varible to left and right
  protected ExpressionNode left;
  protected ExpressionNode right;
  
  /**
   * First of all, when the node is created, its will assing values to super class (for initial the variable that required)
   * then will create an sub tree for expresstion tree by generateExpression method.
   */
  public DoubleOperators (int height) {
    super(height);
    generateExpression();
  }
  
  /**
   * If node is leaf node (height is equal one) its will return one of x and y
   * otherwise, if it's not a leaf node, its will create another subtree in it children
   */
  public void generateExpression () {
    if (height != 1) {
      left = randExpression();
      right = randExpression();
    }
    else {
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
  public abstract String toString();                         // toString() will return all of expression as String
}