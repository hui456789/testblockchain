package umicash.coin.Recharge.ETCRecharge;


import cn.hutool.json.JSONUtil;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 测试数据
 *
 * @Autor Tricky
 * @Date 2021-04-01 22:06:36
 */

public class EthTestData {
    private static String privateKey="";

    private static String myAddress = "";
    //rinkeby上面的测试币 erc20-usdt同款
    private static String contract="";

    private static Web3j web3j ;


    {
        try{
            web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/apikey"));
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    /**
     * 创建地址
     */
    /*public void createAddress(){
        System.out.println("创建地址:"+ JSONUtil.toJsonStr(EthUtils.createAddress()));
    }*/

    /**
     * 查询eth数量
     */
    public static void  balanceOf(){
        System.out.println("查询ETH:"+EthUtils.balanceOf(web3j,myAddress));
    }

    /**
     * 查询ERC20数量
     */
    public void balanceOfErc20(){
        System.out.println("查询ERC20:"+EthUtils.balanceOfErc20(web3j,contract,myAddress));
    }

    /**
     * 发送ERC20
     */
    public void sendErc20(){
        String txid = EthUtils.sendErc20(web3j, contract, privateKey, myAddress, BigInteger.valueOf(10000000));
        System.out.println("发送ERC20:"+txid);
    }

    /**
     * 发送以太坊
     */
    public void sendEth(){
        String txid = EthUtils.sendEth(web3j, privateKey, myAddress, new BigDecimal("0.001"));
        System.out.println("发送ETH:"+txid);
    }

    public void getTransaction(){
        //eth
        String txid2="0xef3c06f56085187d6a43edec2bb399a7fe98572aad63bcd5bd80e5e5dab153b3";
        EthTransaction tx = EthUtils.getTransaction(web3j, txid2);
        System.out.println("查询交易:"+JSONUtil.toJsonStr(tx));
    }

    public static void main(String[] args) throws IOException {
        balanceOf();
        String DATA_PREFIX = "";
        //  合约签名方法0x313ce567是查询decimals的
        String DATA_DECIMAL_PREFIX = "";
        // USDT ERC-20的合约地址
        String CONTRACT_ADDRESS = "";
        // 查询地址
        String address = "";
        // 获取余额
        String value = Admin.build(new HttpService("https://mainnet.infura.io"))
                .ethCall(org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(address,
                        CONTRACT_ADDRESS, DATA_PREFIX + address.substring(2)), DefaultBlockParameterName.PENDING).send().getValue();
        // 16进制转10进制
        String valueStr = new BigInteger(value.substring(2), 16).toString();

        // 获取decimals
        String decimals = Admin.build(new HttpService("https://mainnet.infura.io"))
                .ethCall(org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(address,
                        CONTRACT_ADDRESS, DATA_DECIMAL_PREFIX + address.substring(2)), DefaultBlockParameterName.PENDING).send().getValue();
        String decimalStr = new BigInteger(decimals.substring(2), 16).toString();
        // 10的6次方
        BigDecimal WEI = new BigDecimal(Math.pow(10, Double.parseDouble(decimalStr)));
        BigDecimal balance = new BigDecimal(valueStr).divide(WEI, Integer.parseInt(decimalStr), RoundingMode.HALF_DOWN);
        System.out.println(balance);
    }
}

