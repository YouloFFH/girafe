package cc.fireflyhut.girafe.service.impl;

import cc.fireflyhut.girafe.configure.MyAppConfigure;
import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.dao.ApiGrantTokenDAO;
import cc.fireflyhut.girafe.dao.UrlMappingDAO;
import cc.fireflyhut.girafe.enums.GenUrlIdStrategyEnum;
import cc.fireflyhut.girafe.exception.BusinessException;
import cc.fireflyhut.girafe.pojo.bo.model.GenShortLinkModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenShortLinkResult;
import cc.fireflyhut.girafe.pojo.dbobject.UrlMappingDO;
import cc.fireflyhut.girafe.service.GenShortLinkService;
import cc.fireflyhut.girafe.service.handle.UrlIdHandle;
import cc.fireflyhut.girafe.service.helper.ApiTokenHelper;
import cc.fireflyhut.girafe.util.DateTimeUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class GenShortLinkServiceImpl implements GenShortLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenShortLinkServiceImpl.class);

    @Autowired
    private UrlIdHandle urlIdHandle;

    @Autowired
    private UrlMappingDAO urlMappingDAO;

    @Autowired
    private MyAppConfigure myAppConfigure;

    @Autowired
    private ApiTokenHelper apiTokenHelper;

    private final Date lastReqTime;

    // 公平的可重入锁
    private static final Lock lock = new ReentrantLock(true);

    public GenShortLinkServiceImpl() {
        lastReqTime = new Date(System.currentTimeMillis());
    }

    /**
     * 生成短链业务逻辑
     * @param model 请求体
     * @return
     */
    @Override
    @Transactional
    public GenShortLinkResult genShortLink(GenShortLinkModel model) {
        LOGGER.info("生成短链 >> 业务方法genShortLink调用参数：{}", JSONObject.toJSONString(model));
        String token = model.getToken();
        try {
            // 加锁防并发，以防生成相同的uid导致数据库异常
            lock.tryLock(10, TimeUnit.SECONDS);
            /* 1.判断间隔时间 */
            if (StringUtils.isBlank(token)) {
                checkAccessInterval();
            } else {
                // 检查token
                boolean effectiveToken = apiTokenHelper.checkToken(token);
                if (!effectiveToken) {
                    LOGGER.error("上传的token错误：{}", token);
                    throw BusinessException.TOKEN_ERROR;
                }
            }
            lastReqTime.setTime(System.currentTimeMillis());
            /* 2.获取一系列参数 */
            Integer expireTime = model.getExpire();
            String target = model.getTarget();
            Date expireDate = null;
            if (null != expireTime) {
                expireDate = DateTimeUtil.getDateOffsetHour(expireTime);
            }
            String ip = model.getIp();
            /* 3.生成uid */
            String uid = null;
            uid = getUrlIdByStrategy();
            /* 4.持久化 */
            // 组数据库对象参数
            UrlMappingDO urlMappingDO = buildUrlMapping(uid, target, ip, expireDate);
            // 插入数据库
            int insertRes = urlMappingDAO.insertSelective(urlMappingDO);
            LOGGER.info("插入gf_url_mapping表结果：{}", insertRes);
            /* 5.组返回参数 */
            String webRoot = myAppConfigure.getShortWebUrlRoot();
            String shortLink = webRoot + uid;
            GenShortLinkResult result = buildGenShortLinkResult(shortLink, expireDate);
            LOGGER.info("生成短链 >> 业务方法genShortLink返回结果：{}", JSONObject.toJSONString(result));
            return result;
        } catch (InterruptedException e) {
            LOGGER.error("生成短链 >> 持有锁超时:{}", e.getMessage());
            throw BusinessException.UNKNOWN_ERROR;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 检查访问时间间隔是否达到预设
     */
    private void checkAccessInterval() {
        if (DateTimeUtil.offsetSecondByNow(lastReqTime) < myAppConfigure.getApiReqInterval()) {
            LOGGER.warn("未达到api请求间隔时间");
            throw BusinessException.SERVER_BUSY;
        }
        lastReqTime.setTime(System.currentTimeMillis());
    }

    /**
     * 通过预设策略得到uid
     * @return
     */
    private String getUrlIdByStrategy() {
        int strategyCount = 0;
        GenUrlIdStrategyEnum strategy = Constants.genUidStrategies[strategyCount];
        String uid = urlIdHandle.genUrlId(strategy);
        LOGGER.info("首次生成的uid为：{}", uid);
        // uid 查重
        int count = urlMappingDAO.checkDuplicate(uid);
        for (strategyCount++; count > 0; strategyCount++) {
            // 变更策略
            strategy = Constants.genUidStrategies[strategyCount];
            LOGGER.info("生成uid重复，变更策略为：{}", strategy.getName());
            // 再次获取
            uid = urlIdHandle.genUrlId(strategy);
            LOGGER.info("再次生成的uid为：{}", uid);
            // 再次查重
            count = urlMappingDAO.checkDuplicate(uid);
            // 越界归位
            if (strategyCount >= Constants.genUidStrategies.length) {
                strategyCount = -1;
            }
        }
        LOGGER.info("最终结果的uid为：{}", uid);
        return uid;
    }

    /**
     * 组装数据库对象
     * @param uid 唯一标识
     * @param target 目标链接
     * @param ip 请求ip
     * @param expireTime 过期时间
     * @return 数据库对象
     */
    private UrlMappingDO buildUrlMapping(String uid, String target, String ip, Date expireTime) {
        UrlMappingDO urlMappingDO = new UrlMappingDO();
        urlMappingDO.setUid(uid);
        urlMappingDO.setTargetLink(target);
        urlMappingDO.setCreateIp(ip);
        urlMappingDO.setForwardCount(Constants.INTEGER_0);
        urlMappingDO.setLastForwardTime(null);
        urlMappingDO.setValid(Constants.INTEGER_1);
        urlMappingDO.setExpireTime(expireTime);
        urlMappingDO.setCreateTime(DateTimeUtil.getNowDate());
        urlMappingDO.setUpdateTime(DateTimeUtil.getNowDate());
        return urlMappingDO;
    }

    private GenShortLinkResult buildGenShortLinkResult(String shortLink, Date expireTime) {
        GenShortLinkResult result = new GenShortLinkResult();
        result.setShortLink(shortLink);
        if (expireTime != null) {
            String expireStr = DateTimeUtil.convertDateToFullString(expireTime);
            result.setExpire(expireStr);
        }
        return result;
    }
}
