/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/15
 * @since 1.0
 */
public class TRXOKlink {
  public static void main(String[] args) throws Exception {
    List<String> addr = new ArrayList<>();
    addr.add("TCueWbwJHRh9ZLdDopjDbZgF2NJCjtb1G7");
    addr.add("TLgkpuQ68hzMvDbFDKZAAj5P4wtnRTKrAY");
    for (String s : addr) {
      String trc = "https://www.oklink.com/api/explorer/v1/tron/transfers?contractAddress=" + s + "&tokenType=TRC20&limit=50&tokenContractAddress=TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t&to=" + s;
      Thread.sleep(1000);
      String trc20Url = httpUtil.get(trc);
      String trc20data = JSONObject.parseObject(trc20Url).getString("data");
      if (JSONObject.parseObject(trc20data).getIntValue("total") == 0) {
        continue;
      }
      JSONArray hits = JSONObject.parseObject(trc20data).getJSONArray("hits");
      for (int i = 0; i < hits.size(); i++) {
        JSONObject transaction = hits.getJSONObject(i);
        String txid = transaction.getString("txhash");
        String currency = transaction.getString("symbol");
        String to = transaction.getString("to");
        if (to.equalsIgnoreCase(s)) {
          String tokenContractAddress = transaction.getString("tokenContractAddress");
          System.out.println("tokenContractAddress = " + tokenContractAddress);
          System.out.println("txid = " + txid);
          System.out.println("to = " + to);
          System.out.println("currency = " + currency);
        }
      }
    }
  }
}