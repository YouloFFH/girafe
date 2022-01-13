package cc.fireflyhut.girafe;

import cc.fireflyhut.girafe.pojo.dto.response.GenShortLinkResponse;
import cc.fireflyhut.girafe.service.handle.UrlIdHandle;
import cc.fireflyhut.girafe.util.ConvertUtil;
import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Random;

@SpringBootTest
class GirafeApplicationTests {

    @Autowired
    UrlIdHandle urlIdHandle;

    @Test
    void contextLoads() {
    }


    @Test
    public void test1() {
        Random r = new Random();
        int ra = r.nextInt(10);
        System.out.println(ra);
    }

    @Test
    public void testl() {
        Object result = null;
        GenShortLinkResponse response = BeanUtil.copyProperties(result, GenShortLinkResponse.class);
        System.out.println(response);
    }

    @Test
    public void testseed() {
        long t = System.currentTimeMillis();  // 获得当前时间的毫秒数
        Random rd = new Random(t);  // 作为种子数传入到Random的构造器中
        System.out.println(rd.nextDouble());  // 生成随即整数
    }

    @Test
    public void testcorv() {
        System.out.println(ConvertUtil.encode10To62(new BigInteger("63")));
    }

}
