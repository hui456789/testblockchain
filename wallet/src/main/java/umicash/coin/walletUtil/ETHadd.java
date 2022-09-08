package umicash.coin.walletUtil;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.MnemonicUtils;
import sun.security.provider.SecureRandom;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ETHadd {
    /**
     * path路径
     * BTC使用m/44’/0’/0’/0的 Extended Public Key 生成 m/44’/0’/0’/0/*
     * ETH使用m/44’/60’/0’/0的 Extended Public Key 生成 m/44’/60’/0’/0/*
     * m/44'/0'/60'/0/0	18xFnje4kXPqJgsTmjTTDkzUExVsmdNkPp	02395d9478c28798ad9e863eb9bdaecc701ca9c5a1a4cfc96b4ed94c50672b82ec	L3K3LaYrbMDJEgqMGwpKFoZKydesCajPA7sNYDdt6aPNCFzqwaVf
     3wVr2gfTMsLc1P3EBSjU2qrfHmBCtiuDjUVZzqQkq97MvcW
     */
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    /**
     * 测试链可以用TestNet3Params
     */
    private static final MainNetParams mainnetParams = new MainNetParams();
    //private static final TestNet3Params mainnetParams = new TestNet3Params();
    /**
     * 创建账号生成钱包
     */
    private static String createWallet(byte[] seed){
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        //通过公钥生成钱包地址
        String address = Keys.getAddress(keyPair.getPublicKey());
        System.out.println();
        System.out.println("地址：");
        System.out.println("0x"+address);
        System.out.println();
        System.out.println("私钥：");
        System.out.println("0x"+keyPair.getPrivateKey().toString(16));
        System.out.println();
        System.out.println("公钥：");
        System.out.println(keyPair.getPublicKey().toString(16));
        return "私钥："+keyPair.getPrivateKey().toString(16) + "公钥："+keyPair.getPublicKey().toString(16);
    }
    /**
     * 生成地址主要依赖Extended Public Key，加上addressIndex(0至232-1)就可以确定一个地址.
     * 生成多地址，官方建议一次成成最多20个
     */
    /*private static String getEthAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        ECKey uncompressedChildKey = childDK.decompress();
        //以太坊需要把前缀去掉（0x04前缀表示未压缩）
        String hexK = uncompressedChildKey.getPublicKeyAsHex().substring(2);
        String addr = Keys.getAddress(hexK);
        return Keys.toChecksumAddress(addr);
    }*/
    private static String getEthAddress(int addressIndex, String ext_key){
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        System.out.println("public key:" +"0x"+childDK.getPublicKeyAsHex());
        System.out.println("pv key:" +"0x"+ childDK.getPrivateKeyAsHex());
        ECKey uncompressedChildKey = childDK.decompress();
        //以太坊需要把前缀去掉（0x04前缀表示未压缩）
        String hexK = uncompressedChildKey.getPublicKeyAsHex().substring(2);
        String addr = Keys.getAddress(hexK);
        return Keys.toChecksumAddress(addr);
    }
    /**
     * 生成 Extended Public Key (扩展公钥)
     *  扩展公钥以xpub做前缀
     *  扩展公钥不等同于公钥，扩展公钥主要包含了3个信息：
     *  1. 区块链网络（mainnet 或 testnet）
     *  2. 公钥
     *  3. chain code
     * */
    private static String getExstatictendedPublicKey(DeterministicHierarchy deterministicHierarchy) {
        //DeterministicKey deterministicKey = deterministicHierarchy.deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");

        DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
        DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
        return childKey0.serializePubB58(mainnetParams);
    };
    /**
     * 生成 Extended Private Key (扩展私钥)
     *  扩展公钥以xpub做前缀
     * */
    private static String getExtendedPrivateKey(DeterministicHierarchy deterministicHierarchy) {
        //DeterministicKey deterministicKey = deterministicHierarchy.deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));

        List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");

        DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
        DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
        return childKey0.serializePrivB58(mainnetParams);
    };
    /**
     * 生成12位助记词
     * */
    private static List<String> getMnemonic() throws MnemonicException.MnemonicLengthException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.engineNextBytes(entropy);
        return MnemonicCode.INSTANCE.toMnemonic(entropy);
    }
    /**
     * 生成seed
     * */
    private static byte[] getSeed(List<String> mnemonic) {
        //使用助记词生成钱包种子
        byte[] seed = MnemonicCode.toSeed(mnemonic, "");
        return seed;
    }
    /**
     * 生成公钥
     * */
    private static String getPublickey(List<String> mnemonic) {

        return "";
    }
    /**
     * 生成私钥
     * */
    private static String getPrivatekey(List<String> mnemonic) {

        return "";
    }
    public static void main(String[] args) throws Exception {
        String filepast = "D:\\fj\\texta.text";
        String string ="宜 夜";
        List<String> mnemonic = Arrays.asList(string.split(","));
        //获取Seed
        byte[] seed = getSeed(mnemonic);
        System.out.println("seed = " + seed);
        //创建钱包
        String wallet = createWallet(seed);
        byte[] seed1 = wallet.getBytes();
        //生成扩展公钥
        String exstatictendedPublicKey = getExtendedPrivateKey(new DeterministicHierarchy(HDKeyDerivation.createMasterPrivateKey(seed)));
        System.out.println("公钥：" + exstatictendedPublicKey);
        //生成动态地址
         for (int i = 0; i <20 ; i++) {
            String ethAddress = getEthAddress(i, exstatictendedPublicKey );
                    System.out.println(ethAddress);
        }
    }
}
