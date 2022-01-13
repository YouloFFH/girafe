package cc.fireflyhut.girafe.dao.mapper;

import cc.fireflyhut.girafe.pojo.dbobject.UrlMappingDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UrlMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UrlMappingDO record);

    int insertSelective(UrlMappingDO record);

    UrlMappingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UrlMappingDO record);

    int updateByPrimaryKey(UrlMappingDO record);

    int countByUid(@Param("uid") String uid);

    /**
     * 一次请求：修改数据库
     * @param record
     * @return
     */
    int updateByUidSelectivePassOne(UrlMappingDO record);

    /**
     * 一次请求：通过uid查询
     * @param uid
     * @return
     */
    UrlMappingDO selectByUidPassOne(@Param("uid") String uid);
}