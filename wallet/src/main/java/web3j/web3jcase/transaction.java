/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.web3jcase;

import com.alibaba.fastjson.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import web3j.util.EthConstant;

import java.math.BigInteger;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/29
 * @since 1.0
 */
public class transaction {

  /**
  * 查询以太币余额
  * */
  public BigInteger getBalance(String address) {
    Web3j web3 = Web3j.build(new HttpService(EthConstant.SERVER));
    BigInteger banlance = new BigInteger("0");
    try {
      banlance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //这里返回的单位是wei 以太币的单位是ether
    //如果需要转成ether
    //BigDecimal ether = Convert.fromWei(banlance.toString(), Convert.Unit.ETHER);
    return banlance;
  }

  /**
   * 使用智能合约查询代币余额
   */
  public BigInteger getUsdtBalance(String address) {

    Web3j web3 = Web3j.build(new HttpService(EthConstant.SERVER));
    BigInteger banlance = new BigInteger("0");
    try {
      Credentials credentials = Credentials.create(EthConstant.OUT_KEY);
      EthContract contract = EthContract.load(EthConstant.UMI_CONTRACTA_DDRESS, web3, credentials,
        Convert.toWei(EthConstant.GAS_PRICE, Convert.Unit.GWEI).toBigInteger(), Convert.toWei(EthConstant.GAS_LIMIT, Convert.Unit.WEI).toBigInteger());
      banlance = contract.balanceOf(address).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //这里返回的单位是wei  如果需要转成个数
    //BigDecimal ether = Convert.fromWei(banlance.toString(), Convert.Unit.MWEI);
    return banlance;
  }
  /**
   * 代币转账
   */
  public static String tranToken( String to, String num) throws Exception{

    Web3j web3 = Web3j.build(new HttpService(EthConstant.SERVER));
    String sendKey = EthConstant.OUT_KEY;//from账户的私钥 根据自己情况去查
    Credentials credentials = Credentials.create(sendKey);
    EthContract contract = EthContract.load(EthConstant.UMI_CONTRACTA_DDRESS, web3, credentials,
      Convert.toWei(EthConstant.GAS_PRICE, Convert.Unit.GWEI).toBigInteger(), Convert.toWei(EthConstant.GAS_LIMIT, Convert.Unit.WEI).toBigInteger());
    TransactionReceipt result = contract.transfer(to, Convert.toWei(num, Convert.Unit.MWEI).toBigInteger()).send();
    //result 中有交易hash，块高，gas使用数等数据 去TransactionReceipt类中看
    return result.getTransactionHash();
  }

  public static void main(String[] args) throws Exception {
    String to = "";
    String num = "1520";
    String s = tranToken(to, num);
    System.out.println("s = " + s);
  }

}
