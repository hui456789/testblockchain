package umicash.coin.walletUtil;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.web3j.abi.datatypes.Address;

public class bnbTest {

    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(714, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    private static final String  wordList = "harsh over wash reduce";
    private static final byte[]  SEED =  null ;
    private static final String PASSPHRASE = "";
    private static final Long CREATIONTIMESECONDS = 0l;
    private static final NetworkParameters mainnetParams = MainNetParams.get();


    public static void main(String[] args) throws Exception {
        testBIP44BNB();
    }
    public static void testBIP44BNB() throws Exception {
        DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, SEED, PASSPHRASE, CREATIONTIMESECONDS);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
        DeterministicKey accountKey0 = deterministicHierarchy.get(BIP44_ETH_ACCOUNT_ZERO_PATH, true, true);
        DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
        ECKey decompress = childKey0.decompress();
        String privateKeyAsHex = decompress.getPrivateKeyAsHex();
        System.out.println("privateKeyAsHex = "    + privateKeyAsHex );

    }
}
