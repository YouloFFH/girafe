package cc.fireflyhut.girafe.pojo.dbobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * gf_api_grant_token
 */
@Data
public class ApiGrantTokenDO {
    /**
     * 主键：自增
     */
    private Long id;

    /**
     * 授权的token
     */
    private String token;

    /**
     * token的持有者
     */
    private String username;

    /**
     * 使用次数
     */
    private Integer useCount;

    /**
     * 是否有效：1-有效；0-无效
     */
    private Integer valid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}