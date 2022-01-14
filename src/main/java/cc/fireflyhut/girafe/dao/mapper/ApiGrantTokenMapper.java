package cc.fireflyhut.girafe.dao.mapper;

import cc.fireflyhut.girafe.pojo.dbobject.ApiGrantTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiGrantTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApiGrantTokenDO record);

    int insertSelective(ApiGrantTokenDO record);

    ApiGrantTokenDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiGrantTokenDO record);

    int updateByPrimaryKey(ApiGrantTokenDO record);

    /**
     * 通过token更新
     * @param record
     * @return
     */
    int updateByTokenWhenUse(ApiGrantTokenDO record);
}