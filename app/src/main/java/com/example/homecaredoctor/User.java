package com.example.homecaredoctor;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String name, username, password;
    private ArrayList<String> treatments;
    public dob getDate() {
        return date;
    }

    public void setDate(dob date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class dob {
        private int m, d, y;

        public dob(int m, int d, int y) {
            this.m = m;
            this.d = d;
            this.y = y;
        }
        public dob() {}


        public int getM() {
            return m;
        }

        public void setM(int m) {
            this.m = m;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    };
    private int age;
    private dob date;
    public User() {

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
