package cc.fireflyhut.girafe.service.handle;

import cc.fireflyhut.girafe.enums.GenUrlIdStrategyEnum;

/**
 * url标识处理业务
 */
public interface UrlIdHandle {


    String genUrlId(GenUrlIdStrategyEnum strategy);

}
