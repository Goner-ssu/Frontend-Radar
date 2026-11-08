package com.radar.app.data;

import com.radar.app.data.model.Appointment;
import com.radar.app.data.model.Participant;
import com.radar.app.data.model.Ranking;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<Ranking> getRankingList() {
        List<Ranking> list = new ArrayList<>();
        list.add(new Ranking("이가희", 87, 6, false));
        list.add(new Ranking("김세림", 64, 9, false));
        list.add(new Ranking("이현준", 41, 7, false));
        list.add(new Ranking("김종민", 35, 2, false));
        list.add(new Ranking("나", 22, 3, true));
        list.add(new Ranking("김의송", 12, 5, false));
        return list;
    }

    public static List<Appointment> getPastAppointments() {
        List<Appointment> list = new ArrayList<>();

        // 1. 홍대 저녁 모임
        List<Participant> p1 = new ArrayList<>();
        p1.add(new Participant("이가희", "18:52", -8, false));
        p1.add(new Participant("나", "18:58", -2, true));
        p1.add(new Participant("김의송", "19:07", 7, false));
        list.add(new Appointment(1, "홍대 저녁 모임", 10, "10월 11일 (일)", "19:00", "홍대입구", p1));

        // 2. 점심 약속
        List<Participant> p2 = new ArrayList<>();
        p2.add(new Participant("나", "12:25", -5, true));
        p2.add(new Participant("이현준", "12:29", -1, false));
        list.add(new Appointment(2, "점심 약속", 10, "10월 5일 (월)", "12:30", "강남역", p2));

        // 3. 동아리 뒤풀이
        List<Participant> p3 = new ArrayList<>();
        p3.add(new Participant("김세림", "17:50", -10, false));
        p3.add(new Participant("나", "17:55", -5, true));
        p3.add(new Participant("김종민", "17:58", -2, false));
        p3.add(new Participant("이가희", "18:00", 0, false));
        list.add(new Appointment(3, "동아리 뒤풀이", 10, "10월 2일 (금)", "18:00", "신촌", p3));

        // 4. 성수 카페 투어
        List<Participant> p4 = new ArrayList<>();
        p4.add(new Participant("나", "13:57", -3, true));
        p4.add(new Participant("이현준", "14:06", 6, false));
        p4.add(new Participant("김세림", "14:15", 15, false));
        list.add(new Appointment(4, "성수 카페 투어", 9, "9월 27일 (일)", "14:00", "성수", p4));

        return list;
    }
    public static Appointment findAppointmentById(int id) {
        for (Appointment a : getPastAppointments()) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
}