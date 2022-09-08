/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/4/21
 * @since 1.0
 */
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Ceshi {
  public static void main(String[] args) {
    // 格式化时间
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 获取当前系统时间
    LocalDateTime toDay = LocalDateTime.now();
    /*
     判断当天时间是否大于早上8点
     true: 获取昨天早上8点
     false: 获取当天早上8点
    */
    if(toDay.getHour() < 8){
      toDay = toDay.plusDays(-1);
    }
    //System.out.println("toDay = " + toDay);
    // 获取当天早上8点时间  时间精确到8:00:00
    LocalDateTime startDateTime = LocalDateTime.of(toDay.toLocalDate(), LocalTime.MIN.withHour(8));
    System.out.println("时间 = "+df.format(startDateTime));

  }
}
