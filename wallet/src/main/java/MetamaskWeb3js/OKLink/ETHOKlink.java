/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONObject;

import static MetamaskWeb3js.OKLink.httpUtil.get;

/**
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/20
 * @since 1.0
 */
public class ETHOKlink {
  public static void main(String[] args) throws Exception {
    String str = "https://www.oklink.com/api/v5/explorer/blockchain/block?chainShortName=eth";
    String s = get(str);
    String data = JSONObject.parseObject(s).getString("data");
    System.out.println("data = " + data);
  }
}
