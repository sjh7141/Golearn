package kr.co.golearn.util;

import java.util.Date;

public class CommonUtils {
    public static final String BASE_URL = "http://k3a402.p.ssafy.io:8801/";
    public static final String AUTH_URL = "auth/";
    public static final String ACCOUNT_URL = "account/";
    public static final String SEARCH_URL = "search-service/";

    public static String calcTimeDate(Date date) {
        long curTime = (long) new Date().getTime();
        long time = (long) date.getTime();
        long diff = (curTime - time) / 1000 + 32397;
        System.out.println(curTime + " " + time + " " + diff);
        if (diff < 60) {
            return diff +"초 전";
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
}
