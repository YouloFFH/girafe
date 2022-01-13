package cc.fireflyhut.girafe.util;

import cc.fireflyhut.girafe.constants.Constants;

import java.math.BigInteger;

/**
 * 进制转换工具类
 */
public class ConvertUtil {

    // 62进制
    private static final BigInteger SCALE_62 = new BigInteger("62");
    // 公共变量1
    private static final BigInteger BIGINT_1 = new BigInteger("1");

    public static String encode10To62(BigInteger num) {
        // 结果集
        StringBuilder resultSb = new StringBuilder();
        // num > scale - 1
        while (num.compareTo(SCALE_62.subtract(BIGINT_1)) > 0) {
            // 对62取余
            BigInteger remainder = num.mod(SCALE_62);
            // 将转换后的字符加入结果集，此时是倒序
            resultSb.append(Constants.NUMS_AND_LETTERS_CHARS.charAt(remainder.intValue()));
            // 除以进制数，获取下一个末尾数
            num = num.divide(SCALE_62);
        }
        // 把最前面以为加入结果集
        resultSb.append(Constants.NUMS_AND_LETTERS_CHARS.charAt(num.intValue()));
        // 翻转
        String result = resultSb.reverse().toString();
        return result;
    }

}
