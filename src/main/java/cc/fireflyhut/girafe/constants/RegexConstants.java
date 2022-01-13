package cc.fireflyhut.girafe.constants;

public class RegexConstants {

    /**
     * ipv4
     */
    public static final String IP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

    /**
     * url
     */
    public static final String URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    /**
     * 正整数
     */
    public static final String POSITIVE_INTEGER = "^[0-9]*[1-9][0-9]*$";
}
