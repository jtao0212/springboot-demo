package com.cmb.demo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static String timeStampToString(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(timestamp);
    }
}
