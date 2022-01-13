package cc.fireflyhut.girafe.advice;

import cc.fireflyhut.girafe.enums.RespCodeEnum;
import cc.fireflyhut.girafe.exception.BusinessException;
import cc.fireflyhut.girafe.pojo.dto.base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常拦截器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse globalErrorHandler(Exception e) {
        if (e instanceof BusinessException) {
            LOGGER.error("全局异常拦截：{}", e.getMessage());
            /* 全局业务异常拦截器 */
            BusinessException ex = (BusinessException)e;
            BaseResponse response = new BaseResponse();
            response.setRespCode(ex.getErrorCode());
            response.setRespMsg(ex.getErrorMsg());
            LOGGER.error("全局异常拦截响应：{}", JSONObject.toJSONString(response));
            return response;
        } else if (e instanceof MethodArgumentNotValidException) {
            LOGGER.error("全局异常拦截：{}", e.getMessage());
            /* 请求参数校验拦截器 */
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
            StringBuilder resSb = new StringBuilder();
            List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
            for (ObjectError allError : allErrors) {
                resSb.append(allError.getDefaultMessage());
                resSb.append(", ");
            }
            int tail = resSb.lastIndexOf(",");
            String resMsg = resSb.substring(0, tail >=0 ? tail : resSb.length());
            BaseResponse response = new BaseResponse();
            response.setRespCode(RespCodeEnum.PARAMETER_ERROR.getCode());
            response.setRespMsg(resMsg);
            LOGGER.error("全局异常拦截响应：{}", JSONObject.toJSONString(response));
            return response;
        } else if (e instanceof InterruptedException) {
            LOGGER.error("全局异常拦截：{}", e.getMessage());
            BaseResponse response = new BaseResponse();
            response.setRespCode(RespCodeEnum.REQUEST_TIME_OUT.getCode());
            response.setRespMsg(RespCodeEnum.REQUEST_TIME_OUT.getMsg());
            LOGGER.error("全局异常拦截响应：{}", JSONObject.toJSONString(response));
            return response;
        } else {
            LOGGER.error("全局异常拦截：{}", e.getMessage());
            BaseResponse response = new BaseResponse();
            response.setRespCode(RespCodeEnum.FAIL.getCode());
            response.setRespMsg(RespCodeEnum.FAIL.getMsg());
            LOGGER.error("全局异常拦截响应：{}", JSONObject.toJSONString(response));
            return response;
        }
    }


}
