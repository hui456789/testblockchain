/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/6
 * @since 1.0
 */
public class oklinktest {
  public static void main(String[] args) throws Exception {
    String str2 = "https://www.oklink.com/api/explorer/v1/btc/transactions?blockHeight=740586&offset=300&limit=150 ";
    String s1 = httpUtil.get(str2);
    String data1 = JSONObject.parseObject(s1).getString("data");
    JSONObject jsonObject = JSONObject.parseObject(data1);
    for (int i = 0; i < jsonObject.getJSONArray("hits").size(); i++) {
      JSONArray hits = jsonObject.getJSONArray("hits");
      JSONArray outputs = JSONObject.parseObject(hits.getString(i)).getJSONArray("outputs");
      JSONArray inputs = JSONObject.parseObject(hits.getString(i)).getJSONArray("inputs");
      int inputsCount = JSONObject.parseObject(hits.getString(i)).getIntValue("inputsCount");
      System.out.println("inputsCount = " + inputsCount);
      if (inputs.size()>0 ){
        for (int j = 0; j < inputs.size(); j++) {
          JSONArray inputs1 = JSONObject.parseObject(inputs.getString(j)).getJSONArray("prevAddresses");
          System.out.println("inputs1 = " + inputs1.getString(0));
        }
      }

      if (outputs.size() > 0) {
        for (int j = 0; j < outputs.size(); j++) {
          }
        }
    }
  }
}