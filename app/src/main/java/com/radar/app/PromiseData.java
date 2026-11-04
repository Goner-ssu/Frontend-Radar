package com.radar.app;

import java.util.ArrayList;

// 약속 추가 화면들에서 입력한 값을 여기에 모아둔다
public class PromiseData {
    public static String name = "";
    public static String date = "";
    public static String time = "";
    public static String place = "";
    public static ArrayList<String> friends = new ArrayList<>();
    public static String penalty = "";

    // 약속 다 만들고 나면 초기화
    public static void clear() {
        name = "";
        date = "";
        time = "";
        place = "";
        friends.clear();
        penalty = "";
    }
}
