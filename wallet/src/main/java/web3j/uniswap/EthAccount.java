/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.uniswap;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/23
 * @since 1.0
 */
public class EthAccount {
  public  static  EthGetTransactionCount getTradeCount(Web3j web3j , String pk ) throws IOException {
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
      pk, DefaultBlockParameterName.LATEST).send();
    return  ethGetTransactionCount ;
  }
}
