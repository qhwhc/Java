package com.fast.core.support;

import com.fast.core.utils.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class Convert {
    public static String utf8Str(Object obj) {
        return str(obj, CharsetKit.CHARSET_UTF_8);
    }
    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof String) {
            return (String)obj;
        }
        if (obj instanceof byte[] || obj instanceof Byte[]) {
            return str(obj, charset);
        }
        if (obj instanceof ByteBuffer) {
            return str(obj, charset);
        }
        return obj.toString();
    }
    public static String str(byte[] bytes,String charset) {
        return str(bytes, StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(ByteBuffer data,String charset) {
        if (data == null) {
            return null;
        }
        return str(data,Charset.forName(charset));
    }

    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }
}
