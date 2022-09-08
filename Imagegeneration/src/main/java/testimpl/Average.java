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
 * This class is Average function that will return the values between x and y
 * by use (x+y)/2 equalation
 */

public class Average extends DoubleOperators {
  
  public Average (int height) {
    super(height);
  }
  
  /**
   * If it's leaf node, it will return one of x and y value
   * otherwise, will create abother subtree and use (x+y)/2 equalation
   */
  public double getResult (double x, double y) {
    if (height != 1) {
      return ( left.getResult(x, y) + right.getResult(x, y) ) / 2;
    }
    else {
      return getOperand(x, y);
    }
  }
  
  public String toString () {
    if(height == 1) {
      return operand;
    }
    else {
      return "avg(" +left.toString()+ "," +right.toString()+ ")";
    }
  }
}