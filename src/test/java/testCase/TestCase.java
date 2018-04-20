package testCase;


import com.sun.istack.internal.NotNull;
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
    public void test() {
        int a = 10;
        Integer b = null;
        System.out.println(Add(a, b));
    }

    /**
     * 不用+号运算符处理两个数相加
     *
     * @param one number one
     * @param two number two
     * @return sum
     */
    int Add(@NotNull Integer one, @NotNull Integer two) {
        int sum = 0;
        do {
            sum = one ^ two;                //不考虑进位相加
            two = (one & two) << 1;         //进位
            one = sum;
        } while (two != 0);                 //有进位继续相加
        return sum;
    }
}


