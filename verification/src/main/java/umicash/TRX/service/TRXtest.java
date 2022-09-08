package umicash.TRX.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.TRX.util.HttpUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TRXtest {
    /*
     * 获取最新区块高度
     */
     public static String getBockHeight(){
        String s = HttpUtil.sendGet("https://services.tokenview.com/vipapi/coin/latest", "trx");
        return JSONObject.parseObject(s).get("data").toString();
    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("https://services.tokenview.com/vipapi/block", "trx" , getBockHeight());
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        String fee = JSONObject.parseObject(o).get("fee").toString();
        System.out.println(fee);
        return fee;
    }
    /*
     * 获取区块头详情
     */
    public static void getBlockTransaction (){
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "trx" , "38205699" ,"1","3");
        //System.out.println("s = " + s);
      JSONArray data = JSONObject.parseObject(s).getJSONArray("data");
      String string = data.getString(2);
      JSONArray tokenTransfer = JSONObject.parseObject(string).getJSONArray("tokenTransfer");
      JSONObject jsonObject = tokenTransfer.getJSONObject(0);
      JSONObject tokenInfo = jsonObject.getJSONObject("tokenInfo");
      String string2 = jsonObject.getString("from");
      System.out.println("from = " + string2);
      String string1 = jsonObject.getString("to");
      System.out.println("to = " + string1);
      String s1 = tokenInfo.getString("s");
      System.out.println("s1 = " + s1);
      BigDecimal value = jsonObject.getBigDecimal("value");
      BigDecimal divide = value.divide(BigDecimal.valueOf(1000000), 8, RoundingMode.HALF_DOWN);
      System.out.println("value = " + divide);
      System.out.println(tokenInfo);

    }
    /*
     * 获取区块列表
     */
    public static void getBlockList (){
        String s = HttpUtil.getBList("https://services.tokenview.com/vipapi/blocks", "trx" ,"1","1");
      System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        String miner = JSONObject.parseObject(o).get("miner").toString();
        System.out.println(miner);
    }
    public static void main(String[] args) {
      getBlockTransaction();
        //System.out.println(bockHeight);
    }

}
