/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.uniswap;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/26
 * @since 1.0
 */
public class tokenTest {
  public static void transferToken() throws Exception {
    Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
    Credentials credentials = Credentials.create("");
    String fromAddress = credentials.getAddress();
    EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
      fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
    BigInteger nonce = ethGetTransactionCount.getTransactionCount();

    Address address = new Address("");
    Uint256 value = new Uint256(new BigInteger("100000"));
    List<Type> parametersList = new ArrayList<>();
    parametersList.add(address);
    parametersList.add(value);
    List<TypeReference<?>> outList = new ArrayList<>();
    Function function = new Function("transfer", parametersList, outList);
    String encodedFunction = FunctionEncoder.encode(function);
    /*System.out.println( DefaultGasProvider.GAS_PRICE);
    System.out.println(DefaultGasProvider.GAS_LIMIT);*/
    EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
    RawTransaction rawTransaction = RawTransaction.createTransaction(nonce,  ethGasPrice.getGasPrice(),
      new BigInteger("210000"), "", encodedFunction);
    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
    String hexValue = Numeric.toHexString(signedMessage);
    EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
    Object transactionHash = ethSendTransaction.getTransactionHash();
    System.out.println(transactionHash.toString());
  }

  public static void main(String[] args) throws Exception {
    transferToken();
  }
}
