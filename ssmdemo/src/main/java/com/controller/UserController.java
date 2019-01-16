package com.controller;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/demo")
public class UserController {

     @Autowired
     private UserService userService;
     @RequestMapping(value = "queryUser")
     @ResponseBody
     public Map<String,Object> queryUser(){
          List<User> user = userService.queryUser();
          Map<String,Object> map = new HashMap<>();
          map.put("list",user);
          return map;
     }
     @GetMapping(value = "index")
     public String index(){
          return "index";
     }
}
