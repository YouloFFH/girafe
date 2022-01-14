package cc.fireflyhut.girafe.exception;

import cc.fireflyhut.girafe.enums.RespCodeEnum;

public class BusinessException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 未知错误
     */
    public static final BusinessException UNKNOWN_ERROR = new BusinessException(RespCodeEnum.FAIL.getCode(), RespCodeEnum.FAIL.getMsg());
    /**
     * 服务器忙
     */
    public static final BusinessException SERVER_BUSY = new BusinessException(RespCodeEnum.SERVER_BUSY.getCode(), RespCodeEnum.SERVER_BUSY.getMsg());
    /**
     * token错误
     */
    public static final BusinessException TOKEN_ERROR = new BusinessException(RespCodeEnum.TOKEN_ERROR.getCode(), RespCodeEnum.TOKEN_ERROR.getMsg());

    /* get set */
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
