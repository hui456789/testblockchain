package umicash.LUNA.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.LUNA.util.HttpUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LUNAtest {
    /*
     * 获取最新区块高度
     */
    public static String getBockHeight(){
        String s = HttpUtil.sendGet("https://services.tokenview.com/vipapi/coin/latest", "eth");
        return JSONObject.parseObject(s).get("data").toString();
    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("https://services.tokenview.com/vipapi/block", "etc" , getBockHeight());
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
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "eth" , getBockHeight() ,"1","1");
        System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        System.out.println(o);
    }
    /*
     * 获取区块列表
     */
    public static void getBlockList (){
        //https://services.tokenview.com/vipapi/tx/eth/5500018/1/2?apikey=CDYbVXPHybSGgBRhSPbP
        String s = HttpUtil.getBList("https://services.tokenview.com/vipapi/tx/eth", "5500222" ,"1","7");
        String date = JSONObject.parseObject(s).get("data").toString();
        //System.out.println("date = " + date);
        String o = JSONArray.parseArray(date).get(0).toString();
        //System.out.println("o = " + o);
        JSONArray tokenTransfer = JSONObject.parseObject(o).getJSONArray("tokenTransfer");
      //System.out.println("tokenTransfer = " + tokenTransfer);
        int size = tokenTransfer.size();

      if ( tokenTransfer.size()> 0 )  {
        for (int i = 0; i <tokenTransfer.size() ; i++) {
          JSONObject jsonObject = tokenTransfer.getJSONObject(i);
          //System.out.println("jsonObject = " + jsonObject);
          BigDecimal value = jsonObject.getBigDecimal("value");
          BigDecimal divide = value.divide(BigDecimal.valueOf(1000000000), 8, RoundingMode.HALF_UP);
          System.out.println("value = " + divide);
          JSONObject tokenInfo1 = JSONObject.parseObject(jsonObject.toString()).getJSONObject("tokenInfo");
          String s1 = tokenInfo1.getString("s");
          System.out.println("s1 = " + tokenInfo1);
          String h = tokenInfo1.getString("h");
          System.out.println(h);
          System.out.println(s1);

        }
      }
    }
    public static void main(String[] args) {
      getBlockList();
        //System.out.println(bockHeight);

      BigDecimal b1 = new BigDecimal("123.564");
      BigDecimal divide = b1.divide(BigDecimal.valueOf(100000000));
      System.out.println(divide);
    }

}
