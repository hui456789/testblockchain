/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package timeImage;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/3/11
 * @since 1.0
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("serial")
public class CreateImageServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setContentType("image/jpeg");
    createImage(response.getOutputStream());
  }
  @SuppressWarnings("unused")
  private void createImage(OutputStream out){
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    Date  curDate  =   new Date(System.currentTimeMillis());//获取当前时间
    String s = "岱嘉："+formatter.format(curDate); //转化为字符串
    Font font = new Font("Serif", Font.BOLD, 10); //设置字体 及大小
    int width = 250;  //图片的大小
    int height = 200;
    BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=bi.createGraphics();
    g.setBackground(Color.green);
    g.clearRect(0, 0, width, height);
    g.setColor(Color.red);
    FontRenderContext context = g.getFontRenderContext();
    //  g.drawLine(0, 0, 99, 199);
    Rectangle2D bounds = font.getStringBounds(s, context);
    double x = (width - bounds.getWidth()) / 2;
    double y = (height - bounds.getHeight()) / 2;
    double ascent = -bounds.getY();
    double baseY = y + ascent;
    g.drawString(s, (int)x, (int)y);
    g.dispose();
    bi.flush();
    JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
    JPEGEncodeParam param=encoder.getDefaultJPEGEncodeParam(bi);
    param.setQuality(1, false);
    encoder.setJPEGEncodeParam(param);

    try {
      encoder.encode(bi);
    } catch (ImageFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
