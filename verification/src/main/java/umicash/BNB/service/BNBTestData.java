package umicash.BNB.service;

import cn.hutool.json.JSONUtil;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import umicash.ETH.util.EthUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BNBTestData {
    private static String privateKey="";
    private static String myAddress = "";
    private static String contract="";
    private static Web3j ETHweb3j  = Web3j.build(new HttpService("https://mainnet.infura.io/v3/"));;

    /**
     * 查询eth数量
     */
    /*public  static void balanceOf(){
        System.out.println("查询ETH:"+ EthUtils.balanceOf(BNBweb3j,myAddress));
    }*/

    /**
     * 查询ERC20数量
     */
    /*public static void balanceOfErc20(Web3j web3j , String to , String add){
        System.out.println("查询ERC20:"+EthUtils.balanceOfErc20(web3j,to,add));
    }*/

    /**
     * 发送ERC20
     */
    /*public static void sendErc20(){
        String txid = EthUtils.sendErc20(BNBweb3j, contract, privateKey, myAddress, BigInteger.valueOf(10000000));
        System.out.println("发送ERC20 = "+txid);
    }*/

    /**
     * 发送以太坊
     */
    public static void sendEth(){
        String txid = EthUtils.sendEth(ETHweb3j, privateKey, contract, new BigDecimal("0.001"));
        System.out.println("发送ETH:"+txid);
    }

    public static void getTransaction(){
        //eth
        String txid="";
        EthTransaction tx = EthUtils.getTransaction(ETHweb3j, txid);
        System.out.println("查询交易:"+ JSONUtil.toJsonStr(tx));
    }

    public static void main(String[] args) {
       long blockNumber = EthUtils.getNowBlockNumber(ETHweb3j);
        System.out.println("blockNumber = " + blockNumber);
        sendEth();
    }
}
