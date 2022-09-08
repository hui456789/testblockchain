package umicash.coin.walletUtil;

import com.google.common.collect.ImmutableList;


import com.sun.javafx.robot.FXRobotImage;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bouncycastle.util.encoders.Hex;
import org.spongycastle.crypto.digests.SHA3Digest;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.MnemonicUtils;
import umicash.params.LitecoinParams;

import javax.lang.model.SourceVersion;
import java.util.Arrays;
import java.util.List;

import static umicash.coin.addurl.AddressUtil.*;

public class flowTest {
    private final static ImmutableList<ChildNumber> BIP44_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(397, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    private static final MainNetParams mainnetParams = MainNetParams.get();

    public static void createAddress() {
        String mnemonic1 = "curve devote";
        String[] mm = mnemonic1.split(" ");
        List<String> mnemonic = Arrays.asList(mm.clone());
        byte[] seed = MnemonicCode.toSeed(mnemonic, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministic = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey accountKey0 = deterministic.get(BIP44_ACCOUNT_ZERO_PATH, true, true);
        byte[] sha256Bytes = Sha256Hash.hash(Hex.decode(accountKey0.getPrivateKeyAsHex()));
        //2.将第1步结果进行RIPEMD160哈希
        SHA3Digest digest = new SHA3Digest();
        digest.update(sha256Bytes, 0, sha256Bytes.length);
        byte[] ripemd160Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(ripemd160Bytes, 0);
        String result = Hex.toHexString(ripemd160Bytes);
        byte[] firstHash = Sha256Hash.hash(Hex.decode(result));
        byte[] doubleHash = Sha256Hash.hash(firstHash);
        String checkStr = Hex.toHexString(doubleHash).substring(0,22);
        String address = Base58.encode(Hex.decode(checkStr));
        System.out.println("s = " + address );

    }
    public static void main(String[] args) {
        createAddress();
    }

    public static class TestBip {

        private static final byte[] SEED = null;
        private static final String PASSPHRASE = "";
        private static final Long CREATIONTIMESECONDS = 0L;
        private static final NetworkParameters mainnetParams =MainNetParams.get();
        private static final TestNet3Params mainnetParams2 = new TestNet3Params();
        private static final  NetworkParameters  mainnetParams1 = LitecoinParams.get();
        private static final String  wordList = "wash reduce";
        public static void TestBip44ETH() throws Exception {
            String s = "";
            DeterministicKey deterministicKey = DeterministicKey.deserializeB58(s, mainnetParams);
            System.out.println("deterministicKey = " + deterministicKey);

            DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(deterministicKey);
            List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");
            DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
            DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 1);
            String path = "m/44'/60'/0'/0/";
             for (int i = 0; i < 20; i++) {
            String ethAddress = getEthAddress(i,childKey0.serializePrivB58(mainnetParams) );
               System.out.println("path:  " +path+i+"\t"+"address:" + ethAddress);
            }
        }
        public static void TestBip44BTC() throws Exception {
            DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, SEED, PASSPHRASE, CREATIONTIMESECONDS);
            // 生成根私钥 root private key
            DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
            DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
            List<ChildNumber> parsePath = HDUtils.parsePath("44H/0H/0H");
            DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
            DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 1);
            String path = "m/44'/0'/0'/1/";
            for (int i = 0; i <20 ; i++) {
            System.out.println("path:  " +path+i+"\t"+"address:" + getBtcAddress(i,childKey0.serializePrivB58(mainnetParams)));
          }

        }
        public static void TestBip44TRX() throws Exception {
            DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, SEED, PASSPHRASE, CREATIONTIMESECONDS);
            // 生成根私钥 root private key
            DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
            DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
            List<ChildNumber> parsePath = HDUtils.parsePath("44H/0H/0H");
            DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
            DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
            String str = "";
          String ph = "m/44'/195'/0'/1/";
          for (int i = 0; i < 20; i++) {
            String LtcAddress = getTRXAddress(i, str);

            System.out.println("path ：" + ph+i + "\t" + "address:" +LtcAddress);
          }

        }



        public static void main(String[] args) throws Exception {
            /*
             * d9b71368b29da0f97a8050d0bf77c2fe808d77baba174da476523ee12f4ece50
             * */
          TestBip44ETH();
        }
    }

    public static class test {

        private final static ImmutableList<ChildNumber> BIP44_ZERO_PATH =
                ImmutableList.of(new ChildNumber(44, true), new ChildNumber(394, true),
                        ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        public static void createAddress() throws Exception {
            String mnemonic = "betray curve";
            System.out.println(mnemonic);
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");
            byte[] s = Hex.encode(seed);
            //System.out.println(s);
            DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
            DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
            DeterministicKey deterministicKey = deterministicHierarchy
                    .deriveChild(BIP44_ZERO_PATH, false, true, new ChildNumber(0));

            byte[] privKeyBytes = deterministicKey.getPrivKeyBytes();
            ECKeyPair keyPair = ECKeyPair.create(privKeyBytes);
        }

        public static void main(String[] args) {
            try {
                createAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}