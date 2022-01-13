package cc.fireflyhut.girafe.service.impl;

import cc.fireflyhut.girafe.dao.UrlMappingDAO;
import cc.fireflyhut.girafe.enums.AccessStatusEnum;
import cc.fireflyhut.girafe.pojo.bo.model.RecordRedirectModel;
import cc.fireflyhut.girafe.pojo.dbobject.UrlMappingDO;
import cc.fireflyhut.girafe.service.RedirectService;
import cc.fireflyhut.girafe.service.helper.RedirectHelper;
import cc.fireflyhut.girafe.util.DateTimeUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RedirectServiceImpl implements RedirectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectServiceImpl.class);

    @Autowired
    private RedirectHelper redirectHelper;

    @Autowired
    private UrlMappingDAO urlMappingDAO;

    /**
     * 通过uid找到重定向url
     * @param model 请求过来的业务对象
     * @return 目标url
     */
    @Override
    public String doRedirect(RecordRedirectModel model) {
        LOGGER.info("重定向 >> 业务实现方法doRedirect调用参数：{}", JSONObject.toJSONString(model));
        // 拿到请求时路径中的uid
        String uid = model.getUid();
        // 通过uid查数据库
        UrlMappingDO urlMappingDO = urlMappingDAO.selectByUidPassOne(uid);
        // 查不到即为访问失败
        if (urlMappingDO == null) {
            model.setStatus(AccessStatusEnum.FAIL.getValue());
            LOGGER.info("重定向 >> 访问了无效uid >> 业务终止");
            // 返回空控制层重定向到主页
            return null;
        }
        // 判断是否过期
        boolean closeFlag = false;
        Date expireTime = urlMappingDO.getExpireTime();
        if (null != expireTime) {
            int compareRes = DateTimeUtil.compareWithNowDate(expireTime);
            if (compareRes <= 0) {
                closeFlag = true;
                LOGGER.info("重定向失败，短链已过期：{}", DateTimeUtil.convertDateToFullString(expireTime));
            }
        }
        // 更新映射表记录信息
        int updateRes = redirectHelper.updateMappingRecord(uid, closeFlag);
        LOGGER.info("更新gf_url_mapping表结果：{}", updateRes);
        // 行为记录
        if (closeFlag) {
            model.setStatus(AccessStatusEnum.FAIL.getValue());
        } else {
            model.setStatus(AccessStatusEnum.SUCCESS.getValue());
        }
        int recordRes = redirectHelper.recordRedirect(model);
        LOGGER.info("插入gf_access_action_record表结果：{}", recordRes);
        // 返回目标链接
        if (closeFlag) {
            return null;
        }
        String targetUrl = urlMappingDO.getTargetLink();
        LOGGER.info("重定向成功，重定向地址：{}", targetUrl);
        return targetUrl;
    }
}
