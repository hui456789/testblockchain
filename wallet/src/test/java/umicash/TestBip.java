package umicash;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.junit.Test;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: flx
 * @date: 2018/8/28 16:52
 * @description: 生成mnemonic > 生成seed > 生成 Extended Public Key
 * 生成地址主要依赖Extended Public Key，加上addressIndex(0至232-1)就可以确定一个地址.
 * BTC使用m/44’/0’/0’/0的 Extended Public Key 生成 m/44’/0’/0’/0/*，
 * ETH使用m/44’/60’/0’/0的 Extended Public Key 生成 m/44’/60’/0’/0/*,
 * mainnet的Extended Public Key以xpub做前缀
 * 验证网址：https://iancoleman.io/bip39/
 */
@Slf4j
public class TestBip {

    private static final String C_BLANK1 = " ";
    private static final String PREFIX = "0x";
    private static final byte[] SEED = null;
    private static final String PASSPHRASE = "";
    private static final Long CREATIONTIMESECONDS = 0L;
    /**
     * TestNet3Params(公共测试网络)/RegTestParams(私有测试网络)/MainNetParams(生产网络)
     */
    private static final MainNetParams mainnetParams = MainNetParams.get();

    @Test
    public void TestBip44ETH() throws Exception {
        String wordList = this.getWordListString();
        wordList = "sibling bomb you dutch entry timber exhaust seminar inflict month one acoustic robot web analyst";
        DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, SEED, PASSPHRASE, CREATIONTIMESECONDS);
        /**生成根私钥 root private key*/
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
        /**根私钥进行 priB58编码*/
        String priv = rootPrivateKey.serializePrivB58(mainnetParams);
        System.out.println("priv = " + rootPrivateKey.serializePrivB58(mainnetParams));
        /**由根私钥生成HD钱包*/
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
        /**定义父路径*/
        List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");

        DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
        log.info("Account extended private key:{}", accountKey0.serializePrivB58(mainnetParams));
        log.info("Account extended public key:{}", accountKey0.serializePubB58(mainnetParams));

        /**由父路径,派生出第一个子私钥*/
        DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
//        DeterministicKey childKey0 = deterministicHierarchy.deriveChild(parsePath, true, true, new ChildNumber(0));
        log.info("BIP32 extended 0 private key:{}", childKey0.serializePrivB58(mainnetParams));
        log.info("BIP32 extended 0 public key:{}", childKey0.serializePubB58(mainnetParams));
        log.info("0 private key:{}", childKey0.getPrivateKeyAsHex());
        log.info("0 public key:{}", childKey0.getPublicKeyAsHex());
        System.out.println("childKey0.getPrivKeyBytes() = " + childKey0.getPrivKeyBytes());
        ECKeyPair childEcKeyPair0 = ECKeyPair.create(childKey0.getPrivKeyBytes());
        log.info("0 address:{}", PREFIX + Keys.getAddress(childEcKeyPair0));
    }

    @Test
    public void TestBip44BTC() throws Exception {
        String wordList = " wash reduce";
        DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, SEED, PASSPHRASE, CREATIONTIMESECONDS);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
        String priv = rootPrivateKey.serializePrivB58(mainnetParams);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);

        List<ChildNumber> parsePath = HDUtils.parsePath("44H/0H/0H");

        DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
        System.out.println("private key= " + accountKey0.serializePrivB58(mainnetParams));
        System.out.println("public key= " + accountKey0.serializePubB58(mainnetParams));
        /**由父路径,派生出第一个子私钥*/
        DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
        /**由父路径,派生出第二个子私钥*/
        DeterministicKey childKey1 = HDKeyDerivation.deriveChildKey(accountKey0, 1);
    }

    /**
     * 生成12个助记词
     *
     * @return
     * @throws IOException
     * @throws MnemonicException.MnemonicLengthException
     */
    public String getWordListString() throws IOException, MnemonicException.MnemonicLengthException {
        StringBuilder stringBuilder = new StringBuilder();
        getWordList().stream().forEach(word -> {
            stringBuilder.append(word).append(C_BLANK1);
        });
        return stringBuilder.toString().trim();
    }

    /**
     * 生成12个助记词
     *
     * @return
     * @throws IOException
     * @throws MnemonicException.MnemonicLengthException
     */
    public List<String> getWordList() throws IOException, MnemonicException.MnemonicLengthException {
        MnemonicCode mnemonicCode = new MnemonicCode();
        SecureRandom secureRandom = new SecureRandom();
        /**必须是被4整除*/
        byte[] initialEntropy = new byte[16];
        secureRandom.nextBytes(initialEntropy);
        return mnemonicCode.toMnemonic(initialEntropy);
    }

    //ETH
    public static String getEthAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        System.out.println(childDK.getPublicKeyAsHex());
        ECKey uncompressedChildKey = childDK.decompress();
        //以太坊需要把前缀去掉（0x04前缀表示未压缩）
        System.out.println("pv key:" + uncompressedChildKey.getPrivateKeyAsHex() );
        return Keys.toChecksumAddress(Keys.getAddress(uncompressedChildKey.getPublicKeyAsHex().substring(2)));
    }

}