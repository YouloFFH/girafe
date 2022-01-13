package cc.fireflyhut.girafe.service.helper.impl;

import cc.fireflyhut.girafe.dao.AccessActionRecordDAO;
import cc.fireflyhut.girafe.dao.UrlMappingDAO;
import cc.fireflyhut.girafe.pojo.bo.model.RecordRedirectModel;
import cc.fireflyhut.girafe.pojo.dbobject.AccessActionRecordDO;
import cc.fireflyhut.girafe.pojo.dbobject.UrlMappingDO;
import cc.fireflyhut.girafe.service.helper.RedirectHelper;
import cc.fireflyhut.girafe.util.DateTimeUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectHelperImpl implements RedirectHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedirectHelperImpl.class);

    @Autowired
    private AccessActionRecordDAO accessActionRecordDAO;

    @Autowired
    private UrlMappingDAO urlMappingDAO;

    /**
     * 重定向记录表插入表业务
     * @param recordRedirectModel
     * @return
     */
    @Override
    public int recordRedirect(RecordRedirectModel recordRedirectModel) {
        AccessActionRecordDO accessActionRecordDO = new AccessActionRecordDO();
        accessActionRecordDO.setTargetUid(recordRedirectModel.getUid());
        accessActionRecordDO.setAccessIp(recordRedirectModel.getAccessIp());
        accessActionRecordDO.setStatus(recordRedirectModel.getStatus());
        accessActionRecordDO.setCreateTime(DateTimeUtil.getNowDate());
        accessActionRecordDO.setUpdateTime(DateTimeUtil.getNowDate());
        return accessActionRecordDAO.insertSelective(accessActionRecordDO);
    }

    /**
     * 更新映射表业务
     * @param uid 请求过来的uid
     * @param doClose 是否把本条映射关闭
     * @return 更新变动行
     */
    @Override
    public int updateMappingRecord(String uid, boolean doClose) {
        UrlMappingDO urlMappingDO = new UrlMappingDO();
        // 条件uid
        urlMappingDO.setUid(uid);
        // 更新当前时间戳
        if (doClose) {
            urlMappingDO.setValid(0);
        } else {
            urlMappingDO.setLastForwardTime(DateTimeUtil.getNowDate());
        }
        LOGGER.info("更新gf_url_mapping表 >> 更新参数：{}", JSONObject.toJSONString(urlMappingDO));
        return urlMappingDAO.updateByUidSelectivePassOne(urlMappingDO);
    }
}
