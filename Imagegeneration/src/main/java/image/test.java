/**
 * Copyright (c) 2022, 59store. All rights reserved.
 *//*

package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

*/
/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/3/11
 * @since 1.0
 *//*

public class test {


  @RequestMapping(method = POST, value = "/getVerify", produces = JSON_UTF8, consumes = JSON_UTF8)
  public void getVerify(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("image/jpeg");  //设置相应类型,告诉浏览器输出的内容为图片
      response.setHeader("Pragma", "No-cache");  //设置响应头信息，告诉浏览器不要缓存此内容
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expire", 0);
      randomValidateCodeUtilService.getRandcode(request, response); //输出验证码图片方法
    } catch (Exception e) {
      logger.error("获取验证码失败", e);
    }
  }


  public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";  //放到session中的key
  //private String randString = "0123456789";  //随机产生只有数字的字符串
  //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //随机产生只有字母的字符串
  private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //随机产生数字与字母组合的字符串

  // 设置图片属性
  private int width = 95;// 图片宽
  private int height = 25;// 图片高
  private int lineSize = 40;// 干扰线数量
  private int stringNum = 4;// 随机产生字符数量

  private static final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtilService.class);
  private Random random = new Random();


  */
/**
   * 生成随机图片
   *//*

  public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
    g.fillRect(0, 0, width, height);//图片大小
    g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));//字体大小
    g.setColor(getRandColor(110, 133));//字体颜色
    // 绘制干扰线
    for (int i = 0; i <= lineSize; i++) {
      drowLine(g);
    }
    // 绘制随机字符
    String randomString = "";
    for (int i = 1; i <= stringNum; i++) {
      randomString = drowString(g, randomString, i);
    }
    logger.info(randomString);
    // 将生成的随机字符串保存到session中
    session.removeAttribute(RANDOMCODEKEY);
    session.setAttribute(RANDOMCODEKEY, randomString);
    g.dispose();
    try {
      // 将内存中的图片通过流动形式输出到客户端
      ImageIO.write(image, "JPEG", response.getOutputStream());
    } catch (Exception e) {
      logger.error("将内存中的图片通过流动形式输出到客户端失败", e);
    }
  }


  */
/**
   * 获得颜色
   *//*

  private Color getRandColor(int fc, int bc) {
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc - 16);
    int g = fc + random.nextInt(bc - fc - 14);
    int b = fc + random.nextInt(bc - fc - 18);
    return new Color(r, g, b);
  }


  */
/**
   * 绘制干扰线
   *//*

  private void drowLine(Graphics g) {
    int x = random.nextInt(width);
    int y = random.nextInt(height);
    int xl = random.nextInt(13);
    int yl = random.nextInt(15);
    g.drawLine(x, y, x + xl, y + yl);
  }


  */
/**
   * 绘制字符串
   *//*

  private String drowString(Graphics g, String randomString, int i) {
    g.setFont(getFont());
    g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
      .nextInt(121)));
    String rand = String.valueOf(getRandomString(random.nextInt(randString
      .length())));
    randomString += rand;
    g.translate(random.nextInt(3), random.nextInt(3));
    g.drawString(rand, 13 * i, 16);
    return randomString;
  }


  */
/**
   * 获取随机的字符
   *//*

  public String getRandomString(int num) {
    return String.valueOf(randString.charAt(num));
  }


  */
/**
   * 获得字体
   *//*

  private Font getFont() {
    return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
  }


}
*/
