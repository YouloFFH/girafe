package cc.fireflyhut.girafe.controller;

import cc.fireflyhut.girafe.configure.MyAppConfigure;
import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.constants.RegexConstants;
import cc.fireflyhut.girafe.constants.ViewConstants;
import cc.fireflyhut.girafe.enums.AccessStatusEnum;
import cc.fireflyhut.girafe.pojo.bo.model.RecordRedirectModel;
import cc.fireflyhut.girafe.service.RedirectService;
import cc.fireflyhut.girafe.util.IpUtil;
import cc.fireflyhut.girafe.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 重定向控制器
 */
@Controller
public class RedirectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectController.class);

    @Autowired
    private RedirectService redirectService;
    @Autowired
    private MyAppConfigure myAppConfigure;

    @RequestMapping("/{uid}")
    public String redirect(@PathVariable("uid") String uid, HttpServletRequest request) {
        ThreadContext.put(Constants.LOG_ID, RandomUtil.genSpecialLengthRandString(8));
        LOGGER.info("重定向api >> 重定向uid:{}", uid);
        String mainSite = myAppConfigure.getMainWebUrlRoot();
        // uid为空重定向到主页
        if (StringUtils.isBlank(uid)) {
            if (StringUtils.isNotBlank(mainSite) && mainSite.matches(RegexConstants.URL)) {
                return ViewConstants.REDIRECT_FLAG + mainSite;
            }
            return ViewConstants.REDIRECT_FLAG + ViewConstants.MAIN_PAGE_LOGIC_PATH;
        }
        // 得到请求ip
        String ip = IpUtil.getIp(request);
        LOGGER.info("重定向api >> 请求ip:{}", ip);
        // 组BO参数
        RecordRedirectModel model = new RecordRedirectModel();
        model.setUid(uid);
        model.setAccessIp(ip);
        model.setStatus(AccessStatusEnum.UNKNOWN.getValue());
        // 拿到目标url
        String targetUrl = redirectService.doRedirect(model);
        // 目标url为空重定向到主页
        if (StringUtils.isBlank(targetUrl)) {
            if (StringUtils.isNotBlank(mainSite) && mainSite.matches(RegexConstants.URL)) {
                return ViewConstants.REDIRECT_FLAG + mainSite;
            }
            return ViewConstants.REDIRECT_FLAG + ViewConstants.MAIN_PAGE_LOGIC_PATH;
        }
        // 重定向到目标url
        return ViewConstants.REDIRECT_FLAG + targetUrl;
    }

}
