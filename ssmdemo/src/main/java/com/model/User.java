package com.model;

public class User {

    private String name;

    private int age;

    private  String birth;

    public User() {
    }

    public User(String name, int age, String birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }
}
