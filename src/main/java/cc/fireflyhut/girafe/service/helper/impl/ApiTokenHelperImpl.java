package cc.fireflyhut.girafe.service.helper.impl;

import cc.fireflyhut.girafe.dao.ApiGrantTokenDAO;
import cc.fireflyhut.girafe.pojo.bo.model.GenTokenModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenTokenResult;
import cc.fireflyhut.girafe.service.helper.ApiTokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiTokenHelperImpl implements ApiTokenHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTokenHelperImpl.class);

    @Autowired
    private ApiGrantTokenDAO apiGrantTokenDAO;

    /**
     * 校验token
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        int res = apiGrantTokenDAO.useToken(token);
        LOGGER.info("使用token时更新结果：{}", res);
        return res > 0;
    }

    /**
     * 生成token
     * @param model
     * @return
     */
    @Override
    public GenTokenResult genToken(GenTokenModel model) {
        return null;
    }
}
