/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import web3j.inputdate.constant.EthContract;
import web3j.inputdate.constant.Eth_Dao_Contract;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static web3j.util.ComplementUtil.*;
import static web3j.util.utils.*;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/7/18
 * @since 1.0
 */
public class ethTest {
  private  static  Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
  /**
   * 获取地址余额
   * */
  public static  BigDecimal balance(String address) throws ExecutionException, InterruptedException {
    //获取地址余额
    EthGetBalance ethGetBalance = web3j.ethGetBalance(
      address,
      DefaultBlockParameterName.fromString(DefaultBlockParameterName.LATEST.name())
    ).sendAsync().get();
    BigInteger balance = ethGetBalance.getBalance();
    BigDecimal divide = new BigDecimal(balance).divide(BigDecimal.valueOf(1000000000000000000L));
    System.out.println("ETH ==" +divide);
    return divide;
  }

  /**
   * 获取当前的 Gas 价格
   * */
  public static  BigInteger gasrice() throws ExecutionException, InterruptedException {
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    return ethGasPrice.getGasPrice();
  }
  /**
   * 通过交易哈希获取交易详情
   * 交易手续费(Tx Fee) = 实际运行步数(Actual Gas Used) * 单步价格(Gas Price)
   * */
  public static void transactionDetails(String transactionHash) throws ExecutionException, InterruptedException {
    EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get();
    TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().orElseThrow(RuntimeException::new);
    String tranHash = transactionReceipt.getTransactionHash();
    String blockHash = transactionReceipt.getBlockHash();
    String from = transactionReceipt.getFrom();
    BigInteger gasUsed = transactionReceipt.getGasUsed();
    BigInteger gasrice = gasrice();
    BigInteger divide =  gasUsed.multiply(gasrice);
    BigDecimal gasfee= new BigDecimal(divide).divide(BigDecimal.valueOf(1000000000000000000L));
    String to = transactionReceipt.getTo();
    BigInteger cumulativeGasUsed = transactionReceipt.getCumulativeGasUsed();
    String gasUsedRaw = transactionReceipt.getGasUsedRaw();
    System.out.println("gasUsedRaw = " + gasUsedRaw);
    System.out.println("hash = " +tranHash + " = blockHash = " + blockHash + " = from = " + from + " = to = " + to + " = gasfee = " + gasfee + " = cumulativeGasUsed = " + cumulativeGasUsed);
  }
  /**
    * 发起一笔ETH转账
    * */
  public static void tran(String fromAddress, String fromPk, String toAddress,BigInteger value) throws Exception {
    //使用私钥生成Credentials对象
    Credentials credentials = Credentials.create(fromPk);
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
      fromAddress, DefaultBlockParameterName.LATEST).send();
    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
    BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
    BigInteger gasLimit = new BigInteger("900000");
    //生成RawTransaction交易对象
    RawTransaction rawTransaction  = RawTransaction.createTransaction(nonce,gasPrice,gasLimit,toAddress,value,"abcde123");//可以额外带数据
    //使用Credentials对象对RawTransaction对象进行签名
    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,credentials);
    String hexValue = Numeric.toHexString(signedMessage);
    EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
    String transactionHash = ethSendTransaction.getTransactionHash();
    System.out.println("transactionHash = " + transactionHash);
    if(ethSendTransaction.hasError()){
      String message=ethSendTransaction.getError().getMessage();
      System.out.println("transaction failed,info:"+message);
    }else{
      EthGetTransactionReceipt send = web3j.ethGetTransactionReceipt(transactionHash).send();
      if (send != null) {
        System.out.println("交易成功");
      }
    }
  }
  /**
   * erc20代币转账
   * @param from            转账地址
   * @param to              收款地址
   * @param value           转账金额
   * @param privateKey      转账这私钥
   * @param contractAddress 代币合约地址
   */
  public static String transferERC20Token(String from, String to, BigInteger value, String privateKey, String contractAddress, int decimal) throws InterruptedException, IOException, ExecutionException {
    //加载转账所需的凭证，用私钥
    Credentials credentials = Credentials.create(privateKey);
    //获取nonce，交易笔数
    BigInteger nonce;
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send();
    if (ethGetTransactionCount == null) {
      return null;
    }
    nonce = ethGetTransactionCount.getTransactionCount();
    //gasPrice和gasLimit 都可以手动设置
    BigInteger gasPrice;
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    if (ethGasPrice == null) {
      return null;
    }
    gasPrice = ethGasPrice.getGasPrice();
    BigInteger gasLimit = BigInteger.valueOf(6000000L);
    //ERC20代币合约方法
    BigInteger val = new BigDecimal(value).multiply(new BigDecimal("10").pow(decimal)).toBigInteger();// 单位换算
    Function function = new Function(
      "transfer",
      Arrays.asList(new Address(to), new Uint256(val)),
      Collections.singletonList(new TypeReference<Type>() {
      }));
    String encodedFunction = FunctionEncoder.encode(function);
    RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
      contractAddress, encodedFunction);

    //签名Transaction
    byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
    String hexValue = Numeric.toHexString(signMessage);
    //发送交易
    EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
    String hash = ethSendTransaction.getTransactionHash();
    System.out.println("hash = " + hash);
    if (hash != null) {
      return hash;
    }
    return null;
  }

  //交易使用eth换usdc
  public static String TranUsdc(String from, String sendKey,String to, String num , String contractAddress) throws Exception{
    Credentials credentials = Credentials.create(sendKey);
    //gasPrice和gasLimit 都可以手动设置
    BigInteger gasPrice;
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    if (ethGasPrice == null) {
      return null;
    }
    gasPrice = ethGasPrice.getGasPrice();

    BigInteger gasLimit = BigInteger.valueOf(6000000L);
    EthContract contract = EthContract.load(contractAddress, web3j, credentials,
      gasPrice, gasLimit);
    TransactionReceipt result = contract.transfer(to, Convert.toWei(num, Convert.Unit.MWEI).toBigInteger()).send();
    //result 中有交易hash，块高，gas使用数等数据 去TransactionReceipt类中看
    String transactionHash = result.getTransactionHash();
    return transactionHash;
  }

  //交易使用eth换Dao
  public static String TranDao(String sendKey,String contractAddress) throws Exception{
    Credentials credentials = Credentials.create(sendKey);
    //gasPrice和gasLimit 都可以手动设置
    BigInteger gasPrice;
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    if (ethGasPrice == null) {
      return null;
    }
    gasPrice = ethGasPrice.getGasPrice();
    BigInteger gasLimit = BigInteger.valueOf(6000000L);
    Eth_Dao_Contract load = Eth_Dao_Contract.load(contractAddress, web3j, credentials,
      gasPrice, gasLimit);
    String token1 = "";
    String token1to16string = string2HexString(numericStrUrl(token1));
    byte[] token1byte = Hex16StringToHex16Byte(token1to16string);
    String token0 = "";
    String token0to16string = string2HexString(numericStrUrl(token0));
    byte[] token0byte = Hex16StringToHex16Byte(token0to16string);
    String fee = "3000";
    String feesrc = string2HexString(numericBigUrl(fee));
    byte[] feebyte = Hex16StringToHex16Byte(feesrc);
    String owneraddress = "";
    String owneraddressTo16Str = string2HexString(numericStrUrl(owneraddress));
    byte[] ownerAddreessbyte = Hex16StringToHex16Byte(owneraddressTo16Str);
    String amountOut = "";
    String amountOutstc16 = string2HexString(numericBigUrl(amountOut));
    byte[] amountOutbyte = Hex16StringToHex16Byte(amountOutstc16);
    String quotedAmountIn = "";
    String quotedAmountInsrc = string2HexString(numericBigUrl(quotedAmountIn));
    byte[] quotedAmountInbyte = Hex16StringToHex16Byte( quotedAmountInsrc);
    String o = "0";
    String ostc = string2HexString(numericStrUrl(o));
    byte[] o0 = Hex16StringToHex16Byte(ostc);
    //0x
    String inputData = "";
    byte[] bytessrc = hexToByteArray(inputData);
    List<byte[]> bytes = new ArrayList<>();
    bytes.add(bytessrc);
    TransactionReceipt result = load.multicall(BigInteger.valueOf(new Date().getTime() + 1000*60*3), bytes).send();
    String transactionHash = result.getTransactionHash();
    return transactionHash;
  }

  public static void main(String[] args) throws Exception {
    String fromAddress = "";
    String fromPk = "";
    String fromContractAddress = "";
    String contractAddress = "";
    String toAddress = "";
    BigInteger value = BigInteger.valueOf(1100000000);
    tran(fromAddress,fromPk,toAddress , value);
  }
}
