/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j;

import com.alibaba.fastjson.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import web3j.inputdate.constant.UmiContract;
import web3j.inputdate.constant.UsdcContract;
import web3j.util.EthConstant;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/7/15
 * @since 1.0
 */
public class ethAggregate {
  private static Web3j web3j = Web3j.build(new HttpService(EthConstant.SERVER));
  //获取ETH余额
  public static BigDecimal getBalance(String address) {
    BigInteger banlance = new BigInteger("0");
    try {
      banlance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //这里返回的单位是wei 以太币的单位是ether
    //如果需要转成ether
    BigDecimal bigdec = new BigDecimal(banlance);
    return bigdec.divide(BigDecimal.valueOf(1000000000000000000L));
  }
  //获取ERC20链上USDC余额
  public static BigDecimal getUsdcBalance(String address) {
    BigInteger banlance = new BigInteger("0");
    try {
      Credentials credentials = Credentials.create(EthConstant.OUT_KEY);
      UsdcContract contract = UsdcContract.load(EthConstant.USDC_CONTRACTA_DDRESS, web3j, credentials,
        Convert.toWei(EthConstant.GAS_PRICE, Convert.Unit.GWEI).toBigInteger(), Convert.toWei(EthConstant.GAS_LIMIT, Convert.Unit.WEI).toBigInteger());
      banlance = contract.balanceOf(address).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return  new BigDecimal(banlance).divide(BigDecimal.valueOf(1000000L));
  }

  public static BigDecimal getUmiBalance(String address) {
    BigInteger banlance = new BigInteger("0");
    try {
      Credentials credentials = Credentials.create(EthConstant.OUT_KEY);
      UmiContract contract = UmiContract.load(EthConstant.UMI_CONTRACTA_DDRESS ,web3j, credentials,
        Convert.toWei(EthConstant.GAS_PRICE, Convert.Unit.GWEI).toBigInteger(), Convert.toWei(EthConstant.GAS_LIMIT, Convert.Unit.WEI).toBigInteger());
      banlance = contract.balanceOf1(address).send();
    } catch (Exception e) {
      e.printStackTrace();

    }
    BigDecimal bigdec = new BigDecimal(banlance);
    BigDecimal divide = bigdec.divide(BigDecimal.valueOf(1000000L));
    return divide;
  }
  //交易使用umi换usdc
  public JSONObject TranUsdc(String from, String to, String num) throws Exception{
    Web3j web3 = Web3j.build(new HttpService(EthConstant.SERVER));
    String sendKey = "";//from账户的私钥 根据自己情况去查
    Credentials credentials = Credentials.create(sendKey);
    UmiContract contract = UmiContract.load(EthConstant.USDC_CONTRACTA_DDRESS, web3j, credentials,
      Convert.toWei(EthConstant.GAS_PRICE, Convert.Unit.GWEI).toBigInteger(), Convert.toWei(EthConstant.GAS_LIMIT, Convert.Unit.WEI).toBigInteger());
    TransactionReceipt result = contract.transfer(to, Convert.toWei(num, Convert.Unit.MWEI).toBigInteger()).send();
    //result 中有交易hash，块高，gas使用数等数据 去TransactionReceipt类中看
    String transactionHash = result.getTransactionHash();
    return null;
  }

  public static void main(String[] args) {
    String address = "";
    BigDecimal umiBalance = getUsdcBalance(address);
    System.out.println("balance = " + umiBalance);
  }

}
