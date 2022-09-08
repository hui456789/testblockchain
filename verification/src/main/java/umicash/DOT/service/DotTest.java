package umicash.DOT.service;

import umicash.DOT.util.HttpUtil;

public class DotTest   {
    public static String block() {
        String utl = "https://api.binance.com/api/v3/exchangeInfo?symbol=BNBBTC";
        String param = "";
        String post = HttpUtil.Post(utl , "");
        System.out.println("post = " + post);
        return "";
    }

    public static void main(String[] args) {
        String block = block();
        System.out.println("block = " + block);
    }
}
