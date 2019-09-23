package com.fast.framework.piinyin4j;

import com.fast.core.utils.StringUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PinyinUtils {
    private static final Logger LOG = LoggerFactory.getLogger(PinyinUtils.class);

    public static String getPinYinHeadChar(String str, int len) throws Exception {
        if (StringUtils.isEmpty(str)) {
            LOG.error("字符为空，不能生成拼音！，请检查信息!");
            throw new Exception("字符为空!");
        } else {
            if (str.length() > len) {
                str = str.substring(0, len);
            }

            StringBuilder convert = new StringBuilder();

            for (int j = 0; j < str.length(); ++j) {
                char word = str.charAt(j);
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
                if (pinyinArray != null) {
                    convert = convert.append(pinyinArray[0].charAt(0));
                } else {
                    convert = convert.append(word);
                }
            }
            return convert.toString();
        }
    }

    public static String getPinYinAllCharWithTone(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            LOG.error("字符为空，不能生成拼音！，请检查信息!");
            throw new Exception("字符为空!");
        } else {
            StringBuilder convert = new StringBuilder();

            for (int j = 0; j < str.length(); ++j) {
                char word = str.charAt(j);
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
                if (pinyinArray != null) {
                    convert = convert.append(pinyinArray[0]);
                } else {
                    convert = convert.append(word);
                }
            }

            return convert.toString();
        }
    }

    public static String getPinYinAllChar(String src) throws Exception {
        if (StringUtils.isEmpty(src)) {
            LOG.error("字符为空，不能生成拼音！，请检查信息!");
            throw new Exception("字符为空!");
        } else {
            char[] t1 = src.toCharArray();
            String[] t2 = new String[t1.length];
            HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
            t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            t3.setVCharType(HanyuPinyinVCharType.WITH_V);
            String result = "" ;
            int t0 = t1.length;

            try {
                for (int i = 0; i < t0; ++i) {
                    if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                        result = result + t2[0];
                    } else {
                        result = result + Character.toString(t1[i]);
                    }
                }
            } catch (Exception var7) {
                LOG.error("字符转换错误!");
                var7.printStackTrace();
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getPinYinHeadChar("你好", 2));
        System.out.println(getPinYinAllChar("你好3h明报"));
        System.out.println("你好啊".length());
        System.out.println("你好啊".substring(0, 3));
    }
}
