package com.radar.app.data.model;
public class Ranking {

    private String name;
    private int totalLateMinutes;
    private int lateCount;
    private boolean isMe;

    public Ranking(String name, int totalLateMinutes, int lateCount, boolean isMe) {
        this.name = name;
        this.totalLateMinutes = totalLateMinutes;
        this.lateCount = lateCount;
        this.isMe = isMe;
    }

    public String getName() { return name; }
    public int getTotalLateMinutes() { return totalLateMinutes; }
    public int getLateCount() { return lateCount; }
    public boolean isMe() { return isMe; }
}