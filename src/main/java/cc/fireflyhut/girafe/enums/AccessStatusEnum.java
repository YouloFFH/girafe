package cc.fireflyhut.girafe.enums;

/**
 * 访问记录表状态枚举
 */
public enum AccessStatusEnum {

    UNKNOWN(0, "未知"),
    SUCCESS(1, "成功"),
    FAIL(2, "失败");

    private int value;

    private String describe;

    AccessStatusEnum(int value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public int getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }
}
