package umicash.utils;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.ECKeyPair;
import umicash.enums.Coin;

public class ECKeyUtil {

    public static  ECKeyPair generateEcKey(String mnemonic, Coin coin) throws UnreadableWalletException {
        DeterministicSeed seed = getDeterministicSeed(mnemonic);
      return  getEcKeyPairByDeterministicSeed(coin.getPath(),seed);
    }

    public static  ECKeyPair generateKey(String mnemonic, String path) throws UnreadableWalletException {
        DeterministicSeed seed = getDeterministicSeed(mnemonic);
        return  getEcKeyPairByDeterministicSeed(path,seed);
    }
   private static DeterministicSeed getDeterministicSeed(String mnemonic) throws UnreadableWalletException {
            long creationTimeSeconds = System.currentTimeMillis();
            return new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);
    }

    public static ECKeyPair getEcKeyPairByDeterministicSeed(String path, DeterministicSeed ds) {
        String [] pathArray = path.split("/");
        byte[] seedBytes = ds.getSeedBytes();
        if (seedBytes == null)
          return  null;
        DeterministicKey dkKey = HDKeyDerivation.createMasterPrivateKey(seedBytes);
        ChildNumber childNumber;
        for (int i = 1; i < pathArray.length; i++) {
            if (pathArray[i].endsWith("'")) {
                int number = Integer.parseInt(pathArray[i].substring(0, pathArray[i].length() - 1));
                childNumber = new ChildNumber(number, true);
            } else {
                int number = Integer.parseInt(pathArray[i]);
                childNumber = new ChildNumber(number, false);
            }
            dkKey = HDKeyDerivation.deriveChildKey(dkKey, childNumber);
        }
      return ECKeyPair.create(dkKey.getPrivKeyBytes());
    }


}
