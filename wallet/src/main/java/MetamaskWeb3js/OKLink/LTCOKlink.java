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
public class LTCOKlink {
  public static void main(String[] args) throws Exception {
    String contractUrl = "https://www.oklink.com/api/explorer/v1/eth/transfers?tranHash=0x3991617626ab4433917f42680bb47a6d56af3cafa416a69de5d9502a8ef92dbb";
    String s1 = httpUtil.get(contractUrl);
    String data1 = JSONObject.parseObject(s1).getString("data");
    System.out.println("data1 = " + data1);
  }
}
