package umicash.account;


import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;
import umicash.bean.WalletBean;
import umicash.enums.Coin;
import umicash.params.BitcoinCashParams;
import umicash.params.DashParams;
import umicash.params.DogeParams;
import umicash.params.LitecoinParams;
import umicash.utils.ECKeyUtil;


public class AddressUtil1 {
    public static WalletBean generateAddress(String mnemonic, String path , Coin coin) throws UnreadableWalletException {
        ECKeyPair keyPair = ECKeyUtil.generateKey(mnemonic, path);
        ECKey ecKey = ECKey.fromPrivate(keyPair.getPublicKey());
        WalletBean wallet = new WalletBean();
        wallet.setCoin_type(coin.getCoin());
        NetworkParameters parameters = null;
        switch (coin) {
            case BTC:
                parameters = MainNetParams.get();
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case BCH:
                parameters = MainNetParams.get();
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case BSV:
                parameters = MainNetParams.get();
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                break;
            case LTC:
                parameters = LitecoinParams.get();
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case DOGE:
                parameters = DogeParams.get();
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case DASH:
                parameters = DashParams.get();
                wallet.setAddress(ecKey.toAddress(parameters).toBase58());
                wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(parameters));
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case ETH:
            case ETC:
            case HT:
            case PI:
            case WAN:
            case BNB:
            case EM:
                String address = Keys.getAddress(keyPair);
                if (coin == Coin.EM) {
                    address = "EM" + address;
                } else {
                    address = "0x" + address;
                }
                wallet.setAddress(address);
                String privateKey = Numeric.toHexStringNoPrefixZeroPadded(keyPair.getPrivateKey(), Keys.PRIVATE_KEY_LENGTH_IN_HEX);
                wallet.setPrivateKey(privateKey);
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case NEO:
                io.neow3j.crypto.ECKeyPair ecKeyPair = io.neow3j.crypto.ECKeyPair.create(keyPair.getPrivateKey());
                wallet.setAddress(ecKeyPair.getAddress());
                wallet.setPrivateKey(ecKeyPair.exportAsWIF());
                break;
            case TRX:
                break;
            case FIL:
                break;
            case AVAX:
                wallet.setPrivateKey(ecKey.getPrivateKeyAsHex());
                wallet.setKeystore(ecKey.getPublicKeyAsHex());
                break;
            case SOL:
            case DOT:
                break;
            default:
                break;
        }
        wallet.setMnemonic(mnemonic);
        return wallet;
    }

    private static final String wordList = "copy";

    public static void main(String[] args) throws UnreadableWalletException {
        for (int i = 0; i < 2; i++) {
            String aa = Coin.ETH.getPath()+i;
            WalletBean walletBean = generateAddress(wordList , aa ,  Coin.ETH);
            walletBean.setPath(aa);
            System.out.println(walletBean);
        }

    }

   /* public static void main(String[] args) throws UnreadableWalletException {
        byte[] seedBytes = new DeterministicSeed(wordList, null, "", 0l).getSeedBytes();
        WalletBean walletBean = new WalletBean();
        for (int i = 0; i < 20; i++) {
            String aa = Coin.AVAX.getPath()+i;
            String avaxadd = new AvalancheUtil().generateAddress(i , seedBytes);
            walletBean.setPath(aa);
            walletBean.setAddress(avaxadd);
            walletBean.setMnemonic(wordList);
            System.out.println(walletBean);
        }
    }*/
}
