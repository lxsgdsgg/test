package com.waper.test.service.impl;

import com.waper.test.dao.BookDao;
import com.waper.test.entity.Book;
import com.waper.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
     private BookDao bookDao;

    @Override
    public Book getById(long bookId) {
        return bookDao.queryBookById(bookId);
    }

    @Override
    public List<Book> getList(int offset, int limit) {
       return bookDao.queryAll(0,4);
    }

}
