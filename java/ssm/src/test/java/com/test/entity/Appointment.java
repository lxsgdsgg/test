package com.test.entity;

import com.waper.test.entity.Book;

import java.util.Date;

public class Appointment {

    private long bookId;// 图书ID

    private long studentId;// 学号

    private Date appointTime;// 预约时间

    // 多对一的复合属性
    private Book book;
}