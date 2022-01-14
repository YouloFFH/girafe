package cc.fireflyhut.girafe.constants;

import cc.fireflyhut.girafe.enums.GenUrlIdStrategyEnum;

public class Constants {

    public static final String UNKNOWN = "unknown";

    /**
     * 非正常格式本地ip
     */
    public static final String LOCALHOST_ABNORMAL_IP = "0:0:0:0:0:0:0:1";

    /**
     * 本机
     */
    public static final String LOCALHOST_IP = "127.0.0.1";

    public static final String LOG_ID = "logId";

    /**
     * uid生成策略枚举数组
     */
    public static final GenUrlIdStrategyEnum[] genUidStrategies = GenUrlIdStrategyEnum.values();

    /**
     * 所有的数字和字母
     */
    public static final String NUMS_AND_LETTERS_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 所有的数字和小写字母
     */
    public static final String NUMS_AND_LOWER_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 公共数字常量-1
     */
    public static final String STRING_NG1 = "-1";
    public static final Integer INTEGER_NG1 = -1;
    /**
     * 公共数字常量0
     */
    public static final String STRING_0 = "0";
    public static final Integer INTEGER_0 = 0;
    /**
     * 公共数字常量1
     */
    public static final String STRING_1 = "1";
    public static final Integer INTEGER_1 = 1;
    /**
     * 公共数字常量2
     */
    public static final String STRING_2 = "2";
    public static final Integer INTEGER_2 = 2;
}
