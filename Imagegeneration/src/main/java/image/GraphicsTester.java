/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package image;

import java.awt.*;
import javax.swing.*;

public class GraphicsTester extends JFrame{
  public GraphicsTester() {
    super("画图");
    setVisible(true);
    setSize(370,460);
  }
  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.white);
    g.fillRect(0, 0, 370, 450);
    Color blue = new Color(0,100,255);
    g.setColor(blue);
    g.fillOval(65,60,240,230);
    g.fillRect(109, 290, 150, 120);

    int xValues[] = {109,70,63,109};
    int yValues[] = {290, 278, 308, 321};

    g.fillPolygon(xValues,yValues,4);

    int xValues2[] = {259,305, 295, 259};
    int yValues2[] = {290, 308, 332, 321};

    g.fillPolygon(xValues2,yValues2,4);

    g.setColor(Color.white);
    g.fillOval(41,271,40,40);
    g.fillOval(292, 305, 40, 40);
    g.fillOval(173, 402, 20, 20);
    g.fillOval(90, 405, 90, 25);
    g.fillOval(186, 405, 90, 25);
    g.setColor(Color.black);
    g.drawOval(41,271,40,40);
    g.drawOval(292, 305, 40, 40);
    g.drawOval(90, 405, 90, 25);
    g.drawOval(186, 405, 90, 25);


    g.setColor(Color.white);
    g.fillOval(85, 130, 200, 150);
    g.fillOval(123, 260, 120, 112);
    g.setColor(Color.red);
    g.fillRoundRect(103, 275, 166, 16, 15, 15);
    g.setColor(new Color(204,204,0));
    g.fillOval(169, 278, 30, 30);
    g.setColor(Color.black);
    g.drawLine(171, 285, 197, 285);
    g.drawLine(169,290,200, 290);
    g.fillOval(179, 293, 10, 10);
    g.drawLine(184, 300, 184, 307);


    g.drawArc(148, 290, 70, 70, 0, -180);
    g.drawLine(148, 325, 218, 325);
    g.setColor(Color.white);
    g.fillOval(123,	100, 60, 70);
    g.fillOval(183, 100, 60, 70);
    g.setColor(Color.black);
    g.drawOval(123,100,60,70);
    g.drawOval(183,100,60,70);
    g.fillOval(152, 138, 10 ,16);
    g.fillOval(202, 138, 10, 16);
    g.setColor(Color.red);
    g.fillOval(169, 158, 27, 27);
    g.setColor(Color.black);
    g.drawOval(169,158,27,27);
    g.setColor(Color.white);
    g.fillOval(184, 161, 10, 13);



    g.setColor(Color.black);
    g.drawArc(90, 60, 190, 190, -45,-90);
    g.drawLine(183, 185, 183, 250);
    g.drawLine(100,182,148, 189);
    g.drawLine(93, 200, 148, 200);
    g.drawLine(98, 217, 148, 211);

    g.drawLine(219, 189, 268, 182);
    g.drawLine(219,200, 272, 200);
    g.drawLine(219, 211, 269, 218);

    g.setFont(new Font("隶书",Font.PLAIN,24));
    g.setColor(Color.BLUE);
    g.drawString("哆啦A梦",20, 70);


  }
  public static void main(String[] args) {
    GraphicsTester app = new GraphicsTester();
    System.out.println("app = " + app);
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}


