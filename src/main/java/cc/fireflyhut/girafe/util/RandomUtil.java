package cc.fireflyhut.girafe.util;

import cc.fireflyhut.girafe.constants.Constants;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {

    /**
     * 获取指定位数的int随机数
     * @param digit 位数
     * @return int随机数
     */
    public static int getSpecialDigitRandNum(int digit) {
        if (digit <= 0) {
            return -1;
        }
        int multiplier = (int)Math.pow(10, digit);
        int res = (int)(Math.random() * multiplier);
        return res;
    }

    /**
     * 通过当前时间戳种子获取指定位数的int随机数
     * @param digit
     * @return
     */
    public static int getSpecialDigitRandNumByTimeStampSeed(int digit) {
        if (digit <= 0) {
            return -1;
        }
        long time = System.currentTimeMillis();
        Random random = new Random(time);
        int multiplier = (int)Math.pow(10, digit);
        int res = (int)(random.nextDouble() * multiplier);
        return res;
    }

    /**
     * 生成指定长度随机字符串
     * @param digit 长度
     * @return 随机字符串
     */
    public static String genSpecialLengthRandString(int digit) {
        int fullCharLength = Constants.NUMS_AND_LETTERS_CHARS.length();
        Random random = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < fullCharLength; i++) {
            int index = random.nextInt(fullCharLength);
            res.append(Constants.NUMS_AND_LETTERS_CHARS.charAt(index));
        }
        return res.toString();
    }

}
