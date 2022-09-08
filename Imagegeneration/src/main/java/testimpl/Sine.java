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
 * Sine class contain sin method
 */
public class Sine extends SingleOperator {
  
  public Sine (int height) {
    super(height);
  }
  
  public double getResult (double x, double y) {
    if (height != 1) {
      return Math.sin( Math.PI * nextNode.getResult(x, y) );
    }
    else {
      return getOperand(x,y);
    }
  }
  
  public String toString () {
    if(height == 1) {
      return operand;
    }
    else {
      return "sin(pi*" +nextNode.toString()+ ")";
    }
  }
  
}