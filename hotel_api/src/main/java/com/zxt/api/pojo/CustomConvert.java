package com.zxt.api.pojo;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/22 16:42
 */
public class CustomConvert implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {
            // 转成直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {

            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return simpleDateFormat.parse(source);
            } catch (ParseException e1) {

                System.out.println("日期转换失败->" + this.getClass().getName());
            }
        }
        // 如果参数绑定失败返回null
        return null;
    }
}
