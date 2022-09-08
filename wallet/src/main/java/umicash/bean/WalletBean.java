package umicash.bean;

public class WalletBean {
    private String coin_type;
    private String path;
    private String mnemonic;
    private String address;
    private String keystore;
    private String privateKey;

    public String getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAddress(String s) {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getPrivateKey(String privateKeyAsHex) {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }


    @Override
    public String toString() {
        return "{" +
                "coin_type='" + coin_type + '\'' +
                "path='" + path + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", address='" + address + '\'' +
                ", keystore='" + keystore + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
