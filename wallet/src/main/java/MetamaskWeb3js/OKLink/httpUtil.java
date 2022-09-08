/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js.OKLink;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/6
 * @since 1.0
 */
public class httpUtil {

  public static void main(String[] args) throws Exception {
    String result = get("https://www.oklink.com/api/explorer/v1/eth/blocks/14931289");
    System.out.println("result="+result);
  }

  /**
   * get请求
   */
  public static String get(String url) throws Exception {
    String content = null;
    URLConnection urlConnection = new URL(url).openConnection();
    HttpURLConnection connection = (HttpURLConnection) urlConnection;
    connection.setRequestProperty("accept", "*/*");
    connection.setRequestProperty("ok-access-key", "");
    connection.setRequestProperty("contentType", "application/json");
    connection.setRequestProperty("connection", "Keep-Alive");
    connection.setRequestProperty("user-agent",
      "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    connection.setConnectTimeout(60000);
    connection.setReadTimeout(60000);
    //连接
    connection.connect();
    //得到响应码
    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
        (connection.getInputStream(), StandardCharsets.UTF_8));
      StringBuilder bs = new StringBuilder();
      String l;
      while ((l = bufferedReader.readLine()) != null) {
        bs.append(l).append("\n");
      }
      content = bs.toString();
    }
    return content;
  }
}
