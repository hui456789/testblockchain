package umicash.AVAX;

public class test {

    public static String test(int obj) {
        if (obj==1 || obj == 2 || obj == 3){
            return "测试一下" ;
        }
       return "";
    }

    public static void main(String[] args) {
        String test = test(3);
        System.out.println(test);
    }
}
