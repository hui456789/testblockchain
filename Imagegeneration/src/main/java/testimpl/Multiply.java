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
 * Multiply class contain multiply mathod that will return one of value of
 * x*x, y*y, x*y, and y*x
 */
public class Multiply extends DoubleOperators {
  
  public Multiply (int height) {
    super(height);
  }
  
  public double getResult (double x, double y) {
    if (height != 1) {
      return left.getResult(x, y) * right.getResult(x, y);
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
      return "(" +left.toString()+ "*" +right.toString()+ ")";
    }
  }
}