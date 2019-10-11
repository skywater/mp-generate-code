package com.jpq.mpg.gen;

import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
    
    public static void initText(String suffix, AbstractButton[] buttons, JLabel... labels) {
        Locale locale = checkLocalEnv();
        if(null != buttons && buttons.length > 0) {
            for(AbstractButton elem : buttons) {
                elem.setText(CommonElem.valueOf(elem.getText().toUpperCase()).getTxt(locale));
            }
        }
        if(null != labels && labels.length > 0) {
            if(Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) {
                suffix = convertWidthStr(suffix, false);
            } else {
                suffix = convertWidthStr(suffix, true);
            }
            for(JLabel elem : labels) {
                elem.setText(CommonElem.valueOf(elem.getText().toUpperCase()).getTxt(locale) + suffix);
            }
        }
    }
    
    /**
     * 获取当前语言环境 <br/>
     * @author jiangpeiquan
     * @return
     */
    public static Locale checkLocalEnv() {
        // Locale.SIMPLIFIED_CHINESE
        // Locale.TRADITIONAL_CHINESE
        // Locale.US
        // Locale.UK
        return Locale.getDefault();
    }
    
    /**
     * 全角半角互转
     * fullWidthStr <--> halfWidthStr <br/>
     * @author jiangpeiquan
     * @param str
     * @return
     */
    public static String convertWidthStr(String str) {
        return convertWidthStr(str, null);
    }
    public static String convertWidthStr(String str, Boolean isToHalf) {
        /*
                        半角字符是从33开始到126结束；
                        与半角字符对应的全角字符是从65281开始到65374结束；
                        其中半角的空格是32，对应的全角空格是12288；
                        半角和全角的关系很明显，除空格外的字符偏移量是65248(65281-33 = 65248)；
        */
        if(StringUtils.isBlank(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            if(null == isToHalf || isToHalf) {
                if (charIntValue >= 65281 && charIntValue <= 65374) {
                    charArray[i] = (char) (charIntValue - 65248);
                } else if (charIntValue == 12288) {
                    charArray[i] = (char) 32;
                }
            }
            if(null == isToHalf || !isToHalf) {
                if (charIntValue >= 33 && charIntValue <= 126) {
                    charArray[i] = (char) (charIntValue + 65248);
                } else if (charIntValue == 32) {
                    charArray[i] = (char) 12288;
                }
            }
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();//对Locale类实例化定义
        System.out.println(locale);
        System.out.println(locale.getLanguage());//输出系统语言代码：zh
        System.out.println(locale.getCountry());//输出系统国家代码： CN
        System.out.println(locale.getDisplayLanguage());//输出系统语言名称：中文
        System.out.println(locale.getDisplayCountry());//输出系统国家名称：中国
        // 如果系统使用的是汉语，那么getLanguage()返回的字符串为zh；
        // 然后根据getCountry()返回结果可以判断出简体还是繁体了。
        // 如果是TW，那么就是繁体了，返回CN则是简体，如果返回了HK
        if(locale.equals(Locale.SIMPLIFIED_CHINESE) || locale.equals(Locale.US) || locale.equals(Locale.UK) ) {
             System.out.println(true);
        }else {
            System.out.println(false);
        }
    }
}
