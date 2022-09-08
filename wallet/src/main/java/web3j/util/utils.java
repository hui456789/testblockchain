/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.util;

import java.math.BigInteger;
import java.util.Collections;

import static web3j.util.ComplementUtil.bytes2hex;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/16
 * @since 1.0
 */
public class utils {

  /**
   * 功能描述：16进制转10进制整数。
   */
  public static BigInteger hexToBigInteger(String strHex) {
    if (strHex.length() > 2) {
      if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
        strHex = strHex.substring(2);
      }
      BigInteger bigInteger = new BigInteger(strHex, 16);
      return bigInteger;
    }
    return null;
  }


  /**
   * 功能描述：hex地址转地址。
   */
  public static String hexToAddress(String strHex) {
    if (strHex.length() > 42) {
      if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
        strHex = strHex.substring(2);
      }
      strHex = strHex.substring(24);
      return strHex;
    }
    return null;
  }

  public static void main(String[] args) {
    String inputData = "";
    byte[] bytes = hexStringToByteArray(inputData);
    String s = bytes2hex(bytes);
    System.out.println("bytes = " + s);

  }
  public static byte[] String2Byte(String s) {
    byte[] baKeyword = new byte[s.length() / 2];
    for (int i = 0; i < baKeyword.length; i++) {
      try {
        baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return baKeyword;
  }
  public static String asciiToHex(String asciiValue)
  {
    char[] chars = asciiValue.toCharArray();
    StringBuffer hex = new StringBuffer();
    for (int i = 0; i < chars.length; i++)
    {
      hex.append(Integer.toHexString((int) chars[i]));
    }

    return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length()/2), "00"));
  }

  // 此方法用于将16进制的字符串转换成16进制的字节数组
  public static byte[] Hex16StringToHex16Byte(String _hex16String)
  {
    //去掉字符串中的空格。
    _hex16String = _hex16String.replace(" ", "");
    if (_hex16String.length() / 2 == 0)
    {
      _hex16String += " ";
    }
    //声明一个字节数组，其长度等于字符串长度的一半。
    byte[] buffer = new byte[_hex16String.length() / 2];
    for (int i = 0; i < buffer.length; i++)
    {
      //为字节数组的元素赋值。
      buffer[i] = (byte) Integer.parseInt((_hex16String.substring(i * 2, (i*2)+2)), 16);
    }
    //返回字节数组
    return buffer;
  }

  public static String string2HexString(String strPart) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < strPart.length(); i++) {
      int ch = (int) strPart.charAt(i);
      String strHex = Integer.toHexString(ch);
      hexString.append(strHex);
    }
    return hexString.toString();
  }

  public static byte[] hexStringToBytes(String hex) {
    byte[] result = new byte[hex.length() / 2];
    char[] chars = hex.toCharArray();
    for (int i = 0, j = 0; i < result.length; i++) {
      result[i] = (byte) (toByte(chars[j++]) << 4 | toByte(chars[j++]));
    }
    return result;
  }

  private static int toByte(char c) {
    if (c >= '0' && c <= '9') return (c - '0');
    if (c >= 'A' && c <= 'F') return (c - 'A' + 0x0A);
    if (c >= 'a' && c <= 'f') return (c - 'a' + 0x0a);
    throw new RuntimeException("invalid hex char '" + c + "'");
  }

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] b = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
      b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
        .digit(s.charAt(i + 1), 16));
    }
    return b;
  }
  /**
   * hex字符串转byte数组
   *
   * @param inHex 待转换的Hex字符串
   * @return 转换后的byte数组结果
   */
  public static byte[] hexToByteArray(String inHex) {
    int hexlen = inHex.length();
    byte[] result;
    if (hexlen % 2 == 1) {
      //奇数
      hexlen++;
      result = new byte[(hexlen / 2)];
      inHex = "0" + inHex;
    } else {
      //偶数
      result = new byte[(hexlen / 2)];
    }
    int j = 0;
    for (int i = 0; i < hexlen; i += 2) {
      result[j] = hexToByte(inHex.substring(i, i + 2));
      j++;
    }
    return result;
  }
  /**
   * Hex字符串转byte
   *
   * @param inHex 待转换的Hex字符串
   * @return 转换后的byte
   */
  public static byte hexToByte(String inHex) {
    return (byte) Integer.parseInt(inHex, 16);
  }
}
