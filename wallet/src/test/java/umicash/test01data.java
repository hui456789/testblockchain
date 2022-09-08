/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package umicash;

import java.util.*;
/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/6/13
 * @since 1.0
 */
public class test01data {
  public static Object getObj() {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("ab");
    return list;
  }
  public static void main(String[] args) {
    Object obj = getObj();
    List<String> strings = castList(obj, String.class);
    System.out.println("strings = " + strings);
    for (String string : strings) {
      System.out.println("string = " + string);
    }
  }
  public static <T> List<T> castList(Object obj, Class<T> clazz)
  {
    List<T> result = new ArrayList<T>();
    if(obj instanceof List<?>)
    {
      for (Object o : (List<?>) obj)
      {
        result.add(clazz.cast(o));
      }
      return result;
    }
    return null;
  }
}
