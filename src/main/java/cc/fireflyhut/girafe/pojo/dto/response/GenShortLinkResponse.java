package cc.fireflyhut.girafe.pojo.dto.response;

import cc.fireflyhut.girafe.pojo.dto.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenShortLinkResponse extends BaseResponse {

    /**
     * 生成的短链
     */
    private String shortLink;

    /**
     * 失效时间 格式：yyyy-MM-dd HH:mm:ss
     * 为空为永久
     */
    private String expire;

}
