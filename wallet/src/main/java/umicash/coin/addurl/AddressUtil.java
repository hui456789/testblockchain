package umicash.coin.addurl;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.util.encoders.Hex;
import org.tron.TronWalletApi;
import org.tron.wallet.util.ByteArray;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;
import umicash.params.LitecoinParams;

public class AddressUtil {
    private static final String C_BLANK1 = " ";
    private static final String PREFIX = "0x";
    private static final byte[] SEED = null;
    private static final String PASSPHRASE = "";
    private static final Long CREATIONTIMESECONDS = 0L;
    private static final NetworkParameters mainnetParams = MainNetParams.get();
    private static final TestNet3Params mainnetParams2 = new TestNet3Params();
    private static final  NetworkParameters  mainnetParams1 = LitecoinParams.get();

    // BTC DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey
    public static String getBtcAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        String privateKeyAsHex = childDK.getPrivateKeyAsHex();
        return childDK.toAddress(mainnetParams).toBase58();
    }
    //ETH
    public static String getEthAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        //System.out.println(childDK.getPublicKeyAsHex());
        ECKey uncompressedChildKey = childDK.decompress();
        //以太坊需要把前缀去掉（0x04前缀表示未压缩）
        //System.out.println("pv key:" + uncompressedChildKey.getPrivateKeyAsHex() );
        return Keys.toChecksumAddress(Keys.getAddress(uncompressedChildKey.getPublicKeyAsHex().substring(2)));
    }
    // TRX
    public static String getTRXAddress(int addressIndex, String ext_key) throws IllegalAccessException {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        byte[] privKeyBytes = childDK.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(privKeyBytes);
        String address ="41" + Keys.getAddress(keyPair);
        return TronWalletApi.encode58Check(ByteArray.fromHexString(address));
    }
    //LTC
    public static String getLtcAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        String publicKeyAsHex = childDK.getPublicKeyAsHex();
        ECKey decompress = childDK.decompress();
        System.out.println("public:"+ publicKeyAsHex);
        String s = decompress.toAddress(mainnetParams).toBase58();
        String privateKey = decompress.getPrivateKeyAsWiF(mainnetParams);
        System.out.println("public:"+ privateKey);
        return s;
    }
    //DOGE
    public static String getDogeAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams1);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        String privateKeyAsHex = childDK.getPrivateKeyAsHex();
        String publicKeyAsHex = childDK.getPublicKeyAsHex();
        System.out.println("public:"+ publicKeyAsHex);
        byte[] sha256Bytes = Sha256Hash.hash(Hex.decode(privateKeyAsHex));
        //2.将第1步结果进行RIPEMD160哈希
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(sha256Bytes, 0, sha256Bytes.length);
        byte[] ripemd160Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(ripemd160Bytes, 0);
        //3.将BTC地址版本号(00)加在第2步结果签名
        String result = "1e" + Hex.toHexString(ripemd160Bytes);
        //4.将第3步结果进行双SHA256哈希
        byte[] firstHash = Sha256Hash.hash(Hex.decode(result));
        byte[] doubleHash = Sha256Hash.hash(firstHash);
        //5.取第4步结果的前4字节，并加在第3步结果后面
        String checkStr =result + Hex.toHexString(doubleHash).substring(0, 8);
        String address = Base58.encode(Hex.decode(checkStr));
        //System.out.println(address);
        return address;
    }



    public static String getBnbAddress(int addressIndex, String ext_key) {
        DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
        DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
        String privateKeyAsHex = childDK.getPrivateKeyAsHex();
        String publicKeyAsHex = childDK.getPublicKeyAsHex();
        ECKey decompress = childDK.decompress();
        KeyCrypter keyCrypter = decompress.getKeyCrypter();
        System.out.println("public:"+ publicKeyAsHex);
        String s = decompress.toAddress(mainnetParams).toBase58();
        String privateKey = decompress.getPrivateKeyAsWiF(mainnetParams);
        System.out.println("public:"+ privateKey);
        return s;
    }
}
