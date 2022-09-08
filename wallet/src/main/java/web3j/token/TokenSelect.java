/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.token;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/24
 * @since 1.0
 */
public class TokenSelect {
  public static void main(String[] args) {
    //查询的钱包地址
    String from = "";
    //合约地址
    String contractAddress="";
    //合约部署节点
    Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
    try {
      String code = getERC20Balance(web3j,from,contractAddress);
      System.out.println("code = " + code);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static final BigDecimal WEI = new BigDecimal(10000);
  /**
   * 获取ERC-20 token指定地址余额
   *
   * @param address         查询地址
   * @param contractAddress 合约地址
   * @return
   * @throws InterruptedException
   */
  public static String getERC20Balance(Web3j web3j, String address, String contractAddress) throws InterruptedException {
    String methodName = "balanceOf";
    List<Type> inputParameters = new ArrayList<>();
    List<TypeReference<?>> outputParameters = new ArrayList<>();
    Address fromAddress = new Address(address);
    inputParameters.add(fromAddress);

    TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
    };
    outputParameters.add(typeReference);
    Function function = new Function(methodName, inputParameters, outputParameters);
    String data = FunctionEncoder.encode(function);
    Transaction transaction = Transaction.createEthCallTransaction(address, contractAddress, data);

    EthCall ethCall;
    BigDecimal balanceValue = BigDecimal.ZERO;
    try {
      ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
      List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
      Integer value = 0;
      if(results != null && results.size()>0){
        value = Integer.parseInt(String.valueOf(results.get(0).getValue()));
      }
      balanceValue = new BigDecimal(value).divide(WEI, 6, RoundingMode.HALF_DOWN);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balanceValue.toString();
  }
}
