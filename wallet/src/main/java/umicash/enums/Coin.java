package umicash.enums;

public enum Coin {
    BTC("BTC","m/44'/0'/0'/0/"),
    LTC("LTC","m/44'/2'/0'/0/"),
    ETH("ETH","m/44'/60'/0'/0/"),
    ETC("ETC","m/44'/61'/0'/0/"),
    BCH("BCH","m/44'/145'/0'/0/"),
    TRX("TRX","m/44'/195'/0'/0/"),
    DOGE("Doge","m/44'/3'/0'/0/"),
    DASH("Dash","m/44'/5'/0'/0/"),
    DOT("DOT","m/44'/354'/0'/0/"),
    FIL("FIL","m/44'/461'/0'/0/"),
    SOL("SOL","m/44'/501'/0'/0/"),
    BNB("BNB","m/44'/714'/0'/0/"),
    OKT("OKT","m/44'/996'/0'/0/"),
    HT("HT","m/44'/1010'/0'/0/"),
    AVAX("AVAX","m/44'/9000'/0'/0/"),
    LUNA("LUNA","m/44'/330'/0'/0/"),
    FLOW("FLOW","m/44'/539'/0'/0/"),
    NEAR("NEAR","m/44'/397'/0'/0/"),

    BSV("BSV","m/44'/236'/0'/0/"),
    NEO("NEO","m/44'/888'/0'/0/"),
    EM("EM","m/44'/454'/0'/0/"),
    PI("PI","m/44'/314159'/0'/0/"),
    WAN("WAN","m/44'/5718350'/0'/0/");


    private String coin;
    private String path;
    Coin(String coin,String path){this.coin=coin;this.path=path;}

    public String getCoin(){return coin;}

    public String getPath(){return path;}
}
