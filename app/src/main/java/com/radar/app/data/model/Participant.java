package com.radar.app.data.model;

// 약속 참여자 한 명
public class Participant {

    private String name;        // 이름
    private String arrivalTime; // 도착 시각
    private int lateMinutes;    // 지각한 분
    private boolean isMe;

    public Participant(String name, String arrivalTime, int lateMinutes, boolean isMe) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.lateMinutes = lateMinutes;
        this.isMe = isMe;
    }

    public String getName() { return name; }
    public String getArrivalTime() { return arrivalTime; }
    public int getLateMinutes() { return lateMinutes; }
    public boolean isMe() { return isMe; }

    public boolean isLate() {
        return lateMinutes > 0;
    }
    public String getArrivalText() {
        if (isLate()) {
            return arrivalTime + " · " + lateMinutes + "분 지각";
        } else {
            return arrivalTime + " · " + Math.abs(lateMinutes) + "분 일찍";
        }
    }
}