package umicash.coin.transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;

import com.alibaba.fastjson.JSON;
import org.bitcoinj.core.TransactionConfidence;

public class BCTTransaction {
    private  static NetworkParameters params = new TestNet3Params();


    /**
     * @Title: signTransaction
     * @param @param privKey 私钥
     * @param @param recevieAddr 收款地址
     * @param @param formAddr 发送地址
     * @param @param amount 金额
     * @param @param fee 手续费(自定义 或者 默认)
     * @param @param unUtxos 未交易的utxo
     * @param @return    参数
     * @return char[]    返回类型
     * @throws
     */
    public static String signTransaction(String privKey, String recevieAddr, String formAddr,
                                         long amount, long fee,
                                         List<UnSpentUtxo> unUtxos) {
        if(!unUtxos.isEmpty() && null != unUtxos){
            List<UTXO> utxos = new ArrayList<UTXO>();
            // String to a private key
            DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, privKey);
            ECKey key = dumpedPrivateKey.getKey();
            // 接收地址
            Address receiveAddress = Address.fromBase58(params, recevieAddr);
            // 构建交易
            Transaction tx = new Transaction(params);
            tx.addOutput(Coin.valueOf(amount), receiveAddress); // 转出
            // 如果需要找零 消费列表总金额 - 已经转账的金额 - 手续费
            long value = unUtxos.stream().mapToLong(UnSpentUtxo::getValue).sum();
            Address toAddress = Address.fromBase58(params, formAddr);
            long leave  = value - amount - fee;
            if(leave > 0){
                tx.addOutput(Coin.valueOf(leave), toAddress);
            }
            // utxos is an array of inputs from my wallet
            for (UnSpentUtxo unUtxo : unUtxos) {
                utxos.add(new UTXO(Sha256Hash.wrap(unUtxo.getHash()),
                        unUtxo.getTxN(),
                        Coin.valueOf(unUtxo.getValue()),
                        unUtxo.getHeight(),
                        false,
                        new Script(Utils.HEX.decode(unUtxo.getScript())),
                        unUtxo.getAddress()));
            }
            for (UTXO utxo : utxos) {
                TransactionOutPoint outPoint = new TransactionOutPoint(params, utxo.getIndex(), utxo.getHash());
                // YOU HAVE TO CHANGE THIS
                tx.addSignedInput(outPoint, utxo.getScript(), key, Transaction.SigHash.ALL, true);
            }
            Context context = new Context(params);
            tx.getConfidence().setSource(TransactionConfidence.Source.NETWORK);
            tx.setPurpose(Transaction.Purpose.USER_PAYMENT);

            return new String(Hex.encodeHex(tx.bitcoinSerialize()));
        }
        return null;
    }

    public static void main(String[] args) {
        List<UnSpentUtxo> us = new ArrayList<UnSpentUtxo>();
        UnSpentUtxo u = new UnSpentUtxo();
        u.setAddress("");
        u.setHash("");
        u.setHeight(1413239);
        u.setScript("");
        u.setTxN(1);
        u.setValue(100000);

        UnSpentUtxo u1 = new UnSpentUtxo();
        u1.setAddress("");
        u1.setHash("");
        u1.setHeight(1413334);
        u1.setScript("");
        u1.setTxN(1);
        u1.setValue(400000);
        us.add(u);
        us.add(u1);

        System.out.println(JSON.toJSONString(us));
        String c = signTransaction("", "", "", 400000, 10000, us);
        System.out.println(c);
    }
}
