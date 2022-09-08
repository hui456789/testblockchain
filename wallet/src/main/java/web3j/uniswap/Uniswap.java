package web3j.uniswap;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
@Service
public class Uniswap {

  @Autowired
  private static EthGas ethGas;

  @Autowired
  private static EthAccount ethAccount;

  /**
   *
   * @param gasLimit  gaslimit
   * @param gasPrice   gas费用
   * @param web3j       链路
   * @param privateKey   自己的私钥
   * @param v            要兑换的对应代币的位数
   * @param contractAddress    如用USDT兑换BTC，则此值是BTC的合约地址
   * @param amountOutMin      兑换代币的最小的个数
   * @param sendTokenContractAddress   如用USDT兑换BTC，则此值是USDT的合约地址
   * @param approveAddress	approve合约地址，设置此值可以合约内直接确认，无需调钱包插件确认
   */
  public static String  sendContract(BigInteger gasLimit, BigInteger gasPrice, Web3j web3j , String privateKey, BigDecimal v, String contractAddress, BigInteger amountOutMin,
                                     String sendTokenContractAddress, String approveAddress) {
    try {
      Credentials credentials = Credentials.create(privateKey);
      String fromAddress = credentials.getAddress();
      //获取交易次数
      //EthGetTransactionCount ethGetTransactionCount = ethAccount.getTradeCount(web3j, contractAddress);
      //错误返回
      /*if (ethGetTransactionCount.hasError()) {
        return ethGetTransactionCount.getError().getMessage();
      }*/
      BigInteger nonce;
      EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
      if (ethGetTransactionCount == null) {
        return null;
      }
      nonce = ethGetTransactionCount.getTransactionCount();
      //合约函数参数
      List<Address> addList = new ArrayList<>();
      addList.add(new Address(sendTokenContractAddress));
      addList.add(new Address(approveAddress));
      StaticArray<Address> address = new StaticArray<Address>(addList) {
      };
      if(gasPrice.compareTo(BigInteger.valueOf(0)) != 1) { //没有输入gas时查询当前链路的gas
        EthGasPrice ethGasPrice = ethGas.getGasPrice(web3j);
        if (ethGasPrice.hasError()) {
          return ethGasPrice.getError().getMessage();
        }
        gasPrice = ethGasPrice.getGasPrice();
      }
      //创建inputdata
      String data = createSwapMethod(amountOutMin, address, addList.size(), new Address(fromAddress),
        new Uint256(System.currentTimeMillis() / 1000 + 300));

      BigInteger value = Convert.toWei(String.valueOf(v.toString()), Convert.Unit.ETHER).toBigInteger();
      RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress,
        value, data);
      byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
      String hexValue = Numeric.toHexString(signedMessage);
      //发送交易
      EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
      if (ethSendTransaction.hasError()) {
        return ethSendTransaction.getError().getMessage();
      }
      String transactionHash = ethSendTransaction.getTransactionHash();
      EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(transactionHash)
        .sendAsync().get();
      if (ethGetTransactionReceipt.hasError()) {
        return ethGetTransactionReceipt.getError().getMessage();
      }
      return transactionHash;
    } catch (Exception e) {
      // TODO: handle exception
      return e.getMessage();
    }
  }

  @SuppressWarnings("rawtypes")
  public static String createSwapMethod(BigInteger amountOutMin, StaticArray<Address> addressArray, int size,
                                        Address to, Uint256 deadline) {
    List<Type> parametersList = new ArrayList<>();
    parametersList.add(new Uint256(amountOutMin));
    parametersList.add(new Uint256(128));
    parametersList.add(to);
    parametersList.add(deadline);
    parametersList.add(new Uint256(BigInteger.valueOf(size)));
    parametersList.add(addressArray);
    List<TypeReference<?>> outList = new ArrayList<>();
    Function function = new Function("swapExactETHForTokens", parametersList, outList);
    String encodedFunction = FunctionEncoder.encode(function);
    System.out.println(encodedFunction);
    //函数编码不正确，先替换
    return encodedFunction.replace("0x8fd067c2", "0x7ff36ab5");
  }
  /**
   *  gasLimit  gaslimit
   *  gasPrice   gas费用
   *  host       链路
   *  privateKey   自己的私钥
   *  v            要兑换的对应代币的
   *  contractAddress    如用USDT兑换ETH，则此值是ETH的合约地址
   *  amountOutMin      兑换代币的最小的个数
   *  sendTokenContractAddress   如用USDT兑换ETH，则此值是USDT的合约地址
   *  approveAddress	approve合约地址，设置此值可以合约内直接确认，无需调钱包插件确认
   * */

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
    BigInteger gasLimit = BigInteger.valueOf(9000000);
    //BigInteger gasPrice = GasPrice.gasPrice(web3j);
    BigInteger gasPrice = BigInteger.valueOf(20101);
    System.out.println("gasPrice = " + gasPrice);
    String fromPk = "";
    BigDecimal v = BigDecimal.valueOf(6);
    //0x0000000000000000000000000000000000000000
    String contractAddress = "";
    BigInteger amountOutMin =  BigInteger.valueOf(1234);
    //0x780155C5Ac259961d5F0BDeD22E6C41f465ED6a6
    String sendTokenContractAddress = "";
    String approveAddress = "" ;
    String s = sendContract(gasLimit, gasPrice, web3j, fromPk, v, contractAddress, amountOutMin, sendTokenContractAddress,
      approveAddress);
    System.out.println("s = " + s);
  }
}