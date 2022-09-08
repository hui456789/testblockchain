package umicash.coin.walletUtil;


import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.HexUtil;
import org.web3j.utils.Numeric;
import ove.crypto.digest.Blake2b;

public class walletUtil {
    /*
    * filecoin地址計算方法
    * */
    public static String filecoinPubutil(String pubKeyAsHex) {
        Blake2b.Digest blake2b1 = Blake2b.Digest.newInstance(20);
        byte[] bytes = Numeric.hexStringToByteArray(pubKeyAsHex);
        byte[] black2HashByte = blake2b1.digest(bytes);
        String black2HashStr = Numeric.toHexStringNoPrefix(black2HashByte);
        //将得到的blake2哈希值前添加0x01
        String black2HashSecond = "01"+black2HashStr;
        //用blake2b算法计算4位校验和
        Blake2b.Digest blake2b2 = Blake2b.Digest.newInstance(4);
        byte[] checksumBytes = blake2b2.digest(Numeric.hexStringToByteArray(black2HashSecond));
        byte[] addressBytes = new byte[black2HashByte.length + checksumBytes.length];
        System.arraycopy(black2HashByte, 0, addressBytes, 0, black2HashByte.length);
        System.arraycopy(checksumBytes, 0, addressBytes, black2HashByte.length,checksumBytes.length);
        //f 正式 t 测试 1 钱包 2 合约
        return "f1"+ Base32.encode(addressBytes);
    }

    public static String filecoinPubutil1(byte[] pub) {
        Blake2b.Digest digest = Blake2b.Digest.newInstance(20);
        String hash = HexUtil.encodeHexStr(digest.digest(pub));
        //将得到的blake2哈希值前添加0x01
        String pubKeyHash = "01" + HexUtil.encodeHexStr(digest.digest(pub));
        //用blake2b算法计算4位校验和
        Blake2b.Digest blake2b3 = Blake2b.Digest.newInstance(4);
        String checksum = HexUtil.encodeHexStr(blake2b3.digest(HexUtil.decodeHex(pubKeyHash)));
        //Base32编码
        return "f1" + Base32.encode(HexUtil.decodeHex(hash + checksum)).toLowerCase();
    }

}
