package cc.fireflyhut.girafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cc.fireflyhut.girafe.dao.mapper")
public class GirafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GirafeApplication.class, args);
    }

}
