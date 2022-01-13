package cc.fireflyhut.girafe.dao;

import cc.fireflyhut.girafe.dao.mapper.AccessActionRecordMapper;
import cc.fireflyhut.girafe.pojo.dbobject.AccessActionRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessActionRecordDAO {

    @Autowired
    private AccessActionRecordMapper accessActionRecordMapper;

    /**
     * 选择插入
     * @param accessActionRecordDO
     * @return
     */
    public int insertSelective(AccessActionRecordDO accessActionRecordDO) {
        return accessActionRecordMapper.insertSelective(accessActionRecordDO);
    }

}
