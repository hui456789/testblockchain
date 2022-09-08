package umicash.utils;

import org.bitcoinj.crypto.*;

import java.util.Arrays;
import java.util.List;

public class soltest {
    public static void fromMnemonic(List<String> words, String passphrase) {
        byte[] seed = MnemonicCode.toSeed(words, passphrase);
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey child = deterministicHierarchy.get(HDUtils.parsePath("M/44H/501H/0H/0H"), true, true);

        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(child.getPrivKeyBytes());

        System.out.println("keyPair = " + keyPair);
    }

    public static void main(String[] args) {
        String wordList = "web analyst";
        List<String> words = Arrays.asList(wordList.split(" "));
        fromMnemonic(words,"");
    }
}
