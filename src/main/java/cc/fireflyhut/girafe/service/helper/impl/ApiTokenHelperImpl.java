package cc.fireflyhut.girafe.service.helper.impl;

import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.dao.ApiGrantTokenDAO;
import cc.fireflyhut.girafe.exception.BusinessException;
import cc.fireflyhut.girafe.pojo.bo.model.GenTokenModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenTokenResult;
import cc.fireflyhut.girafe.pojo.dbobject.ApiGrantTokenDO;
import cc.fireflyhut.girafe.service.helper.ApiTokenHelper;
import cc.fireflyhut.girafe.util.RandomUtil;
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
        String token = RandomUtil.genSpecialLengthRandLowString(16);
        String username = model.getUsername();
        // 组数据库参数
        ApiGrantTokenDO apiGrantTokenDO = new ApiGrantTokenDO();
        apiGrantTokenDO.setToken(token);
        apiGrantTokenDO.setUsername(username);
        apiGrantTokenDO.setValid(Constants.INTEGER_1);
        // 入库
        int res = apiGrantTokenDAO.insertNewToken(apiGrantTokenDO);
        LOGGER.info("新建token入表结果：{}", res);
        if (res <= 0) {
            LOGGER.error("新建token入表失败");
            throw BusinessException.GEN_TOKEN_FAIL;
        }
        // 返回参数
        GenTokenResult result = new GenTokenResult();
        result.setToken(token);
        return result;
    }
}
