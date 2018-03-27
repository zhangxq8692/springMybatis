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
        System.out.println(Add(3, 15));
    }
    int Add(int num1, int num2) {
        int sum = 0;
        do {
            sum = num1 ^ num2;//不考虑进位相加
            num2 = (num1 & num2) << 1;//进位
            num1 = sum;
        }while (num2 !=0);//有进位继续相加

        return sum;
    }
}
