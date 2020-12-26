package com.example.homecaredoctor;

public class Pill {
    private int pillId;
    private int[] taken = new int[3];
    public String[] time = {"sáng", "trưa", "chiều"};

    public Pill() {
        this.taken[0] = 1;
        this.taken[1] = 1;
        this.taken[2] = 1;
    }

    public Pill(int pillId, int[] taken) {
        this.pillId = pillId;
        this.taken = taken;
    }

    // TODO: get pillname and counter type from this.pillId
    public String getPillName() {
        return "Fluoxetin (hydroclorid)";
    }
    public String getCounter() { return "viên";}

    public String toString() {
        String counter = getCounter();
        String ans = new String();
        for (int i = 0; i < 3; ++i) {
            if (taken[i] > 0)
                ans.concat(Integer.toString(taken[i]) + " " + counter + " buổi " + time[i] + "    ");
        }
        return ans;
    }
}
