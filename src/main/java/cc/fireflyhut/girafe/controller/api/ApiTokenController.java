package cc.fireflyhut.girafe.controller.api;

import cc.fireflyhut.girafe.configure.MyAppConfigure;
import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.exception.BusinessException;
import cc.fireflyhut.girafe.pojo.bo.model.GenTokenModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenTokenResult;
import cc.fireflyhut.girafe.pojo.dto.request.GenApiTokenRequest;
import cc.fireflyhut.girafe.pojo.dto.response.GenApiTokenResponse;
import cc.fireflyhut.girafe.service.helper.ApiTokenHelper;
import cc.fireflyhut.girafe.util.RandomUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTokenController.class);

    @Autowired
    private MyAppConfigure myAppConfigure;

    @Autowired
    private ApiTokenHelper apiTokenHelper;

    @PostMapping("/api/genApiToken")
    public GenApiTokenResponse genApiToken(GenApiTokenRequest request) {
        ThreadContext.put(Constants.LOG_ID, RandomUtil.genSpecialLengthRandString(8));
        LOGGER.info("生成api token >> 请求参数：{}", JSONObject.toJSONString(request));
        // 校验配置文件里的密码
        if (myAppConfigure.getGenTokenPassword().equals(request.getPassword())) {
            LOGGER.error("生成token密码错误");
            throw BusinessException.GEN_TOKEN_FAIL;
        }
        GenTokenModel model = BeanUtil.copyProperties(request, GenTokenModel.class);
        GenTokenResult result = apiTokenHelper.genToken(model);
        GenApiTokenResponse response = BeanUtil.copyProperties(result, GenApiTokenResponse.class);
        return response;
    }

}
