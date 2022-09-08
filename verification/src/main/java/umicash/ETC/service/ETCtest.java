package umicash.ETC.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.ETC.util.HttpUtil;

import java.math.BigDecimal;

public class ETCtest {
    /*
     * 获取最新区块高度
     */
     public static void getBockHeight(){
         //
         String str = HttpUtil.sendGet("https://services.tokenview.com/vipapi/addr/b/btc/183hmJGRuTEi2YDCWy5iozY8rZtFwVgahM", "");
         BigDecimal data = JSON.parseObject(str).getBigDecimal("data");
         System.out.println("data = " +  data);
         //String token = JSONObject.parseObject(s).get("token").toString();


    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("https://services.tokenview.com/vipapi/block", "etc" ,"1");
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
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "etc" , "1" ,"1","1");
        System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        System.out.println(o);
    }
    /*
     * 获取区块列表
     */
    public static void getBlockList (){
        String s = HttpUtil.getBList("https://services.tokenview.com/vipapi/blocks", "eth" ,"1","1");
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        String miner = JSONObject.parseObject(o).get("miner").toString();
        System.out.println(miner);
    }
    public static void main(String[] args) {
      getBlockList();
        //System.out.println(bockHeight);
        /*List<String> list = new ArrayList();
        list.add("1");
        list.add("3");
        list.add("4");
        list.forEach(lis -> {
            System.out.println("lis = " + lis);
        });*/
        //Date date = new Date(1641540805 * 1000);
        //System.out.println("date = " + System.currentTimeMillis());
    }

}
