/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package umicash;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static MetamaskWeb3js.OKLink.httpUtil.get;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/13
 * @since 1.0
 */
public class bolkstest {
  public static void main(String[] args) throws Exception {
    Long value = 0L;
    String k  = null ;
    String url ="https://www.oklink.com/api/explorer/v1/btc/info";
    //获取当前区块高度/币种信息
    String retStr =get(url);
    if(!StringUtils.isEmpty(retStr)){
      String data = JSONObject.parseObject(retStr).getString("data");
      String symbol = JSONObject.parseObject(data).getString("symbol");
      String block = JSONObject.parseObject(data).getString("block");
      String height = JSONObject.parseObject(block).getString("height");
      Map<String, Long> mapadd = new HashMap<>();
      mapadd.put(symbol , Long.valueOf(height));
      for (String s : mapadd.keySet()) {
        k = s ;
        value= mapadd.get(s);
      }
      System.out.println(k + ":" + value);
    }

  }

}
