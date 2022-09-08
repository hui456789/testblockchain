package umicash;

import org.web3j.abi.TypeDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class testtrx {

    public static void decodeInput() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String inputData = "";
    String method = inputData.substring(0, 10);
    System.out.println(method);
    String to = inputData.substring(10, 74);
    Method refMethod = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
    refMethod.setAccessible(true);
    Address address = (Address) refMethod.invoke(null, to, 0, Address.class);
    System.out.println(address.toString());
    String to1= inputData.substring(74, 138);
    Method refMethod1 = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
    refMethod1.setAccessible(true);
    Address address1 = (Address) refMethod.invoke(null, to1, 0, Address.class);
    System.out.println(address1.toString());

    String to2= inputData.substring(138, 202);
    Method refMethod2 = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
    refMethod2.setAccessible(true);
    Address address2 = (Address) refMethod.invoke(null, to2, 0, Address.class);
    System.out.println(address2.toString());

    String to3= inputData.substring(202, 266);
    Method refMethod3 = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
    refMethod3.setAccessible(true);
    Address address3 = (Address) refMethod.invoke(null, to3, 0, Address.class);
    System.out.println(address3.toString());

    String value = inputData.substring(266,330);
    Uint256 amount = (Uint256) refMethod.invoke(null, value, 0, Uint256.class);
    System.out.println(amount.getValue());
    String value1 = inputData.substring(330);
    Uint256 amount1 = (Uint256) refMethod.invoke(null, value1, 0, Uint256.class);
    System.out.println(amount1.getValue());
  }

  public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
      int a = 0;
      int b = 0;
      for (int i = 0; i < 5; i++) {
        if ( a - b == 3) {
          System.out.println("i = " + b );
        }
        b  -- ;
    }
  }
}
