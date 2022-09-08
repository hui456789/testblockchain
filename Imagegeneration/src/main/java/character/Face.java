/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package character;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/3/14
 * @since 1.0
 */
public class Face extends JFrame {
  /**

   *

   */

  private static final long serialVersionUID = 1L;

  public Face(){

    setSize(500, 500);

    setResizable(false);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    Dimension screenSize = Toolkit.getDefaultToolkit()

      .getScreenSize();

    Dimension frameSize = getSize();

    setLocation((screenSize.width - frameSize.width) / 2,

      (screenSize.height - frameSize.height) / 2);

    setVisible(true);

  }


  //下面的是关键的绘图代码
  public void paint(Graphics g){
    //画头
    g.drawOval(100, 50, 300, 400);

    //画眼睛
    g.drawOval(140, 150, 100, 50);

    g.drawOval(260, 150, 100, 50);

    //画鼻子

    g.drawArc(140, 150, 100, 150, -90, 90);

    g.drawArc(260, 150, 100, 150, 180, 90);

    //画嘴巴

    g.drawOval(170, 320, 150, 50);

  }


  public static void main(String args[]){
    new Face();
  }
}
