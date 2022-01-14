package cc.fireflyhut.girafe.dao;

import cc.fireflyhut.girafe.dao.mapper.ApiGrantTokenMapper;
import cc.fireflyhut.girafe.pojo.dbobject.ApiGrantTokenDO;
import cc.fireflyhut.girafe.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiGrantTokenDAO {

    @Autowired
    ApiGrantTokenMapper apiGrantTokenMapper;

    /**
     * 使用token
     * @param token
     * @return
     */
    public int useToken(String token) {
        ApiGrantTokenDO updateDo = new ApiGrantTokenDO();
        updateDo.setToken(token);
        updateDo.setUpdateTime(DateTimeUtil.getNowDate());
        return apiGrantTokenMapper.updateByTokenWhenUse(updateDo);
    }

}
