/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.inputdate;

import java.io.Serializable;
import java.math.BigInteger;

public class Account implements Serializable {
  private String address;
  private BigInteger balance;//wei
  private String privateKey;
  private String publicKey;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public BigInteger getBalance() {
    return balance;
  }

  public void setBalance(BigInteger balance) {
    this.balance = balance;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  public String toString() {
    return "Account{" +
      "address='" + address + '\'' +
      ", balance=" + balance +
      ", privateKey='" + privateKey + '\'' +
      ", publicKey='" + publicKey + '\'' +
      '}';
  }
}