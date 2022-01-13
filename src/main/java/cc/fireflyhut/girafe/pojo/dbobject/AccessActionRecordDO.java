package cc.fireflyhut.girafe.pojo.dbobject;

import lombok.Data;

import java.util.Date;

/**
 * gf_access_action_record
 */
@Data
public class AccessActionRecordDO {

    /**
     * 主键：自增
     */
    private Long id;

    /**
     * 被访问短链的唯一标识
     */
    private String targetUid;

    /**
     * 访问该短链的ip
     */
    private String accessIp;

    /**
     * 访问状态：0-未知；1-成功；2-失败
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}