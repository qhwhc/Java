package com.fast.core.support;

import com.fast.core.utils.StringUtils;


public class StrFormatter {
    public static final char C_BACKSLASH = '\\';
    public static final char C_DELIM_END = '}';
    public static final char C_DELIM_START = '{';
    public static final String EMPTY_JSON = "{}";

    public static String format(String strPattern, Object... argArray) {
        if (StringUtils.isEmpty(strPattern) || StringUtils.isEmpty(argArray)) {
            return strPattern;
        }
        int strPatternLength = strPattern.length();
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
        int handledPosition = 0;
        int argIndex = 0;
        while (argIndex < argArray.length) {
            int delimIndex = strPattern.indexOf(EMPTY_JSON, handledPosition);
            if (delimIndex != -1) {
                if (delimIndex <= 0 || strPattern.charAt(delimIndex - 1) != C_BACKSLASH) {
                    sbuf.append(strPattern, handledPosition, delimIndex);
                    sbuf.append(Convert.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                } else if (delimIndex <= 1 || strPattern.charAt(delimIndex - 2) != C_BACKSLASH) {
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(C_DELIM_START);
                    handledPosition = delimIndex + 1;
                } else {
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(Convert.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                }
                argIndex++;
            } else if (handledPosition == 0) {
                return strPattern;
            } else {
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }
        }
        sbuf.append(strPattern, handledPosition, strPattern.length());
        return sbuf.toString();
    }
}
