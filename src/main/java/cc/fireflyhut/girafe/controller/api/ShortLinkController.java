package cc.fireflyhut.girafe.controller.api;

import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.enums.RespCodeEnum;
import cc.fireflyhut.girafe.exception.BusinessException;
import cc.fireflyhut.girafe.pojo.bo.model.GenShortLinkModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenShortLinkResult;
import cc.fireflyhut.girafe.pojo.dto.request.GenShortLinkRequest;
import cc.fireflyhut.girafe.pojo.dto.response.GenShortLinkResponse;
import cc.fireflyhut.girafe.service.GenShortLinkService;
import cc.fireflyhut.girafe.util.IpUtil;
import cc.fireflyhut.girafe.util.RandomUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 生成短链接的控制器
 */
@RestController
public class ShortLinkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    private GenShortLinkService genShortLinkService;

    /**
     * post生成短链api
     * @param request 请求体
     * @return 响应体
     */
    @PostMapping("/api/genShortLink")
    public GenShortLinkResponse genShortLink(@RequestBody @Valid GenShortLinkRequest request, HttpServletRequest httpRequest) {
        ThreadContext.put(Constants.LOG_ID, RandomUtil.genSpecialLengthRandString(12));
        LOGGER.info("生成短链api >> 请求参数：{}", JSONObject.toJSONString(request));
        GenShortLinkModel model = BeanUtil.copyProperties(request, GenShortLinkModel.class);
        // 获取请求ip
        String ip = IpUtil.getIp(httpRequest);
        model.setIp(ip);
        // 调用业务逻辑
        GenShortLinkResult result = genShortLinkService.genShortLink(model);
        GenShortLinkResponse response = BeanUtil.copyProperties(result, GenShortLinkResponse.class);
        if (result != null && response != null) {
            response.setRespCode(RespCodeEnum.SUCCESS.getCode());
            response.setRespMsg(RespCodeEnum.SUCCESS.getMsg());
        } else {
            throw BusinessException.UNKNOWN_ERROR;
        }
        LOGGER.info("生成短链api >> 响应参数：{}", JSONObject.toJSONString(response));
        return response;
    }


}
