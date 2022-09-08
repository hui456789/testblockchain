package umicash.SOL.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.SOL.util.HttpUtil;

import java.math.BigDecimal;

public class SOLtest {
    /*
     * 获取最新区块高度
     */
     public static String getBockHeight(){
         String s = HttpUtil.sendGet("https://public-api.solscan.io/block/last", "");
         for (int i = 0; i < 1 ; i++) {
             String string = JSONArray.parseArray(s).getString(i);
             int currentSlot = JSONObject.parseObject(string).getIntValue("currentSlot");
             String result = JSONObject.parseObject(string).getString("result");
             int transactionCount = JSONObject.parseObject(result).getIntValue("transactionCount");
             int d = transactionCount / 50;
             System.out.println("currentSlot = " + currentSlot);
             System.out.println("d = " + d);
         }
         String url = "https://public-api.solscan.io/account/solTransfers?account=" +""+ "&fromTime=" + (System.currentTimeMillis()- 60*60*1000)
                  ;
         String s1 = HttpUtil.sendGet(url, "");
         //System.out.println("s1 = " + s1);
         String string1 = JSONObject.parseObject(s1).getString("data");
         //System.out.println("string1 = " + string1);
         JSONArray objects = JSONArray.parseArray(string1);
         if (objects.size() > 0) {
             System.out.println("objects = " + objects);
             String string4 = objects.getString(0);
             //System.out.println("string4 = " + string4);
             String transaction = JSONObject.parseObject(string4).getString("txHash");
             //System.out.println("transaction = " + transaction);
             String url1 = "https://public-api.solscan.io/transaction/";
             String s2 = HttpUtil.sendGet(url1, transaction);
             JSONObject jsonObject = JSONObject.parseObject(s2);
             System.out.println("jsonObject = " + jsonObject);
             JSONArray solTransfers = jsonObject.getJSONArray("solTransfers");
             System.out.println("solTransfers = " + solTransfers);
             if (solTransfers.size() > 0) {
                 String string2 = solTransfers.getString(0);
                 BigDecimal amount = JSONObject.parseObject(string2).getBigDecimal("amount");
                 String string3 = JSONObject.parseObject(string2).getString("destination");
                 String txHash = jsonObject.getString("txHash");
                 System.out.println("txHash = " + txHash);
                 System.out.println("s1 = " + string3);
                 System.out.println("amount = " + amount);
             }
         }

         return "沒有結果";
    }
    public static void main(String[] args) {
             getBockHeight();
        //System.out.println(bockHeight);
    }

}
