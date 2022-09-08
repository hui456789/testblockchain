package umicash.ETH.service;

import cn.hutool.json.JSONUtil;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import umicash.ETH.util.EthUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ETHTestData {
    //    {"address":"0xe81128942ed67a3b453576cad44fa9fb7f0b2098","privateKey":"8ca3edaabc0567d9555ade455bab24a27bea6ee0524e96ffac9a3cfc2b841214"}
    private static String privateKey="";

    private static String myAddress = "";
    //rinkeby上面的测试币
    private static String contract="";

    private static Web3j ETHweb3j  = Web3j.build(new HttpService("https://mainnet.infura.io/v3/"));
    private static Web3j BNBweb3j  = Web3j.build(new HttpService("https://data-seed-prebsc-1-s1.binance.org:8545"));
    /*{
        try{
            //如果这个地址不知道怎么获取 可以参考  https://blog.csdn.net/sail331x/article/details/115395131
            web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/78bfd39bf8ea44f58db79e51774b81aa"));
        }catch (Throwable t){
            t.printStackTrace();
        }
    }*/


    /**
     * 查询eth数量
     */
    public  static void balanceOf(){
        System.out.println("查询ETH:"+ EthUtils.balanceOf(BNBweb3j,myAddress));
    }

    /**
     * 查询ERC20数量
     */
    public static void balanceOfErc20(Web3j web3j , String to , String add){
        System.out.println("查询ERC20:"+EthUtils.balanceOfErc20(web3j,to,add));
    }

    /**
     * 发送ERC20
     */
    public static void sendErc20(){
        String txid = EthUtils.sendErc20(ETHweb3j, contract, privateKey, myAddress, BigInteger.valueOf(10000000));
        System.out.println("发送ERC20:"+txid);
    }

    /**
     * 发送以太坊
     */
    public static void sendEth(){
        String txid = EthUtils.sendEth(BNBweb3j, privateKey, contract, new BigDecimal("0.001"));
        System.out.println("发送ETH:"+txid);
    }

    public static void getTransaction(){
        //eth
        String txid="0x5472f593d46093916c3f15274e17413f6e91b74c37b365d198794bdc39f9ba9a";
        EthTransaction tx = EthUtils.getTransaction(ETHweb3j, txid);
        System.out.println("查询交易:"+ JSONUtil.toJsonStr(tx));
    }

    public static void main(String[] args) {
       /*long blockNumber = EthUtils.getNowBlockNumber(BNBweb3j);
        System.out.println("blockNumber = " + blockNumber);
        balanceOfErc20(BNBweb3j , contract , myAddress);*/
        //balanceOf();
        sendEth();
        //getTransaction();
    }
}
