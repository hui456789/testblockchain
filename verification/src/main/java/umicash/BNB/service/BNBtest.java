package umicash.BNB.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.BNB.util.HttpUtil;

import java.math.BigDecimal;

public class BNBtest {
    /*
     * 获取最新区块高度
     */
     public static void getBockHeight(){
         String s = HttpUtil.doGet("https://api.bscscan.com/api?module=gastracker&action=gasoracle&apikey=","");
         JSONObject jsonObject1 = JSONObject.parseObject(s);
         //System.out.println("jsonObject1 = " + jsonObject1);
         String result = JSONObject.parseObject(s).getString("result");
         Long lastBlock = JSONObject.parseObject(result).getLong("LastBlock");
         long l = lastBlock - 1;
         String str = "https://api.bscscan.com/api?module=account&action=txlist&address=" +
                 "0xF426a8d0A94bf039A35CEE66dBf0227A7a12D11e&" +
                 "startblock=" + 0 +
                 "&endblock=" + 99999999 +
                 "&page=1&offset=1&sort=asc&apikey=";
         String ss = HttpUtil.doGet(str , "");
         JSONArray result1 = JSONObject.parseObject(ss).getJSONArray("result");
         if (result1.size() > 0 ) {
             for (int i = 0; i < result1.size(); i++) {
                 String string = result1.getString(i);
                 JSONObject jsonObject = JSONObject.parseObject(string);
                 String to = jsonObject.getString("to");
                 BigDecimal bigDecimal = jsonObject.getBigDecimal("value");
                 if(bigDecimal.compareTo(BigDecimal.ZERO) > 0) {
                     bigDecimal = bigDecimal.divide(BigDecimal.valueOf(1000000000000000000L));// 注意是100000000
                 }
                 System.out.println("bigDecimal = " + bigDecimal);
                 System.out.println("to = " + to);
             }

         }
    }
    public static void main(String[] args) {
         getBockHeight();
        //System.out.println(bockHeight);
    }

}
