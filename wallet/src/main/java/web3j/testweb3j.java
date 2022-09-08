/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import umicash.coin.Recharge.ETCRecharge.EthUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/8
 * @since 1.0
 */
public class testweb3j {
  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
    String contract = "";
    String myAddress = "" ;
    balanceOfErc20(web3 , contract , myAddress);
  }


  public static  void balanceOfErc20(Web3j web3j, String contract , String myAddress){
    System.out.println("查询ERC20:"+ EthUtils.balanceOfErc20(web3j,contract,myAddress));
  }
}
