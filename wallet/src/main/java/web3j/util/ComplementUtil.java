/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.util;

import org.web3j.utils.Numeric;
import java.math.BigInteger;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/9/1
 * @since 1.0
 */
public class ComplementUtil {
  public static void main(String[] args) {
    String MethodID = "0x04e45aaf";
    byte[] MethodIDbyte = Numeric.hexStringToByteArray(MethodID);
    String s = bytes2hex(MethodIDbyte);
    String token1 = "";
    byte[] token1byte = Numeric.hexStringToByteArray(token1);
    String token1src = bytes2hex(token1byte);
    String token0 = "";
    byte[] token0byte = Numeric.hexStringToByteArray(token0);
    String token0src = bytes2hex(token0byte);
    //交易费率
    String fee = "3000";
    byte[] feebyte = Numeric.toBytesPadded( new BigInteger(fee), 32);
    String feesrc = bytes2hex(feebyte);
    //0x780155C5Ac259961d5F0BDeD22E6C41f465ED6a6
    String owneraddress = "";
    byte[] ownerAddreessbyte = Numeric.hexStringToByteArray(owneraddress);
    String owneraddresssrc = bytes2hex(ownerAddreessbyte);
    //使用0.1ETH购买
    String amountOut = "";
    byte[] amountOutbyte = Numeric.toBytesPadded( new BigInteger(amountOut), 32);
    String amountOutsrc= bytes2hex(amountOutbyte);
    // 交易池通过ETH兑换的usdt数量
    String quotedAmountIn = "";
    byte[] quotedAmountInbyte = Numeric.toBytesPadded( new BigInteger(quotedAmountIn), 32);
    String quotedAmountInsrc= bytes2hex(quotedAmountInbyte);
   /* long time = new Date().getTime();
    String datatime = String.valueOf(time);
    byte[] datatimebyte = Numeric.hexStringToByteArray(datatime);
    String datatimesrc = bytes2hex(datatimebyte);*/
    String o = "0";
    byte[] o0 = Numeric.hexStringToByteArray(o);
    String o00= bytes2hex(o0);
    String data = "0x04e45aaf" + token1src + token0src + feesrc + owneraddresssrc  + amountOutsrc + quotedAmountInsrc + o00;
   //System.out.println("bytes = " + data );

    /*String src = "";
    byte[] bytesrc =Numeric.hexStringToByteArray(src);
    String s = bytes2hex(bytesrc);
    System.out.println("bytesrc = " +s);*/
  }
  public static String bytes2hex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    String tmp;
    int len = 64;
    for (byte b : bytes) {
      // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
      tmp = Integer.toHexString(0xFF & b);
      if (tmp.length() == 1) {
        tmp = "0" + tmp;//只有一位的前面补个0
      }
      sb.append(tmp);//每个字节用空格断开
    }
    while (sb.length() < len) {   //若长度不足进行补零
      sb.insert(0, "0");
    }
    return sb.toString();
  }

  public static String numericStrUrl(String str ){
    byte[] dataByte = Numeric.hexStringToByteArray(str);
    return bytes2hex(dataByte);
  }

  public static String numericBigUrl(String str ){
    byte[] dataByte = Numeric.toBytesPadded( new BigInteger(str), 32);
    return bytes2hex(dataByte);
  }
}
