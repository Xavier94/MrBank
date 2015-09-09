package com.example.xav.mrbank;

public class ListItem {

    public static final int STATUS_NEW = 0;

    private String name;
    private int imageId;
    private int age;

    private int status;
    private int timeLeft;
    private int money;

    public ListItem(String name, int age, int imageId) {
        this.name = name;
        this.age = age;
        this.imageId = imageId;
    }

    public ListItem(String[] data) {
        this.status = Integer.parseInt(data[0]);
        this.timeLeft = Integer.parseInt(data[1]);
        this.money = Integer.parseInt(data[2]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.age;
    }
}
