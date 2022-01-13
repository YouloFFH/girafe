package cc.fireflyhut.girafe.dao.mapper;

import cc.fireflyhut.girafe.pojo.dbobject.AccessActionRecordDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessActionRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccessActionRecordDO record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(AccessActionRecordDO record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    AccessActionRecordDO selectByPrimaryKey(Long id);

    /**
     * 根据主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AccessActionRecordDO record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(AccessActionRecordDO record);
}