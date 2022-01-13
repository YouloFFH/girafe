package cc.fireflyhut.girafe.enums;

/**
 * 生成uid策略枚举
 */
public enum GenUrlIdStrategyEnum {

    TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_8_8("8位时间戳随机数和8位纯随机数交叉排列", 8, 8, null, null),
    TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_9_7("9位时间戳随机数和7位纯随机数交叉排列", 9, 7, null, null),
    TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_10_6("10位时间戳随机数和6位纯随机数交叉排列", 10, 6, null, null),
    TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_11_5("11位时间戳随机数和5位纯随机数交叉排列", 11, 5, null, null),
    TS_RANDOM_NUM_18("18位时间戳随机数", 18, null, null, null);


    private String name;

    private Integer factor1;

    private Integer factor2;

    private Integer factor3;

    private Integer factor4;

    GenUrlIdStrategyEnum(String name, Integer factor1, Integer factor2, Integer factor3, Integer factor4) {
        this.name = name;
        this.factor1 = factor1;
        this.factor2 = factor2;
        this.factor3 = factor3;
        this.factor4 = factor4;
    }

    public String getName() {
        return name;
    }

    public Integer getFactor1() {
        return factor1;
    }

    public Integer getFactor2() {
        return factor2;
    }

    public Integer getFactor3() {
        return factor3;
    }

    public Integer getFactor4() {
        return factor4;
    }
}
