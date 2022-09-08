package umicash.utils;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Hash;
import umicash.AVAX.AvalancheWallet;
import umicash.AVAX.BIP44Address;
import umicash.AVAX.WalletGenerator;

import java.util.ArrayList;
import java.util.List;

import static umicash.AVAX.Constants.HARDENED;


public class  AvalancheUtil extends WalletGenerator {
    private static final String CHAIN_CODE = "X";
    private static final String CHAIN_DELIMITER = "-";
    private static final String BECH32_HRP = "avax";
    private static final int ADDRESS_LENGTH = 45;

    private  Bip32ECKeyPair masterKeyPair;


    /*public AvalancheUtil(byte[] seed) {
        this.masterKeyPair = Bip32ECKeyPair.generateKeyPair(seed);
    }

    public  BIP44Address generateAddress(int index) {
        int[] addressPath = getAddressPath(index);
        Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeyPair, addressPath);
        StringBuilder addressBuilder = new StringBuilder(ADDRESS_LENGTH);
        addressBuilder.append(CHAIN_CODE);
        addressBuilder.append(CHAIN_DELIMITER);
        addressBuilder.append(Bech32Util.encode(BECH32_HRP, to5BitBytesSafe(Hash.sha256hash160(derivedKeyPair.getPublicKeyPoint().getEncoded(true)))));
        String address = addressBuilder.toString();
        return  new BIP44Address(address, addressPath);
    }*/
    public  String generateAddress(int index , byte[] seed) {
        int[] addressPath = getAddressPath(index);
        Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(Bip32ECKeyPair.generateKeyPair(seed), addressPath);
        StringBuilder addressBuilder = new StringBuilder(ADDRESS_LENGTH);
        addressBuilder.append(CHAIN_CODE);
        addressBuilder.append(CHAIN_DELIMITER);
        addressBuilder.append(Bech32Util.encode(BECH32_HRP, to5BitBytesSafe(Hash.sha256hash160(derivedKeyPair.getPublicKeyPoint().getEncoded(true)))));
        return  addressBuilder.toString();
    }

    public  String Avaxadd(byte[] seed) {
        Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.generateKeyPair(seed);
        StringBuilder addressBuilder = new StringBuilder(ADDRESS_LENGTH);
        addressBuilder.append(CHAIN_CODE);
        addressBuilder.append(CHAIN_DELIMITER);
        addressBuilder.append(Bech32Util.encode(BECH32_HRP, to5BitBytesSafe(Hash.sha256hash160(derivedKeyPair.getPublicKeyPoint().getEncoded(true)))));
        return addressBuilder.toString();
    }

    private static int[] getAddressPath(int index) {
        int purpose = AvalancheWallet.PURPOSE | HARDENED;
        int coinCode = AvalancheWallet.COIN.getCode() | HARDENED;
        int account = HARDENED; // 0 | HARDENED
        return new int[] {purpose, coinCode, account, 0, index};
    }

    @Override
    protected void logWarning(String field, int val) {
        logWarning(field, AvalancheWallet.COIN, val);
    }

    @Override
    protected void logMissing(String field) {
        logMissing(field, AvalancheWallet.COIN);
    }

    @Override
    public AvalancheWallet generateWallet(Integer account, Integer change, Integer index, int numAddresses) {
        if (account != null) {
            logWarning(ACCOUNT, account);
        }
        if (change != null) {
            logWarning(CHANGE, change);
        }
        if (index == null) {
            logMissing(INDEX);
            index = DEFAULT_FIELD_VAL;
        }

        List<BIP44Address> addresses = new ArrayList<>(numAddresses);

        for(int i = index; i < (index + numAddresses); ++i) {
            //addresses.add(generateAddress(i));

        }

        return new AvalancheWallet(addresses);
    }

    @Override
    public AvalancheWallet generateDefaultWallet() {

        List<BIP44Address> wrapper = new ArrayList<>(1);
        //wrapper.add(generateAddress(DEFAULT_FIELD_VAL));

        return new AvalancheWallet(wrapper);
    }

    public static byte[] to5BitBytesSafe(byte[] input) {
        byte[] rawBytes;
        byte[] result;
        int val = input.length % 5;

        if (val == 0) {
            rawBytes = new byte[input.length];
        } else {
            rawBytes = new byte[input.length + (5 - val)];
        }
        result = new byte[(rawBytes.length / 5) * 8];
        System.arraycopy(input, 0, rawBytes, 0, input.length);

        for (int i = 0, j = 0; i < rawBytes.length; i += 5, j += 8) {
            result[j] = (byte)((rawBytes[i] >> 3) & 0x1f);
            result[j + 1] = (byte)(((rawBytes[i] & 0x07) << 2) + ((rawBytes[i + 1] >> 6) & 0x03));
            result[j + 2] = (byte)(((rawBytes[i + 1] & 0x3e) >> 1) & 0x1f);
            result[j + 3] = (byte)(((rawBytes[i + 1] & 0x01) << 4) + ((rawBytes[i + 2] >> 4) & 0x0f));
            result[j + 4] = (byte)(((rawBytes[i + 2] & 0x0f) << 1) + ((rawBytes[i + 3] >> 7) & 0x01));
            result[j + 5] = (byte)(((rawBytes[i + 3] & 0x7c) >> 2) & 0x1f);
            result[j + 6] = (byte)(((rawBytes[i + 3] & 0x03) << 3) + ((rawBytes[i + 4] >> 5) & 0x07));
            result[j + 7] = (byte)(rawBytes[i + 4] & 0x1f);
        }
        return result;
    }
}
