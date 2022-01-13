package cc.fireflyhut.girafe.pojo.bo.result;

import lombok.Data;

@Data
public class GenShortLinkResult {

    /**
     * 生成的短链
     */
    private String shortLink;

    /**
     * 失效时间 格式：yyyy-MM-dd HH:mm:ss
     */
    private String expire;
}
