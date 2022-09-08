/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package MetamaskWeb3js;

import com.aliyun.oss.common.comm.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/7/14
 * @since 1.0
 */
public class Controller {
  @RequestMapping(value = "/oauth/metamask", method = RequestMethod.POST)
  @ResponseBody
  public ResponseMessage login(@RequestBody HashMap requestObject){
    final String publicAddress = (String) requestObject.get("publicAddress");
    final String signature = (String) requestObject.get("signature");
    final String message = (String) requestObject.get("message");
    return null;
  }
}
