package com.fast.core.support;

import com.fast.core.utils.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharsetKit {

    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final String GBK = "GBK";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
    public static final Charset CHARSET_GBK = Charset.forName(GBK);
    public static final Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);

    public static Charset charset(String charset) {
        if (StringUtils.isEmpty(charset)) {
            return Charset.defaultCharset();
        }
        return Charset.forName(charset);
    }

    public static String convert(String source, String srcCharset, String destCharset) {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    public static String convert(String source, Charset srcCharset, Charset destCharset) {
        if (srcCharset == null) {
            srcCharset = StandardCharsets.ISO_8859_1;
        }
        if (destCharset == null) {
            srcCharset = StandardCharsets.UTF_8;
        }
        return (StringUtils.isEmpty(source) || srcCharset.equals(destCharset)) ? source : new String(source.getBytes(srcCharset), destCharset);
    }

    public static String systemCharset() {
        return Charset.defaultCharset().name();
    }
}
