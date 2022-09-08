package umicash.AVAX;

import umicash.enums.Coins;

import java.util.List;


public class AvalancheWallet extends ColdWallet {

    public static final Coins COIN = Coins.AVAX;
    public static final int PURPOSE = 44;

    public AvalancheWallet(List<BIP44Address> derivedAddresses) {
        super(derivedAddresses);
    }

    @Override
    public String identifier() {
        return COIN.toString();
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        // append coin name
        result.append(COIN);
        result.append(':');

        // append addresses
        for (BIP44Address address : addresses) {
            result.append('\n');
            result.append(address.toString());
        }

        return result.toString();
    }
}