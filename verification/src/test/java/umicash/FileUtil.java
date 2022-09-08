/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package umicash;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/5/17
 * @since 1.0
 */

import java.io.File;
import java.io.IOException;

/**
 * @Auther: larson
 * @Since: 2020/08/26 18:02
 * @Description: 批量重命名文件
 */
public class FileUtil {
  public static void main(String[] args) {
    File file = new File("D:\\新建文件夹\\images的副本\\images的副本");
    String[] list = file.list();
    for (int i = 0; i < list.length; i++) {
      System.out.println("list[i] = " + list[i]);
      String[] split = list[i].split("\\.");
      String replace = split[0].substring(1);
      File file1 = new File("D:\\新建文件夹\\images的副本\\images的副本\\"+list[i]);
      file1.renameTo(new File("D:\\新建文件夹\\images的副本\\images的副本" + "\\" + replace + ".png"));
    }
  }
}