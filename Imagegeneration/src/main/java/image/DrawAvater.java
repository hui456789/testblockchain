/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package image;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
public class DrawAvater extends JPanel {
  boolean fillin[][];
  protected void paintComponent ( Graphics g ) {
    super.paintComponent(g);
    //随机生成图形矩阵：
    Random r = new Random();
    boolean[][] fillin= new boolean[5][5];
    //随机生成左半部分：
    for(int j=0;j<2;j++){
      for(int i=0;i<5;i++){
        fillin[i][j]=r.nextBoolean();
      }
    }
    //随机生成中间一列：
    for(int j=2,i=0;i<5;i++){
      fillin[i][j]=r.nextBoolean();
    }
    //镜像生成右半部分：
    for(int j=3;j<5;j++){
      for(int i=0;i<5;i++){
        fillin[i][j]=fillin[i][4-j];
      }
    }
    int p=(AvaterGenerator.L-2*AvaterGenerator.F)/5;			//p为每个着色像素块的边长
    Color c = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),50+r.nextInt(205));
    //随机生成颜色。4个随机生成的参数为：RGB和透明度，透明度不宜太低
    for(int i=0;i<5;i++){
      for(int j=0;j<5;j++){
        g.setColor(c);
        if(fillin[i][j]){
          g.fillRect(j*p+AvaterGenerator.F, i*p+AvaterGenerator.F, p, p);
        }
      }
    }
  }
}
