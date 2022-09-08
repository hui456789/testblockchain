package umicash.BCH.service;

import umicash.BTC.util.HttpUtil;

public class BCHtest {
    /*
     * 获取最新区块高度
     */
     public static String getBockHeight(){
        String s = HttpUtil.sendGet("https://public-api.solscan.io/block/last", "");
        return JSONObject.parseObject(s).get("data").toString();
    }
    /*
     * 获取区块头详情
     */
    public static String getBlockHead(){
        String s = HttpUtil.BlockGet("https://services.tokenview.com/vipapi/block", "bch" , getBockHeight());
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
        String s = HttpUtil.getbtn("https://services.tokenview.com/vipapi/tx", "bch" , getBockHeight() ,"1","1");
        System.out.println("s = " + s);
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        String blockHash = JSONObject.parseObject(o).get("blockHash").toString();
        System.out.println(blockHash);
    }
    /*
     * 获取区块列表
     */
    public static void getBlockList (){
        String s = HttpUtil.getBList("https://services.tokenview.com/vipapi/blocks", "bch" ,"1","1");
        String date = JSONObject.parseObject(s).get("data").toString();
        String o = JSONArray.parseArray(date).get(0).toString();
        String miner = JSONObject.parseObject(o).get("newAddress").toString();
        System.out.println(date);
    }
    public static void main(String[] args) {
        getBockHeight();
        //System.out.println(bockHeight);
    }

}
