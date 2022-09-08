package umicash.coin.params;


import org.bitcoinj.params.AbstractBitcoinNetParams;

/**
 * LIT 地址
 */

public class LitecoinParams extends AbstractBitcoinNetParams {
    public LitecoinParams() {
        super();
        p2shHeader = 5;
        dumpedPrivateKeyHeader = 176;
        addressHeader = 48;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader, 50};
        id = "org.litecoin.production";
    }

    private static LitecoinParams instance;
    public static synchronized LitecoinParams get() {
        if (instance == null) {
            instance = new LitecoinParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return "main";
    }

}
