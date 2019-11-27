package com.yiseven.zhoudaxiao.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_FORMAT_DEFAULT_STYLE = "yyyy-MM-dd HH:mm:ss";

    public static final String DAY_FORMAT = "yyyy-MM-dd";

    public static Date stringToDate(String paramDate, String style) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
        return simpleDateFormat.parse(paramDate);
    }

    public static String dateToString(Date date, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
        return simpleDateFormat.format(date);
    }

}
