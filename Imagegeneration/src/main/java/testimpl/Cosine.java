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
 * Cosine class contain cosine method that will return value of 
 * sin(_INPUT_);
 */
public class Cosine extends SingleOperator {
  
  public Cosine (int height) {
    super(height);
  }
  
  public double getResult (double x, double y) {
    if (height != 1) {
      return Math.cos( Math.PI * nextNode.getResult(x, y) );
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
      return "cos(pi*" +nextNode.toString()+ ")";
    }
  }
  
}