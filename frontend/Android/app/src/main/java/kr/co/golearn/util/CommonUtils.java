package kr.co.golearn.util;

import java.text.DecimalFormat;
import java.util.Date;

public class CommonUtils {
    public static final String BASE_URL = "http://k3a402.p.ssafy.io:8801/";
    public static final String AUTH_URL = "auth/";
    public static final String ACCOUNT_URL = "account/";
    public static final String SEARCH_URL = "search-service/";
    public static final String VIDEO_URL = "video/";
    public static final String COURSE_URL = "course/";

    public static String calcTimeDate(Date date) {
        long curTime = (long) new Date().getTime();
        long time = (long) date.getTime();
        long diff = (curTime - time) / 1000 + 32397;
        if (diff < 60) {
            return diff + "초 전";
        }
        diff /= 60;
        if (diff < 60) {
            return diff + "분 전";
        }
        diff /= 60;
        if (diff < 24) {
            return diff + "시간 전";
        }
        diff /= 24;
        if (diff < 7) {
            return diff + "일 전";
        }
        if (diff < 30) {
            return (diff / 7) + "주 전";
        }
        if (diff < 365) {
            return (diff / 30) + "달 전";
        }
        return (diff / 365) + "년 전";
    }

    public static String convertCount(int count) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return "조회수 " + decimalFormat.format(count) + "회";
    }

    public static String convertVideoTime(int length) {
        int second = length % 60;
        String resultSecond = (second < 10) ? ("0" + second) : (second + "");
        int minute = (length % 3600) / 60;
        String resultMinute = (minute < 10) ? ("0" + minute) : (minute + "");
        int hour = length / 3600;
        String resultHour = (hour < 10) ? ("0" + hour) : (hour + "");

        if (hour == 0) {
            return resultMinute + ":" + resultSecond;
        } else {
            return resultHour + ":" + resultMinute + ":" + resultSecond;
        }
    }
}
