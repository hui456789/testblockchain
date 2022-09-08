package umicash.LTC.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import umicash.ETC.util.HttpUtil;

public class LTCtest {
    /*
     * 获取最新区块高度
     */
     public static String getBockHeight(){
        String s = HttpUtil.sendGet("https://services.tokenview.com/vipapi/coin/latest", "ltc");
        return JSONObject.parseObject(s).get("data").toString();
    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("https://services.tokenview.com/vipapi/block", "ltc" , getBockHeight());
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
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "ltc" , getBockHeight() ,"1","1");
        //System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        System.out.println(o);
    }
    /*
     * 获取区块列表
     */
    public static void getBlockList (){
        String s = HttpUtil.getBList("https://services.tokenview.com/vipapi/blocks", "ltc" ,"1","1");
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
