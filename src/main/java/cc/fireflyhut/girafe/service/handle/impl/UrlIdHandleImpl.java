package cc.fireflyhut.girafe.service.handle.impl;

import cc.fireflyhut.girafe.constants.Constants;
import cc.fireflyhut.girafe.enums.GenUrlIdStrategyEnum;
import cc.fireflyhut.girafe.service.handle.UrlIdHandle;
import cc.fireflyhut.girafe.util.ConvertUtil;
import cc.fireflyhut.girafe.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UrlIdHandleImpl implements UrlIdHandle {

    /**
     * 生成uid
     * @return
     */
    @Override
    public String genUrlId(GenUrlIdStrategyEnum strategy) {
        String uid = null;
        switch (strategy) {
            case TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_8_8:
                uid = genUidByTsAndPureRandNumCross(8, 8);
                break;
            case TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_9_7:
                uid = genUidByTsAndPureRandNumCross(9, 7);
                break;
            case TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_10_6:
                uid = genUidByTsAndPureRandNumCross(10, 6);
                break;
            case TS_RANDOM_NUM_AND_PURE_RANDOM_NUM_CROSS_11_5:
                uid = genUidByTsAndPureRandNumCross(11, 5);
                break;
            case TS_RANDOM_NUM_18:
                uid = genUidByTsAndPureRandNumCross(18, 0);
                break;
            default:
                uid = genUidByTsAndPureRandNumCross(0, 18);
        }
        return uid;
    }

    /**
     * 时间戳种子随机数和纯随机数交叉叠加后62进制转换生成uid
     * @param tsLength 时间戳种子随机数长度
     * @param pureLength 纯随机数长度
     * @return 两者交叉叠加后生生成的uid
     */
    private String genUidByTsAndPureRandNumCross(int tsLength, int pureLength) {
        if (tsLength <= 0 && pureLength <= 0) {
            return null;
        }
        String randNum1 = String.valueOf(RandomUtil.getSpecialDigitRandNumByTimeStampSeed(tsLength));
        String randNum2 = String.valueOf(RandomUtil.getSpecialDigitRandNum(pureLength));
        if (Constants.STRING_NG1.equals(randNum1)) {
            randNum1 = null;
        } else if (Constants.STRING_NG1.equals(randNum2)) {
            randNum2 = null;
        }
        String idSeed = disruptBothByCross(randNum1, randNum2);
        String urlId = ConvertUtil.encode10To62(new BigInteger(idSeed));
        return urlId;
    }

    /**
     * 将两个字符串的字符交叉生成新的字符串，第一个字符串的第一个字符位新字符串的第一个字符
     * @param s1
     * @param s2
     * @return 交叉后的字符串
     */
    private String disruptBothByCross(String s1, String s2) {
        if (StringUtils.isBlank(s1) && StringUtils.isNotBlank(s2)) {
            return s2;
        } else if (StringUtils.isBlank(s2) && StringUtils.isNotBlank(s1)) {
            return s1;
        } else if (StringUtils.isBlank(s2) && StringUtils.isBlank(s1)) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int time = Math.min(s1.length(), s2.length());
        for (int i = 0; i < time; i++) {
            res.append(s1.charAt(i));
            res.append(s2.charAt(i));
        }
        if (s1.length() == s2.length()) {
            return res.toString();
        } else if (time == s1.length()) {
            res.append(s2.substring(time, s2.length()));
        } else {
            res.append(s1.substring(time, s1.length()));
        }
        return res.toString();
    }
}
