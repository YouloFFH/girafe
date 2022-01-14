package cc.fireflyhut.girafe.pojo.dto.response;

import cc.fireflyhut.girafe.pojo.dto.base.BaseResponse;
import lombok.Data;

@Data
public class GenApiTokenResponse extends BaseResponse {

    private String token;

}
