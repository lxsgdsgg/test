package com.waper.test.service;

import com.waper.test.entity.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> getList(int offset,int limit);

}