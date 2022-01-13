package cc.fireflyhut.girafe.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 全局配置文件
 */
@Component
public class MyAppConfigure {

    @Value("${girafe.shortWebUrlRoot}")
    private String shortWebUrlRoot;

    @Value("${girafe.mainWebUrlRoot}")
    private String mainWebUrlRoot;

    @Value("${girafe.apiReqInterval}")
    private Integer apiReqInterval;

    @Value("@{girafe.genTokenPassword}")
    private String genTokenPassword;

    public String getShortWebUrlRoot() {
        return shortWebUrlRoot;
    }

    public String getMainWebUrlRoot() {
        return mainWebUrlRoot;
    }

    public Integer getApiReqInterval() {
        return apiReqInterval;
    }

    public String getGenTokenPassword() {
        return genTokenPassword;
    }
}
