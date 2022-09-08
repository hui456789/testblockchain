package umicash.utils;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.*;
import org.tron.TronWalletApi;
import org.tron.wallet.util.ByteArray;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.util.Arrays;

public class testtrx {

    /**
     * path路径
     */
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(195, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    public static void newAddress() {
        String wordList = "acoustic robot web analyst";
        byte[] seed = MnemonicCode.toSeed(Arrays.asList(wordList.split(" ")), "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(1));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        //通过公钥生成钱包地址
        String address ="41" + Keys.getAddress(keyPair);
        String pvk = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
        String pub = Numeric.toHexStringNoPrefix(keyPair.getPublicKey());
        String s = TronWalletApi.encode58Check(ByteArray.fromHexString(address));
        System.out.println("pub = " + pub);
        System.out.println("pvk = " + pvk);
        System.out.println("s = " + s);
    }

    /**
     * 41 ---- > T
     *
     * @param address
     * @return
     */
    public static String fromHexAddress(String address) {
        return TronWalletApi.encode58Check(ByteArray.fromHexString(address));
    }
    /**
     * T ---->  41
     *
     * @param address
     * @return
     */
    public static String toHexAddress(String address) {
        return ByteArray.toHexString(TronWalletApi.decodeFromBase58Check(address));
    }

    public static void main(String[] args) {
        newAddress();
    }
}
