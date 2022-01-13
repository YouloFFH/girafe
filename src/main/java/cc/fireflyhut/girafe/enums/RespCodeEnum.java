package cc.fireflyhut.girafe.enums;

public enum RespCodeEnum {

    SUCCESS("0000", "处理成功"),
    FAIL("9999", "未知错误"),

    REQUEST_TIME_OUT("1001", "请求超时"),
    PARAMETER_ERROR("1002", "参数错误"),
    SERVER_BUSY("1003", "服务器忙");

    private String code;

    private String msg;

    RespCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
