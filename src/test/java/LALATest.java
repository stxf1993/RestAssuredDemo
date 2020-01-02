
import org.testng.annotations.Test;

/**
 * Created by xiaofenShentu on 2019/12/30 16:25
 * 递归实现n！
 */
public class LALATest {

    public static int testDigui(int n){
        int m;
        if (n==0)return 1;
        else {
            m=n*testDigui(n-1);
            return m;
        }
    }

    public static void main(String[] args) {

        System.out.println(LALATest.testDigui(3));
    }
}
