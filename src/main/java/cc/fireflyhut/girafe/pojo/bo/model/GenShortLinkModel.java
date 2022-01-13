package cc.fireflyhut.girafe.pojo.bo.model;

import lombok.Data;

@Data
public class GenShortLinkModel {

    /**
     * 目标链接
     */
    private String target;

    /**
     * 过期时间
     * 要求纯数字，单位小时，不传永久有效
     */
    private Integer expire;

    /**
     * 访问者ip
     */
    private String ip;

    /**
     * api
     */
    private String token;

}
