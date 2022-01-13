package cc.fireflyhut.girafe.pojo.dto.request;

import cc.fireflyhut.girafe.constants.RegexConstants;
import cc.fireflyhut.girafe.pojo.dto.base.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 短链生成请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GenShortLinkRequest extends BaseRequest {

    /**
     * 目标链接
     */
    @NotBlank(message = "目标链接字段target不能为空")
    @Pattern(regexp = RegexConstants.URL, message = "目标链接字段target格式不正确")
    private String target;

    /**
     * 过期时间
     * 要求纯数字，单位小时，不传永久有效
     */
    @Range(min = 1, max = Integer.MAX_VALUE - 1, message = "有效期字段expire格式不正确")
    private Integer expire;

    /**
     * api token
     */
    private String token;

}
