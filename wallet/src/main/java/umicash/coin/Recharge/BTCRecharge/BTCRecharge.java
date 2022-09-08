package umicash.coin.Recharge.BTCRecharge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bitcoinj.core.UTXO;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


import static com.sun.scenario.Settings.get;

public class BTCRecharge {
    /**
     * 获取用户地址以太坊余额
     *
     * @param address
     */
    public static BigDecimal getBalance(String address) throws IOException {
        Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/"));
        EthGetBalance getBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        return Convert.fromWei(getBalance.getBalance().toString(), Convert.Unit.ETHER);
    }
    /**
     * 获取矿工费用
     *
     * @param amount
     * @param utxos
     * @return
     */
    public static Long getFee(long amount, List<UTXO> utxos) {
        Long feeRate = getFeeRate();//获取费率
        Long utxoAmount = 0L;
        Long fee = 0L;
        Long utxoSize = 0L;
        for (UTXO us : utxos) {
            utxoSize++;
            if (utxoAmount >= (amount + fee)) {
                break;
            } else {
                utxoAmount += us.getValue().value;
                //其实34乘以几都行，加10也可以不要，无非就是手续费低点，打包确认慢点
                // fee = (utxoSize * 148 * 34 * 3 + 10) * feeRate;
                fee = (utxoSize * 148 * 34 * 3 + 10) * feeRate;

            }
        }
        return fee;
    }

    /**
     * 获取btc费率
     *
     * @return
     */
    public static Long getFeeRate() {
        try {
            String httpGet1 = get("https://bitcoinfees.earn.com/api/v1/fees/recommended");
            Map map = JSON.parseObject(httpGet1, Map.class);
            Long fastestFee = Long.valueOf(map.get("hourFee").toString());
            return fastestFee;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static void main(String[] args) throws IOException {
        String myAddress = "";
        Long queryBtc = getFeeRate();
        System.out.println("balance = " + queryBtc);
    }
}
