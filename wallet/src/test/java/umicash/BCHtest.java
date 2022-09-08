/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package umicash;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.tron.TronWalletApi;
import org.tron.wallet.util.ByteArray;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.util.List;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/17
 * @since 1.0
 */
public class BCHtest {
  private static final NetworkParameters mainnetParams = MainNetParams.get();
  public static void main(String[] args){
    String s = "";
    DeterministicKey deterministicKey = DeterministicKey.deserializeB58(s, mainnetParams);
    DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(deterministicKey);
    List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");
    DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
    DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
    String path = "m/44'/60'/0'/" + 0;
    String trxAddress = getEthAddress(0, childKey0.serializePrivB58(mainnetParams));
    System.out.println("trxAddress = " + trxAddress);
  }
  public static String getTRXAddress(int addressIndex, String ext_key){
    DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
    DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
    byte[] privKeyBytes = childDK.getPrivKeyBytes();
    ECKeyPair keyPair = ECKeyPair.create(privKeyBytes);
    String address ="41" + Keys.getAddress(keyPair);
    return TronWalletApi.encode58Check(ByteArray.fromHexString(address));
  }
  public static String getEthAddress(int addressIndex, String ext_key) {
    DeterministicKey parentDK = DeterministicKey.deserializeB58(ext_key, mainnetParams);
    DeterministicKey childDK = HDKeyDerivation.deriveChildKey(parentDK, addressIndex);
    ECKey uncompressedChildKey = childDK.decompress();
    return Keys.toChecksumAddress(Keys.getAddress(uncompressedChildKey.getPublicKeyAsHex().substring(2)));
  }
}
