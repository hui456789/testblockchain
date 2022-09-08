package umicash.coin.walletUtil;

import cn.hutool.core.util.HexUtil;
import com.google.common.collect.ImmutableList;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.*;


import java.text.SimpleDateFormat;
import java.util.Date;

import static umicash.coin.walletUtil.walletUtil.filecoinPubutil;
import static umicash.coin.walletUtil.walletUtil.filecoinPubutil1;

public class test {
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(461, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    private static final MainNetParams mainnetParams = new MainNetParams();
    private static final String  wordList = "harsh over wash reduce";

    private static String createWallet() throws UnreadableWalletException, CipherException {
        DeterministicSeed seed = new DeterministicSeed(wordList, null, "", 0L);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed.getSeedBytes());
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        ECKey ecKey = ECKey.fromPrivate(keyPair.getPrivateKey(),false);

        String s = filecoinPubutil(ecKey.getPublicKeyAsHex());
        return s;
    }

    private static String cWallet() throws UnreadableWalletException {
        DeterministicSeed seed = new DeterministicSeed(wordList, null, "", 0L);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed.getSeedBytes());
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        ECKey ecKey = ECKey.fromPrivate(keyPair.getPrivateKey(),false);
        String filAddress = filecoinPubutil1(ecKey.getPubKey());
        String publicKeyAsHex = ecKey.getPublicKeyAsHex();
        String privateKey = HexUtil.encodeHexStr(ecKey.getPrivKeyBytes());
        return filAddress + "\t" + privateKey + "\t" + publicKeyAsHex;
    }
    public static void main(String[] args) throws UnreadableWalletException, CipherException {
      Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以随意定义得到想要的时间格式
      String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
      System.out.println("sd = " + sd);
    }
}
