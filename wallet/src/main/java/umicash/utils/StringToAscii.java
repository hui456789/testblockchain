/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package umicash.utils;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/2/17
 * @since 1.0
 */

public class StringToAscii {

  private static String toHexUtil(int n){
    String rt="";
    switch(n){
      case 10:rt+="A";break;
      case 11:rt+="B";break;
      case 12:rt+="C";break;
      case 13:rt+="D";break;
      case 14:rt+="E";break;
      case 15:rt+="F";break;
      default:
        rt+=n;
    }
    return rt;
  }

  public static String toHex(int n){
    StringBuilder sb=new StringBuilder();
    if(n/16==0){
      return toHexUtil(n);
    }else{
      String t=toHex(n/16);
      int nn=n%16;
      sb.append(t).append(toHexUtil(nn));
    }
    return sb.toString();
  }

  public static String parseAscii(String str){
    StringBuilder sb=new StringBuilder();
    byte[] bs=str.getBytes();
    for(int i=0;i<bs.length;i++)
      sb.append(toHex(bs[i]));
    return sb.toString();
  }

  public static void main(String args[]){
    String s="dcbcb2a2e11b191";
    System.out.println("转换后的字符串是："+StringToAscii.parseAscii(s));
  }
}
