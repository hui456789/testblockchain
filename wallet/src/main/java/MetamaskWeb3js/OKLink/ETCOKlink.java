/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/15
 * @since 1.0
 */
public class ETCOKlink {
  public static void main(String[] args) throws Exception {
    String str = "https://www.oklink.com/api/explorer/v1/eth/info";
    String s = httpUtil.get(str);
    String data = JSONObject.parseObject(s).getString("data");
    String symbol = JSONObject.parseObject(data).getString("symbol");
    String block = JSONObject.parseObject(data).getString("block");
    String height = JSONObject.parseObject(block).getString("height");
    System.out.println("symbol = " + symbol);
    System.out.println("height = " + height);
  }
}
