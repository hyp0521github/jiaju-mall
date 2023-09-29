package com.yz.furn.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class WebUtils {
    //字符串转数字
    public static int parseInt(String num, int defaultNum) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return defaultNum;
    }

    // 将数据转为javabean
    public static <T> T copyMapToBean(Map<String, String[]> value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    // 生成当前时间
    public static String currentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
