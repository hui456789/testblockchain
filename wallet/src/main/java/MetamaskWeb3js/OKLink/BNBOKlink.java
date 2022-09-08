/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

import static MetamaskWeb3js.OKLink.httpUtil.get;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/15
 * @since 1.0
 */
public class BNBOKlink {
  public static void main(String[] args) throws Exception {
    String str2 = "https://www.oklink.com/api/explorer/v1/bsc/transactionsNoRestrict?blockHeight=18847944&limit=500";
    String s1 = get(str2);
    String data1 = JSONObject.parseObject(s1).getString("data");
    JSONArray hits = JSONObject.parseObject(data1).getJSONArray("hits");
    for (int i = 0; i < hits.size(); i++) {
      JSONObject jsonObject = JSONObject.parseObject(hits.getString(i));
      if (!jsonObject.getBoolean("isToContract")){
        String hash = jsonObject.getString("hash");
        String to = jsonObject.getString("to");
        String from = jsonObject.getString("from");
        BigDecimal value = jsonObject.getBigDecimal("value");
        System.out.println("hash = " + hash);
        System.out.println("value = " + value);
        System.out.println("from = " + from);
        System.out.println("to = " + to);
      }
    }
  }
}
