package cc.fireflyhut.girafe.pojo.dto.base;

import lombok.Data;

@Data
public class BaseResponse {

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应语
     */
    private String respMsg;

}
