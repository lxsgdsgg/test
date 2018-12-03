package com.test;

import com.waper.test.dao.BookDao;
import com.waper.test.entity.Book;
import com.waper.test.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookTest extends BaseTest {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    @Test
    public void toQueryBookById() throws Exception {
        long bookId = 1001;
        Book book = bookDao.queryBookById(bookId);
        System.out.println(book);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book);
        }

    }
    @Test
    public void testqueryALl1(){
        Book book  = bookService.getById(1000);
        System.out.println(book);
    }
}