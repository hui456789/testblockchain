package umicash.AVAX;

import java.util.List;

public abstract class ColdWallet extends Wallet {

    protected final List<BIP44Address> addresses;

    protected ColdWallet(List<BIP44Address> addresses) {
        this.addresses = addresses;
    }
}