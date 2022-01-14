package cc.fireflyhut.girafe.pojo.dto.request;

import cc.fireflyhut.girafe.pojo.dto.base.BaseRequest;
import lombok.Data;

@Data
public class GenApiTokenRequest extends BaseRequest {

    private String password;

    private String username;

}
