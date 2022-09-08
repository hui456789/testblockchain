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
import java.awt.event.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.util.concurrent.*; 

/**
 * This class represents frame for random art project.
 * The frame include GUI panel and random-art panel
 * that GUI panel has menu to control expression and the expression will exffect to random-art panel
 *
 * 这个类代表随机艺术项目的框架。
 * 框架包括GUI面板和随机艺术面板
 * 该GUI面板具有控制表达式的菜单，表达式将影响随机艺术面板
 */
public class ArtFrame extends JFrame implements ActionListener {
  
  private ArtPanel thePanel;
  
  private JLabel theCurrentExpression_RED;     //代表红色的表达    // Represent the expression of red color
  private JLabel theCurrentExpression_GREEN;   //代表绿色的表达   // Represent the expression of green color
  private JLabel theCurrentExpression_BLUE;    //代表蓝色的表达     // Represent the expression of blue color
  private JLabel timeForGenerate;    //此标签将显示用于生成随机艺术面板的时间              // This label will show time that use for generate the random art panel
  private JButton buttonGenerate;    //“生成”按钮将重新绘制随机艺术面板               // The generate button will repaint the random art panel

  
  private static final int EQUATION_FONT_SIZE = 16; //正面尺寸 // The defualt size of front
  private ArrayList<JMenuItem> itemHeight;  //代表所有菜单高度，供用户输入        // Represent the all of menu height, for input of user
  
  public ArtFrame(){
    // Set the common properties of the frame
    setTitle("Random Art Project : Group 17 [CSC319 SIT.KMUTT]");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500,600);
    
    // Set the window display on the centre on the screen
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) (dimension.getWidth()/2 - getWidth()/2);
    int y = (int) (dimension.getHeight()/2 - getHeight()/2);
    setLocation(x, y);

    // create the panel to represent random art panel
    thePanel = new ArtPanel();
    
    // add the menu for Colored Art options
    setJMenuBar(createMenu());
    // add components to frame
    add( createExpressionAsString(), BorderLayout.NORTH );  // panel for show string of expression
    add( createThePanel(), BorderLayout.CENTER );           // panel for show random art
    add( createButton(), BorderLayout.SOUTH);               // create all buttom (menu and somthing else)
  }
  
  /**
   * Return the panel that represent random art
   * 返回代表随机艺术的面板
   */
  public JPanel createThePanel () {
      return thePanel;
  }
  
  /**
   * Create button and panel in south panel of the frame
   * 在框架的南面板中创建按钮和面板
   */
  public JPanel createButton () {
    // South panel include button for generate and stirng for time that took for generate
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new GridLayout(2,0));
    southPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    timeForGenerate = new JLabel("",JLabel.CENTER);     // represent time that took for generate
    buttonGenerate = new JButton("Generate");           // generate button
    buttonGenerate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        long startTime = System.currentTimeMillis();    // first time
        thePanel.setNewExpr();                          // set new expression
        setCurrentExpr();                               // get string of expression
        repaint();                                      // repaint random art
        long endTime = System.currentTimeMillis();      // second time
        timeForGenerate.setText("Took " +(endTime-startTime)+ " seconds for generate");     // second time minus first time
      }
    });
    
    // add thw panels into the south panel
    southPanel.add(timeForGenerate);
    southPanel.add(buttonGenerate);
    
    return southPanel;
  }
  
  /**
   * Get the string of expreestion for each color (red, green, and blue) then show these expression in the panel
   */
  public JPanel createExpressionAsString () {
    JPanel exprString = new JPanel(new GridLayout(3,0));
    
    theCurrentExpression_RED = new JLabel( thePanel.getExpressionAsString("RED") );
    theCurrentExpression_RED.setFont(new Font("Serif", Font.PLAIN, EQUATION_FONT_SIZE));
    theCurrentExpression_GREEN = new JLabel( thePanel.getExpressionAsString("GREEN") );
    theCurrentExpression_GREEN.setFont(new Font("Serif", Font.PLAIN, EQUATION_FONT_SIZE));
    theCurrentExpression_BLUE = new JLabel( thePanel.getExpressionAsString("BLUE") );
    theCurrentExpression_BLUE.setFont(new Font("Serif", Font.PLAIN, EQUATION_FONT_SIZE));
    
    exprString.add(theCurrentExpression_RED);
    exprString.add(theCurrentExpression_GREEN);
    exprString.add(theCurrentExpression_BLUE);  
    
    return exprString;
  }
  
  /**
   * Set the current expression that use to represent the random art
   */
  public void setCurrentExpr () {
    theCurrentExpression_RED.setText( thePanel.getExpressionAsString("RED") );
    theCurrentExpression_GREEN.setText( thePanel.getExpressionAsString("GREEN") );
    theCurrentExpression_BLUE.setText( thePanel.getExpressionAsString("BLUE") );
  }
  
  /**
  * Create menu bar and all menu need 
  */
  private JMenuBar createMenu() {
    JMenuBar menu = new JMenuBar();
    
    JMenu menuFile = new JMenu("File");
    JMenu menuColorOptions = new JMenu("Color Option");
    JMenu menuSetHeight = new JMenu("Set Height of tree");
    JMenu menuGetString = new JMenu("Get the expression");
    JMenu menuHelp = new JMenu("Help");
    
    JMenuItem itemQuit = new JMenuItem("Quit");
    JMenuItem itemColorful = new JMenuItem("Colorful mode");
    JMenuItem itemGrayscale = new JMenuItem("Grayscale mode");
    JMenuItem itemAbout = new JMenuItem("About us");
    JMenuItem itemString_R = new JMenuItem("Get expression RED");
    JMenuItem itemString_G = new JMenuItem("Get expression GREEN");
    JMenuItem itemString_B = new JMenuItem("Get expression BLUR");
    JMenuItem itemString_ALL = new JMenuItem("Get all expression");
    
    
    itemHeight = new ArrayList<JMenuItem>();
    for (int i=4 ; i<15 ; i++) {
        JMenuItem buttonHeight = new JMenuItem(""+i);
        buttonHeight.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                setHeight (e.getActionCommand());
            }
        });
        itemHeight.add(buttonHeight);
     }
    
    itemQuit.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
    });
    
    itemColorful.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            displayRandomArt(false);
        }
    });
    
    itemGrayscale.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            displayRandomArt(true);
        }
    });

   
    itemAbout.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            aboutUs();
        }
    });
    
    itemString_R.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            String text = "";
            String temp = thePanel.getExpressionAsString ("RED");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            JOptionPane.showMessageDialog(null, text, "Expression for Red", JOptionPane.INFORMATION_MESSAGE);
        }
    });
    
    itemString_G.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            String text = "";
            String temp = thePanel.getExpressionAsString ("GREEN");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            JOptionPane.showMessageDialog(null, text, "Expression for Green", JOptionPane.INFORMATION_MESSAGE);
        }
    });
    
    itemString_B.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            String text = "";
            String temp = thePanel.getExpressionAsString ("BLUE");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            JOptionPane.showMessageDialog(null, text, "Expression for Blue", JOptionPane.INFORMATION_MESSAGE);
        }
    });
    
    itemString_ALL.addActionListener( new ActionListener() {
        public void actionPerformed (ActionEvent e) {
            String text = "";
            String temp = thePanel.getExpressionAsString ("RED");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            
            text = text + "\n\n";
            
            temp = thePanel.getExpressionAsString ("GREEN");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            
            
            text = text + "\n\n";
            temp = thePanel.getExpressionAsString ("BLUE");
            for (int i=0 ; i<temp.length() ; i++) {
                if (i>1 && i%100==0) {
                    text = text + "\n";
                }
                else {
                    text = text + temp.charAt(i);
                }
            }
            JOptionPane.showMessageDialog(null, text, "Expression for Red, Green, and Blue", JOptionPane.INFORMATION_MESSAGE);
        }
    });
    
    menu.add(menuFile);
    menu.add(menuColorOptions);
    menu.add(menuHelp);
    
    menuFile.add(menuGetString);
    menuFile.add(itemQuit);
    menuColorOptions.add(itemColorful);
    menuColorOptions.add(itemGrayscale);
    menuColorOptions.add(menuSetHeight);
    menuGetString.add(itemString_R);
    menuGetString.add(itemString_G);
    menuGetString.add(itemString_B);
    menuGetString.add(itemString_ALL);
    
    Iterator<JMenuItem> it = itemHeight.iterator();
    while (it.hasNext()) {
        menuSetHeight.add(it.next());
    }
    menuHelp.add(itemAbout);
    
    return menu;
  }
  
  public void aboutUs () {
      String text = "RANDOM ART PROJECT\n" +
                    "CSC319 Object-Oriented Software Development\n" +
                    "School of Information Technology\n" +
                    "King Mongkut's University of Technology Thonburi\n\n" +
 
                    "Group No. 17 \n" +
                    "55130500205 Khemmachart Chutapetch \n" +
                    "55130500239 Nontachai Booontavornsakun\n";
      JOptionPane.showMessageDialog(null, text, "About us", JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * repaint the random art, if grayscale is true then the random art will use the same expression
   * that's mean the art will be grayscale
   */
  public void displayRandomArt (boolean grayscale) {
      thePanel.setGrayscale(grayscale);
      repaint();
      setCurrentExpr();
  }
  
  /**
   * the default action performed method
   */
  public void actionPerformed(ActionEvent event) {
    System.out.println("This is "+ event.getActionCommand() +" function call from actionPerformed method!");
  }
  
  /**
   * method set height of tree will call the method set height of tree from the random art panel
   */
  public void setHeight (String s) {
      int height = Integer.parseInt(s);
      thePanel.setHeight(height);
    }
  
  /**
   * Starting method
   */
  public void start(){
    setVisible(true);
  }
}