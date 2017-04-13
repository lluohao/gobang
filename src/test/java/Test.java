import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by llhao on 2017/4/13.
 */
public class Test {
    @org.junit.Test
    public void test() throws IOException {
        InputStream is = Test.class.getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(is);
        System.out.println(properties.get("username"));
    }
}
