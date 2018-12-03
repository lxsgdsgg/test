package com.waper.test.controller;

import com.waper.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
        @Autowired
        private BookService bookService;

        @RequestMapping(value="enterInx")
        @ResponseBody
        public void enterInx(){
            System.out.println("hellow");
        }
}
