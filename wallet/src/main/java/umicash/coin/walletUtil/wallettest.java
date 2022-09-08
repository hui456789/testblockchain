package umicash.coin.walletUtil;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.MnemonicUtils;
import sun.security.provider.SecureRandom;

import java.util.List;

public class wallettest {
    /**
     * path路径
     */
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    private static final MainNetParams mainnetParams = MainNetParams.get();
    /**
     * 创建钱包
     */
    private static void createWallet()   {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.engineNextBytes(entropy);

        //生成12位助记词
        String string ="achine";
        //List<String> mnemonic = Arrays.asList(string.split(","));
        byte[] seed = MnemonicUtils.generateSeed(string, "");
        //使用助记词生成钱包种子
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        //通过公钥生成钱包地址
        String address = Keys.getAddress(keyPair.getPublicKey());

        System.out.println();
        System.out.println("助记词：");
        System.out.println(string);
        System.out.println();
        System.out.println("地址：");
        System.out.println("0x"+address);
        System.out.println();
        System.out.println("私钥：");
        System.out.println("0x"+keyPair.getPrivateKey().toString(16));
        System.out.println();
        System.out.println("公钥：");
        System.out.println("0x"+ keyPair.getPublicKey().toString(16));
    }

    public static void main(String[] args) throws Exception {
        //创建钱包
        createWallet();
    }
}
