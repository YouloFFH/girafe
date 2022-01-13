package cc.fireflyhut.girafe.service.helper;

import cc.fireflyhut.girafe.pojo.bo.model.RecordRedirectModel;

public interface RedirectHelper {

    int recordRedirect(RecordRedirectModel recordRedirectModel);

    int updateMappingRecord(String uid, boolean doClose);

}
