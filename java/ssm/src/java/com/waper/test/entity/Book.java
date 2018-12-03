package com.waper.test.entity;

import lombok.Data;

@Data
public class Book {

    private long bookId;// 图书ID

    private String name;// 图书名称

    private int number;// 馆藏数量
}
