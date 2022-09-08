package umicash.BTC.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.BTC.util.HttpUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BTCtest {
    /*
     * 获取最新区块高度
     */
     public static void getBockHeight(){

         //String url = "https://services.tokenview.com/vipapi/address/btc/" + "183hmJGRuTEi2YDCWy5iozY8rZtFwVgahM" + "/1/10" + apikey;
         String s = HttpUtil.sendGet("https://services.tokenview.com/vipapi/coin/latest", "btc");
         String data = JSONObject.parseObject(s).get("data").toString();
         System.out.println("data = " + data);
         String s1 = HttpUtil.sendGet("https://services.tokenview.com/vipapi/block/btc/",data );
         JSONArray data1 = JSONObject.parseObject(s1).getJSONArray("data");
         //System.out.println("data1 = " + data1);
         BigDecimal txCnt = data1.getJSONObject(0).getBigDecimal("txCnt");
         System.out.println("txCnt = " + txCnt);


         String retStr = "https://services.tokenview.com/vipapi/tx/btc/" + data  + "/1/50";
         String l1 = HttpUtil.sendGet(retStr, "" );
         JSONArray txList = JSONObject.parseObject(l1).getJSONArray("data");
         for (int i = 0; i < txList.size(); i++) {
             JSONObject tx = txList.getJSONObject(i);
             JSONArray outputs = tx.getJSONArray("outputs");
             JSONArray inputs = tx.getJSONArray("inputs");
           List<String> list = new ArrayList<String>();
           for (int  l = 0; l < inputs.size(); l++) {
               if (outputs.getJSONObject(l).get("address") != null) {
                 list.add(inputs.getJSONObject(l).get("address").toString());
               }
             }
             for (int j = 0; j < outputs.size(); j++) {
                 if (outputs.getJSONObject(j).get("address") != null){
                     String address = outputs.getJSONObject(j).get("address").toString();
                     BigDecimal value = outputs.getJSONObject(j).getBigDecimal("value");
                    // System.out.println("value = " + value +"\t\t\t\t"+ "address=" + address);
                 }
             }
         }
    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("http://api.binance.com/api/v3/exchangeInfo?symbol=BNBBTC");
        System.out.println(s);
        return s;
    }
    /*
     * 获取区块头详情
     */
    public static void getBlockTransaction (){
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "etc" , "1" ,"1","1");
        System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        System.out.println(o);
    }
    /*
     * 获取区块列表
     */
    public static void getBlockList () throws IOException {
        String url1 = "https://polkadot.api.subscan.io/api/scan/metadata";
        String body1 = "";
        //8573454
        String httpPost = HttpUtil.getHttpPost(url1,body1);
        String data = JSONObject.parseObject(httpPost).getString("data");
        Long blockNum = JSONObject.parseObject(data).getLong("blockNum");
       // System.out.println("blockNum = " + blockNum);
        String url = "https://polkadot.api.subscan.io/api/scan/blocks";
        String body = "{\"row\": 1,\"page\":1}";
        String httpPost1 = HttpUtil.getHttpPost(url,body);
        String data1 = JSONObject.parseObject(httpPost1).getString("data");
        JSONArray jsonArray = JSONObject.parseObject(data1).getJSONArray("blocks");
        String string = jsonArray.getString(0);
        //System.out.println("string = " + string);
        String blockhash = JSONObject.parseObject(string).getString("hash");
        String url2 = "https://polkadot.api.subscan.io/api/scan/block";
        String body2 = "{\"block_hash\": \""+ blockhash + "\"}";
        String httpPost2 = HttpUtil.getHttpPost(url2,body2);
        //System.out.println("httpPost2 = " + httpPost2);
        String data3 = JSONObject.parseObject(httpPost2).getString("data");
        //System.out.println("data3 = " + data3);
        JSONArray extrinsics = JSONObject.parseObject(data3).getJSONArray("extrinsics");

        //String call_module_function = JSONObject.parseObject(extrinsics.getString(2)).getString("call_module_function");
        String extrinsic_index = "";//JSONObject.parseObject(extrinsics.getString(2)).getString("extrinsic_index");
        //System.out.println("httpPost2 = " + call_module_function);
        //System.out.println("extrinsic_index = " + extrinsic_index);
        String url3 = "https://polkadot.api.subscan.io/api/scan/extrinsic";
        extrinsic_index = "8616558-2" ;
        String body3 = "{\"extrinsic_index\": \""+extrinsic_index+"\"}";
        String httpPost3 = HttpUtil.getHttpPost(url3,body3);
        String data2 = JSONObject.parseObject(httpPost3).getString("data");
        //System.out.println("data2 = " + data2);
        JSONObject jsonObject2 = JSONObject.parseObject(data2);
        String string1 = jsonObject2.getString("extrinsic_hash");
        System.out.println("extrinsic_hash = " + string1);
        String transfer = JSONObject.parseObject(data2).getString("transfer");
        JSONObject jsonObject = JSONObject.parseObject(transfer);
        String to = jsonObject.getString("to");
        BigDecimal amount = jsonObject.getBigDecimal("amount");
        System.out.println("httpPost3 = " + to);
        System.out.println("amount = " + amount);
    }
    public static void main(String[] args) throws IOException {

      getBlockHead();

    }

}
