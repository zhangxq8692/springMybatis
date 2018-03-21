package testCase;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:20
 * @description:
 */
public class TestCase {
    @Test
    public void test(){
      /* Scanner sc = new Scanner(System.in);
        CODE:
       for (int i=0;i<100;i++) {
           System.out.println("i="+i);
           *//*int tem = sc.nextInt();*//*
           if (i > 10) {
               continue CODE;
           }
       }
        CODE:{
            System.out.println("123");
        }
        System.out.println("over");*/
        int i=1;
        i+=i++;
        System.out.println(i);
    }
}
