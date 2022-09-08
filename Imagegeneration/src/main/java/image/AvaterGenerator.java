/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package image;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
public class AvaterGenerator {
  public static final int L=420,F=34;	//L为图像边长，F为边框长度
  public static void main(String[] args){
    DrawAvater da = new DrawAvater();
    JFrame f=new JFrame("Profile");
    f.setSize(L+22,L+56);	//JFrame的标题栏也会占像素，因此增加一个常数校正
    Color c = new Color(240, 71, 28);	//背景色
    f.setBackground(c);
    f.setLocation(200,200);
    f.setVisible(true);
    f.add(da);
    //键盘监听，按空格调用repaint函数，生成一个新头像
    f.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE) da.repaint();
      }
    });
  }
}
