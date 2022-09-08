/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.util;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author
 */
public class ContractUtil {

  private Web3j bscWeb3j = Web3j.build(new HttpService("https://data-seed-prebsc-1-s1.binance.org:8545"));

  private Credentials credentials = Credentials.create("matemask的私钥");

  /**
   * call
   * @return
   */
  public Object call() {
    String methodName = "claimUpgratedMaterial";
    List<Type> inputParameters = new ArrayList<>();
    inputParameters.add(new Uint256(1));
    List<TypeReference<?>> outputParameters = new ArrayList<>();

    TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {};
    outputParameters.add(typeReference);
    Function function = new Function(methodName, inputParameters, outputParameters);
    String data = FunctionEncoder.encode(function);
    Transaction transaction = Transaction.createEthCallTransaction("0x500616C4a5CCeE062b6c9aD8855F48940FE754c0", "", data);

    EthCall ethCall;
    Object balanceValue = null;
    try {
      ethCall = bscWeb3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
      List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
      if (results != null) {
        balanceValue = results.get(0).getValue();
      }
    } catch (IOException e) {

    }
    return balanceValue;
  }

  /**
   * send
   * @return
   */
  public String send() throws Exception {
    BigInteger nonce = getNonce("0x500616C4a5CCeE062b6c9aD8855F48940FE754c0");
    String methodName = "startProcess";
    List<Type> inputParameters = new ArrayList<>();
    inputParameters.add(new Uint256(1));
    inputParameters.add(new Uint256(1000));
    List<TypeReference<?>> outputParameters = new ArrayList<>();
    TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {};
    outputParameters.add(typeReference);

    Function function = new Function(methodName, inputParameters, outputParameters);

    String functionEncode = FunctionEncoder.encode(function);

    BigInteger gasPrice = bscWeb3j.ethGasPrice().send().getGasPrice().multiply(BigInteger.TEN);
    BigInteger gasLimit = new BigInteger("500000");

    RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, "0x72A49A8Af3eF49B3058F4e3f320c411eE9979165",functionEncode);
    EthSendTransaction response = bscWeb3j.ethSendRawTransaction(Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, credentials)))
      .sendAsync()
      .get();
    if (response.hasError()) {
      System.out.println("合约deposit方法执行异常：" + response.getError().getMessage());
    } else {
      System.out.println("deposit执行完成，nonce=[" + nonce + "],hash=[" + response.getTransactionHash() + "]");
      String hash = response.getTransactionHash();

      while (true) {
        boolean isOk = transactionCheck(hash);

        System.out.println("transactionCheck：" + isOk);

        if (isOk) {
          break;
        }

        try {
          Thread.sleep(800);
        } catch (Exception e) {
          // TODO: handle exception
        }
      }
    }

    return null;
  }


  /**
   * 使用交易hash查询交易状态
   * @param hash
   * @return
   * @throws IOException
   */
  public boolean transactionCheck(String hash) throws IOException {
    Optional<TransactionReceipt> receipt = bscWeb3j.ethGetTransactionReceipt(hash).send().getTransactionReceipt();
    if (receipt.isPresent()) {
      TransactionReceipt transactionReceipt = receipt.get();
      return Boolean.parseBoolean(transactionReceipt.getBlockHash());
    } else {
      return false;
    }
  }

  /**
   * 获取账户的Nonce
   * @param address
   * @return
   */
  public BigInteger getNonce(String address) {
    try {
      EthGetTransactionCount getNonce = bscWeb3j.ethGetTransactionCount(address,DefaultBlockParameterName.PENDING).send();
      if (getNonce == null){
        throw new RuntimeException("net error");
      }
      return getNonce.getTransactionCount();
    } catch (IOException e) {
      throw new RuntimeException("net error");
    }
  }
}