package cc.fireflyhut.girafe.dao;

import cc.fireflyhut.girafe.dao.mapper.UrlMappingMapper;
import cc.fireflyhut.girafe.pojo.dbobject.UrlMappingDO;
import cc.fireflyhut.girafe.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UrlMappingDAO {

    @Autowired
    private UrlMappingMapper urlMappingMapper;

    /**
     * 选择插入urlMapping表
     * @param urlMappingDO
     * @return
     */
    public int insertSelective(UrlMappingDO urlMappingDO) {
        return urlMappingMapper.insertSelective(urlMappingDO);
    }

    /**
     * uid查重
     * @param uid
     * @return
     */
    public int checkDuplicate(String uid) {
        return urlMappingMapper.countByUid(uid);
    }

    /**
     * 一次访问后通过uid更新
     * @param urlMappingDO
     * @return
     */
    public int updateByUidSelectivePassOne(UrlMappingDO urlMappingDO) {
        urlMappingDO.setUpdateTime(DateTimeUtil.getNowDate());
        return urlMappingMapper.updateByUidSelectivePassOne(urlMappingDO);
    }

    /**
     * 一次请求：通过uid查找
     * @param uid
     * @return
     */
    public UrlMappingDO selectByUidPassOne(String uid) {
        return urlMappingMapper.selectByUidPassOne(uid);
    }

}
