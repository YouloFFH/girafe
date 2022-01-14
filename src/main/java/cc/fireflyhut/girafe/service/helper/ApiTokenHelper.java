package cc.fireflyhut.girafe.service.helper;

import cc.fireflyhut.girafe.pojo.bo.model.GenTokenModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenTokenResult;

/**
 * api token操作业务类
 */
public interface ApiTokenHelper {

    /**
     * 校验token
     * @param token
     * @return
     */
    boolean checkToken(String token);

    /**
     * 生成token
     * @param model
     * @return
     */
    GenTokenResult genToken(GenTokenModel model);

}
