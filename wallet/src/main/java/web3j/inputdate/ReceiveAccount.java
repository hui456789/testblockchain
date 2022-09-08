/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package web3j.inputdate;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/8/16
 * @since 1.0
 */
public class ReceiveAccount extends Account implements Serializable {
  private BigDecimal sendAmount;

  public BigDecimal getSendAmount() {
    return sendAmount;
  }

  public void setSendAmount(BigDecimal sendAmount) {
    this.sendAmount = sendAmount;
  }
}

