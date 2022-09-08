package umicash.BTC.service;

import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String str = "[0.3826BTC, 11.1653ETH, 39.7269BNB]";
        String substring = str.substring(1, str.length()-1);
        String[] strArray = substring.split(",");
        List<String> strings = Arrays.asList(strArray);
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("i = " + strings.get(i));
        }
    }
}
