package com.example.homecaredoctor;

public class Pill {
    private int pillId;
    private String pillName;
    private int[] taken = new int[3];

    public Pill() {
        this.pillName = getPillNameFromId(1);
        this.taken[0] = 1;
        this.taken[1] = 1;
        this.taken[2] = 1;
    }

    public Pill(int pillId, int[] taken) {
        this.pillId = pillId;
        this.pillName = getPillNameFromId(pillId);
        this.taken = taken;
    }

    // TODO: get pillname from id
    private String getPillNameFromId(int pillId) {
        return "Fluoxetin (hydroclorid)";
    }
}
