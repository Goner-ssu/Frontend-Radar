package com.radar.app.data.model;

import java.util.List;

// 끝난 약속 하나
public class Appointment {

    private int id;                         // 구분용 번호
    private String title;                   // 약속 이름
    private int month;                      // 월 (월별로 묶을 때 사용)
    private String date;                    // 날짜
    private String time;                    // 약속 시간
    private String place;                   // 장소
    private List<Participant> participants; // 참여자 목록 (도착 순서대로)

    public Appointment(int id, String title, int month, String date, String time,
                       String place, List<Participant> participants) {
        this.id = id;
        this.title = title;
        this.month = month;
        this.date = date;
        this.time = time;
        this.place = place;
        this.participants = participants;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getMonth() { return month; }
    public List<Participant> getParticipants() { return participants; }

    public String getDateTimePlace() {
        return date + " · " + time + " · " + place;
    }

    public int getLateCount() {
        int count = 0;
        for (Participant p : participants) {
            if (p.isLate()) {
                count++;
            }
        }
        return count;
    }

    public int getOnTimeCount() {
        return participants.size() - getLateCount();
    }
}