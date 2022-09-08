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
 * The main class is using to start the project.
 * User can start project by run this class
 */
public class RandomArt {
    public static void RandomArt () {
        ArtFrame frame = new ArtFrame();
        frame.start();
    }

  public static void main(String[] args) {
    RandomArt ();
  }
}