/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.uniswap;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/23
 * @since 1.0
 */
public class GasPrice {
  public  static BigInteger  gasPrice(Web3j web3j) throws ExecutionException, InterruptedException {
    BigInteger gasPrice;
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    if (ethGasPrice == null) {
      return null;
    }
    return ethGasPrice.getGasPrice();
  }
}
