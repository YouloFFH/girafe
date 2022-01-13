package cc.fireflyhut.girafe.service;

import cc.fireflyhut.girafe.pojo.bo.model.GenShortLinkModel;
import cc.fireflyhut.girafe.pojo.bo.result.GenShortLinkResult;

public interface GenShortLinkService {

    GenShortLinkResult genShortLink(GenShortLinkModel model);

}
