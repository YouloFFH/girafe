package cc.fireflyhut.girafe.pojo.dbobject;

import lombok.Data;

import java.util.Date;

/**
 * gf_url_mapping
 */
@Data
public class UrlMappingDO {
    /**
     * 主键：自增
     */
    private Long id;

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 目标链接
     */
    private String targetLink;

    /**
     * 创建该映射的机器IP
     */
    private String createIp;

    /**
     * 转发次数
     */
    private Integer forwardCount;

    /**
     * 上次转发时间
     */
    private Date lastForwardTime;

    /**
     * 是否有效：0-失效；1有效
     */
    private Integer valid;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}