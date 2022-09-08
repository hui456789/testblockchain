/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/3
 * @since 1.0
 */
public class BTCOKlink {
  public static void main(String[] args) throws Exception {
    String str2 = "https://www.oklink.com/api/explorer/v1/btc/addresses/bc1q89pqe8tgmrpvr3kgd6knp9pksvmgn83hwxsvwx/transactions?to=bc1q89pqe8tgmrpvr3kgd6knp9pksvmgn83hwxsvwx&limit=50";
    String s1 = httpUtil.get(str2);
    System.out.println("s1 = " + s1);
    String data1 = JSONObject.parseObject(s1).getString("data");
    int intValue = JSONObject.parseObject(s1).getJSONObject("data").getIntValue("total");
    System.out.println("intValue = " + intValue);
    System.out.println("data1 = " + data1);
    JSONArray hits = JSONObject.parseObject(data1).getJSONArray("hits");
    for (int i = 0; i < hits.size() ; i++) {
      String string = hits.getString( i);
      JSONObject jsonObject = JSONObject.parseObject(string);
      String hash = jsonObject.getString("hash");
      if (hash.equalsIgnoreCase("b785cb510ba621e32616c67c3e9963becd03de524aa8fcb830baa28d52feaa4a")){
        continue;
      }
      JSONArray outputs = jsonObject.getJSONArray("outputs");
      JSONArray inputs = jsonObject.getJSONArray("inputs");
      String string1 = inputs.getString(0);
      String blockHeight = jsonObject.getString("blockHeight");
      String prevAddresses = JSONObject.parseObject(string1).getJSONArray("prevAddresses").getString(0);
      for (Object output : outputs) {
        if (JSONObject.parseObject(output.toString()).getJSONArray("addresses").size() == 0) {
          continue;
        }
          String addresses = JSONObject.parseObject(output.toString()).getJSONArray("addresses").getString(0);
          String value = JSONObject.parseObject(output.toString()).getString("value");
          if (addresses.equalsIgnoreCase("1M4UyBiyuikgNtcbN8SbWDkxZQxCLGPGwo")) {
            System.out.println("blockHeight = " + blockHeight);
            System.out.println("addresses = " + addresses);
            System.out.println("prevAddresses = " + prevAddresses);
            System.out.println("value = " + value);
            System.out.println("hash = " + hash);
          }

      }
    }
  }
}
